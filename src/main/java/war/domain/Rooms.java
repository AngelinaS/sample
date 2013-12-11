package war.domain;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.List;

@Entity
@Table( name = "rooms")
public class Rooms {

    @Id
    @GeneratedValue(generator="increment")
    @GenericGenerator(name="increment", strategy = "increment")
    public int roomsID;

    public String roomsType;
    public Integer roomsQuantity;
    public Double price;
    public Integer rooms;
    public Integer onePerBedQuant;
    public Integer twoPerBedQuant;

    @OneToMany(mappedBy = "rooms")
    private List<Booking> bookings;

    @ManyToOne
    @JoinColumn(name = "HOTELID")
    private Hotel hotel;

    public int getRoomsID() {
        return roomsID;
    }

    public void setRoomsID(int roomsID) {
        this.roomsID = roomsID;
    }

    public String getRoomType() {
        return roomsType;
    }

    public void setRoomType(String roomType) {
        this.roomsType = roomType;
    }

    public Integer getRoomsQuantity() {
        return roomsQuantity;
    }

    public void setRoomsQuantity(Integer roomsQuantity) {
        this.roomsQuantity = roomsQuantity;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Integer getRooms() {
        return rooms;
    }

    public void setRooms(Integer rooms) {
        this.rooms = rooms;
    }

    public Integer getOnePerBedQuant() {
        return onePerBedQuant;
    }

    public void setOnePerBedQuant(Integer onePerBedQuant) {
        this.onePerBedQuant = onePerBedQuant;
    }

    public Integer getTwoPerBedQuant() {
        return twoPerBedQuant;
    }

    public void setTwoPerBedQuant(Integer twoPerBedQuant) {
        this.twoPerBedQuant = twoPerBedQuant;
    }

    public String getRoomsType() {
        return roomsType;
    }

    public void setRoomsType(String roomsType) {
        this.roomsType = roomsType;
    }

    public List<Booking> getBookings() {
        return bookings;
    }

    public void setBookings(List<Booking> bookings) {
        this.bookings = bookings;
    }

    public Hotel getHotel() {
        return hotel;
    }

    public void setHotel(Hotel hotel) {
        this.hotel = hotel;
    }
}
