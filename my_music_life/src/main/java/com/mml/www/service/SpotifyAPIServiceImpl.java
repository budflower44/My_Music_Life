package com.mml.www.service;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mml.www.domain.AlbumsVO;
import com.mml.www.domain.ArtistsVO;
import com.mml.www.domain.TracksVO;
import com.mml.www.repository.SpotifyDAO;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Service
public class SpotifyAPIServiceImpl implements SpotifyAPIService {
	
	private final Logger log = LoggerFactory.getLogger(SpotifyAPIServiceImpl.class);
	
	@Autowired
	private SpotifyDAO sdao;

	@Override
	public void insertDBAlbums(AlbumsVO albumsVO) {
		log.info("serviceImpl in");
		// TODO Auto-generated method stub
		try {
			 if (sdao != null) {
		            String isExistingAlbums = sdao.compareToDBDataAlbums(albumsVO.getId());
		            System.out.println("비교 완료 : "+isExistingAlbums);
		            if(isExistingAlbums == null) {
		                sdao.insertDBAlbums(albumsVO);
		            } else {
		                log.info("DB 넣기 실패");
		            }
		        } else {
		            log.info("sdao 객체가 null입니다.");
		        }
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

	@Override
	public void insertDBArtists(ArtistsVO artistsVO) {
		log.info("serviceImpl in");
		// TODO Auto-generated method stub
		try {
			 if (sdao != null) {
		            String isExistingArtists = sdao.compareToDBDataArtists(artistsVO.getId());
		            System.out.println("비교 완료 : "+isExistingArtists);
		            if(isExistingArtists == null) {
		                sdao.insertDBArtists(artistsVO);
		            } else if(isExistingArtists != null){
//		            	sdao.updateDBArtists(artistsVO.getId());
		            	log.info("DB 업데이트 진행");
		            }else {		            	
		            	log.info("DB 넣기 실패");
		            }
		        } else {
		            log.info("sdao 객체가 null입니다.");
		        }
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

	@Override
	public void insertDBTracks(TracksVO tracksVO) {
		log.info("serviceImpl in");
		// TODO Auto-generated method stub
		try {
			 if (sdao != null) {
		            String isExistingTracks = sdao.compareToDBDataTracks(tracksVO.getId());
		            System.out.println("비교 완료 : "+isExistingTracks);
		            if(isExistingTracks == null) {
		                sdao.insertDBTracks(tracksVO);
		            } else if(isExistingTracks != null){
//		            	sdao.updateDBArtists(artistsVO.getId());
		            	log.info("DB 업데이트 진행");
		            }else {		            	
		            	log.info("DB 넣기 실패");
		            }
		        } else {
		            log.info("sdao 객체가 null입니다.");
		        }
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
	}
	
	@Override
	public Map<String, String> getDetailInfoAlbum(String id) {
		log.info("serviceImpl in");
		Map<String, String> infoMap = new HashMap<>();
		try {
			AlbumsVO albumVO = sdao.getDetailAlbum(id);
			log.info("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@ albumsVO >> "+albumVO);
			System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@ albumsVO >> "+albumVO);
			
			infoMap.put("images_url", albumVO.getImagesUrl());
			infoMap.put("artist_name", albumVO.getArtistsName());
			infoMap.put("name", albumVO.getName());
			infoMap.put("release_date", albumVO.getReleaseDate());
			infoMap.put("total_tracks", Integer.toString(albumVO.getTotalTracks()));
			infoMap.put("likes", Long.toString(albumVO.getLikes()));
			
		} catch (Exception e) {
			e.printStackTrace();
			log.info("info 잘못 가져옴");
		}
		
		return infoMap;
	}
	
	@Override
	public Map<String, String> getDetailInfoArtist(String id) {
		Map<String, String> infoMap = new HashMap<>();
		try {
			ArtistsVO artistVO = sdao.getDetailArtist(id);
			System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@ artistVO >> "+artistVO);
			
			infoMap.put("images_url", artistVO.getImagesUrl());
			infoMap.put("name", artistVO.getName());
			infoMap.put("genres", artistVO.getGenres());
			infoMap.put("followers_total", Long.toString(artistVO.getFollowersTotal()));
			infoMap.put("likes", Long.toString(artistVO.getLikes()));
			
		} catch (Exception e) {
			e.printStackTrace();
			log.info("info 잘못 가져옴");
		}
		
		return infoMap;
	}
	
	@Override
	public Map<String, String> getDetailInfoTrack(String id) {
		Map<String, String> infoMap = new HashMap<>();
		try {
			TracksVO trackVO = sdao.getDetailTrack(id);
			System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@ artistVO >> "+trackVO);
			
			infoMap.put("album_images_url", trackVO.getAlbumImagesUrl());
			infoMap.put("track_name", trackVO.getName());
			infoMap.put("artists_name", trackVO.getArtistsName());
			infoMap.put("album_name", trackVO.getAlbumName());
			infoMap.put("album_release_date", trackVO.getAlbumReleaseDate());
			infoMap.put("duration_ms", Long.toString(trackVO.getDurationMs()));
			infoMap.put("likes", Long.toString(trackVO.getLikes()));
			
		} catch (Exception e) {
			e.printStackTrace();
			log.info("info 잘못 가져옴");
		}
		
		return infoMap;
	}
	
}
