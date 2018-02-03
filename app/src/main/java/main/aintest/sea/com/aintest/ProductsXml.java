package main.aintest.sea.com.aintest;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;
import java.util.List;



@Root(name = "xml")
public class ProductsXml{
    @ElementList(inline = true)
    public List<Products> products;
}
@Root(name = "products")
class Products{
    @ElementList(inline = true)
    public List<Product> product;
}

@Root(name = "product")
class Product {

    @Element(name = "id")
    private int id;
    @Element(name = "name")
    private String name;
    @Element(name = "price")
    private String price;

    public Product() {
    }

    public Product(String name, String price) {
        this.name = name;
        this.price = price;
    }

    public Product(int id, String name, String price) {
        this.id = id;
        this.name = name;
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }
}
