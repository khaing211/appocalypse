package com.github.appocalypse.pandemic;

public enum Cities implements City {
	ATLANTA(Constant.CityName.ATLANTA, RegionColor.BLUE),
	WASHINGTON(Constant.CityName.WASHINGTON, RegionColor.BLUE),
	CHICAGO(Constant.CityName.CHICAGO, RegionColor.BLUE),
	MONTREAL(Constant.CityName.MONTREAL, RegionColor.BLUE),
	SAN_FRANCISCO(Constant.CityName.SAN_FRANCISCO, RegionColor.BLUE),
	NEW_YORK(Constant.CityName.NEW_YORK, RegionColor.BLUE),
	LONDON(Constant.CityName.LONDON, RegionColor.BLUE),
	ESSEN(Constant.CityName.ESSEN, RegionColor.BLUE),
	PARIS(Constant.CityName.PARIS, RegionColor.BLUE),
	MADRID(Constant.CityName.MADRID, RegionColor.BLUE),
	MILAN(Constant.CityName.MILAN, RegionColor.BLUE),
	ST_PETERSBURG(Constant.CityName.ST_PETERSBURG, RegionColor.BLUE),
	MEXICO_CITY(Constant.CityName.MEXICO_CITY, RegionColor.YELLOW),
	LOS_ANGELES(Constant.CityName.LOS_ANGELES, RegionColor.YELLOW),
	MIAMI(Constant.CityName.MIAMI, RegionColor.YELLOW),
	BOGOTA(Constant.CityName.BOGOTA, RegionColor.YELLOW),
	LIMA(Constant.CityName.LIMA, RegionColor.YELLOW),
	SANTIAGO(Constant.CityName.SANTIAGO, RegionColor.YELLOW),
	BUENOS_AIRES(Constant.CityName.BUENOS_AIRES, RegionColor.YELLOW),
	SAO_PAULO(Constant.CityName.SAO_PAULO, RegionColor.YELLOW),
	LAGOS(Constant.CityName.LAGOS, RegionColor.YELLOW),
	KINSHASA(Constant.CityName.KINSHASA, RegionColor.YELLOW),
	KHARTOUM(Constant.CityName.KHARTOUM, RegionColor.YELLOW),
	JOHANNESBURG(Constant.CityName.JOHANNESBURG, RegionColor.YELLOW),
	ALGIERS(Constant.CityName.ALGIERS, RegionColor.BLACK),
	ISTANBUL(Constant.CityName.ISTANBUL, RegionColor.BLACK),
	MOSCOW(Constant.CityName.MOSCOW, RegionColor.BLACK),
	TEHRAN(Constant.CityName.TEHRAN, RegionColor.BLACK),
	BAGHDAD(Constant.CityName.BAGHDAD, RegionColor.BLACK),
	RIYADH(Constant.CityName.RIYADH, RegionColor.BLACK),
	CAIRO(Constant.CityName.CAIRO, RegionColor.BLACK),
	KARACHI(Constant.CityName.KARACHI, RegionColor.BLACK),
	DELHI(Constant.CityName.DELHI, RegionColor.BLACK),
	MUMBAI(Constant.CityName.MUMBAI, RegionColor.BLACK),
	KOLKATA(Constant.CityName.KOLKATA, RegionColor.BLACK),
	CHENNAI(Constant.CityName.CHENNAI, RegionColor.BLACK),
	BANGKOK(Constant.CityName.BANGKOK, RegionColor.RED),
	JAKARTA(Constant.CityName.JAKARTA, RegionColor.RED),
	HO_CHI_MINH_CITY(Constant.CityName.HO_CHI_MINH_CITY, RegionColor.RED),
	SYDNEY(Constant.CityName.SYDNEY, RegionColor.RED),
	MANILA(Constant.CityName.MANILA, RegionColor.RED),
	HONG_KONG(Constant.CityName.HONG_KONG, RegionColor.RED),
	TAIPEI(Constant.CityName.TAIPEI, RegionColor.RED),
	SHANGHAI(Constant.CityName.SHANGHAI, RegionColor.RED),
	OSAKA(Constant.CityName.OSAKA, RegionColor.RED),
	TOKYO(Constant.CityName.TOKYO, RegionColor.RED),
	SEOUL(Constant.CityName.SEOUL, RegionColor.RED),
	BEIJING(Constant.CityName.BEIJING, RegionColor.RED),
	;
	
	final private String name;
	final private RegionColor regionColor;
	
	private Cities(String name, RegionColor regionColor) {
		this.name = name;
		this.regionColor = regionColor;
	}
	
	@Override
	public String getName() {
		return name;
	}

	@Override
	public RegionColor getRegionColor() {
		return regionColor;
	}
	
	@Override
	public String toString() {
		return "City [name=" + name + ", regionColor=" + regionColor + "]";
	}
}
