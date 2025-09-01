<%@ page contentType="text/html;charset=utf-8" import="java.sql.*"%>

<!DOCTYPE html>
<html lang="ko">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Got !t</title>
        <link rel="stylesheet" href="assets/css/reset.css">
        <link rel="stylesheet" href="assets/css/common.css">
        <link rel="stylesheet" href="assets/css/main.css">
        <script src="assets/js/main.js" defer></script>
    </head>
    <body>

        <!-- ======================================
            로그인 하면     : <header id="login">
            디폴트(비로그인) : <header id="default">
            --------------------------------------
            - login 아이디가 추가되면 
               <div class="header-right"> 가 생성됨
            - list.html 헤더 참고
        ====================================== -->
        <header id="default">
            <div class="nav">
                <a href="<%=request.getContextPath()%>/boards?type=index" class="logo">
                    <img src="assets/img/common/logo.svg" alt="로고">
                </a>
                <ul>
                    <li><a href="<%=request.getContextPath()%>/boards?type=index">홈</a></li>
                    <li><a href="<%=request.getContextPath()%>/boards?type=QnA">Q&A</a></li>
                    <li><a href="<%=request.getContextPath()%>/boards?type=knowledge">지식나눔</a></li>
                    <li><a href="<%=request.getContextPath()%>/boards?type=index">자유게시판</a></li>
                    <li><a href="<%=request.getContextPath()%>/boards?type=notice">공지사항</a></li>
                </ul>
                <div class="link-wrap">
                    <a href="#">로그인</a>
                    <a href="#">회원가입</a>
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
                <img src="assets/img/common/logo.svg" alt="로고">
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
        <!-- 모바일 메뉴 패널 끝 -->

        <!-- 컨텐츠 -->
        <div id="main" class="content-wrap">
            <!-- 좌측 랭킹 -->
            <div class="rank-wrap">
                <p class="title">Top Writer</p>
                <ul>
                    <li>
                        <span class="rank"></span>
                        <p>짱구는목말라</p>
                         <div class="score">
                            <img src="assets/img//main/rank_icon04.png" alt="메달">
                            <span class="">112</span>
                        </div>
                    </li>
                    <li>
                        <span class="rank"></span>
                        <p>헨젤과그랬대</p>
                        <div class="score">
                            <img src="assets/img//main/rank_icon04.png" alt="메달">
                            <span class="">91</span>
                        </div>
                    </li>
                    <li>
                        <span class="rank"></span>
                        <p>참지마요</p>
                        <div class="score">
                            <img src="assets/img//main/rank_icon04.png" alt="메달">
                            <span class="">79</span>
                        </div>
                    </li>
                    <li>
                        <span class="rank">4. </span>
                        <p>뽀빠이</p>
                        <div class="score">
                            <img src="assets/img//main/rank_icon04.png" alt="메달">
                            <span class="">57</span>
                        </div>
                    </li>
                    <li>
                        <span class="rank">5. </span>
                        <p>오다주어따어주다오오다</p>
                        <div class="score">
                            <img src="assets/img//main/rank_icon04.png" alt="메달">
                            <span class="">43</span>
                        </div>
                    </li>
                </ul>
            </div>
            <!-- 좌측 랭킹 요소가 fixed 되도 공간 유지하기 위해 생성한 div -->
            <div class="rank-placeholder"></div>
            <!-- 게시판 모음 -->
            <div class="board-wrap">
                <!-- 공지 게시판 -->
                <div class="notice">
                    <a href="/" class="board-title">공지사항</a>
                    <div class="post-item-wrap">
                        <!-- 게시글 한 묶음 -->
                        <a href="list.html">
                            <div class="post-item">
                                <div class="post-item-header">
                                    <img src="assets/img/common/post_info_profile.svg" class="profile" alt="프로필 사진">
                                    <p class="writer">운영관리자</p>
                                    <span class="time">3시간 전</span>
                                </div>
                                <div class="post-counts">
                                    <div class="view-count">
                                        <img src="assets/img/main/post_info_icon01.png" alt="조회수">
                                        <p>1822</p>
                                    </div>
                                    <div class="thumb-count">
                                        <img src="assets/img/main/post_info_icon02.png" alt="좋아요수">
                                        <p>312</p>
                                    </div>
                                    <div class="comment-count">
                                        <img src="assets/img/main/post_info_icon03.png" alt="댓글수">
                                        <p>57</p>
                                    </div>
                                </div>
                            </div>
                            <div class="post-title"><span class="important">[필독]</span> 게시판 이용 수칙 안내</div>
                        </a>
                        <!-- 게시글 한 묶음 끝 -->

                        <!-- 게시글 한 묶음 -->
                        <a href="list.html">
                            <div class="post-item">
                                <div class="post-item-header">
                                    <img src="assets/img/common/post_info_profile.svg" class="profile" alt="프로필 사진">
                                    <p class="writer">운영관리자</p>
                                    <span class="time">3시간 전</span>
                                </div>
                                <div class="post-counts">
                                    <div class="view-count">
                                        <img src="assets/img/main/post_info_icon01.png" alt="조회수">
                                        <p>992</p>
                                    </div>
                                    <div class="thumb-count">
                                        <img src="assets/img/main/post_info_icon02.png" alt="좋아요수">
                                        <p>132</p>
                                    </div>
                                    <div class="comment-count">
                                        <img src="assets/img/main/post_info_icon03.png" alt="댓글수">
                                        <p>27</p>
                                    </div>
                                </div>
                            </div>
                            <div class="post-title"><span class="important">[필독]</span> 폭염 대비 생활 수칙 안내</div>
                        </a>
                        <!-- 게시글 한 묶음 끝 -->
                         
                        <!-- 게시글 한 묶음 -->
                        <a href="list.html">
                            <div class="post-item">
                                <div class="post-item-header">
                                    <img src="assets/img/common/post_info_profile.svg" class="profile" alt="프로필 사진">
                                    <p class="writer">콘텐츠 관리자</p>
                                    <span class="time">3시간 전</span>
                                </div>
                                <div class="post-counts">
                                    <div class="view-count">
                                        <img src="assets/img/main/post_info_icon01.png" alt="조회수">
                                        <p>232</p>
                                    </div>
                                    <div class="thumb-count">
                                        <img src="assets/img/main/post_info_icon02.png" alt="좋아요수">
                                        <p>12</p>
                                    </div>
                                    <div class="comment-count">
                                        <img src="assets/img/main/post_info_icon03.png" alt="댓글수">
                                        <p>7</p>
                                    </div>
                                </div>
                            </div>
                            <div class="post-title"><span>[공지]</span> 전국 영어토론대회 참가자 모집</div>
                        </a>
                        <!-- 게시글 한 묶음 끝 -->
                    </div>
                </div>
                <div class="sub-board">
                    <!-- qna 게시판 -->
                    <div class="qna">
                        <a href="/" class="board-title">Q&A</a>
                        <div class="post-item-wrap">
                            <!-- 게시글 한 묶음 -->
                            <a href="list.html">
                                <div class="post-item">
                                    <div class="post-item-header">
                                        <img src="assets/img/common/post_info_profile02.png" class="profile" alt="프로필 사진">
                                        <p class="writer">짱구는목말라</p>
                                        <span class="time">32분 전</span>
                                    </div>
                                    <div class="post-counts">
                                        <div class="view-count">
                                            <img src="assets/img/main/post_info_icon01.png" alt="조회수">
                                            <p>89</p>
                                        </div>
                                        <div class="thumb-count">
                                            <img src="assets/img/main/post_info_icon02.png" alt="좋아요수">
                                            <p>3</p>
                                        </div>
                                        <div class="comment-count">
                                            <img src="assets/img/main/post_info_icon03.png" alt="댓글수">
                                            <p>12</p>
                                        </div>
                                    </div>
                                </div>
                                <div class="post-title">현대시 작품 해석이 잘 안 돼요</div>
                            </a>
                            <!-- 게시글 한 묶음 끝 -->

                            <!-- 게시글 한 묶음 -->
                            <a href="list.html">
                                <div class="post-item">
                                    <div class="post-item-header">
                                        <img src="assets/img/common/post_info_profile.svg" class="profile" alt="프로필 사진">
                                        <p class="writer">헨젤과그랬대</p>
                                        <span class="time">1시간 전</span>
                                    </div>
                                    <div class="post-counts">
                                        <div class="view-count">
                                            <img src="assets/img/main/post_info_icon01.png" alt="조회수">
                                            <p>89</p>
                                        </div>
                                        <div class="thumb-count">
                                            <img src="assets/img/main/post_info_icon02.png" alt="좋아요수">
                                            <p>3</p>
                                        </div>
                                        <div class="comment-count">
                                            <img src="assets/img/main/post_info_icon03.png" alt="댓글수">
                                            <p>12</p>
                                        </div>
                                    </div>
                                </div>
                                <div class="post-title">접선의 방정식이 이해가 안 돼요</div>
                            </a>
                            <!-- 게시글 한 묶음 끝 -->

                            <!-- 게시글 한 묶음 -->
                            <a href="list.html">
                                <div class="post-item">
                                    <div class="post-item-header">
                                        <img src="assets/img/common/post_info_profile.svg" class="profile" alt="프로필 사진">
                                        <p class="writer">뽀빠이</p>
                                        <span class="time">13시간 전</span>
                                    </div>
                                    <div class="post-counts">
                                        <div class="view-count">
                                            <img src="assets/img/main/post_info_icon01.png" alt="조회수">
                                            <p>286</p>
                                        </div>
                                        <div class="thumb-count">
                                            <img src="assets/img/main/post_info_icon02.png" alt="좋아요수">
                                            <p>9</p>
                                        </div>
                                        <div class="comment-count">
                                            <img src="assets/img/main/post_info_icon03.png" alt="댓글수">
                                            <p>5</p>
                                        </div>
                                    </div>
                                </div>
                                <div class="post-title">조건부 확률 문제 풀이 도와주세요</div>
                            </a>
                            <!-- 게시글 한 묶음 끝 -->

                            <!-- 게시글 한 묶음 -->
                            <a href="list.html">
                                <div class="post-item">
                                    <div class="post-item-header">
                                        <img src="assets/img/common/post_info_profile.svg" class="profile" alt="프로필 사진">
                                        <p class="writer">오다주어따어주다오오다</p>
                                        <span class="time">1일 전</span>
                                    </div>
                                    <div class="post-counts">
                                        <div class="view-count">
                                            <img src="assets/img/main/post_info_icon01.png" alt="조회수">
                                            <p>682</p>
                                        </div>
                                        <div class="thumb-count">
                                            <img src="assets/img/main/post_info_icon02.png" alt="좋아요수">
                                            <p>32</p>
                                        </div>
                                        <div class="comment-count">
                                            <img src="assets/img/main/post_info_icon03.png" alt="댓글수">
                                            <p>9</p>
                                        </div>
                                    </div>
                                </div>
                                <div class="post-title">세포 분열 단계 구분하기 어려워요</div>
                            </a>
                            <!-- 게시글 한 묶음 끝 -->

                            <!-- 게시글 한 묶음 -->
                            <a href="list.html">
                                <div class="post-item">
                                    <div class="post-item-header">
                                        <img src="assets/img/common/post_info_profile.svg" class="profile" alt="프로필 사진">
                                        <p class="writer">동글동글</p>
                                        <span class="time">1일 전</span>
                                    </div>
                                    <div class="post-counts">
                                        <div class="view-count">
                                            <img src="assets/img/main/post_info_icon01.png" alt="조회수">
                                            <p>182</p>
                                        </div>
                                        <div class="thumb-count">
                                            <img src="assets/img/main/post_info_icon02.png" alt="좋아요수">
                                            <p>32</p>
                                        </div>
                                        <div class="comment-count">
                                            <img src="assets/img/main/post_info_icon03.png" alt="댓글수">
                                            <p>7</p>
                                        </div>
                                    </div>
                                </div>
                                <div class="post-title">관계대명사랑 관계부사 차이 어떻게 구분하나요?</div>
                            </a>
                            <!-- 게시글 한 묶음 끝 -->
                        </div>
                    </div>
                    <!-- 지식 게시판 -->
                    <div class="study">
                        <a href="/" class="board-title">지식나눔</a>
                        <div class="post-item-wrap">
                            <!-- 게시글 한 묶음 -->
                            <a href="list.html">
                                <div class="post-item">
                                    <div class="post-item-header">
                                        <img src="assets/img/common/post_info_profile03.png" class="profile" alt="프로필 사진">
                                        <p class="writer">구르미</p>
                                        <span class="time">3시간 전</span>
                                    </div>
                                    <div class="post-counts">
                                        <div class="view-count">
                                            <img src="assets/img/main/post_info_icon01.png" alt="조회수">
                                            <p>182</p>
                                        </div>
                                        <div class="thumb-count">
                                            <img src="assets/img/main/post_info_icon02.png" alt="좋아요수">
                                            <p>32</p>
                                        </div>
                                        <div class="comment-count">
                                            <img src="assets/img/main/post_info_icon03.png" alt="댓글수">
                                            <p>7</p>
                                        </div>
                                    </div>
                                </div>
                                <div class="post-title">지문 읽을 때 핵심 키워드 표시법</div>
                            </a>
                            <!-- 게시글 한 묶음 끝 -->
                            <a href="list.html">
                                <div class="post-item">
                                    <div class="post-item-header">
                                        <img src="assets/img/common/post_info_profile02.png" class="profile" alt="프로필 사진">
                                        <p class="writer">짱구는목말라</p>
                                        <span class="time">7시간 전</span>
                                    </div>
                                    <div class="post-counts">
                                        <div class="view-count">
                                            <img src="assets/img/main/post_info_icon01.png" alt="조회수">
                                            <p>231</p>
                                        </div>
                                        <div class="thumb-count">
                                            <img src="assets/img/main/post_info_icon02.png" alt="좋아요수">
                                            <p>12</p>
                                        </div>
                                        <div class="comment-count">
                                            <img src="assets/img/main/post_info_icon03.png" alt="댓글수">
                                            <p>8</p>
                                        </div>
                                    </div>
                                </div>
                                <div class="post-title">미적분 공식 암기 꿀팁 공유</div>
                            </a>
                            <!-- 게시글 한 묶음 끝 -->

                            <!-- 게시글 한 묶음 -->
                            <a href="list.html">
                                <div class="post-item">
                                    <div class="post-item-header">
                                        <img src="assets/img/common/post_info_profile.svg" class="profile" alt="프로필 사진">
                                        <p class="writer">안녕하살법</p>
                                        <span class="time">1일 전</span>
                                    </div>
                                    <div class="post-counts">
                                        <div class="view-count">
                                            <img src="assets/img/main/post_info_icon01.png" alt="조회수">
                                            <p>463</p>
                                        </div>
                                        <div class="thumb-count">
                                            <img src="assets/img/main/post_info_icon02.png" alt="좋아요수">
                                            <p>21</p>
                                        </div>
                                        <div class="comment-count">
                                            <img src="assets/img/main/post_info_icon03.png" alt="댓글수">
                                            <p>12</p>
                                        </div>
                                    </div>
                                </div>
                                <div class="post-title">단어 외울 때 Anki 앱 추천합니다</div>
                            </a>
                            <!-- 게시글 한 묶음 끝-->

                            <!-- 게시글 한 묶음 -->
                            <a href="list.html">
                                <div class="post-item">
                                    <div class="post-item-header">
                                        <img src="assets/img/common/post_info_profile.svg" class="profile" alt="프로필 사진">
                                        <p class="writer">참지마요</p>
                                        <span class="time">1일 전</span>
                                    </div>
                                    <div class="post-counts">
                                        <div class="view-count">
                                            <img src="assets/img/main/post_info_icon01.png" alt="조회수">
                                            <p>102</p>
                                        </div>
                                        <div class="thumb-count">
                                            <img src="assets/img/main/post_info_icon02.png" alt="좋아요수">
                                            <p>18</p>
                                        </div>
                                        <div class="comment-count">
                                            <img src="assets/img/main/post_info_icon03.png" alt="댓글수">
                                            <p>5</p>
                                        </div>
                                    </div>
                                </div>
                                <div class="post-title">원자 구조 외울 때 그림 그리기 방법</div>
                            </a>
                            <!-- 게시글 한 묶음 끝 -->

                            <!-- 게시글 한 묶음 -->
                            <a href="list.html">
                                <div class="post-item">
                                    <div class="post-item-header">
                                        <img src="assets/img/common/post_info_profile.svg" class="profile" alt="프로필 사진">
                                        <p class="writer">지지징</p>
                                        <span class="time">2일 전</span>
                                    </div>
                                    <div class="post-counts">
                                        <div class="view-count">
                                            <img src="assets/img/main/post_info_icon01.png" alt="조회수">
                                            <p>88</p>
                                        </div>
                                        <div class="thumb-count">
                                            <img src="assets/img/main/post_info_icon02.png" alt="좋아요수">
                                            <p>2</p>
                                        </div>
                                        <div class="comment-count">
                                            <img src="assets/img/main/post_info_icon03.png" alt="댓글수">
                                            <p>1</p>
                                        </div>
                                    </div>
                                </div>
                                <div class="post-title">공부 집중 안 될 때 "뽀모도로 타이머" 사용해봐요!</div>
                            </a>
                            <!-- 게시글 한 묶음  끝-->
                        </div>
                    </div>
                </div>
            </div>
            <div class="empty-wrap"></div>
        </div>
        
        <!-- 푸터 -->
        <footer>
			<div class="footer-wrap">
				<div class="footer-top">
					<div class="footer-nav">
						<img src="assets/img/common/logo.svg" alt="로고" class="f-logo"></a>
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