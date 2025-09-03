document.addEventListener("DOMContentLoaded", function() {
    
    // ======================================
    // 모바일 패널
    // ======================================
    const hamburger = document.querySelector('.mobile-menu');
    const menuPanel = document.querySelector('.mobile-panel');
    const overlay = document.querySelector('.overlay');
    const menuItems = document.querySelectorAll('.mobile-menu-items a'); 
    
    let isMenuOpen = false;

    // 패널 토글
    function toggleMenu() {
        isMenuOpen = !isMenuOpen;
        if (isMenuOpen) {
            openMenu();
        } else {
            closeMenu();
        }
    }

    // 모바일 패널 열기
    function openMenu() {
        hamburger.classList.add('active');
        menuPanel.classList.add('active');
        overlay.classList.add('active');
        document.body.style.overflow = 'hidden';
        // 메뉴 애니메이션 초기화 후 재실행
        menuItems.forEach(item => {
            item.style.animation = 'none';
            item.offsetHeight; 
            item.style.animation = null;
        });
    }

    // 모바일 패널 닫기
    function closeMenu() {
        hamburger.classList.remove('active');
        menuPanel.classList.remove('active');
        overlay.classList.remove('active');
        document.body.style.overflow = '';
        isMenuOpen = false;
    }

    // 햄버거 버튼 클릭 → 패널 열기/닫기
    hamburger.addEventListener('click', toggleMenu);

    // 오버레이 클릭 → 패널 닫기
    overlay.addEventListener('click', closeMenu);

    // 메뉴 항목 클릭 → 페이지 이동 전 패널 닫기
    menuItems.forEach(item => {
        item.addEventListener('click', (e) => {
            e.preventDefault();
            closeMenu();
        });
    });

    // ESC 키 → 패널 닫기
    document.addEventListener('keydown', (e) => {
        if (e.key === 'Escape' && isMenuOpen) {
            closeMenu();
        }
    });

    // 화면 크기 768px 이상 → 패널 자동 닫기
    window.addEventListener('resize', () => {
        if (window.innerWidth > 780 && isMenuOpen) {
            closeMenu();
        }
    });

    // 네비게이션 버튼 클릭 시 active 클래스 처리
    const navLinks = document.querySelectorAll('header nav ul li a');
    navLinks.forEach(link => {
        link.addEventListener('click', function() {
            navLinks.forEach(navLink => navLink.classList.remove('active'));
            this.classList.add('active');
        });
    });
    // ======================================
    // 모바일 패널 끝
    // ======================================
        
    // ======================================
    // 헤더 + 아이콘/프로필 드롭다운 메뉴 
    // ======================================
    const header = document.querySelector('header');

    if (header && header.classList.contains('login')) {  // 헤더가 login 클래스일 때만 실행
        const writeButtonGroup = document.querySelector('.write-button-group');
        const plusIcon = document.querySelector('.plus-icon');
        const dropdownArrowIcon = document.querySelector('.dropdown-arrow-icon');
        const writeDropdownMenu = document.querySelector('.dropdown-menu');
        const profileButton = document.querySelector('.profile-button');
        const profileDropdownMenu = document.querySelector('.profile-dropdown-menu');

        // 글쓰기 드롭다운 토글
        function toggleWriteDropdown(event) {
            event.stopPropagation();
        writeDropdownMenu.classList.toggle('active');
        profileDropdownMenu.classList.remove('active');
        }

        // + 아이콘 클릭 → 글쓰기 드롭다운 열기
        plusIcon.addEventListener('click', toggleWriteDropdown);
        dropdownArrowIcon.addEventListener('click', toggleWriteDropdown);

        // 프로필 버튼 클릭 → 프로필 드롭다운 열기
        profileButton.addEventListener('click', (event) => {
            event.stopPropagation();
            profileDropdownMenu.classList.toggle('active');
            writeDropdownMenu.classList.remove('active');
        });

        // 드롭다운 메뉴 바깥 클릭 → 모두 닫기
        document.addEventListener('click', (event) => {
            if (!writeDropdownMenu.contains(event.target) && !writeButtonGroup.contains(event.target)) {
                writeDropdownMenu.classList.remove('active');
            }
            if (!profileDropdownMenu.contains(event.target) && !profileButton.contains(event.target)) {
                profileDropdownMenu.classList.remove('active');
            }
        }); 
    }
    // ======================================
    // 헤더 + 아이콘/프로필 드롭다운 메뉴 끝 
    // ======================================

    // ======================================
    // 랭킹 배너 스크롤 이동 시 고정 
    // ======================================
    const rankWrap = document.querySelector(".rank-wrap");
    const rankPlaceholder = document.querySelector(".rank-placeholder");
    const mainContent = document.querySelector("#main.content-wrap");
	if (rankWrap && rankPlaceholder && mainContent) {
	    const headerHeight = header.offsetHeight;
	    const headerGap = 40;
	    const transitionPoint = headerHeight + headerGap;
	
	    window.addEventListener("scroll", () => {
	        const scrollY = window.scrollY || document.documentElement.scrollTop;
		
	        // 부모 요소의 현재 위치를 실시간으로 계산
	        const mainRect = mainContent.getBoundingClientRect();
	        const mainLeft = mainRect.left;
	
	        if (scrollY > transitionPoint) {
	            // fixed 클래스 추가
	            rankWrap.classList.add("fixed");
	            rankPlaceholder.classList.add("visible");
	            
	            // 부모의 left 값을 랭킹 배너에 적용
	            rankWrap.style.left = `${mainLeft}px`;
	            
	        } else {
	            // fixed 클래스 제거
	            rankWrap.classList.remove("fixed");
	            rankPlaceholder.classList.remove("visible");
	
	            // left 값 초기화
	            rankWrap.style.left = '';
	        }
	    });
	}
    // ======================================
    // 랭킹 배너 고정 끝
    // ======================================

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
    // ======================================
    // list.html 카테고리 버튼 끝
    // ======================================

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
    // ==========================================
    // view.html 좋아요/북마크 버튼 끝
    // ==========================================
	
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
	  // ======================================
	  // view.html 수정/삭제 드롭다운 메뉴 끝 
	  // ======================================

});
