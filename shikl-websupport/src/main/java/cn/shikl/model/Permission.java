package cn.shikl.model;

/**
 * 权限BO
 *
 * @author shikl
 * @version v1.0    2016/12/29.
 */
public class Permission {
    /**
     * 权限类型
     */
    private String type;

    /**
     * 权限拦截的URL
     */
    private String url;

    /**
     * 目标
     */
    private String tarObject;

    /**
     * 操作
     */
    private String operation;


    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getTarObject() {
        return tarObject;
    }

    public void setTarObject(String tarObject) {
        this.tarObject = tarObject;
    }

    public String getOperation() {
        return operation;
    }

    public void setOperation(String operation) {
        this.operation = operation;
    }
}
