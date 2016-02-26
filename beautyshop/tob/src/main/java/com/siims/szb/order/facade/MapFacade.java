package com.siims.szb.order.facade;

import java.util.HashMap;
import java.util.Map;

import com.siims.szb.order.enums.ResultType;

public class MapFacade extends ResultFacade {

	private Map<String, String> map = new HashMap<String, String>();

	public MapFacade() {
	}
	
	public MapFacade(ResultType result, String detail) {
		super(result, detail);
	}

	public MapFacade(ResultType result, String detail ,Map<String, String> map) {
		super(result, detail);
		this.map = map;
	}

	public Map<String, String> getMap() {
		return map;
	}

	public void setMap(Map<String, String> map) {
		this.map = map;
	}
	
	

}
