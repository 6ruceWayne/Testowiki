package ua.java.models;

import java.util.HashMap;
import java.util.Map;

public class UserAnswerForm {

	private Map<Long, Boolean> map = new HashMap<Long, Boolean>();
	private long questionid;
	private Integer page;
	/*
	 * private List<AnswerByUser> list = new ArrayList<AnswerByUser>();
	 * 
	 * public List<AnswerByUser> getList() { return list; }
	 * 
	 * public void setList(List<AnswerByUser> list) { this.list = list; }
	 */

	public Integer getPage() {
		return page;
	}

	public void setPage(Integer page) {
		this.page = page;
	}

	public long getQuestionid() {
		return questionid;
	}

	public void setQuestionid(long questionid) {
		this.questionid = questionid;
	}

	public Map<Long, Boolean> getMap() {
		return map;
	}

	public void setMap(Map<Long, Boolean> list) {
		this.map = list;
	}
}
