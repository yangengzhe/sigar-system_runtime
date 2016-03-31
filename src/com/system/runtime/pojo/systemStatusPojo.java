package com.system.runtime.pojo;

import java.io.Serializable;

public class systemStatusPojo implements Serializable{
    private static final long serialVersionUID = 1L;
    private long memory_uesd;
    private long memory_total;
    private double cpu_combined;
    private float rx_speed;
    private float tx_speed;
    
    public long getMemory_uesd() {
        return memory_uesd;
    }
    
    public void setMemory_uesd(long memory_uesd) {
        this.memory_uesd = memory_uesd;
    }
    
    public long getMemory_total() {
        return memory_total;
    }
    
    public void setMemory_total(long memory_total) {
        this.memory_total = memory_total;
    }
    
    public double getCpu_combined() {
        return cpu_combined;
    }
    
    public void setCpu_combined(double cpu_combined) {
        this.cpu_combined = cpu_combined;
    }
    
    public float getRx_speed() {
        return rx_speed;
    }
    
    public void setRx_speed(float rx_speed) {
        this.rx_speed = rx_speed;
    }
    
    public float getTx_speed() {
        return tx_speed;
    }
    
    public void setTx_speed(float tx_speed) {
        this.tx_speed = tx_speed;
    }
}
