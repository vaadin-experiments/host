package vaadin.webcomponents.greeter.views.bookstore;

import com.vaadin.flow.component.AttachEvent;
import com.vaadin.flow.component.Tag;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.dependency.JsModule;
import com.vaadin.flow.component.dependency.NpmPackage;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.IFrame;
import com.vaadin.flow.dom.Element;
import com.vaadin.flow.router.BeforeEvent;
import com.vaadin.flow.router.HasUrlParameter;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.WildcardParameter;

import lombok.extern.log4j.Log4j2;
import vaadin.webcomponents.greeter.views.main.MainView;

/*
@Route(value = "bookstore/", layout = MainView.class)
@CssImport("./styles/iFrames.css")
@NpmPackage(value="postmate", version="1.5.2")
@Log4j2
public class BookstoreComponent extends Div implements HasUrlParameter<String>{

	private static final long serialVersionUID = 7267997802026059489L;

	public BookstoreComponent(){
		super();
		var id = "bookstore";
		setId(id);
		var page = UI.getCurrent().getPage();
		//var pendingResult = page.executeJs("window.createFramedApp($0, $1)", getId().get(), "https://book-store.cfapps.io/bookstore/");
		var pendingResult = page.executeJs("window.portal.createFramedApp($0, $1, $2)", getId().get(), "http://localhost:8080/bookstore/", "/bookstore");
		pendingResult.then(
			result -> log.info(result), 
			error -> log.error(error));
		/*
		super("//book-store.cfapps.io/bookstore");
		setId("bookstore");
		addClassName("appFrame");
		setSandbox(SandboxType.ALLOW_FORMS, SandboxType.ALLOW_SCRIPTS, SandboxType.ALLOW_SAME_ORIGIN, SandboxType.ALLOW_TOP_NAVIGATION, SandboxType.ALLOW_TOP_NAVIGATION_BY_USER_ACTIVATION);
		getElement().setAttribute("seamless", "seamless");
		*/
/*	}

	@Override
    protected void onAttach(AttachEvent attachEvent) {
		super.onAttach(attachEvent);
		setWidth("100%");
		//TODO
	}
	
	@Override
    public void setParameter(BeforeEvent event,
            @WildcardParameter String parameter) {
				log.info("Parameter: {}", parameter);
				this.getChildren().filter(IFrame.class::isInstance).forEach(iFrame -> log.info(iFrame));
    }

}
*/