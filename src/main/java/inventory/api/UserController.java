package inventory.api;

import java.util.List;

import inventory.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


@Controller
public class UserController {
    
    @Autowired
    private UserService userService;
    
    
    @RequestMapping(value = "/users", method = RequestMethod.GET)
    public ResponseEntity userDetails() {
        List<?> userDetails  = userService.getUserDetails();
        return new ResponseEntity(userDetails, HttpStatus.OK);
    }
    
}
