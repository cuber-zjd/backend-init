package com.cuber.backend.system.model.common;

/**
 * 返回工具类
 */
public class ResultUtils {

    /**
     * 成功
     *
     */
    public static <T> Result<T> success(T data) {
        return new Result<>(200, data, "ok");
    }

    /**
     * 失败
     */
    public static Result error(ErrorCode errorCode) {
        return new Result<>(errorCode);
    }

    /**
     * 失败
     */
    public static Result error(int code, String message) {
        return new Result(code, null, message);
    }

    /**
     * 失败
     */
    public static Result error(ErrorCode errorCode, String message) {
        return new Result(errorCode.getCode(), null, message);
    }
}
