package com.tutorial.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * @Author: jimmy
 * @Date: 2019/4/15
 */
@Data
public class Account implements Serializable {
    private static final long serialVersionUID = -2897227658999048216L;

    private Integer id;

    private String name;
}
