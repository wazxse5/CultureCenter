package client.controller;

import client.ThreadClient;
import client.ViewManager;

public class RegisterViewController {
    private ViewManager viewManager;
    private ThreadClient threadClient;

    public void initialize() {

    }

    public void back() {
        viewManager.setInitScene();
    }


    public void setViewManager(ViewManager viewManager) {
        this.viewManager = viewManager;
    }

    public void setThreadClient(ThreadClient threadClient) {
        this.threadClient = threadClient;
    }
}
