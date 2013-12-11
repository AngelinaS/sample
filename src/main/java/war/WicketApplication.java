package war;

import org.apache.wicket.protocol.http.WebApplication;
import war.domain.BookingRepository;
import war.domain.HotelRepository;
import war.domain.RoomsRepository;
import war.domain.UserRepository;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class WicketApplication extends WebApplication {

    public static EntityManagerFactory emf = Persistence.createEntityManagerFactory("booking");

    UserRepository userRepository= new UserRepository();
    UserRepository repository = new UserRepository();

    public WicketApplication() {

        repository.loadById(1);
        userRepository.loadById(2);
    }

	public Class<HomePage> getHomePage() {
		return HomePage.class;
	}

}
