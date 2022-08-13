package com.github.dadiyang.httpinvoker.entity;

import java.io.Serializable;
import java.util.List;
import java.util.StringJoiner;

/**
 * 通用返回类(标准版)
 *
 */
public class Result<T> implements Serializable {

    private static final long serialVersionUID = 8350327877975282483L;

    /**
     * 调用者id
     */
    private String callId;

    /**
     * 请求是否成功  true ,false
     */
    private boolean success;

    /**
     * 响应对象
     */
    private T result;

    /**
     * 错误编码
     */
    private String errorCode;

    /**
     * 错误描述
     */
    private String errorMsg;

    public Result() {
        
    }

    /**
     * 如果实际返回结果为 null, <code>this.success</code> 为 true
     *
     * @param result 实际返回结果
     */
    public Result(T result) {
        this.success = true;
        this.result = result;
    }

    public Result(String errorCode, String errorMsg) {
        this.errorCode = errorCode;
        this.errorMsg = errorMsg;
    }

    public static <T extends Serializable> Result<T> of(T data) {
        return new Result<>(data);
    }

    public static <T extends Serializable> Result<List<T>> of(List<T> data) {
        return new Result<>(data);
    }

    public static <T extends Serializable> Result<T> ofFail(String errorCode, String errorMsg) {
        return new Result<>(errorCode, errorMsg);
    }

    public static <T extends Serializable> Result<List<T>> ofFailList(String errorCode, String errorMsg) {
        return new Result<>(errorCode, errorMsg);
    }

    public boolean isSuccess() {
        return this.success;
    }

    public T getResult() {
        return this.result;
    }

    /**
     * 如果实际返回结果为 null, <code>this.success</code> 仍为false
     *
     * @param result 实际返回结果
     */
    public void setResult(T result) {
        this.result = result;
    }

    public String getErrorCode() {
        return this.errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    public String getCallId() {
        return callId;
    }

    public void setCallId(String callId) {
        this.callId = callId;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", Result.class.getSimpleName() + "[", "]")
                .add("callId=" + callId)
                .add("success=" + success)
                .add("result=" + result)
                .add("errorCode='" + errorCode + "'")
                .add("errorMsg='" + errorMsg + "'")
                .toString();
    }
}
