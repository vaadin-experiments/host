package vaadin.webcomponents.greeter.views.main;

import com.vaadin.flow.component.AttachEvent;
import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.applayout.AppLayout;
import com.vaadin.flow.component.applayout.DrawerToggle;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.dependency.JsModule;
import com.vaadin.flow.component.dependency.NpmPackage;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.FlexComponent.Alignment;
import com.vaadin.flow.component.orderedlayout.FlexLayout;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.tabs.Tab;
import com.vaadin.flow.component.tabs.Tabs;
import com.vaadin.flow.router.BeforeEvent;
import com.vaadin.flow.router.HasUrlParameter;
import com.vaadin.flow.router.HighlightConditions;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouterLink;
import com.vaadin.flow.router.WildcardParameter;
import com.vaadin.flow.server.InitialPageSettings;
import com.vaadin.flow.server.PWA;
import com.vaadin.flow.server.PageConfigurator;
import com.vaadin.flow.spring.annotation.SpringComponent;
import com.vaadin.flow.spring.annotation.UIScope;

import org.springframework.util.StringUtils;

import elemental.json.impl.JreJsonFactory;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.extern.log4j.Log4j2;

/**
 * A sample Vaadin view class.
 * <p>
 * To implement a Vaadin view just extend any Vaadin component and use @Route
 * annotation to announce it in a URL as a Spring managed bean. Use the @PWA
 * annotation make the application installable on phones, tablets and some
 * desktop browsers.
 * <p>
 * A new instance of this class is created for every new user and every browser
 * tab/window.
 */
@Route("")
@PWA(name = "Greeter Application", shortName = "Greeter", description = "This is an example Vaadin application.", enableInstallPrompt = false)
@JsModule("./js/iFrames.js")
@CssImport("./styles/iFrames.css")
// @JsModule("./js/iFrames.js")
@NpmPackage(value = "postmate", version = "1.5.2")
@SpringComponent
@UIScope
@Log4j2
public class MainView extends AppLayout implements PageConfigurator, HasUrlParameter<String> {

        private JreJsonFactory json;
        private String parameter;
        private static final long serialVersionUID = -1817009361621321205L;
        private Navbar navbar;

        public MainView(Navbar navbar, JreJsonFactory json) {
                this.json = json;
                this.navbar = navbar;
                setDrawerOpened(false);
                addToDrawer(new VerticalLayout()); // TODO replace with clipboard app
                addToNavbar(navbar);
                var contentLayout = new HorizontalLayout();
                contentLayout.add(navbar.getContent());
                setContent(contentLayout); // TODO replace with Card Menu

        }

        @Override
        public void configurePage(InitialPageSettings settings) {
                // TODO Auto-generated method stub

        }

        @Override
        public void setParameter(BeforeEvent event, @WildcardParameter String parameter) {
this.parameter = parameter;
        }

        @Override
        protected void onAttach(AttachEvent attachEvent) {
                super.onAttach(attachEvent);
                if (!StringUtils.isEmpty(parameter)) {
                        var path = parameter.split("/");
                        var page = UI.getCurrent().getPage();
                        var e = this.json.createObject();
                        if (path.length > 1) {
                                e.put("setUrl", "/" + parameter);
                        }
                        e.put("activeApp", path[0]);
                        //this.navbar.setInitialTab(path[0]);
                        page.executeJs("window.portal.handleNavigation($0)", e).then(
                                        success -> log.info("init: {}", parameter, success),
                                        error -> log.info("not init: {} {}", parameter, error));

                }
        }

        

}
