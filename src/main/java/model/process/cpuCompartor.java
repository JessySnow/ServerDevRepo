package model.process;

import java.util.Comparator;

/**
 *  进程CPU占用比较器
 * @author JessySnow
*/
public class cpuCompartor implements Comparator<MyProcess>{
    /**
     * @param myProcess1 比较进程1
     * @param myProcess2 比较进程2
    */
    @Override
    public int compare(MyProcess myProcess1, MyProcess myProcess2){
        if(myProcess1.cpuUsage > myProcess2.cpuUsage) return -1;
        else if(myProcess1.cpuUsage < myProcess2.cpuUsage) return 1;
        else return myProcess1.processName.compareTo(myProcess2.processName);
    }
}
