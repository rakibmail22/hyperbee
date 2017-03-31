package net.bahmed.hyperbee.web.security;

import net.bahmed.hyperbee.domain.Role;
import net.bahmed.hyperbee.domain.enums.RoleType;

import java.io.Serializable;
import java.util.List;

/**
 * @author rayed
 * @since 11/23/16 1:56 PM
 */
public class AuthUser implements Serializable {

    private static final long serialVersionUID = 1L;

    private int id;
    private String username;
    private List<Role> roleList;

    public AuthUser(int id, String username, List<Role> roleList) {
        this.id = id;
        this.username = username;
        this.roleList = roleList;
    }

    public AuthUser() {

    }

    public int getId() {

        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<Role> getRoleList() {

        return this.roleList;
    }

    public void setRoleList(List<Role> roleList) {
        this.roleList = roleList;
    }

    public String getUsername() {

        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public boolean isAdmin() {
        for (Role role : roleList) {
            if (role.getRoleType() == RoleType.ADMIN) {

                return true;
            }
        }

        return false;
    }
}
