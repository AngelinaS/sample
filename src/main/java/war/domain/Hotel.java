package war.domain;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.security.PrivateKey;

//@Entity
//@Table(name = "HOTELS")
public class Hotel {

    @Id
    @Column(name = "HOTELID")
    @GeneratedValue(generator="increment")
    @GenericGenerator(name="increment", strategy = "increment")
    private int hotelId;

    @Column(name = "NAME")
    private String name;

    private String country;
    private String city;
    private String address;
    private Long telephone;
    private Integer stars;
    private Integer roomsQuantity;
    private Object image;
    private String description;
    private Boolean pets;
    private String services;
    private Boolean parking;
    private Boolean wifi;
    private String foodAndDrink;
    private String activities;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getHotelId() {
        return hotelId;
    }

    public void setHotelId(int hotelId) {
        this.hotelId = hotelId;
    }
}
