<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
  <head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>회원가입</title>
    <link rel="stylesheet" href="assets/css/global.css" />
    <link rel="stylesheet" href="assets/css/auth.css" />
    <script src="${pageContext.request.contextPath}/assets/js/signup.js" defer></script>
  </head>
  <body>
    <div class="auth-container">
      <div class="auth-wrap">
        <div class="auth-header">
          <h1 class="h-tit">
            <a href="/">
              <img src="assets/img/common/logo.svg" alt="logo" />
            </a>
          </h1>
        </div>

        <form
          class="auth-form"
          action="auth.do?mode=signup-action"
          method="post"
          novalidate
          id="registerForm"
        >
          <div class="auth-content">
            <div class="auth-title">
              <h2>GOT IT에 가입해 보세요!</h2>
            </div>

            <div class="auth-group">
              <!-- 이름 -->
              <div class="inp-group">
                <input
                  type="text"
                  class="inp-fd"
                  id="username"
                  name="name"
                  placeholder=""
                  required
                  autocomplete="name"
                  minlength="2"
                />
                <label for="username" class="inp-lb">이름</label>
              </div>
              
               <!-- 닉네임 -->
              <div class="inp-group">
                <input
                  type="text"
                  class="inp-fd"
                  id="alias"
                  name="alias"
                  placeholder=""
                  required
                  minlength="2"
                  maxlength="8"
                />
                <label for="alias" class="inp-lb">닉네임</label>
              </div>
              

              <!-- 이메일 -->
              <div class="inp-group">
                <input
                  type="email"
                  class="inp-fd"
                  id="email"
                  name="email"
                  placeholder=""
                  required
                  autocomplete="email"
                  inputmode="email"
                />
                <label for="email" class="inp-lb">이메일</label>
              </div>

              <!-- 비밀번호 -->
              <div class="inp-group">
                <input
                  type="password"
                  class="inp-fd"
                  id="passwd"
                  name="passwd"
                  placeholder=""
                  required
                  autocomplete="new-password"
                  minlength="8"
                />
                <label for="passwd" class="inp-lb">비밀번호 (8자 이상)</label>
              </div>

              <!-- 비밀번호 확인 -->
              <div class="inp-group">
                <input
                  type="password"
                  class="inp-fd"
                  id="passwdConfirm"
                  name="passwdConfirm"
                  placeholder=""
                  required
                  autocomplete="new-password"
                  minlength="8"
                />
                <label for="passwdConfirm" class="inp-lb">비밀번호 확인</label>
              </div>

              <!-- 약관 동의 -->
              <div class="terms-wrap">
                <label class="terms-row">
                  <input type="checkbox" id="agreeTerms" name="agreeTerms"  required />
                  <span>이용약관 및 개인정보 처리방침에 동의합니다.</span>
                </label>
                <p class="terms-links">
                  <a href="/terms" target="_blank" rel="noopener">이용약관</a> ·
                  <a href="/privacy" target="_blank" rel="noopener"
                    >개인정보 처리방침</a
                  >
                </p>
              </div>

              <!-- 도움말/에러 -->
              <div class="help-text-wrap">
                <p class="error-message" aria-live="polite">${error}</p>
                <p class="help-text">
                  이미 계정이 있으신가요?
                  <a href="auth.do?mode=login-form">로그인</a>
                </p>
              </div>
            </div>
          </div>

          <!-- 버튼 영역 -->
          <div class="auth-btn-wrap">
            <button
              type="submit"
              class="auth-btn"
              aria-busy="false"
              id="registerBtn"
            >
              <span class="spinner" hidden></span>
              <span class="auth-btn-text">회원가입</span>
            </button>
          </div>
        </form>
      </div>
    </div>
  </body>
</html>
