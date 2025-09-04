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
                        <strong>Q&A</strong>
                        <p>좋은 질문과 답변으로 친구들의 시간을 아껴주세요.</p>
                    </div>
                    <div class="filter-wrap">
                        <div class="filter-top">
                        
                         <c:choose>
					        <c:when test="${not empty sessionScope.loginOkUser}">
					            <a href="post.do?mode=write" class="write-button">작성하기</a>
					        </c:when>
					        
					        <c:otherwise>
					            <a href="javascript:alert('로그인이 필요한 서비스입니다.');" class="write-button">작성하기</a>
					        </c:otherwise>
   						 </c:choose>
					    
                           <!-- ======================================
                                1. 버튼 active 클래스 추가
                                - <button class="active"> 시 아이콘이 회색으로 변경됨
                                
                                2️. desktop-only 클래스 적용
                                - 화면이 데스크톱일 때만 버튼 표시
                                - 모바일에서는 숨김 처리
                            ====================================== -->
                            <div class="category-button-wrap desktop-only">
                                <button>국어</button>
                                <button>영어</button>
                                <button>수학</button>
                                <button>사회</button>
                                <button>과학</button>
                                <button>역사</button>
                                <button>정보/컴퓨터</button>
                                <button>예체능</button>
                                <button>기타</button>
                                <button>전체</button>
                            </div>

                            <!-- ======================================
                                mobile-only 클래스 적용
                                - 화면이 모바일일 때만 셀렉트박스 표시
                                - 데스크톱에서는 숨김 처리
                            ====================================== -->
                            <div class="category-button-wrap mobile-only">
                                <select id="mobile-category-select">
                                    <option>국어</option>
                                    <option>영어</option>
                                    <option>수학</option>
                                    <option>사회</option>
                                    <option>과학</option>
                                    <option>역사</option>
                                    <option>정보/컴퓨터</option>
                                    <option>예체능</option>
                                    <option>기타</option>
                                    <option>전체</option>
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
                                    <span>1</span>
                                    <p>/</p>
                                    <span>182</span>
                                    <p>페이지</p>
                                </div>
                                <div class="paging-button">
                                    <button class="prev">
                                        <img src="../assets/img/list/paging_button_icon.svg" alt="">
                                    </button>
                                    <button class="next">
                                        <img src="../assets/img/list/paging_button_icon.svg" alt="">
                                    </button>
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
						<c:forEach items="${postList}" var="postDto">
                        <a href="post.do?mode=select&postId=${postDto.postId}">
                            <div class="post-item">
                                <div class="post-item-header">
                                    <img src="../assets/img/common/post_info_profile02.png" class="profile" alt="프로필 사진">
                                    <input type="hidden" name="userId" value="${sessionScope.loginOkUser.userId}">
                                    <p class="writer">${postDto.nickName}</p>
                                    <%-- 이 부분에서 `DateUtils` 클래스를 사용합니다 --%>
            						<span class="time">
									  ${postDto.updatedAt != null ? DateUtils.formatTimeAgo(postDto.updatedAt) : '방금 전'}
									</span>

                                </div>
                            </div>
                            <p class="post-title">${postDto.title}</p>
                            <p class="post-content">
                                ${postDto.content}
                            </p>
                            <div class="post-bottom">
                                <div class="post-keyword">
                                    <span class="category">국어</span>
                                    <span class="hashtag">#${postDto.postTag}</span>
                                </div>
                                <div class="post-counts">
                                    <div class="view-count">
                                        <img src="../assets/img/main/post_info_icon01.png" alt="조회수">
                                        <p>${postDto.viewCount}</p>
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
		
            <!-- 게시글 페이징 --><%-- 
            <div class="pagination">
               <a href="post.do?page=${curPage - 1}">
				    <button class="pagination-button prev" ${curPage == 1 ? 'disabled' : ''}>
				        ‹ Previous
				    </button>
				</a>
				
				<c:forEach var="i" begin="1" end="${totalPage}">
				    <a href="post.do?page=${i}">
				        <button class="page ${i == curPage ? 'active' : ''}">${i}</button>
				    </a>
				</c:forEach>
				
				<a href="post.do?page=${curPage + 1}">
				    <button class="pagination-button next" ${curPage == totalPage ? 'disabled' : ''}>
				        Next ›
				    </button>
				</a>
            </div> --%>
            
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
        <script src="../assets/js/main.js"></script>
    </body>
</html>