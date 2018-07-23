package com.taskingfx.impls;

import javafx.fxml.Initializable;

import java.net.URL;
import java.util.ResourceBundle;

public interface TskController extends Initializable {

    default void properties() {

    }

    default void appCalls() {

    }

    default void run() {

    }

    @Override
    default void initialize(URL location, ResourceBundle resources) {
        //A sequência não deve ser alterada
        properties();
        appCalls();
        run();
    }
}
