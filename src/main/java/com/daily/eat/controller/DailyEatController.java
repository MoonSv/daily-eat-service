package com.daily.eat.controller;

import com.daily.eat.mapper.FoodMapper;
import com.daily.eat.model.DailyRecord;
import com.daily.eat.model.FoodRef;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/record")
public class DailyEatController {

    @Autowired
    private FoodMapper foodMapper;

    @PostMapping
    public void addDailyRecord(@RequestBody DailyRecord record) {

    }

    @RequestMapping("/dailyRec")
    public List<DailyRecord> getAllDailyRecord() {
        return foodMapper.selectAll();
    }

    @RequestMapping("/foodRef")
    public List<FoodRef> getAllFoodRef() {
        return foodMapper.getAllFoodRef();
    }
}
