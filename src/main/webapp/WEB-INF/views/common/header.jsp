<%@ page contentType="text/html;charset=utf-8" import="java.sql.*"%>
<!-- ======================================
    로그인 하면     : <header id="login">
    디폴트(비로그인) : <header id="default">
    --------------------------------------
    - login 아이디가 추가되면 
       <div class="header-right"> 가 생성됨
====================================== -->

<header id="default" data-logged-in="${not empty sessionScope.loginOkUser}">
  <div class="nav">
    <a href="index.html" class="logo">
      <img
        src="${pageContext.request.contextPath}/assets/img/common/logo.svg"
        alt="로고"
      />
    </a>
    <ul>
      <li><a href="index.html">홈</a></li>
      <li><a href="list.html">Q&A</a></li>
      <li><a href="list.html">지식나눔</a></li>
      <li><a href="list.html">자유게시판</a></li>
      <li><a href="list.html">공지사항</a></li>
    </ul>
    <div class="link-wrap">
      <a href="auth.do?mode=login-form">로그인</a>
      <a href="auth.do?mode=signup-form">회원가입</a>
    </div>
    <!-- 로그인 상태 전용 UI -->
    <div class="header-right">
      <div class="write-button-wrap">
        <button class="write-button-group">
          <span class="plus-icon">
            <svg
              width="20"
              height="20"
              viewBox="0 0 24 24"
              fill="none"
              xmlns="http://www.w3.org/2000/svg"
            >
              <path
                d="M12 5V19"
                stroke="white"
                stroke-width="2"
                stroke-linecap="round"
                stroke-linejoin="round"
              />
              <path
                d="M5 12H19"
                stroke="white"
                stroke-width="2"
                stroke-linecap="round"
                stroke-linejoin="round"
              />
            </svg>
          </span>
          <span class="dropdown-arrow-icon">
            <svg
              width="20"
              height="20"
              viewBox="0 0 24 24"
              fill="none"
              xmlns="http://www.w3.org/2000/svg"
            >
              <path
                d="M6 9L12 15L18 9"
                stroke="white"
                stroke-width="2"
                stroke-linecap="round"
                stroke-linejoin="round"
              />
            </svg>
          </span>
        </button>
        <div class="dropdown-menu">
          <a href="#" class="menu-item"> 새 글 쓰기 </a>
          <div class="menu-divider"></div>
          <a href="#" class="menu-item with-icon">
            <span><strong>Q&A</strong>에 글쓰기</span>
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
        <button class="profile-button" style="background-image: ${sessionScope.loginOkUser.nickname}"></button>
        <div class="profile-dropdown-menu">
          <div class="profile-info">
            <strong>내 계정</strong>
            <p>${sessionScope.loginOkUser.email}</p>
            <p><span>${sessionScope.loginOkUser.nickname}</span> 님, 안녕하세요.</p>
          </div>
          <div class="menu-divider"></div>
          <a href="#" class="profile-menu-item">북마크 페이지</a>
          <div class="menu-divider"></div>
          <a href="auth.do?mode=logout" class="profile-menu-item">로그아웃</a>
        </div>
      </div>
    </div>
  </div>
</header>

<!--모바일 메뉴 패널-->

<!-- 모바일 전용: 햄버거 버튼/오버레이/사이드패널 -->
<button
  class="mobile-menu"
  aria-label="메뉴 열기"
  aria-expanded="false"
  aria-controls="mobilePanel"
>
  <span></span><span></span><span></span>
</button>

<div class="overlay" hidden></div>

<nav id="mobilePanel" class="mobile-panel" aria-hidden="true">
  <div class="mobile-menu-top">
    <div>
      <img
        src="${pageContext.request.contextPath}/assets/img/common/logo.svg"
        alt="로고"
      />
    </div>
    <ul class="mobile-menu-item">
      <li class="mobile-menu-li"><a href="index.html">홈</a></li>
      <li class="mobile-menu-li"><a href="index.html">Q&A</a></li>
      <li class="mobile-menu-li"><a href="index.html">지식나눔</a></li>
      <li class="mobile-menu-li"><a href="index.html">자유게시판</a></li>
      <li class="mobile-menu-li"><a href="index.html">공지사항</a></li>
    </ul>
  </div>
  <ul class="mobile-menu-bottom">
    <li class="mobile-bottom-li"><a href="index.html">로그인</a></li>
    <li class="mobile-bottom-li"><a href="index.html">회원가입</a></li>
  </ul>
</nav>
<!-- 모바일 메뉴 패널 끝 -->
