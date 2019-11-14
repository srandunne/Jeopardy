package jeopardy;

public class Question {
	
	

	private String question;
	private String answer1;
	private String answer2;
	private String answer3;
	private String answer4;
	private int corrAnwser;
	private int value;
	/**
	 * @param question
	 * @param anwser1
	 * @param anwser2
	 * @param anwser3
         * @param answer4
	 * @param corrAnwser
	 * @param value
	 */
	public Question(String question, String anwser1, String anwser2,
			String anwser3, String answer4, int corrAnwser, int value) {

		this.question = question;
		this.answer1 = anwser1;
		this.answer2 = anwser2;
		this.answer3 = anwser3;
		this.answer4 = answer4;
		this.corrAnwser = corrAnwser;
		this.value = value;
	}
	/*
        This method retruns a to-string of the anwser choices and the question
        @ retrun: the string of choices and the question
        */
	public String toString() {
		return  question + "\n" + answer1+ "\n"
				+ answer2+ "\n" +  answer3+ "\n"
				+ answer4;
	}
	/*
        This methods gets the correct anwser
        @ return: the correct ans
        */
	public int getCorrAnwser() {
		return corrAnwser;
	}

	
	
	                            
	
	
	
	


}