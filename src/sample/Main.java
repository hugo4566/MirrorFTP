package sample;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;

public class Main extends Application {

    private Stage primaryStage;
    private BorderPane rootLayout;

    private ObservableList<FileFTP> fileData = FXCollections.observableArrayList();

    public Main(){
        fileData.add(new FileFTP(1,"hugo",100,"nunca"));
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        this.primaryStage = primaryStage;

        initRootLayout();
    }

    /**
     * Inicializa o root layout (layout base).
     */
    public void initRootLayout() throws Exception {
        try {
            // Carrega o root layout do arquivo fxml.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("sample.fxml"));
            rootLayout = (BorderPane) loader.load();

            // Mostra a scene (cena) contendo  layout.
            Scene scene = new Scene(rootLayout);
            primaryStage.setTitle("MirrorFTP");
            primaryStage.setScene(scene);       // primaryStage.setScene(new Scene(root, 1024, 728));
            primaryStage.show();

            // Dá ao controlador acesso à the main app.
            Controller controller = loader.getController();
            controller.setMainApp(this);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public ObservableList<FileFTP> getFileData() {
        return fileData;
    }

    public Stage getPrimaryStage() {
        return primaryStage;
    }

    public static void main(String[] args) {
        launch(args);
    }
}
