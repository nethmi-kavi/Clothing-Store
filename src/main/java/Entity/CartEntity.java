package Entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class CartEntity {
    private String id;
    private String desc;
    private int qty;
    private  double unitPrice;
    private double total;
}