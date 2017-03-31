package net.bahmed.hyperbee.dao;

import net.bahmed.hyperbee.domain.Profile;
import net.bahmed.hyperbee.utils.constant.Messages;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * @author duity
 * @since 11/22/16.
 */
@Repository
public class ProfileDaoImpl implements ProfileDao {

    @PersistenceContext
    private EntityManager em;

    @Transactional
    public String save(Profile profile) {
        em.persist(profile);

        return Messages.PROFILE_SAVE_MESSAGE;
    }
}
