package com.mml.www.repository;

import com.mml.www.domain.AlbumsVO;

public interface SpotifyDAO {
	
	String compareToDBDataAlbums(String id);

	void insertDBAlbums(AlbumsVO albumsVO);
	
}
