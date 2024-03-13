package com.mml.www.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@ToString
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class MemberDTO {
	
	private MemberVO mvo;
	private AlbumsVO albumsVO;
	private ArtistsVO artistVO;
	private TracksVO tracksVo;
	
	public MemberDTO(MemberVO mvo, AlbumsVO albumsVO) {
		this.mvo = mvo;
		this.albumsVO = albumsVO;
	}

	public MemberDTO(MemberVO mvo, ArtistsVO artistVO) {
		this.mvo = mvo;
		this.artistVO = artistVO;
	}

	public MemberDTO(MemberVO mvo, TracksVO tracksVo) {
		this.mvo = mvo;
		this.tracksVo = tracksVo;
	}
	
	
	
	
}
