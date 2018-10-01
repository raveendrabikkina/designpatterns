package typesofdesignpatterns.creational.builder;

import java.util.List;

/**
 * Created by ravi on 1/10/18.
 */
public class Builder {
    private int id;
    private String name;
    private String colour;
    private List<SpecialProduct> specialProducts;

    public int getId() {
        return id;
    }

    public void setId(final int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public String getColour() {
        return colour;
    }

    public void setColour(final String colour) {
        this.colour = colour;
    }

    public List<SpecialProduct> getSpecialProducts() {
        return specialProducts;
    }

    public void setSpecialProducts(final List<SpecialProduct> specialProducts) {
        this.specialProducts = specialProducts;
    }
}
