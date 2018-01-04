package cn.shikl.utils;

/**
 * UUID 生成类.
 * 
 * @author shikl
 * 
 */
public final class IDGeneratorUUID {

	/**
	 * 返回32位的UUID值.
	 * 
	 * @return 生成32位的随机字符串.
	 */
	public static String generatorId() {
		return java.util.UUID.randomUUID().toString().replace("-", "");
	}

	/**
	 * 私有的构造方法.
	 */
	private IDGeneratorUUID() {
		super();
	}
}
