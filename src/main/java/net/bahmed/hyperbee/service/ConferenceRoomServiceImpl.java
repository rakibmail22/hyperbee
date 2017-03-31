package net.bahmed.hyperbee.service;

import net.bahmed.hyperbee.domain.ConferenceRoom;
import net.bahmed.hyperbee.web.helper.ReservationHelper;
import net.bahmed.hyperbee.dao.ConferenceRoomDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author rumman
 * @since 11/27/16
 */
@Service
public class ConferenceRoomServiceImpl implements ConferenceRoomService {

    @Autowired
    private ConferenceRoomDao conferenceRoomDao;

    @Autowired
    private ReservationHelper reservationHelper;

    @Override
    @Transactional
    public void saveConferenceRoom(ConferenceRoom conferenceRoom) {
        conferenceRoomDao.save(conferenceRoom);
        reservationHelper.persistInSession();
    }

    @Override
    public ConferenceRoom findConferenceRoomById(int conferenceRoomId) {
        return conferenceRoomDao.findById(conferenceRoomId);
    }

    @Override
    public List<ConferenceRoom> findAllConferenceRoom() {
        return conferenceRoomDao.findAll();
    }

    @Override
    @Transactional
    public void delete(int conferenceRoomId) {
        conferenceRoomDao.delete(conferenceRoomId);
        reservationHelper.persistInSession();
    }
}
