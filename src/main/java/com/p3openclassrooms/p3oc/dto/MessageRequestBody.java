package com.p3openclassrooms.p3oc.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class MessageRequestBody {
    private String message;
    private Long user_id;
    private Long rental_id;
}
