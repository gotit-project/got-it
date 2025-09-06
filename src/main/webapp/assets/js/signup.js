(function () {
  // 엘리먼트 참조
  var form       = document.getElementById('registerForm');
  var nameInput  = document.getElementById('username');
  var aliasInput = document.getElementById('alias');
  var emailInput = document.getElementById('email');
  var passInput  = document.getElementById('passwd');
  var pass2Input = document.getElementById('passwdConfirm');
  var agreeTerms = document.getElementById('agreeTerms');

  var errorBox   = document.querySelector('.error-message');
  var submitBtn  = document.getElementById('registerBtn');
  var spinner    = submitBtn ? submitBtn.querySelector('.spinner') : null;
  var btnText    = submitBtn ? submitBtn.querySelector('.auth-btn-text') : null;

  // 유틸
  function trim(v) {
    return (v === undefined || v === null ? '' : String(v)).trim();
  }
  function setBusy(busy) {
    if (!submitBtn) return;
    submitBtn.disabled = !!busy;
    submitBtn.setAttribute('aria-busy', busy ? 'true' : 'false');
    if (spinner) spinner.hidden = !busy;
    if (btnText) btnText.textContent = busy ? '처리 중…' : '회원가입';
  }
  function showFormError(msg) {
    if (!errorBox) return;
    errorBox.textContent = msg || '';
  }
  function markInvalid(el, invalid) {
    if (!el) return;
    if (invalid) {
      el.classList.add('is-invalid');
      el.setAttribute('aria-invalid', 'true');
    } else {
      el.classList.remove('is-invalid');
      el.setAttribute('aria-invalid', 'false');
    }
  }

  // 규칙
  var NAME_MIN  = 2;
  var ALIAS_MIN = 2;
  var ALIAS_MAX = 8;
  var PASS_MIN  = 8;
  var aliasRegex = /^[가-힣A-Za-z0-9_]+$/;
  var emailRegex = /^[^\s@]+@[^\s@]+\.[^\s@]{2,}$/;

  // 개별 검증 → 에러 메시지(정상이면 '')
  function validateName() {
    var v = trim(nameInput && nameInput.value);
    if (!v) return '이름을 입력해주세요.';
    if (v.length < NAME_MIN) return '이름은 최소 ' + NAME_MIN + '자 이상이어야 합니다.';
    return '';
  }
  function validateAlias() {
    var v = trim(aliasInput && aliasInput.value);
    if (!v) return '닉네임을 입력해주세요.';
    if (v.length < ALIAS_MIN || v.length > ALIAS_MAX)
      return '닉네임은 ' + ALIAS_MIN + '~' + ALIAS_MAX + '자여야 합니다.';
    if (!aliasRegex.test(v)) return '닉네임은 한글/영문/숫자/밑줄(_)만 가능합니다.';
    return '';
  }
  function validateEmail() {
    var v = trim(emailInput && emailInput.value);
    if (!v) return '이메일을 입력해주세요.';
    if (!emailRegex.test(v)) return '올바른 이메일 형식이 아닙니다.';
    return '';
  }
  function validatePassword() {
    var v = (passInput && passInput.value) || '';
    if (!v) return '비밀번호를 입력해주세요.';
    if (v.length < PASS_MIN) return '비밀번호는 최소 ' + PASS_MIN + '자 이상이어야 합니다.';
    return '';
  }
  function validatePasswordConfirm() {
    var v1 = (passInput && passInput.value) || '';
    var v2 = (pass2Input && pass2Input.value) || '';
    if (!v2) return '비밀번호 확인을 입력해주세요.';
    if (v1 !== v2) return '비밀번호가 일치하지 않습니다.';
    return '';
  }
  function validateTerms() {
    if (!agreeTerms || !agreeTerms.checked) return '약관에 동의해야 회원가입이 가능합니다.';
    return '';
  }

  // 전체 검증
  function validateAll() {
    showFormError('');
    // 초기화
    markInvalid(nameInput,  false);
    markInvalid(aliasInput, false);
    markInvalid(emailInput, false);
    markInvalid(passInput,  false);
    markInvalid(pass2Input, false);

    var checks = [
      { el: nameInput,  fn: validateName },
      { el: aliasInput, fn: validateAlias },
      { el: emailInput, fn: validateEmail },
      { el: passInput,  fn: validatePassword },
      { el: pass2Input, fn: validatePasswordConfirm }
    ];

    for (var i = 0; i < checks.length; i++) {
      var msg = checks[i].fn();
      if (msg) {
        markInvalid(checks[i].el, true);
        showFormError(msg);
        if (checks[i].el && typeof checks[i].el.focus === 'function') {
          checks[i].el.focus();
        }
        return false;
      }
    }

    var termsMsg = validateTerms();
    if (termsMsg) {
      showFormError(termsMsg);
      if (agreeTerms && typeof agreeTerms.focus === 'function') agreeTerms.focus();
      return false;
    }
    return true;
  }

  // 입력 시 에러 해제
  function bindClear(el) {
    if (!el) return;
    el.addEventListener('input', function () {
      markInvalid(el, false);
      showFormError('');
    });
  }
  bindClear(nameInput);
  bindClear(aliasInput);
  bindClear(emailInput);
  bindClear(passInput);
  bindClear(pass2Input);

  if (agreeTerms) {
    agreeTerms.addEventListener('change', function () {
      showFormError('');
    });
  }

  // 제출
  if (form) {
    form.addEventListener('submit', function (e) {
      e.preventDefault();
      setBusy(true);
      try {
        if (!validateAll()) {
          setBusy(false);
          return;
        }
        form.submit();
      } catch (err) {
        console.error(err);
        showFormError('일시적인 오류가 발생했습니다. 잠시 후 다시 시도해 주세요.');
        setBusy(false);
      }
    });
  }
})();
