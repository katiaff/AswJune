package citizensLoader.letters;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

import persistence.User;

public class TxtLetter implements LetterGenerator{
	
	public void generateLetter(User user) throws IOException {
		BufferedWriter file;

		String fileName = "PersonalLetters/"+user.getEmail()+".txt";
		file = new BufferedWriter(new FileWriter(fileName));

		String message ="User:"+user.getEmail()+"\tPassword:"+user.getPassword();
		file.write(message);
		file.close();
	}
}
