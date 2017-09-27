package com.quspacedragon.workflow.entity;

import java.io.Serializable;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.Version;
import com.quspacedragon.workflow.entity.BaseEntity;

/**
 * <p>
 * 
 * </p>
 *
 * @author quspacedragon
 * @since 2017-09-27
 */
public class Dict extends BaseEntity<Dict> {

    private static final long serialVersionUID = 1L;

	@TableField("enterprise_id")
	private Integer enterpriseId;
	@TableField("dict_name")
	private String dictName;
	@TableField("dict_type")
	private Integer dictType;
	@TableField("dict_key")
	private Integer dictKey;
    /**
     * 拼音简码
     */
	private String code;


	public Integer getEnterpriseId() {
		return enterpriseId;
	}

	public void setEnterpriseId(Integer enterpriseId) {
		this.enterpriseId = enterpriseId;
	}

	public String getDictName() {
		return dictName;
	}

	public void setDictName(String dictName) {
		this.dictName = dictName;
	}

	public Integer getDictType() {
		return dictType;
	}

	public void setDictType(Integer dictType) {
		this.dictType = dictType;
	}

	public Integer getDictKey() {
		return dictKey;
	}

	public void setDictKey(Integer dictKey) {
		this.dictKey = dictKey;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public static final String ENTERPRISE_ID = "enterprise_id";

	public static final String DICT_NAME = "dict_name";

	public static final String DICT_TYPE = "dict_type";

	public static final String DICT_KEY = "dict_key";

	public static final String CODE = "code";

	@Override
	protected Serializable pkVal() {
		return this.id;
	}

	@Override
	public String toString() {
		return "Dict{" +
			"enterpriseId=" + enterpriseId +
			", dictName=" + dictName +
			", dictType=" + dictType +
			", dictKey=" + dictKey +
			", code=" + code +
			"}";
	}
}
