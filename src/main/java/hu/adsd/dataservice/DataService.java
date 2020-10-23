package hu.adsd.dataservice;

import hu.adsd.products.Product;
import hu.adsd.products.ProductSort;
import hu.adsd.projects.Project;

import java.util.List;

public interface DataService
{
    public List<Product> getProductsFilteredSortedAndByRoom( String filter, ProductSort productsort, String room );

    public Project getProject();

    public void updateProject( Project project );
}
