package com.worldofzaar.dao;

import com.worldofzaar.entity.VkUser;

/**
 * Created with IntelliJ IDEA.
 * User: Дмитрий
 * Date: 25.12.13
 * Time: 11:14
 * To change this template use File | Settings | File Templates.
 */
public class VkUserDao extends GenericDaoMain<VkUser> {
    public VkUserDao() {
        super(new VkUser());
    }


}
