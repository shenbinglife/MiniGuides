package io.github.shenbinglife.springboot2.layui.model;

public class Result<T> {
    public static final long SUCCESS_CODE = 0L;
    public static final long FAILURE_CODE = 1L;

    private long code;
    private String message;
    private T data;
    private boolean success;

    public Result() {
    }

    public Result(long code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
        this.success = code == SUCCESS_CODE;
    }

    public static Result<Void> success() {
        return new Result<>(SUCCESS_CODE, null, null);
    }

    public static <T>  Result<T> success(T data) {
        return new Result<>(SUCCESS_CODE, null, data);
    }

    public static Result<Void> failure (String message) {
        return new Result<>(FAILURE_CODE, message, null);
    }

    public static Result<Void> failure(long code, String message) {
        return new Result<>(code, message, null);
    }

    public long getCode() {
        return code;
    }

    public void setCode(long code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }
}
