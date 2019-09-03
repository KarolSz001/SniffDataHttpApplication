package com.app.service;

import com.app.exception.MyAppException;
import com.app.utility.DataManager;

public class ControlAppService {

    public ControlAppService() {
    }

    OperationService operationService = new OperationService();

    public void controlLoop() {

        try {


            operationService.operationNumberOne();
            DataManager.getLine("\n PRESS ANY NUMBER KEY TO CONTINUE");
            operationService.operationNumberTwo();
            Long idNumber = operationService.operationNumberTree();
            String name = operationService.operationNumberFour(idNumber);
            DataManager.getLine("\n PRESS ANY NUMBER KEY TO CONTINUE");
            System.out.println(name + " was founded ");
            DataManager.getLine("\n PRESS ANY NUMBER KEY TO CONTINUE");
            operationService.operationNumberFive(name);



        } catch (MyAppException e) {
            e.getMessage();
        }
    }


}

