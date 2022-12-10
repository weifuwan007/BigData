package com.weifw.hadoop.rpc.rmi;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * user service
 */
public interface UserService extends Remote {

    User getUserInfo(String id) throws RemoteException;
}
