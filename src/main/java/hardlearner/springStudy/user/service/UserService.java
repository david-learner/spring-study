package hardlearner.springStudy.user.service;

import hardlearner.springStudy.user.domain.User;

public interface UserService {
    void add(User user);
    void upgradeLevels();
}
