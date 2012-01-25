package de.mediapool.grab.web;

import com.vaadin.ui.Window;

public class WebWindow extends Window {

    public WebWindow() {

        // entity manager
        WebEntityManagerView entityManagerView = new WebEntityManagerView();
        setContent(entityManagerView);

        // select window theme
        setTheme("web");
    }
}
