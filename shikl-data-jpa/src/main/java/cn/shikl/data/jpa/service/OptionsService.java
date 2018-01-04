package cn.shikl.data.jpa.service;

import cn.shikl.data.jpa.entity.Options;
import cn.shikl.data.service.IService;

/**
 * 应用程序选项服务接口.
 * 
 * @author libo
 * 
 */
public interface OptionsService extends IService<Options> {

	/**
	 * spring bean name.
	 */
	String BEAN_NAME = "optionsService";

	/**
	 * 根据编号读取选项值 .如果该选项没有设置值，则返回默认值.
	 * 
	 * @param id
	 *            编号.
	 * @return 选项值.
	 */
	String getValue(String id);

	/**
	 * 恢复所有选项到默认值 .
	 */
	void restoreDefault();
}
