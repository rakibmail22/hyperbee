package net.bahmed.hyperbee.dao;

import net.bahmed.hyperbee.domain.Reservation;
import net.bahmed.hyperbee.domain.enums.ReservationStatus;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

/**
 * @author rumman
 * @since 11/29/16
 */
@Repository
public class ReservationDaoImpl implements ReservationDao {

    private static final String RESERVATION_ALL_QUERY = "FROM Reservation";
    private static final String RESERVATION_ACTIVE_BY_RANGE_QUERY = "SELECT reservation FROM Reservation reservation" +
            " where reservation.reservationStatus =:status ORDER BY reservation.id DESC";

    @PersistenceContext
    private EntityManager em;

    @Override
    @Transactional
    public Reservation saveOrUpdate(Reservation reservation) {
        if (reservation.isNew()) {
            em.persist(reservation);
            em.flush();
        } else {
            reservation = em.merge(reservation);
        }

        return reservation;
    }

    @Override
    public Reservation findById(int reservationId) {
        return em.find(Reservation.class, reservationId);
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Reservation> findAll() {
        return em.createQuery(RESERVATION_ALL_QUERY).getResultList();
    }

    @Override
    @Transactional
    public void delete(int reservationId) {
        Reservation attachedReservation = em.getReference(Reservation.class, reservationId);
        em.remove(attachedReservation);
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Reservation> findLatestReservation(int range) {

        return em.createQuery(RESERVATION_ACTIVE_BY_RANGE_QUERY, Reservation.class)
                .setParameter("status", ReservationStatus.APPROVED)
                .setMaxResults(range)
                .getResultList();
    }
}
