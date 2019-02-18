package name.guolanren.common;

/**
 * @author guolanren
 * @date 2019-01-17
 */
public enum ResultCode {

    /**
     * 响应成功
     */
    SUCCESS(0, "响应成功"),

    /**
     * 请求异常
     */
    REQUEST_FAILD(1, "请求异常"),

    /**
     *  Session失效
     */
    SESSION_FAILD(2, "SESSION失效"),

    /**
     * 内部异常
     */
    INTERNAL_FAILD(-1, "内部异常"),

    /**
     * 未知异常
     */
    UNKNOW_FAILD(100, "未知异常");

    private Integer code;
    private String description;

    ResultCode(Integer code, String description) {
        this.code = code;
        this.description = description;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
