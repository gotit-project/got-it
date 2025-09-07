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
        <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/common/reset.css">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/common/header.css">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/common/footer.css">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/index.css">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/list.css">
        <script src="${pageContext.request.contextPath}/assets/js/common/header.js" defer></script>
        <script src="${pageContext.request.contextPath}/assets/js/index.js" defer></script>
        <script src="${pageContext.request.contextPath}/assets/js/list.js" defer></script>
    </head>
    <body>
		<%-- 안전 기본값 --%>
		<c:set var="curPage" value="${empty curPage ? 1 : curPage}" />
		<c:set var="totalPage" value="${empty totalPage ? 1 : totalPage}" />
		<%-- 현재 선택된 카테고리 (없으면 0=전체) --%>
		<c:set var="catParam" value="${empty param.categoryId ? 0 : param.categoryId}" />
		<c:set var="pageBase" value="board.do?mode=list&id=${board.boardId}&categoryId=${catParam}" />
		
        <%@ include file="/WEB-INF/views/common/header.jsp" %>

        <!-- 컨텐츠 -->
        
        <div id="list" class="content-wrap">
            <!-- 게시판 모음 -->
            <div class="board-wrap">
                <!-- 공지 게시판 -->
                <div class="notice">
                    <div class="board-title">
                        <strong>${board.boardName}</strong>
                        <p>${board.description}</p>
                    </div>
                    <div class="filter-wrap">
                        <div class="filter-top">
			                <div class="write-button-wrap">
			                <c:choose>
					        <c:when test="${not empty sessionScope.loginOkUser}">
					            <a href="post.do?mode=write&id=${board.boardId}" class="write-button">작성하기</a>
					        </c:when>
					        
					        <c:otherwise>
					            <a href="javascript:alert('로그인이 필요한 서비스입니다.');" class="write-button">작성하기</a>
					        </c:otherwise>
   						 </c:choose>
			                </div>
                            <div class="category-button-wrap desktop-only" id="category-buttons">
							  <c:if test="${not empty board.categories}">
							    <c:forEach items="${board.categories}" var="cat">
							      <c:url var="catUrl" value="board.do">
							        <c:param name="mode" value="list"/>
							        <c:param name="id" value="${board.boardId}"/>
							        <c:param name="categoryId" value="${cat.categoryId}"/>
							        <c:param name="page" value="1"/>
							      </c:url>
							      <a href="${catUrl}">
							        <button type="button" class="${cat.categoryId == catParam ? 'active' : ''}">
							          ${cat.categoryName}
							        </button>
							      </a>
							    </c:forEach>
							
							    <c:url var="allUrl" value="board.do">
							      <c:param name="mode" value="list"/>
							      <c:param name="id" value="${board.boardId}"/>
							      <c:param name="categoryId" value="0"/>
							      <c:param name="page" value="1"/>
							    </c:url>
							    <a href="${allUrl}">
							      <button type="button" class="${catParam == 0 ? 'active' : ''}">전체</button>
							    </a>
							  </c:if>
							</div>
                            
                            <form class="category-button-wrap mobile-only" method="get" action="board.do">
							  <input type="hidden" name="mode" value="list"/>
							  <input type="hidden" name="id" value="${board.boardId}"/>
							
							  <select id="mobile-category-select" name="categoryId" onchange="this.form.submit()">
							    <c:if test="${not empty board.categories}">
							      <c:forEach items="${board.categories}" var="cat">
							        <option value="${cat.categoryId}" ${cat.categoryId == catParam ? 'selected' : ''}>
							          ${cat.categoryName}
							        </option>
							      </c:forEach>
							    </c:if>
							    <option value="0" ${catParam == 0 ? 'selected' : ''}>전체</option>
							  </select>
							</form>

                            <div class="order-button-wrap">
                                <button>최신순</button>
                            </div>
                        </div>
                         <div class="filter-bottom">
                            <button type="button" class="refresh-button" onclick="location.reload()">
							  <img src="${pageContext.request.contextPath}/assets/img/list/refresh_button_icon.svg" alt="새로고침">
							</button>
                            <div class="search-wrap">
                                <input type="text"  class="search-box" placeholder="지식나눔에서 검색">
                                <button class="search-button">
                                    <img src="../assets/img/list/search_button_icon.svg" alt="검색">
                                </button>
                            </div>
                            <div class="paging-wrap">
                                <div class="paging-text">
                                    <span>${page.curPage}</span>
                                    <p>/</p>
                                    <span>${page.totalPage}</span>
                                    <p>페이지</p>
                                </div>
                                <div class="paging-button">
                                    <a class="prev" href="board.do?mode=list&id=${board.boardId}&categoryId=${catParam}&page=${page.curPage - 1}">
                                        <button ${page.curPage == 1 ? 'disabled' : ''}>
                                        	<img src="../assets/img/list/paging_button_icon.svg" alt="이전 버">
                                       	</button>
                                    </a>
                                    <a class="next" href="board.do?mode=list&id=${board.boardId}&categoryId=${catParam}&page=${page.curPage + 1}">
                                        <button ${page.curPage == page.totalPage ? 'disabled' : ''}>
                                        	<img src="../assets/img/list/paging_button_icon.svg" alt="다음 버튼">
                                      	</button>
                                    </a>
                                </div>
                            </div>
                        </div>
                    </div>
                    <!-- 공지사항 로테이션 -->
                    <!-- boardId가 4가 아닐 때 -->
 					<c:if test="${board.boardId != 4}">
                    	<a href="#" class="board-fixed-list" id="notice-link">
						    <span>공지사항</span>
						    <p id="notice-text">
						      <c:forEach var="post" items="${noticePosts}" varStatus="status">
						        <c:if test="${status.index == 0}">
						          ${post.title}
						        </c:if>
						      </c:forEach>
						    </p>
						</a>
				  </c:if>
				  <!-- boardId가 4일 때 -->
				  <c:if test="${board.boardId == 4}">
				    	<div class="board-fixed-list"></div>
				  </c:if>
                    <!-- 공지사항 로테이션 끝 -->
                    
                    <!-- 게시글  -->
                    <div class="board-list">
                    	 <!-- 게시글 한 묶음 -->
                    	 <c:if test="${empty postList}">
						    <tr>
						    <td align='center' colspan="5">데이터가 하나도 없음</td>
						    </tr>
						</c:if>
						<c:forEach items="${postList}" var="post">
                        <a href="post.do?mode=view&postId=${post.postId}"
    						data-category-id="${post.categoryId}"
     						data-category-name="${post.categoryName}">
                            <div class="post-item">
                                <div class="post-item-header">
                                    <img src="${pageContext.request.contextPath}/avatar?img=${post.imgName}" class="profile" alt="프로필 사진">
                                    <p class="writer">${post.nickName}</p>
                                    <%-- 이 부분에서 `DateUtils` 클래스를 사용합니다 --%>
            						<span class="time">${DateUtils.formatTimeAgo(post.createdAt)}</span>
                                </div>
                            </div>
                            <p class="post-title">${post.title}</p>
                            <p class="post-content">${post.rawContent}</p>
                            <div class="post-bottom">
                                <div class="post-keyword">
                                    <span class="category">${post.categoryName}</span>
                                    <span class="hashtag">#${post.postTag}</span>
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
                        </a>
                        <!-- 게시글 한 묶음 끝 -->
                    	</c:forEach>
                    </div>
                    <!-- 게시글 끝 -->
                    
                </div>
            </div>
		
            <!-- 게시글 페이징 -->
			<%-- 게시글 페이징 --%>
			<c:if test="${page.totalPage >= 1}">
			  <div class="pagination">
			
			    <%-- 이전 --%>
			    <c:choose>
			      <c:when test="${page.curPage > 1}">
			        <a href="${pageBase}&page=${page.curPage - 1}">
			          <button class="pagination-button prev">‹ Previous</button>
			        </a>
			      </c:when>
			      <c:otherwise>
			        <button class="pagination-button prev" disabled>‹ Previous</button>
			      </c:otherwise>
			    </c:choose>
			
			    <%-- 번호 --%>
			    <c:forEach var="i" begin="1" end="${page.totalPage}">
			      <a href="${pageBase}&page=${i}">
			        <button class="page ${i == page.curPage ? 'active' : ''}">${i}</button>
			      </a>
			    </c:forEach>
			
			    <%-- 다음 --%>
			    <c:choose>
			      <c:when test="${page.curPage < page.totalPage}">
			        <a href="${pageBase}&page=${page.curPage + 1}">
			          <button class="pagination-button next">Next ›</button>
			        </a>
			      </c:when>
			      <c:otherwise>
			        <button class="pagination-button next" disabled>Next ›</button>
			      </c:otherwise>
			    </c:choose>
			
			  </div>
			</c:if>
		</div>

       	<%@ include file="/WEB-INF/views/common/footer.jsp" %>
       	
       	<!--
       	* 공지사항 로테이션  
       	* JSP 내에서 JSTL 반복문 설정 해주고 
       	* 나머지 js 코드는 notice-rotaion.js 
       	 -->
     	<script>
	       	const noticeData = [
	       	  <c:forEach var="post" items="${noticePosts}" varStatus="status">
	       	    { title: "${post.title}", url: "post.do?mode=view&postId=${post.postId}" }<c:if test="${!status.last}">,</c:if>
	       	  </c:forEach>
	       	];
		</script>
		<script src="${pageContext.request.contextPath}/assets/js/notice-rotation.js" ></script>
    </body>
</html>