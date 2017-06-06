package participants;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import akka.event.slf4j.Logger;
import persistence.User;

@RestController
public class APIController {

//	private static final Logger LOG = LoggerFactory.getLogger(APIController.class);
//
//    @Autowired
//    private UserRepository repository;
//
//    @PostMapping("/user")
//    public ResponseEntity<User> getParticipantInfo(
//            @RequestBody AccessInfo info) {
//        LOG.info("Email trying to log-in:  " + info.getEmail());
//        User participant = repository.findUserByEmailAndPassword(info.getEmail() ,info.getPassword());
//
//        if(participant != null)
//            return new ResponseEntity<>(participant,
//                    HttpStatus.OK);
//        else
//            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//    }
}
