package by.zhigarev.service;

import by.zhigarev.service.impl.UserServiceImpl;

public class ServiceProvider {
    private static final ServiceProvider instance = new ServiceProvider();

    public static ServiceProvider getInstance(){
        return instance;
    }

    public UserService getUserService(){
        return UserServiceImpl.getInstance();
    }
}
