(function () {
  const btnWrap  = document.getElementById('category-buttons');
  const selectEl = document.getElementById('mobile-category-select');

  // 현재 쿼리 파싱
  const params = new URLSearchParams(window.location.search);
  const curCat = params.get('categoryId') || '0';

  // 버튼 active & 셀렉트 동기화
  if (btnWrap) {
    btnWrap.querySelectorAll('button[data-cat]').forEach(b => {
      b.classList.toggle('active', String(b.dataset.cat) === String(curCat));
    });
  }
  if (selectEl) {
    if ([...selectEl.options].some(o => o.value === String(curCat))) {
      selectEl.value = String(curCat);
    }
  }

  // 데스크톱 버튼 클릭
  if (btnWrap) {
    btnWrap.addEventListener('click', (e) => {
      const b = e.target.closest('button[data-cat]');
      if (!b) return;
      goWithCategory(b.dataset.cat);
    });
  }

  // 모바일 셀렉트 변경
  if (selectEl) {
    selectEl.addEventListener('change', () => goWithCategory(selectEl.value));
  }
})();

document.addEventListener("DOMContentLoaded", function () {
  const dropdown = document.querySelector(".order-dropdown");
  const toggleBtn = dropdown.querySelector(".order-toggle");
  const menu = dropdown.querySelector(".order-menu");

  toggleBtn.addEventListener("click", () => {
    menu.style.display = (menu.style.display === "block" ? "none" : "block");
  });

  // 메뉴 외부 클릭하면 닫기
  document.addEventListener("click", (e) => {
    if (!dropdown.contains(e.target)) {
      menu.style.display = "none";
    }
  });

  // 선택 시 토글 버튼에 텍스트 반영 (선택된 항목 보여주기)
  menu.querySelectorAll(".sort-btn").forEach(btn => {
    btn.addEventListener("click", () => {
      toggleBtn.textContent = btn.textContent + " ▾";
      menu.style.display = "none";
    });
  });
});