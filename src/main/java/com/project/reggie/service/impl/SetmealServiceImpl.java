package com.project.reggie.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.project.reggie.domain.Setmeal;
import com.project.reggie.domain.SetmealDish;
import com.project.reggie.dto.SetmealDto;
import com.project.reggie.mapper.SetmealMapper;
import com.project.reggie.service.SetmealDishService;
import com.project.reggie.service.SetmealService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class SetmealServiceImpl extends ServiceImpl<SetmealMapper, Setmeal> implements SetmealService {

    @Autowired
    private SetmealDishService setmealDishService;

    /**
     * add setmeal and save setmeal and dish relationship
     *
     * @param setmealDto
     */
    @Override
    @Transactional
    public void saveWithDish(SetmealDto setmealDto) {
        this.save(setmealDto);
        List<SetmealDish> setmealDishes = setmealDto.getSetmealDishes();
        setmealDishes
                .stream()
                .peek(item -> item.setSetmealId(setmealDto.getId()))
                .collect(Collectors.toList());
        setmealDishService.saveBatch(setmealDishes);
    }
}
