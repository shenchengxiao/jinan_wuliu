package com.manager.pojo.citys;

import java.util.ArrayList;
import java.util.List;

public class Province {
	public String id;
	public String text;
	public List<City> children  = new ArrayList<>();
}
