package hu.adsd.projects;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

public class ConfigurationTitleComponent extends VBox implements Initializable {

    private String title;

    @FXML
    private Label configurationTitle;

    public ConfigurationTitleComponent(String title)
    {
        this.title = title;

        FXMLLoader fxmlLoader = new FXMLLoader( getClass().getResource( "../../../titleConfigurationLabel.fxml" ) );
        fxmlLoader.setRoot( this );
        fxmlLoader.setController( this );

        try
        {
            fxmlLoader.load();
        }
        catch ( IOException e )
        {
            e.printStackTrace();
        }
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        
        configurationTitle.setText(this.title);
    }
    
}
