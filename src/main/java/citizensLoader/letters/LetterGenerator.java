package citizensLoader.letters;

import java.io.IOException;

import persistence.User;

public interface LetterGenerator {
	
	void generateLetter(User user) throws IOException;
}
