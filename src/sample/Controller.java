package sample;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;

import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable, ChangeListener {

    @FXML
    TextField addressBar;

    @FXML
    WebView view;

    public void onGo() {
        String address = addressBar.getText();
        if (!address.startsWith("http")) {
            address = "http://" + address;
        }
        WebEngine engine = view.getEngine();
        engine.load(address);
    }

    public void onKeyPressed(KeyEvent event) {
        if (event.getCode() == KeyCode.ENTER) {
            onGo();
        }
    }

    public void onBack() {
        try {
            view.getEngine().getHistory().go(-1);
        } catch (Exception e) {}
        }


    public void onForward() {
        try {
            view.getEngine().getHistory().go(1);
        } catch (Exception e) {}
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        view.getEngine().getLoadWorker().stateProperty().addListener(this);
    }

    @Override
    public void changed(ObservableValue observable, Object oldValue, Object newValue) {
        String address = view.getEngine().getLocation();
        addressBar.setText(address);
    }

}

