//学生登陆类
package com.exam.dao;

import java.math.BigInteger;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import com.exam.model.PageBean;
import com.exam.model.Student;
import com.exam.util.HibernateUtil;
import com.exam.util.StringUtil;

public class StudentDao {
	public  Student login(Student student)throws Exception{
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();  //开始
		Query query = session.createQuery("from Student as s where s.id=:id and s.password=:password");
		query.setString("id", student.getId()); // 获取学生的学号id
		query.setString("password", student.getPassword());   //获取学生的登陆密码
		Student resultStu = (Student)query.uniqueResult();  //每一个学生对应的一个结果
		session.getTransaction().commit();//结束
		return resultStu;
	}
	
	//根据学生的ID获取到相应的学生的信息
	public Student getStudentById(String id)throws Exception{
		Session session=HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		Student student=(Student)session.get(Student.class, id);
		session.getTransaction().commit();
		return student;
	}
	
	//将修改的后的学生信息保存起来
	public void saveStudent(Student student)throws Exception{
		Session session=HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		session.merge(student);
		session.getTransaction().commit();//提交事务
	}
	
	//查询所有学生信息
	public List<Student> getStudents(Student s_student,PageBean pageBean)throws Exception{
		Session session=HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		StringBuffer hql=new StringBuffer("from Student");
		
		//根据学生id查询
		if(StringUtil.isNotEmpty(s_student.getId())){
			hql.append(" and id like '%"+s_student.getId()+"%'");
		}
		//根据学生姓名查询
		if(StringUtil.isNotEmpty(s_student.getName())){
			hql.append(" and name like '%"+s_student.getName()+"%'");
		}
		Query query=session.createQuery(hql.toString().replaceFirst("and", "where"));
		if(pageBean!=null){
			query.setFirstResult(pageBean.getStart());
			query.setMaxResults(pageBean.getPageSize());
		}
		@SuppressWarnings("unchecked")
		List<Student> studentList=(List<Student>)query.list();
		session.getTransaction().commit();
		return studentList;
	}
	
	//获取总计录数
	public int studentCount(Student s_student)throws Exception{
		Session session=HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		StringBuffer sql=new StringBuffer("select count(*) from t_student");
		if(StringUtil.isNotEmpty(s_student.getId())){
			sql.append(" and id like '%"+s_student.getId()+"%'");
		}
		if(StringUtil.isNotEmpty(s_student.getName())){
			sql.append(" and name like '%"+s_student.getName()+"%'");
		}
		Query query=session.createSQLQuery(sql.toString().replaceFirst("and", "where"));
		//找到了总记录数
		int count=((BigInteger)query.uniqueResult()).intValue();
		session.getTransaction().commit();
		return count;
	}
	
	//删除学生信息
	public void studentDelete(Student student)throws Exception{
		Session session=HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		session.delete(student);
		session.getTransaction().commit();
	}
}
