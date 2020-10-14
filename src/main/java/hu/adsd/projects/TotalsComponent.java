package hu.adsd.projects;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class TotalsComponent extends VBox implements Initializable
{
    @FXML
    private Label embodiedCarbonLabel;

    @FXML
    private Label embodiedEnergyLabel;

    private final double totalCarbon;
    private final double totalEnergy;

    public TotalsComponent( double totalCarbon, double totalEnergy )
    {
        this.totalCarbon = totalCarbon;
        this.totalEnergy = totalEnergy;

        FXMLLoader fxmlLoader = new FXMLLoader( getClass().getResource( "../../../projectConfigTotal.fxml" ) );
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
    public void initialize( URL location, ResourceBundle resources )
    {
        embodiedCarbonLabel.setText( String.format( "Embodied carbon: %s", this.totalCarbon ) );
        embodiedEnergyLabel.setText( String.format( "Embodied energy: %s", this.totalEnergy ) );
    }
}
