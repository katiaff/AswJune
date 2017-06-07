package database;

import static org.junit.Assert.assertEquals;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.Test;

import persistence.Persistence;
import persistence.User;
import persistence.UserDao;

public class UserTest {
	
	private UserDao userdao = Persistence.getUserDao();
	
	@Test
	public void saveUserTest() throws ParseException {
		
		Date birthdate = new SimpleDateFormat("dd/MM/yyyy").parse("27/06/1995");
		
		User newUser = new User("71900607P", "Katia", "fernandez", birthdate, "Aviles", 
				"katia@uniovi.es", "Español", 1);
		newUser.setPassword("01234");
		
		userdao.saveUser(newUser);
		
		User lookForNewUser = userdao.getUserByEmail("katia@uniovi.es");
		
		assertEquals(newUser, lookForNewUser);
	}
	
	@Test
	public void getUserByEmailTest() throws ParseException {

		
		String dniUser = "71900607P";
		
		User userByEmail = userdao.getUserByEmail("katia@uniovi.es");
		String dniFound = userByEmail.getDni();
		
		assertEquals(dniFound, dniUser);
	}
}
