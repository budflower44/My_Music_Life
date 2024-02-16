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

async function searchAlbumsDataToServer(searchData){
    try {
        const url = "/mml/search/albums";
        const config = {
            method : 'post',
            headers : {
                'content-type' : 'application/json; charset=utf-8'
            },
            body : JSON.stringify(searchData)
        };
        const resp = await fetch(url, config);
        const result = await resp.json();
        console.log("서버 응답 받음:", result);
    } catch (error) {
        console.log(error);
    }
}

document.getElementById('searchBtn').addEventListener('click', async ()=>{
    try {
        const keywordVal = document.getElementById('searchKeyword').value;
        console.log(keywordVal);
        getTokenToServer().then(token=>{
            fetchWebApi(token, keywordVal).then(res =>{
                if(res.albums){
                    searchAlbumsDataToServer(res.albums).then(albums =>{

                    })
                    for(let i=0; i<res.albums.items.length; i++){
                        console.log(res.albums.items[i].external_urls.spotify);
                        const url = res.albums.items[i].external_urls.spotify;
                        const embedUrl = url.match(/\/album\/(\w+)/)[0];
                        console.log(embedUrl);
                    }
                }
                if(res.artists){
                    for(let i=0; i<res.artists.items.length; i++){
                        console.log(res.artists.items[i].external_urls.spotify);
                        const url = res.artists.items[i].external_urls.spotify;
                        const embedUrl = url.match(/\/artist\/(\w+)/)[0];
                        console.log(embedUrl);
                    }
                }
                if(res.tracks){
                    for(let i=0; i<res.tracks.items.length; i++){
                        console.log(res.tracks.items[i].external_urls.spotify);
                        const url = res.tracks.items[i].external_urls.spotify;
                        const embedUrl = url.match(/\/track\/(\w+)/)[0];
                        console.log(embedUrl);
                    }
                }

                location.href="/mml/main?"+keywordVal;

            })
        })
    } catch (error) {
        console.log(error);
    }
})

