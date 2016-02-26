package com.siims.szb.bespeak.business.impl;

import java.util.List;

import javax.inject.Singleton;

import com.siims.framework.ioc.ServiceContext;
import com.siims.framework.ioc.guice.annotation.AutoBind;
import com.siims.szb.bespeak.business.BespeakBusiness;
import com.siims.szb.bespeak.data.BespeakData;
import com.siims.szb.bespeak.persistence.BespeakPersistence;

@Singleton
@AutoBind(bindClass = BespeakBusiness.class)
public class BespeakBusineeImpl implements BespeakBusiness{

	public String AddBespeakRecord(BespeakData data) {
		// TODO Auto-generated method stub
		return ServiceContext.get(BespeakPersistence.class).AddBespeakRecord(data);
	}

	public List<BespeakData> GetBespeakByDate(String date,String shopid) {
		// TODO Auto-generated method stub
		return ServiceContext.get(BespeakPersistence.class).GetBespeakByDate(date,shopid);
	}

	public List<BespeakData> GetBespeakByPoint(String date, int line, int row) {
		// TODO Auto-generated method stub
		return ServiceContext.get(BespeakPersistence.class).GetBespeakByPoint(date, line, row);
	}

	public BespeakData GetBespeakById(String id) {
		// TODO Auto-generated method stub
		return ServiceContext.get(BespeakPersistence.class).GetBespeakById(id);
	}

	public void ChangeBespeakState(BespeakData data) {
		// TODO Auto-generated method stub
		ServiceContext.get(BespeakPersistence.class).ChangeBespeakState(data);
	}

	public void DeleteBespeak(BespeakData data) {
		// TODO Auto-generated method stub
		ServiceContext.get(BespeakPersistence.class).DeleteBespeak(data);
	}

}
