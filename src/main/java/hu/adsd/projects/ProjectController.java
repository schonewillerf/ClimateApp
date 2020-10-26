package hu.adsd.projects;

import hu.adsd.ClimateApp;
import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
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
    Project project;

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
        this.project = ClimateApp.getProject();

        nameColumn.setCellValueFactory( new PropertyValueFactory<>( "name" ) );
        embodiedColumn.setCellValueFactory( new PropertyValueFactory<>( "embodiedEnergy" ) );
        productsColumn.setCellValueFactory( new PropertyValueFactory<>( "numberOfProducts" ) );

        ObservableList<ProductsConfiguration> configurations = FXCollections.observableArrayList(project.getConfigurations());

        productsConfigurationTable.setItems( configurations );

        productsConfigurationTable.getSelectionModel().select( 0 );
    }

    public void goToConfiguration() throws IOException
    {
        ClimateApp.goToScreen( "projectProductsConfigurationView" );
    }

    public void goToProductsConfigurationCompare() throws IOException
    {
        ClimateApp.goToScreen( "projectProductsConfigurationCompareView" );
    }

    public void addConfiguration()
    {
        ProductsConfiguration productsConfiguration = new ProductsConfiguration(
                "Nieuwe Configuratie"
        );

        project.getConfigurations().add( productsConfiguration );

        productsConfigurationTable.getItems().add( productsConfiguration );
    }

    public void copyConfiguration()
    {
        ProductsConfiguration productsConfiguration = new ProductsConfiguration(
                "Kopie van " + project.getConfigurations().get( getSelectedIndex() ).getName()
        );

        project.getConfigurations().add( productsConfiguration );

        productsConfigurationTable.getItems().add( productsConfiguration );
    }

    public void editConfiguration() throws IOException
    {
        ClimateApp.goToScreen( "projectProductsConfigurationView", getSelectedIndex() );
    }

    public void deleteConfiguration()
    {
        int selectedIndex = getSelectedIndex();

        project.getConfigurations().remove( selectedIndex );

        productsConfigurationTable.getItems().remove( selectedIndex );
    }

    private int getSelectedIndex()
    {
        return productsConfigurationTable.getSelectionModel().getSelectedIndex();
    }
}
