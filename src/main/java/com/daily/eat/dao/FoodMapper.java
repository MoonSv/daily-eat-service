package com.daily.eat.dao;

import com.daily.eat.model.DailyRecord;
import com.daily.eat.model.FoodRef;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface FoodMapper {
    @Select("select * from DAILY_RECORD")
    List<DailyRecord> selectAll();

    @Select("select * from FOOD_REF")
    List<FoodRef> getAllFoodRef();
}
