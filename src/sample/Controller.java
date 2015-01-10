package sample;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.stage.DirectoryChooser;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable {

    private Main mainApp;

    SimpleFTP ftp = new SimpleFTP();

    @FXML
    public TextField campoServidor;
    public TextField campoLogin;
    public PasswordField campoPassword;
    public Label statusLogin;
    public Button btLogar;
    public Label labelPath;
    public Label labelSinc;
    public Button btPasta;
    public Button btLog;

    @FXML
    public void doLogin(ActionEvent actionEvent) throws IOException {
        if(!(campoServidor.getText().isEmpty() || campoLogin.getText().isEmpty() || campoPassword.getText().isEmpty())){
            String response = ftp.connect(campoServidor.getText(),campoLogin.getText(),campoPassword.getText());
            statusLogin.setText(response);
            System.out.println(response);
            if(response.startsWith("Now")) {
                statusLogin.setTextFill(Color.web("#7df28b"));
                campoServidor.setDisable(true);
                campoLogin.setDisable(true);
                campoPassword.setDisable(true);
                btLogar.setDisable(true);
                btPasta.setDisable(false);
                btLog.setDisable(false);
            }else{
                statusLogin.setTextFill(Color.RED);
            }
        }
    }

    @FXML
    public void doPromptPath(ActionEvent actionEvent) throws IOException {
        DirectoryChooser directoryChooser = new DirectoryChooser();
        File selectedDirectory = directoryChooser.showDialog(mainApp.getPrimaryStage());
        if(selectedDirectory == null){
            labelPath.setText("No Directory selected");
        }else{
            labelPath.setText(selectedDirectory.getAbsolutePath());
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        btPasta.setDisable(true);
        btLog.setDisable(true);
    }

    public void setMainApp(Main mainApp) {
        this.mainApp = mainApp;
    }
}
