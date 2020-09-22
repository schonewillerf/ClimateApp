package hu.adsd;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.util.List;
import java.util.Scanner;

public class ClimateApp extends Application
{
    // Class variables
    //
    private final Project project;
    private final List<Material> availableMaterials;
    //
    private double xOffset;
    private double yOffset;

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
        scene.setFill( Color.TRANSPARENT);

        stage.setScene(scene);
        stage.initStyle( StageStyle.TRANSPARENT);
        stage.setTitle("Klimaat App");
        stage.show();

        root.setOnMousePressed( ( MouseEvent event) -> {
            xOffset = event.getSceneX();
            yOffset = event.getSceneY();
        });

        root.setOnMouseDragged((javafx.scene.input.MouseEvent event) -> {
            stage.setX(event.getScreenX() - xOffset);
            stage.setY(event.getScreenY() - yOffset);
        });
    }
}
