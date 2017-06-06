package database;

import static org.junit.Assert.assertEquals;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.Test;

import persistence.User;
import persistence.UserDao;
import persistence.impl.UserDaoImpl;

public class UserTest {

	@Test
	public void getUserByEmailTest() throws ParseException {
		
		UserDao userdao = new UserDaoImpl();
		
		Date birthdate = new SimpleDateFormat("dd/MM/yyyy").parse("27/06/1995");
		
		User newUser = new User("12345678P", "Pablo", "Fernandez", birthdate, "Aviles", 
				"luis@uniovi.es", "Español", 1);
		newUser.setId(1);
		newUser.setPassword("01234");
		
		User userByEmail = userdao.getUserByEmail("luis@uniovi.es");

		assertEquals(newUser, userByEmail);
	}
	
	@Test
	public void saveUserTest() throws ParseException {
		
		UserDao userdao = new UserDaoImpl();
		
		Date birthdate = new SimpleDateFormat("dd/MM/yyyy").parse("227/06/1995");
		
		User newUser = new User("12345678P", "Prueba", "usuario", birthdate, "Aviles", 
				"pruebausuario@uniovi.es", "Español", 1);
		newUser.setPassword("01234");
		
		userdao.saveUser(newUser);
		
		User lookForNewUser = userdao.getUserByEmail("pruebausuario@uniovi.es");
		System.out.println(lookForNewUser);
		System.out.println(lookForNewUser);
		
		assertEquals(newUser, lookForNewUser);
	}

}
