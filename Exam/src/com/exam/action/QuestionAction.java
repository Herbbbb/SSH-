package com.exam.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONObject;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.ServletRequestAware;

import com.exam.dao.PaperDao;
import com.exam.dao.QuestionDao;
import com.exam.model.PageBean;
import com.exam.model.Paper;
import com.exam.model.Question;
import com.exam.util.PageUtil;
import com.exam.util.ResponseUtil;
import com.exam.util.StringUtil;
import com.opensymphony.xwork2.ActionSupport;

public class QuestionAction extends ActionSupport implements ServletRequestAware{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private HttpServletRequest request;
	
	private QuestionDao questionDao=new QuestionDao();
	private PaperDao paperDao=new PaperDao();
	
	private List<Question> questionList;
	
	private String mainPage;
	
	private Question question;
	private Question s_question;
	
	private String page;
	private int total;
	private String pageCode;
	
	private String questionId;
	
	private List<Paper> paperList;
	
	private String title;
	
	
	public Question getQuestion() {
		return question;
	}



	public void setQuestion(Question question) {
		this.question = question;
	}



	public String getQuestionId() {
		return questionId;
	}



	public void setQuestionId(String questionId) {
		this.questionId = questionId;
	}



	public List<Question> getQuestionList() {
		return questionList;
	}



	public void setQuestionList(List<Question> questionList) {
		this.questionList = questionList;
	}



	public String getMainPage() {
		return mainPage;
	}



	public void setMainPage(String mainPage) {
		this.mainPage = mainPage;
	}



	public Question getS_question() {
		return s_question;
	}



	public void setS_question(Question s_question) {
		this.s_question = s_question;
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
	
	



	public List<Paper> getPaperList() {
		return paperList;
	}



	public void setPaperList(List<Paper> paperList) {
		this.paperList = paperList;
	}
	
	



	public String getTitle() {
		return title;
	}



	public void setTitle(String title) {
		this.title = title;
	}



	@Override
	public void setServletRequest(HttpServletRequest request) {
		// TODO Auto-generated method stub
		this.request=request;
	}

	public String list()throws Exception{
		HttpSession session=request.getSession();
		if(StringUtil.isEmpty(page)){
			page="1";
		}
		if(s_question==null){
			Object o=session.getAttribute("s_question");
			if(o!=null){
				s_question=(Question)o;
			}else{
				s_question=new Question();				
			}
		}else{
			session.setAttribute("s_question", s_question);
		}
		PageBean pageBean=new PageBean(Integer.parseInt(page),3);
		questionList=questionDao.getQuestions(s_question, pageBean);
		total=questionDao.questionCount(s_question);
		pageCode=PageUtil.genPagination(request.getContextPath()+"/question!list", total, Integer.parseInt(page), 3);
		mainPage="question/questionList.jsp";
		return SUCCESS;
	}
	
	public String getQuestionById()throws Exception{
		question=questionDao.getQuestion(questionId);
		mainPage="question/questionShow.jsp";
		return SUCCESS;
	}
	
	public String delete()throws Exception{
		question=questionDao.getQuestion(questionId);
		questionDao.questionDelete(question);
		JSONObject resultJson=new JSONObject();
		resultJson.put("success", true);
		ResponseUtil.write(resultJson, ServletActionContext.getResponse());
		return null;
	}
	
	public String preSave()throws Exception{
		paperList=paperDao.getPapers();
		if(StringUtil.isNotEmpty(questionId)){
			question=questionDao.getQuestion(questionId);
			title="修改试卷信息";
		}else{
			title="添加试卷信息";
		}
		mainPage="question/questionStorage.jsp";
		return SUCCESS;
	}
	
	public String saveQuestion()throws Exception{
		if(StringUtil.isNotEmpty(questionId)){
			question.setId(Integer.parseInt(questionId));
		}
		questionDao.saveQuestion(question);
		return "save";
	}
}
