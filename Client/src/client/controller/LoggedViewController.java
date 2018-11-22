package client.controller;

import client.ThreadClient;
import client.ViewManager;

public class LoggedViewController {
    private ViewManager viewManager;
    private ThreadClient threadClient;


    public void initialize() {

    }

    public void setThreadClient(ThreadClient threadClient) {
        this.threadClient = threadClient;
    }

    public void setViewManager(ViewManager viewManager) {
        this.viewManager = viewManager;
    }
}
