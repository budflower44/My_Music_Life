package com.mml.www.handler;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mml.www.domain.TracksVO;
import com.mml.www.service.SpotifyAPIService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class TracksJSONHandler {
	
	@Autowired
	private SpotifyAPIService ssv; 
	
	@Autowired
	private AlbumsJSONHandler albumsJSONHandler;
	
	@Autowired
	private ArtistsJSONHandler artistsJSONHandler;
	
	public ResponseEntity<MultiValueMap<String, String>> tracksJSONHandler(String TracksData){
		
		//결과값 반환할 map 생성
		MultiValueMap<String, String> tracksMap = new LinkedMultiValueMap<String, String>();

		// Jackson ObjectMapper 생성
		ObjectMapper objectMapper = new ObjectMapper();
		
		try {
			//JSON 문자열을 Java 객체로 파싱
			Map<String, Object> TracksDataJSONMap = objectMapper.readValue(TracksData, new TypeReference<Map<String, Object>> () {});
			
			List<Map<String, Object>> traVOList = (List<Map<String, Object>>) TracksDataJSONMap.get("items");
			
			for(Map<String, Object> trVOMap : traVOList) {
				//각 객체별로 필요한 값 가져오기
				log.info("@@@@@@@@@@@@@@@@@@@@@@@@ tracks album >>"+trVOMap.get("album"));
				
				String albumArtistsExternalUrls = "";
				String albumArtistsHref = "";
				String albumArtistsId = "";
				String albumArtistsName = "";
				String albumArtistsType = "";
				String albumArtistsUri = "";

				String albumExternalUrls = "";
				String albumHref = "";
				String albumId = "";
				String albumImagesUrl = "";
				int albumImagesHeight = 0;
				int albumImagesWidth = 0;
				String albumName = "";
				String albumReleaseDate = "";
				String albumReleaseDatePrecision = "";
				int albumTotalTracks = 0;
				String albumType = "";
				String albumUri = "";

				String artistsExternalUrls = "";
				String artistsHref = "";
				String artistsId = "";
				String artistsName = "";
				String artistsType = "";
				String artistsUri = "";
				
				//album 오브젝트 안의 값 가져오기
				
	            Object albumObj = (Object) trVOMap.get("album");
	            Map<String, Object> albumMap = (Map<String, Object>) albumObj;
	            log.info("!!!!!!!!!!!!!!!!!!!!!!! albumMap >> "+albumMap);
	            
	            String album_type = (String) albumMap.get("album_type");
	            log.info("!!!!!!!!!!!!!!!!!!!!!!! album_type >> "+album_type);
	            
	            
	            Object albumObjObj = (Object) albumMap.get("artists");
	            log.info("1!!!!!!!!!!!!!!!!!!!!11 albumObjObj >> "+albumObjObj);
	            
	            List<Map<String, Object>> albumObjList = (List<Map<String, Object>>) albumObjObj;
	            for (Map<String, Object> album : albumObjList) {
	            	Object externalUrlsObj = (Object) album.get("external_urls");
	                Map<String, String> externalUrlsMap = (Map<String, String>) externalUrlsObj;
	                albumArtistsExternalUrls = externalUrlsMap.get("spotify");
	                
	                albumArtistsHref = (String) album.get("href");
	            	albumArtistsId = (String) album.get("id");
	            	albumArtistsName = (String) album.get("name");
	            	albumArtistsType = (String) album.get("type");
	            	albumArtistsUri = (String) album.get("uri");
	            	
		            System.out.println("albumArtistsExternalUrls: " + albumArtistsExternalUrls);
		            System.out.println("albumArtistsHref: " + albumArtistsHref);
		            System.out.println("albumArtistsId: " + albumArtistsId);
		            System.out.println("albumArtistsName: " + albumArtistsName);
		            System.out.println("albumArtistsType: " + albumArtistsType);
		            System.out.println("albumArtistsUri: " + albumArtistsUri);
	            
	            }
	            
	            Object albumsexternalUrlsObj = (Object) albumMap.get("external_urls");
	            Map<String, String> albumsexternalUrlsMap = (Map<String, String>) albumsexternalUrlsObj;
	            albumExternalUrls = albumsexternalUrlsMap.get("spotify");
	            
	            albumHref = (String) albumMap.get("href");
	            albumId = (String) albumMap.get("id");
	            
                System.out.println("albumExternalUrls: " + albumExternalUrls);
                System.out.println("albumHref: " + albumHref);
                System.out.println("albumId: " + albumId);
                
	            //오브젝트 안의 배열 안의 오브젝트 값 가져오기
	            List<Object> imagesObjList = new ArrayList<Object> ((List<Object>) albumMap.get("images"));
                for(Object imagesObj : imagesObjList) {
                	Map<String, Object> imagesMap = (Map<String, Object>) imagesObj;
	            	// height와 width 값 가져오기
                	albumImagesHeight = (int) imagesMap.get("height");
	            	albumImagesWidth = (int) imagesMap.get("width");
	            	// height와 width가 640이면 데이터 출력
	            	if (albumImagesHeight == 640 && albumImagesWidth == 640) {
	            		albumImagesUrl = (String) imagesMap.get("url");
	            		
	                    System.out.println("albumImagesUrl: " + albumImagesUrl);
	                    System.out.println("albumImagesHeight: " + albumImagesHeight);
	                    System.out.println("albumsImagesWidth: " + albumImagesWidth);
	               }
	            }
	            
                albumName = (String) albumMap.get("name");
                albumReleaseDate = (String) albumMap.get("release_date");
                albumReleaseDatePrecision = (String) albumMap.get("release_date_precision");
                albumTotalTracks = (int) albumMap.get("total_tracks");
                albumType = (String) albumMap.get("type");
                albumUri = (String) albumMap.get("uri");
                
                System.out.println("albumExternalUrls: " + albumExternalUrls);
                System.out.println("albumHref: " + albumHref);
                System.out.println("albumId: " + albumId);
                System.out.println("albumName: " + albumName);
                System.out.println("albumReleaseDate: " + albumReleaseDate);
                System.out.println("albumReleaseDatePrecision: " + albumReleaseDatePrecision);
                System.out.println("albumTotalTracks: " + albumTotalTracks);
                System.out.println("albumType: " + albumType);
                System.out.println("albumUri: " + albumUri);				
				
				//artists 오브젝트 안의 값 가져오기
				log.info("@@@@@@@@@@@@@@@@@@@@@@@@ tracks artists >>"+trVOMap.get("artists"));
				
				List<Map<String, Object>> artistsList = new ArrayList<>();
				Object artistsObj = (Object) trVOMap.get("artists");
				artistsList = (List<Map<String, Object>>) artistsObj;
				log.info("!!!!!!!!!!!!!!!!!!!!!!! artistsList >> "+artistsList);
				
				for(Map<String, Object> artist : artistsList) {
					Object artistsExternalUrlsObj = (Object) artist.get("external_urls");
		            Map<String, String> artistsExternalUrlsMap = (Map<String, String>) artistsExternalUrlsObj;
		            artistsExternalUrls = artistsExternalUrlsMap.get("spotify");
	                
		            artistsHref = (String) artist.get("href");
		            artistsId = (String) artist.get("id");
		            artistsName = (String) artist.get("name");
		            artistsType = (String) artist.get("type");
		            artistsUri = (String) artist.get("uri");
		            
		            System.out.println("artistsExternalUrls: " + artistsExternalUrls);
		            System.out.println("artistsHref: " + artistsHref);
		            System.out.println("artistsid: " + artistsId);
		            System.out.println("artistsName: " + artistsName);
		            System.out.println("artistsType: " + artistsType);
		            System.out.println("artistsUri: " + artistsUri);
				}
				
				//그 외 객체 가져오기
				int discNumber = (int) trVOMap.get("disc_number");
				int durationMs = (int) trVOMap.get("duration_ms");
				boolean explicit = (boolean) trVOMap.get("explicit");
				
				//오브젝트 내 오브젝트 객체 가져오기
				Object externalIdsObj = (Object) trVOMap.get("external_ids");
	            Map<String, String> externalIdsMap = (Map<String, String>) externalIdsObj;
	            String externalIds = externalIdsMap.get("isrc");
				
	            Object externalUrlsObj = (Object) trVOMap.get("external_urls");
	            Map<String, String> externalUrlsMap = (Map<String, String>) externalUrlsObj;
	            String externalUrls = externalUrlsMap.get("spotify");  
				
	            String href = (String) trVOMap.get("href");
	            String id = (String) trVOMap.get("id");
	            boolean isLocal = (boolean) trVOMap.get("is_local");
	            String name = (String) trVOMap.get("name");
	            int popularity = (int) trVOMap.get("popularity");
	            String previewUrl = (String) trVOMap.get("preview_url");
	            int trackNumber = (int) trVOMap.get("track_number");
	            String type = (String) trVOMap.get("type");
	            String uri = (String) trVOMap.get("uri");
	            
	            System.out.println("discNumber: " + discNumber);
	            System.out.println("durationMs: " + durationMs);
	            System.out.println("explicit: " + explicit);
	            System.out.println("href: " + href);
	            System.out.println("id: " + id);
	            System.out.println("isLocal: " + isLocal);
	            System.out.println("name: " + name);
	            System.out.println("popularity: " + popularity);
	            System.out.println("previewUrl: " + previewUrl);
	            System.out.println("trackNumber: " + trackNumber);
	            System.out.println("type: " + type);
	            System.out.println("uri: " + uri);
				
	            TracksVO tracksVO = new TracksVO(albumArtistsExternalUrls, albumArtistsHref, albumArtistsId, 
	            		albumArtistsName, albumArtistsType, albumArtistsUri, albumExternalUrls, albumHref, 
	            		albumId, albumImagesUrl, albumImagesHeight, albumImagesWidth, albumName, 
	            		albumReleaseDate, albumReleaseDatePrecision, albumTotalTracks, albumType, albumUri, 
	            		artistsExternalUrls, artistsHref, artistsId, artistsName, artistsType, artistsUri, 
	            		id, externalUrls, discNumber, durationMs, explicit, externalIds, href, isLocal, name, 
	            		popularity, previewUrl, trackNumber, type, uri);
	            
	            log.info("@@@@@@@@@@@@@@@@@@@@@@2 완성된 tracksVO >> "+tracksVO);
	            
	            tracksMap.add("ID", id);
	            tracksMap.add("albumArtistsExternalUrls", albumArtistsExternalUrls);
	            tracksMap.add("albumArtistsHref", albumArtistsHref);
	            tracksMap.add("albumArtistsId", albumArtistsId);
	            tracksMap.add("albumArtistsName", albumArtistsName);
	            tracksMap.add("albumArtistsType", albumArtistsType);
	            tracksMap.add("albumArtistsUri", albumArtistsUri);
	            tracksMap.add("albumExternalUrls", albumExternalUrls);
	            tracksMap.add("albumHref", albumHref);
	            tracksMap.add("albumId", albumId);
	            tracksMap.add("albumImagesUrl", albumImagesUrl);
	            tracksMap.add("albumImagesHeight", Integer.toString(albumImagesHeight));
	            tracksMap.add("albumImagesWidth", Integer.toString(albumImagesWidth));
	            tracksMap.add("albumName", albumName);
	            tracksMap.add("albumReleaseDate", albumReleaseDate);
	            tracksMap.add("albumReleaseDatePrecision", albumReleaseDatePrecision);
	            tracksMap.add("albumTotalTracks", Integer.toString(albumTotalTracks));
	            tracksMap.add("albumType", albumType);
	            tracksMap.add("albumUri", albumUri);
	            tracksMap.add("artistsExternalUrls", artistsExternalUrls);
	            tracksMap.add("artistsHref", artistsHref);
	            tracksMap.add("artistsId", artistsId);
	            tracksMap.add("artistsName", artistsName);
	            tracksMap.add("artistsType", artistsType);
	            tracksMap.add("artistsUri", artistsUri);
	            tracksMap.add("externalUrls", externalUrls);
	            tracksMap.add("discNumber", Integer.toString(discNumber));
	            tracksMap.add("durationMs", Integer.toString(durationMs));
	            tracksMap.add("explicit", Boolean.toString(explicit));
	            tracksMap.add("externalIds", externalIds);
	            tracksMap.add("href", href);
	            tracksMap.add("isLocal", Boolean.toString(isLocal));
	            tracksMap.add("name", name);
	            tracksMap.add("popularity", Integer.toString(popularity));
	            tracksMap.add("previewUrl", previewUrl);
	            tracksMap.add("trackNumber", Integer.toString(trackNumber));
	            tracksMap.add("type", type);
	            tracksMap.add("uri", uri);
	            
	            if(ssv != null) {
	            	   ssv.insertDBTracks(tracksVO);            	   
	               }else {
	            	   log.info("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@ >> sevice로 못넘어가는 현상");
	            }
	            
	            
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		return ResponseEntity.ok(tracksMap);
	}
	
	
	
}