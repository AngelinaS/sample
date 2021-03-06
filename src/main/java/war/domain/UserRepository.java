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

    public User changeData(String name, String surname, String email , Integer id){
        User user = entityManager.find(User.class, id);
        entityManager.getTransaction().begin();
        user.setName(name);
        user.setSurname(surname);
        user.setEmail(email);
        entityManager.getTransaction().commit();
        entityManager.refresh(user);
        return user;
    }

    public User changePass (Integer id, String password){
        User user = entityManager.find(User.class, id);
        entityManager.getTransaction().begin();
        user.setPassword(password);
        entityManager.getTransaction().commit();
        entityManager.refresh(user);
        return user;
    }

    public User deleteAccount(Integer id){
        User user = entityManager.find(User.class, id);
        entityManager.getTransaction().begin();
        entityManager.remove(user);
        entityManager.getTransaction().commit();
        return  null;
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
