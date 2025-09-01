<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>got-it | 글 작성</title>
        <link rel="stylesheet" href="../assets/css/reset.css">
        <link rel="stylesheet" href="../assets/css/common.css">
        <link rel="stylesheet" href="../assets/css/write.css">
        <script src="../assets/js/main.js" defer></script>
    </head>
    <body>
        <!-- ======================================
            로그인 하면     : <header id="login">
            디폴트(비로그인) : <header id="default">
            --------------------------------------
            - login 아이디가 추가되면 
               <div class="header-right"> 가 생성됨
            - list.html 헤더 참고
        ====================================== -->
        <header id="default">
            <div class="nav">
                <a href="index.html" class="logo">
                    <img src="../assets/img/common/logo.svg" alt="로고">
                </a>
                <ul>
                    <li><a href="index.html">홈</a></li>
                    <li><a href="list.html">Q&A</a></li>
                    <li><a href="list.html">지식나눔</a></li>
                    <li><a href="list.html">자유게시판</a></li>
                    <li><a href="list.html">공지사항</a></li>
                </ul>
                <div class="link-wrap">
                    <a href="#">로그아웃</a>
                    <a href="#">회원가입</a>
                </div>
            </div>
        </header>

        <!--모바일 메뉴 패널-->
        <!-- css 로 보임/숨김 처리 하므로 html 코드 다 넣어주시면 됩니다. -->
        <button class="mobile-menu">
            <span></span>
            <span></span>
            <span></span>
        </button>
        <div class="overlay"></div>
        <nav class="mobile-panel">
            <div class="mobile-menu-top"> 
                <img src="../assets/img/common/logo.svg" alt="로고">
            </div>
            <div class="mobile-menu-item">
                <a href="index.html">홈</a>
                <a href="list.html">Q&A</a>
                <a href="list.html">지식나눔</a>
                <a href="list.html">자유게시판</a>
                <a href="list.html">공지사항</a>
            </div>
            <div class="mobile-menu-bottom">
                <a href="#">로그인</a>
                <a href="#">회원가입</a>
            </div>
        </nav>
        
        <!-- 컨텐츠 -->
   		<div id="write" class="content-wrap">
		  <form action="post.do?m=insert" method="post">
		  		<!-- 유저, 보드 임시아이디 -->
		   		<input type="hidden" name="user_id" value="1"> 	
   				<input type="hidden" name="board_id" value="1">
   				
   				<c:forEach items="${list}" var="dto">
   				
			       <div class="form-group">
			           <label for="category">카테고리</label>
			           <input type="text" id="category" name="board_id">
			       </div>
			       
			       <div class="form-group">
			           <label for="title">제목</label>
			           <input type="text" id="title" name="title" value="${dto.title}">
			       </div>
			       
			       <div class="form-group">
			           <label for="tags">태그</label>
			           <input type="text" id="tag" name="tag" value="${dto.tag}">
			       </div>
			       
			       <div class="form-group">
			           <label for="content">본문</label>
			           <textarea id="content" name="content" rows="10">${dto.content}</textarea>
			       </div>
		       </c:forEach>
		       
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
						<img src="../assets/img/common/logo.svg" alt="로고" class="f-logo"></a>
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