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
        <script src="${pageContext.request.contextPath}/assets/js/common/header.js" defer></script>
        <script src="${pageContext.request.contextPath}/assets/js/index.js" defer></script>
    </head>
    <body>

        <%@ include file="WEB-INF/views/common/header.jsp" %>

        <!-- 컨텐츠 -->
        <div id="main" class="content-wrap">
            <div class="rank-placeholder"></div>
            <!-- 게시판 모음 -->
            <div class="board-wrap">
                <!-- 공지 게시판 -->
                <div class="notice">
                    <a href="/" class="board-title">공지사항</a>
                    <div class="post-item-wrap">
                        
						
						<button id="likeTest" data-post-id="1" data-user-id="1">
						    👍 좋아요
						</button>
						<button id="scrapTest" data-post-id="1" data-user-id="1">
						    👍 스크랩
						</button>
						<p id="likeResult">현재 좋아요 수: ${postDto.likeCount}</p>
                        <p id="scrapResult">현재 스크 수: ${postDto.scrapCount}</p> 
                        
                        <span class="corrected-mark ${postDto.updatedAt.time != postDto.createdAt.time ? '' : 'hidden'}">
						    수정됨
						</span>
                        
                       <!-- 게시글 한 묶음 -->
                        <a href="/">
                            <div class="post-item">
                                <div class="post-item-header">
                                    <img src="../assets/img/common/post_info_profile.svg" class="profile" alt="프로필 사진">
                                    <p class="writer">운영관리자</p>
                                    <span class="time">3시간 전</span>
                                </div>
                                <div class="post-counts">
                                    <div class="view-count">
                                        <img src="../assets/img/main/post_info_icon01.png" alt="조회수">
                                        <p>1822</p>
                                    </div>
                                    <div class="thumb-count">
                                        <img src="../assets/img/main/post_info_icon02.png" alt="좋아요수">
                                        <p>312</p>
                                    </div>
                                    <div class="comment-count">
                                        <img src="../assets/img/main/post_info_icon03.png" alt="댓글수">
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
                                    <img src="../assets/img/common/post_info_profile.svg" class="profile" alt="프로필 사진">
                                    <p class="writer">운영관리자</p>
                                    <span class="time">3시간 전</span>
                                </div>
                                <div class="post-counts">
                                    <div class="view-count">
                                        <img src="../assets/img/main/post_info_icon01.png" alt="조회수">
                                        <p>992</p>
                                    </div>
                                    <div class="thumb-count">
                                        <img src="../assets/img/main/post_info_icon02.png" alt="좋아요수">
                                        <p>132</p>
                                    </div>
                                    <div class="comment-count">
                                        <img src="../assets/img/main/post_info_icon03.png" alt="댓글수">
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
                                    <img src="../assets/img/common/post_info_profile.svg" class="profile" alt="프로필 사진">
                                    <p class="writer">콘텐츠 관리자</p>
                                    <span class="time">3시간 전</span>
                                </div>
                                <div class="post-counts">
                                    <div class="view-count">
                                        <img src="../assets/img/main/post_info_icon01.png" alt="조회수">
                                        <p>232</p>
                                    </div>
                                    <div class="thumb-count">
                                        <img src="../assets/img/main/post_info_icon02.png" alt="좋아요수">
                                        <p>12</p>
                                    </div>
                                    <div class="comment-count">
                                        <img src="../assets/img/main/post_info_icon03.png" alt="댓글수">
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
                        <a href="/" class="board-title">Q&amp;A</a>
                        <div class="post-item-wrap">
                            <!-- 게시글 한 묶음 -->
                            <a href="list.html">
                                <div class="post-item">
                                    <div class="post-item-header">
                                        <img src="../assets/img/common/post_info_profile02.png" class="profile" alt="프로필 사진">
                                        <p class="writer">짱구는목말라</p>
                                        <span class="time">32분 전</span>
                                    </div>
                                    <div class="post-counts">
                                        <div class="view-count">
                                            <img src="../assets/img/main/post_info_icon01.png" alt="조회수">
                                            <p>89</p>
                                        </div>
                                        <div class="thumb-count">
                                            <img src="../assets/img/main/post_info_icon02.png" alt="좋아요수">
                                            <p>3</p>
                                        </div>
                                        <div class="comment-count">
                                            <img src="../assets/img/main/post_info_icon03.png" alt="댓글수">
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
                                        <img src="../assets/img/common/post_info_profile.svg" class="profile" alt="프로필 사진">
                                        <p class="writer">헨젤과그랬대</p>
                                        <span class="time">1시간 전</span>
                                    </div>
                                    <div class="post-counts">
                                        <div class="view-count">
                                            <img src="../assets/img/main/post_info_icon01.png" alt="조회수">
                                            <p>89</p>
                                        </div>
                                        <div class="thumb-count">
                                            <img src="../assets/img/main/post_info_icon02.png" alt="좋아요수">
                                            <p>3</p>
                                        </div>
                                        <div class="comment-count">
                                            <img src="../assets/img/main/post_info_icon03.png" alt="댓글수">
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
                                        <img src="../assets/img/common/post_info_profile.svg" class="profile" alt="프로필 사진">
                                        <p class="writer">뽀빠이</p>
                                        <span class="time">13시간 전</span>
                                    </div>
                                    <div class="post-counts">
                                        <div class="view-count">
                                            <img src="../assets/img/main/post_info_icon01.png" alt="조회수">
                                            <p>286</p>
                                        </div>
                                        <div class="thumb-count">
                                            <img src="../assets/img/main/post_info_icon02.png" alt="좋아요수">
                                            <p>9</p>
                                        </div>
                                        <div class="comment-count">
                                            <img src="../assets/img/main/post_info_icon03.png" alt="댓글수">
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
                                        <img src="../assets/img/common/post_info_profile.svg" class="profile" alt="프로필 사진">
                                        <p class="writer">오다주어따어주다오오다</p>
                                        <span class="time">1일 전</span>
                                    </div>
                                    <div class="post-counts">
                                        <div class="view-count">
                                            <img src="../assets/img/main/post_info_icon01.png" alt="조회수">
                                            <p>682</p>
                                        </div>
                                        <div class="thumb-count">
                                            <img src="../assets/img/main/post_info_icon02.png" alt="좋아요수">
                                            <p>32</p>
                                        </div>
                                        <div class="comment-count">
                                            <img src="../assets/img/main/post_info_icon03.png" alt="댓글수">
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
                                        <img src="../assets/img/common/post_info_profile.svg" class="profile" alt="프로필 사진">
                                        <p class="writer">동글동글</p>
                                        <span class="time">1일 전</span>
                                    </div>
                                    <div class="post-counts">
                                        <div class="view-count">
                                            <img src="../assets/img/main/post_info_icon01.png" alt="조회수">
                                            <p>182</p>
                                        </div>
                                        <div class="thumb-count">
                                            <img src="../assets/img/main/post_info_icon02.png" alt="좋아요수">
                                            <p>32</p>
                                        </div>
                                        <div class="comment-count">
                                            <img src="../assets/img/main/post_info_icon03.png" alt="댓글수">
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
                                        <img src="../assets/img/common/post_info_profile03.png" class="profile" alt="프로필 사진">
                                        <p class="writer">구르미</p>
                                        <span class="time">3시간 전</span>
                                    </div>
                                    <div class="post-counts">
                                        <div class="view-count">
                                            <img src="../assets/img/main/post_info_icon01.png" alt="조회수">
                                            <p>182</p>
                                        </div>
                                        <div class="thumb-count">
                                            <img src="../assets/img/main/post_info_icon02.png" alt="좋아요수">
                                            <p>32</p>
                                        </div>
                                        <div class="comment-count">
                                            <img src="../assets/img/main/post_info_icon03.png" alt="댓글수">
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
                                        <img src="../assets/img/common/post_info_profile02.png" class="profile" alt="프로필 사진">
                                        <p class="writer">짱구는목말라</p>
                                        <span class="time">7시간 전</span>
                                    </div>
                                    <div class="post-counts">
                                        <div class="view-count">
                                            <img src="../assets/img/main/post_info_icon01.png" alt="조회수">
                                            <p>231</p>
                                        </div>
                                        <div class="thumb-count">
                                            <img src="../assets/img/main/post_info_icon02.png" alt="좋아요수">
                                            <p>12</p>
                                        </div>
                                        <div class="comment-count">
                                            <img src="../assets/img/main/post_info_icon03.png" alt="댓글수">
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
                                        <img src="../assets/img/common/post_info_profile.svg" class="profile" alt="프로필 사진">
                                        <p class="writer">안녕하살법</p>
                                        <span class="time">1일 전</span>
                                    </div>
                                    <div class="post-counts">
                                        <div class="view-count">
                                            <img src="../assets/img/main/post_info_icon01.png" alt="조회수">
                                            <p>463</p>
                                        </div>
                                        <div class="thumb-count">
                                            <img src="../assets/img/main/post_info_icon02.png" alt="좋아요수">
                                            <p>21</p>
                                        </div>
                                        <div class="comment-count">
                                            <img src="../assets/img/main/post_info_icon03.png" alt="댓글수">
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
                                        <img src="../assets/img/common/post_info_profile.svg" class="profile" alt="프로필 사진">
                                        <p class="writer">참지마요</p>
                                        <span class="time">1일 전</span>
                                    </div>
                                    <div class="post-counts">
                                        <div class="view-count">
                                            <img src="../assets/img/main/post_info_icon01.png" alt="조회수">
                                            <p>102</p>
                                        </div>
                                        <div class="thumb-count">
                                            <img src="../assets/img/main/post_info_icon02.png" alt="좋아요수">
                                            <p>18</p>
                                        </div>
                                        <div class="comment-count">
                                            <img src="../assets/img/main/post_info_icon03.png" alt="댓글수">
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
                                        <img src="../assets/img/common/post_info_profile.svg" class="profile" alt="프로필 사진">
                                        <p class="writer">지지징</p>
                                        <span class="time">2일 전</span>
                                    </div>
                                    <div class="post-counts">
                                        <div class="view-count">
                                            <img src="../assets/img/main/post_info_icon01.png" alt="조회수">
                                            <p>88</p>
                                        </div>
                                        <div class="thumb-count">
                                            <img src="../assets/img/main/post_info_icon02.png" alt="좋아요수">
                                            <p>2</p>
                                        </div>
                                        <div class="comment-count">
                                            <img src="../assets/img/main/post_info_icon03.png" alt="댓글수">
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
        </div>
        
        <%@ include file="WEB-INF/views/common/footer.jsp" %>
    </body>
</html>