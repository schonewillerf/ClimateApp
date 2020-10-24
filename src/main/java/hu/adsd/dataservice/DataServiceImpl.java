package hu.adsd.dataservice;

import hu.adsd.products.Product;
import hu.adsd.products.ProductSort;
import hu.adsd.projects.Project;

import java.util.List;

/**
 * This is the implementation class DataService interface
 *
 * This is the class that should be used from within other packages
 * Here we decide once which data provider to use for a specific method
 * This could be useful for us during development
 *
 * BTW DatabasHandler class is package private, meaning it is not visible from outside the package
 */
public class DataServiceImpl implements DataService
{
    @Override
    public List<Product> getProductsFilteredSortedAndByRoom( String filter, ProductSort productsort, String room )
    {
        return new DatabaseHandler().getProductsFilteredSortedAndByRoomFromAPI( filter, productsort, room );
    }

    @Override
    public Project getProject()
    {
        return null;
    }

    @Override
    public void updateProject( Project project )
    {
        // This method is still empty
    }
}
