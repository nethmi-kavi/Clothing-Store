package model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Supplier {
    private String id;
    private String name;
    private String regNo;
    private String mobile;
    private String address;
    private String email;
}
