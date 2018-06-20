//试卷类
package com.exam.model;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name="t_paper")
public class Paper {
	
	private int id;
	private String paperName;
	private Date joinDate;
	//一个集合，实现一套试卷绑定多个试题
	private Set<Question> questions = new HashSet<Question>();
	
	
	@Id
	@GeneratedValue(generator="_native")   //是让id主键自动加1
	@GenericGenerator(name="_native",strategy="native")
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getPaperName() {
		return paperName;
	}
	public void setPaperName(String paperName) {
		this.paperName = paperName;
	}
	public Date getJoinDate() {
		return joinDate;
	}
	public void setJoinDate(Date joinDate) {
		this.joinDate = joinDate;
	}
	
	@OneToMany(mappedBy="paper",fetch=FetchType.EAGER)     //一对多的关系，对应Question中的paper,fetch=FetchType.EAGER;立即加载
	public Set<Question> getQuestions() {
		return questions;
	}
	public void setQuestions(Set<Question> questions) {
		this.questions = questions;
	}
	
}
