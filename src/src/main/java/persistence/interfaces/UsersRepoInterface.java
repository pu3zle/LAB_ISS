package persistence.interfaces;

import models.User;

public interface UsersRepoInterface extends Repository<Integer, User> {
    User findOneByUsername(String username);
}
