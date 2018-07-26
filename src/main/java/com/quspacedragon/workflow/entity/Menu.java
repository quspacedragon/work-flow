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
 * @since 2018-07-24
 */
public class Menu extends BaseEntity<Menu> {

    private static final long serialVersionUID = 1L;

    /**
     * 菜单名称
     */
	@TableField("menu_name")
	private String menuName;
    /**
     * 排序id
     */
	private Integer order;
    /**
     * 菜单url
     */
	private String url;


	public String getMenuName() {
		return menuName;
	}

	public void setMenuName(String menuName) {
		this.menuName = menuName;
	}

	public Integer getOrder() {
		return order;
	}

	public void setOrder(Integer order) {
		this.order = order;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public static final String MENU_NAME = "menu_name";

	public static final String ORDER = "order";

	public static final String URL = "url";


	@Override
	protected Serializable pkVal() {
		return this.id;
	}

	@Override
	public String toString() {
		return "Menu{" +
			"menuName=" + menuName +
			", order=" + order +
			", url=" + url +
			", createTime=" + createTime +
			"}";
	}
}
