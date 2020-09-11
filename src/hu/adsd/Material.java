package hu.adsd;

public class Material
{
    private int id;
    private String name;
    private int carbon;
    private RecycleType recycleType;
    private int quantity;

    public Material( int id, String name, int carbon, RecycleType recycleType, int quantity )
    {
        this.id = id;
        this.name = name;
        this.carbon = carbon;
        this.recycleType = recycleType;
        this.quantity = quantity;
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

    public void setName( String name )
    {
        this.name = name;
    }

    public int getCarbon()
    {
        return carbon;
    }

    public void setCarbon( int carbon )
    {
        this.carbon = carbon;
    }

    public RecycleType getRecycleType()
    {
        return recycleType;
    }

    public void setRecycleType( RecycleType recycleType )
    {
        this.recycleType = recycleType;
    }

    public int getQuantity()
    {
        return quantity;
    }

    public void setQuantity( int quantity )
    {
        this.quantity = quantity;
    }

    @Override
    public String toString()
    {
        return String.format(
                "Productnaam: %s, Carbon: %s, Quantity: %s, RecycleType: %s",
                this.name,
                this.carbon,
                this.quantity,
                this.recycleType
        );
    }
}
