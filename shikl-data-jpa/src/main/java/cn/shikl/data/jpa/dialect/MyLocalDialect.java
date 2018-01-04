package cn.shikl.data.jpa.dialect;

import org.hibernate.dialect.MySQL57InnoDBDialect;
import org.hibernate.dialect.function.StandardSQLFunction;
import org.hibernate.type.StandardBasicTypes;

/**
 * Created by yangcm on 2017/6/30.
 */
public class MyLocalDialect extends MySQL57InnoDBDialect {

    public MyLocalDialect() {
        super();
        this.registerFunction("group_concat", new StandardSQLFunction("group_concat", StandardBasicTypes.STRING));
    }
}
