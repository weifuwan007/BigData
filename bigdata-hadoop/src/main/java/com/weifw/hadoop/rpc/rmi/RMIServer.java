package com.weifw.hadoop.rpc.rmi;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.MalformedURLException;
import java.rmi.AlreadyBoundException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;

/**
 * RMI server
 */
public class RMIServer {
    public static final Logger LOG = LoggerFactory.getLogger(RMIServer.class);

    public static void main(String[] args) {


        try {

            final UserServiceImpl userService = new UserServiceImpl();
            // 注册端口
            LocateRegistry.createRegistry(1900);
            // 注册服务
            Naming.rebind("rmi://localhost:1900/user", userService);
            LOG.info("start rmi server 1900");
        } catch (RemoteException | MalformedURLException remoteException) {
            LOG.error(remoteException.getMessage());
        }

    }
}
