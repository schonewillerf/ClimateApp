package hu.adsd.projects;

import hu.adsd.ClimateApp;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.io.IOException;

public class StartController
{
    @FXML
    private TextField projectField;

    @FXML
    private TextField conceptField;

    @FXML
    private Label errorText;


    public void createProject() throws IOException
    {
        if ( projectField.getText().isEmpty() || conceptField.getText().isEmpty() )
        {
            errorText.setVisible( true );
        }
        else
        {
            Project project = new Project( projectField.getText(), conceptField.getText() );

            ClimateApp.setProject( project );

            ClimateApp.goToProductsScreen( 0 );
        }
    }
}
