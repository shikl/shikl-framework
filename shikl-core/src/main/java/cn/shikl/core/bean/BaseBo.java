package cn.shikl.core.bean;

import java.io.Serializable;

/**
 * 对外接口返回参数说明
 * @author shikl
 */
public class BaseBo implements Serializable{

	/**
	 * 序列化编号
	 */
	private static final long serialVersionUID = 3661061174778618464L;

	public static final String SUCCESS = "success";

	public static final String ERROR = "error";
	
	/**
	 * 接口调用说明   success / error
	 */
	private String result;
	
	/**
	 * 具体说明
	 */
	private String message;
	
	
	public BaseBo(){
		
	}
	
	public BaseBo(String result , String message){
		this.result = result;
		this.message = message;
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
}
