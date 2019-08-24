package com.app.main;

import com.app.service.ControlAppService;

public class App {

    public static void main(String[] args) {

        final String appName = " SniffHttpApplication v1.07 27.04.2019 ";
        System.out.println(appName);
        ControlAppService cs = new ControlAppService();
        cs.controlLoop();
    }
}
