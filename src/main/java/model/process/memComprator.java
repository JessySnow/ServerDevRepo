package model.process;

import java.util.Comparator;

/**
 * 进程内存占用比较器
 * @author JessySnow
*/
public class memComprator implements Comparator<MyProcess>{
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
