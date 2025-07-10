package Entity;

import lombok.*;
import model.OrderDetails;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Data
public class OrderEntity {
    private  String orderId;
    private String date;
    private String userId;
   // private List<OrderDetails> orderDetails;
}
