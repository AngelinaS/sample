package war.domain;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
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
//    private int userID;
//    private int hotelID;
//    private int roomsID;


    public int getBookingID() {
        return bookingID;
    }

    public void setBookingID(int bookingID) {
        this.bookingID = bookingID;
    }

    public Date getChekIn() {
        return checkIn;
    }

    public void setChekIn(Date chekIn) {
        this.checkIn = chekIn;
    }

    public Date getChekOut() {
        return checkOut;
    }

    public void setChekOut(Date chekOut) {
        this.checkOut = chekOut;
    }

    public Date getBookingDate() {
        return bookingDate;
    }

    public void setBookingDate(Date bookingDate) {
        this.bookingDate = bookingDate;
    }
}
