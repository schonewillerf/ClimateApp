package hu.adsd.projects;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

public class TotalsComponent extends VBox implements Initializable {

    private String title;

    @FXML
    private Label circulationTypeLabel;

    public TotalsComponent(String title)
    {
        this.title = title;

        FXMLLoader fxmlLoader = new FXMLLoader( getClass().getResource( "../../../projectBuildingMaterialConfigCardView.fxml" ) );
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

        // commented because of dropdown in the same fxml, raymond will make seperate fxml later
        // circulationTypeLabel.setText("Totaal " + title + ":");

    }

    public void buttonTest()
    {
        System.out.println("test van knop");
    }
    
}
