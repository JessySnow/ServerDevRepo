package model;

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
}

/**
 *  进程CPU占用比较器
 * @author JessySnow
*/
class cpuCompartor implements Comparator<MyProcess>{
    /**
     * @param myProcess1 比较进程1
     * @param myProcess2 比较进程2
    */
    @Override
    public int compare(MyProcess myProcess1, MyProcess myProcess2){
        if(myProcess1.cpuUsage > myProcess2.cpuUsage) return 1;
        else if(myProcess1.cpuUsage < myProcess2.cpuUsage) return -1;
        else return myProcess1.processName.compareTo(myProcess2.processName);
    }
}

/**
 * 进程内存占用比较器
 * @author JessySnow
*/
class memComprator implements Comparator<MyProcess>{
     /**
     * @param myProcess1 比较进程1
     * @param myProcess2 比较进程2
    */
    @Override
    public int compare(MyProcess myProcess1, MyProcess myProcess2){
        if(myProcess1.memUsage > myProcess2.memUsage) return 1;
        else if(myProcess1.memUsage < myProcess2.memUsage) return -1;
        else return myProcess1.processName.compareTo(myProcess2.processName);
    }
}