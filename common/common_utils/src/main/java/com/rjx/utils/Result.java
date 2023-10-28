package com.rjx.utils;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Result {
    private Integer code;//响应码：20000->成功 20001->失败
    private String msg;
    private Object data;

    public static Result success() {
        return new Result(20_000, "success", null);
    }

    public static Result success(Object data) {
        return new Result(20_000, "success", data);
    }

    public static Result error(String msg) {
        return new Result(20_001, msg, null);
    }
}