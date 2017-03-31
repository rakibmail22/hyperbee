package net.bahmed.hyperbee.dao;

import net.bahmed.hyperbee.domain.User;
import net.bahmed.hyperbee.domain.enums.DisplayStatus;

import java.util.List;

/**
 * @author rayed
 * @since 11/22/16 10:49 AM
 */
public interface UserDao {

    public User createUser(User user);

    public User findById(int id);

    public User findByUsername(String username);

    public User findByUsernameAndPassword(User user);

    public User findByUsernameOrEmail(String username, String email);

    public List<User> findAll();

    public List<User> findActiveUser();

    void inactivate(int userId);

    void activate(int userId);

    int findByDisplayStatus(DisplayStatus status);
}
