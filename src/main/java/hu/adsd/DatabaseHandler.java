package hu.adsd;

import org.sqlite.SQLiteDataSource;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class DatabaseHandler
{
    public ArrayList<Product> getMaterialList()
    {
        try
        {
            return parseDatabase();
        }
        catch ( Exception e )
        {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    public Product getProductById( int id )
    {
        return null;
    }

    public void updateProduct( Product product )
    {
        //
    }

    public void removeProductById( int id )
    {
        //
    }

    private ArrayList<Product> parseDatabase()
    {
        ArrayList<Product> productArrayList = new ArrayList<>();

        SQLiteDataSource dataSource = new SQLiteDataSource();
        dataSource.setUrl("jdbc:sqlite:src/main/resources/database_sqlite.db");
        try ( Connection c = dataSource.getConnection() )
        {
            String sql = "SELECT * FROM products;";

            try
                    (
                            Statement stmt = c.createStatement();
                            ResultSet rs = stmt.executeQuery(sql)
                    )
            {
                while ( rs.next() )
                {
                    int id = rs.getInt( "id" );
                    String name = rs.getString( "name" );
                    String carbon = rs.getString( "co2" );
                    int quantity = rs.getInt( "units" );


                    Product product = new Product( id, name, carbon, CirculationType.NEW, quantity );
                    productArrayList.add( product );
                }
            }
        }
        catch ( Exception e )
        {
            e.printStackTrace();
        }

        return productArrayList;
    }
}
