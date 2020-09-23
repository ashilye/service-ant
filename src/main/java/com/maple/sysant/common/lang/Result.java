package com.maple.sysant.common.lang;

import com.maple.sysant.common.Constants;
import lombok.Data;
import java.io.Serializable;

@Data
public class Result implements Serializable {

    private int code; // 200是正常，非200表示异常
    private String msg;
    private Object data;

    public static Result success(Object data) {
        return success(Constants.RESULT_SUCCESS, "操作成功", data);
    }

    public static Result success(int code, String msg, Object data) {
        Result r = new Result();
        r.setCode(code);
        r.setMsg(msg);
        r.setData(data);
        return r;
    }

    public static Result fail(String msg) {
        return fail(Constants.RESULT_FAIL, msg, null);
    }

    public static Result fail(String msg, Object data) {
        return fail(Constants.RESULT_FAIL, msg, data);
    }

    public static Result fail(int code, String msg, Object data) {
        Result r = new Result();
        r.setCode(code);
        r.setMsg(msg);
        r.setData(data);
        return r;
    }

}
