package com.weifw.hadoop.rpc.rmi;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * RMI client
 */
public class RMIClient {
    public static final Logger LOG = LoggerFactory.getLogger(RMIClient.class);

    public static void main(String[] args) {

        // find method
        try {
            UserService userService =  (UserService) Naming.lookup("rmi://localhost:1900/user");
            User userInfo = userService.getUserInfo("0001");
            LOG.info(userInfo.toString());
        } catch (NotBoundException | MalformedURLException | RemoteException e) {
            LOG.error(e.getMessage());
        }

    }
}
