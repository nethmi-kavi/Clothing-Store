package model;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Data
public class OrderDetails {

    private String id;
    private String itemCode;
    private int qty;
    private double unitPrice;
}
