//试卷类
package com.exam.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import com.exam.model.Paper;
import com.exam.model.Student;
import com.exam.util.HibernateUtil;

public class PaperDao {

	public List<Paper> getPapers()throws Exception{
		Session session=HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		Query query=session.createQuery("from Paper");
		List<Paper> paperList=(List<Paper>)query.list();
		//防止嵌套出错
		session.getTransaction().commit();
		return paperList;
	}
	
	//随机获取试卷题目
	public Paper getPaper(String paperId)throws Exception{
		Session session=HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		// 根据卷子的id获取题目		
		Paper paper=(Paper)session.get(Paper.class, Integer.parseInt(paperId));
		session.getTransaction().commit();
		return paper;
	}
	
	//删除试卷
	public void paperDelete(Paper paper)throws Exception{
		Session session=HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		session.delete(paper);
		session.getTransaction().commit();
	}
	
	//添加试卷
	public void savePaper(Paper paper)throws Exception{
		Session session=HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		session.merge(paper);
		session.getTransaction().commit();
	}
}
