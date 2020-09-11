package hu.adsd;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ClimateApp
{
    private final Project project;
    private final List<Material> availableMaterials;
    private final Scanner scanner;

    public ClimateApp()
    {
        // Initialisation
        this.project = new Project();
        this.availableMaterials = new ArrayList<>();
        this.scanner = new Scanner( System.in );

        // Create some data
        availableMaterials.add( new Material( 1, "Wasbak", 0, CirculationType.REUSED, 10 ) );
        availableMaterials.add( new Material( 2, "Wasbak", 30, CirculationType.RECYCLED, 35 ) );
        availableMaterials.add( new Material( 3, "Wasbak", 100, CirculationType.NEW, 80 ) );
        availableMaterials.add( new Material( 4, "Toilet", 0, CirculationType.REUSED, 12 ) );
        availableMaterials.add( new Material( 5, "Toilet", 40, CirculationType.RECYCLED, 10 ) );
        availableMaterials.add( new Material( 6, "Toilet", 150, CirculationType.NEW, 100 ) );
        availableMaterials.add( new Material( 7, "Kraan", 0, CirculationType.REUSED, 30 ) );
        availableMaterials.add( new Material( 8, "Kraan", 60, CirculationType.RECYCLED, 40 ) );
        availableMaterials.add( new Material( 9, "Kraan", 200, CirculationType.NEW, 10 ) );

        // Show UI in Console
        showUI();
    }

    private void showUI()
    {
        while ( true )
        {
            // Option menu
            System.out.println( "\nOptions:" );
            System.out.println( "1. Show available products" );
            System.out.println( "2. Show products used in project" );
            System.out.println( "3. Quit program" );
            String answer = scanner.next();

            if ( answer.equals( "1" ) )
            {
                System.out.println( "\nAvailable products:" );

                // Display available products
                for ( int i = 0; i < availableMaterials.size(); i++ )
                {
                    System.out.printf( "%s. %s\n", i + 1, availableMaterials.get( i ).toString() );
                }

                System.out.println( "\nChoose product to add to project or press 0 to return:" );

                String materialSelected = scanner.next();

                if ( !materialSelected.equals( "0" ))
                {
                    Material material = availableMaterials.get( Integer.parseInt( materialSelected ) - 1 );

                    System.out.println( "\nEnter amount of product:" );

                    String amountOfProduct = scanner.next();

                    material.setQuantity( Integer.parseInt( amountOfProduct ) );

                    project.addProjectMaterial( material );
                }
            }
            else if ( answer.equals( "2" ) )
            {
                // Check project material not empty
                if ( project.getProjectMaterials().size() == 0 )
                {
                    System.out.println( "\nThere are no products used in project" );
                }
                else
                {
                    System.out.println( "\nThe following product are used in project" );

                    int totalAmountOfCarbon = 0;

                    for ( int i = 0; i < project.getProjectMaterials().size(); i++)
                    {
                        System.out.printf( "%s. %s\n", i + 1, project.getProjectMaterial( i ).toString() );

                        totalAmountOfCarbon += project.getProjectMaterial( i ).getTotalCarbonAmount();
                    }

                    System.out.printf( "The total carbon amount in the project is: %s\n", totalAmountOfCarbon);

                    System.out.println( "\nChoose item to editor or press 0 to return:" );
                    String itemSelected = scanner.next();

                    if ( !itemSelected.equals( "0" ))
                    {
                        System.out.println( "\nEdit quantity of product:" );
                        String quantity = scanner.next();

                        if ( quantity.equals( "0" ) )
                        {
                            project.removeProjectMaterial( Integer.parseInt( itemSelected ) );
                        }
                        else
                        {
                            int index = Integer.parseInt( itemSelected ) - 1;

                            Material material = project.getProjectMaterial( index );
                            material.setQuantity( Integer.parseInt( quantity ) );

                            project.updateProjectMaterial( index, material );
                        }
                    }
                }
            }
            else
            {
                return;
            }
        }
    }
}