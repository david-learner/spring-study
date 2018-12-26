package hardlearner.springStudy.user.dao;

import hardlearner.springStudy.user.domain.User;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDao {

    private ConnectionMaker connectionMaker;
    private JdbcContext context;
    private DataSource dataSource;

    // DI - 생성자 주입
    public UserDao(ConnectionMaker connectionMaker) {
        this.connectionMaker = connectionMaker;
    }

    // 의존관계검색 - 스스로 컨테이너에게 요청하는 방법
//    public UserDao() {
//        DaoFactory daoFactory = new DaoFactory();
//        this.connectionMaker = daoFactory.connectionMaker();
//    }

    public UserDao() {
        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(DaoFactory.class);
        this.connectionMaker = context.getBean("connectionMaker", ConnectionMaker.class);
    }

    public void setDataSource(DataSource dataSource) {
        context = new JdbcContext();
        context.setDatasource(dataSource);
        this.dataSource = dataSource;
    }

    public void add(User user) throws ClassNotFoundException, SQLException {
//        context.workWithStatementStrategy(c -> {
//                PreparedStatement ps = c.prepareStatement(
//                        "insert into users(id, name, password) values(?,?,?)"
//                );
        context.workWithStatementStrategy(
                new StatementStrategy() {
                    @Override
                    public PreparedStatement makePreparedStatement(Connection c) throws SQLException {
                        PreparedStatement ps = c.prepareStatement("insert into users(id, name, password) values(?,?,?)");

                        ps.setString(1, user.getId());
                        ps.setString(2, user.getName());
                        ps.setString(3, user.getPassword());

                        return ps;
                    }
                }

        );


    }

    public void deleteAll() throws SQLException {
        this.context.executeSql("delete from users");
    }

    public User get(String id) throws ClassNotFoundException, SQLException {
        Connection c = connectionMaker.makeConnection();

        PreparedStatement ps = c.prepareStatement(
                "select * from users where id = ?"
        );

        ps.setString(1, id);

        ResultSet rs = ps.executeQuery();
        rs.next();
        User user = new User();
        user.setId(rs.getString("id"));
        user.setName(rs.getString("name"));
        user.setPassword(rs.getString("password"));

        rs.close();
        ps.close();
        c.close();

        return user;
    }
}