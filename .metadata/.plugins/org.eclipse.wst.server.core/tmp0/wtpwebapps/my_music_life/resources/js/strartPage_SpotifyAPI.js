document.getElementById('searchBtn').addEventListener('click', async ()=>{
    const keywordVal = document.getElementById('searchKeyword').value;
    console.log(keywordVal);
    location.href=`/mml/main/${keywordVal}`;
})

const searchInput = document.getElementById("searchKeyword");
searchInput.addEventListener('keydown', async (e) => {
    if (e.key === 'Enter') {
        e.preventDefault();
        const keywordVal = document.getElementById('searchKeyword').value;
    	console.log(keywordVal);
    	location.href=`/mml/main/${keywordVal}`;
    }
});

