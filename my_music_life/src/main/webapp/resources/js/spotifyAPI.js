//비동기로 컨트롤러에서 토큰 가져오기
async function getTokenToServer(){
    try {
        const url = "/mml/token";
        const config = {
            method:'post'
        };
        const resp = await fetch(url, config);
        const result = await resp.text();
        return result;
    } catch (error) {
        console.log(error);
    }
}

//Search 스포티파이 API 호출하기
async function fetchWebApi(token, keyword) {
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

//SearchData 중 albums에 대한 데이터만 DB로 넘겨서 저장시키고, 쿼리스트링 오브젝트로 반환하기 (embemFrom 호출에 사용하기 위함)
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

//SearchData 중 artists에 대한 데이터만 DB로 넘겨서 저장시키고, 쿼리스트링 오브젝트로 반환하기 (embemFrom 호출에 사용하기 위함)
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

//SearchData 중 Tracks에 대한 데이터만 DB로 넘겨서 저장시키고, 쿼리스트링 오브젝트로 반환하기 (embemFrom 호출에 사용하기 위함)
async function searchTracksDataToServer(searchData){
    try {
        const url = "/mml/search/tracks"
        const config = {
            method: 'post',
            headers : {
                'content-type' : 'appication/json; charset=utf-8'
            },
            body : JSON.stringify(searchData)
        };
        const resp = await fetch(url, config);
        const result = await resp.text();
        console.log("tracks 서버 응답 받음", result);
        const searchObj =  parseQueryString(result);;
        console.log(searchObj);
        return searchObj;
    } catch (error) {
        console.log(error);
    }
}

document.getElementById('searchBtn').addEventListener('click', async ()=>{
    search();
})

const searchInput = document.getElementById("searchKeyword");
searchInput.addEventListener('keydown', async (e) => {
    if (e.key === 'Enter') {
        e.preventDefault();
        console.log("search 버튼 먹힘");
        search();
    }
});

function search(){
    try {
        document.getElementById('SearchBox').innerHTML = "";
        AlbumsTableFrom();
        const keywordVal = document.getElementById('searchKeyword').value;
        console.log(keywordVal);
        getTokenToServer().then(token=>{
            fetchWebApi(token, keywordVal).then(res =>{
                //JSON 데이터가 albums을 포함하고 있을 경우
                if(res.albums){
                    searchAlbumsDataToServer(res.albums).then(album =>{
                        console.log(album);
                        document.getElementById('SearchBox').innerHTML = "";
                            AlbumsTableFrom();
                            for(let i=0; i<res.albums.items.length; i++){
                                let artistsUri = album.artistsUri[i];
                                let artistsName = album.artistsName[i];
                                let albumType = album.albumType[i];
                                let releaseDate = album.releaseDate[i];
                                let name = album.name[i];
                                let id = album.ID[i]
                                console.log(artistsUri);
                                console.log(res.albums.items[i].external_urls.spotify);
                                const url = res.albums.items[i].external_urls.spotify;
                                let embedUrl = url.match(/\/album\/(\w+)/)[0];
                                console.log(embedUrl);
                                AlubmsEmbedFrom(artistsUri, artistsName, albumType, releaseDate, name, embedUrl, id);
                            }
                            const SearchTabAlbums = document.querySelector('.SearchTabAlbums');
                            SearchTabAlbums.addEventListener('click', (e)=>{
                                SearchTabAlbums.focus();
                                document.getElementById('SearchBox').innerHTML = "";
                                AlbumsTableFrom();
                                for(let i=0; i<res.albums.items.length; i++){
                                    let artistsUri = album.artistsUri[i];
                                    let artistsName = album.artistsName[i];
                                    let albumType = album.albumType[i];
                                    let releaseDate = album.releaseDate[i];
                                    let name = album.name[i];
                                    let id = album.ID[i]
                                    console.log(artistsUri);
                                    console.log(res.albums.items[i].external_urls.spotify);
                                    const url = res.albums.items[i].external_urls.spotify;
                                    let embedUrl = url.match(/\/album\/(\w+)/)[0];
                                    console.log(embedUrl);
                                    AlubmsEmbedFrom(artistsUri, artistsName, albumType, releaseDate, name, embedUrl, id);
                                }})
                        })
                }
                //JSON 데이터가 Artists 데이터를 포함하고 있을 경우
                if(res.artists){
                    searchArtistsDataToServer(res.artists).then(artist =>{
                        console.log(artist);
                        const SearchTabArtists = document.querySelector('.SearchTabArtists');
                        SearchTabArtists.addEventListener('click', ()=>{
                            document.getElementById('SearchBox').innerHTML = "";
                            ArtistsTableFrom();
                            for(let i=0; i<res.artists.items.length; i++){
                                let imagesUrl = artist.imagesUrl[i];
                                let name = artist.name[i];
                                let type = artist.type[i];
                                let genres = artist.genres[i];
                                let id = artist.ID[i]
                                console.log(imagesUrl);
                                console.log(res.artists.items[i].external_urls.spotify);
                                const url = res.artists.items[i].external_urls.spotify;
                                let embedUrl = url.match(/\/artist\/(\w+)/)[0];
                                console.log(embedUrl);
                                ArtistsEmbedFrom(imagesUrl, name, type, genres, embedUrl, id);
                            }
                        })
                    })
                }
                //JSON 데이터가 Tracks 데이터를 포함하고 있을 경우
                if(res.tracks){
                    searchTracksDataToServer(res.tracks).then(track =>{
                        console.log(track);
                            const SearchTabTracks = document.querySelector('.SearchTabTracks');
                            SearchTabTracks.addEventListener('click', ()=>{
                                document.getElementById('SearchBox').innerHTML = "";
                                tracksTableFrom();
                                for(let i=0; i<res.tracks.items.length; i++){
                                    let id = track.ID[i]
                                    let albumImagesUrl = track.albumImagesUrl[i];
                                    let artistsName = track.artistsName[i];
                                    let type = track.type[i];
                                    let albumReleaseDate = track.albumReleaseDate[i];
                                    let name = track.name[i];
                                    console.log(res.tracks.items[i].external_urls.spotify);
                                    const url = res.tracks.items[i].external_urls.spotify;
                                    let embedUrl = url.match(/\/track\/(\w+)/)[0];
                                    console.log(embedUrl);
                                    tracksEmbedFrom(id, albumImagesUrl, artistsName, type, albumReleaseDate, name, embedUrl);
                                }
                            })
                    }
                )
            }})
        
        })
    } catch (error) {
        console.log(error);
    }
}

// 플레이어 생성 후 뿌리기 구문
function embedFromPlayer(url){
    document.getElementById('MusicBox').innerHTML += `
    <iframe style="border-radius:12px" 
    src="https://open.spotify.com/embed/${url}?utm_source=generator&autoplay=1" 
    width="100%" height="152" frameBorder="0" allowfullscreen="" allow="autoplay; 
    clipboard-write; encrypted-media; fullscreen; picture-in-picture" loading="lazy" >
    </iframe>`;
}

//Search Albums 데이터 테이블 형식으로 뿌리기 구문 : 상단
function AlbumsTableFrom(){
    document.getElementById('SearchBox').innerHTML += `
    <tr>
    <th></th>
    <th>Artist</th>
    <th>Type</th>
    <th>Release</th>
    <th>Name</th>
    <th></th>
    </tr>
    `;
}

//Search Albums 데이터 테이블 형식으로 뿌리기 구문 : 하단
function AlubmsEmbedFrom(artistsUri, artistsName, albumType, releaseDate, name, embedUrl, id){
    console.log(document.getElementById('MusicBox'));
    document.getElementById('SearchBox').innerHTML += `
    <tr class="PlayMusic">
    <td>
    <input type="hidden" class="id" value="${id}">
    <img src="${artistsUri}" style="height:75px; width:75px; display:inline;">
    </td>
    <td>${artistsName}</td>
    <td>${albumType}</td>
    <td>${releaseDate}</td>
    <td>${name}</td>
    <td><input type="hidden" class="embed" value="${embedUrl}"><i class="bi bi-play-circle-fill"></i></td>
    </tr>`;
}

//Search Artists 데이터 테이블 형식으로 뿌리기 구문 : 상단
function ArtistsTableFrom(){
    document.getElementById('SearchBox').innerHTML += `
    <tr>
    <th></th>
    <th>Name</th>
    <th>Type</th>
    <th>Genres</th>
    <th></th>
    </tr>
    `;
}

//Search Artists 데이터 테이블 형식으로 뿌리기 구문 : 하단
function ArtistsEmbedFrom(imagesUrl, name, type, genres, embedUrl, id){
    console.log(document.getElementById('MusicBox'));
    document.getElementById('SearchBox').innerHTML += `
    <tr class="PlayMusic">
    <td>
    <input type="hidden" class="id" value="${id}">
    <img src="${imagesUrl}" style="height:75px; width:75px; display:inline;">
    </td>
    <td>${name}</td>
    <td>${type}</td>
    <td>${genres}<input type="hidden" class="embed" value="${embedUrl}"></td>
    <td><i class="bi bi-play-circle-fill"></i></td>
    </tr>`;
}

//Search Tracks 데이터 테이블 형식으로 뿌리기 구문 : 상단
function tracksTableFrom(){
    document.getElementById('SearchBox').innerHTML += `
    <tr>
    <th></th>
    <th>Artist</th>
    <th>Type</th>
    <th>Release</th>
    <th>Name</th>
    <th></th>
    </tr>
    `;
}

//Search Tracks 데이터 테이블 형식으로 뿌리기 구문 : 하단
function tracksEmbedFrom(id, albumImagesUrl, artistsName, type, albumReleaseDate, name, embedUrl){
    console.log(document.getElementById('MusicBox'));
    document.getElementById('SearchBox').innerHTML += `
    <tr class="PlayMusic">
    <td>
    <input type="hidden" class="id" value="${id}">
    <img src="${albumImagesUrl}" style="height:75px; width:75px; display:inline;">
    </td>
    <td>${artistsName}</td>
    <td>${type}</td>
    <td>${albumReleaseDate}</td>
    <td>${name}</td>
    <td><input type="hidden" class="embed" value="${embedUrl}"><i class="bi bi-play-circle-fill"></i></td>
    </tr>`;
}

//search 후 테이블 클릭 시 원하는 항목 재생 구문
document.addEventListener('click', (e)=>{
    console.log(e.target);
    let MusicBoxTr = e.target.closest('tr');
    if(MusicBoxTr.classList.contains('PlayMusic')){
        document.getElementById('MusicBox').innerHTML = "";
        let embedUrl = MusicBoxTr.querySelector('.embed').value;
        console.log(embedUrl);
        embedFromPlayer(embedUrl);

        let idVal = MusicBoxTr.querySelector('.id').value;
        console.log(idVal);
    }
})

//쿼리스트링 key, value 형식으로 파싱하기 
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

//페이지 로딩 시 MusicPlayer 노출
document.addEventListener('load', ()=>{

})

function getQueryAndStartMusicPlayer(){

}

