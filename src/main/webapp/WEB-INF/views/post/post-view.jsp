<%@ page contentType="text/html;charset=utf-8" import="java.sql.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="gotit.common.util.DateUtils" %>

<!DOCTYPE html>
<html lang="ko">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Got !t</title>
        <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/global.css">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/common/header.css">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/common/footer.css">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/index.css">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/view.css">
        <script src="${pageContext.request.contextPath}/assets/js/index.js" defer></script>
        <script src="${pageContext.request.contextPath}/assets/js/common/header.js" defer></script>
         <script src="${pageContext.request.contextPath}/assets/js/main.js" defer></script>
        <script src="${pageContext.request.contextPath}/assets/js/view.js" defer></script>
        
    </head>
    <body>

        <%@ include file="/WEB-INF/views/common/header.jsp" %>
        
        <!-- 컨텐츠 -->
        <div id="view" class="content-wrap">
            <!-- 게시글 -->
            <div class="post-item-wrap">
                <div class="view-title">
                    <div class="view-span-wrap">
                        <a href="list.html">Q&A</a>
                        <span>/</span>
                        <a href="list.html" class="category">국어</a>
                    </div>
                </div>
                <div>
                    <div class="post-item-header">
                        <img src="../assets/img/common/post_info_profile02.png" class="profile" alt="프로필 사진">
                        <div class="profile-info">
                            <p class="writer">${sessionScope.loginOkUser.userName}</p>
                            <%-- 이 부분에서 `DateUtils` 클래스를 사용합니다 --%>
    						<span class="time">
							    ${postDto.updatedAt != null ? DateUtils.formatTimeAgo(postDto.updatedAt) : ""}
							</span>
                            <p class="view-count">조회수 <span>${postDto.viewCount}</span></p>
                            <span class="corrected-mark">수정됨</span> 
                        </div>
                        <!-- ======================================
                            버튼 active 클래스 추가 <button class="active">
                            --------------------------------------
                            - 아이콘이 파란색으로 변경됨
                        ====================================== -->
                        <div class="etc-button-wrap">
                            <button>좋아요</button>
                            <button>공유</button>
                            <button>북마크</button>
                        </div>
                        <!--로그인 하면 보이는 수정/삭제 버튼 패널-->
        				<!-- css 로 보임/숨김 처리 하므로 html 코드 다 넣어주시면 됩니다. -->
                        <div class="edit-wrap">
	                        <button class="edit-button">
	                           ···
	                        </button>
	                        <div class="edit-dropdown-menu">
	                            <div class="menu-divider"></div>
                            	<a href="post.do?mode=edit&postId=${postDto.postId}" class="edit-menu-item">수정</a>
                            	<div class="menu-divider"></div>
                            	<a href="post.do?mode=delete&postId=${postDto.postId}" class="edit-menu-item">삭제</a>
	                        </div>
	                    </div>
                    </div>
                    <div class="post-item">
                        <p class="post-title">${postDto.title}</p>
                        <br/>
                        <br/>
                        <p class="post-content">
                            ${postDto.content}
                        </p>
                    </div>
                    <div class="post-bottom">
                        <div class="post-keywork">
                            <span class="hashtag">#${postDto.postTag}</span>
                        </div>
                        <button class="report-button">
                            신고
                        </button>
                    </div>
                </div>
            </div>
           
         
         <%@ include file="/WEB-INF/views/post/post-comment.jsp" %>
         
        </div>

        <!-- 푸터 -->
         <footer>
			<div class="footer-wrap">
				<div class="footer-top">
					<div class="footer-nav">
						<img src="../assets/img/common/logo.svg" alt="로고" class="f-logo"></a>
						<ul>
							<li><a href="" target="_blank">회사소개</a></li>
							<li><a href="" target="_blank">공지사항</a></li>
							<li><a href="" target="_blank">연락처</a></li>
							<li><a href="" target="_blank">개인정보처리방침</a></li>
						</ul>
					</div>
				</div>
				<div class="footer-bottom">
					<ul>
						<li>서울특별시 금천구 디지털로9길 23, 11층 천재IT교육센터</li>
						<li>이메일: genia@chunjae.co.kr</li>
						<li>전화: 02-3282-8589</li>
						<li>CopyRight 2025ⓒgotIt.All right Reserved.</li>
					</ul>
				</div>
			</div>
		</footer>
    </body>
</html>