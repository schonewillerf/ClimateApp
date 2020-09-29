package hu.adsd;

import org.sqlite.SQLiteDataSource;

import java.sql.*;
import java.util.ArrayList;

public class DatabaseHandler
{
    //Returns a list of all products in database
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

    public Product getProductById( int id ) {
        Product product = null;

        SQLiteDataSource dataSource = new SQLiteDataSource();
        dataSource.setUrl("jdbc:sqlite:src/main/resources/database_sqlite.db");

        //Connection with database started, closes automatically at end of code block
        try ( Connection c = dataSource.getConnection() )
        {
            String sql = "SELECT * FROM products WHERE id=" + String.valueOf(id) + ";";

            try
                    (
                            Statement stmt = c.createStatement();
                            ResultSet rs = stmt.executeQuery(sql)
                    )
            {
                while ( rs.next() )
                {
                    int dataId = rs.getInt( "id" );
                    String name = rs.getString( "name" );
                    Double minCarbon = rs.getDouble( "co2_min" );
                    Double maxCarbon = rs.getDouble("co2_max");
                    String circulationType = rs.getString( "materialtype" );
                    int quantity = rs.getInt( "units" );


                    product = new Product( dataId, name, minCarbon, maxCarbon, CirculationType.valueOf( circulationType ), quantity );
                }
            }
        }
        catch ( Exception e )
        {
            e.printStackTrace();
        }
        return product;
    }

    public void updateProduct( Product product ) throws SQLException
    {
        SQLiteDataSource dataSource = new SQLiteDataSource();
        dataSource.setUrl("jdbc:sqlite:src/main/resources/database_sqlite.db");

        String SQL = "UPDATE products SET units= ? WHERE id = ?";

        try (
                Connection connection = dataSource.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(SQL)
        )
        {
            preparedStatement.setInt(1, product.getQuantity());
            preparedStatement.setInt(2, product.getId());


            int betrokkenRijen = preparedStatement.executeUpdate();

            if (betrokkenRijen != 1)
            {
                throw new SQLException(
                        String.format("Updaten van meetmoment gefaald, %s rijen geupdatet", betrokkenRijen)
                );
            }
        }
    }

    public void removeProductById( int id ) throws SQLException
    {
        SQLiteDataSource dataSource = new SQLiteDataSource();
        dataSource.setUrl("jdbc:sqlite:src/main/resources/database_sqlite.db");

        String SQL = "DELETE from products WHERE id = ?";

        try (
                Connection connection = dataSource.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(SQL)
        )
        {
            preparedStatement.setInt(1, id);

            int betrokkenRijen = preparedStatement.executeUpdate();

            if (betrokkenRijen != 1)
            {
                throw new SQLException(
                        String.format("Updaten van meetmoment gefaald, %s rijen geupdatet", betrokkenRijen)
                );
            }
        }
    }

    //Returns a list of all products in database
    private ArrayList<Product> parseDatabase()
    {
        ArrayList<Product> productArrayList = new ArrayList<>();

        SQLiteDataSource dataSource = new SQLiteDataSource();
        dataSource.setUrl("jdbc:sqlite:src/main/resources/database_sqlite.db");

        //Connection with database started, closes automatically at end of code block
        try ( Connection c = dataSource.getConnection() )
        {
            String sql = "SELECT * FROM products;";

            //Run SQL query and get the results, closes automatically at end of code block
            try
                    (
                            Statement stmt = c.createStatement();
                            ResultSet rs = stmt.executeQuery(sql)
                    )
            {
                //each item in database initialized as a Product and put into ArrayList
                while ( rs.next() )
                {
                    int id = rs.getInt( "id" );
                    String name = rs.getString( "name" );
                    Double minCarbon = rs.getDouble( "co2_min" );
                    Double maxCarbon = rs.getDouble("co2_max");
                    String circulationType = rs.getString( "materialtype" );
                    int quantity = rs.getInt( "units" );


                    Product product = new Product( id, name, minCarbon, maxCarbon, CirculationType.valueOf( circulationType ), quantity );
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
