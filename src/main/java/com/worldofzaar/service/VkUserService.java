package com.worldofzaar.service;

import com.worldofzaar.dao.VkUserDao;
import com.worldofzaar.entity.User;
import com.worldofzaar.entity.VkUser;
import com.worldofzaar.modelAttribute.VKInitialization;

/**
 * Created with IntelliJ IDEA.
 * User: Дмитрий
 * Date: 25.12.13
 * Time: 11:20
 * To change this template use File | Settings | File Templates.
 */
public class VkUserService {
    public VkUser getVkUser(Integer vkUserId) {
        VkUserDao vkUserDao = new VkUserDao();
        return vkUserDao.find(vkUserId);
    }

    public VkUser getOrCreate(VKInitialization vk) {
        VkUser vkUser = getVkUser(vk.getViewer_id());
        if (vkUser == null) {
            vkUser = new VkUser();
            vkUser.setViewerId(vk.getViewer_id());
            VkUserDao vkUserDao = new VkUserDao();
            vkUserDao.add(vkUser);
        }
        return vkUser;
    }

    public void setUser(Integer vkUserId, User user) {
        VkUserDao vkUserDao = new VkUserDao();
        VkUser vkUser = vkUserDao.find(vkUserId);
        vkUser.setUser(user);
        vkUserDao.update(vkUser);

    }

}
