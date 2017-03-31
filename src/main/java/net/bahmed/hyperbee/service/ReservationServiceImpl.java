package net.bahmed.hyperbee.service;

import net.bahmed.hyperbee.domain.Reservation;
import net.bahmed.hyperbee.dao.ReservationDao;
import net.bahmed.hyperbee.web.helper.ReservationHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author rumman
 * @since 11/29/16
 */
@Service
public class ReservationServiceImpl implements ReservationService {

    @Autowired
    private ReservationDao reservationDao;

    @Autowired
    private ReservationHelper reservationHelper;

    @Override
    public void saveReservation(Reservation reservation) {
        reservationDao.saveOrUpdate(reservation);
        reservationHelper.persistInSession();
    }

    @Override
    public Reservation findReservationById(int reservationId) {
        return reservationDao.findById(reservationId);
    }

    @Override
    public List<Reservation> findAllReservation() {
        return reservationDao.findAll();
    }

    @Override
    public void delete(int reservationId) {
        reservationDao.delete(reservationId);
        reservationHelper.persistInSession();
    }

    @Override
    public List<Reservation> findLatestReservation(int range) {
        return reservationDao.findLatestReservation(range);
    }
}
