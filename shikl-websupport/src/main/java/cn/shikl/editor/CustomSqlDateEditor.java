package cn.shikl.editor;

import java.beans.PropertyEditorSupport;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import org.springframework.util.StringUtils;

/**
 * java.sql.Date类型转换器.
 *
 * @author shikl <br/>
 * @version 1.0.0
 */
public class CustomSqlDateEditor extends PropertyEditorSupport {

    /**
     * 时间格式化.
     */
    private final SimpleDateFormat dateFormat;
    /**
     * 是否为空.
     */
    private final boolean allowEmpty;


    /**
     * 构造函数.
     *
     * @param dateFormat
     * @param allowEmpty
     */
    public CustomSqlDateEditor(SimpleDateFormat dateFormat, boolean allowEmpty) {
        this.dateFormat = dateFormat;
        this.allowEmpty = allowEmpty;
    }

    /**
     * 数据类型转换.
     */
    @Override
    public void setAsText(String text) throws IllegalArgumentException {
        if ((this.allowEmpty) && (!(StringUtils.hasText(text)))) {
            setValue(null);
        } else {
            try {
                setValue(new Date(this.dateFormat.parse(text).getTime()));
            } catch (ParseException ex) {
                throw new IllegalArgumentException("Could not parse date: " + ex.getMessage(), ex);
            }
        }
    }

    /**
     * 返回转换字符串.
     */
    @Override
    public String getAsText() {
        Date value = (Date) getValue();
        return ((value != null) ? this.dateFormat.format(value) : "");
    }
}
