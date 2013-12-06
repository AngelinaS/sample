package war;

import org.apache.wicket.protocol.http.WebApplication;
import war.domain.Hotel;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;

public class WicketApplication extends WebApplication {

	public WicketApplication() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("booking");
        EntityManager entityManager = emf.createEntityManager();

        entityManager.find(Hotel.class, 1);

        Hotel hotel = new Hotel();
        hotel.setName("My Favourite Hotel :)");

        entityManager.getTransaction().begin();
        entityManager.persist(hotel);  // Save new data
        // entityManager.merge(hotel);  // Update data
        entityManager.close();


        emf.close();
    }

	public Class<HomePage> getHomePage() {
		return HomePage.class;
	}

}
