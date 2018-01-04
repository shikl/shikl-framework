package cn.shikl.data.jpa.entity;


import javax.persistence.*;

/**
 * 数据库中保存每表的自动编号的持久类.
 */
@Entity
@Table(name = "r_idgenerator")
public class IdGenerator {

    /**
     * .
     * 串行化标志.
     */
    public static final long serialVersionUID = 1L;

    /**
     * 常量.
     */
    @Transient
    private final int idLen = 32;

    /**
     * 常量数字1.
     */
    @Transient
    private final int one = 1;

    /**
     * 编号.
     */
    @Id
    @Column(name = "autoid")
    private String id;

    /**
     * 表名.
     */
    private String tableName;

    /**
     * 辅助值.
     */
    private String assValue;

    /**
     * 下一个值.
     */
    @Column(name = "NextValue")
    private int value = one;

    /**
     * 编号长度.
     */
    private int len = one;

    /**
     * 版本.
     */
    @javax.persistence.Column(name = "version")
    private long version;

    @Override
    public final int hashCode() {
        final int prime = 31;
        int result = 1;
        if (assValue == null) {
            result = prime * result;
        } else {
            result = prime * result + assValue.hashCode();
        }
        if (id == null) {
            result = prime * result;
        } else {
            result = prime * result + id.hashCode();
        }
        result = prime * result + len;
        if (tableName == null) {
            result = prime * result;
        } else {
            result = prime * result + tableName.hashCode();
        }
        result = prime * result + value;
        result = prime * result + (int) (version ^ (version >>> idLen));
        return result;
    }

    @Override
    public final boolean equals(final Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        IdGenerator other = (IdGenerator) obj;
        if (assValue == null) {
            if (other.assValue != null) {
                return false;
            }
        } else if (!assValue.equals(other.assValue)) {
            return false;
        }
        if (id == null) {
            if (other.id != null) {

                return false;
            }
        } else if (!id.equals(other.id)) {
            return false;
        }
        if (len != other.len) {
            return false;
        }
        if (tableName == null) {
            if (other.tableName != null) {
                return false;
            }
        } else if (!tableName.equals(other.tableName)) {
            return false;
        }
        if (value != other.value) {
            return false;
        }
        if (version != other.version) {
            return false;
        }
        return true;
    }

    public String getId() {
        return id;
    }

    /**
     * 设置 id.
     *
     * @param id id.
     */
    public void setId(String id) {
        this.id = id;
    }

    public long getVersion() {
        return version;
    }

    public void setVersion(long version) {
        this.version = version;
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public int getLen() {
        return len;
    }

    public void setLen(int len) {
        this.len = len;
    }

    /**
     * @return 获取生成ID辅助值.
     */
    public final String getAssValue() {
        return assValue;
    }

    /**
     * 设置生成ID辅助值.
     *
     * @param assValue 设置辅助值.
     */
    public final void setAssValue(final String assValue) {
        this.assValue = assValue;
    }

}
