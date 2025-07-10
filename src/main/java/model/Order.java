package model;

import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Order {
    private  String orderId;
    private String date;
    private String userId;
    private List<OrderDetails> orderDetails;
}
