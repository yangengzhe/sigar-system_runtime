package com.system.runtime;

import java.io.File;
import java.io.FileInputStream;
import java.rmi.Naming;
import java.rmi.registry.LocateRegistry;
import java.util.Iterator;
import java.util.Properties;
import java.util.Set;

import org.hyperic.sigar.CpuInfo;
import org.hyperic.sigar.CpuPerc;
import org.hyperic.sigar.SigarException;
import org.omg.CORBA.portable.InputStream;

import com.system.runtime.common.SystemRuntime;
import com.system.runtime.service.systemStatusService;
import com.system.runtime.service.systemStatusServiceImpl;

public class main {
    public static String ip;
    //需要测量CPU、内存、网络使用率 java -jar test.jar 192.168.191.5
    public static void main(String args[]) {
        try {
            System.setProperty("java.library.path", "monitor_libs");
            if(args.length==0) ip = "192.168.191.5";
            else ip = args[0];
            systemStatusService sss = new systemStatusServiceImpl();
            LocateRegistry.createRegistry(8828);
            Naming.bind("rmi://127.0.0.1:8828/systemstatus",sss);
            System.out.println("启动成功！"+System.getProperty("java.library.path"));
        } catch (Exception e) {
            System.out.println("创建远程对象发生异常！"); 
            System.out.println(e);
        }
        

    }
}
