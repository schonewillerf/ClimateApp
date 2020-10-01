package hu.adsd;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class ProjectController implements Initializable
{
    @FXML
    private TableView<Product> materialTable;

    @FXML
    private TableColumn<Product, String> productColumn;

    @FXML
    private TableColumn<Product, CirculationType> circulationColumn;

    @FXML
    private TableColumn<Product, Integer> carbonMinColumn;

    @FXML
    private TableColumn<Product, Integer> carbonMaxColumn;

    @FXML
    private TableColumn<Product, Integer> amountColumn;

    @FXML
    private TextField editField;

    @FXML
    private Button editButton;

    @FXML
    private Button deleteButton;

    @FXML
    private Label labelCarbonCurrent;

    @FXML
    private Label labelCarbonNew;

    @FXML
    private Label labelCarbonDifference;

    @FXML
    void editList(ActionEvent event)
    {

        switch(editButton.getText())
        {
            case"Bewerken":

                if(!materialTable.getSelectionModel().getSelectedItems().isEmpty())
                {
                    System.out.println("bewerk knop ingedrukt!"+materialTable.getSelectionModel().getSelectedItems());

                    editField.setEditable(true);

                    editButton.setText("Annuleren");
                    deleteButton.setText("Opslaan");
                }
                else
                {
                    System.out.println("Selecteer eerst een rij voor u wilt bewerken!");
                }

                break;

            case"Annuleren":
                System.out.println("annuleer knop ingedrukt!");

                editField.setEditable(false);
                editButton.setText("Bewerken");
                deleteButton.setText("Verwijderen");
                editField.setText("");

                break;
        }

    }

    @FXML
    void deleteListItem(ActionEvent event) throws IOException
    {
        int index = materialTable.getSelectionModel().getSelectedIndex();

        switch(deleteButton.getText())
        {
            case"Opslaan":
                int amount = Integer.parseInt(editField.getText());

                System.out.println("\nopslaan knop ingedrukt! \nNieuw Quantity: ");
                System.out.println(amount);

                // opslaan functie

                deleteButton.setText("Verwijderen");
                editButton.setText("Bewerken");
                editField.setText("");
                editField.setEditable(false);

                Product selectedProduct = materialTable.getSelectionModel().getSelectedItem();
                selectedProduct.setQuantity(amount);

                new DatabaseHandler().updateProjectProduct(selectedProduct);

                ClimateApp.goToScreen( "projectView" );

                break;

            case"Verwijderen":

                if(!materialTable.getSelectionModel().getSelectedItems().isEmpty())
                {
                    System.out.println("verwijder knop ingedrukt!");

                    new DatabaseHandler().removeProjectProduct(materialTable.getSelectionModel().getSelectedItem().getId());

                    materialTable.getItems().remove(index);

                    // Update Carbon Totals
                    updateCarbonLabels( materialTable.getItems() );
                }
                else
                {
                    System.out.println("Selecteer eerst een rij voor u wilt verwijderen!");
                }

                break;
        }
    }

    private void mpSelectionChangeAction(int selected)
    {
        if (selected == -1) // Als er geen rij geselecteerd is in de tableview
        {
            System.out.println("geen selected");
            editButton.setDisable(true);
            deleteButton.setDisable(true);
        } else // Als er wel een rij geselecteerd is in de tableview
        {
            System.out.println("wel selected");
            editButton.setDisable(false);
            deleteButton.setDisable(false);
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources)
    {
        editField.setEditable(false);
        editButton.setDisable(true);
        deleteButton.setDisable(true);

        // stap 1 arraylist uit db ophalen
        DatabaseHandler db = new DatabaseHandler();
        List<Product> materialen = db.getProjectProductsList();

        // stap 2 observable arraylist maken (javafx functie)
        final ObservableList<Product> materiaal = FXCollections.observableArrayList( materialen);

        // stap 3 cell value properties
        productColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        circulationColumn.setCellValueFactory(new PropertyValueFactory<>("circulationType"));
        carbonMinColumn.setCellValueFactory(new PropertyValueFactory<>("minCarbonAmount"));
        carbonMaxColumn.setCellValueFactory(new PropertyValueFactory<>("maxCarbonAmount"));
        amountColumn.setCellValueFactory(new PropertyValueFactory<>("quantity"));

        // stap 4 set items van de tabel
        materialTable.setItems(materiaal);
        materialTable.getSelectionModel().selectedIndexProperty().addListener((o, oldVal, newVal) -> {
            mpSelectionChangeAction(newVal.intValue());
        });

        updateCarbonLabels( materialTable.getItems() );

        // wanneer table leeg is, table weg en een label toevoegen
    }

    // Method for handling button click to go to other screen
    public void goToProducts() throws IOException
    {
        ClimateApp.goToScreen( "productsListView" ); // Change the Scene
    }

    /**
     * Update the carbon labels
     *
     * @param products Any type of List ie ObservableList
     */
    private void updateCarbonLabels( List<Product> products )
    {
        // Initialise local variables
        double carbonCurrent = 0;
        double carbonNew = 0;

        // Loop over products in project
        for ( Product product : products )
        {
            // Increment carbon totals
            carbonCurrent += product.getTotalMinCarbonAmount();
            carbonNew += product.getTotalMaxCarbonAmount();
        }

        // Update labels
        labelCarbonCurrent.setText( Double.toString( carbonCurrent ) );
        labelCarbonNew.setText( Double.toString( carbonNew ) );
        labelCarbonDifference.setText( Double.toString( carbonNew - carbonCurrent ) );
    }
}
