package com.project.reggie.dto;

import com.project.reggie.domain.Setmeal;
import com.project.reggie.domain.SetmealDish;
import lombok.Data;

import java.util.List;

@Data
public class SetmealDto extends Setmeal {

    private List<SetmealDish> setmealDishes;

    private String categoryName;
}
