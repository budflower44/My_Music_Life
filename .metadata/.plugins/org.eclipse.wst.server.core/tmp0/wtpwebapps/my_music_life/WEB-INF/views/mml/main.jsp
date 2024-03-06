<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<% request.setCharacterEncoding("UTF-8"); %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.10.5/font/bootstrap-icons.css">
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-4bw+/aepP/YC94hEpVNVgiZdgIC5+VKNBQNGCHeKRQN+PtmoHDEXuppvnDJzQIu9" crossorigin="anonymous">
<style>
body{
	background-color : black;
	color: white;
	padding: 0;
	margin: 0;
}
.parentsDiv{
	height: 100vh;
	width: 100vw;
	display: flex;
	flex-direction: column;
}
.headerDiv{
	height: 60px;
}
.midDiv{
	height: calc(100vh - 224px);
	min-height: calc(100vh - 224px);
	display:flex;
}
.footerDiv{
	height: 154px;
	padding: 0;
	margin: 0;
	position:sticky;
	top: calc(100vh - 224px);
}
.searchLineDiv{

}
.userDetailDiv{
	width: 250px;
	min-width: 250px;
	display: flex;
	flex-direction: column;
	align-items: center;
	text-align: center;
	margin-top : 45px;
}
.userImgDiv{
	width: 80%;
	height: 50%;
}
.userImgDiv Img{
	border-radius: 80%;	
}
.userDetailInfoDiv{
	width: 80%;
	height: 50%;
	display: flex;
	align-items: center;
	flex-direction: column;
	padding-top: 10px;
}
.contentsDiv{
	width: 100%;
	height:calc(100vw-550px);
	display: flex;
	justify-content: space-between;
	overflow: scroll;
}
.contentDiv{
	width: 100%;
	height: calc(100vw-550px);
	overflow: scroll;
	align-items: flex-start;
}
.contentDetailsDiv{
	position: sticky;
	width: 60%;
	min-width: 350px;
	height: 100%;
	display: flex;
	flex-direction: column;
	align-items: center;
}
.contentInfoDiv{
	width: 100%;
	display: flex;
	align-items: center;
	flex-direction: column;
}
.contentImageDiv{
	background-color: black;
	width: 200px;
	height: 200px;
	margin-top: 10px;
}
.contentInfoDetailDiv{
	width: 300px;
	margin-top: 5px;
	text-align: center;
}
.contentInfoDetailDiv p{
	margin: 1px;
}
.contentCommentDiv{
	width: 100%;
	height: calc(100%-350px);
	display: flex;
	align-items: center;
	flex-direction: column;
	overflow: scroll;
}
.commentInputDiv{
	width: 90%;
}
.commentPrintDiv{
	width: 90%;
	display: flex;
	align-items: center;
}
.musicBox{
	padding: 0;
	margin: 5px;
    
}
.searchDiv{
	background-color: black;
	height: 75px;
	margin: 5px;
}
.searchDiv img{
	margin-left: 10px;
}
.searchDiv span{
	text-align: center;
}
.playIcon {
	display: inline;
}

.navDiv{
	position:sticky;
	padding-left: 10px;
	padding-right: 10px;
}
.nav {
    --bs-nav-link-color: white;
    --bs-nav-link-hover-color: white;
}
.navbar{
	background-color: black;
}
.nav-link{
	color: gold;
}
.nav-link:hover {
	color: white;
}
.nav-link:focus {
	color: white;
}
table th:nth-child(3){
	width: 50px;
}
Button{
	border: none;
	background-color: transparent;
	color: gold;
}
.mb-4{
	display: flex;
	flex-direction: row;
}
.mr-3{
	margin: 8px;
}
.media-body{
	width: 350px;
}
a{
	color: gold;
	text-decoration: none;
}
a:hover {
	color: white;
}
</style>
<body>
<div class="parentsDiv">
	<div class="headerDiv">
		<div class="searchLineDiv">
				<div class="navDiv">
					<nav class="navbar border-bottom border-body" data-bs-theme="dark">
						<ul class="nav nav-underline justify-content-end">
							<li class="nav-item"><a class="nav-link" aria-current="page" href="#">Active</a></li>
							<li class="nav-item"><a class="nav-link" href="#">Link</a></li>
							<li class="nav-item"><a class="nav-link" href="#">Link</a></li>
						</ul>
						<form class="d-flex" role="search">
        					<input class="form-control me-2 " id="searchKeyword" type="search" placeholder="Search (최대 20개 출력)" aria-label="Search">
        					<button class="btn" type="button" id="searchBtn">Search</button>
      					</form>
					</nav>
				</div>
		</div>	
	</div>
	<div class="midDiv">
		<div class="userDetailDiv">
			<div class="userImgDiv">
				<img alt="" src="/resources/images/스쿠나.gif" style="height:100%; width:100%;">
			</div>
			<div class="userDetailInfoDiv">
				<a href="#"><h3>Nick_name ${nick_name }</h3></a>
				<p>ID : ${ses.id}</p>
				<p>최근 접속 : ${release_date}</p>
				<button id="logout" class="danger">Logout</button>
			</div>
		</div>
		<div class="contentsDiv">
			<div class="contentDiv">
				<div class="searchDiv">
						<ul class="nav nav-underline justify-content-center SearchTab">
							<li class="nav-item"><a class="nav-link searchTabAlbums" href="#">Albums</a></li>
							<li class="nav-item"><a class="nav-link searchTabArtists" href="#">Artists</a></li>
							<li class="nav-item"><a class="nav-link searchTabTracks" href="#">Tracks</a></li>
						</ul>
						<table id="searchBox" class="table table-dark table-hover">
  					<tr>
						<th></th>
						<th>Artist</th>
						<th>Type</th>
						<th>Release Date</th>
						<th>Name</th>
						<th></th>
					</tr>
					<tr>
						<td>
						<img alt="" src="https://i.scdn.co/image/ab67616d0000b273a3b39c1651a617bb09800fd8" style="height:75px; width:75px; display:inline;">
						</td>
						<td>찰리푸스</td>
						<td>Type</td>
						<td>Release Date</td>
						<td>Name</td>
						<td><i class="bi bi-play-circle-fill"></i></td>
					</tr> 
				</table>
				</div>
			</div>
		</div>
			<div class="contentDetailsDiv">
				<div class="contentInfoDiv">
 					<div class="contentImageDiv">
						<img alt="" src="${images_url}">
					</div>
					<div class="contentInfoDetailDiv">
						<p>Album Name : ${name}</p>
						<p>Artist Name : ${artist_name}</p>
						<p>Release Date : ${release_date}</p>
						<p>Total Tracks : ${total_tracks}</p>
						<p>likes : ${likes}</p>				
					</div>
				</div>
				<div class="contentCommentDiv">
					<div class="commentInputDiv">
					<br>
					<div class="input-group">
						<span id="cmtWriter" class="input-group-text">Writer</span>
						<input type="text" id="cmtText" class="form-control" aria-label="Recipient's username" aria-describedby="button-addon2" placeholder="Add Comment...">
						<button class="btn btn-outline-secondary" type="button" id="cmtPostBtn">Add</button>
					</div>
					<br>
					<div class="commentPrintDiv">
						<div id="comment-area">
							<div class="media mb-4">
								<img class="d-flex mr-3 rounded-circle" style="height:30px; width:30px; display:inline;"
								alt="image" src="/resources/images/4490035.png">
								<div class="media-body">
									<h6 class="mt-0">Commenter Name</h6>
									첫번째 댓글.
									<button id="commentModBtn">M</button>
									<button id="commentDelBtn">X</button>
								</div>
							</div>
							<div class="media mb-4">
								<img class="d-flex mr-3 rounded-circle" style="height:30px; width:30px; display:inline;"
								alt="image" src="/resources/images/no_thumbnail.png">
								<div class="media-body">
									<h6 class="mt-0">Commenter Name</h6>
									두번째 댓글. 줄 넘어가면 어떻게 되나요? 보는 주우우우우우우우우우ㅜ우우우우우ㅜ우우우우우우웅.ㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇ..,,,,,,,,,,,,,
									<button id="commentModBtn">M</button>
									<button id="commentDelBtn">X</button>
								</div>
							</div>
							<div class="media mb-4">
								<img class="d-flex mr-3 rounded-circle" style="height:30px; width:30px; display:inline;"
								alt="image" src="/resources/images/4490035.png">
								<div class="media-body">
									<h6 class="mt-0">Commenter Name</h6>
									세번째 댓글. 스크롤 왜안되ㅣㅣ우우우웅.ㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇ..,,,,,,,,,,,,,
									<button id="commentModBtn">M</button>
									<button id="commentDelBtn">X</button>
								</div>
							</div>
						</div>		
					</div>
					<br>
					</div>
				</div>
			</div>		
	</div>
	<div class="footerDiv">
		<div class="musicBox" id="musicBox"></div>
	</div>
	<div></div>
</div>
<script src="/resources/js/spotifyAPI.js"></script>
<script type="text/javascript">
	const keyword = `<c:out value="${keyword}" />`;
	console.log(keyword);
	search(keyword);
</script>
<script src="/resources/js/comment.js"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/js/bootstrap.min.js" integrity="sha384-Rx+T1VzGupg4BHQYs2gCW9It+akI2MM/mndMCy36UVfodzcJcF0GGLxZIzObiEfa" crossorigin="anonymous"></script>

</body>
</html>