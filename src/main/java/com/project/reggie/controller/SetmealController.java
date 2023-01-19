package com.project.reggie.controller;

import com.project.reggie.common.R;
import com.project.reggie.dto.SetmealDto;
import com.project.reggie.service.SetmealDishService;
import com.project.reggie.service.SetmealService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/setmeal")
@Slf4j
public class SetmealController {
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

}
