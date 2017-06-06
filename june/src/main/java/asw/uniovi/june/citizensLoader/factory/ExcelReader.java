package asw.uniovi.june.citizensLoader.factory;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import asw.uniovi.june.citizensLoader.letters.LetterGenerator;
import asw.uniovi.june.citizensLoader.letters.PDFLetter;
import asw.uniovi.june.citizensLoader.letters.PasswordGenerator;
import asw.uniovi.june.citizensLoader.letters.TxtLetter;
import persistence.User;

public class ExcelReader {
	
	private LetterGenerator letterGenerator = new TxtLetter();
	private LetterGenerator letterGenerator2 = new PDFLetter();

	public ArrayList<User> readFrom(String filename) throws IOException {

		ArrayList<User> users = new ArrayList<User>();

		FileInputStream file;

		file = new FileInputStream(new File(filename));
		
		 XSSFWorkbook hwb = new XSSFWorkbook(file);
		 XSSFSheet sheet = hwb.getSheetAt(0);
		 Iterator<Row> rowIterator = sheet.iterator();
		 
		 while(rowIterator.hasNext()) {
			 Row row = rowIterator.next();
			 User us = userFromRow(row);
			 if(us != null)
				 users.add(us);				 
		 }
		 
		 for(User user : users){
			 letterGenerator.generateLetter(user);
			 letterGenerator2.generateLetter(user);
		 }
		 
		 hwb.close();
		 file.close();
		 
		 return users;
	}

	/**
	 * generates a new Person from the excel data file
	 * @param row excel row from which the person data is obtained
	 * @return new user
	 */
	private User userFromRow(Row row) {
		User us = null;
		
		if (row != null) {
			String firstname = row.getCell(0).getStringCellValue();
			String lastname = row.getCell(1).getStringCellValue();
			String email = row.getCell(2).getStringCellValue();
			Date dateofbirth = row.getCell(3).getDateCellValue();
			String address = row.getCell(4).getStringCellValue();
			String nationality = row.getCell(5).getStringCellValue();
			String dni = row.getCell(6).getStringCellValue();
			int polling = (int) row.getCell(7).getNumericCellValue();

			us = new User(firstname, lastname, dateofbirth, address, nationality, dni, email);
		
		us.setPassword(PasswordGenerator.generateRandomPassword());
		}
		return us;
	}
	
}