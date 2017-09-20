package com.quspacedragon.workflow.entity;

import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author quspacedragon
 * @since 2017-09-20
 */
public class User extends BaseEntity<User> {

    private static final long serialVersionUID = 1L;

    /**
     * 客户名称
     */
	private String name;


	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	protected Serializable pkVal() {
		return this.id;
	}

	@Override
	public String toString() {
		return "User{" +
			"name=" + name +
			"}";
	}
}
