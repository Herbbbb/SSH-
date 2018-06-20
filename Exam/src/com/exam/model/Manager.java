//管理员实体类
package com.exam.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.GenericGenerator;

@Entity       //注解实体类，只有当实体类要访问数据库才会链接，
@Table(name="t_manager")    //映射数据表，表示该实体类链接的数据表的名称
public class Manager {
	
	private int id;        //管理员的id;
	private String userName;    //用户名字
	private String password;     //管理员密码
	private String name;          //管理员的名字
	private String flag = "1";     //判断是用户名还是管理员登陆，不映射到数据中,是1表示管理员登陆
	
	
	//开始映射字段,就是要把那些字段，映射到数据表中
	@Id
	@GeneratedValue(generator="_native")   //是让id主键自动加1
	@GenericGenerator(name="_native",strategy="native")
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String passward) {
		this.password = passward;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	
	@Transient            //表示该字段不会映射到数据库中去
	public String getFlag() {
		return flag;
	}
	public void setFlag(String flag) {
		this.flag = flag;
	}
	
	
}
