package cn.ipanel.apps.marketing.campaign.service.controller.resp;

import lombok.Data;

import java.io.Serializable;

/**
 * @author luzh
 */
@Data
public class Result<T> implements Serializable {

    private static final long serialVersionUID = 1L;
    private static final String SUCCESS = "000";
    public static final String FAIL = "-1";
    public static final String EXCEPTION = "500";
    private String message = "success";
    private String code = SUCCESS;
    private T data;

    public Result() {
        super();
    }

    public Result(T data, String message, String code) {
        super();
        this.data = data;
        this.code = code;
        this.message = message;
    }

    public Result(String message, String code) {
        super();
        this.code = code;
        this.message = message;
    }

    public Result(T data) {
        super();
        this.data = data;
    }

    public Result(Throwable e) {
        super();
        this.message = e.toString();
        this.code = EXCEPTION;
    }
}
