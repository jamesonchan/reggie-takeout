package com.project.reggie.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.project.reggie.common.CustomException;
import com.project.reggie.domain.Category;
import com.project.reggie.domain.Dish;
import com.project.reggie.domain.Setmeal;
import com.project.reggie.mapper.CategoryMapper;
import com.project.reggie.service.CategoryService;
import com.project.reggie.service.DishService;
import com.project.reggie.service.SetmealService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CategoryServiceImpl extends ServiceImpl<CategoryMapper, Category> implements CategoryService {

    @Autowired
    private DishService dishService;
    @Autowired
    private SetmealService setmealService;

    /**
     * remove by id logic
     *
     * @param id
     */
    @Override
    public void remove(Long id) {
        LambdaQueryWrapper<Dish> dishLambdaQueryWrapper = new LambdaQueryWrapper<>();
        dishLambdaQueryWrapper.eq(Dish::getCategoryId, id);
        int dishCount = (int) dishService.count(dishLambdaQueryWrapper);
        if (dishCount > 0) {
            throw new CustomException("This category is linked to a dish");
        }

        LambdaQueryWrapper<Setmeal> setmealLambdaQueryWrapper = new LambdaQueryWrapper<>();
        setmealLambdaQueryWrapper.eq(Setmeal::getCategoryId, id);
        int setmealCount = (int) setmealService.count(setmealLambdaQueryWrapper);
        if (setmealCount > 0) {
            throw new CustomException("This category is linked to a setmeal");
        }

        super.removeById(id);
    }
}
