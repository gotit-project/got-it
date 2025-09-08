<%@ page contentType="text/html;charset=utf-8" import="java.sql.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>


<!-- ======================================
	JSP에서 정적 리소스 경로를 쓸 때는 반드
	${pageContext.request.contextPath}를 앞에 붙이기
	현재 애플리케이션의 컨텍스트 경로를 반환
======================================= -->

<!DOCTYPE html>
<html lang="ko">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Got !t</title>
        <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/global.css">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/common/reset.css">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/common/header.css">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/common/footer.css">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/index.css">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/mypage.css">
        <script src="${pageContext.request.contextPath}/assets/js/common/header.js" defer></script>
        <script src="${pageContext.request.contextPath}/assets/js/index.js" defer></script>
        <script src="${pageContext.request.contextPath}/assets/js/mypage.js" defer></script>
    </head>
    <body>
    	<%-- 안전 기본값 --%>
		<c:set var="curPage" value="${empty curPage ? 1 : curPage}" />
		<c:set var="totalPage" value="${empty totalPage ? 1 : totalPage}" />
		<%-- 현재 선택된 경로 --%>
		<c:set var="ctx" value="${pageContext.request.contextPath}" />
		<c:set var="mode" value="${empty param.mode ? 'list' : param.mode}" />
		<c:set var="isList"  value="${mode eq 'list'}" />
		<c:set var="isScrap" value="${mode eq 'scrap'}" />

        <%@ include file="/WEB-INF/views/common/header.jsp" %>
        
          <div id="mypage" class="content-wrap">
	        <!-- 프로필 헤더 -->
	       <!-- 프로필 헤더 -->
<div class="profile-header">
  <div class="profile-info">
    <h1>${sessionScope.loginOkUser.nickname}</h1>
    <p>아직 소개를 작성하지 않았습니다.</p>

    <div class="point-box">
      <img src="${pageContext.request.contextPath}/assets/img/common/bx-medal.png" alt="포인트">
      <p>${sessionScope.loginOkUser.point}</p>
    </div>

    <span class="badge ${sessionScope.loginOkUser.badgeName}">
      ${sessionScope.loginOkUser.badgeName}
    </span>
  </div>

  <div class="profile-avatar-wrap">
    <div class="profile-avatar">
      <!-- BUGFIX: post.imgName -> sessionScope.loginOkUser.imgName -->
      <img
        src="${pageContext.request.contextPath}/avatar?img=${sessionScope.loginOkUser.imgName}&v=${session.lastAccessedTime}"
        class="profile" alt="프로필 사진">
    </div>

    <div class="settings-icon">
      <!-- 톱니 아이콘 -->
      <svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 24 24" fill="currentColor" aria-hidden="true">
        <path d="M12 18a6 6 0 1 0 0-12 6 6 0 0 0 0 12zM12 21a9 9 0 1 1 0-18 9 9 0 0 1 0 18zM12 22a10 10 0 1 0 0-20 10 10 0 0 0 0 20zM12 19a7 7 0 1 1 0-14 7 7 0 0 1 0 14zM12 17a5 5 0 1 0 0-10 5 5 0 0 0 0 10zM12 16a4 4 0 1 1 0-8 4 4 0 0 1 0 8zM12 14a2 2 0 1 0 0-4 2 2 0 0 0 0 4z"/>
      </svg>

      <div class="settings-dropdown-menu">
        <a id="changeAvatarBtn" class="menu-item">프로필 사진 변경</a>

        <!-- 숨김 업로드 폼: 파일 선택 시 자동 제출 -->
        <form id="avatarForm"
              action="${pageContext.request.contextPath}/avatar/upload"
              method="post"
              enctype="multipart/form-data"
              style="display:none;">
          <!-- 서버에서 세션으로 사용자 식별한다면 hidden 필요 없음 -->
          <!-- <input type="hidden" name="user_id" value="${sessionScope.loginOkUser.userId}"> -->
          <input id="avatarInput" type="file" name="avatar" accept="image/*">
        </form>
      </div>
    </div>
  </div>
</div>

<script>
  // 버튼 클릭 → 파일 선택창 열기
  document.getElementById('changeAvatarBtn')?.addEventListener('click', function (e) {
    e.preventDefault();
    document.getElementById('avatarInput')?.click();
  });

  // 파일 선택되면 즉시 업로드
  document.getElementById('avatarInput')?.addEventListener('change', function () {
    if (this.files && this.files.length > 0) {
      // 간단 클라이언트 검증(선택): 이미지 타입/용량 체크
      const f = this.files[0];
      if (!f.type.startsWith('image/')) {
        alert('이미지 파일만 업로드할 수 있어요.');
        this.value = '';
        return;
      }
      // 예: 5MB 제한 (서버와 맞추세요)
      if (f.size > 5 * 1024 * 1024) {
        alert('파일 크기가 5MB를 초과합니다.');
        this.value = '';
        return;
      }
      document.getElementById('avatarForm').submit();
    }
  });
</script>
	        
             <!-- 탭 메뉴 (좋아요 탭 제거) -->
		      <div class="tabs" role="tablist" aria-label="마이페이지 탭">
		        <!-- a 태그로 서버 라우팅도 되고, JS로 pushState도 가능 -->
		        <a
		          role="tab"
		          href="${ctx}/mypage?tab=write"
		          class="tab-button ${isList ? 'active' : ''}"
		          aria-selected="${isList}"
		          data-tab="list"
		        >
		          내가 작성한 게시글
		        </a>
		
		        <%-- <a
		          role="tab"
		          href="${ctx}/mypage?tab=scrap"
		          class="tab-button ${isScrap ? 'active' : ''}"
		          aria-selected="${isScrap}"
		          data-tab="scrap"
		        >
		          스크랩한 게시글
		        </a> --%>
		      </div>
		
		      <!-- 탭 콘텐츠 -->
		      <div class="tab-content-wrapper">
		        <!-- 내가 작성한 게시글 -->
		        <section
		          id="my-posts"
		          class="tab-content ${isList ? 'active' : ''}"
		          role="tabpanel"
		          aria-labelledby="tab-list"
		          aria-hidden="${!isList}"
		        >
		          <ul class="post-list">
		            <c:choose>
					  <c:when test="${not empty myPosts}">
					    <c:forEach var="post" items="${myPosts}">
					      <li class="post-item">
					        <a href="post.do?mode=view&postId=${post.postId}">
					          <p class="post-title">${post.title}</p>
					          <p class="post-content">${post.rawContent}</p>
					          <div class="post-bottom">
					            <div class="view-count">
					            	<img src="../assets/img/main/post_info_icon01.png" alt="조회수">
					            	<p>${post.viewCount}</p>
				            	</div>
					            <div class="thumb-count">
					            	<img src="../assets/img/main/post_info_icon02.png" alt="좋아요수">
					           		<p>${post.likeCount}</p>
				            	</div>
					            <div class="comment-count">
					           		<img src="../assets/img/main/post_info_icon03.png" alt="댓글수">
					            	<p>${post.commentCount}</p>
				            	</div>
					          </div>
					        </a>
					      </li>
					    </c:forEach>
					  </c:when>
					  <c:otherwise>
					    <%-- 게시글 없을 때 메시지 --%>
					    <li class="no-data">
					      <div class="no-data-illustration"></div>
					      아직 작성한 게시글이 없어요.
					      <p>첫 게시글을 작성해 활동 내역을 시작해 보세요!</p>
					    </li>
					  </c:otherwise>
					</c:choose>
				</ul>
      		 </section>
		
		        <!-- 스크랩한 게시글 -->
		        <section
		          id="scrapped-posts"
		          class="tab-content ${isScrap ? 'active' : ''}"
		          role="tabpanel"
		          aria-labelledby="tab-scrap"
		          aria-hidden="${!isScrap}"
		        >
		          <ul class="post-list">
		            <c:choose>
		              <c:when test="${not empty myScraps}">
		                <c:forEach var="post" items="${myScraps}">
		                  <li class="post-item">
		                    <a href="${ctx}/board/post/detail?id=${post.id}">
		                      <h3><c:out value="${post.title}" /></h3>
		                      <p><c:out value="${post.excerpt}" /></p>
		                      <div class="meta">
		                        <span class="date">
		                          <fmt:formatDate value="${post.createdAt}" pattern="yyyy.MM.dd HH:mm" />
		                        </span>
		                        <span class="views">조회 <c:out value="${post.viewCount}" default="0"/></span>
		                        <span class="likes">좋아요 <c:out value="${post.likeCount}" default="0"/></span>
		                      </div>
		                    </a>
		                  </li>
		                </c:forEach>
		              </c:when>
		              <c:otherwise>
		                <li class="no-data">
		                  <div class="no-data-illustration"></div>
		                  아직 스크랩한 게시글이 없어요.
		                  <p>유익한 글을 스크랩해 보관해 보세요!</p>
		                </li>
		              </c:otherwise>
		            </c:choose>
		          </ul>
		        </section>
		      </div>
		    </div>
        
        <%@ include file="/WEB-INF/views/common/footer.jsp" %>
        

	</body>

</html>