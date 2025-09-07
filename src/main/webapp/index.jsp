<%@ page contentType="text/html;charset=utf-8" import="java.sql.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page import="gotit.common.util.DateUtils" %>



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
        <script src="${pageContext.request.contextPath}/assets/js/common/header.js" defer></script>
        <script src="${pageContext.request.contextPath}/assets/js/index.js" defer></script>
    </head>
    <body>

       <%@ include file="WEB-INF/views/common/header.jsp" %>
		
		<div id="main" class="content-wrap">
		    <div class="board-wrap">
		    

		        <!-- 공지 게시판 -->
		        <div class="notice">
		            <div class="board-title notice-title">
		            	공지사항
		            	<img src="/assets/img/common/board_title_notice.png" class="board-title-img">
		            </div>
		            <div class="post-item-wrap">
		                <c:set var="posts" value="${boardPostsMap['4']}" />
		                <c:if test="${not empty posts}">
		                    <c:forEach var="post" items="${posts}">
		                        <a href="post.do?mode=view&postId=${post.postId}">
		                            <div class="post-item">
		                                <div class="post-item-header">
		                                    <img src="${pageContext.request.contextPath}/avatar?img=${post.imgName}" class="profile" alt="프로필 사진">
		                                    <p class="writer">${post.nickName}</p>
		                                    <span class="time">${post.createdAt != null ? DateUtils.formatTimeAgo(post.createdAt) : ""}</span>
		                                </div>
		                                <div class="post-counts">
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
		                            </div>
	                              	<div class="post-title">
	                                    <span class="important">[필독]</span>
	                                    ${post.title}
	                                </div>
		                        </a>
		                    </c:forEach>
		                </c:if>
		                <c:if test="${empty posts}">
		                    <p>게시글 없음</p>
		                </c:if>
		            </div>
		        </div> <!-- 공지 게시 -->
		
				<!-- 서브 보드 -->
				<div class="sub-board">
				
			        <!-- Q&A 게시판 -->
			        <div class="qna">
			            <a href="board.do?mode=list&id=1&categoryId=0&page=1" class="board-title">
			            	Q&A
			            	<img src="/assets/img/common/board_title_1.png" class="board-title-img">
			            </a>
			            <div class="post-item-wrap">
			                <c:set var="posts" value="${boardPostsMap['1']}" />
			                <c:if test="${not empty posts}">
			                    <c:forEach var="post" items="${posts}">
			                        <a href="post.do?mode=view&postId=${post.postId}">
			                            <div class="post-item">
			                                <div class="post-item-header">
			                                    <img src="${pageContext.request.contextPath}/avatar?img=${post.imgName}" class="profile" alt="프로필 사진">
			                                    <p class="writer">${post.nickName}</p>
			                                    <span class="time">${post.createdAt != null ? DateUtils.formatTimeAgo(post.createdAt) : ""}</span>
			                                </div>
			                                <div class="post-counts">
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
			                            </div>
		                                <div class="post-title">
		                                	${post.title}
		                                </div>
			                        </a>
			                    </c:forEach>
			                </c:if>
			                <c:if test="${empty posts}">
			                    <p>게시글 없음</p>
			                </c:if>
			            </div>
			        </div>
			
			        <!-- 지식 게시판 -->
			        <div class="study">
			            <a href="board.do?mode=list&id=2&categoryId=0&page=1" class="board-title">
			            	지식나눔
			            	<img src="/assets/img/common/board_title_2.png" class="board-title-img">
		            	</a>
			            <div class="post-item-wrap">
			                <c:set var="posts" value="${boardPostsMap['2']}" />
			                <c:if test="${not empty posts}">
			                    <c:forEach var="post" items="${posts}">
			                        <a href="post.do?mode=view&postId=${post.postId}">
			                            <div class="post-item">
			                                <div class="post-item-header">
			                                    <img src="${pageContext.request.contextPath}/avatar?img=${post.imgName}" class="profile" alt="프로필 사진">
			                                    <p class="writer">${post.nickName}</p>
			                                    <span class="time">${post.createdAt != null ? DateUtils.formatTimeAgo(post.createdAt) : ""}</span>
			                                </div>
			                                <div class="post-counts">
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
			                            </div>
			                             <div class="post-title">
		                                 	${post.title}
		                                </div>
			                        </a>
			                    </c:forEach>
			                </c:if>
			                <c:if test="${empty posts}">
			                    <p>게시글 없음</p>
			                </c:if>
			            </div>
			        </div>
			
			   
			   </div><!-- 서브 보드 --> 
			   
		    </div> <!-- board-wrap 끝 -->
		</div> <!-- main 끝 -->

	<%@ include file="WEB-INF/views/common/footer.jsp" %>
    </body>
</html>