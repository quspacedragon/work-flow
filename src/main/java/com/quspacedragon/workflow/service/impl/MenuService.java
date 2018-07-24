package com.quspacedragon.workflow.service.impl;

import com.quspacedragon.workflow.entity.Menu;
import com.quspacedragon.workflow.mapper.MenuMapper;
import com.quspacedragon.workflow.service.IMenuService;
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
public class MenuService extends ServiceImpl<MenuMapper, Menu> implements IMenuService {
	
}
