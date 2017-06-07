package participants;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import persistence.User;
import repositories.UserRepository;


@RestController
public class APIController {

	private static final Logger LOG = LoggerFactory.getLogger(APIController.class);

    @Autowired
    private UserRepository repository;

    @PostMapping("/user")
    public ResponseEntity<User> getGivenCredentials(
            @RequestBody Credentials credentials) {
        LOG.info("Logging in, user:  " + credentials.getEmail());
        
        User userLogged = repository.findUserByEmailAndPassword(credentials.getEmail(),
        		credentials.getPassword());

        if(userLogged != null){
        	//The introduced credentials are correct
            return new ResponseEntity<>(userLogged, HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
