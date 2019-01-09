package hardlearner.springStudy.user.dao;

import hardlearner.springStudy.user.domain.User;

public interface UserLevelUpgradePolicy {
    boolean canUpgradeLevel(User user);
    void upgradeLevel(User user);
}
