package hu.adsd.projects;

import hu.adsd.ClimateApp;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ProjectController implements Initializable
{
    @FXML
    private TableView<ProductsConfiguration> productsConfigurationTable;

    @FXML
    private TableColumn<ProductsConfiguration, String> nameColumn;

    @FXML
    private TableColumn<ProductsConfiguration, String> embodiedColumn;

    @FXML
    private TableColumn<ProductsConfiguration, Integer> productsColumn;

    @Override
    public void initialize( URL url, ResourceBundle resourceBundle )
    {
        Project project = ClimateApp.getProject();

        nameColumn.setCellValueFactory( new PropertyValueFactory<>( "name" ) );
        embodiedColumn.setCellValueFactory( new PropertyValueFactory<>( "embodiedEnergy" ) );
        productsColumn.setCellValueFactory( new PropertyValueFactory<>( "numberOfProducts" ) );

        ObservableList<ProductsConfiguration> configurations = FXCollections.observableArrayList(project.getConfigurations());

        productsConfigurationTable.setItems( configurations );
    }

    public void goToConfiguration() throws IOException
    {
        ClimateApp.goToScreen( "projectProductsConfigurationView" );
    }

    public void goToProductsConfigurationCompare() throws IOException
    {
        ClimateApp.goToScreen( "projectProductsConfigurationCompareView" );
    }
}
