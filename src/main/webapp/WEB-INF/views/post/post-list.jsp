<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    
    
<!DOCTYPE html>
<html lang="ko">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>got it</title>
        <link rel="stylesheet" href="../assets/css/reset.css">
        <link rel="stylesheet" href="../assets/css/common.css">
        <link rel="stylesheet" href="../assets/css/list.css">
        <script src="../assets/js/main.js" defer></script>
    </head>
    <body>

        <!-- ======================================
            로그인 하면     : <header id="login">
            디폴트(비로그인) : <header id="default">
            --------------------------------------
            - login 아이디가 없으면
               <div class="header-right"> 가 존재 x
            - index.html 헤더 참고
        ====================================== -->
       <header id="login">
            <div class="nav">
                <a href="index.html" class="logo">
                    <img src="../assets/img/common/logo.svg" alt="로고">
                </a>
                <ul>
                    <li><a href="index.html">홈</a></li>
                    <li><a href="list.html">Q&A</a></li>
                    <li><a href="list.html">지식나눔</a></li>
                    <li><a href="list.html">자유게시판</a></li>
                    <li><a href="list.html">공지사항</a></li>
                </ul>
                <div class="header-right">
                    <div class="write-button-wrap">
                        <button class="write-button-group">
                            <span class="plus-icon">
                                <svg width="24" height="24" viewBox="0 0 24 24" fill="none" xmlns="http://www.w3.org/2000/svg">
                                    <path d="M12 5V19" stroke="white" stroke-width="2" stroke-linecap="round" stroke-linejoin="round"/>
                                    <path d="M5 12H19" stroke="white" stroke-width="2" stroke-linecap="round" stroke-linejoin="round"/>
                                </svg>
                            </span>
                            <span class="dropdown-arrow-icon">
                                <svg width="24" height="24" viewBox="0 0 24 24" fill="none" xmlns="http://www.w3.org/2000/svg">
                                    <path d="M6 9L12 15L18 9" stroke="white" stroke-width="2" stroke-linecap="round" stroke-linejoin="round"/>
                                </svg>
                            </span>
                        </button>
                        <div class="dropdown-menu">
                            <a href="#" class="menu-item">
                                새 글 쓰기
                            </a>
                            <div class="menu-divider"></div>
                            <a href="#" class="menu-item with-icon">
                                <span><strong>Q&amp;A</strong>에 글쓰기</span>
                                <span class="icon">❓</span>
                            </a>
                            <a href="#" class="menu-item with-icon">
                                <span><strong>지식</strong>에 글쓰기</span>
                                <span class="icon">📚</span>
                            </a>
                            <a href="#" class="menu-item with-icon">
                                <span><strong>커뮤니티</strong>에 글쓰기</span>
                                <span class="icon">😊</span>
                            </a>
                            <a href="#" class="menu-item with-icon">
                                <span><strong>이벤트</strong>에 글쓰기</span>
                                <span class="icon">🔥</span>
                            </a>
                        </div>
                    </div>
                    <div class="profile-wrap">
                        <button class="profile-button">
                            수빈
                        </button>
                        <div class="profile-dropdown-menu">
                            <div class="profile-info">
                                <strong>내 계정</strong>
                                <p>park@gmail.com</p>
                                <p><span>박수빈</span> 님, 안녕하세요.</p>
                            </div>
                            <div class="menu-divider"></div>
                            <a href="#" class="profile-menu-item">북마크 페이지</a>
                            <div class="menu-divider"></div>
                            <a href="#" class="profile-menu-item">로그아웃</a>
                        </div>
                    </div>
                </div>
            </div>
        </header>
    
        <!--모바일 메뉴 패널-->
        <!-- css 로 보임/숨김 처리 하므로 html 코드 다 넣어주시면 됩니다. -->
        <button class="mobile-menu">
            <span></span>
            <span></span>
            <span></span>
        </button>
        <div class="overlay"></div>
        <nav class="mobile-panel">
            <div class="mobile-menu-top">
                <img src="../assets/img/common/logo.svg" alt="로고">
            </div>
            <div class="mobile-menu-item">
                <a href="index.html">홈</a>
                <a href="list.html">Q&A</a>
                <a href="list.html">지식나눔</a>
                <a href="list.html">자유게시판</a>
                <a href="list.html">공지사항</a>
            </div>
            <div class="mobile-menu-bottom">
                <a href="#">로그인</a>
                <a href="#">회원가입</a>
            </div>
        </nav>

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
                            <div class="write-button-wrap">
                                <button class="write-button">작성하기</button>
                            </div>
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
                    	 <c:if test="${empty list}">
						    <tr>
						    <td align='center' colspan="5">데이터가 하나도 없음</td>
						    </tr>
						</c:if>
						<c:forEach items="${list}" var="dto">
                        <a href="post.do?m=select&postId=${dto.post_id}">
                            <div class="post-item">
                                <div class="post-item-header">
                                    <img src="../assets/img/common/post_info_profile02.png" class="profile" alt="프로필 사진">
                                    <p class="writer">${dto.user_id}</p>
                                    <span class="time">3시간 전</span>
                                </div>
                            </div>
                            <p class="post-title">${dto.title}</p>
                            <p class="post-content">
                                ${dto.content}
                            </p>
                            <div class="post-bottom">
                                <div class="post-keyword">
                                    <span class="category">국어</span>
                                    <span class="hashtag">#문학</span>
                                </div>
                                <div class="post-counts">
                                    <div class="view-count">
                                        <img src="../assets/img/main/post_info_icon01.png" alt="조회수">
                                        <p>111</p>
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
                <!-- 이전 버튼 -->
                <button class="pagination-button prev" disabled>
                    <span>‹ Previous</span>
                </button>
                <!-- 페이지 번호 -->
                <button class="page active">1</button>
                <button class="page">2</button>
                <button class="page">3</button>
                <button class="page">4</button>
                <button class="page">5</button>
                <span class="dots">...</span>
                <button class="page">7917</button>
                <!-- 다음 버튼 -->
                <button class="pagination-button next">
                    <span>Next ›</span>
                </button>
            </div>
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