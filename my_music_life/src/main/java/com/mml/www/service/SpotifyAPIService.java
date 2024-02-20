package com.mml.www.service;

import com.mml.www.domain.AlbumsVO;
import com.mml.www.domain.ArtistsVO;
import com.mml.www.domain.TracksVO;

public interface SpotifyAPIService {

	void insertDBAlbums(AlbumsVO albumsVO);

	void insertDBArtists(ArtistsVO artistsVO);

	void insertDBTracks(TracksVO tracksVO);

	
}
