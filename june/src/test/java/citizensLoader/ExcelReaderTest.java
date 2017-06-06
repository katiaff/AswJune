package citizensLoader;

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import citizensLoader.factory.ExcelReader;
import persistence.User;
import persistence.UserDao;
import persistence.impl.UserDaoImpl;

public class ExcelReaderTest {

	private UserDao userdao;

	private final static String MARIA = "User[Name: Maria; Surname: Fernandez; "
			+ "Email: maria@uniovi.es; Birth date: 27/06/1995; "
			+ "Address: C/ Rey Pelayo; Nationality: Español; DNI: 99999999X; Polling station: 1]";
	private final static String PEPE = "User[Name: Pepe; Surname: Fernandez; "
			+ "Email: pepe@uniovi.es; Birth date: 05/07/1999; " + "Address: C/ Castillo Gauzon; Nationality: "
			+ "Español; DNI: 11111111P; Polling station: 2]";
	
	@Before
	public void setUp() throws IOException {
		this.userdao = new UserDaoImpl();
	}

	@Test
	public void testReader() throws IOException {
		
		ExcelReader reader = new ExcelReader();
		List<User> users = reader.readFrom("src/test/resources/testReader.xlsx");

		assertEquals(MARIA, users.get(0).toString());

		assertEquals(PEPE, users.get(1).toString());
	}
}
