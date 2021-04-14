package inventory.service;

import java.util.List;

import inventory.dao.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class UserService {
    
    @Autowired
    private UserDao userDao;
    
    public List<?> getUserDetails() {
        return userDao.getUserDetails();
    }
    
}
