package com.monfood.boot.del.dao;

import java.sql.Timestamp;
import java.util.List;

import com.monfood.boot.del.DelBean;

public interface DelDAO {
	DelBean add(DelBean delBean);

//	DelBean update(DelBean delBean);
//	DelBean updateWithoutPic(DelBean delBean);

//Orderrecord
//	String getCost(Timestamp startDate, Timestamp endDate, Integer delID);
//	String getRideTimes(Timestamp startDate, Timestamp endDate, Integer delID);
	List<String> getRecord(Timestamp startDate, Timestamp endDate, Integer delID);
	
//	DelBean login(String delTel, String delPassword);

//	DelBean findByaccount(String delAccount);
//	DelBean findByTel(String delTel);
	DelBean findByDelID(Integer delID);
//	DelBean findByDelNamePassword(String delName, String delTel);
//ForAdmin	
	List<DelBean> getAll();

	boolean delete(Integer delID);
}
