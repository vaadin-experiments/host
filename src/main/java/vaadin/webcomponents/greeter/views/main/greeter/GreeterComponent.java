package vaadin.webcomponents.greeter.views.main.greeter;

import com.vaadin.flow.component.AttachEvent;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.HasSize;
import com.vaadin.flow.component.Tag;
import com.vaadin.flow.router.Route;

import vaadin.webcomponents.greeter.views.main.MainView;


@Route(value = "greet", layout = MainView.class)
@Tag("ve-greeter")
public class GreeterComponent extends Component implements HasSize {

	private static final long serialVersionUID = -3500209011750166789L;

	@Override
    protected void onAttach(AttachEvent attachEvent) {
		super.onAttach(attachEvent);
		attachEvent.getUI().getPage().executeJs(
			"var s = document.createElement('script');s.type='module'; s.src=$0;document.head.appendChild(s);",
			"//greeter-master.cfapps.io/web-component/ve-greeter.js"); //crashes
			//"//greeter.cfapps.io/greeter/web-component/ve-greeter.js");
			//"//localhost:9090/greeter/web-component/ve-greeter.js");
        setWidth("100%");
    }

}
