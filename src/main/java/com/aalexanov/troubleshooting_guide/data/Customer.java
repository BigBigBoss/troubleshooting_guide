package com.aalexanov.troubleshooting_guide.data;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@AllArgsConstructor
@Builder
@Data
public class Customer {

    private String userName;
    private String address;
    private String bankAccount;
    private Integer rating;
}
