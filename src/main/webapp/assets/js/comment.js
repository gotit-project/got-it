document.addEventListener("DOMContentLoaded", function() {

  // =========================
  // 댓글 수정
  // =========================
  document.querySelectorAll('#view .edit-button').forEach(editButton => {
    editButton.addEventListener('click', function() {
      const commentId = this.dataset.commentId;
      const commentItem = document.getElementById(`comment-${commentId}`);
      const contentP = commentItem.querySelector('.comment-content');
      const currentText = contentP.textContent;

      const textarea = document.createElement('textarea');
      textarea.value = currentText;
      textarea.classList.add('edit-textarea');
      contentP.replaceWith(textarea);

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
          } else {
            alert("댓글 수정에 실패하였습니다.");
          }
        })
        .catch(err => console.error(err));
      });

      commentItem.querySelector('.comment-item-footer').appendChild(saveButton);
    });
  });

  // =========================
  // 댓글 삭제
  // =========================
  document.querySelectorAll('#view .delete-button').forEach(deleteButton => {
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