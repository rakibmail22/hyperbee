package net.bahmed.hyperbee.service;

import net.bahmed.hyperbee.domain.Role;
import net.bahmed.hyperbee.dao.RoleDao;
import net.bahmed.hyperbee.dao.UserDao;
import net.bahmed.hyperbee.domain.User;
import net.bahmed.hyperbee.domain.enums.DisplayStatus;
import net.bahmed.hyperbee.utils.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * @author rayed
 * @since 11/22/16 10:49 AM
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Autowired
    private RoleDao roleDao;

    @Autowired
    private Utils utils;

    @Override
    @Transactional
    public User createUser(User user) {
        Role role = roleDao.findRole(2);

        List<Role> roleList = new ArrayList<Role>();
        roleList.add(role);

        String hashMd5 = utils.hashMd5(user.getPassword());

        user.setRoleList(roleList);
        user.setPassword(hashMd5);

        return userDao.createUser(user);
    }

    @Override
    public User findById(int id) {

        return userDao.findById(id);
    }

    @Override
    public User findByUsername(String username) {

        return userDao.findByUsername(username);
    }

    @Override
    public User findByUsernameOrEmail(String username, String email) {

        return userDao.findByUsernameOrEmail(username, email);
    }

    @Override
    public User findByUsernameAndPassword(User user) {
        String hashMd5 = utils.hashMd5(user.getPassword());
        user.setPassword(hashMd5);

        return userDao.findByUsernameAndPassword(user);
    }

    @Override
    public List<User> findAll() {

        return userDao.findAll();
    }

    @Override
    public List<User> findActiveUsers() {

        return userDao.findActiveUser();
    }

    @Override
    public void inactivate(int userId) {
        userDao.inactivate(userId);
    }

    @Override
    public void activate(int userId) {
        userDao.activate(userId);
    }

    @Override
    public int findByDisplayStatus(DisplayStatus status) {

        return userDao.findByDisplayStatus(status);
    }
}
