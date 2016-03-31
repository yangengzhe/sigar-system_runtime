package com.system.runtime.service;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.hyperic.sigar.CpuPerc;

import com.system.runtime.main;
import com.system.runtime.common.SystemRuntime;
import com.system.runtime.pojo.systemStatusPojo;

public class systemStatusServiceImpl extends UnicastRemoteObject implements systemStatusService {
    public systemStatusServiceImpl() throws RemoteException{
        super();
        // TODO Auto-generated constructor stub
    }
    private static final long serialVersionUID = 1L;
    @Override
    public systemStatusPojo getRuntime() throws RemoteException{
        System.out.println("调用服务");
        systemStatusPojo sp = new systemStatusPojo();
        try {
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
            System.out.println("当前时间："+df.format(new Date()));
            SystemRuntime sy = new SystemRuntime();
            sp.setMemory_total(sy.memory().getTotal() / 1024L / 1024L);
            System.out.println("总共内存(M)："+sp.getMemory_total());
            sp.setMemory_uesd(sy.memory().getUsed() / 1024L / 1024L);
            System.out.println("使用内存(M)："+sp.getMemory_uesd());
            sp.setCpu_combined(sy.cpu().getCombined());
            System.out.println("CPU(%)："+sp.getCpu_combined());
            float[] net = sy.net(main.ip);
            sp.setRx_speed(net[0]);
            System.out.println("下载(kb/s)："+sp.getRx_speed());
            sp.setTx_speed(net[1]);
            System.out.println("上传(kb/s)："+sp.getTx_speed());
        } catch (Exception e) {
            System.out.println(e);
        }
        return sp;
    }

}
