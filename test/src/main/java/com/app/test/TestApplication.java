package com.app.test;

import com.app.exception.MyAppException;
import com.app.model.DictionaryPhrase;
import com.app.service.OperationService;
import com.app.service.http.HttpConnectionCalc;
import com.app.service.http.HttpConnectionName;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.internal.matchers.Null;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

import java.math.BigDecimal;
import java.util.List;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
@TestInstance(TestInstance.Lifecycle.PER_METHOD)

public class TestApplication {


    OperationService operationService = new OperationService();

    @Test
    @DisplayName("Test - operationNumberFour method with wrong args ")
    public void shouldThrowExceptionForWrongArgInOperationNumberFourMethod() {
        MyAppException e = Assertions.assertThrows(MyAppException.class, () -> operationService.operationNumberFour((long) -19));
        Assertions.assertEquals(" wrong arg in operationNumberFour method ", e.getMessage());
    }
//printMapOfAuthorAndNumbersCharInDefinition

    /*@Spy
    List<DictionaryPhrase> spyList;
    @Test
    @DisplayName("Test - operationNumberFour method with wrong args ")
    public void shouldThrowExceptionForWrongArgInMethod() {
        MyAppException e = Assertions.assertThrows(MyAppException.class, () -> operationService.printMapOfAuthorAndNumbersCharInDefinition(spyList));
        Assertions.assertEquals(" wrong arg in operationNumberFour method ", e.getMessage());
    }*/


    @Spy
    HttpConnectionCalc httpConnectionCalc;

    @Test
    @DisplayName("Test - operationNumberFour method with wrong args ")
    public void shouldThrowExceptionForWrongArgInHttpConnectionCalc() {

        MyAppException e = Assertions.assertThrows(MyAppException.class, () -> httpConnectionCalc.establishAsyncConnection(null));
        Assertions.assertEquals(" wrong arg in establishAsyncConnection method ", e.getMessage());
    }

    @Test
    @DisplayName("Test - operationNumberFour method with wrong args ")
    public void shouldTReturnCorrectNumberInHttpConnectionCalc() {
        Double result = httpConnectionCalc.establishAsyncConnection("RUB");
        Mockito.verify(httpConnectionCalc).establishAsyncConnection("RUB");
        Assertions.assertEquals(66.78, result);
    }


    @Test
    @DisplayName("Test - operationNumberFour method with wrong args ")
    public void shouldReturnCorrectNumberInHttpConnectionCalc2() {

        Assertions.assertAll(
                () -> Assertions.assertEquals(httpConnectionCalc.establishAsyncConnection("RUB"), 66.78),
                () -> Assertions.assertEquals(httpConnectionCalc.establishAsyncConnection("CAD"), 1.33),
                () -> Assertions.assertEquals(httpConnectionCalc.establishAsyncConnection("INR"), 72.2)
        );

    }


    @Mock
    HttpConnectionName httpConnectionName;

    @Test
    @DisplayName("Test - mock httpConnectionName and verify method establishAsyncConnection ")
    public void shouldReturnCorrectNumberInHttpConnectionCalc22() {

        httpConnectionName.establishAsyncConnection(1l);
        Mockito.verify(httpConnectionName).establishAsyncConnection(1l);
        Mockito.when(httpConnectionName.establishAsyncConnection(1l)).thenReturn("KAROL");
        Assertions.assertEquals("KAROL", httpConnectionName.establishAsyncConnection(1l));
    }

    @Spy
    HttpConnectionName httpConnectionName2;

    @Test
    @DisplayName("Test - shouldReturnCorrectNumberInHttpConnectionName ")
    public void shouldReturnCorrectNumberInHttpConnectionName() {

        Assertions.assertAll(
                () -> Assertions.assertEquals(httpConnectionName2.establishAsyncConnection(1l), "Mike Vogel"),
                () -> Assertions.assertEquals(httpConnectionName2.establishAsyncConnection(100l), "Ashley Gable")
        );

    }

    @Test
    @DisplayName("Test - shouldReturnCorrectNumberInHttpConnectionName ")
    public void shouldThrowExceptionInHttpConnectionNameForWrongArg() {

        MyAppException e = Assertions.assertThrows(MyAppException.class, () -> httpConnectionName2.establishAsyncConnection(-100l));
        Assertions.assertEquals(" wrong arg in HttpConnectionName/ establishAsyncConnection method ", e.getMessage());

    }

}


