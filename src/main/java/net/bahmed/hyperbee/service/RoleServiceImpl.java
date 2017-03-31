package net.bahmed.hyperbee.service;

import net.bahmed.hyperbee.domain.Role;
import net.bahmed.hyperbee.dao.RoleDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author rayed
 * @since 11/24/16 10:33 AM
 */
@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleDao roleDao;

    @Override
    public Role findRole(int id) {

        return roleDao.findRole(id);
    }
}
