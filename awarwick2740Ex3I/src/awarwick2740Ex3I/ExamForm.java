package awarwick2740Ex3I;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.DefaultListModel;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.AbstractListModel;
import javax.swing.UIManager;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.ListSelectionModel;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.event.ListSelectionListener;
import javax.swing.event.ListSelectionEvent;

import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

public class ExamForm extends JFrame {

	private JPanel contentPane;
	private JTextField answerText;
	private JLabel resultsLabel;
	private JList responsesList;
	private JLabel questNumLabel;
	private JButton btnPrev;
	private JButton btnNext;
	private DefaultListModel responsesListModel;
	private DriverExam exam;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ExamForm frame = new ExamForm();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public ExamForm() {
		setTitle("Ex3I Driver Exam");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 284, 326);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblResponses = new JLabel("Responses:");
		lblResponses.setBounds(10, 11, 69, 14);
		contentPane.add(lblResponses);
		
		JLabel lblResult = new JLabel("Result:");
		lblResult.setBounds(135, 11, 46, 14);
		contentPane.add(lblResult);
		
		JList list = new JList();
		list.setEnabled(false);
		list.setBackground(UIManager.getColor("Label.background"));
		list.setModel(new AbstractListModel() {
			String[] values = new String[] {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10"};
			public int getSize() {
				return values.length;
			}
			public Object getElementAt(int index) {
				return values[index];
			}
		});
		list.setBounds(10, 36, 34, 190);
		contentPane.add(list);
		
		questNumLabel = new JLabel("#0");
		questNumLabel.setBounds(10, 237, 34, 14);
		contentPane.add(questNumLabel);
		
		responsesList = new JList();
		responsesList.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent e) {
				do_responsesList_valueChanged(e);
			}
		});
		responsesList.setBorder(new EmptyBorder(0, 0, 0, 0));
		responsesList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		responsesList.setBounds(43, 36, 36, 190);
		contentPane.add(responsesList);
		
		answerText = new JTextField();
		answerText.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				do_answerText_focusGained(e);
			}
		});
		questNumLabel.setLabelFor(answerText);
		answerText.setBounds(43, 234, 46, 20);
		contentPane.add(answerText);
		answerText.setColumns(10);
		
		resultsLabel = new JLabel("");
		resultsLabel.setBounds(89, 36, 171, 31);
		resultsLabel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		contentPane.add(resultsLabel);
		
		JButton btnPass = new JButton("Pass");
		btnPass.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				do_btnPass_actionPerformed(e);
			}
		});
		btnPass.setBounds(107, 78, 114, 23);
		contentPane.add(btnPass);
		
		JButton btnCorrect = new JButton("Correct");
		btnCorrect.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				do_btnCorrect_actionPerformed(e);
			}
		});
		btnCorrect.setBounds(107, 112, 114, 23);
		contentPane.add(btnCorrect);
		
		JButton btnIncorrect = new JButton("Incorrect");
		btnIncorrect.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				do_btnIncorrect_actionPerformed(e);
			}
		});
		btnIncorrect.setBounds(107, 146, 114, 23);
		contentPane.add(btnIncorrect);
		
		JButton btnListIncorrect = new JButton("List Incorrect");
		btnListIncorrect.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				do_btnListIncorrect_actionPerformed(e);
			}
		});
		btnListIncorrect.setBounds(107, 180, 114, 23);
		contentPane.add(btnListIncorrect);
		
		btnPrev = new JButton("Prev");
		btnPrev.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				do_btnPrev_actionPerformed(e);
			}
		});
		btnPrev.setEnabled(false);
		btnPrev.setBounds(99, 214, 69, 23);
		contentPane.add(btnPrev);
		
		btnNext = new JButton("Next");
		btnNext.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				do_btnNext_actionPerformed(e);
			}
		});
		btnNext.setBounds(99, 246, 69, 23);
		contentPane.add(btnNext);
		
		DriverExamObjMapper mapper = new DriverExamObjMapper("DriverExam.txt");
		this.exam = mapper.getDriverExam();
		this.responsesListModel = exam.getAnswers();
		responsesList.setModel(this.responsesListModel);
		
		responsesList.setSelectedIndex(0);
	}
	protected void do_btnPass_actionPerformed(ActionEvent e) {
		this.exam.setResponses((DefaultListModel) responsesList.getModel());
		int invalid = this.exam.validate();
		if (invalid >= 0) {
			resultsLabel.setText("Invalid Response #" + Integer.toString(invalid + 1));
			responsesList.setSelectedIndex(invalid);
			
		}
		else {
			if (exam.passed()) resultsLabel.setText("You Passed");
			else resultsLabel.setText("You failed");
		}
	}
	protected void do_btnCorrect_actionPerformed(ActionEvent e) {
		this.exam.setResponses((DefaultListModel) responsesList.getModel());
		int invalid = this.exam.validate();
		if (invalid >= 0) {
			resultsLabel.setText("Invalid Response #" + Integer.toString(invalid + 1));
			responsesList.setSelectedIndex(invalid);
			
		}
		else {
			resultsLabel.setText("You got " + exam.totalCorrect() + " Correct answers.");
		}
		
	}
	protected void do_btnIncorrect_actionPerformed(ActionEvent e) {
		
		this.exam.setResponses((DefaultListModel) responsesList.getModel());
		int invalid = this.exam.validate();
		if (invalid >= 0) {
			resultsLabel.setText("Invalid Response #" + Integer.toString(invalid + 1));
			responsesList.setSelectedIndex(invalid);
			
		}
		else {
			resultsLabel.setText("You got " + exam.totalIncorrect() + " Incorrect answers.");
		}
	}
	protected void do_btnListIncorrect_actionPerformed(ActionEvent e) {
		this.exam.setResponses((DefaultListModel) responsesList.getModel());
		int invalid = this.exam.validate();
		if (invalid >= 0) {
			resultsLabel.setText("Invalid Response #" + Integer.toString(invalid + 1));
			responsesList.setSelectedIndex(invalid);
			
		}
		else {
			int [] missed = exam.questionsMissed();
			int i = 0;
			String strMissed = "";
			while (i < missed.length && missed[i] > 0) {
				strMissed = strMissed + " " + missed[i];
				i++;
			}
			resultsLabel.setText("Missed: " + strMissed);
		}
	}
	protected void do_btnPrev_actionPerformed(ActionEvent e) {
		this.responsesListModel.setElementAt(answerText.getText().toUpperCase(), responsesList.getSelectedIndex());
		responsesList.setSelectedIndex(responsesList.getSelectedIndex() - 1);
		questNumLabel.setText("#" + Integer.toString((responsesList.getSelectedIndex() + 1)));
		answerText.setText((String) responsesList.getSelectedValue());
		
		btnNext.setEnabled(true);
		if (responsesList.getSelectedIndex() == 0)
			btnPrev.setEnabled(false);
		answerText.requestFocus();
	}
	protected void do_btnNext_actionPerformed(ActionEvent e) {
//		if (responsesListModel() == null) 
		this.responsesListModel.setElementAt(answerText.getText().toUpperCase(), responsesList.getSelectedIndex());
		responsesList.setSelectedIndex(responsesList.getSelectedIndex() + 1);
		questNumLabel.setText("#" + Integer.toString((responsesList.getSelectedIndex() + 1)));
		answerText.setText((String) responsesList.getSelectedValue());
		
		btnPrev.setEnabled(true);
		if (responsesList.getSelectedIndex() == responsesListModel.getSize() -1)
			btnNext.setEnabled(false);
		answerText.requestFocus();
	}
	protected void do_responsesList_valueChanged(ListSelectionEvent e) {
		questNumLabel.setText("#" + Integer.toString((responsesList.getSelectedIndex() + 1)));
		answerText.setText((String) responsesList.getSelectedValue());
		
		btnPrev.setEnabled(true);
		btnNext.setEnabled(true);
		if (responsesList.getSelectedIndex() == responsesListModel.getSize() - 1)
			btnNext.setEnabled(false);
		if (responsesList.getSelectedIndex() == 0)
			btnPrev.setEnabled(false);
		answerText.requestFocus();
		
	}
	protected void do_answerText_focusGained(FocusEvent e) {
		answerText.selectAll();
	}
}
