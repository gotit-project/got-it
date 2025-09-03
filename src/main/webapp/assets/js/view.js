document.addEventListener("DOMContentLoaded", function() {

  // =========================
  // 댓글 수정
  // =========================
  document.querySelectorAll('.comment-area .edit-button').forEach(editButton => {
      editButton.addEventListener('click', function() {
        const commentId = this.dataset.commentId;
        const commentItem = document.getElementById(`comment-${commentId}`);
        const contentP = commentItem.querySelector('.comment-content');
        const currentText = contentP.textContent;

        // textarea 생성
        const textarea = document.createElement('textarea');
        textarea.value = currentText;
        textarea.classList.add('edit-textarea');
        contentP.replaceWith(textarea);

        // 버튼 변경: 저장 / 취소
        this.textContent = '취소';

        const editBtn = this; // 현재 버튼 참조

        // 저장 버튼 이벤트
        const saveButton = document.createElement('button');
        saveButton.type = "button";
        saveButton.textContent = '저장';
        saveButton.addEventListener('click', function() {
          const updatedContent = textarea.value;
          fetch(`comment.do?mode=update`, {
            method: 'POST',
            headers: { 'Content-Type': 'application/x-www-form-urlencoded' },
            body: `commentId=${commentId}&content=${encodeURIComponent(updatedContent)}`
          })
          .then(res => res.json())
          .then(data => {
            if(data.flag){
              alert("댓글이 수정되었습니다.");
              const newP = document.createElement('p');
              newP.classList.add('comment-content');
              newP.textContent = updatedContent;
              textarea.replaceWith(newP);
              saveButton.remove();
              editBtn.textContent = '수정';
            } else {
              alert("댓글 수정에 실패하였습니다.");
            }
          })
          .catch(err => console.error(err));
        });

        commentItem.querySelector('.comment-item-footer').appendChild(saveButton);

        // 취소 버튼 기능
        editBtn.addEventListener('click', function cancelEdit() {
          // textarea 제거, 원래 텍스트 복원
          const newP = document.createElement('p');
          newP.classList.add('comment-content');
          newP.textContent = currentText;
          textarea.replaceWith(newP);

          // 버튼 텍스트 원상복귀
          editBtn.textContent = '수정';
          saveButton.remove();

          // 이 이벤트 한 번만 적용되도록 제거
          editBtn.removeEventListener('click', cancelEdit);
        });
      });
    });

  // =========================
  // 댓글 삭제
  // =========================
  document.querySelectorAll('.comment-area .delete-button').forEach(deleteButton => {
    deleteButton.addEventListener('click', function(e){
      e.preventDefault();
      const commentId = this.dataset.commentId;
      if(!confirm("정말 삭제하시겠습니까?")) return;

      fetch(`comment.do?mode=delete&commentId=${commentId}`, { method: 'POST' })
        .then(res => res.json())
        .then(data => {
          if(data.flag){
            // DOM에서 바로 제거
            const commentItem = document.getElementById(`comment-${commentId}`);
            if(commentItem) commentItem.remove();
            alert("댓글이 삭제되었습니다.");
          } else {
            alert("댓글 삭제에 실패하였습니다.");
          }
        })
        .catch(err => console.error(err));
    });
  });


});
