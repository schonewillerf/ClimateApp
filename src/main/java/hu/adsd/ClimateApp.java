package hu.adsd;

import hu.adsd.projects.Project;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

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

        scene.getStylesheets().add( ClimateApp.class.getResource( "../../Style.css" ).toExternalForm() );

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
     * @throws IOException FXML Document is an external file
     */
    public static void goToScreen( String fxmlDocument ) throws IOException
    {
        Parent root = FXMLLoader.load( ClimateApp.class.getResource( "../../" + fxmlDocument + ".fxml" ) );
        scene.setRoot( root );
    }

    public ClimateApp()
    {
        project = new Project();
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
