package net.bahmed.hyperbee.service;

import net.bahmed.hyperbee.domain.User;
import net.bahmed.hyperbee.domain.enums.DisplayStatus;

import java.util.List;

/**
 * @author rayed
 * @since 11/22/16 10:44 AM
 */
public interface UserService {

    public User createUser(User user);

    public User findById(int id);

    public User findByUsername(String username);

    public User findByUsernameOrEmail(String username, String email);

    public User findByUsernameAndPassword(User user);

    public List<User> findAll();

    public List<User> findActiveUsers();

    void inactivate(int userId);

    void activate(int userId);

    public int findByDisplayStatus(DisplayStatus status);
}
