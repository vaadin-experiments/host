package vaadin.webcomponents.greeter.views.main;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import com.vaadin.flow.component.AttachEvent;
import com.vaadin.flow.component.applayout.DrawerToggle;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.FlexLayout;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.page.History.HistoryStateChangeEvent;
import com.vaadin.flow.component.page.History.HistoryStateChangeHandler;
import com.vaadin.flow.component.tabs.Tabs;
import com.vaadin.flow.router.AfterNavigationEvent;
import com.vaadin.flow.router.AfterNavigationObserver;
import com.vaadin.flow.spring.annotation.SpringComponent;
import com.vaadin.flow.spring.annotation.UIScope;

import org.springframework.beans.factory.annotation.Autowired;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;

/**
 * Navbar
 */
@SpringComponent
// @NoArgsConstructor
@Log4j2
@UIScope
public class Navbar extends HorizontalLayout implements AfterNavigationObserver, HistoryStateChangeHandler {
    

    private static final long serialVersionUID = -3659539687960892117L;

    @Autowired
    private MenuProperties menu;

    @Getter(AccessLevel.PACKAGE)
    private HorizontalLayout content;

    private Tabs tabs;
    private List<FrameContainerBuilder> fcb = new ArrayList<>();

    @PostConstruct
    public void init() {
        log.info("initializing navbar");
        super.setClassName("navbar");
        super.setWidth("100%");
        super.setDefaultVerticalComponentAlignment(Alignment.CENTER);

        var img = new Image("https://placeholder.com/wp-content/uploads/2018/10/placeholder.com-logo1.png", "Logo");
        img.setHeight("44px");
        super.add(img);

        this.content = new HorizontalLayout();
        this.content.setId("navtarget");
        buildTabs();

        FlexLayout tabLayout = new FlexLayout();
        tabLayout.add(tabs);
        tabLayout.setClassName("navbar-tabs");

        super.addAndExpand(tabLayout);

        var toggle = new DrawerToggle();
        toggle.setIcon(new Icon(VaadinIcon.CLIPBOARD_TEXT));
        super.add(toggle);
    }

    private void buildTabs() {
        tabs = new Tabs();
        tabs.setId("tabs");
        tabs.getElement().setProperty("appid", "");
        //tabs.getElement().addSynchronizedProperty("app-id");
        //tabs.getElement().setAttribute("attribute", "resource");
        tabs.getElement().addPropertyChangeListener("appid", appId -> log.info("AppId changed: {}", appId));
        menu.getApps().entrySet().stream().map(entry -> new FrameContainerBuilder(entry.getKey(), entry.getValue()))
                .forEach(this::placeFrame);

        tabs.setOrientation(Tabs.Orientation.HORIZONTAL);
        tabs.addSelectedChangeListener(event -> {
            var selection = tabs.getSelectedTab();
            fcb.forEach(f -> f.tabSelectionChanged(selection));
        });
        tabs.setAutoselect(false);
        tabs.setSelectedTab(null);
    }

    private void placeFrame(FrameContainerBuilder fcb) {
        this.fcb.add(fcb);
        tabs.add(fcb.getTab());
        content.add(fcb.getDiv());
    }

    @Override
    protected void onAttach(AttachEvent attachEvent) {
        super.onAttach(attachEvent);
    }

    void setInitialTab(String appId){
        fcb.stream().filter(f -> f.getAppId().equals(appId)).findAny().stream().forEach(f -> tabs.setSelectedTab(f.getTab()));
    }

    @Override
    public void afterNavigation(AfterNavigationEvent event) {
        log.info("afterNavigation");

    }

    @Override
    public void onHistoryStateChange(HistoryStateChangeEvent event) {
        log.info("onHistoryStateChange");

    }

}