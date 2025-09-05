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
 const likeResult = document.getElementById("likeResult");

 if (likeButton && likeResult) {
   likeButton.addEventListener("click", function (e) {
     e.preventDefault();

     const postId = likeButton.dataset.postId;
     const userId = likeButton.dataset.userId;

     if (!userId) {
       alert("로그인이 필요합니다.");
       return;
     }

     // active 토글
     toggleActive(likeButton);

     fetch(`/reaction.do?mode=like-action&postId=${postId}&userId=${userId}`, {
       method: "POST",
     })
       .then((response) => response.json())
       .then((data) => {
         if (data.success) {
           likeResult.textContent = data.likeCount;
         }
       })
       .catch((err) => {
         console.error(err);
         likeResult.textContent = "에러 발생!";
       });
   });
 }

 // =========================
 // 스크랩 토글
 // =========================
 const scrapButton = document.getElementById("scrapButton");
 const scrapResult = document.getElementById("scrapResult");

 if (scrapButton && scrapResult) {
   scrapButton.addEventListener("click", function (e) {
     e.preventDefault();

     const postId = scrapButton.dataset.postId;
     const userId = scrapButton.dataset.userId;

     if (!userId) {
       alert("로그인이 필요합니다.");
       return;
     }

     // active 토글
     toggleActive(scrapButton);

     fetch(`/reaction.do?mode=scrap-action&postId=${postId}&userId=${userId}`, {
       method: "POST",
     })
       .then((response) => response.json())
       .then((data) => {
         if (data.success) {
           scrapResult.textContent = data.scrapCount;
         }
       })
       .catch((err) => {
         console.error(err);
         scrapResult.textContent = "에러 발생!";
       });
   });
 }

 // =========================
 // active 토글 함수
 // =========================
 function toggleActive(button) {
   if (!button) return;
   button.classList.toggle("active"); 
 }
 
});