package com.app.service;

import com.app.exception.MyAppException;
import com.app.model.Definition;
import com.app.model.DictionaryPhrase;
import com.app.service.http.*;
import com.app.utility.DataManager;

import java.util.*;
import java.util.stream.Collectors;

public class OperationService {

    private static final String REGEX = "[A-Za-z0-9]";

    public Integer operationNumberOne() {

        System.out.println(">>>>>>>>>>>>>>>>>> let's check new joke about chuck norris >>>>>>>>>>>>>>>>>>");
        HttpConnectionChuckNorris httpConnectionChuckNorris = new HttpConnectionChuckNorris();
        Integer solution = httpConnectionChuckNorris.establishAsyncConnection();
        System.out.println(">>>>>>>>>>>>>>>>>> that's is more than I expected , any word about chuck Norris is priceless , right now is equal to 100 USD");
        System.out.println(">>>>>>>>>>>>>>>>>> it means that you collect ...... " + solution + " USD");
        return solution;
    }

    public void operationNumberTwo() {

        System.out.println(">>>>>>>>>>>>>>>>>> Where do you wanna spend all this money >>>>>>>>>>>>>>>>>>");
        HttpConnectionMoney httpConnectionMoney = new HttpConnectionMoney();
        System.out.println(httpConnectionMoney.establishAsyncConnection());

        Map<String, String> currencies = new HashMap<>(Map.of(
                "SGD", "Singapore Dollar",
                "MYR", "Malaysian Ringgit",
                "EUR", "EURO",
                "USD", "United States Dollar",
                "AUD", "Australian dollar",
                "JPY", "Japanese yen",
                "CNH", "Chinese Yuan Renminbi",
                "HKD", "Hong Kong dollar",
                "CAD", "Canadian Dollar",
                "INR", "Indian Rupee"));
        currencies.put("DKK", "Danish Krone");
        currencies.put("GBP", "Pound sterling");
        currencies.put("RUB", "Russian Ruble");
        currencies.put("NZD", "New Zealand Dollar");
        currencies.put("MXN", "Mexican Peso");
        currencies.put("IDR", "Indonesian Rupiah");
        currencies.put("TWD", "Taiwan New Dollar");
        currencies.put("THB", "Thailand Baht");
        currencies.put("VND", "Viet Nam Dong");
        currencies.forEach((k, v) -> System.out.println(k + "::::::::::::::::::" + v));
    }

    public Long operationNumberTree() {

        String currency = DataManager.getLine("\n>>>>>>>>>>>>>>>>>> Choose you destination we convert you money for this occasion, " +
                "press shortcut from above");
        HttpConnectionCalc httpConnectionCalc = new HttpConnectionCalc();
        Double money = httpConnectionCalc.establishAsyncConnection(currency);
        if (money == null || money.isNaN()) {
            throw new MyAppException(" wrong result of establishAsyncConnection method ");
        }
        System.out.println("\n>>>>>>>>>>>>>>>>>> We convert you money from USD to " + currency +
                " and You have now >>> " + money + " " + currency);
        return averNumberDigits(money);
    }

    public String operationNumberFour(Long number) {

        if (number == null || number < 0) {
            throw new MyAppException(" wrong arg in operationNumberFour method ");
        }
        System.out.println("\n>>>>>>>>>>>>>>>>>> Aver number from you money is " + number +
                " So we are looking for actor in TV service with Id number " + number);
        HttpConnectionName httpConnectionNr4 = new HttpConnectionName();
        return httpConnectionNr4.establishAsyncConnection(number);
    }

    public void operationNumberFive(String name) {

        if (name == null) {
            throw new MyAppException(" wrong arg in operationNumberFive method ");
        }
        System.out.println("\n>>>>>>>>>>>>>>>>>> Let's find out what his FirstName means, " +
                "we will get definition from digits library API ");
        List<DictionaryPhrase> dictionaryPhraseList = getDictionaryPhraseList(name);
        printMapOfAuthorAndNumbersCharInDefinition(dictionaryPhraseList);
        printSolutionOfNumberFiveOperation(dictionaryPhraseList);
    }


    private void printSolutionOfNumberFiveOperation(List<DictionaryPhrase> item) {
        System.out.println("\n>>>>>>>>>>>>>>>>Author of the shortest Definition is >>>" + getAuthorOfShortestDefinition(item));
        System.out.println("\n>>>>>>>>>>>>>>>>The shortest Definition is >>>>>" + getShortestDefinition(item));
    }

    private List<DictionaryPhrase> getDictionaryPhraseList(String item) {

        HttpConnectionDefine httpConnectionDefine = new HttpConnectionDefine();
        Definition definition = httpConnectionDefine.establishSyncConnection(item.split(" ")[0]);
        return definition.getList();
    }

    private void printMapOfAuthorAndNumbersCharInDefinition(List<DictionaryPhrase> item) {

        if (item == null) {
            throw new MyAppException(" wrong arg in printMapOfAuthorAndNumbersCharInDefinition method ");
        }
        if (item.isEmpty()) {
            throw new MyAppException(" args list is empty ");
        }
        System.out.println("\n>>>>>>>>>>>>>>Print Map with Author and Numbers of Character in Definition");
        item.stream().collect(Collectors.toMap(
                DictionaryPhrase::getAuthor,
                e -> getNumberOfCharInSentence(e.getDefinition()))).forEach((k, v) -> System.out.println(k + ":::::::::::::::::" + v));
    }

    private String getAuthorOfShortestDefinition(List<DictionaryPhrase> item) {

        if (item == null) {
            throw new MyAppException(" wrong arg in getAuthorOfShortestDefinition method ");
        }
        if (item.isEmpty()) {
            throw new MyAppException(" args list is empty ");
        }
        return item.stream().collect(Collectors.toMap(
                DictionaryPhrase::getAuthor,
                e -> getNumberOfCharInSentence(e.getDefinition())
        ))
                .entrySet().stream().min(Comparator.comparing(Map.Entry::getValue)).orElseThrow(() -> new MyAppException(" NO RESULT getAuthorOfShortestDefinition")).getKey();
    }

    private String getShortestDefinition(List<DictionaryPhrase> item) {

        if (item == null) {
            throw new MyAppException(" wrong arg in getShortestDefinition method ");
        }
        if (item.isEmpty()) {
            throw new MyAppException(" args list is empty ");
        }
        return item.stream().collect(Collectors.toMap(
                DictionaryPhrase::getDefinition,
                e -> getNumberOfCharInSentence(e.getDefinition())
        ))
                .entrySet().stream().min(Comparator.comparing(Map.Entry::getValue)).orElseThrow(() -> new MyAppException(" NO RESULT printShortestDefinition")).getKey();
    }

    private Long getNumberOfCharInSentence(String text) {

        if (text == null) {
            throw new MyAppException(" wrong arg in getNumberOfCharInSentence method ");
        }
        return Arrays.stream(text.split("")).filter(this::validInputData).count();
    }

    private boolean validInputData(String input) {

        if (input == null) {
            throw new MyAppException(" wrong arg in validInputData method ");
        }
        return input.matches(REGEX);
    }

    private static Long averNumberDigits(Double number) {
        if (number == null) {
            throw new MyAppException(" wrong arg in averNumberDigits method ");
        }
        return (Arrays.stream(number.toString().split("")).filter(f -> !f.equals(".")).collect(Collectors.averagingInt(Integer::valueOf)).longValue());
    }
}
