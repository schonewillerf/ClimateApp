package hu.adsd;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;

public class Controller implements Initializable
{
    @FXML
    private ImageView OffImage2, TableViewAwayGlassImage;

    @FXML
    private Button AddButton, TableViewButton, TableViewLargeAwayButton;

    @FXML
    private TableView<Material> materialTable;

    @FXML
    private TableColumn<Material, String> productColumn;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
        // TODO Auto-generated method stub
        
        // stap 1 arraylist maken
        //ArrayList<Material> = new ArrayList<Material>();

        // stap 2 observable arraylist maken (javafx functie)
        // ObservableList materialData

        // stap 3 cell value properties
        // productColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        
        // stap 4 set items van de tabel
        // materialTable.setItems(materialData);
	}

    

}
