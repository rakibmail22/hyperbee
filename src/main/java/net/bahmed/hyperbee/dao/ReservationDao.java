package net.bahmed.hyperbee.dao;

import net.bahmed.hyperbee.domain.Reservation;

import java.util.List;

/**
 * @author rumman
 * @since 11/29/16
 */
public interface ReservationDao {

    Reservation saveOrUpdate(Reservation reservation);

    Reservation findById(int reservationId);

    List<Reservation> findAll();

    void delete(int reservationId);

    List<Reservation> findLatestReservation(int range);
}
