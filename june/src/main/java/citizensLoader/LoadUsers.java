package citizensLoader;

import java.io.IOException;
import java.util.ArrayList;

import citizensLoader.factory.ExcelReader;
import persistence.User;
import persistence.UserDao;
import persistence.impl.UserDaoImpl;


public class LoadUsers {
	
	public static void main(String... args) {
		final LoadUsers runner = new LoadUsers();
		runner.run(args);
	}

	
	private void run(String... args) {
		System.out.println("#########\nCitizens Loader \n#########\n");
		
		ArrayList<User> users = new ArrayList<User>();
		ExcelReader reader = new ExcelReader();
		UserDao userdao = new UserDaoImpl();

		for (String file : args){
			users.clear();
			try {
				users = reader.readFrom(file);
				for(User us : users)  {
					userdao.saveUser(us);
					System.out.println("Saved " + us.toString());
				}
				System.out.println("Loaded " + users.size() + " users from " + file + "\n");
				
			}catch (IOException e){
				System.out.printf("\n[ERROR] The file %s could not be loaded\n\n", file);
			}
			catch(Exception e) {
				e.printStackTrace();
			}
		}
	}
}