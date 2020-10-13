package hu.adsd.projects;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import hu.adsd.ClimateApp;

public class ProjectController implements Initializable {
    @FXML
    private VBox buildingPartsBox;

    @FXML
    private HBox titleBox;

    @FXML
    private HBox totalsBox;

    @FXML
    private Button addConfigButton;

    private List<String> configurationList;

    private Project project;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        
        this.project = ClimateApp.getProject();

        // add titles to hbox
        for (String configurationTitle : project.getProjectConfigurations()) {
            titleBox.getChildren().add(new ConfigurationTitleComponent(configurationTitle));
        }

        // add content to table
        for (BuildingPart buildingPart : project.getProjectBuildingParts()) {
            buildingPartsBox.getChildren().add(new BuildingPartComponent(buildingPart));
        }

        totalsBox.getChildren().add(new ProductsTotalComponent());
        // add totals below table
        for (String configurationTitle : project.getProjectConfigurations()) {
            totalsBox.getChildren().add(new TotalsComponent(configurationTitle));
        }

    }

    public void goToBuildingMaterials() throws IOException
    {
        ClimateApp.goToScreen("productsListView");
    }

    public void addConfiguration() throws IOException
    {
        if(project.getProjectConfigurations().size()<5)
        {
            project.getProjectConfigurations().add("Mijn nieuwe configuratie");
        }else
        {
            // button disabled maken met andere refresh functie
        }

        // temporarily reload screen
        ClimateApp.goToScreen("projectView");
    }
}
