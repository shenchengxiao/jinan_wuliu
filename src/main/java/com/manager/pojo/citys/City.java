package com.manager.pojo.citys;

import java.util.ArrayList;
import java.util.List;

public class City {
	public String id;
	public String text;
	public List<Region> children = new ArrayList<>();
}
