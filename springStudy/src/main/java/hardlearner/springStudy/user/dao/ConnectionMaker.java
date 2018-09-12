package hardlearner.springStudy.user.dao;

import com.sun.org.apache.bcel.internal.generic.SALOAD;

import java.sql.Connection;
import java.sql.SQLException;

public interface ConnectionMaker {
    public Connection makeConnection() throws ClassNotFoundException, SQLException;
}
