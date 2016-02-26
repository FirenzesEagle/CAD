package com.siims.szb.bespeak.service.impl;

import java.util.Date;
import java.util.List;

import javax.inject.Singleton;

import com.siims.framework.ioc.ServiceContext;
import com.siims.framework.ioc.guice.annotation.AutoBind;
import com.siims.framework.transaction.TransactionalContext;
import com.siims.szb.bespeak.business.BespeakBusiness;
import com.siims.szb.bespeak.data.BespeakData;
import com.siims.szb.bespeak.service.BespeakService;

@Singleton
@AutoBind(bindClass = BespeakService.class)
@TransactionalContext
public class BespeakServiceImpl implements BespeakService{

	public String AddBespeakRecord(BespeakData data) {
		// TODO Auto-generated method stub
		data.setCreatetime(new Date());
		data.setIsdelete(1);
		return ServiceContext.get(BespeakBusiness.class).AddBespeakRecord(data);
	}

	public List<BespeakData> GetBespeakByDate(String date,String shopid) {
		// TODO Auto-generated method stub
		return ServiceContext.get(BespeakBusiness.class).GetBespeakByDate(date,shopid);
	}

	public List<BespeakData> GetBespeakByPoint(String date, int line, int row) {
		// TODO Auto-generated method stub
		return ServiceContext.get(BespeakBusiness.class).GetBespeakByPoint(date, line, row);
	}

	public BespeakData GetBespeakById(String id) {
		// TODO Auto-generated method stub
		return ServiceContext.get(BespeakBusiness.class).GetBespeakById(id);
	}

	public void ChangeBespeakState(BespeakData data) {
		// TODO Auto-generated method stub
		ServiceContext.get(BespeakBusiness.class).ChangeBespeakState(data);
	}

	public void DeleteBespeak(BespeakData data) {
		// TODO Auto-generated method stub
		ServiceContext.get(BespeakBusiness.class).DeleteBespeak(data);
	}

}
