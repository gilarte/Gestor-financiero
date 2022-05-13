package com.Rafa.GestorFinanciero;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * JavaFX App
 */
public class App extends Application {

    private static Scene scene;

    /**
     * Método que carga una escena
     */
    @Override
    public void start(Stage stage) throws IOException {
        scene = new Scene(loadFXML("App"), 640, 401);
        stage.setScene(scene);
        stage.show();
    }

    /**
	 * Métodos que llaman a otras escenas
	 * @throws IOException
	 */
    static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }

    /**
     * Carga un archivo fxml
     * @param fxml: archivo cargado
     * @return 
     * @throws IOException
     */
    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }

    /**
     * Método main que ejecuta el programa
     * @param args
     */
    public static void main(String[] args) {
        launch();
    }

}