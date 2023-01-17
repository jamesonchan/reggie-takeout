package com.project.reggie.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.project.reggie.domain.Dish;
import com.project.reggie.domain.DishFlavor;
import com.project.reggie.dto.DishDto;
import com.project.reggie.mapper.DishMapper;
import com.project.reggie.service.DishFlavorService;
import com.project.reggie.service.DishService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class DishServiceImpl extends ServiceImpl<DishMapper, Dish> implements DishService {

    @Autowired
    private DishFlavorService dishFlavorService;

    /**
     * add new dish and save corresponding flavor data at the same time
     *
     * @param dishDto
     */
    @Override
    @Transactional
    public void saveWithFlavor(DishDto dishDto) {
        this.save(dishDto);

        Long dishId = dishDto.getId();
        List<DishFlavor> flavorList = dishDto.getFlavorList();
        List<DishFlavor> flavorCollect = flavorList
                .stream()
                .peek((item) -> item.setDishId(dishId))
                .collect(Collectors.toList());

        dishFlavorService.saveBatch(flavorCollect);
    }
}
