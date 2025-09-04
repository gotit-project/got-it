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
        <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/list.css">
        <script src="${pageContext.request.contextPath}/assets/js/common/header.js" defer></script>
        <script src="${pageContext.request.contextPath}/assets/js/index.js" defer></script>
    </head>
    <body>

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
					            <a href="post.do?mode=write&id=${board.boardId }" class="write-button">작성하기</a>
					        </c:when>
					        
					        <c:otherwise>
					            <a href="javascript:alert('로그인이 필요한 서비스입니다.');" class="write-button">작성하기</a>
					        </c:otherwise>
   						 </c:choose>
			                </div>
					    
                           <!-- ======================================
                                1. 버튼 active 클래스 추가
                                - <button class="active"> 시 아이콘이 회색으로 변경됨
                                
                                2️. desktop-only 클래스 적용
                                - 화면이 데스크톱일 때만 버튼 표시
                                - 모바일에서는 숨김 처리
                            ====================================== -->
                            <div class="category-button-wrap desktop-only">
						      <c:if test="${not empty board.categories}">
								  <c:forEach items="${board.categories}" var="cat">
								    <button>${cat.categoryName}</button>
							  	</c:forEach>
								  <button>전체</button>
							  </c:if>
                            
                            </div>

                            <!-- ======================================
                                게시판 카테고리 가져오기
                            ====================================== -->
                            <div class="category-button-wrap mobile-only">
                                <select id="mobile-category-select">
                                 	<c:if test="${not empty board.categories}">
 										<c:forEach items="${board.categories}" var="cat">
                                    		<option>${cat.categoryName}</option>
                                		</c:forEach>
	                                    <option>전체</option>
 									 </c:if>
                                </select>
                            </div>

                            <div class="order-button-wrap">
                                <button>전체</button>
                                <button>최신순</button>
                            </div>
                        </div>
                         <div class="filter-bottom">
                            <button class="refresh-button">
                                <img src="../assets/img/list/refresh_button_icon.svg" alt="새로고침">
                            </button>
                            <div class="search-wrap">
                                <input type="text"  class="search-box" placeholder="지식나눔에서 검색">
                                <button class="search-button">
                                    <img src="../assets/img/list/search_button_icon.svg" alt="검색">
                                </button>
                            </div>
                            <div class="paging-wrap">
                                <div class="paging-text">
                                    <span>${curPage}</span>
                                    <p>/</p>
                                    <span>${totalPage}</span>
                                    <p>페이지</p>
                                </div>
                                <div class="paging-button">
                                    <a class="prev" href="board.do?mode=list&id=${board.boardId}&page=${curPage - 1}">
                                        <button ${curPage == 1 ? 'disabled' : ''}>
                                        	<img src="../assets/img/list/paging_button_icon.svg" alt="이전 버">
                                       	</button>
                                    </a>
                                    <a class="next" href="board.do?mode=list&id=${board.boardId}&page=${curPage + 1}">
                                        <button ${curPage == totalPage ? 'disabled' : ''}>
                                        	<img src="../assets/img/list/paging_button_icon.svg" alt="다음 버튼">
                                      	</button>
                                    </a>
                                </div>
                            </div>
                        </div>
                    </div>
                    <a href="#" class="board-fixed-list">
                        <span>공지사항</span>
                        <p>[태그 잊지말고 달기] 좋은 답글 달리는 꿀팁!!</p>
                    </a>
                    <div class="board-list">
                    	 <!-- 게시글 한 묶음 -->
                    	 <c:if test="${empty postList}">
						    <tr>
						    <td align='center' colspan="5">데이터가 하나도 없음</td>
						    </tr>
						</c:if>
						<c:forEach items="${postList}" var="post">
                        <a href="post.do?mode=view&postId=${post.postId}">
                            <div class="post-item">
                                <div class="post-item-header">
                                    <img src="../assets/img/common/post_info_profile02.png" class="profile" alt="프로필 사진">
                                    <p class="writer">${post.nickName}</p>
                                    <%-- 이 부분에서 `DateUtils` 클래스를 사용합니다 --%>
            						<span class="time">${DateUtils.formatTimeAgo(post.updatedAt)}</span>
                                </div>
                            </div>
                            <p class="post-title">${post.title}</p>
                            <p class="post-content">
                                ${post.rawContent}
                            </p>
                            <div class="post-bottom">
                                <div class="post-keyword">
                                    <span class="category">${post.categoryName}</span>
                                    <span class="hashtag">${post.postTag}</span>
                                </div>
                                <div class="post-counts">
                                    <div class="view-count">
                                        <img src="../assets/img/main/post_info_icon01.png" alt="조회수">
                                        <p>${post.viewCount}</p>
                                    </div>
                                    <div class="thumb-count">
                                        <img src="../assets/img/main/post_info_icon02.png" alt="좋아요수">
                                        <p>2</p>
                                    </div>
                                    <div class="comment-count">
                                        <img src="../assets/img/main/post_info_icon03.png" alt="댓글수">
                                        <p>57</p>
                                    </div>
                                </div>
                            </div>
                        </a>
                        <!-- 게시글 한 묶음 끝 -->
                    	</c:forEach>
                    </div>
                </div>
            </div>
		
            <!-- 게시글 페이징 -->
            <div class="pagination">
               <a href="board.do?mode=list&name=${board.boardName}&page=${curPage - 1}">
				    <button class="pagination-button prev" ${curPage == 1 ? 'disabled' : ''}>
				        ‹ Previous
				    </button>
				</a>
				<c:forEach var="i" begin="1" end="${totalPage}">
				    <a href="board.do?mode=list&name=${board.boardName}&page=${i}">
				        <button class="page ${i == curPage ? 'active' : ''}">${i}</button>
				    </a>
				</c:forEach>
				<a href="board.do?mode=list&name=${board.boardName}&page=${curPage + 1}">
				    <button class="pagination-button next" ${curPage == totalPage ? 'disabled' : ''}>
				        Next ›
				    </button>
				</a>
            </div>
        </div>

       	<%@ include file="/WEB-INF/views/common/footer.jsp" %>
    </body>
</html>