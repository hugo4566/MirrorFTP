package sample;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.stage.DirectoryChooser;
import javafx.stage.Popup;
import javafx.stage.PopupWindow;
import javafx.util.Callback;
import org.controlsfx.dialog.Dialogs;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;
import java.util.ResourceBundle;

public class Controller implements Initializable {

    private Main mainApp;

    SimpleFTP ftp = new SimpleFTP();

    @FXML
    public TextField campoServidor;

    @FXML
    public TextField campoLogin;

    @FXML
    public PasswordField campoPassword;

    @FXML
    public Label statusLogin;

    @FXML
    public Button btLogar;

    @FXML
    public Label labelPath;

    @FXML
    public Label labelSinc;

    @FXML
    public Button btPasta;

    @FXML
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
                String responseList = ftp.list("/");
                mainApp.getFileData().addAll(destrinchaLista(responseList));
            }else{
                Dialogs.create().owner(mainApp.getPrimaryStage()).title("Erro").message(response).showError();
            }
        }
    }

    private Collection<FileFTP> destrinchaLista(String responseList) throws IOException {
        Collection<FileFTP> listaFileFTPs = new ArrayList<FileFTP>();
        String[] listaArquivos = responseList.split("\\r\\n");
        for (int i = 0; i < listaArquivos.length; i++) {
            FileFTP fileFTP = new FileFTP(listaArquivos[i]);
            listaFileFTPs.add(fileFTP);
        }
        mainApp.getFileData().clear();
        if(!ftp.getAtualList().equals("/")){
            mainApp.getFileData().add(new FileFTP("","...","","",""));
        }
        return listaFileFTPs;
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

        nomeColumn.setCellValueFactory(p -> ((TableColumn.CellDataFeatures<FileFTP, String>) p).getValue().nomeProperty());

        tamanhoColumn.setCellValueFactory(p -> ((TableColumn.CellDataFeatures<FileFTP, String>) p).getValue().tamanhoProperty());

        nomeAlteradoColumn.setCellValueFactory(p -> ((TableColumn.CellDataFeatures<FileFTP, String>) p).getValue().nomeModificadorProperty());

        ultimaAltColumn.setCellValueFactory(p -> ((TableColumn.CellDataFeatures<FileFTP, String>) p).getValue().modificadoProperty());

        fileTable.setRowFactory(getRowAction());

        btPasta.setDisable(true);
        btLog.setDisable(true);
    }

    private Callback<TableView<FileFTP>, TableRow<FileFTP>> getRowAction() {
        return param -> {
            final TableRow<FileFTP> row = new TableRow<>();
            row.addEventFilter(MouseEvent.MOUSE_RELEASED, event -> {
                if (event.getButton() == MouseButton.MIDDLE) {
                    try {
                        if(row.getItem().getNome().equals("...")){
                            String responseList = ftp.list(ftp.getUltimoList());
                            mainApp.getFileData().addAll(destrinchaLista(responseList));
                        }else if(row.getItem().getTipo().equals("3")){
                            String responseList = ftp.list("/"+row.getItem().getNome());
                            mainApp.getFileData().addAll(destrinchaLista(responseList));
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    event.consume();
                }

                if (event.getButton() == MouseButton.SECONDARY) {
                    System.out.println("OLa");
                }
            });


            return row;
    };
    }

    public void setMainApp(Main mainApp) {
        this.mainApp = mainApp;
        fileTable.setItems(mainApp.getFileData());
    }
}
