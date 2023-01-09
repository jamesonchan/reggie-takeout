package com.project.reggie.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.project.reggie.domain.Category;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface CategoryMapper extends BaseMapper<Category> {
}
