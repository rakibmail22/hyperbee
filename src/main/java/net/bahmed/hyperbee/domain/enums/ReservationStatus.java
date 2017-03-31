package net.bahmed.hyperbee.domain.enums;

import net.bahmed.hyperbee.utils.constant.Constant;

/**
 * @author bashir
 * @author rumman
 * @since 11/21/16
 */
public enum ReservationStatus {

    APPROVED(Constant.APPROVED),
    REJECTED(Constant.REJECTED),
    PENDING(Constant.PENDING);

    private String status;

    ReservationStatus(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }
}
