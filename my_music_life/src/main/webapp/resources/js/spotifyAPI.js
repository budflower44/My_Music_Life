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

//쿼리스트링 key, value 형식으로 파싱하기 
function parseQueryString(parsingData) {
    const params = new URLSearchParams(parsingData);
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

//검색 버튼 클릭 시 검색 실행
document.getElementById('searchBtn').addEventListener('click', async ()=>{
    const keywordVal = document.getElementById('searchKeyword').value;
    console.log(keywordVal);
    search(keywordVal);
})

//검색 창에서 enter 입력 시 검색 실행
const searchInput = document.getElementById("searchKeyword");
searchInput.addEventListener('keydown', async (e) => {
    if (e.key === 'Enter') {
        e.preventDefault();
        const keywordVal = document.getElementById('searchKeyword').value;
        console.log(keywordVal);
        search(keywordVal);
    }
});

//검색 기능 실행 구문
function search(keyword){
    try {
        document.getElementById('searchBox').innerHTML = "";
        document.getElementById('musicBox').innerHTML = "";
        albumsTableFrom();
        getTokenToServer().then(token=>{
            fetchWebApi(token, keyword).then(res =>{
                //JSON 데이터가 albums을 포함하고 있을 경우
                if(res.albums){
                    searchAlbumsDataToServer(res.albums).then(album =>{
                        console.log(album);
                        document.getElementById('searchBox').innerHTML = "";
                            albumsTableFrom();
                            let url;
                            let embedUrl;
                            for(let i=0; i<res.albums.items.length; i++){
                                let artistsUri = album.artistsUri[i];
                                let artistsName = album.artistsName[i];
                                let albumType = album.albumType[i];
                                let releaseDate = album.releaseDate[i];
                                let name = album.name[i];
                                let id = album.ID[i];
                                let uri = album.uri[i];
                                console.log(artistsUri);
                                console.log(res.albums.items[i].external_urls.spotify);
                                url = res.albums.items[i].external_urls.spotify;
                                embedUrl = url.match(/\/album\/(\w+)/)[0];
                                console.log(embedUrl);
                                alubmsEmbedFrom(artistsUri, artistsName, albumType, releaseDate, name, embedUrl, id, uri);
                            }
                            //첫번째 값 가져오기
                                console.log(album);
                                let artistsUri = album.artistsUri[0];
                                let artistsName = album.artistsName[0];
                                let albumType = album.albumType[0];
                                let releaseDate = album.releaseDate[0];
                                let name = album.name[0];
                                let id = album.ID[0];
                                let uri = album.uri[0];
                                url = res.albums.items[0].external_urls.spotify;
                                embedUrl = url.match(/\/album\/(\w+)/)[0];
                            alubmsEmbedFrom(artistsUri, artistsName, albumType, releaseDate, name, embedUrl, id, uri);
                            embedFromPlayer(embedUrl);
                            getContentInfoAlbumToServer(id).then(album =>{
                                console.log(album);
                                let images_url = album.images_url;
                                let name = album.name;
                                let artist_name = album.artist_name;
                                let release_date = album.release_date;
                                let total_tracks = album.total_tracks;
                                let likes = album.likes;
                                detailFromAlbum(images_url, name, artist_name, release_date, total_tracks, likes);
                            })
                            const searchTabAlbums = document.querySelector('.searchTabAlbums');
                            searchTabAlbums.addEventListener('click', (e)=>{
                                searchTabAlbums.focus();
                                document.getElementById('searchBox').innerHTML = "";
                                albumsTableFrom();
                                for(let i=0; i<res.albums.items.length; i++){
                                    let artistsUri = album.artistsUri[i];
                                    let artistsName = album.artistsName[i];
                                    let albumType = album.albumType[i];
                                    let releaseDate = album.releaseDate[i];
                                    let name = album.name[i];
                                    let id = album.ID[i];
                                    let uri = album.uri[i];
                                    console.log(artistsUri);
                                    console.log(res.albums.items[i].external_urls.spotify);
                                    const url = res.albums.items[i].external_urls.spotify;
                                    let embedUrl = url.match(/\/album\/(\w+)/)[0];
                                    console.log(embedUrl);
                                    alubmsEmbedFrom(artistsUri, artistsName, albumType, releaseDate, name, embedUrl, id, uri);
                                }})
	                            
                        })
                }
                //JSON 데이터가 Artists 데이터를 포함하고 있을 경우
                if(res.artists){
                    searchArtistsDataToServer(res.artists).then(artist =>{
                        console.log(artist);
                        const searchTabArtists = document.querySelector('.searchTabArtists');
                        searchTabArtists.addEventListener('click', ()=>{
                            document.getElementById('searchBox').innerHTML = "";
                            artistsTableFrom();
                            for(let i=0; i<res.artists.items.length; i++){
                                let imagesUrl = artist.imagesUrl[i];
                                let name = artist.name[i];
                                let type = artist.type[i];
                                let genres = artist.genres[i];
                                let id = artist.ID[i];
                                let uri = artist.uri[i];
                                console.log(imagesUrl);
                                console.log(res.artists.items[i].external_urls.spotify);
                                const url = res.artists.items[i].external_urls.spotify;
                                let embedUrl = url.match(/\/artist\/(\w+)/)[0];
                                console.log(embedUrl);
                                artistsEmbedFrom(imagesUrl, name, type, genres, embedUrl, id, uri);
                            }
                        })
                    })
                }
                //JSON 데이터가 Tracks 데이터를 포함하고 있을 경우
                if(res.tracks){
                    searchTracksDataToServer(res.tracks).then(track =>{
                        console.log(track);
                            const searchTabTracks = document.querySelector('.searchTabTracks');
                            searchTabTracks.addEventListener('click', ()=>{
                                document.getElementById('searchBox').innerHTML = "";
                                tracksTableFrom();
                                for(let i=0; i<res.tracks.items.length; i++){
                                    let id = track.ID[i];
                                    let albumImagesUrl = track.albumImagesUrl[i];
                                    let artistsName = track.artistsName[i];
                                    let type = track.type[i];
                                    let albumReleaseDate = track.albumReleaseDate[i];
                                    let name = track.name[i];
                                    let uri = track.uri[i];
                                    console.log(res.tracks.items[i].external_urls.spotify);
                                    const url = res.tracks.items[i].external_urls.spotify;
                                    let embedUrl = url.match(/\/track\/(\w+)/)[0];
                                    console.log(embedUrl);
                                    tracksEmbedFrom(id, albumImagesUrl, artistsName, type, albumReleaseDate, name, embedUrl, uri);
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

//Search Albums 데이터 테이블 형식으로 뿌리기 구문 : 상단
function albumsTableFrom(){
    document.getElementById('searchBox').innerHTML += `
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
function alubmsEmbedFrom(artistsUri, artistsName, albumType, releaseDate, name, embedUrl, id, uri){
    console.log(document.getElementById('musicBox'));
    document.getElementById('searchBox').innerHTML += `
    <tr class="playMusic">
    <td>
    <input type="hidden" class="id" value="${id}">
    <img src="${artistsUri}" style="height:75px; width:75px; display:inline;">
    </td>
    <td>${artistsName}<input type="hidden" class="uri" value="${uri}"></td>
    <td>${albumType}</td>
    <td>${releaseDate}</td>
    <td>${name}</td>
    <td><input type="hidden" class="embed" value="${embedUrl}"><i class="bi bi-play-circle-fill"></i></td>
    </tr>`;
}

//Search Artists 데이터 테이블 형식으로 뿌리기 구문 : 상단
function artistsTableFrom(){
    document.getElementById('searchBox').innerHTML += `
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
function artistsEmbedFrom(imagesUrl, name, type, genres, embedUrl, id, uri){
    document.getElementById('searchBox').innerHTML += `
    <tr class="playMusic">
    <td>
    <input type="hidden" class="id" value="${id}">
    <img src="${imagesUrl}" style="height:75px; width:75px; display:inline;">
    </td>
    <td>${name}<input type="hidden" class="uri" value="${uri}"></td>
    <td>${type}</td>
    <td>${genres}<input type="hidden" class="embed" value="${embedUrl}"></td>
    <td><i class="bi bi-play-circle-fill"></i></td>
    </tr>`;
}

//Search Tracks 데이터 테이블 형식으로 뿌리기 구문 : 상단
function tracksTableFrom(){
    document.getElementById('searchBox').innerHTML += `
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
function tracksEmbedFrom(id, albumImagesUrl, artistsName, type, albumReleaseDate, name, embedUrl, uri){
    console.log(document.getElementById('musicBox'));
    document.getElementById('searchBox').innerHTML += `
    <tr class="playMusic">
    <td>
    <input type="hidden" class="id" value="${id}">
    <img src="${albumImagesUrl}" style="height:75px; width:75px; display:inline;">
    </td>
    <td>${artistsName}<input type="hidden" class="uri" value="${uri}"></td>
    <td>${type}</td>
    <td>${albumReleaseDate}</td>
    <td>${name}</td>
    <td><input type="hidden" class="embed" value="${embedUrl}"><i class="bi bi-play-circle-fill"></i></td>
    </tr>`;
}

//search 후 테이블 클릭 시 원하는 항목 재생 구문 & info 띄우기
document.addEventListener('click', (e)=>{
    console.log(e.target);
    let musicBoxTr = e.target.closest('tr');
    if(musicBoxTr.classList.contains('playMusic')){
        document.getElementById('musicBox').innerHTML = "";
        let embedUrl = musicBoxTr.querySelector('.embed').value;
        embedFromPlayer(embedUrl);
        let idVal = musicBoxTr.querySelector('.id').value;
        console.log(idVal);
        let uriVal = musicBoxTr.querySelector('.uri').value;
        console.log(uriVal);
        if(uriVal.match(/:(.*?):/)[1] == "album"){
            let infoWord = uriVal.match(/album/)[0];
            console.log(infoWord);
            getContentInfoAlbumToServer(idVal).then(album =>{
                console.log(album);
                let images_url = album.images_url;
                let name = album.name;
                let artist_name = album.artist_name;
                let release_date = album.release_date;
                let total_tracks = album.total_tracks;
                let likes = album.likes;
                detailFromAlbum(images_url, name, artist_name, release_date, total_tracks, likes);
            })
        }else if(uriVal.match(/:(.*?):/)[1] == "artist"){
            let infoWord = uriVal.match(/artist/)[0];
            console.log(infoWord);
            getContentInfoArtistToServer(idVal).then(artist=>{
                console.log(artist);
                let images_url = artist.images_url;
                let name = artist.name;
                let genres = artist.genres;
                let followers_total = artist.followers_total;
                let likes = artist.likes;
                detailFromArtist(images_url, name, genres, followers_total, likes);
            })
        }else if(uriVal.match(/:(.*?):/)[1] == "track"){
            let infoWord = uriVal.match(/track/)[0];
            console.log(infoWord);
            getContentInfoTrackToServer(idVal).then(track => {
                console.log(track);
                let album_images_url = track.album_images_url;
                let track_name = track.track_name;
                let artists_name = track.artists_name;
                let album_release_date = track.album_release_date;
                let duration_ms = track.duration_ms;
                let likes = track.likes;
                detailFromTrack(album_images_url, track_name, artists_name, album_release_date, duration_ms, likes);
            })
        }
    }
})

// 플레이어 생성 후 뿌리기 구문
function embedFromPlayer(url){
    document.getElementById('musicBox').innerHTML += `
    <iframe style="border-radius:12px" 
    src="https://open.spotify.com/embed/${url}?utm_source=generator&autoplay=1" 
    width="100%" height="152" frameBorder="0" allowfullscreen="" allow="autoplay; 
    clipboard-write; encrypted-media; fullscreen; picture-in-picture" loading="lazy" >
    </iframe>`;
}

//Album -> DB 데이터 가져오기
async function getContentInfoAlbumToServer(idVal){
    try {
        const url = "/mml/info/album";
        const config = {
            method : 'POST',
            headers : { 
                'content-type' : 'application/json; charset=utf-8'
            },
            body : idVal
        };
        const resp = await fetch (url, config);
        const result = await resp.json();
        console.log(result);
        return result;
    } catch (error) {
        console.log(error);
    }
}

//Album -> DetailInfo 뿌리기 구문
function detailFromAlbum(images_url, name, artist_name, release_date, total_tracks, likes){
    let div = document.querySelector('.contentInfoDiv');
    div.innerHTML = "";
    div.innerHTML += `
    <div class="contentImageDiv">
        <img alt="" src="${images_url}" style="height:200px; width:200px;">
    </div>
    <div class="contentInfoDetailDiv">
        <p>Album Name : ${name}</p>
        <p>Artist Name : ${artist_name}</p>
        <p>Release Date : ${release_date}</p>
        <p>Total Tracks : ${total_tracks}</p>			
    </div>
    `;
    // div.innerHTML += `
    // <div class="contentImageDiv">
    //     <img alt="" src="${images_url}" style="height:200px; width:200px;">
    // </div>
    // <div class="contentInfoDetailDiv">
    //     <p>Album Name : ${name}</p>
    //     <p>Artist Name : ${artist_name}</p>
    //     <p>Release Date : ${release_date}</p>
    //     <p>Total Tracks : ${total_tracks}</p>
    //     <p>likes : ${likes}</p>
    //     <button class="likesBtn">♡</button>					
    // </div>
    // `;
}

//Artist -> DB 데이터 가져오기
async function getContentInfoArtistToServer(idVal){
    try {
        const url = "/mml/info/artist";
        const config = {
            method : 'POST',
            headers : { 
                'content-type' : 'application/json; charset=utf-8'
            },
            body : idVal
        };
        const resp = await fetch (url, config);
        const result = await resp.json();
        console.log(result);
        return result;
    } catch (error) {
        console.log(error);
    }
}

//Artist -> DetailInfo 뿌리기 구문
function detailFromArtist(images_url, name, genres, followers_total, likes){
    let div = document.querySelector('.contentInfoDiv');
    div.innerHTML = "";
    div.innerHTML += `
    <div class="contentImageDiv">
        <img alt="" src="${images_url}" style="height:200px; width:200px;">
    </div>
    <div class="contentInfoDetailDiv">
        <p>Album Name : ${name}</p>
        <p>Artist Name : ${genres}</p>
        <p>Release Date : ${followers_total}</p>			
    </div>
    `;
    // div.innerHTML += `
    // <div class="contentImageDiv">
    //     <img alt="" src="${images_url}" style="height:200px; width:200px;">
    // </div>
    // <div class="contentInfoDetailDiv">
    //     <p>Album Name : ${name}</p>
    //     <p>Artist Name : ${genres}</p>
    //     <p>Release Date : ${followers_total}</p>
    //     <p>likes : ${likes}</p>
    //     <button class="likesBtn">♡</button>				
    // </div>
    // `;
}

//Track -> DB 데이터 가져오기
async function getContentInfoTrackToServer(idVal){
    try {
        const url = "/mml/info/track";
        const config = {
            method : 'POST',
            headers : { 
                'content-type' : 'application/json; charset=utf-8'
            },
            body : idVal
        };
        const resp = await fetch (url, config);
        const result = await resp.json();
        console.log(result);
        return result;
    } catch (error) {
        console.log(error);
    }
}

//Track -> DetailInfo 뿌리기 구문
function detailFromTrack(album_images_url, track_name, artists_name, album_release_date, duration_ms, likes){
    let div = document.querySelector('.contentInfoDiv');
    div.innerHTML = "";
    div.innerHTML += `
    <div class="contentImageDiv">
        <img alt="" src="${album_images_url}" style="height:200px; width:200px;">
    </div>
    <div class="contentInfoDetailDiv">
        <p>Album Name : ${track_name}</p>
        <p>Artist Name : ${artists_name}</p>
        <p>Release Date : ${album_release_date}</p>
        <input type="hidden" value=${duration_ms} />			
    </div>
    `;
    // div.innerHTML += `
    // <div class="contentImageDiv">
    //     <img alt="" src="${album_images_url}" style="height:200px; width:200px;">
    // </div>
    // <div class="contentInfoDetailDiv">
    //     <p>Album Name : ${track_name}</p>
    //     <p>Artist Name : ${artists_name}</p>
    //     <p>Release Date : ${album_release_date}</p>
    //     <input type="hidden" value=${duration_ms} />
    //     <p>likes : ${likes}</p>	
    //     <button class="likesBtn" id="likesBtnDisabled">♡</button>				
    // </div>
    // `;

    // if(document.getElementById('likesBtnDisabled')){
    //     let likesBtnDisabled = document.getElementById('likesBtnDisabled');
    //     likesBtnDisabled.addEventListener('click', ()=>{
    //         likesBtnDisabled.innerHTML = ``;
    //         likesBtnDisabled.innerHTML = `<button class="likesBtn" id="likesBtnAbled">♥︎</button>`;
            
    //         if(document.getElementById('likesBtnAbled')){
    //             let likesBtnAbled = document.getElementById('likesBtnAbled');
    //             likesBtnAbled.addEventListener('click', ()=>{
    //                 likesBtnAbled.innerHTML = ``;
    //                 likesBtnAbled.innerHTML = `<button class="likesBtn" id="likesBtnAbled">♡</button>`;
    //             })
    //         }
    //     })
    // }
}

async function likesUp(){

}
