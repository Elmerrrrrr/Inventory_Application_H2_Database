package inventory.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import inventory.model.UserDetails;
import org.springframework.stereotype.Repository;


@Repository
public class UserDao {
    
    
    @PersistenceContext
    private EntityManager entityManager;
    
    
    public List<UserDetails> getUserDetails() {
        String queryString = "FROM UserDetails";
        TypedQuery<UserDetails> query = entityManager.createQuery(queryString, UserDetails.class);
        return query.getResultList();
    }
    
}