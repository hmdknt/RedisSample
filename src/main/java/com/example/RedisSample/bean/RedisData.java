package com.example.RedisSample.bean;

import java.util.List;
import java.util.Map;
import lombok.Data;

@Data
public class RedisData {

    private String string;

    private List<String> list;

    private Map<String,String> map;
}
