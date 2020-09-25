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

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class Controller implements Initializable
{
    @FXML
    private TableView<Material> materialTable;

    @FXML
    private TableColumn<Material, String> productColumn;

    @FXML
    private TableColumn<Material, CirculationType> circulationColumn;

    @FXML
    private TableColumn<Material, Integer> carbonColumn;

    @FXML
    private TableColumn<Material, Integer> amountColumn;

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
                } else
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
        switch(deleteButton.getText())
        {
            case"Opslaan":
                System.out.println("opslaan knop ingedrukt!");

                // opslaan functie

                deleteButton.setText("Verwijderen");
                editButton.setText("Bewerken");

                break;

            case"Verwijderen":

                if(!materialTable.getSelectionModel().getSelectedItems().isEmpty())
                {
                    System.out.println("verwijder knop ingedrukt!");
                } else
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
        
        // stap 1 arraylist maken
        ArrayList<Material> materialen = new ArrayList<Material>();
        materialen.add(new Material(1, "Kraan", "200", CirculationType.RECYCLED, 20));
        materialen.add(new Material(2, "Wasbak", "350", CirculationType.REUSED, 7));
        materialen.add(new Material(3, "Douche", "1400", CirculationType.NEW, 2));
        

        // stap 2 observable arraylist maken (javafx functie)
        final ObservableList<Material> materiaal = FXCollections.observableArrayList(materialen);

        // stap 3 cell value properties
        productColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        circulationColumn.setCellValueFactory(new PropertyValueFactory<>("circulationType"));
        carbonColumn.setCellValueFactory(new PropertyValueFactory<>("carbonAmount"));
        amountColumn.setCellValueFactory(new PropertyValueFactory<>("quantity"));
        
        // stap 4 set items van de tabel
        materialTable.setItems(materiaal);
    }

}
