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
public class TracksVO {
	
	
	private String albumArtistsExternalUrls;
	private String albumArtistsHref;
	private String albumArtistsId;
	private String albumArtistsName;
	private String albumArtistsType;
	private String albumArtistsUri;
	
	private String albumExternalUrls;
	private String albumHref;
	private String albumId;
	private String albumImagesUrl;
	private int albumImagesHeight;
	private int albumImagesWidth;
	private String albumName;
	private String albumReleaseDate;
	private String albumReleaseDatePrecision;
	private int albumTotalTracks;
	private String albumType;
	private String albumUri;
	
	private String artistsExternalUrls;
	private String artistsHref;
	private String artistsId;
	private String artistsName;
	private String artistsType;
	private String artistsUri;
	
    private String id;
    private String externalUrl;
    private long discNumber;
    private long durationMs;
    private Boolean explicit;
    private String externalIds;
    private String href;
    private Boolean isLocal;
    private String name;
    private long popularity;
    private String previewUrl;
    private int trackNumber;
    private String type;
    private String uri;
    private long likes;
    private long playCount;
    private int rankMonth;
    private int rankWeek;
    private int rankDay;

	public TracksVO(String albumArtistsExternalUrls, String albumArtistsHref, String albumArtistsId,
			String albumArtistsName, String albumArtistsType, String albumArtistsUri, String albumExternalUrls,
			String albumHref, String albumId, String albumImagesUrl, int albumImagesHeight, int albumImagesWidth,
			String albumName, String albumReleaseDate, String albumReleaseDatePrecision, int albumTotalTracks,
			String albumType, String albumUri, String artistsExternalUrls, String artistsHref, String artistsId,
			String artistsName, String artistsType, String artistsUri, String id, String externalUrl, long discNumber,
			long durationMs, Boolean explicit, String externalIds, String href, Boolean isLocal, String name,
			long popularity, String previewUrl, int trackNumber, String type, String uri) {
	    this.albumArtistsExternalUrls = albumArtistsExternalUrls != null ? albumArtistsExternalUrls : "";
	    this.albumArtistsHref = albumArtistsHref != null ? albumArtistsHref : "";
	    this.albumArtistsId = albumArtistsId != null ? albumArtistsId : "";
	    this.albumArtistsName = albumArtistsName != null ? albumArtistsName : "";
	    this.albumArtistsType = albumArtistsType != null ? albumArtistsType : "";
	    this.albumArtistsUri = albumArtistsUri != null ? albumArtistsUri : "";
	    this.albumExternalUrls = albumExternalUrls != null ? albumExternalUrls : "";
	    this.albumHref = albumHref != null ? albumHref : "";
	    this.albumId = albumId != null ? albumId : "";
	    this.albumImagesUrl = albumImagesUrl != null ? albumImagesUrl : "";
	    this.albumImagesHeight = albumImagesHeight;
	    this.albumImagesWidth = albumImagesWidth;
	    this.albumName = albumName != null ? albumName : "";
	    this.albumReleaseDate = albumReleaseDate != null ? albumReleaseDate : "";
	    this.albumReleaseDatePrecision = albumReleaseDatePrecision != null ? albumReleaseDatePrecision : "";
	    this.albumTotalTracks = albumTotalTracks;
	    this.albumType = albumType != null ? albumType : "";
	    this.albumUri = albumUri != null ? albumUri : "";
	    this.artistsExternalUrls = artistsExternalUrls != null ? artistsExternalUrls : "";
	    this.artistsHref = artistsHref != null ? artistsHref : "";
	    this.artistsId = artistsId != null ? artistsId : "";
	    this.artistsName = artistsName != null ? artistsName : "";
	    this.artistsType = artistsType != null ? artistsType : "";
	    this.artistsUri = artistsUri != null ? artistsUri : "";
	    this.id = id != null ? id : "";
	    this.externalUrl = externalUrl != null ? externalUrl : "";
	    this.discNumber = discNumber;
	    this.durationMs = durationMs;
	    this.explicit = explicit;
	    this.externalIds = externalIds != null ? externalIds : "";
	    this.href = href != null ? href : "";
	    this.isLocal = isLocal;
	    this.name = name != null ? name : "";
	    this.popularity = popularity;
	    this.previewUrl = previewUrl != null ? previewUrl : "";
	    this.trackNumber = trackNumber;
	    this.type = type != null ? type : "";
	    this.uri = uri != null ? uri : "";
	}
    
    
}
