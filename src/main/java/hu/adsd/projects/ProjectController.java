package hu.adsd.projects;

import hu.adsd.ClimateApp;
import hu.adsd.csvgenerator.CSVGenerator;
import javafx.animation.PauseTransition;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Duration;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.concurrent.TimeUnit;

import static java.util.concurrent.TimeUnit.SECONDS;

public class ProjectController implements Initializable {
    Project project;

    @FXML
    private TableView<ProductsConfiguration> productsConfigurationTable;

    @FXML
    private TableColumn<ProductsConfiguration, String> nameColumn;

    @FXML
    private TableColumn<ProductsConfiguration, String> embodiedColumn;

    @FXML
    private TableColumn<ProductsConfiguration, Integer> productsColumn;

    @FXML
    private ChoiceBox<String> selectConfiguration1;

    @FXML
    private ChoiceBox<String> selectConfiguration2;

    @FXML
    private Label errorText, ExportLabel;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        this.project = ClimateApp.getProject();

        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        embodiedColumn.setCellValueFactory(new PropertyValueFactory<>("embodiedEnergy"));
        productsColumn.setCellValueFactory(new PropertyValueFactory<>("numberOfProducts"));

        ObservableList<ProductsConfiguration> configurations = FXCollections.observableArrayList(project.getConfigurations());

        productsConfigurationTable.setItems(configurations);
        productsConfigurationTable.getSelectionModel().select(0);

        setOrUpdateConfigurationChoiceBoxes();
    }

    private void setOrUpdateConfigurationChoiceBoxes() {
        selectConfiguration1.getItems().clear();
        selectConfiguration2.getItems().clear();

        for (ProductsConfiguration productsConfiguration : project.getConfigurations()) {
            selectConfiguration1.getItems().add(productsConfiguration.getName());
            selectConfiguration2.getItems().add(productsConfiguration.getName());
        }

        selectConfiguration1.getSelectionModel().selectFirst();
        selectConfiguration2.getSelectionModel().selectLast();
    }

    public void goToProductsConfigurationCompare() throws IOException {
        int select1 = selectConfiguration1.getSelectionModel().getSelectedIndex();
        int select2 = selectConfiguration2.getSelectionModel().getSelectedIndex();

        if (select1 != select2) {
            ClimateApp.goToScreen("projectProductsConfigurationCompareView", select1, select2);
        } else {
            // Show error message
            errorText.setVisible(true);
        }
    }

    public void addConfiguration() {
        ProductsConfiguration productsConfiguration = new ProductsConfiguration(
                "Nieuwe Configuratie"
        );

        project.getConfigurations().add(productsConfiguration);

        productsConfigurationTable.getItems().add(productsConfiguration);

        setOrUpdateConfigurationChoiceBoxes();
    }

    public void copyConfiguration() {
        ProductsConfiguration originalConfiguration = project.getConfigurations().get(getSelectedIndex());

        ProductsConfiguration productsConfiguration = new ProductsConfiguration(
                "Kopie van " + originalConfiguration.getName(),
                originalConfiguration.getBuildingParts()
        );

        project.getConfigurations().add(productsConfiguration);

        productsConfigurationTable.getItems().add(productsConfiguration);

        setOrUpdateConfigurationChoiceBoxes();
    }

    public void editConfiguration() throws IOException {
        ClimateApp.goToScreen("projectProductsConfigurationView", getSelectedIndex());
    }

    public void deleteConfiguration() {
        int selectedIndex = getSelectedIndex();

        project.getConfigurations().remove(selectedIndex);

        productsConfigurationTable.getItems().remove(selectedIndex);

        setOrUpdateConfigurationChoiceBoxes();
    }

    private int getSelectedIndex() {
        return productsConfigurationTable.getSelectionModel().getSelectedIndex();
    }

    public void exportConfiguration() throws IOException{
        ProductsConfiguration productsConfiguration = project.getConfigurations().get(getSelectedIndex());

        new CSVGenerator().exportConfiguration(productsConfiguration);

        ExportLabel.setVisible(true);

        PauseTransition visiblePause = new PauseTransition(
                Duration.seconds(3)
        );
        visiblePause.setOnFinished(
                event -> ExportLabel.setVisible(false)
        );
        visiblePause.play();

    }
}
