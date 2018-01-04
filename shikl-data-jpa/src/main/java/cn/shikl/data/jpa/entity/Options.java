package cn.shikl.data.jpa.entity;

import org.hibernate.validator.constraints.Length;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * 程序选项.
 * 
 * @author libo
 */
@Entity
@Table(name = "r_options")
public class Options implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public static final String RESTORE_DEFAULT="restoreDefault";
	
	@Id
	@Length(max=32)
	private String id;

	private String name;

	@Column(name="optionValue")
	private String value;

	private String defaultValue;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getDefaultValue() {
		return defaultValue;
	}

	public void setDefaultValue(String defaultValue) {
		this.defaultValue = defaultValue;
	}

}
