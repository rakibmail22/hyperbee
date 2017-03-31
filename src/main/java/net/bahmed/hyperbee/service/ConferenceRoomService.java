package net.bahmed.hyperbee.service;

import net.bahmed.hyperbee.domain.ConferenceRoom;

import java.util.List;

/**
 * @author rumman
 * @since 11/27/16
 */
public interface ConferenceRoomService {

    void saveConferenceRoom(ConferenceRoom conferenceRoom);

    ConferenceRoom findConferenceRoomById(int conferenceRoomId);

    List<ConferenceRoom> findAllConferenceRoom();

    void delete(int conferenceRoomId);

}