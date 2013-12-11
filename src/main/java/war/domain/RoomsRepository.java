package war.domain;

import war.WicketApplication;

import javax.persistence.EntityManager;

public class RoomsRepository {
    private EntityManager entityManager = WicketApplication.emf.createEntityManager();

    public Rooms loadById(int id){
        return entityManager.find(Rooms.class, id);
    }
}

