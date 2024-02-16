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
        const result = await resp.text();
        console.log("albums 서버 응답 받음:", result);
        const searchObj =  parseQueryString(result);;
        console.log(searchObj);
        return searchObj;
    } catch (error) {
        console.log(error);
    }
}

async function searchArtistsDataToServer(searchData){
    try {
        const url = "/mml/search/artists"
        const config = {
            method: 'post',
            headers : {
                'content-type' : 'appication/json; charset=utf-8'
            },
            body : JSON.stringify(searchData)
        };
        const resp = await fetch(url, config);
        const result = await resp.text();
        console.log("artists 서버 응답 받음", result);
        const searchObj =  parseQueryString(result);;
        console.log(searchObj);
        return searchObj;
    } catch (error) {
        console.log(error);
    }
}

document.getElementById('searchBtn').addEventListener('click', async ()=>{
    try {
        document.getElementById('SearchBox').innerHTML = "";
        tableFrom();
        const keywordVal = document.getElementById('searchKeyword').value;
        console.log(keywordVal);
        getTokenToServer().then(token=>{
            fetchWebApi(token, keywordVal).then(res =>{
                if(res.albums){
                    searchAlbumsDataToServer(res.albums).then(album =>{
                        console.log(album);
                        for(let i=0; i<res.albums.items.length; i++){
                            let artistsUri = album.artistsUri[i];
                            let artistsName = album.artistsName[i];
                            let albumType = album.albumType[i];
                            let releaseDate = album.releaseDate[i];
                            let name = album.name[i];
                            console.log(artistsUri);
                            console.log(res.albums.items[i].external_urls.spotify);
                            const url = res.albums.items[i].external_urls.spotify;
                            let embedUrl = url.match(/\/album\/(\w+)/)[0];
                            console.log(embedUrl);
                            embedFrom(artistsUri, artistsName, albumType, releaseDate, name, embedUrl);
                        }})
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

            })
        })
    } catch (error) {
        console.log(error);
    }
})

function embedFromPlayer(url){
    document.getElementById('MusicBox').innerHTML += `
    <iframe style="border-radius:12px" 
    src="https://open.spotify.com/embed/${url}?utm_source=generator" 
    width="100%" height="152" frameBorder="0" allowfullscreen="" allow="autoplay; 
    clipboard-write; encrypted-media; fullscreen; picture-in-picture" loading="lazy" >
    </iframe>`;
}

function tableFrom(){
    document.getElementById('SearchBox').innerHTML += `
    <tr>
    <th></th>
    <th>Artist</th>
    <th>Type</th>
    <th>Release Date</th>
    <th>Name</th>
    <th></th>
    </tr>
    `;
}

function embedFrom(artistsUri, artistsName, albumType, releaseDate, name, embedUrl){
    console.log(document.getElementById('MusicBox'));
    document.getElementById('SearchBox').innerHTML += `
    <tr class="PlayMusic">
    <td>
    <img src="${artistsUri}" style="height:75px; width:75px; display:inline;">
    </td>
    <td>${artistsName}</td>
    <td>${albumType}</td>
    <td>${releaseDate}</td>
    <td>${name}</td>
    <td><input type="hidden" value="${embedUrl}"><i class="bi bi-play-circle-fill"></i></td>
    </tr>`;
}

document.addEventListener('click', (e)=>{
    document.getElementById('MusicBox').innerHTML = "";
    console.log(e.target);
    console.log(e.target.closest('tr'));
    let PlayMusicTr = e.target.closest('tr');
    if(PlayMusicTr.classList.contains('PlayMusic')){
        const embedUrl = PlayMusicTr.querySelector('input').value;
        console.log(embedUrl);
        embedFromPlayer(embedUrl);
    }
})

function parseQueryString(ParsingData) {
    const params = new URLSearchParams(ParsingData);
    const result = {};

    // 모든 키-값 쌍을 객체에 추가
    for (const [key, value] of params.entries()) {
        // 만약 이미 같은 키가 있다면 값을 배열로 저장
        if (result.hasOwnProperty(key)) {
            if (Array.isArray(result[key])) {
                result[key].push(value);
            } else {
                // 값이 이미 존재하는데 배열이 아닌 경우, 값을 배열로 변환
                result[key] = [result[key], value];
            }
        } else {
            result[key] = value;
        }
    }

    return result;
}