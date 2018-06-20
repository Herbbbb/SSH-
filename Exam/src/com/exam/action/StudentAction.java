package com.exam.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.ServletRequestAware;

import com.exam.dao.StudentDao;
import com.exam.model.PageBean;
import com.exam.model.Student;
import com.exam.util.DateUtil;
import com.exam.util.PageUtil;
import com.exam.util.ResponseUtil;
import com.exam.util.StringUtil;
import com.opensymphony.xwork2.ActionSupport;

import net.sf.json.JSONObject;

public class StudentAction extends ActionSupport implements ServletRequestAware{

	private static final long serialVersionUID = 1L;
	private HttpServletRequest request;
	private StudentDao studentDao = new StudentDao();
	
	private Student student;
	private String error;          //登陆报错信息
	
	private String mainPage;         //主页
	
	private List<Student> studentList;   //学生信息列表
	
	private Student s_student;          //接收学生信息查询条件
	
	private String page;           //当前页
	private int total;             //总记录数
	private String pageCode;        //当前页的记录数
	
	private String title;
	
	private String id;      //修改学生的id
	
	public Student getStudent() {
		return student;
	}


	public void setStudent(Student student) {
		this.student = student;
	}


	public String getError() {
		return error;
	}


	public void setError(String error) {
		this.error = error;
	}

	public String getMainPage() {
		return mainPage;
	}


	public void setMainPage(String mainPage) {
		this.mainPage = mainPage;
	}

	public List<Student> getStudentList() {
		return studentList;
	}


	public void setStudentList(List<Student> studentList) {
		this.studentList = studentList;
	}

	public Student getS_student() {
		return s_student;
	}


	public void setS_student(Student s_student) {
		this.s_student = s_student;
	}

	public String getPage() {
		return page;
	}


	public void setPage(String page) {
		this.page = page;
	}


	public int getTotal() {
		return total;
	}


	public void setTotal(int total) {
		this.total = total;
	}


	public String getPageCode() {
		return pageCode;
	}


	public void setPageCode(String pageCode) {
		this.pageCode = pageCode;
	}

	public String getTitle() {
		return title;
	}


	public void setTitle(String title) {
		this.title = title;
	}

	public String getId() {
		return id;
	}


	public void setId(String id) {
		this.id = id;
	}


	//登陆
	public String login()throws Exception{
		HttpSession session = request.getSession();
		Student currentUser = studentDao.login(student);
		if(currentUser == null){
			error = "准考证号或密码错误！";
			return ERROR;
		}else{
			session.setAttribute("currentUser", currentUser);
			return SUCCESS;
		}
	}
	
	//自修改，跳转到修改界面
	public String preUpdatePassword()throws Exception{
		mainPage="student/updatePassword.jsp";
		return SUCCESS;
	}
	
	//根据id获得学生实体
	public String updatePassword()throws Exception{
		Student s=studentDao.getStudentById(student.getId());
		s.setPassword(student.getPassword());      //修改密码
		studentDao.saveStudent(s);
		mainPage="student/updateSuccess.jsp";
		return SUCCESS;
	}
	
    //	注销用户
	public String logout()throws Exception{
//		注销session
		request.getSession().invalidate();
		return "logout";
	}
	
	public String list()throws Exception{
		HttpSession session=request.getSession();
		//当前页
		if(StringUtil.isEmpty(page)){
			page="1";
		}
		if(s_student==null){
			Object o=session.getAttribute("s_student");
			if(o!=null){
				s_student=(Student)o;
			}else{
				s_student=new Student();				
			}
		}else{
			session.setAttribute("s_student", s_student);
		}
		PageBean pageBean=new PageBean(Integer.parseInt(page),3);
		studentList=studentDao.getStudents(s_student,pageBean);
		//获取总记录数
		total=studentDao.studentCount(s_student);
		//获取当前页的记录数
		pageCode=PageUtil.genPagination(request.getContextPath()+"/student!list", total, Integer.parseInt(page), 3);
		mainPage="student/studentList.jsp";
		return SUCCESS;
	}
	
	//欲添加操作
	public String preSave()throws Exception{
		if(StringUtil.isNotEmpty(id)){
			student=studentDao.getStudentById(id);
			title="修改学生信息";
		}else{
			title="添加学生信息";			
		}
		mainPage="student/Preservation.jsp";
		return SUCCESS;
	}
	
	//学生信息保存
	public String saveStudent()throws Exception{
		if(StringUtil.isEmpty(student.getId())){
			student.setId("JS"+DateUtil.getCurrentDateStr());
		}
		studentDao.saveStudent(student);
		return "save";
	}
	
	//删除学生
	public String deleteStudent()throws Exception{
		student=studentDao.getStudentById(id);
		studentDao.studentDelete(student);
		JSONObject resultJson=new JSONObject();
		resultJson.put("success", true);
		ResponseUtil.write(resultJson, ServletActionContext.getResponse());
		return null;
	}

	@Override
	public void setServletRequest(HttpServletRequest request) {
		this.request = request;	
	}

}
