package citizensLoader.factory;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import citizensLoader.letters.LetterGenerator;
import citizensLoader.letters.PDFLetter;
import citizensLoader.letters.PasswordGenerator;
import citizensLoader.letters.TxtLetter;
import persistence.User;

public class ExcelReader {
	
	private LetterGenerator letterGenerator = new TxtLetter();
	private LetterGenerator letterGenerator2 = new PDFLetter();
	
	public ExcelReader()
	{
		
	}

	/**
	 * Reads the users from the given filem saves them in the database and generates a 
	 * txt and pdf letter with the credentials to access the system
	 * 
	 * @param filename
	 * @return arrayList of users 
	 * @throws IOException
	 */
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
	 * generates a new User from the excel data file
	 * 
	 * @param excel row from which the person data is obtained
	 * @return new user
	 */
	private User userFromRow(Row row) {
		User us = null;
		
		if (row != null) {
			String firstName = row.getCell(0).getStringCellValue();
			String lastName = row.getCell(1).getStringCellValue();
			String email = row.getCell(2).getStringCellValue();
			Date dateOfBirth = row.getCell(3).getDateCellValue();
			String address = row.getCell(4).getStringCellValue();
			String nationality = row.getCell(5).getStringCellValue();
			String dni = row.getCell(6).getStringCellValue();
			int pollingStation = (int) row.getCell(7).getNumericCellValue();

			us = new User(dni, firstName, lastName, dateOfBirth, address, email, nationality, pollingStation);
		
		us.setPassword(PasswordGenerator.generateRandomPassword());
		}
		return us;
	}
	
}