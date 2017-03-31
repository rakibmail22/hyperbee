package net.bahmed.hyperbee.service;

import net.bahmed.hyperbee.dao.UserDao;
import net.bahmed.hyperbee.domain.Profile;
import net.bahmed.hyperbee.domain.User;
import net.bahmed.hyperbee.utils.constant.Messages;
import net.bahmed.hyperbee.web.helper.ImageUploader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * @author duity
 * @since 11/22/16.
 */
@Service
public class ProfileServiceImpl implements ProfileService {

    @PersistenceContext
    private EntityManager em;

    @Autowired
    private UserDao userDao;

    @Autowired
    private ImageUploader imageUploader;

    @Override
    public Profile findProfileById(int id) {
        return em.find(Profile.class, id);
    }

    @Override
    @Transactional
    public String saveProfileForUser(Profile profile, int userId) {
        User user = userDao.findById(userId);

        if (user.getProfile() == null) {
            user.setProfile(profile);
            em.persist(user);
            em.flush();
        } else {
            user.setProfile(profile);
            em.merge(user);
            em.flush();
        }

        return Messages.PROFILE_SAVE_MESSAGE;
    }
}
