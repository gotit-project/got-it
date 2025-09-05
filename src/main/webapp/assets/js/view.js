document.addEventListener("DOMContentLoaded", () => {
// ===========================
// // 글 작성자 메뉴 (수정/삭제) 토글
// ===========================
 const editWrap = document.querySelector(".edit-wrap");
 if (editWrap) {
   const editButton = editWrap.querySelector(".edit-button");
   const editDropdownMenu = editWrap.querySelector(".edit-dropdown-menu");

   if (editButton && editDropdownMenu) {
     // 버튼 클릭 시 드롭다운 토글
     editButton.addEventListener("click", (event) => {
       event.stopPropagation(); // 클릭 이벤트 전파 막기
       editDropdownMenu.classList.toggle("active");
     });

     // 드롭다운 내부 클릭 시 닫히지 않도록
     editDropdownMenu.addEventListener("click", (event) => {
       event.stopPropagation();
     });

     // 문서 클릭 시 드롭다운 닫기
     document.addEventListener("click", () => {
       editDropdownMenu.classList.remove("active");
     });
   }
 }
 // =========================
 // 좋아요 토글
 // =========================
 const likeButton = document.getElementById("likeButton");
   if(likeButton) {
     likeButton.addEventListener("click", function(e) {
       e.preventDefault();
       const postId = likeButton.dataset.postId;
       const userId = likeButton.dataset.userId;
       if(!userId) { alert("로그인이 필요합니다."); return; }

       fetch(`/reaction.do?mode=like-action&postId=${postId}&userId=${userId}`, { method: "POST" })
         .then(res => res.json())
         .then(data => {
           if(data.success) {
             likeButton.classList.toggle("active");
           }
         })
         .catch(console.error);
     });
   }

 // =========================
 // 스크랩 토글
 // =========================
 const scrapButton = document.getElementById("scrapButton");
  if(scrapButton) {
    scrapButton.addEventListener("click", function(e) {
      e.preventDefault();
      const postId = scrapButton.dataset.postId;
      const userId = scrapButton.dataset.userId;
      if(!userId) { alert("로그인이 필요합니다."); return; }

      fetch(`/reaction.do?mode=scrap-action&postId=${postId}&userId=${userId}`, { method: "POST" })
        .then(res => res.json())
        .then(data => {
          if(data.success) {
            scrapButton.classList.toggle("active");
          }
        })
        .catch(console.error);
    });
  }

 // =========================
 // active 토글 함수
 // =========================
 function toggleActive(button) {
   if (!button) return;
   button.classList.toggle("active"); 
 }
 // ===========================
 // 공유 버튼 클릭 시 링크 복사
 // ===========================
 const shareButton = document.querySelector(".share-button");

     if (shareButton) {
         shareButton.addEventListener("click", () => {
             const textarea = document.createElement("textarea");
             document.body.appendChild(textarea);

             // 현재 페이지 URL 가져오기
             textarea.value = window.location.href;

             textarea.select();
             document.execCommand("copy");
             document.body.removeChild(textarea);

             alert("링크가 복사되었습니다. 필요하신 곳에 붙여넣기 하세요!");
         });
     }
 
});