package cn.shikl.model;

import org.springframework.http.HttpStatus;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 前端与后端通讯消息对象.
 *
 * @author shikl.
 */
public class Messages extends BaseVO {

    /**
     * 后端处理成功.
     */
    public static final String SUCCESS = "success";

    /**
     * 后端处理失败.
     */
    public static final String FAILURE = "failure";

    public static final String SUCCESSCODE = "0";

    public static final String FAILURECODE = "1";

    /**
     * 接口返回码.
     */
    private String code;

    /**
     * http 状态码.
     */
    private int httpCode;

    /**
     * 返回结果.
     */
    private String result;

    /**
     * 返回消息.
     */
    private List<String> messages;

    /**
     * 其他数据.
     */
    private Object data;

    private Messages() {
        this.messages = new ArrayList<String>();
    }

    /**
     * 构建成功的消息对象.
     *
     * @param message 消息.
     * @return
     */
    public static Messages success(String... message) {

        Messages messages = new Messages(Messages.SUCCESS, HttpStatus.OK.value(), message);
        messages.setCode(SUCCESSCODE);

        return messages;
    }

    /**
     * 构建失败的消息对象.
     *
     * @param message 消息.
     * @return
     */
    public static Messages failure(String... message) {
        Messages messages = new Messages(Messages.FAILURE, HttpStatus.INTERNAL_SERVER_ERROR.value(), message);
        messages.setCode(FAILURECODE);
        return messages;
    }

    /**
     * 构建失败的消息对象.
     *
     * @param message 消息.
     * @return
     */
    public static Messages failure200(String... message) {
        Messages messages = new Messages(Messages.FAILURE, HttpStatus.OK.value(), message);
        messages.setCode(FAILURECODE);
        return messages;
    }

    /**
     * 构建失败的消息对象.
     *
     * @param message 消息.
     * @return
     */
    public static Messages failure(HttpStatus status, String... message) {
        Messages messages = new Messages(Messages.FAILURE, status.value(), message);
        messages.setCode(FAILURECODE);
        return messages;
    }

    public Messages(String result, int httpCode, String... messages) {
        this.messages = new ArrayList<String>();
        this.result = result;
        this.messages.addAll(Arrays.asList(messages));
        this.httpCode = httpCode;
    }

    public Messages(String result, String... messages) {
        this.messages = new ArrayList<String>();
        this.result = result;
        this.messages.addAll(Arrays.asList(messages));
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public List<String> getMessages() {
        return messages;
    }

    public void setMessages(List<String> messages) {
        this.messages = messages;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public Messages addMessages(String... message) {
        this.messages.addAll(Arrays.asList(message));
        return this;
    }

    public Messages addMessages(List<String> message) {
        this.messages.addAll(message);
        return this;
    }

    public int getHttpCode() {
        return httpCode;
    }

    public void setHttpCode(int httpCode) {
        this.httpCode = httpCode;
    }
}
