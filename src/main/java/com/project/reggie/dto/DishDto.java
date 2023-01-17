package com.project.reggie.dto;

import com.project.reggie.domain.Dish;
import com.project.reggie.domain.DishFlavor;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class DishDto extends Dish {
    private List<DishFlavor> flavorList = new ArrayList<>();
    private String categoryName;
    private Integer copies;
}
