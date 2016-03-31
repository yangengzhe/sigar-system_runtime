package com.system.runtime.service;

import java.rmi.Remote;
import java.rmi.RemoteException;

import com.system.runtime.pojo.systemStatusPojo;

public interface systemStatusService extends Remote{
    public systemStatusPojo getRuntime() throws RemoteException;//内存 M 网络 kb/s
}
