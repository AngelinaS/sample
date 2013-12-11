package war;

import org.apache.wicket.guice.GuiceComponentInjector;
import org.apache.wicket.protocol.http.WebApplication;
import war.domain.BookingRepository;
import war.domain.HotelRepository;
import war.domain.RoomsRepository;
import war.domain.UserRepository;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class WicketApplication extends WebApplication {

    public static EntityManagerFactory emf;

    @Override
    protected void init()
    {
        super.init();
        emf = Persistence.createEntityManagerFactory("booking");
        getComponentInstantiationListeners().add(new GuiceComponentInjector(this));
    }

	public Class<HomePage> getHomePage() {
		return HomePage.class;
	}

}
