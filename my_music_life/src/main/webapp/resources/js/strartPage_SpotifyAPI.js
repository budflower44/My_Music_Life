async function getTokenToServer(){
    try {
        const url = "/mml/token";
        const config = {
            method:'post'
        };
        const resp = await fetch(url, config);
        const result = await resp.text();
        // console.log("getTokenToServer result >> "+result);
        return result;
    } catch (error) {
        console.log(error);
    }
}

async function fetchWebApi(token, keyword) {
    var res1 = encodeURI(keyword);
    console.log(keyword);
    const res = await fetch(`https://api.spotify.com/v1/search?q=${keyword}&type=album%2Ctrack%2Cartist`, {
        headers: {
            Authorization: `Bearer ${token}`,
        },
        method : 'GET'
    });
  const data = await res.json();
  console.log(data);
  return data;
}

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

