package com.monfood.boot.del.dao.impl;

import java.io.Serializable;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import com.monfood.boot.del.DelBean;
import com.monfood.boot.del.dao.DelDAO;


public class DelDAOImpl implements DelDAO{
	
	private SessionFactory sessionFactory;
	
	public DelDAOImpl(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	public Session getSession() {
		return sessionFactory.getCurrentSession();
	}
	
	@Override
	public DelBean add(DelBean delBean) {
		if(delBean != null) {
			DelBean temp = this.getSession().get(DelBean.class, delBean.getDelid());
			if(temp == null) {
				Serializable pk = this.getSession().save(delBean);
				return delBean;
			}
		}
		return null;
	}

//	@Override
//	public DelBean updateWithoutPic(DelBean delBean) {
//		// TODO Auto-generated method stub
//		return null;
//	}

	@Override
	public DelBean findByDelID(Integer delID) {
		if(delID != null) {
			return this.getSession().get(DelBean.class, delID);
		} 
		return null;
	}

	@Override
	public List<DelBean> getAll() {
//		return (List<DelBean>) this.getSession().createNamedQuery("del.DelBean.all").list();
		
// criteria
		CriteriaBuilder builder = this.getSession().getCriteriaBuilder();
		CriteriaQuery<DelBean> criteriaQuery = builder.createQuery(DelBean.class); //因為要回傳list<DelBean>所以這裡用DelBean
		
// from DEL(這邊是要查哪一個table所以table del對應到的就是DelBean這個class)
		//這裡產生的root也是當我們要用where時取得欄位的方法
		Root<DelBean> root = criteriaQuery.from(DelBean.class);
		
		
		TypedQuery<DelBean> typedQuery = this.getSession().createQuery(criteriaQuery);
		List<DelBean> result = typedQuery.getResultList();
		
		if(result != null) {
			return result;
		} else {
			return new ArrayList<>(); 
		}
	}
	
	@Override
	public boolean delete(Integer delID) {
		if(delID != null) {
			DelBean temp = this.getSession().get(DelBean.class, delID);
			if(temp != null) {
				this.getSession().delete(temp);
				return true;
			}
		}
		return false;
	}

//	@Override
//	public String getCost(Timestamp startDate, Timestamp endDate, Integer delID) {
//		if(delID != null) {
//			DelBean tempBean = this.getSession().get(DelBean.class, delID);
//			if(tempBean != null) {
////				Select del_cost 
////				From del and order 
////				where delid = ?
//				String hql = "SELECT DEL_COST FROM DEL, ORDER";
//				Query<Integer> query = this.getSession().createQuery(hql);
//			}
//		}
//		return null;
//	}
//
//	@Override
//	public String getRideTimes(Timestamp startDate, Timestamp endDate, Integer delID) {
//		// select count(*) from order, del on delid
//		
//		return null;
//	}
//
	@Override
	public List<String> getRecord(Timestamp startDate, Timestamp endDate, Integer delID) {
		if(delID != null) {
			List<String> comments = (List<String>) this.getSession().createNamedQuery("del.DelBean.getComment").list();
			if(comments != null) {
				return comments;
			}
		} 
		return null;
	}

}
