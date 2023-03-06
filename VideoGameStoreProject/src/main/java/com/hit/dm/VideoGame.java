package com.hit.dm;

import java.io.Serializable;

public class VideoGame implements Serializable {
	private String name;
	private	 Short YearOfRelease;
	private Long id;
	private GamePlatformEnum Platform;
	
	
	public VideoGame(String name, Short yearOfRelease, Long id, GamePlatformEnum platform) {
		super();
		this.name = name;
		this.YearOfRelease = yearOfRelease;
		this.id = id;
		this.Platform = platform;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Short getYearOfRelease() {
		return YearOfRelease;
	}
	public void setYearOfRelease(Short yearOfRelease) {
		YearOfRelease = yearOfRelease;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public GamePlatformEnum getPlatform() {
		return Platform;
	}
	public void setPlatform(GamePlatformEnum platform) {
		Platform = platform;
	}
}
