package ua.java.models;

public class AnswerByUser {
	private long answerId;
	private boolean choise;

	public long getAnswerId() {
		return answerId;
	}

	public void setAnswerId(long answerId) {
		this.answerId = answerId;
	}

	public boolean isChoise() {
		return choise;
	}

	public void setChoise(boolean choise) {
		this.choise = choise;
	}
}
