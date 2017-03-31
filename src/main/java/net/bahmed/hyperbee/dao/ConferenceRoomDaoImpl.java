package net.bahmed.hyperbee.dao;

import net.bahmed.hyperbee.domain.ConferenceRoom;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

/**
 * @author rumman
 * @since 11/27/16
 */
@Repository
public class ConferenceRoomDaoImpl implements ConferenceRoomDao {

    @PersistenceContext
    private EntityManager em;

    @Override
    @Transactional
    public ConferenceRoom save(ConferenceRoom conferenceRoom) {
        if (conferenceRoom.isNew()) {
            em.persist(conferenceRoom);
            em.flush();
        } else {
            conferenceRoom = em.merge(conferenceRoom);
        }

        return conferenceRoom;
    }

    @Override
    public ConferenceRoom findById(int conferenceRoomId) {
        return em.find(ConferenceRoom.class, conferenceRoomId);
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<ConferenceRoom> findAll() {
        return em.createNamedQuery("ConferenceRoom.findAllRoom", ConferenceRoom.class)
                .getResultList();
    }

    @Override
    @Transactional
    public void delete(int conferenceRoomId) {
        ConferenceRoom attachedConferenceRoom = em.getReference(ConferenceRoom.class, conferenceRoomId);
        em.remove(attachedConferenceRoom);
    }
}