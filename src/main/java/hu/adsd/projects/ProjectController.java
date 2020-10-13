package hu.adsd.projects;

import hu.adsd.ClimateApp;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class ProjectController implements Initializable
{
    @FXML
    private VBox buildingPartsBox;

    @Override
    public void initialize( URL url, ResourceBundle resourceBundle )
    {
        // voor ne even niet uit db
        List<BuildingPart> buildingParts = new ArrayList<>();
        buildingParts.add( new BuildingPart( "Toilet" ) );
        buildingParts.add( new BuildingPart( "Dak" ) );
        buildingParts.add( new BuildingPart( "Badkamer" ) );


        for( BuildingPart buildingPart : buildingParts )
        {
            buildingPartsBox.getChildren().add( new BuildingPartComponent( buildingPart ) );
        }

    }

    public void goToBuildingMaterials() throws IOException
    {
        ClimateApp.goToScreen( "productsListView" );
    }
}
