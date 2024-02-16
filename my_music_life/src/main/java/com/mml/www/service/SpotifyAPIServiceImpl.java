package com.mml.www.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mml.www.domain.AlbumsVO;
import com.mml.www.repository.SpotifyDAO;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Service
public class SpotifyAPIServiceImpl implements SpotifyAPIService {
	
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
	
}
