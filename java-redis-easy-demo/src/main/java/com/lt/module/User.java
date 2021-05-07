package com.lt.module;

import lombok.Data;

/**
 * @author liangtao
 * @description
 * @date 2021年05月06 16:34
 **/
@Data
public class User {
    public User(String id) {
        this.id = id;
    }

    String id;
    String name;
}
