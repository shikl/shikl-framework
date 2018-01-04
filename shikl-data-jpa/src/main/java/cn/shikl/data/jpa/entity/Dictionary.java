package cn.shikl.data.jpa.entity;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

import cn.shikl.utils.IDGeneratorUUID;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Version;
import java.io.Serializable;

/**
 * 数据字典.
 *
 * @author shikl
 * @version 1.0
 */
@Table(name = "r_dictionary")
@javax.persistence.Entity
public class Dictionary implements Serializable {

    /**
     * 串行化标志.
     */
    private static final long serialVersionUID = 1L;
    /**
     * 字典编号.表内唯一。
     */
    @Id
    @NotEmpty
    @Length(max = 32)
    @Column(name = "dictId")
    private String id;
    /**
     * 字典值.
     */
    @Length(max = 50)
    @Column(name = "dictValue")
    private String value;

    /**
     * 字典名称.
     */
    @NotEmpty
    @Length(max = 50)
    @Column(name = "dictName")
    private String name;

    /**
     * 字典分组.
     */
    @NotEmpty
    @Length(max = 10)
    @Column(name = "dictGroup")
    private String group;

    /**
     * 是否是默认值.
     */
    @Column(name = "defaultValue")
    private boolean defaultValue;

    private String projectId;
    /**
     * 版本.
     */
    @Column(name = "version")
    @Version
    private int version;

    /**
     * @return the id
     */
    public String getId() {
        return id;
    }

    /**
     * set id.
     *
     * @param id the id to set
     */
    public void setId(String id) {

        this.id = id;
    }

    /**
     * @return the value
     */
    public String getValue() {
        return value;
    }

    /**
     * set value.
     *
     * @param value the value to set
     */
    public void setValue(String value) {
        this.value = value;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * set name.
     *
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the group
     */
    public String getGroup() {
        return group;
    }

    /**
     * set group.
     *
     * @param group the group to set
     */
    public void setGroup(String group) {
        this.group = group;
    }

    /**
     * @return the defaultValue
     */
    public boolean isDefaultValue() {
        return defaultValue;
    }

    /**
     * set defaultValue.
     *
     * @param defaultValue the defaultValue to set
     */
    public void setDefaultValue(boolean defaultValue) {
        this.defaultValue = defaultValue;
    }

    /**
     * @return the version
     */
    public int getVersion() {
        return version;
    }

    /**
     * set version.
     *
     * @param version the version to set
     */
    public void setVersion(int version) {
        this.version = version;
    }

    public String getProjectId() {
        return projectId;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }
}
