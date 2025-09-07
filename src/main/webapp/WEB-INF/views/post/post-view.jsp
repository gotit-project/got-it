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
		<script src="https://uicdn.toast.com/editor/latest/toastui-editor-all.min.js"></script>
        <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/view.css">
		<link rel="stylesheet" href="https://uicdn.toast.com/editor/latest/toastui-editor.css" />
        <script src="${pageContext.request.contextPath}/assets/js/common/header.js" defer></script>
        <script src="${pageContext.request.contextPath}/assets/js/index.js" defer></script>
        <script src="${pageContext.request.contextPath}/assets/js/view.js" defer></script>
        <script src="${pageContext.request.contextPath}/assets/js/comment.js" defer></script>
        	  <style>
		    body {
		      all: unset;         /* 기본 속성 모두 제거 */
		      display: block;     /* all: unset 하면 body도 inline처럼 변할 수 있어서 block 지정 */
		    }
		  </style>
    </head>
    <body>

        <%@ include file="/WEB-INF/views/common/header.jsp" %>
        
        <!-- 컨텐츠 -->
        <div id="view" class="content-wrap">
            <!-- 게시글 -->
            <div class="post-item-wrap">
                <div class="view-title">
                    <div class="view-span-wrap">
                        <a href="board.do?mode=list&id=${post.boardId}&categoryId=0&page=1">${post.boardName}</a>
                        <span>/</span>
                        <a href="board.do?mode=list&id=${post.boardId}&categoryId=${post.categoryId}&page=1" class="z">${post.categoryName}</a>
                    </div>
                </div>
                <div>
                    <div class="post-item-header">
                        <img src="../assets/img/common/post_info_profile02.png" class="profile" alt="프로필 사진">
                        <div class="profile-info">
                        	<input type="hidden" name="userId" value="${sessionScope.loginOkUser.userId}">
                            <p class="writer">
                            	${post.nickName}
                            	<!-- 
                                  뱃지 6가지 종류 css 적용하는 방법 
                                  <span class="badge"></span>
                                  <span class="badge bronze"></span>
                                  <span class="badge silver"></span>
                                  <span class="badge gold "></span>
                                  <span class="badge platinum"></span>
                                  <span class="badge diamond"></span>
                                  <span class="badge challenger"></span>
                            	 -->
                            	<span class="badge ${post.badgeName}">
								   ${post.badgeName}
								</span>
                            </p> 
	                        <div class="flex-box">
	    						<span class="time">
								    ${post.updatedAt != null ? DateUtils.formatTimeAgo(post.updatedAt) : ""}
								</span>
	                            <p class="view-count">조회수 ${post.viewCount}</p>
	                            <span class="corrected-mark ${post.updatedAt.time != post.createdAt.time ? '' : 'hidden'}">
							    	수정됨
								</span>
							</div>
                        </div>
                       <div class="etc-button-wrap">
        				    <button id="likeButton" class="like-button ${userLiked ? "active" : ""}" 
							        data-post-id="${post.postId}"
							        data-user-id="${sessionScope.loginOkUser != null ? sessionScope.loginOkUser.userId : ''}">
							</button>
							
							<button class="share-button">공유하기</button>
							
							<button id="scrapButton" class="scrap-button ${userScrapped ? "active" : ""}"
							        data-post-id="${post.postId}"
							        data-user-id="${sessionScope.loginOkUser != null ? sessionScope.loginOkUser.userId : ''}">
							</button>

						</div>
						<c:if test="${not empty sessionScope.loginOkUser && sessionScope.loginOkUser.userId == post.userId}">
						    <div class="edit-wrap">
						        <button class="edit-button">···</button>
						        <div class="edit-dropdown-menu">
						            <div class="menu-divider"></div>
						            <form action="post.do?mode=edit&postId=${post.postId}" method="post">
						              	<input type="hidden" name="boardId" value="${post.boardId}">
						            	<button type="submit" class="edit-menu-item">수정</button>
						            </form>
						            <div class="menu-divider"></div>
						            	<a href="post.do?mode=delete&postId=${post.postId}" class="edit-menu-item">삭제</a>
						        </div>
						    </div>
						</c:if>
						
                      
                    </div>
                    <div class="post-item">
                        <p class="post-title">${post.title}</p>
                        <br/>
                        <br/>
                        <p class="post-content">
                            ${post.htmlContent}
                        </p>
                    </div>
                    <div class="post-bottom">
                        <div class="post-keywork">
                            <span class="hashtag">#${post.postTag}</span>
                        </div>
                      <!--   <button class="report-button">
                            신고
                        </button> -->
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