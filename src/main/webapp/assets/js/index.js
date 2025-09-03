// ======================================
// 모바일 헤더 + 사이드
// ======================================
document.addEventListener("DOMContentLoaded", function () {
  const btn = document.querySelector(".mobile-menu");
  const panel = document.getElementById("mobilePanel");
  const overlay = document.querySelector(".overlay");

  if (!btn || !panel || !overlay) return;

  const open = () => {
    document.body.classList.add("menu-open");
    btn.setAttribute("aria-expanded", "true");
    panel.setAttribute("aria-hidden", "false");
    overlay.hidden = false;
    // 스크롤 잠금(선택)
    document.body.style.overflow = "hidden";
  };
  const close = () => {
    document.body.classList.remove("menu-open");
    btn.setAttribute("aria-expanded", "false");
    panel.setAttribute("aria-hidden", "true");
    overlay.hidden = true;
    document.body.style.overflow = "";
  };

  btn.addEventListener("click", () => {
    const opened = document.body.classList.contains("menu-open");
    opened ? close() : open();
  });
  overlay.addEventListener("click", close);
  document.addEventListener("keydown", (e) => {
    if (e.key === "Escape") close();
  });
});

// ======================================
// 헤더 + 아이콘/프로필 드롭다운 메뉴
// ======================================
document.addEventListener("DOMContentLoaded", () => {
  const header = document.querySelector("header");
  const isLoggedIn = header && header.id === "login";
  if (!isLoggedIn) return;

  // 로그인 상태에서만 필요한 요소들
  const writeButtonGroup = document.querySelector(".write-button-group");
  const plusIcon = document.querySelector(".plus-icon");
  const dropdownArrowIcon = document.querySelector(".dropdown-arrow-icon");
  const writeDropdownMenu = document.querySelector(".dropdown-menu");
  const profileButton = document.querySelector(".profile-button");
  const profileDropdownMenu = document.querySelector(".profile-dropdown-menu");

  // 필요한 요소가 하나라도 없으면 조용히 종료 (런타임 에러 방지)
  if (
    !writeButtonGroup ||
    !plusIcon ||
    !dropdownArrowIcon ||
    !writeDropdownMenu ||
    !profileButton ||
    !profileDropdownMenu
  ) {
    return;
  }

  // 글쓰기 드롭다운 토글
  function toggleWriteDropdown(event) {
    event.stopPropagation();
    writeDropdownMenu.classList.toggle("active");
    profileDropdownMenu.classList.remove("active");
  }

  plusIcon.addEventListener("click", toggleWriteDropdown);
  dropdownArrowIcon.addEventListener("click", toggleWriteDropdown);

  // 프로필 드롭다운 토글
  profileButton.addEventListener("click", (event) => {
    event.stopPropagation();
    profileDropdownMenu.classList.toggle("active");
    writeDropdownMenu.classList.remove("active");
  });

  // 드롭다운 내부 클릭 시 닫히지 않도록
  writeDropdownMenu.addEventListener("click", (e) => e.stopPropagation());
  profileDropdownMenu.addEventListener("click", (e) => e.stopPropagation());

  // 바깥 클릭 시 닫기
  document.addEventListener("click", (event) => {
    if (
      !writeDropdownMenu.contains(event.target) &&
      !writeButtonGroup.contains(event.target)
    ) {
      writeDropdownMenu.classList.remove("active");
    }
    if (
      !profileDropdownMenu.contains(event.target) &&
      !profileButton.contains(event.target)
    ) {
      profileDropdownMenu.classList.remove("active");
    }
  });
});

	