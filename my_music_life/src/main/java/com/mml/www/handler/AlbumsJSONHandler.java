package com.mml.www.handler;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mml.www.domain.AlbumsVO;
import com.mml.www.service.SpotifyAPIService;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class AlbumsJSONHandler {
	
	@Autowired
	private SpotifyAPIService ssv; 
	
	public ResponseEntity<MultiValueMap<String, String>> albumsJSONHandler(String albumsData){
		
		//결과값 반환할 map 생성
		MultiValueMap<String, String> albumsresultMap = new LinkedMultiValueMap<String, String>();

		// Jackson ObjectMapper 생성
        ObjectMapper objectMapper = new ObjectMapper();
        
        try {
        	
        	// JSON 문자열을 Java 객체로 파싱
        	Map<String, Object> albumsDataJsonMap = objectMapper.readValue(albumsData, new TypeReference<Map<String, Object>>() {});
                    
           List<Map<String, Object>> alVOList = (List<Map<String, Object>>) albumsDataJsonMap.get("items");
           
           for (Map<String, Object> alVOMap : alVOList) {
               // 각 오브젝트에서 필요한 값 가져오기
               String id = (String) alVOMap.get("id");
               String albumType = (String) alVOMap.get("album_type");
               
               //오브젝트 안의 오브젝트 값 가져오기
               Object externalUrlsObj = (Object) alVOMap.get("external_urls");
               Map<String, String> externalUrlsMap = (Map<String, String>) externalUrlsObj;
               String externalUrls = externalUrlsMap.get("spotify");
               
               // 배열 안의 오브젝트 가져오기
               List<Object> artistsObjList = new ArrayList<Object> ((List<Object>) alVOMap.get("artists"));
               String artistsExternalUrls = null;
               String artistsHref = null;
               String artistsId = null;
               String artistsName = null;
               String artistsType = null;
               String artistsUri = null;
               for(Object artistObj : artistsObjList) {
            	   // 배열 안의 오브젝트 안의 오브젝트 가져오기
            	   Map<String, Object> artistMap = (Map<String, Object>) artistObj;
            	    // "external_urls" 오브젝트 가져오기
            	    Map<String, String> artistsExternalUrlsMap = (Map<String, String>) artistMap.get("external_urls");
            	    // "spotify" 값 가져오기
            	    artistsExternalUrls = artistsExternalUrlsMap.get("spotify");
            	    artistsHref = (String) artistMap.get("href");
            	    artistsId = (String) artistMap.get("id");
            	    artistsName = (String) artistMap.get("name");
            	    artistsType = (String) artistMap.get("type");
            	    artistsUri = (String) artistMap.get("uri");
               }
               
               
               //오브젝트 안의 배열 안의 오브젝트 값 가져오기
               List<Object> imagesObjList = new ArrayList<Object> ((List<Object>) alVOMap.get("images"));
               int imagesHeight = 0;
               int imagesWidth = 0;
               String imagesUrl = null;
               for(Object imagesObj : imagesObjList) {
            	   Map<String, Object> imagesMap = (Map<String, Object>) imagesObj;
            	    // height와 width 값 가져오기
            	    imagesHeight = (int) imagesMap.get("height");
            	    imagesWidth = (int) imagesMap.get("width");
            	    // height와 width가 640이면 데이터 출력
            	    if (imagesHeight == 640 && imagesWidth == 640) {
            	        imagesUrl = (String) imagesMap.get("url");
            	    }
               }
               String href = (String) alVOMap.get("href");
               String name = (String) alVOMap.get("name");
               String releaseDate = (String) alVOMap.get("release_date");
               String releaseDatePrecision = (String) alVOMap.get("release_date_precision");
               int totalTracks = (int) alVOMap.get("total_tracks");
               String type = (String) alVOMap.get("type");
               String uri = (String) alVOMap.get("uri");

               
               // 가져온 데이터 출력 확인용
               System.out.println("ID: " + id);
               System.out.println("external_urls: " + externalUrls);
               System.out.println("album_type: " + albumType);
               System.out.println("artistsExternalUrls: " + artistsExternalUrls);
               System.out.println("artistsHref: " + artistsHref);
               System.out.println("artistsId: " + artistsId);
               System.out.println("artistsName: " + artistsName);
               System.out.println("artistsType: " + artistsType);
               System.out.println("artistsUri: " + imagesUrl);
               System.out.println("imagesUrl: " + imagesUrl);
   	           System.out.println("imagesHeight: " + imagesHeight);
   	           System.out.println("imagesWidth: " + imagesWidth);
               System.out.println("href: " + href);
               System.out.println("name: " + name);
               System.out.println("releaseDate: " + releaseDate);
               System.out.println("releaseDatePrecision: " + releaseDatePrecision);
               System.out.println("total_tracks: " + totalTracks);
               System.out.println("type: " + type);
               System.out.println("uri: " + uri);
               
               AlbumsVO albumsVO = new AlbumsVO(id, externalUrls, albumType, artistsExternalUrls, 
            		   artistsHref, artistsId, artistsName, artistsType, artistsUri, href, imagesUrl, 
            		   imagesHeight, imagesWidth, name, releaseDate, releaseDatePrecision, totalTracks, 
            		   type, uri);
               
               System.out.println(albumsVO);
              
               albumsresultMap.add("ID", id);
               albumsresultMap.add("externalUrls", externalUrls);
               albumsresultMap.add("albumType", albumType);
               albumsresultMap.add("artistsExternalUrls", artistsExternalUrls);
               albumsresultMap.add("artistsHref", artistsHref);
               albumsresultMap.add("artistsId", artistsId);
               albumsresultMap.add("artistsName", artistsName);
               albumsresultMap.add("artistsType", artistsType);
               albumsresultMap.add("artistsUri", imagesUrl);
               albumsresultMap.add("imagesHeight", Integer.toString(imagesHeight));
               albumsresultMap.add("imagesWidth", Integer.toString(imagesWidth));
               albumsresultMap.add("href", href);
               albumsresultMap.add("name", name);
               albumsresultMap.add("releaseDate", releaseDate);
               albumsresultMap.add("releaseDatePrecision", releaseDatePrecision);
               albumsresultMap.add("totalTracks", Integer.toString(totalTracks));
               albumsresultMap.add("type", type);
               albumsresultMap.add("uri", uri);
               
               if(ssv != null) {
            	   ssv.insertDBAlbums(albumsVO);            	   
               }else {
            	   log.info("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@ >> sevice로 못넘어가는 현상");
               }
               
           }
           }catch (Exception e) {
			// TODO: handle exception
        	   e.printStackTrace();
        	   return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
		}
        return ResponseEntity.ok(albumsresultMap);
	}
	
}
