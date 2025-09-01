<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<html lang="ko">
  <head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>로그인</title>
    <link rel="stylesheet" href="../assets/css/global.css" />
    <link rel="stylesheet" href="../assets/css/login.css" />
  </head>
  <body>
    <div class="login-container">
      <div class="login-wrap">
        <div class="login-header">
          <h1 class="h-tit">
            <img src="imgs/Gotit-logo.svg" />
          </h1>
        </div>

        <form class="login-form" action="/auth/login" method="post" novalidate>
          <div class="login-content">
            <div class="login-title">
              <h2>GOT IT에 오신 것을 환영합니다!</h2>
            </div>

            <div class="login-group">
              <div class="inp-group">
                <input
                  type="email"
                  class="inp-fd"
                  id="useremail"
                  name="email"
                  placeholder=""
                  required
                  autocomplete="email"
                  inputmode="email"
                />
                <label for="useremail" class="inp-lb">이메일</label>
              </div>

              <!-- 비밀번호 -->
              <div class="inp-group">
                <input
                  type="password"
                  class="inp-fd"
                  id="passwd"
                  name="password"
                  placeholder=""
                  required
                  autocomplete="current-password"
                />
                <label for="passwd" class="inp-lb">비밀번호</label>
              </div>

              <!-- 도움말/아이디 찾기 -->
              <div class="help-text-wrap">
                <!-- 에러 메시지 영역 (동적 메시지 바인딩 대신 고정 영역) -->
                <p class="error-message" aria-live="polite"></p>
                <p class="help-text">
                  <a href="/auth/remember">아이디 및 비밀번호를 잊으셨나요?</a>
                </p>
              </div>
            </div>
          </div>

          <!-- 버튼 영역 -->
          <div class="login-btn-wrap">
            <button type="submit" class="login-btn" aria-busy="false">
              <span class="spinner" hidden></span>
              <span class="login-btn-text">로그인</span>
            </button>

            <p class="signup-text">
              계정이 없으신가요? <a href="./register.html">회원가입</a>
            </p>
          </div>
        </form>
      </div>
    </div>
  </body>
</html>
