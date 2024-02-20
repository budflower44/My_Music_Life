package com.mml.www.controller;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.mml.www.config.AuthSpotifyAPIConfig;
import com.mml.www.handler.AlbumsJSONHandler;
import com.mml.www.handler.ArtistsJSONHandler;
import com.mml.www.handler.TracksJSONHandler;
import com.mml.www.service.SpotifyAPIService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import se.michaelthelin.spotify.SpotifyApi;

@Slf4j
@RequiredArgsConstructor
@RequestMapping("/mml/**")
@Controller
public class SpotifyAPIController {
	
	private SpotifyApi spotifyApi;
	
	private String clientId = "7018fc47d1cb49bba23cee1e5c702d60";

	private String clientSecret = "7c1006ea22e04ce0888c4bb616a68537";

	private String redirectUri = "http://localhost:8089/callback";
	
	public void initialize() {
		// SpotifyApi 객체를 생성하고 클라이언트 ID, 리디렉션 URI를 설정합니다.
		spotifyApi = new SpotifyApi.Builder()
				.setClientId(clientId)
				.setClientSecret(clientSecret)
				.setRedirectUri(URI.create(redirectUri))
				.build();
	}
	
	@Autowired
	private SpotifyAPIService ssv;
	
	@Autowired
	private AlbumsJSONHandler albumsJSON;
	
	@Autowired
	private ArtistsJSONHandler artistsJSON;
	
	@Autowired
	private TracksJSONHandler tracksJSON;

	@GetMapping("/main")
	public void mainPage() {}
	
	@PostMapping("/token")
	public ResponseEntity<String> getAuthSpotifyApiToken(Model m) {
		log.info("token 발급 진행 중");
	    String accessToken = AuthSpotifyAPIConfig.accessToken().toString();
	    log.info(accessToken);
	    return new ResponseEntity<String>(accessToken, HttpStatus.OK);
	}
	
	@PostMapping("/search/albums")
	public ResponseEntity<MultiValueMap<String,String>> searchAlbumsData(@RequestBody String albumsData) {
		System.out.print("@@@@@@@@@@@@@@@@ albumsData >> "+albumsData);
		
		return albumsJSON.albumsJSONHandler(albumsData);
	} 
	
	@PostMapping("/search/artists")
	public ResponseEntity<MultiValueMap<String,String>> searchArtistData(@RequestBody String artistsData) {
		System.out.print("@@@@@@@@@@@@@@@@ artistsData >> "+artistsData);
		
		return artistsJSON.artistsJSONHandler(artistsData);
	} 
	
	@PostMapping("/search/tracks")
	public ResponseEntity<MultiValueMap<String,String>> searchTracksData(@RequestBody String tracksData) {
		System.out.print("@@@@@@@@@@@@@@@@ tracksData >> "+tracksData);
		
		return tracksJSON.tracksJSONHandler(tracksData);
	} 
	
}
