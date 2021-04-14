package inventory.dao;


import java.util.List;

public interface DAO<T>{
    
    boolean exists(String t, String m, int p);
    T getById(long id);
    List<T> getAll();
    void create(T t);
    void update(T t, long id);
    void delete(long id);
    
}
