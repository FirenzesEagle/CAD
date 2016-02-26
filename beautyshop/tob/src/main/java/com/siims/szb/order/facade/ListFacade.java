package com.siims.szb.order.facade;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.siims.szb.order.enums.ResultType;


public class ListFacade extends ResultFacade{

	private List<Map<String, String>> list = new ArrayList<Map<String,String>>();
	
	public ListFacade() {
		// TODO Auto-generated constructor stub
	}

	public ListFacade(ResultType result, String detail) {
		super(result, detail);
	}
	
	public ListFacade(ResultType result, String detail ,List<Map<String, String>> list) {
		super(result, detail);
		this.list = list;
	}

	public List<Map<String, String>> getList() {
		return list;
	}

	public void setList(List<Map<String, String>> list) {
		this.list = list;
	}
	
}
