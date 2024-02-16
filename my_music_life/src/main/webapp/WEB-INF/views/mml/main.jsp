<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.10.5/font/bootstrap-icons.css">
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-4bw+/aepP/YC94hEpVNVgiZdgIC5+VKNBQNGCHeKRQN+PtmoHDEXuppvnDJzQIu9" crossorigin="anonymous">
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.8/dist/umd/popper.min.js" integrity="sha384-I7E8VVD/ismYTF4hNIPjVp/Zjvgyol6VFvRkX/vR+Vc4jQkC+hVqc2pM8ODewa9r" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/js/bootstrap.min.js" integrity="sha384-Rx+T1VzGupg4BHQYs2gCW9It+akI2MM/mndMCy36UVfodzcJcF0GGLxZIzObiEfa" crossorigin="anonymous"></script>
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
.HeaderDiv{
	height: 60px;
}
.MidDiv{
	height: calc(100vh - 224px);
	min-height: calc(100vh - 224px);
	display:flex;
}
.FooterDiv{
	height: 154px;
	padding: 0;
	margin: 0;
	position:sticky;
	top: calc(100vh - 224px);
}
.SearchLineDiv{

}
.UserDetailDiv{
	width: 250px;
	min-width: 250px;
}
.ContentsDiv{
	width: 100%;
	height:calc(100vw-550px);
	display: flex;
	justify-content: space-between;
	overflow: scroll;
}
.ContentDiv{
	width: 60%;
	height: calc(100vw-550px);
	overflow: scroll;
	align-items: flex-start;
}
.ContentDetailsDiv{
	width: 40%;
	min-width: 350px;
	height: 100%
}
.MusicBox{
	padding: 0;
	margin: 5px;
    
}
.searchDiv{
	background-color: black;
	width: 650px;
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
.nav-link{
	color: gold;
}
.nav-link:hover {
	color: white;
}
.navbar{
	background-color: black;
}
.table{
	max-width: 650px;
}
</style>
<body>
<div class="parentsDiv">
	<div class="HeaderDiv">
		<div class="SearchLineDiv">
				<div class="navDiv">
					<nav class="navbar border-bottom border-body" data-bs-theme="dark">
						<ul class="nav nav-underline justify-content-end">
							<li class="nav-item"><a class="nav-link" aria-current="page" href="#">Active</a></li>
							<li class="nav-item"><a class="nav-link" href="#">Link</a></li>
							<li class="nav-item"><a class="nav-link" href="#">Link</a></li>
						</ul>
						<form class="d-flex" role="search">
        					<input class="form-control me-2 " id="searchKeyword" type="search" placeholder="Search" aria-label="Search">
        					<button class="btn" type="button" id="searchBtn">Search</button>
      					</form>
					</nav>
				</div>
		</div>	
	</div>
	<div class="MidDiv">
		<div class="UserDetailDiv">UserDetailDiv</div>
		<div class="ContentsDiv">
			<div class="contentDiv">
				<div class="searchDiv">
				<table id="SearchBox" class="table table-dark table-hover">
<!-- 					<tr>
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
					</tr> -->
				</table>
				</div>
			</div>
			<div class="ContentDetailsDiv">ContentDetailsDiv</div>		
		</div>
	</div>
	<div class="FooterDiv">
		<div class="MusicBox" id="MusicBox"></div>
	</div>
	<div></div>
</div>

<script src="/resources/js/spotifyAPI.js"></script>

</body>
</html>