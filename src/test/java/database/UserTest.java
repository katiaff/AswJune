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
		
		User newUser = new User("prueba", "nuevo", "usuario", birthdate, "oviedo", 
				"nuevousuario@uniovi.es", "España");
		newUser.setPassword("01234");
		
		userdao.saveUser(newUser);
		
		User lookForNewUser = userdao.getUserByEmail("nuevousuario@uniovi.es");
		
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



