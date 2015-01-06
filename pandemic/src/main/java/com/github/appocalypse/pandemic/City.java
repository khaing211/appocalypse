package com.github.appocalypse.pandemic;

public class City {
	final private String name;
	final private RegionColor regionColor;
	
	public City(String name, RegionColor regionColor) {
		this.name = name;
		this.regionColor = regionColor;
	}
	
	public City(String name) {
		this(name, null);
	}
	
	public String getName() {
		return name;
	}

	public RegionColor getRegionColor() {
		return regionColor;
	}

	@Override
	public String toString() {
		return "City [name=" + name + ", regionColor=" + regionColor + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result
				+ ((regionColor == null) ? 0 : regionColor.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		City other = (City) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (regionColor != other.regionColor)
			return false;
		return true;
	}
}
