package war;

import org.apache.wicket.protocol.http.WebSession;
import org.apache.wicket.request.Request;

public final class MySession extends WebSession {
    private Object myObject;

    public MySession (Request request) {
        super(request);
    }

    public final Object getMyObject() {
        return myObject;
    }

    public final void setMyObject(Object myObject) {
        this.myObject = myObject;
    }


}
