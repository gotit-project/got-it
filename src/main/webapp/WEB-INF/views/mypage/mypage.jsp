<%@ page contentType="text/html;charset=utf-8" import="java.sql.*"%>

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
        <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/common/header.css">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/common/footer.css">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/index.css">
                <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/mypage.css">
        <script src="${pageContext.request.contextPath}/assets/js/common/header.js" defer></script>
        <script src="${pageContext.request.contextPath}/assets/js/index.js" defer></script>
        <script src="${pageContext.request.contextPath}/assets/js/mypage.js" defer></script>
    </head>
    <body>

        <%@ include file="/WEB-INF/views/common/header.jsp" %>
        
        
          <div class="content-wrap">
	        <!-- 프로필 헤더 -->
	        <div class="profile-header">
	            <div class="profile-info">
	                <h1>sdjadodoj</h1>
	                <p>아직 소개를 작성하지 않았습니다.</p>
	                <p> point</p>
	            </div>
	            <div class="profile-avatar-wrap">
		            <div class="profile-avatar">수빈</div>
		            <div class="settings-icon">
		             	<svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 24 24" fill="currentColor">
		                    <path d="M12 18a6 6 0 1 0 0-12 6 6 0 0 0 0 12zM12 21a9 9 0 1 1 0-18 9 9 0 0 1 0 18zM12 22a10 10 0 1 0 0-20 10 10 0 0 0 0 20zM12 19a7 7 0 1 1 0-14 7 7 0 0 1 0 14zM12 17a5 5 0 1 0 0-10 5 5 0 0 0 0 10zM12 16a4 4 0 1 1 0-8 4 4 0 0 1 0 8zM12 14a2 2 0 1 0 0-4 2 2 0 0 0 0 4z"/>
		                </svg>
		                <div class="settings-dropdown-menu">
				        	<a href="#" class="menu-item">프로필 사진 변경</a>
				        </div>
	            	</div>
	            </div>
	        </div>
	
	        <!-- 탭 메뉴 -->
	        <div class="tabs">
	            <button class="tab-button active" onclick="openTab(event, 'my-posts')">내가 작성한 게시글</button>
	            <button class="tab-button" onclick="openTab(event, 'scrapped-posts')">스크랩한 게시글</button>
	            <button class="tab-button" onclick="openTab(event, 'liked-posts')">좋아요 누른 게시글</button>
	        </div>
	
	        <!-- 탭 콘텐츠 -->
	        <div class="tab-content-wrapper">
	            <div id="my-posts" class="tab-content active">
	                <ul class="post-list">
	                    <!-- 게시글이 없을 때 -->
	                    <li class="no-data">
	                        <div class="no-data-illustration"></div>
	                        아직 활동 내역이 없어요.
	                        <p>유익한 내용을 공유해 주실수록 활동 내역이 쌓일 거예요!</p>
	                    </li>
	                </ul>
	            </div>
	
	            <div id="scrapped-posts" class="tab-content">
	                <ul class="post-list">
	                    <a href="#" class="post-item">
	                    	<h3>체 관계 매핑(ORM)</h3>
	                        <p>객체 관계 매핑(ORM)에 대한 심도 있는 분석 글입니다.</p>
	                    </a>
	                </ul>
	            </div>
	
	            <div id="liked-posts" class="tab-content">
	                <ul class="post-list">
	                    <a href="#" class="post-item">
	                        <h3>효율적인 SQL 쿼리 작성법</h3>
	                        <p>데이터베이스 성능 최적화를 위한 쿼리 팁입니다.</p>
	                    </a>
	                    <a href="#" class="post-item">
	                        <h3>CSS Flexbox 완벽 가이드</h3>
	                        <p>레이아웃을 쉽게 잡는 Flexbox 사용법에 대한 가이드입니다.</p>
	                    </a>
	                    <a href="#" class="post-item">
	                        <h3>Spring Boot 개발 시작하기</h3>
	                        <p>스프링 부트의 기본 구조와 프로젝트 생성법을 알아봅니다.</p>
	                    </a>
	                </ul>
	            </div>
	        </div>
	    </div>
        
        
        <%@ include file="/WEB-INF/views/common/footer.jsp" %>
        

	</body>

</html>