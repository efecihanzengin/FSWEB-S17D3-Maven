package com.workintech.zoo.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.EqualsAndHashCode;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode
public class Kangaroo {
    private long id;
    private String name;
    private double height;
    private double weight;
    private String gender;
    private Boolean isAggressive;

}