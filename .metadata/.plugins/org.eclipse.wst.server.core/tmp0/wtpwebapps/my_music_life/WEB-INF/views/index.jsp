<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<html>
<head>
	<title>Home</title>
</head>
<style>
body{
	background-color : black;
	color: white;
}

.mainLink{
	text-decoration: none;
	color: white;
}

.loader3{
  position: relative;
  width: 150px;
  height: 20px;
  top: 45%;
  top: -webkit-calc(50% - 10px);
  top: calc(50% - 10px);
  left: 25%;
  left: -webkit-calc(50% - 75px);
  left: calc(50% - 75px);
}

.loader3:after{
  content: "LOADING ...";
  color: white;
  font-family:  Lato,"Helvetica Neue" ;
  font-weight: 200;
  font-size: 16px;
  position: absolute;
  width: 100%;
  height: 20px;
  line-height: 20px;
  left: 0;
  top: 0;
  background-color: black;
  z-index: 1;
}

.loader3:before{
  content: "";
  position: absolute;
  background-color: white;
  top: -5px;
  left: 0px;
  height: 30px;
  width: 0px;
  z-index: 0;
  opacity: 1;
  -webkit-transform-origin:  100% 0%;
      transform-origin:  100% 0% ;
  -webkit-animation: loader3 10s ease-in-out infinite;
      animation: loader3 10s ease-in-out infinite;
}



@-webkit-keyframes loader3{
    0%{width: 0px;}
    70%{width: 100%; opacity: 1;}
    90%{opacity: 0; width: 100%;}
    100%{opacity: 0;width: 0px;}
}

@keyframes loader3{
    0%{width: 0px;}
    70%{width: 100%; opacity: 1;}
    90%{opacity: 0; width: 100%;}
    100%{opacity: 0;width: 0px;}
}
</style>
<body>
<h1>
	Welcome My Music Life! 
</h1>

<P>  Search your Music anyway </P>

<input type="text" id="searchKeyword">
<button type="button" id="searchBtn">Start!</button>

   <div class="box">
      <div class="loader3"></div>
    </div>
    
<script src="/resources/js/strartPage_SpotifyAPI.js"></script>

</body>
</html>

