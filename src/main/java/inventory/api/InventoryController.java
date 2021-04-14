package inventory.api;

import inventory.dao.InventoryDao;
import inventory.model.Inventory;
import inventory.service.InventoryService;
import inventory.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@RestController
public class InventoryController {
    
    @Autowired
    private InventoryService inventoryService;
    
    @Autowired
    private InventoryDao inventoryDao;
    

    
    //GET all inventory items
    @RequestMapping("/inventory")
    public ResponseEntity<List<Inventory>> getAllInventory(){
        List<Inventory> list = inventoryService.getAllInventory();
        return new ResponseEntity<List<Inventory>>(list, HttpStatus.OK);
    }

    //GET single inventory Item
    @RequestMapping("/inventory/{id}")
    public ResponseEntity<Inventory> getInventoryById(@PathVariable long id){
        Inventory item = inventoryService.getById(id);
        return new ResponseEntity<Inventory>(item, HttpStatus.OK);
    }


   //POST new inventory item
   @RequestMapping(method=RequestMethod.POST, value="/inventory")
   public ResponseEntity<Void> addInventory(@RequestBody Inventory item, UriComponentsBuilder builder) {
        boolean exist = inventoryService.addInventory(item);
        if(!exist){return new ResponseEntity<Void>(HttpStatus.CONFLICT);}
        HttpHeaders headers  = new HttpHeaders();
        headers.setLocation(builder.path("inventory/{id}").buildAndExpand(item.getId()).toUri());
        return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
   }

    //UPDATE new inventory item
    @RequestMapping(method=RequestMethod.PUT, value="/inventory/{id}")
    public ResponseEntity<Inventory> updateInventory(@RequestBody Inventory item, @PathVariable long id ) {
        inventoryService.updateInventory(item, id);
        return new ResponseEntity<Inventory>(item, HttpStatus.OK);
    }

    //DELETE inventory item
    @RequestMapping(method=RequestMethod.DELETE, value="/inventory/{id}")
    public ResponseEntity<Void> deleteInventory(@PathVariable long id ) {
        inventoryService.deleteInventory(id);
        return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
    }


}
