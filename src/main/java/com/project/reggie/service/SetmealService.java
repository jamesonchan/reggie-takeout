package com.project.reggie.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.project.reggie.domain.Setmeal;
import com.project.reggie.dto.SetmealDto;

import java.util.List;

public interface SetmealService extends IService<Setmeal> {
    void saveWithDish(SetmealDto setmealDto);

    void removeWithDish(List<Long> ids);
}
