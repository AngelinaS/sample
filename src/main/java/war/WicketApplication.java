package war;

import org.apache.wicket.protocol.http.WebApplication;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;

public class WicketApplication extends WebApplication {

    public static EntityManagerFactory emf;

    public WicketApplication() {
        emf = Persistence.createEntityManagerFactory("booking");
    }

	public Class<HomePage> getHomePage() {
		return HomePage.class;
	}

}
