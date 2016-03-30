package com.system.runtime;

import java.io.File;
import java.io.FileInputStream;
import java.util.Iterator;
import java.util.Properties;
import java.util.Set;

import org.hyperic.sigar.CpuInfo;
import org.hyperic.sigar.CpuPerc;
import org.hyperic.sigar.SigarException;
import org.omg.CORBA.portable.InputStream;

import com.system.runtime.common.SystemRuntime;

public class main {
    //需要测量CPU、内存、网络使用率 java -jar test.jar 192.168.191.5
    public static void main(String args[]) {
        try {
            System.setProperty("java.library.path", ". monitor_lib");
            String ip;
            if(args.length==0) ip = "192.168.191.5";
            else ip = args[0];
            SystemRuntime sy = new SystemRuntime();
            System.out.println("内存使用："+sy.memory().getUsed() / 1024L + "K/总共："+sy.memory().getTotal() / 1024L);
            System.out.println("CPU使用："+CpuPerc.format(sy.cpu().getCombined()));
            float[] net = sy.net(ip);
            System.out.println("网络：下载："+net[0] + "kb/s，上传："+net[1]);
        } catch (Exception e) {
            System.out.println(e);
        }
        

    }
}
