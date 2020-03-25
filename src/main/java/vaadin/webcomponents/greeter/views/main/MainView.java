package vaadin.webcomponents.greeter.views.main;

import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.applayout.AppLayout;
import com.vaadin.flow.component.applayout.DrawerToggle;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.orderedlayout.FlexComponent.Alignment;
import com.vaadin.flow.component.orderedlayout.FlexComponent.JustifyContentMode;
import com.vaadin.flow.component.tabs.Tab;
import com.vaadin.flow.component.tabs.Tabs;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.server.InitialPageSettings.WrapMode;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouterLink;
import com.vaadin.flow.server.InitialPageSettings;
import com.vaadin.flow.server.PWA;
import com.vaadin.flow.server.PageConfigurator;

import vaadin.webcomponents.greeter.views.main.greeter.GreeterComponent;

/**
 * A sample Vaadin view class.
 * <p>
 * To implement a Vaadin view just extend any Vaadin component and
 * use @Route annotation to announce it in a URL as a Spring managed
 * bean.
 * Use the @PWA annotation make the application installable on phones,
 * tablets and some desktop browsers.
 * <p>
 * A new instance of this class is created for every new user and every
 * browser tab/window.
 */
@Route("")
@PWA(name = "Greeter Application",
        shortName = "Greeter",
        description = "This is an example Vaadin application.",
        enableInstallPrompt = false)
public class MainView extends AppLayout {
   
        private static final long serialVersionUID = -1817009361621321205L;
        
        TextField textDependencyForGreeter = new TextField();
        Button buttonDependencyForGreeter = new Button();
       
	public MainView() {

                setDrawerOpened(false);
                var layout = new HorizontalLayout();
                layout.setWidth("100%");
                layout.setDefaultVerticalComponentAlignment(Alignment.CENTER);


                var img = new Image("https://i.imgur.com/GPpnszs.png", "Vaadin Logo");
                img.setHeight("44px");
                layout.add(img);

         
                var tabs = new Tabs(new Tab(new RouterLink("Greeter", GreeterComponent.class)));
                tabs.setOrientation(Tabs.Orientation.HORIZONTAL);
                layout.addAndExpand(tabs);

                var toggle = new DrawerToggle();
                toggle.addClickListener(e -> Notification.show("text"));
                toggle.setIcon(new Icon(VaadinIcon.CLIPBOARD_TEXT));
                layout.add(toggle);
         
                addToNavbar(layout);
                addToDrawer(new VerticalLayout());
                setContent(new Text("Welcome Page"));
         }


}
