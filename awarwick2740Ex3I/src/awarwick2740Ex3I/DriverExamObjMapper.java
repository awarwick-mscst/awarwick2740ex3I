package awarwick2740Ex3I;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.NoSuchElementException;
import java.util.Scanner;

import javax.swing.DefaultListModel;

public class DriverExamObjMapper {
	private String fileName;
	private Scanner inputFile;
	private File file;
	
	
	public DriverExamObjMapper(String fileName) {
		super();
		this.fileName = fileName;
	}
	
	public boolean openInputFile()  {
		boolean fileOpened = false;
//	  	
//	    	File file = new File("Ex3G.txt");
//	    	FileWriter outputFile = new FileWriter(file, true);
		try{
			File file = new File(this.fileName);
			fileOpened = file.exists();
			
		
			if (fileOpened) {
				this.inputFile = new Scanner(file);
//				fileOpened = true;
			}
		}
		catch (IOException e){}
	    return fileOpened;                         
	} 
	

	
	public void closeInputFile() {
		if (this.inputFile != null) this.inputFile.close();
		
	}
	

	
//	public DriverExam getDriverExam() {
//		
//		DriverExam exam = null;	
//		int id = 0;
//		String name = "";
//		double payRate = 0.0;
//		double hours = 0.0;
//		
//		try {
//			String textLine = inputFile.nextLine();
//			id = Integer.parseInt(textLine);
//			name = inputFile.nextLine();
//			textLine = inputFile.nextLine();
//			payRate = Double.parseDouble(textLine);
//			textLine = inputFile.nextLine();
//			hours = Double.parseDouble(textLine);
////			d = new DriverExam(id, name, payRate, hours);
//		}
//		catch (NoSuchElementException e) {}
//		catch (NumberFormatException e) {}
//		
//		return exam;
//	}
	

	
	
	public DriverExam getDriverExam() {
		DriverExam exam = null;
		DefaultListModel driverExamCollection = new DefaultListModel();
		
		if (this.openInputFile()) {
			while  (this.inputFile.hasNext()) {
				String answer = inputFile.nextLine();
				driverExamCollection.addElement(answer);
			}
			exam = new DriverExam(driverExamCollection);
		}
		this.closeInputFile();		
		return exam;
	}
	

}
