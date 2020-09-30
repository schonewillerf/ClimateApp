package hu.adsd;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
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
    private TableColumn<Product, Integer> carbonColumn;

    @FXML
    private TableColumn<Product, Integer> amountColumn;

    @FXML
    private TextField editField;

    @FXML
    private Button editButton;

    @FXML
    private Button deleteButton;

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
    void deleteListItem(ActionEvent event)
    {
        int index = materialTable.getSelectionModel().getSelectedIndex();

        switch(deleteButton.getText())
        {
            case"Opslaan":
                int Aantal = Integer.parseInt(editField.getText());

                System.out.println("\nopslaan knop ingedrukt! \nNieuw Quantity: ");
                System.out.println(Aantal);

                // opslaan functie

                deleteButton.setText("Verwijderen");
                editButton.setText("Bewerken");

                break;

            case"Verwijderen":

                if(!materialTable.getSelectionModel().getSelectedItems().isEmpty())
                {
                    System.out.println("verwijder knop ingedrukt!");
                    materialTable.getItems().remove(index);
                }
                else
                {
                    System.out.println("Selecteer eerst een rij voor u wilt verwijderen!");
                }

                break;
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources)
    {
        editField.setEditable(false);

        // stap 1 arraylist uit db ophalen
        DatabaseHandler db = new DatabaseHandler();
        List<Product> materialen = db.getMaterialList();

        // stap 2 observable arraylist maken (javafx functie)
        final ObservableList<Product> materiaal = FXCollections.observableArrayList( materialen);

        // stap 3 cell value properties
        productColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        circulationColumn.setCellValueFactory(new PropertyValueFactory<>("circulationType"));
        carbonColumn.setCellValueFactory(new PropertyValueFactory<>("carbonAmount"));
        amountColumn.setCellValueFactory(new PropertyValueFactory<>("quantity"));

        // stap 4 set items van de tabel
        materialTable.setItems(materiaal);
    }

    // Method for handling button click to go to other screen
    public void goToProducts() throws IOException
    {
        ClimateApp.goToScreen( "productsListView" ); // Change the Scene
    }

}