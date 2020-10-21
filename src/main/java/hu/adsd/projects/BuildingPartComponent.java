package hu.adsd.projects;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class BuildingPartComponent extends VBox implements Initializable
{
    private final BuildingPart buildingPart;

    @FXML
    private Label nameLabel;

    @FXML
    private VBox buildingMaterialContainerBox;

    public BuildingPartComponent( BuildingPart buildingPart )
    {
        this.buildingPart = buildingPart;

        FXMLLoader fxmlLoader = new FXMLLoader( getClass().getResource( "../../../projectBuildingPartView.fxml" ) );
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
    public void initialize( URL url, ResourceBundle resourceBundle )
    {
        this.nameLabel.setText( this.buildingPart.getName() );

        for( BuildingMaterialContainer buildingMaterialContainer : buildingPart.getBuildingMaterialContainers() )
        {
            buildingMaterialContainerBox.getChildren().add( new BuildingMaterialContainerComponent( buildingMaterialContainer ) );
        }
    }
}
