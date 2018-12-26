package hardlearner.springStudy.user.dao;

import hardlearner.springStudy.user.domain.User;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import java.sql.SQLException;

@RunWith(SpringRunner.class)
@ContextConfiguration(locations = "/applicationContext.xml")
public class UserDaoTest {

    @Autowired
    private ApplicationContext context;
    private UserDao dao;

    @Before
    public void setUp() throws SQLException {
        dao = context.getBean("userDao", UserDao.class);
        dao.deleteAll();
    }

    @Test
    public void add() throws SQLException, ClassNotFoundException {
        User user = new User();
        user.setId("whiteship");
        user.setName("keesun");
        user.setPassword("married");

        dao.add(user);

        System.out.println(user.getId() + " 등록성공");

        User user2 = dao.get(user.getId());
        System.out.println(user2.getName());
        System.out.println(user2.getPassword());

        System.out.println(user2.getId() + " 조회 성공");
    }
}
