package com.exam.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

@Entity
@Table(name="t_student")
public class Student {
	private String id;     //考号
	private String name;   //学生姓名
	private String password;  //学生密码
	private String sex;      //学生性别
	private String prefession;    //学生专业
	private String cardNo;     //身份证号
	
	private String flag = "2";  //身份标识符，是2表示事学生登陆
	
	//双向关联，为了能够让删除学生的时候也能够把成绩给删除掉
	private List<Exam> examList = new ArrayList<Exam>();
	
	
	@Id
	@Column(name="id",unique=true,nullable=false,length=40)
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@Column(name="name",length=20)
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name="password",length=20)
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Column(name="sex",length=5)
	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	@Column(name="prefession",length=40)
	public String getPrefession() {
		return prefession;
	}

	public void setPrefession(String prefession) {
		this.prefession = prefession;
	}

	@Column(name="cardNo",length=50)
	public String getCardNo() {
		return cardNo;
	}

	public void setCardNo(String cardNo) {
		this.cardNo = cardNo;
	}

	public String getFlag() {
		return flag;
	}
	
	@OneToMany(mappedBy="student")    //一对多的关系，关联到student
	@Cascade(CascadeType.DELETE)
	public List<Exam> getExamList() {
		return examList;
	}

	public void setExamList(List<Exam> examList) {
		this.examList = examList;
	}

	@Transient            //表示该字段不会映射到数据库中去
	public void setFlag(String flag) {
		this.flag = flag;
	}
}
