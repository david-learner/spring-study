package hardlearner.springStudy.user.service;

import hardlearner.springStudy.user.dao.UserDao;
import hardlearner.springStudy.user.domain.User;

import java.util.List;

public class UserService {
    UserDao userDao;

    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }
}
