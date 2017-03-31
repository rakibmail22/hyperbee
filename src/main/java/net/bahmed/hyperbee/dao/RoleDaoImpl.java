package net.bahmed.hyperbee.dao;

import net.bahmed.hyperbee.domain.Role;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * @author rayed
 * @since 11/24/16 11:16 AM
 */

@Repository
public class RoleDaoImpl implements RoleDao {

    @PersistenceContext
    private EntityManager em;

    @Override
    public Role findRole(int id) {

        return em.find(Role.class, id);
    }
}
