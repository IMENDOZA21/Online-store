package academy.digitallab.store.shopping.model;
import lombok.Data;

import java.util.Date;

@Data
public class Product {
    private long id;
    private String name;
    private String description;
    private Double stock;
    private Double price;
    private String status;
    private Date createAt;
    private Category category;
}
