<%@ page contentType="text/html;charset=utf-8" import="java.sql.*"%>

	  <!-- 댓글 -->
      <div class="comment-wrap">
          <p><span>7</span> 개의 답변</p>
          <form action="${pageContext.request.contextPath}/comment.do?mode=insert&postId=${post.postId}" method="post">
       		<!-- 유저, 보드, 카테고 임시아이디 -->
	   		<input type="hidden" name="userId" value="${sessionScope.loginOkUser.userId}">
  			<input type="hidden" name="boardId" value="1">
		    <input type="hidden" name="categorieId" value="1" />
            <div class="write-area">
                <div class="top-row">
                   <img src="../assets/img/common/post_info_profile.svg" alt="프로필 이미지">
                 
                    <c:choose>
			        	<c:when test="${not empty sessionScope.loginOkUser}">
					    	<textarea name="content"></textarea>
					    </c:when>
					    <c:otherwise>
					        <textarea name="content" readonly></textarea>
					        	<p class="login-text">댓글을 작성하려면 <a href="auth.do?mode=login-form">로그인</a>이 필요합니다.</p>
					     </c:otherwise>
   					</c:choose>
					    
               </div>
               <div class="button-row">
                   <button type="submit">작성하기</button>
               </div>
           </div>
          </form>	
		  <!-- 댓글 한 묶음 -->
	      <div class="comment-area">
	      <c:forEach items="${commentList}" var="commentDto">
	          <div class="comment-item" id="comment-${commentDto.commentId}">
	               <div class="comment-item-header">
	                   <img src="../assets/img/common/post_info_profile06.png" class="comment-profile" alt="프로필 사진">
	                   <div class="profile-info">
	                       <div>
	                           <p class="writer">${commentDto.nickname}닉네임 </p>
	                           <span class="time"></span>
	                           <span class="corrected-mark">수정됨</span> 
	                       </div>
	                       <button class="adopted-mark checked">
	                           <svg width="40" height="40" viewBox="0 0 40 40" fill="none" xmlns="http://www.w3.org/2000/svg">
	                               <path d="M6.72492 25.7983C6.68647 26.0861 6.66698 26.3762 6.66659 26.6666C6.66659 30.6299 10.2299 33.8066 14.2016 33.2733C15.3566 35.3299 17.5566 36.6666 19.9999 36.6666C22.4433 36.6666 24.6433 35.3299 25.7983 33.2733C29.7616 33.8066 33.3333 30.6299 33.3333 26.6666C33.3333 26.3783 33.3133 26.0883 33.2749 25.7983C35.3299 24.6433 36.6666 22.4416 36.6666 19.9999C36.6666 17.5583 35.3299 15.3566 33.2749 14.2016C33.3133 13.9116 33.3333 13.6216 33.3333 13.3333C33.3333 9.36992 29.7616 6.18658 25.7983 6.72658C24.6433 4.66992 22.4433 3.33325 19.9999 3.33325C17.5566 3.33325 15.3566 4.66992 14.2016 6.72658C10.2299 6.18658 6.66659 9.36992 6.66659 13.3333C6.66659 13.6216 6.68658 13.9116 6.72492 14.2016C4.66992 15.3566 3.33325 17.5583 3.33325 19.9999C3.33325 22.4416 4.66992 24.6433 6.72492 25.7983ZM9.12825 16.7933L10.9649 16.3049L10.2416 14.5499C10.0829 14.1639 10.0009 13.7506 9.99992 13.3333C9.99992 11.4949 11.4949 9.99992 13.3333 9.99992C13.7449 9.99992 14.1649 10.0833 14.5499 10.2416L16.3066 10.9649L16.7949 9.12825C16.983 8.42186 17.3992 7.79739 17.9788 7.35193C18.5584 6.90647 19.2689 6.66496 19.9999 6.66496C20.7309 6.66496 21.4415 6.90647 22.021 7.35193C22.6006 7.79739 23.0168 8.42186 23.2049 9.12825L23.6933 10.9649L25.4499 10.2416C25.8349 10.0833 26.2549 9.99992 26.6666 9.99992C28.5049 9.99992 29.9999 11.4949 29.9999 13.3333C29.9999 13.7449 29.9166 14.1666 29.7583 14.5499L29.0349 16.3049L30.8716 16.7933C31.5763 16.9837 32.1987 17.401 32.6425 17.9806C33.0863 18.5602 33.3268 19.2699 33.3268 19.9999C33.3268 20.7299 33.0863 21.4396 32.6425 22.0192C32.1987 22.5989 31.5763 23.0162 30.8716 23.2066L29.0349 23.6949L29.7583 25.4499C29.9166 25.8333 29.9999 26.2549 29.9999 26.6666C29.9999 28.5049 28.5049 29.9999 26.6666 29.9999C26.2549 29.9999 25.8349 29.9166 25.4499 29.7583L23.6933 29.0349L23.2049 30.8716C23.0168 31.578 22.6006 32.2024 22.021 32.6479C21.4415 33.0934 20.7309 33.3349 19.9999 33.3349C19.2689 33.3349 18.5584 33.0934 17.9788 32.6479C17.3992 32.2024 16.983 31.578 16.7949 30.8716L16.3066 29.0349L14.5499 29.7583C14.1638 29.9168 13.7506 29.9989 13.3333 29.9999C11.4949 29.9999 9.99992 28.5049 9.99992 26.6666C9.99992 26.2549 10.0833 25.8333 10.2416 25.4499L10.9649 23.6949L9.12825 23.2066C8.42351 23.0162 7.80113 22.5989 7.35733 22.0192C6.91354 21.4396 6.67305 20.7299 6.67305 19.9999C6.67305 19.2699 6.91354 18.5602 7.35733 17.9806C7.80113 17.401 8.42351 16.9837 9.12825 16.7933Z" fill="#666C75" fill-opacity="0.3"/>
	                               <path d="M26.2368 17.8501L23.8901 15.4834L18.3384 20.9817L16.1784 18.8217L13.8218 21.1784L18.3284 25.6851L26.2368 17.8501Z" fill="#666C75" fill-opacity="0.3"/>
	                           </svg>
	                       </button>
	                   </div>
	               </div>
	               <p class="comment-content">${commentDto.content}</p>
	               
	          
   					
   					
	               <div class="comment-item-footer">
			            <c:choose>
			                <c:when test="${sessionScope.loginOkUser.userId == commentDto.userId}">
			                    <button data-comment-id="${commentDto.commentId}" 
			                            data-post-id="${post.postId}" 
			                            class="edit-button">수정</button>
			                    <button data-comment-id="${commentDto.commentId}" 
			                            data-post-id="${post.postId}" 
			                            class="delete-button">삭제</button>
			                </c:when>
			                <c:otherwise>
			                </c:otherwise>
			            </c:choose>
					</div>
	               
	          	</div>
	          </c:forEach>
	      </div>
	      <!-- 댓글 한 묶음 끝 -->
	
	      <!-- 대댓글 한 묶음 -->
	     <!--  <div class="recomment-area">
          <div class="recomment-item">
              <div class="recomment-item-header">
                  <img src="../assets/img/common/post_info_profile02.png" class="comment-profile" alt="프로필 사진">
                  <div class="profile-info">
                      <p class="writer">짱구는목말라</p>
                      <span class="time">1시간 전</span>
                    </div>
              </div>
              <p class="recomment-content">
                  추가적인 설명 덕분에 이해가 더 잘되었어요! 감사합니다! 시험 준비할 때 ‘아픔+수용’ 두 가지 포인트로 외워두면 되겠네요 ㅎㅎ
              </p>
              <div class="recomment-item-footer">
                  <button>수정</button>
              </div>
          </div>
      </div> -->
      	  <!-- 대댓글 한 묶음 끝 -->
      </div>
    