package Entity;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Data
public class OrderDetailEntity {

    private String id;
    private String itemCode;
    private int qty;
    private double unitPrice;

}