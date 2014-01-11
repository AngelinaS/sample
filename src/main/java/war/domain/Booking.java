package war.domain;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "booking")
public class Booking {

    @Id
    @GeneratedValue(generator="increment")
    @GenericGenerator(name="increment", strategy = "increment")
    private int bookingID;
    private Date checkIn;
    private Date checkOut;
    private Date bookingDate;
    private Integer roomsq;
    @ManyToOne (fetch = FetchType.LAZY)
    @JoinColumn(name = "USERID", nullable = false, updatable = false, insertable = true)
    private User user;

    @ManyToOne (fetch = FetchType.LAZY)
    @JoinColumn(name = "ROOMSID")
    private Rooms rooms;

    public Date getCheckOut() {
        return checkOut;
    }

    public void setCheckOut(Date checkOut) {
        this.checkOut = checkOut;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Date getCheckIn() {
        return checkIn;
    }

    public void setCheckIn(Date checkIn) {
        this.checkIn = checkIn;
    }

    public int getBookingID() {
        return bookingID;
    }

    public void setBookingID(int bookingID) {
        this.bookingID = bookingID;
    }

    public Date getBookingDate() {
        return bookingDate;
    }

    public void setBookingDate(Date bookingDate) {
        this.bookingDate = bookingDate;
    }


    public Rooms getRooms() {
        return rooms;
    }

    public void setRooms(Rooms rooms) {
        this.rooms = rooms;
    }

    public Integer getRoomsq() {
        return roomsq;
    }

    public void setRoomsq(Integer roomsq) {
        this.roomsq = roomsq;
    }
}
