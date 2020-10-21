package hu.adsd.buildingmaterials;

public class Product
{
    private int id;
    private final String name;
    private final double minCarbonAmount;
    private final double maxCarbonAmount;
    private int quantity;
    private final String buildingPart;

    public Product(
            int id,
            String name,
            double minCarbon,
            double maxCarbon,
            int quantity,
            String buildingPart
    )
    {
        this.id = id;
        this.name = name;
        this.minCarbonAmount = minCarbon;
        this.maxCarbonAmount = maxCarbon;
        this.quantity = quantity;
        this.buildingPart = buildingPart;
    }

    public int getId()
    {
        return id;
    }

    public void setId( int id )
    {
        this.id = id;
    }

    public String getName()
    {
        return name;
    }

    public double getMinCarbonAmount()
    {
        return minCarbonAmount;
    }

    public double getMaxCarbonAmount()
    {
        return maxCarbonAmount;
    }

    public int getQuantity()
    {
        return quantity;
    }

    public void setQuantity( int quantity )
    {
        this.quantity = quantity;
    }

    public String getImagePath()
    {
        return String.format( "../../../images/%s.jpg", this.id );
    }

    public void setImagePath()
    {
        // This is an empty method
    }

    public String getBuildingPart()
    {
        return buildingPart;
    }

    @Override
    public String toString()
    {
        return String.format(
                "Id: %s, Naam: %s, " +
                        "Minimale hoeveelheid koolstof: %s, " +
                        "Maximale hoeveelheid koolstof: %s, " +
                        "Kwantiteit: %s,",
                this.id,
                this.name,
                this.minCarbonAmount,
                this.maxCarbonAmount,
                this.quantity
        );
    }

    public double getTotalMinCarbonAmount()
    {
        return this.minCarbonAmount * this.quantity;
    }

    public double getTotalMaxCarbonAmount()
    {
        return this.maxCarbonAmount * this.quantity;
    }

    public double getTotalEmbodiedCarbon( double factor)
    {
        return this.minCarbonAmount * this.quantity * factor;
    }

    public double getTotalEmbodiedEnergy( double factor )
    {
        return this.maxCarbonAmount * this.quantity * factor;
    }
}
