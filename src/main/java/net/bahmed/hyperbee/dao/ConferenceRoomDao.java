package net.bahmed.hyperbee.dao;

import net.bahmed.hyperbee.domain.ConferenceRoom;

import java.util.List;

/**
 * @author rumman
 * @since 11/27/16
 */
public interface ConferenceRoomDao {

    ConferenceRoom save(ConferenceRoom conferenceRoom);

    ConferenceRoom findById(int conferenceRoomId);

    List<ConferenceRoom> findAll();

    void delete(int conferenceRoomId);
}
