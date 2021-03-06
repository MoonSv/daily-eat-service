package com.daily.eat.GlobalResult;

import com.daily.eat.dao.User;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @program: daily-eat
 * @description: common result class
 * @author: Moon
 * @create: 2020-02-29 17:20
 **/

@Getter
@Setter
@ToString
public class GlobalResult {
    private String status;
    private String msg;
    private boolean isLogIn;
    private User user;

    public GlobalResult(String status, String msg, boolean isLogIn, User user) {
        this.status = status;
        this.msg = msg;
        this.isLogIn = isLogIn;
        this.user = user;
    }

    public static GlobalResult success(String msg, User user) {
        return new GlobalResult("ok", msg, true, user);
    }

    public static GlobalResult fail(String msg, User user) {
        return new GlobalResult("fail", msg, false, user);
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public boolean isLogIn() {
        return isLogIn;
    }

    public void setLogIn(boolean logIn) {
        isLogIn = logIn;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
