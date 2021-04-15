package inventory.service;

import inventory.dao.InventoryDao;
import inventory.model.Inventory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class InventoryService {
    
    
    @Autowired
    private InventoryDao inventoryDao;
    
    //GET all items
    public List<Inventory> getAllInventory() {
        return inventoryDao.getAll();
    }

    //GET one item
    public Inventory getById(long id){
        return inventoryDao.getById(id);
    }

    //CREATE
    public boolean addInventory(Inventory item) {
        System.out.println("addInventory Service method invoked");
//        if(inventoryDao.exists(item.getBrand(), item.getModel(), item.getPrice())){
//            System.out.println("Item already exists!");
//            return false;
//        }else{
            inventoryDao.create(item);
            return true;
       // }
    }

    //UPDATE
    public void updateInventory(Inventory item, long id) {
        inventoryDao.update(item, id);
    }

    //DELETE item
    public void deleteInventory(long id) {
        inventoryDao.delete(id);
    }
}
