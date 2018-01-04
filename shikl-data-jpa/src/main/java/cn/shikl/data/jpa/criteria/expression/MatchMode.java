package cn.shikl.data.jpa.criteria.expression;

import cn.shikl.utils.StringUtils;

/**
 * 模糊查询匹配方式.
 * 
 * @author libo <br/>
 * @version 1.0.0
 */
public enum MatchMode {

    EXACT {
        @Override
        public String toMatchString(String pattern) {
            return pattern;
        }
    },

    /**
     * Match the start of the string to the pattern.
     */
    START {
        @Override
        public String toMatchString(String pattern) {
            return StringUtils.connect(pattern, '%');
        }
    },

    /**
     * Match the end of the string to the pattern.
     */
    END {
        @Override
        public String toMatchString(String pattern) {
            return StringUtils.connect('%', pattern);
        }
    },

    /**
     * Match the pattern anywhere in the string.
     */
    ANYWHERE {
        @Override
        public String toMatchString(String pattern) {
            return StringUtils.connect('%', pattern, '%');
        }
    };

    /**
     * 
     * convert the pattern, by appending/prepending "%".
     * 
     * @param pattern
     *            .
     * @return String.
     */
    public abstract String toMatchString(String pattern);
}
