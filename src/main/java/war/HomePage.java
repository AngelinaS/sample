package war;

import org.apache.wicket.PageParameters;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.form.AjaxSubmitLink;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.form.Button;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.model.IWrapModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.model.PropertyModel;

import java.security.PrivateKey;
import java.util.Date;

/**
 * Homepage
 */
public class HomePage extends WebPage {

    private Label label;
    private Label label2;
    private TextField name;
    private TextField sname;
    public HomePage() {
        Form form = new Form("form");
        name = new TextField("name", new Model(""));
        sname = new TextField("sname", new Model(""));
        form.add(name);
        form.add(sname);
        form.add(new Button("button"){
            @Override
        public void onSubmit(){
                String value = (String)name.getModelObject();
                String value2 = (String)sname.getModelObject();
                label.setModelObject(value);
                label2.setModelObject(value2);
                name.setModelObject("");
                sname.setModelObject("");
            }
        });
        add(form);
        add(label = new Label ("message", new Model("")));
        add(label2 = new Label ("message2", new Model("")));

    }

	// TODO Add any page properties or variables here

    /**
	 * Constructor that is invoked when page is invoked without a session.
	 * 
	 * @param parameters
	 *            Page parameters
	 */


        // Add the simplest type of label
        // TODO Add your page's components here
    }



