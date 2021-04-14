package inventory.dao;

import inventory.model.Inventory;
import org.springframework.stereotype.Repository;
import javax.persistence.*;
import javax.transaction.Transactional;
import java.util.List;


@Repository
public class InventoryDao implements DAO<Inventory>{
    
    
    @PersistenceContext
    private EntityManager entityManager;
    
    
    @Override
    public boolean exists(String b, String m, int p) {
        String jpqlQuery = "FROM Inventory as i WHERE i.brand LIKE :brand AND i.model LIKE :model AND i.price LIKE :price";
        int count = entityManager.createQuery(jpqlQuery).setParameter("brand", b).setParameter("model", m).setParameter("price", p).getResultList().size();
        return count > 0;
    }
    
    @Override
    public Inventory getById(long id) {
        return entityManager.find(Inventory.class, id);
    }
    
    @Override
    public List<Inventory> getAll() {
        String jpqlString = "FROM Inventory";
        TypedQuery<Inventory> query = entityManager.createQuery(jpqlString, Inventory.class);
        return query.getResultList();
    }
    
    
    @Override
    @Transactional
    public void create(Inventory item) {
        entityManager.persist(item);
    }
    
   
    @Override
    @Transactional
    public void update(Inventory item, long id) {
        Inventory itemToUpdate = getById(id);
        itemToUpdate.setBrand(item.getBrand());
        itemToUpdate.setModel(item.getModel());
        itemToUpdate.setPrice(item.getPrice());
        entityManager.flush();
    }
    
    @Override
    @Transactional
    public void delete(long id) {
        Inventory itemToDelete = getById(id);
        entityManager.remove(itemToDelete);
    }
}
