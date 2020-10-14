package hu.adsd;

import hu.adsd.buildingmaterials.CirculationType;
import hu.adsd.buildingmaterials.Product;
import hu.adsd.projects.BuildingMaterialContainer;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import hu.adsd.projects.BuildingPart;
import hu.adsd.projects.Project;

public class ClimateApp extends Application
{
    // This is the application Scene that is available from other classes
    // It will work with the same Scene object from anywhere
    private static Scene scene;

    private static Project project;

    // Start the JavaFX Application
    @Override
    public void start( Stage stage ) throws Exception
    {
        Parent root = FXMLLoader.load( getClass().getResource( "../../projectView.fxml" ) );

        // Now the scene is initialised
        scene = new Scene( root );

        stage.setScene( scene );
        stage.setTitle( "Klimaat App" );
        stage.show();
    }

    /**
     * This is a static method, that means it is available from anywhere without having to create a new ClimateApp object
     * This is usefull so we can find the static scene belonging to this App
     * <p>
     * Example usage:
     * <p>
     * ClimateApp.goToScreen( "projectView" ); // This will load the projectViewBackup.fxml document from resources in the window
     *
     * @param fxmlDocument a Sting value of the document in resources
     * @throws IOException
     */
    public static void goToScreen( String fxmlDocument ) throws IOException
    {
        Parent root = FXMLLoader.load( ClimateApp.class.getResource( "../../" + fxmlDocument + ".fxml" ) );
        scene.setRoot( root );
    }

    public ClimateApp()
    {
        project = new Project();

        // Add dummy data to Project
        // ( Hint: create the child objects before the parents )
        //
        // Create some Products
        Product toilet = new Product( 1, "WC", 1, 1, CirculationType.REUSED, 2 );
        Product faucet = new Product( 2, "Kraan", 1, 1, CirculationType.NEW, 4 );
        Product gutter = new Product( 3, "Dakgoot", 1, 1, CirculationType.LINEAR, 2 );
        //
        // Create some BuildingMaterialContainers, one for each product
        BuildingMaterialContainer toiletContainer = new BuildingMaterialContainer( toilet );
        BuildingMaterialContainer faucetContainer = new BuildingMaterialContainer( faucet );
        BuildingMaterialContainer gutterContainer = new BuildingMaterialContainer( gutter );
        //
        // Create some BuildingParts
        BuildingPart bathroom = new BuildingPart( "badkamer" );
        BuildingPart roof = new BuildingPart( "dak" );
        //
        // Add Products to BuildingParts
        bathroom.getBuildingMaterialContainers().add( toiletContainer );
        bathroom.getBuildingMaterialContainers().add( faucetContainer );
        roof.getBuildingMaterialContainers().add( gutterContainer );
        //
        // Add BuildingParts to Project
        project.getProjectBuildingParts().add( bathroom );
        project.getProjectBuildingParts().add( roof );
    }

    public static Project getProject()
    {
        return project;
    }

    public static void setProject(Project newProject)
    {
        project = newProject;
    }
}
