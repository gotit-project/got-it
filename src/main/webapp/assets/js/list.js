(function () {
  const listWrap = document.querySelector('.board-list');
  const cards = Array.from(document.querySelectorAll('.board-list a[data-category-id]'));
  const btnWrap = document.getElementById('category-buttons');          // 데스크톱 버튼 래퍼(있으면)
  const selectEl = document.getElementById('mobile-category-select');   // 모바일 셀렉트(있으면)
  const emptyId = 'no-result-msg';

  function ensureEmpty() {
    let el = document.getElementById(emptyId);
    if (!el) {
      el = document.createElement('div');
      el.id = emptyId;
      el.style.cssText = 'padding:20px;text-align:center;color:#888;';
      el.textContent = '해당 카테고리에 게시글이 없습니다.';
      listWrap.appendChild(el);
    }
    return el;
  }

  function applyFilter(catIdStr) {
    const want = String(catIdStr || '0'); // '0' = 전체
    let shown = 0;

    cards.forEach(a => {
      const has = String(a.dataset.categoryId || '');
      const match = (want === '0') ? true : (has === want);
      a.style.display = match ? '' : 'none';
      if (match) shown++;
    });

    ensureEmpty().style.display = (shown === 0) ? '' : 'none';

    // 버튼 active 동기화
    if (btnWrap) {
      btnWrap.querySelectorAll('button[data-cat]').forEach(b => {
        b.classList.toggle('active', String(b.dataset.cat) === want);
      });
    }
    // 셀렉트 동기화
    if (selectEl && String(selectEl.value) !== want) {
      selectEl.value = want;
    }

    // URL 갱신(뒤로가기 히스토리 오염 없이)
    const url = new URL(location.href);
    url.searchParams.set('categoryId', want);
    history.replaceState(null, '', url);
  }

  // 버튼 클릭
  if (btnWrap) {
    btnWrap.addEventListener('click', (e) => {
      const b = e.target.closest('button[data-cat]');
      if (!b) return;
      applyFilter(b.dataset.cat);
    });
  }

  // 모바일 셀렉트 변경
  if (selectEl) {
    selectEl.addEventListener('change', () => applyFilter(selectEl.value));
  }

  // 초기값: URL의 categoryId (없으면 전체=0)
  const init = new URL(location.href).searchParams.get('categoryId') || '0';
  if (selectEl) selectEl.value = init;
  applyFilter(init);
})();