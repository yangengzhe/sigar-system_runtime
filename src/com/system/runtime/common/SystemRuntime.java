package com.system.runtime.common;

import java.sql.Time;

import javax.print.attribute.standard.RequestingUserName;

import org.hyperic.sigar.CpuInfo;
import org.hyperic.sigar.CpuPerc;
import org.hyperic.sigar.FileSystem;
import org.hyperic.sigar.FileSystemUsage;
import org.hyperic.sigar.Mem;
import org.hyperic.sigar.NetInterfaceConfig;
import org.hyperic.sigar.NetInterfaceStat;
import org.hyperic.sigar.Sigar;
import org.hyperic.sigar.SigarException;


public class SystemRuntime {
    private Sigar sigar = null;
    public void update(){
        sigar = new Sigar();
    }
    
    public SystemRuntime(){
        sigar = new Sigar();
    }
    public Mem memory() throws SigarException {
        Mem mem = sigar.getMem();
//        // 内存总量
//        System.out.println("内存总量:    " + mem.getTotal() / 1024L + "K av");
//        // 当前内存使用量
//        System.out.println("当前内存使用量:    " + mem.getUsed() / 1024L + "K used");
//        // 当前内存剩余量
//        System.out.println("当前内存剩余量:    " + mem.getFree() / 1024L + "K free");
        return mem;
    }
 
    public CpuPerc cpu() throws SigarException {
        CpuPerc perc = sigar.getCpuPerc();
//        System.out.println("整体cpu的占用情况:");
//        System.out.println("空闲率: " + CpuPerc.format(perc.getIdle()));//获取当前cpu的空闲率
//        System.out.println("占用率: "+ CpuPerc.format(perc.getCombined()));//获取当前cpu的占用率
        return perc;
    }
    public void file() throws Exception {
        FileSystem fslist[] = sigar.getFileSystemList();
        for (int i = 0; i < fslist.length; i++) {
            System.out.println("分区的盘符名称" + i);
            FileSystem fs = fslist[i];
            // 分区的盘符名称
            System.out.println("盘符名称:    " + fs.getDevName());
            // 分区的盘符名称
            System.out.println("盘符路径:    " + fs.getDirName());
            System.out.println("盘符标志:    " + fs.getFlags());//
            // 文件系统类型，比如 FAT32、NTFS
            System.out.println("盘符类型:    " + fs.getSysTypeName());
            // 文件系统类型名，比如本地硬盘、光驱、网络文件系统等
            System.out.println("盘符类型名:    " + fs.getTypeName());
            // 文件系统类型
            System.out.println("盘符文件系统类型:    " + fs.getType());
            FileSystemUsage usage = null;
            usage = sigar.getFileSystemUsage(fs.getDirName());
            System.out.println(fs.getDevName() + "读出：    " + usage.getDiskReads());
            System.out.println(fs.getDevName() + "写入：    " + usage.getDiskWrites());
        }
        return;
    }
    
    public float[] net(String ip) throws Exception{
        float[] result = {0f,0f};
        if(netBytes(ip) == null) return result;
        update();
        long time  = System.currentTimeMillis();
        long rx = netBytes(ip).getRxBytes();
        long tx = netBytes(ip).getTxBytes();
        Thread.sleep(500);
        update();
        time  = System.currentTimeMillis()-time;
        rx = netBytes(ip).getRxBytes()-rx;
        tx = netBytes(ip).getTxBytes()-tx;
        result[0] = rx*1f/time;//kb/sec
        result[1] = tx*1f/time;
        return result;
    }
 
    public NetInterfaceStat netBytes(String ip) throws Exception {
        String ifNames[] = sigar.getNetInterfaceList();
        NetInterfaceStat result = null;
        for (int i = 0; i < ifNames.length; i++) {
            String name = ifNames[i];
            NetInterfaceConfig ifconfig = sigar.getNetInterfaceConfig(name);
            if(ifconfig.getAddress().equals(ip)){
                result = sigar.getNetInterfaceStat(name);
                break;
            }
//            System.out.println("网络设备名:    " + name);// 网络设备名
//            System.out.println("IP地址:    " + ifconfig.getAddress());// IP地址
//            System.out.println("子网掩码:    " + ifconfig.getNetmask());// 子网掩码
//            if ((ifconfig.getFlags() & 1L) <= 0L) {
//                System.out.println("!IFF_UP...skipping getNetInterfaceStat");
//                continue;
//            }
//            NetInterfaceStat ifstat = sigar.getNetInterfaceStat(name);
//            
//            System.out.println(name + "接收的总包裹数:" + ifstat.getRxPackets());// 接收的总包裹数
//            System.out.println(name + "发送的总包裹数:" + ifstat.getTxPackets());// 发送的总包裹数
//            System.out.println(name + "接收到的总字节数:" + ifstat.getRxBytes());// 接收到的总字节数
//            System.out.println(name + "发送的总字节数:" + ifstat.getTxBytes());// 发送的总字节数
//            System.out.println(name + "接收到的错误包数:" + ifstat.getRxErrors());// 接收到的错误包数
//            System.out.println(name + "发送数据包时的错误数:" + ifstat.getTxErrors());// 发送数据包时的错误数
//            System.out.println(name + "接收时丢弃的包数:" + ifstat.getRxDropped());// 接收时丢弃的包数
//            System.out.println(name + "发送时丢弃的包数:" + ifstat.getTxDropped());// 发送时丢弃的包数
        }
        return result;
    }
}
