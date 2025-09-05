document.addEventListener("DOMContentLoaded", function() {

// =========================
// 댓글 수정
// =========================
document.querySelectorAll('#view .edit-button').forEach(editButton => {
      editButton.addEventListener('click', function() {
        const commentItem = this.closest('.comment-item');
        const contentP = commentItem.querySelector('.comment-content');
        const footer = commentItem.querySelector('.comment-item-footer');
        
        // 현재 상태를 확인: 수정 모드 진입 여부
        const isEditing = this.textContent === '취소';

        if (isEditing) {
          // '취소' 버튼을 누른 경우 (수정 모드에서 벗어남)
          const textarea = commentItem.querySelector('.edit-textarea');
          const saveButton = footer.querySelector('.save-button');
          
          // textarea를 다시 p 태그로 바꾸고 저장 버튼 삭제
          const newP = document.createElement('p');
          newP.classList.add('comment-content');
          newP.textContent = textarea.value;
          textarea.replaceWith(newP);
          
          saveButton.remove();
          
          // 버튼 텍스트를 '수정'으로 되돌림
          this.textContent = '수정';
          
        } else {
          // '수정' 버튼을 누른 경우 (수정 모드 진입)
          const postId = this.dataset.postId;
          const commentId = this.dataset.commentId;
          const currentText = contentP.textContent;

          const textarea = document.createElement('textarea');
          textarea.value = currentText;
          textarea.classList.add('edit-textarea');
          contentP.replaceWith(textarea);
          
          // '수정' 버튼 텍스트를 '취소'로 변경
          this.textContent = '취소';

          const saveButton = document.createElement('button');
          saveButton.type = "button";
          saveButton.textContent = '저장';
          saveButton.classList.add('save-button'); // 클래스 추가
          
          // 저장 버튼 클릭 이벤트
          saveButton.addEventListener('click', function() {
            const updatedContent = textarea.value;
            fetch(`comment.do?mode=update`, {
              method: 'POST',
              headers: { 'Content-Type': 'application/x-www-form-urlencoded' },
              body: `postId=${postId}&commentId=${commentId}&content=${encodeURIComponent(updatedContent)}`
            })
            .then(res => res.json())
            .then(data => {
              if (data.flag) {
                const newP = document.createElement('p');
                newP.classList.add('comment-content');
                newP.textContent = updatedContent;
                textarea.replaceWith(newP);
                
                saveButton.remove();
                
                // 저장 성공 시 '취소' 버튼을 다시 '수정'으로 변경
                editButton.textContent = '수정';
              } else {
                alert("댓글 수정에 실패하였습니다.");
              }
            })
            .catch(err => console.error(err));
          });

          footer.appendChild(saveButton);
        }
      });
    });
 

  // =========================
  // 댓글 삭제
  // =========================
  document.querySelectorAll('#view .delete-button').forEach(deleteButton => {
    deleteButton.addEventListener('click', function(e){
      e.preventDefault();
	  const postId = this.dataset.postId;
      const commentId = this.dataset.commentId;
      if(!confirm("정말 삭제하시겠습니까?")) return;

      fetch(`comment.do?mode=delete&postId=${postId}&commentId=${commentId}`, { method: 'POST' })
        .then(res => res.json())
        .then(data => {
          if(data.flag){
            // DOM에서 바로 제거
            const commentItem = document.getElementById(`comment-${commentId}`);
            if(commentItem) commentItem.remove();
          } else {
            alert("댓글 삭제에 실패하였습니다.");
          }
        })
        .catch(err => console.error(err));
    });
  });


});