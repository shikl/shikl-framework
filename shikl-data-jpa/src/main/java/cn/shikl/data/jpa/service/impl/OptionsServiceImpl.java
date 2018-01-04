
package cn.shikl.data.jpa.service.impl;

import cn.shikl.data.criteria.Criteria;
import cn.shikl.data.jpa.criteria.Restrictions;
import cn.shikl.data.jpa.entity.Options;
import cn.shikl.data.jpa.service.OptionsService;

import org.springframework.stereotype.Service;

/**
 * <p>
 * 应用程序选项服务实现类.
 * </p>
 * 
 * <pre>
 * 	可以通过getValue(String id)方法读取选项值.
 * 
 * 需要建表:options
 * {@code
 * create table options (id varchar(10) not null, name varchar(50), optionValue varchar(20), defaultValue varchar(20), primary key (id));
 * }
 * 
 * </pre>
 * 
 * @author shikl
 * 
 */
@Service(value = OptionsService.BEAN_NAME)
public class OptionsServiceImpl extends BaseServiceImpl<Options> implements
		OptionsService {

    @Override
    public String getValue(String id) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void restoreDefault() {
        // TODO Auto-generated method stub
        
    }


	

}
