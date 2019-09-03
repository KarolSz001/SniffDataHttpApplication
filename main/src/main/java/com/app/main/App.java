package com.app.main;

import com.app.service.ControlAppService;

public class App {

    public static void main(String[] args) {

        final String appName = " SniffHttpApplication v2.01 03.09.2019 ";
        System.out.println(appName);
        ControlAppService cs = new ControlAppService();
        cs.controlLoop();
    }
}
