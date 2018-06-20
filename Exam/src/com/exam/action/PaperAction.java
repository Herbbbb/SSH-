package com.exam.action;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.Set;

import org.apache.struts2.ServletActionContext;

import com.exam.dao.PaperDao;
import com.exam.dao.QuestionDao;
import com.exam.model.Paper;
import com.exam.model.Question;
import com.exam.util.ResponseUtil;
import com.exam.util.StringUtil;
import com.opensymphony.xwork2.ActionSupport;

import net.sf.json.JSONObject;

public class PaperAction extends ActionSupport{

	private static final long serialVersionUID = 1L;
	private PaperDao paperDao = new PaperDao();
	private QuestionDao questionDao=new QuestionDao();
	
	private String mainPage;
	
	private List<Paper> paperList = new ArrayList<Paper>();
	
	private String paperId;    //试卷Id
	private Paper paper;       //试卷
	
	//单选题
	private List<Question> squestionList = new ArrayList<Question>();
	//多选题
	private List<Question> mquestionList = new ArrayList<Question>();
	
	private String title;
	
	public String getPaperId() {
		return paperId;
	}

	public void setPaperId(String paperId) {
		this.paperId = paperId;
	}

	public Paper getPaper() {
		return paper;
	}

	public void setPaper(Paper paper) {
		this.paper = paper;
	}

	public List<Question> getSquestionList() {
		return squestionList;
	}

	public void setSquestionList(List<Question> squestionList) {
		this.squestionList = squestionList;
	}

	public List<Question> getMquestionList() {
		return mquestionList;
	}

	public void setMquestionList(List<Question> mquestionList) {
		this.mquestionList = mquestionList;
	}

	public String getMainPage() {
		return mainPage;
	}

	public void setMainPage(String mainPage) {
		this.mainPage = mainPage;
	}

	public List<Paper> getPaperList() {
		return paperList;
	}

	public void setPaperList(List<Paper> paperList) {
		this.paperList = paperList;
	}
	
	public String list()throws Exception{
		paperList = paperDao.getPapers();
		mainPage = "exam/selectPaper.jsp";
		return SUCCESS;
	}
	
	//试卷列表
	public String paperList()throws Exception{
		paperList=paperDao.getPapers();
		mainPage="paper/paperList.jsp";
		return SUCCESS;
	}
	
	//删除试卷
	public String deletePaper()throws Exception{
		paper=paperDao.getPaper(paperId);
		JSONObject resultJson=new JSONObject();
		if(questionDao.existQuestionByPaperId(paperId)){
			resultJson.put("error", "试卷下面有题目，不能删除");
		}else{
			paperDao.paperDelete(paper);
			resultJson.put("success", true);
		}
		ResponseUtil.write(resultJson, ServletActionContext.getResponse());
		return null;
	}
	
	//获取试卷题目
	public String getDetailPaper()throws Exception{
		paper=paperDao.getPaper(paperId);
		paper.getQuestions();
		//题目的集合
		Set<Question> questionList = paper.getQuestions();
		//随机获取题目
		Iterator<Question> it = questionList.iterator();
		//根据数据库中的question的type总段判断是单选题还是多选题
		while(it.hasNext()){
			Question q = it.next();
			if("1".equals(q.getType())){
				//添加到单选题列表表中
				squestionList.add(q);
			}else{
				//添加到多选题列表中
				mquestionList.add(q);
			}
		}
		//随机获取单选题的题目，把他添加到单选题列表当中，并设置单选题有多少题
		squestionList=this.getRandowQuestion(squestionList,3);
		//随机获取多选题的题目，把他添加到多选题列表当中，并设置多选题有多少题
		mquestionList=this.getRandowQuestion(mquestionList,2);
		//把题目添加到mainpaper页面上
		mainPage="exam/paper.jsp";
		return SUCCESS;
	}
	//参数表示在题目集合中随机抽取多少的题目
	private List<Question> getRandowQuestion(List<Question> questionList,int num){
		List<Question> resultList=new ArrayList<Question>();
		//随机获取题目数
		Random random = new Random();
		if(num>0){
			for(int i=1;i<=num;i++){
				int n=random.nextInt(questionList.size());
				//获取问题
				Question q=questionList.get(n);
				//判断问题是否重复
				if(resultList.contains(q)){
					i--;
				}else{
					resultList.add(q);
				}
			}
		}
		return resultList;
	}
	
	//添加试卷与修改试卷的预操作
	public String preSave()throws Exception{
		if(StringUtil.isNotEmpty(paperId)){
			paper=paperDao.getPaper(paperId);
			title="修改试卷";
		}else{
			title="添加试卷";
		}
		mainPage="paper/paperStorage.jsp";
		return SUCCESS;
	}
	
	//保存试卷
	public String savePaper()throws Exception{
		if(StringUtil.isNotEmpty(paperId)){
			paper.setId(Integer.parseInt(paperId));
		}else{
			paper.setJoinDate(new Date());
		}
		paperDao.savePaper(paper);
		return "save";
	}
}
