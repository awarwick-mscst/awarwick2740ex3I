package awarwick2740Ex3I;

import javax.swing.DefaultListModel;

public class DriverExam {
	char answers[] = {' ',' ',' ',' ',' ',' ',' ',' ',' ',' '};
	double requiredPct = 0.7;
//	char[] responses = {'B', 'D', 'A', 'A', 'C', 'A', 'B', 'A', 'C', 'D'};
	char[] responses;
	
	public DriverExam(char[] answers) {
		super();
		
		for (int i = 0; i < answers.length; i++) {
			this.answers[i] = answers[i];
		}
	}
	
	public DriverExam(DefaultListModel answers) {
		super();
		this.answers = new char[answers.getSize()];
		for (int i = 0; i < answers.getSize(); i++) {
			String r = (String) answers.get(i);
			this.answers[i] = r.charAt(0);
		}
		
	}

	/**
	 * @return the answers
	 */
//	public void setAnswers(DefaultListModel answers) {
//		this.answers = new char[answers.getSize()];
//		for (int i = 0; i < answers.getSize(); i++) {
//			String r = (String) answers.get(i);
//			this.answers[i] = r.charAt(0);
//		}
//	}
	
	public void setResponses(DefaultListModel responses) {
		this.responses = new char[responses.getSize()];
		for (int i = 0; i < responses.getSize(); i++) {
			String r = (String) responses.get(i);
			this.responses[i] = r.charAt(0);
		}
	}
	
	public DefaultListModel getAnswers() {
		DefaultListModel answerListModel = new DefaultListModel();
		for (int i = 0; i < answers.length; i++) {
			char r = answers[i];
			answerListModel.addElement(Character.toString(r));
		}
		return answerListModel;
	}



	/**
	 * @return the responses
	 */
	public int validate() {
		int v = -1;
		for (int i = 0; i < answers.length; i++) {
			if (responses[i] != 'A' && responses[i] != 'B' && responses[i] != 'C' && responses[i] != 'D') {
							v = i ;
							i = 10;
			};

		}
		return v;
	}

	/**
	 * @param responses the responses to set
	 */
	public boolean passed() {
		boolean passed = false;
		if ((this.totalCorrect() / 10.0) >= 0.7) passed = true;
		return passed;
	}

	public int totalCorrect() {
		int c = 0;
		for (int i = 0; i < this.answers.length; i++) {
			if (this.answers[i] == this.responses[i]) c++;
		}
		return c;
	}
	
	public int totalIncorrect() {
		int c = 0;
		for (int i = 0; i < this.answers.length; i++) {
			if (this.answers[i] != this.responses[i]) c++;
		}
		return c;
	}
	
	
	public int[] questionsMissed() {
		int missed[] = { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		int m = 0;
		for (int i = 0; i < answers.length; i++){
			if (this.answers[i] != this.responses[i]) {
				missed[m] = (i + 1);
				m++;
			}
		}
		return missed;
	
	}
}
