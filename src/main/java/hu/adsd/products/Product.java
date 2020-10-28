package hu.adsd.products;

public class Product
{
    private int id;
    private final String name;
    private int quantity;
    private final CirculationType circulationType;
    private final double carbon;
    private final double energy;

    // These values are similar for many products
    // Hint, there is a possibility for data normalisation
    private final double refCarbon;
    private final double refEnergy;
    private final String buildingPart;

    public Product(
            int id,
            String name,
            int quantity,
            CirculationType circulationType,
            double carbon,
            double energy,
            double refCarbon,
            double refEnergy,
            String buildingPart
    )
    {
        this.id = id;
        this.name = name;
        this.quantity = quantity;
        this.circulationType = circulationType;
        this.carbon = carbon;
        this.energy = energy;
        this.refCarbon = refCarbon;
        this.refEnergy = refEnergy;
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

    public double getCarbon()
    {
        return carbon;
    }

    public double getEnergy()
    {
        return energy;
    }

    public String getEmbodiedCandE()
    {
        return String.format( "%.2f mg / %.2f kJ", this.carbon, this.energy );
    }

    public int getQuantity()
    {
        return quantity;
    }

    public void setQuantity( int quantity )
    {
        this.quantity = quantity;
    }

    public String getCirculationType()
    {
        return String.valueOf( circulationType );
    }

    public String getImagePath()
    {
        return String.format( "../../../images/%s.jpg", this.id );
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
                this.carbon,
                this.energy,
                this.quantity
        );
    }

    public double getTotalEmbodiedCarbon( double factor)
    {
        return this.carbon * this.quantity * factor;
    }

    public double getTotalEmbodiedEnergy( double factor )
    {
        return this.energy * this.quantity * factor;
    }
}
