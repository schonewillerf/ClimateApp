package hu.adsd.projects;

import hu.adsd.ClimateApp;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ProjectController implements Initializable
{
    @FXML
    private VBox buildingPartsBox;

    @FXML
    private HBox titleBox;

    @FXML
    private HBox totalsBox;

    private Project project;

    @Override
    public void initialize( URL url, ResourceBundle resourceBundle )
    {

        this.project = ClimateApp.getProject();

        createTitleBar();

        createTableContent();

        createTotalsBar();
    }

    private void createTitleBar()
    {
        // Create Title for BuildingPart & BuildingMaterials Column
        titleBox.getChildren().add( new ProductsTitleComponent() );

        // Create Title for Each ProjectConfiguration in Project
        for ( String configurationTitle : project.getProjectConfigurations(1) )
        {
            titleBox.getChildren().add( new ConfigurationTitleComponent( configurationTitle ) );
        }
    }

    private void createTableContent()
    {
        // Create BuildingParts with inner BuildingMaterials
        for ( BuildingPart buildingPart : project.getProjectBuildingParts() )
        {
            buildingPartsBox.getChildren().add( new BuildingPartComponent( buildingPart ) );
        }
    }

    private void createTotalsBar()
    {
        // Create Total for BuildingPart & BuildingMaterials Column
        totalsBox.getChildren().add( new ProductsTotalComponent() );

        for (int i = 0; i < project.getProjectConfigurations(1).size(); i++ )
        {
            totalsBox.getChildren().add(
                    new TotalsComponent(
                            project.getProjectConfigurationTotalCarbon( i ),
                            project.getProjectConfigurationTotalEnergy( i )
                    ) );
        }

    }

    // onAction method for goToBuildingMaterialsButton
    public void goToBuildingMaterials() throws IOException
    {
        ClimateApp.goToScreen( "productsListView" );
    }

    // onAction method for addConfigurationButton
    public void addConfiguration() throws IOException
    {
        project.addConfiguration();

        // temporarily reload screen
        ClimateApp.goToScreen( "projectView" );
    }
}
