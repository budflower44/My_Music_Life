package com.mml.www.repository;

import com.mml.www.domain.AlbumsVO;
import com.mml.www.domain.ArtistsVO;
import com.mml.www.domain.TracksVO;

public interface SpotifyDAO {
	
	String compareToDBDataAlbums(String id);

	void insertDBAlbums(AlbumsVO albumsVO);

	String compareToDBDataArtists(String id);

	void insertDBArtists(ArtistsVO artistsVO);

	String compareToDBDataTracks(String id);

	void insertDBTracks(TracksVO tracksVO);
	
}
