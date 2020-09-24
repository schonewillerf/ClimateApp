package hu.adsd;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class ClimateApp extends Application
{
    // Constructor
    public ClimateApp() {
        new Project();
        new MaterialDatabaseParser().getMaterialList();
    }

    // Start the JavaFX Application
    @Override
    public void start( Stage stage ) throws Exception
    {
        Parent root = FXMLLoader.load( getClass().getResource( "../../sample.fxml" ) );
        Scene scene = new Scene( root );

        stage.setScene(scene);
        stage.setTitle("Klimaat App");
        stage.show();
    }
}
