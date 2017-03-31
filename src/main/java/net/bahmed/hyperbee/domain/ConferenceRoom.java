package net.bahmed.hyperbee.domain;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

/**
 * @author bashir
 * @author rumman
 * @author rayed
 * @author azim
 * @author zoha
 * @since 11/21/16
 */

@NamedQuery(name = "ConferenceRoom.findAllRoom",
        query = "SELECT room FROM ConferenceRoom room ORDER BY room.id")
@Entity
@Table(name = "conference_room")
public class ConferenceRoom implements Serializable {

    private static final long serialVersionUID = 1;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String title;

    private int capacity;

    @OneToMany(mappedBy = "conferenceRoom", cascade = {CascadeType.ALL})
    private List<Reservation> reservationList;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public List<Reservation> getReservationList() {
        return reservationList;
    }

    public void setReservationList(List<Reservation> reservationList) {
        this.reservationList = reservationList;
    }

    public boolean isNew() {
        return id == 0;
    }
}
