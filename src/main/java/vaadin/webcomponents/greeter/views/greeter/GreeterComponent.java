package vaadin.webcomponents.greeter.views.greeter;

import com.vaadin.flow.component.AttachEvent;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.dependency.JsModule;
import com.vaadin.flow.component.dependency.NpmPackage;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.IFrame;
import com.vaadin.flow.router.Route;

import elemental.json.JsonValue;
import elemental.json.impl.JreJsonNull;
import lombok.extern.log4j.Log4j2;
import vaadin.webcomponents.greeter.views.main.MainView;

/*
//@Route(value = "greeter/", layout = MainView.class)
@CssImport("./styles/iFrames.css")
// @JsModule("./js/iFrames.js")
@NpmPackage(value="postmate", version="1.5.2")
@Log4j2
public class GreeterComponent extends Div  {

	private static final long serialVersionUID = -3500209011750166789L;

	private JsonValue handshake;

	public GreeterComponent(){
		super();
		var id = "greeter";
		var url = "https://greeter.cfapps.io/greeter/";
		var ctx = "/greeter";

		setId(id);
		var page = UI.getCurrent().getPage();
		var pendingResult = page.executeJs("window.portal.createFramedApp($0, $1, $2)", getId().get(), url, ctx);
		pendingResult.then(
			this::setHandshake, 
			error -> log.error(error));
		
		/*
		super("//greeter.cfapps.io/greeter");
		setId("greeter");
		addClassName("appFrame");
		setSandbox(SandboxType.ALLOW_FORMS, SandboxType.ALLOW_SCRIPTS, SandboxType.ALLOW_SAME_ORIGIN, SandboxType.ALLOW_TOP_NAVIGATION);
		getElement().setAttribute("seamless", "seamless");
		*/
/*	}

	private void setHandshake(JsonValue handshake){
		this.handshake = handshake;
		log.info(handshake);
	}



}
*/