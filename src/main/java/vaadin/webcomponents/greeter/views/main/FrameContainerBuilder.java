package vaadin.webcomponents.greeter.views.main;

import com.vaadin.flow.component.AttachEvent;
import com.vaadin.flow.component.Tag;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.tabs.Tab;

import lombok.Getter;
import lombok.extern.log4j.Log4j2;

/**
 * FrameContainer
 */
@Log4j2
public class FrameContainerBuilder {

    @Getter private Div div;
    @Getter private Tab tab;
    @Getter private String appId;
    private MenuProperties.App app;

    FrameContainerBuilder(String appId, MenuProperties.App app) {
        this.appId = appId;
        this.app = app;
        buildTab();
        buildDiv();
    }

    private void buildDiv() {
        var containerId = "container-for-" + appId;
        this.div = new Div();
        this.div.setId(containerId);
        this.div.addClassNames("frameContainer", "hidden");
        this.div.addAttachListener(this::attachDiv);
    }

    private void attachDiv(AttachEvent event) {
        log.info("attaching {}", appId);
        var page = UI.getCurrent().getPage();
        page.executeJs("window.portal.createFramedApp($0, $1, $2)", div.getId().get(), app.getUrl(), appId).then(
                success -> log.info("{} initialized: {}", appId, success),
                error -> log.info("{} not initialized: {}", appId, error));
    }

    private void buildTab() {
        log.info("building {} tab", appId);
        this.tab = new Tab(appId);
        this.tab.setId("tab-" + appId);
    }


    public void tabSelectionChanged(Tab selectedTab) {
        if (this.tab.equals(selectedTab)) {
            log.info("{} selected.");
            this.div.removeClassName("hidden");

            var page = UI.getCurrent().getPage();
            page.executeJs("window.portal.switchContext($0)", appId).then(
                    success -> log.info("switched: {}", appId, success),
                    error -> log.info("not switched: {} {}", appId, error));
        } else {
            log.info("{} deselected.");
            this.div.addClassName("hidden");
        }
    }

}