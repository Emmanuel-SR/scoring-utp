package interfaces;

import java.io.Serializable;

public interface ICrudDao<T, ID extends Serializable> {

    boolean insert(T resourse);

    boolean update(T resourse);

    T get(ID primaryKey);

    Iterable<T> all();

    boolean delete(ID primaryKey);
    
    boolean exists(T resource);
}
