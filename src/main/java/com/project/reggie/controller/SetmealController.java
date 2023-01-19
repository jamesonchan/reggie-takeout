package com.project.reggie.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.project.reggie.common.R;
import com.project.reggie.domain.Category;
import com.project.reggie.domain.Setmeal;
import com.project.reggie.dto.SetmealDto;
import com.project.reggie.service.CategoryService;
import com.project.reggie.service.SetmealDishService;
import com.project.reggie.service.SetmealService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/setmeal")
@Slf4j
public class SetmealController {
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private SetmealService setmealService;
    @Autowired
    private SetmealDishService setmealDishService;

    /**
     * add new setmeal
     *
     * @param setmealDto
     * @return
     */
    @PostMapping
    public R<String> save(@RequestBody SetmealDto setmealDto) {
        log.info("setmeal info: {}", setmealDto);
        setmealService.saveWithDish(setmealDto);
        return R.success("save setmeal successfully");
    }

    /**
     * setmeal pagination
     *
     * @param page
     * @param pageSize
     * @param name
     * @return
     */
    @GetMapping("/page")
    public R<Page> page(int page, int pageSize, String name) {
        Page<Setmeal> pageInfo = new Page<>(page, pageSize);
        Page<SetmealDto> dtoPage = new Page<>();
        LambdaQueryWrapper<Setmeal> queryWrapper = new LambdaQueryWrapper<>();

        queryWrapper.like(name != null, Setmeal::getName, name);
        queryWrapper.orderByDesc(Setmeal::getUpdateTime);

        setmealService.page(pageInfo, queryWrapper);
        BeanUtils.copyProperties(pageInfo, dtoPage, "records");
        List<Setmeal> records = pageInfo.getRecords();
        List<SetmealDto> list = records
                .stream()
                .map(item -> {
                    SetmealDto setmealDto = new SetmealDto();
                    BeanUtils.copyProperties(item, setmealDto);
                    Long categoryId = item.getCategoryId();
                    Category category = categoryService.getById(categoryId);

                    if (category != null) {
                        String categoryName = category.getName();
                        setmealDto.setCategoryName(categoryName);
                    }
                    return setmealDto;
                })
                .collect(Collectors.toList());

        dtoPage.setRecords(list);
        return R.success(dtoPage);
    }

    /**
     * delete setmeal by id or ids
     *
     * @param ids
     * @return
     */
    @DeleteMapping
    public R<String> delete(@RequestParam List<Long> ids) {
        log.info("ids: {}", ids);
        setmealService.removeWithDish(ids);
        return R.success("setmeal deleted successfully");
    }

}
