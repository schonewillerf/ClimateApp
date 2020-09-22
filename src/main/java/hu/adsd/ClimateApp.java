package hu.adsd;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.List;

public class ClimateApp extends Application
{
    // Class variables
    private final Project project;
    private final List<Material> availableMaterials;

    // Constructor
    public ClimateApp()
    {
        // Initialisation
        this.project = new Project();
        this.availableMaterials = new MaterialDatabaseParser().getMaterialList();
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
