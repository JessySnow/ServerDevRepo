package model.process;

import java.util.Comparator;

/**
 * 定义的进程信息类，存放对应进程的内存、CPU占用率、进程名称
 * @author JessySnow
 * version 1.0
*/
public class MyProcess {
    public String processName;
    public float cpuUsage;
    public float memUsage;

    /**
     * @param processName 进程名称
     * @param cpuUsage 进程CPU占用
     * @param memUsage 进程内存占用
    */
    public MyProcess(String processName, float cpuUsage, float memUsage){
        this.processName = processName;
        this.cpuUsage = cpuUsage;
        this.memUsage = memUsage;
    }

    @Override
    public String toString(){
        return "ProcessName: " + processName + ", PrcessCPUUsage: " + cpuUsage + ", ProcessMEMUsage: " + memUsage; 
    }
}
