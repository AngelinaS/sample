package war.domain;

import war.WicketApplication;

import javax.persistence.EntityManager;

public class UserRepository {
    private EntityManager entityManager = WicketApplication.emf.createEntityManager();

    public User loadById(int id){
        return entityManager.find(User.class, id);
    }
}
