<%@ page contentType="text/html;charset=utf-8"%>

<script>
    var kind = "${kind}";
    var flag = ${flag};
    
    if(kind === "insert"){
        if(flag){
            alert("글이 등록되었습니다.");
        }else{
            alert("글 등록에 실패하였습니다.");
        }
        location.href="post.do";
    }else if(kind === "delete"){
        if(flag){
            alert("글이 삭제 되었습니다.");
        }else{
            alert("글 삭제에 실패 하였습니다.");
        }
        location.href="post.do";
    }else if(kind === "comment-insert"){
        if(flag){
            alert("댓글이 등록되었습니다.");
        }else{
            alert("댓글 등록에 실패하였습니다.");
        }
        location.href="post.do?mode=select&postId=${postId}";
    }else if(kind === "comment-update"){
        if(flag){
            alert("댓글이 수정 되었습니다.");
        }else{
            alert("댓글 수정에 실패하였습니다.");
        }
        location.href="post.do?mode=select&postId=${postId}";
    }

</script>