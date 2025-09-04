document.addEventListener("DOMContentLoaded", function() {
   
    // ======================================
    // list.html 카테고리 버튼 클릭 시 active 클래스
    // ======================================
    const categoryWrap = document.querySelector('.category-button-wrap');
    if (categoryWrap) {
        categoryWrap.addEventListener('click', (event) => {
            if (event.target.tagName === 'BUTTON') {
                categoryWrap.querySelectorAll('button').forEach(btn => {
                    btn.classList.remove('active');
                });
                event.target.classList.add('active');
            }
        });
    }

    // ==========================================
    // view.html 좋아요/북마크 버튼 클릭 시 active 클래스
    // ==========================================
    const etcWrap = document.querySelector('.etc-button-wrap');
    if (etcWrap) {
        etcWrap.addEventListener('click', (event) => {
            if (event.target.tagName === 'BUTTON') {
                etcWrap.querySelectorAll('button').forEach(btn => {
                    btn.classList.remove('active');
                });
                event.target.classList.add('active');
            }
        });
    }
	
	// ======================================
    // view.html 수정/삭제 드롭다운 메뉴 
    // ======================================
	const viewContentWrap = document.querySelector('.content-wrap#view');
	if (viewContentWrap) { 
		const editWrap = document.querySelector('.post-item-wrap .edit-wrap');
		const editButton = document.querySelector('.post-item-wrap .edit-button');
		const editDropdownMenu = document.querySelector('.post-item-wrap .edit-dropdown-menu');
	
		// 드롭다운 토글
		editButton.addEventListener('click', (event) => {
		  event.stopPropagation(); // 버튼 클릭 시 이벤트 전파 막기
		  editDropdownMenu.classList.toggle('active');
		});
	
		// 드롭다운 바깥 클릭 시 닫기
		document.addEventListener('click', (event) => {
		  if (!editWrap.contains(event.target)) {
		    editDropdownMenu.classList.remove('active');
		  }
		});
	}

});
