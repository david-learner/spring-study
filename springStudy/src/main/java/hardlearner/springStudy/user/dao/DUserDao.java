package hardlearner.springStudy.user.dao;

import java.sql.Connection;
import java.sql.SQLException;

public class DUserDao extends UserDao {
    @Override
    public Connection getConnection() throws ClassNotFoundException, SQLException {
        // D햐사 DB Connection 생성코드
        return null;
    }
}
