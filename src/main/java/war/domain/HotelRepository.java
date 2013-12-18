package war.domain;

import war.WicketApplication;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import java.util.List;

public class HotelRepository {

    private EntityManager entityManager = WicketApplication.emf.createEntityManager();

    public List<Hotel> loadByName(String name){
        return entityManager.createQuery("select h from Hotel h where h.name = :name", Hotel.class)
                .setParameter("name", name)
                .getResultList();
    }

    public List<Hotel> loadHotels() {
        List<Hotel> result = null;
        try{
         result = entityManager.createQuery("select h from Hotel h order by h.name", Hotel.class)
                 .getResultList();
        }catch (NoResultException nre){
        }
        return result;
    }

//    return entityManager.createQuery("select h from Hotel h where h.bookings.date = :date", Hotel.class)
//            .setParameter("date", date)
//    .getResultList();
}
