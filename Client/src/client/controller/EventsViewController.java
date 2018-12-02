package client.controller;

import client.ThreadClient;
import client.ViewManager;

public class EventsViewController {
    private ViewManager viewManager;
    private ThreadClient threadClient;
    public void initialize() {

    }

    public void reserve() {

    }

    public void buy(){

    }

    public void back(){
        if((boolean)threadClient.getConnected().get()) {
        }
         else viewManager.setInitScene();
    }




    public void setThreadClient(ThreadClient threadClient) {
        this.threadClient = threadClient;
    }

    public void setViewManager(ViewManager viewManager) {
        this.viewManager = viewManager;
    }
}
