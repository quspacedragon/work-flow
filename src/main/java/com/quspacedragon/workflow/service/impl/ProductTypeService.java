package com.quspacedragon.workflow.service.impl;

import com.quspacedragon.workflow.entity.ProductType;
import com.quspacedragon.workflow.mapper.ProductTypeMapper;
import com.quspacedragon.workflow.service.IProductTypeService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author quspacedragon
 * @since 2018-07-24
 */
@Service
public class ProductTypeService extends ServiceImpl<ProductTypeMapper, ProductType> implements IProductTypeService {
	
}
