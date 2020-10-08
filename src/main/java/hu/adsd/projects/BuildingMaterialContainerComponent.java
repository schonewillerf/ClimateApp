package hu.adsd.projects;

import hu.adsd.buildingmaterials.CirculationType;
import hu.adsd.buildingmaterials.Product;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.layout.HBox;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class BuildingMaterialContainerComponent extends HBox implements Initializable
{
    private final BuildingMaterialContainer buildingMaterialContainer;

    public BuildingMaterialContainerComponent( BuildingMaterialContainer buildingMaterialContainer )
    {
        this.buildingMaterialContainer = buildingMaterialContainer;

        FXMLLoader fxmlLoader = new FXMLLoader( getClass().getResource( "../../../buildingMaterialContainerView.fxml" ) );
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
        Product product = this.buildingMaterialContainer.getProduct();

        // Create the product card
        this.getChildren().add( new projectBuildingMaterialCard( product ) );

        // Create the configuration cards
        for ( CirculationType circulationType : buildingMaterialContainer.getCirculationTypes() )
        {
            this.getChildren().add( new projectBuildingMaterialConfigCard( circulationType, product ) );
        }

    }
}
