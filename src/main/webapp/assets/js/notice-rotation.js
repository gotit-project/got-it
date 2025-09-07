document.addEventListener("DOMContentLoaded", function() {

	const notices = noticeData;
	const noticeText = document.getElementById("notice-text");
	const noticeLink = document.getElementById("notice-link");
	let currentIndex = 0;

	// 초기 텍스트와 href
	noticeText.textContent = notices[currentIndex].title;
	noticeLink.href = notices[currentIndex].url;

	setInterval(() => {
	  // 애니메이션 클래스 추가
	  noticeText.classList.add('animate-up');

	  setTimeout(() => {
	    // 텍스트 교체
	    currentIndex = (currentIndex + 1) % notices.length;
	    noticeText.textContent = notices[currentIndex].title;
	    noticeLink.href = notices[currentIndex].url;

	    // 클래스 제거 후 다시 적용 가능
	    noticeText.classList.remove('animate-up');
	  }, 500); // CSS 애니메이션 시간과 맞춤
	}, 3000);
});