let contentCommentDiv = document.querySelector('.contentCommentDiv');

document.getElementById('cmtPostBtn').addEventListener('click', ()=>{
    let cmtText = document.getElementById('cmtText');
    if(cmtText.value == null || cmtText.value == ''){
        alert('댓글을 입력해주세요.');
        cmtText.focus();
        return false;
    }else{
        let cmtData={
            id : "",
            writer: document.getElementById('cmtWriter') .innerText,
            content : cmtText.value
        };
    }
})