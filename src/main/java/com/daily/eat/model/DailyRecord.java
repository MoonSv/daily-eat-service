package com.daily.eat.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

@Getter
@Setter
@ToString
public class DailyRecord {
    public int id;
    public String userName;
    public String foodName;
    public String foodType;
    public Date addDate;
    public Date modDate;
    public int inactiveFlag;
}
