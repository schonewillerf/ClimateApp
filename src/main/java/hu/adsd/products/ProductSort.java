package hu.adsd.products;

public enum ProductSort
{
    NAME,
    CARBON,
    ENERGY;

    @Override
    public String toString()
    {
        return name().toLowerCase();
    }
}
