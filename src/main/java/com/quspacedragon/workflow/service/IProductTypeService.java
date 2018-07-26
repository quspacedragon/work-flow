package com.quspacedragon.workflow.service;

import com.quspacedragon.workflow.entity.ProductType;
import com.baomidou.mybatisplus.service.IService;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author quspacedragon
 * @since 2018-07-24
 */
public interface IProductTypeService extends IService<ProductType> {

    public ProductType findByCode(String code);

}
