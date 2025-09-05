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

  // 공통 이동 함수: categoryId 적용 + page=1 리셋
  function goWithCategory(catId) {
    const p = new URLSearchParams(window.location.search);
    p.set('categoryId', String(catId ?? '0'));
    p.set('page', '1');                // ✅ 확실히 붙임
    // 다른 파라미터(id, mode 등)는 그대로 보존됨
    window.location.search = '?' + p.toString();
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