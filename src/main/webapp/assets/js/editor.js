// ======================================
// 텍스트 에디터 
// ======================================
document.addEventListener("DOMContentLoaded", function () { 
	
  const editor = new toastui.Editor({
    el: document.querySelector('#editor'),
    height: '400px',
    initialEditType: 'markdown',
    previewStyle: 'vertical'
  });

  // form submit 전에 textarea에 값 채우기
  const form = document.querySelector("form");
  form.addEventListener("submit", function (e) {
    const markdown = editor.getMarkdown(); // rawContent
    const html = editor.getHTML();         // htmlContent
    
    document.getElementById("rawContent").value = markdown;
    document.getElementById("htmlContent").value = html;
  });

});
