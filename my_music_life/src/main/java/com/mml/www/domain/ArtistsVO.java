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
public class ArtistsVO {

    private String id;
    private String externalUrls;
    private String followersHref;
    private long followersTotal;
    private String genres;
    private String href;
    private String imagesUrl;
    private int imagesHeight;
    private int imagesWidth;
    private String name;
    private long popularity;
    private String type;
    private String uri;
    private long likes;
    private long playCount;
    private int rankMonth;
    private int rankWeek;
    private int rankDay;
    
	public ArtistsVO(String id, String externalUrls, String followersHref, long followersTotal, String genres,
			String href, String imagesUrl, int imagesHeight, int imagesWidth, String name, long popularity, String type,
			String uri) {
		this.id = id != null ? id : "";
		this.externalUrls = externalUrls != null ? externalUrls : "";
		this.followersHref = followersHref != null ? followersHref : "";
		this.followersTotal = followersTotal;
		this.genres = genres != null ? genres : "";
		this.href = href != null ? href : "";
		this.imagesUrl = imagesUrl != null ? imagesUrl : "";
		this.imagesHeight = imagesHeight < 640 ? imagesHeight : 640;
		this.imagesWidth = imagesWidth < 640 ? imagesWidth : 640;
		this.name = name != null ? name : "";
		this.popularity = popularity;
		this.type = type != null ? type : "";
		this.uri = uri != null ? uri : "";
	}
}
