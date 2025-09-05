<%@ page contentType="text/html;charset=utf-8" import="java.sql.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="ko">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Got !t</title>
        <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/global.css">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/common/header.css">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/common/footer.css">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/index.css">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/write.css">
        <script src="${pageContext.request.contextPath}/assets/js/index.js" defer></script>
        <script src="${pageContext.request.contextPath}/assets/js/common/header.js" defer></script>
    </head>
    <body>

        <%@ include file="/WEB-INF/views/common/header.jsp" %>
        
        <!-- 컨텐츠 -->
   		<div id="write" class="content-wrap">
		  <form action="post.do?mode=insert" method="post">
		  		<!-- 유저, 보드, 카테고 임시아이디 -->
   				<input type="hidden" name="boardId" value="${board.boardId}">
		  		<input type="hidden" name="userId" value="${sessionScope.loginOkUser.userId}">
			   				
		       <div class="form-gxroup">
		           <label for="category">카테고리</label>
		           <select name="categoryId">
					  <c:if test="${not empty board.categories}">
					    <c:forEach items="${board.categories}" var="cat">
					      <option value="${cat.categoryId}">
					        ${cat.categoryName}
					      </option>
					    </c:forEach>
					  </c:if>
					</select>
		       </div>
		      
		       <div class="form-group">
		           <label for="title">제목</label>
		           <input type="text" id="title" name="title">
		       </div>
		       
		       <div class="form-group">
		           <label for="tags">태그</label>
		           <input type="text" id="postTag" name="postTag">
		       </div>
		       
		       <div class="form-group">
		           <label for="content">본문</label>
		           <textarea id="content" name="rawContent" rows="10"></textarea>
		       </div>
		       
		       <div class="button-group">
		           <input type="submit" value="전송">
		           <input type="reset" value="다시입력">
		       </div>
		  </form>
		</div>

        <!-- 푸터 -->
         <footer>
			<div class="footer-wrap">
				<div class="footer-top">
					<div class="footer-nav">
						<img src="../assets/img/common/logo.svg" alt="로고" class="f-logo" />
						<ul>
							<li><a href="" target="_blank">회사소개</a></li>
							<li><a href="" target="_blank">공지사항</a></li>
							<li><a href="" target="_blank">연락처</a></li>
							<li><a href="" target="_blank">개인정보처리방침</a></li>
						</ul>
					</div>
				</div>
				<div class="footer-bottom">
					<ul>
						<li>서울특별시 금천구 디지털로9길 23, 11층 천재IT교육센터</li>
						<li>이메일: genia@chunjae.co.kr</li>
						<li>전화: 02-3282-8589</li>
						<li>CopyRight 2025ⓒgotIt.All right Reserved.</li>
					</ul>
				</div>
			</div>
		</footer>
    </body>
</html>