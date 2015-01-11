package sample;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.paint.Color;
import javafx.stage.DirectoryChooser;
import org.controlsfx.dialog.Dialogs;

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
    public TableView<FileFTP> fileTable;

    @FXML
    public TableColumn nomeColumn;

    @FXML
    public TableColumn tamanhoColumn;

    @FXML
    public TableColumn nomeAlteradoColumn;

    @FXML
    public TableColumn ultimaAltColumn;

    @FXML
    public void doLogin(ActionEvent actionEvent) throws IOException {
        if(!(campoServidor.getText().isEmpty() || campoLogin.getText().isEmpty() || campoPassword.getText().isEmpty())){
            String response = ftp.connect(campoServidor.getText(),campoLogin.getText(),campoPassword.getText());
            System.out.println(response);
            if(response.startsWith("Logado!")) {
                travarTudo(response);

            }else{
                Dialogs.create().owner(mainApp.getPrimaryStage()).title("Erro").message(response).showError();
            }
        }
    }

    private void travarTudo(String response) {
        statusLogin.setText(response);
        statusLogin.setTextFill(Color.web("#7df28b"));
        campoServidor.setDisable(true);
        campoLogin.setDisable(true);
        campoPassword.setDisable(true);
        btLogar.setDisable(true);
        btPasta.setDisable(false);
        btLog.setDisable(false);
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
        /*
        nomeColumn.setCellValueFactory(cellData -> cellData.getValue().);
        tamanhoColumn.setCellValueFactory(cellData -> cellData.getValue().lastNameProperty());
        nomeAlteradoColumn.setCellValueFactory(cellData -> cellData.getValue().lastNameProperty());
        ultimaAltColumn.setCellValueFactory(cellData -> cellData.getValue().lastNameProperty());
        */
        btPasta.setDisable(true);
        btLog.setDisable(true);
    }

    public void setMainApp(Main mainApp) {
        this.mainApp = mainApp;
        fileTable.setItems(mainApp.getFileData());
    }
}
