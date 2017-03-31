package net.bahmed.hyperbee.domain;

import net.bahmed.hyperbee.domain.enums.RoleType;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

import static net.bahmed.hyperbee.utils.constant.Constant.USER_STATUS_ENUM;

/**
 * @author bashir
 * @author rayed
 * @author azim
 * @author zoha
 * @since 11/21/16
 */
@Entity
@Table(name = "role")
public class Role implements Serializable {

    private static final long serialVersionUID = 1;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "type", columnDefinition = USER_STATUS_ENUM)
    @Enumerated(EnumType.STRING)
    private RoleType roleType;

    @ManyToMany(mappedBy = "roleList")
    private List<User> userList;

    public Role() {
    }

    public int getId() {

        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public RoleType getRoleType() {

        return roleType;
    }

    public void setRoleType(RoleType roleType) {
        this.roleType = roleType;
    }

    public List<User> getUserList() {

        return userList;
    }

    public void setUserList(List<User> userList) {
        this.userList = userList;
    }
}
