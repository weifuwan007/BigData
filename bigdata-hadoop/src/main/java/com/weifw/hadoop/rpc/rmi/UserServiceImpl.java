package com.weifw.hadoop.rpc.rmi;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class UserServiceImpl extends UnicastRemoteObject implements UserService {
    private static final long serialVersionUID = -1428855843688764711L;

    private static final Logger LOG = LoggerFactory.getLogger(UserServiceImpl.class);

    protected UserServiceImpl() throws RemoteException {
    }

    @Override
    public User getUserInfo(String id) throws RemoteException {
        LOG.info("id -> " + id);
        if ("0001".equals(id)) {
            final User user = new User();
            user.setId(id);
            user.setName("weifuwan");
            return user;
        }
        throw new RemoteException("没有此人");
    }
}
