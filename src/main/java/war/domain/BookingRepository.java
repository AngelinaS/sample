package war.domain;

import war.WicketApplication;

import javax.persistence.EntityManager;
import java.util.List;

public class BookingRepository {

    private EntityManager entityManager = WicketApplication.emf.createEntityManager();

    public Booking loadById(int id){
        return entityManager.find(Booking.class, id);
    }
}
