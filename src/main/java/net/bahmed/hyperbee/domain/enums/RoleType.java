package net.bahmed.hyperbee.domain.enums;

import static net.bahmed.hyperbee.utils.constant.Constant.ADMIN_ROLE;
import static net.bahmed.hyperbee.utils.constant.Constant.USER_ROLE;

/**
 * @author bashir
 * @author rayed
 * @since 11/21/16
 */
public enum RoleType {

    USER(USER_ROLE), ADMIN(ADMIN_ROLE);

    private String role;

    RoleType(String role) {
        this.role = role;
    }

    public String getRole() {

        return role;
    }
}
