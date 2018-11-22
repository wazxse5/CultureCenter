package client.controller;

import client.ThreadClient;
import client.ViewManager;

import java.io.IOException;

public class InitViewController {
    private ViewManager viewManager;
    private ThreadClient threadClient;


    public void initialize() {

    }

    public void login() {
        viewManager.setLoginScene();
    }

    public void register() {
        viewManager.setRegisterScene();
    }

    public void setThreadClient(ThreadClient threadClient) {
        this.threadClient = threadClient;
    }

    public void setViewManager(ViewManager viewManager) {
        this.viewManager = viewManager;
    }
}
