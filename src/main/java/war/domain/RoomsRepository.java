package war.domain;

import war.WicketApplication;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import java.util.List;

public class RoomsRepository {
    private EntityManager entityManager = WicketApplication.emf.createEntityManager();

    public Rooms loadById(int id){
        return entityManager.find(Rooms.class, id);
    }

    public Rooms addRooms (  Hotel hotel, String roomtype, Integer quantrooms, Double price, Integer roomsinroom,
                           Integer foroneperdbed, Integer fortwoperbed) {
        entityManager.getTransaction().begin();
        Rooms rooms = new Rooms();
        rooms.setHotel(hotel);
        rooms.setRoomsType(roomtype);
        rooms.setRoomsQuantity(quantrooms);
        rooms.setPrice(price);
        rooms.setRooms(roomsinroom);
        rooms.setOnePerBedQuant(foroneperdbed);
        rooms.setTwoPerBedQuant(fortwoperbed);

        entityManager.persist(rooms);
        entityManager.getTransaction().commit();
        return rooms;
    }

    public Rooms deleteRooms(Integer id){
        Rooms rooms = entityManager.find(Rooms.class, id);
        entityManager.getTransaction().begin();
        entityManager.remove(rooms);
        entityManager.getTransaction().commit();
        return  null;
    }

    public List<Rooms> loadRooms(Hotel hotelid) {
        List<Rooms> result = null;
        try{
            result = entityManager.createQuery("select R from Rooms R WHERE R.hotel = :hid", Rooms.class)
                    .setParameter("hid", hotelid)
                    .getResultList();
        }catch (NoResultException nre){
            throw nre;
        }
        return result;
    }
    public Rooms loadbyname(String name) {
        Rooms result = null;
        try{
            result = entityManager.createQuery("select R from Rooms R where R.roomsType = :name", Rooms.class)
                    .setParameter("name", name)
                    .getSingleResult();
        }catch (NoResultException nre){
            throw nre;
        }
        return result;
    }


}

