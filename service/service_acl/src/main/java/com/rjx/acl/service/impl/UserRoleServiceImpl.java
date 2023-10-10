package com.rjx.acl.service.impl;

import com.rjx.acl.entity.UserRole;
import com.rjx.acl.mapper.UserRoleMapper;
import com.rjx.acl.service.UserRoleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

@Service
public class UserRoleServiceImpl extends ServiceImpl<UserRoleMapper, UserRole> implements UserRoleService {

}
