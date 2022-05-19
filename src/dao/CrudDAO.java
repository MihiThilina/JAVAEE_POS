package dao;

import com.sun.xml.internal.bind.v2.model.core.ID;

import javax.json.JsonArrayBuilder;
import javax.json.JsonObjectBuilder;
import java.sql.SQLException;
import java.util.List;

public interface CrudDAO<T,Id> extends SuperDAO {

    boolean add(T t ) throws SQLException, ClassNotFoundException;
    boolean delete(ID id) throws SQLException, ClassNotFoundException;
    boolean update(T t) throws SQLException, ClassNotFoundException;
    JsonObjectBuilder search(ID id) throws SQLException, ClassNotFoundException;
    JsonArrayBuilder getAll() throws SQLException;
}

