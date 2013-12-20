package war.domain;

import war.WicketApplication;

import javax.persistence.EntityManager;

public class RoomsRepository {
    private EntityManager entityManager = WicketApplication.emf.createEntityManager();

    public Rooms loadById(int id){
        return entityManager.find(Rooms.class, id);
    }

    public Rooms addRooms (String roomtype, Integer quantrooms, Double price, Integer roomsinroom,
                           Integer foroneperdbed, Integer fortwoperbed) {
        entityManager.getTransaction().begin();
        Rooms rooms = new Rooms();
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
}

