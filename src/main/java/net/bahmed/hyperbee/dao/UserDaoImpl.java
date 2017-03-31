package net.bahmed.hyperbee.dao;

import net.bahmed.hyperbee.domain.User;
import net.bahmed.hyperbee.domain.enums.DisplayStatus;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.simple.SimpleLogger;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import java.util.List;

/**
 * @author rayed
 * @since 11/22/16 10:49 AM
 */
@Repository
public class UserDaoImpl implements UserDao {

    private static final Logger log = LogManager.getLogger(SimpleLogger.class);

    private static final String FIND_BY_USERNAME = "SELECT u FROM User u WHERE u.username = :username";

    private static final String FIND_BY_USRNAME_AND_EMAIL = "User.findByUsernameOrEmail";

    private static final String FIND_BY_USERNAME_AND_PASSWORD = "SELECT u FROM User u WHERE u.username = :username AND u.password = :password";

    private static final String FIND_ALL = "SELECT u FROM User u";

    private static final String FIND_USER_ACTIVE = "SELECT u FROM User u WHERE u.displayStatus = :status";

    private static final String UPDATE_STATUS_TO_ACTIVE = "UPDATE User u SET u.displayStatus = 'ACTIVE' WHERE id = :userId";

    private static final String UPDATE_STATUS_TO_INACTIVE = "UPDATE User u SET u.displayStatus = 'INACTIVE' WHERE id = :userId";

    private static final String FIND_BY_DISPLAY_STATUS = "SELECT u FROM User u WHERE u.displayStatus = :status";

    @PersistenceContext
    private EntityManager em;

    @Override
    @Transactional
    public User createUser(User user) {
        em.persist(user);
        em.flush();

        log.debug("User created: " + user.getUsername());

        return user;
    }

    @Override
    public User findById(int id) {

        return em.find(User.class, id);
    }

    @Override
    public User findByUsername(String username) {
        User user = null;

        try {
            user = em.createQuery(FIND_BY_USERNAME, User.class)
                    .setParameter("username", username)
                    .getSingleResult();
        } catch (NoResultException e) {
            log.debug("Find by username exception", e);
        }

        return user;
    }

    @Override
    public User findByUsernameOrEmail(String username, String email) {
        User user = null;

        try {
            user = em.createNamedQuery(FIND_BY_USRNAME_AND_EMAIL, User.class)
                    .setParameter("username", username)
                    .setParameter("email", email)
                    .getSingleResult();
        } catch (NoResultException e) {
            log.debug("Find by username or email exception", e);
        }

        return user;
    }

    @Override
    public User findByUsernameAndPassword(User user) {
        User retrievedUser = null;

        try {
            retrievedUser = em.createQuery(FIND_BY_USERNAME_AND_PASSWORD, User.class)
                    .setParameter("username", user.getUsername())
                    .setParameter("password", user.getPassword())
                    .getSingleResult();
        } catch (NoResultException e) {
            log.debug("Find by username and password exception", e);
        }

        return retrievedUser;
    }

    @Override
    public List<User> findAll() {

        return em.createQuery(FIND_ALL, User.class).getResultList();
    }

    @Override
    public List<User> findActiveUser() {

        return em.createQuery(FIND_USER_ACTIVE, User.class)
                .setParameter("status", DisplayStatus.ACTIVE)
                .getResultList();
    }

    @Override
    @Transactional
    public void inactivate(int userId) {
        em.createQuery(UPDATE_STATUS_TO_INACTIVE)
                .setParameter("userId", userId)
                .executeUpdate();
        em.flush();
    }

    @Override
    @Transactional
    public void activate(int userId) {
        em.createQuery(UPDATE_STATUS_TO_ACTIVE)
                .setParameter("userId", userId)
                .executeUpdate();
        em.flush();
    }

    @Override
    public int findByDisplayStatus(DisplayStatus status) {

        return em.createQuery(FIND_BY_DISPLAY_STATUS, User.class)
                .setParameter("status", status)
                .getResultList().size();
    }
}
