package hu.adsd.projects;

import hu.adsd.products.Product;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

import java.net.URL;
import java.util.ResourceBundle;

public class BuildingPartController implements Initializable
{
    private BuildingPart buildingPart;

    @FXML
    private Label nameLabel;

    public BuildingPartController( BuildingPart buildingPart )
    {
        this.buildingPart = buildingPart;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle)
    {
        nameLabel.setText( buildingPart.getName() );
//        for ( Product product : buildingPart.getProducts() )
//        {
//
//        }
    }
}
