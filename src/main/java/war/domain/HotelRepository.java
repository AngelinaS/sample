package war.domain;

import war.WicketApplication;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;

public class HotelRepository {

    private EntityManager entityManager = WicketApplication.emf.createEntityManager();

    public List<Hotel> loadByName(String name){
        return entityManager.createQuery("select h from Hotel h where h.name = :name", Hotel.class)
                .setParameter("name", name)
                .getResultList();
    }

//    return entityManager.createQuery("select h from Hotel h where h.bookings.date = :date", Hotel.class)
//            .setParameter("date", date)
//    .getResultList();
}
