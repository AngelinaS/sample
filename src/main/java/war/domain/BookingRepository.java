package war.domain;

import war.WicketApplication;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import java.util.Date;
import java.util.List;

public class BookingRepository {

    private EntityManager entityManager = WicketApplication.emf.createEntityManager();

    public List<Booking> loadByUserId(User userid) {
        List<Booking> result = null;
        try{
            result = entityManager.createQuery("select B from Booking B WHERE B.user = :uid", Booking.class)
                    .setParameter("uid", userid)
                    .getResultList();
        }catch (NoResultException nre){
            throw nre;
        }
        return result;
    }
    public Booking addBooking (Date dateIn, Date dateOut, Date bookingDate, Integer roomsq, User user, Rooms rooms ) {
        entityManager.getTransaction().begin();
        Booking booking = new Booking();
        booking.setRooms(rooms);
        booking.setUser(user);
        booking.setRoomsq(roomsq);
        booking.setBookingDate(bookingDate);
        booking.setCheckIn(dateIn);
        booking.setCheckOut(dateOut);
        entityManager.persist(booking);
        entityManager.getTransaction().commit();
        return booking;
    }
}
