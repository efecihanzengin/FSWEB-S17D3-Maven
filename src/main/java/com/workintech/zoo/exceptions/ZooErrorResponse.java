package com.workintech.zoo.exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ZooErrorResponse {
    private String message;
    private int status;
    private long timestamp;
}
