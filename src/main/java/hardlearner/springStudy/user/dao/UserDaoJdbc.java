package hardlearner.springStudy.user.dao;

import com.mysql.jdbc.MysqlErrorNumbers;
import hardlearner.springStudy.user.domain.User;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class UserDaoJdbc implements UserDao {
    private ConnectionMaker connectionMaker;
    private JdbcTemplate jdbcTemplate;
    private RowMapper<User> userMapper =
            new RowMapper<User>() {
                @Override
                public User mapRow(ResultSet rs, int rowNum) throws SQLException {
                    User user = new User();
                    user.setId(rs.getString("id"));
                    user.setName(rs.getString("name"));
                    user.setPassword(rs.getString("password"));
                    return user;
                }
            };

    // DI - 생성자 주입
    public UserDaoJdbc(ConnectionMaker connectionMaker) {
        this.connectionMaker = connectionMaker;
    }

    // 의존관계검색 - 스스로 컨테이너에게 요청하는 방법
//    public UserDaoJdbc() {
//        DaoFactory daoFactory = new DaoFactory();
//        this.connectionMaker = daoFactory.connectionMaker();
//    }

    public UserDaoJdbc() {
        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(DaoFactory.class);
        this.connectionMaker = context.getBean("connectionMaker", ConnectionMaker.class);
    }

    public void setDataSource(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public void add(User user) throws DuplicateKeyException {
        this.jdbcTemplate.update("INSERT INTO USERS(id, name, password) values(?,?,?)", user.getId(), user.getName(), user.getPassword());
    }

    public void deleteAll() {
        this.jdbcTemplate.update("DELETE FROM USERS");
    }

    public User get(String id) {
        return this.jdbcTemplate.queryForObject("SELECT * FROM USERS WHERE id = ?", new Object[]{id}, this.userMapper);
    }

    public int getCount() {
        return this.jdbcTemplate.queryForObject("SELECT COUNT(*) FROM USERS", Integer.class);
    }

    public List<User> getAll() {
        return this.jdbcTemplate.query("select * from users order by id", this.userMapper);
    }
}