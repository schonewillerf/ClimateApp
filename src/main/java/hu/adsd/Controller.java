package hu.adsd;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.image.ImageView;

public class Controller
{
    @FXML
    private ImageView OffImage2, TableViewAwayGlassImage;

    @FXML
    private Button AddButton, TableViewButton, TableViewLargeAwayButton;

    @FXML
    private TableView TableViewLarge;


    public void closeApp( ActionEvent event )
    {
        System.exit( 0 );
    }

    public void OpenTableViewLarge( ActionEvent event )
    {
        TableViewLarge.setVisible( true );
        TableViewLargeAwayButton.setVisible( true );
        TableViewAwayGlassImage.setVisible( true );
    }

    public void CloseTableViewLarge( ActionEvent event )
    {
        TableViewLarge.setVisible( false );
        TableViewLargeAwayButton.setVisible( false );
        TableViewAwayGlassImage.setVisible( false );
    }
}
