package war.domain;

import war.WicketApplication;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;

public class UserRepository {
    private EntityManager entityManager = WicketApplication.emf.createEntityManager();

    public User loadById(int id){
        return entityManager.find(User.class, id);
    }

    public User loadUserByLogin(String login) {
        User result = null;
        try{
            result = entityManager.createQuery("select u from User u where u.login = :login", User.class)
                    .setParameter("login", login)
                    .getSingleResult();
        }catch (NoResultException nre){

        }
        return result;
    }



    public User addUser (String login, String name, String surname, String password, String email ){
        entityManager.getTransaction().begin();
        User currentUser = new User();
        currentUser.setLogin(login);
        currentUser.setName(name);
        currentUser.setSurname(surname);
        currentUser.setPassword(password);
        currentUser.setEmail(email);
        entityManager.persist(currentUser);
        entityManager.getTransaction().commit();
        return currentUser;
    }
}
