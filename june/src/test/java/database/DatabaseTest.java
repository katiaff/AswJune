package database;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;

import persistence.Database;

public class DatabaseTest {

	@Test
	public void test() {
		assertNotNull(Database.getConnection());
	}
}
