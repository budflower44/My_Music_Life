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
import com.mml.www.domain.ArtistsVO;
import com.mml.www.service.SpotifyAPIService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class ArtistsJSONHandler {
	
	@Autowired
	private SpotifyAPIService ssv; 
	
	public ResponseEntity<MultiValueMap<String, String>> artistsJSONHandler(String artistsData){
		
		//결과값 반환 MAP 생성
		MultiValueMap<String, String> artistsMap = new LinkedMultiValueMap<>();
		
		//JSON 변환 라이브러리 Jackson Object Mapper 생성
		ObjectMapper objectMapper = new ObjectMapper();
		
		try {
			//JSON 문자열을 Java 객체로 파싱
			Map<String, Object> artistsDataJSONMap = objectMapper.readValue(artistsData, new TypeReference<Map<String, Object>> () {});
			
			List<Map<String, Object>> arVOList = (List<Map<String, Object>>) artistsDataJSONMap.get("items");
			
			for(Map<String, Object> arVOMap : arVOList) {
				//각 객체별로 필요한 값 가져오기
				String id = (String) arVOMap.get("id");
				
				//오브젝트 안의 오브젝트 값 가져오기
	               Object externalUrlsObj = (Object) arVOMap.get("external_urls");
	               Map<String, String> externalUrlsMap = (Map<String, String>) externalUrlsObj;
	               String externalUrls = externalUrlsMap.get("spotify");
	           
	             //오브젝트 안의 오브젝트 값 가져오기
	               Object followersObj = (Object) arVOMap.get("followers");
	               System.out.println("2@@@@@@@@@@@@@@@@@@@@@@@@@@ >> "+followersObj);
	               Map<String, Object> followersMap = (Map<String, Object>) followersObj;
	               String followersHref = (String)followersMap.get("href");
	               int followersTotalInt = (int)followersMap.get("total");
	               long followersTotal = (long)followersTotalInt;
	               
	            // 배열 안의 array 값 String 한줄로 가져오기
	               List<String> genresObjList = new ArrayList<String> ((List<String>) arVOMap.get("genres"));
	               String genres = String.join(",", genresObjList);
	            
	            String href = (String) arVOMap.get("href");
	            
	            //오브젝트 안의 배열 안의 오브젝트 값 가져오기
	               List<Object> imagesObjList = new ArrayList<Object> ((List<Object>) arVOMap.get("images"));
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
	               
	               String name = (String) arVOMap.get("name");
	               int popularityInt = (int) arVOMap.get("popularity");
	               long popularity = (long) popularityInt;
	               String type = (String) arVOMap.get("type");
	               String uri = (String) arVOMap.get("uri");
	               
	               // 가져온 데이터 출력 확인용
	               System.out.println("ID: " + id);
	               System.out.println("externalUrls: " + externalUrls);
	               System.out.println("followersHref: " + followersHref);
	               System.out.println("followersTotal: " + followersTotal);
	               System.out.println("genres: " + genres);
	               System.out.println("href: " + href);
	               System.out.println("imagesUrl: " + imagesUrl);
	               System.out.println("imagesHeight: " + imagesHeight);
	               System.out.println("imagesWidth: " + imagesWidth);
	               System.out.println("name: " + name);
	   	           System.out.println("popularity: " + popularity);
	   	           System.out.println("type: " + type);
	               System.out.println("uri: " + uri);
	               
	               ArtistsVO artistsVO = new ArtistsVO(id, externalUrls, followersHref, followersTotal, genres, href, 
	            		   imagesUrl, imagesHeight, imagesWidth, name, popularity, type, uri);
	               
	               System.out.println(artistsVO);
	               
	               artistsMap.add("ID", id);
	               artistsMap.add("externalUrls", externalUrls);
	               artistsMap.add("followersHref", followersHref);
	               artistsMap.add("followersTotal", Integer.toString((int)followersTotal));
	               artistsMap.add("genres", genres);
	               artistsMap.add("href", href);
	               artistsMap.add("imagesUrl", imagesUrl);
	               artistsMap.add("imagesHeight", Integer.toString(imagesHeight));
	               artistsMap.add("imagesWidth", Integer.toString(imagesWidth));
	               artistsMap.add("name", name);
	               artistsMap.add("popularity", Integer.toString((int)popularity));
	               artistsMap.add("type", type);
	               artistsMap.add("uri", uri);

	               if(ssv != null) {
	            	   ssv.insertDBArtists(artistsVO);            	   
	               }else {
	            	   log.info("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@ >> sevice로 못넘어가는 현상");
	               }
	               
	               

			}
			
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			log.info("artist JSON 파싱 실패");
     	   return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);

		}
		
		return ResponseEntity.ok(artistsMap);
	}
	
	
}
