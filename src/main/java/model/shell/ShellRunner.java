package model.shell;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

import model.stringprocessor.BaseProcessor;
import model.process.*;

/**
 * JavaRunTime产生一个新的进程运行shell脚本或者命令
 * @author Jessy Snow
 * @version 1.0
*/
public class ShellRunner{
    private Runtime rt;

    public ShellRunner(){
        rt = Runtime.getRuntime();
    }

    /**
     * 运行Shell脚本，并返回命令输出流和错误流的结果,默认编码 UTF-8
     * @param command 需要运行的命令或者脚本
     * @return ShellRet 运行结果
    */
    public ShellRet run(String command, String append){
        var retSb = new StringBuilder();
        String line;
        boolean errorOccur = true;

        // input stream
        try{
            var inputStream = rt.exec(command).getInputStream();
            var reader = new BufferedReader(new InputStreamReader(inputStream, StandardCharsets.UTF_8));
            while((line = reader.readLine()) != null)
                retSb.append(line + append);
            
            errorOccur = false;
            
            reader.close();
            inputStream.close();
        }catch(IOException ignore){  // error stream
            errorOccur = true;
            try{
                retSb = new StringBuilder();
                var errorStream = rt.exec(command).getErrorStream();
                var reader = new BufferedReader(new InputStreamReader(errorStream, StandardCharsets.UTF_8));
                while((line = reader.readLine()) != null)
                    retSb.append(line + append);
                
                errorStream.close();
                reader.close();
            }catch(IOException ignore_inner){;}
        }

        return new ShellRet(retSb.toString(), errorOccur);
    }

    /**
     * 运行Shell脚本，并返经过String处理器处理过的流结果，默认编码由String处理器给出
     * @param command 需要运行的命令或者脚本
     * @return ShellRet 运行结果
     */
    public ShellRet run(String command, BaseProcessor baseProcessor){
        var retSb = new StringBuilder();
        String line;
        boolean errorOccur = true;

        // input stream
        try{
            var inputStream = rt.exec(command).getInputStream();
            var reader = new BufferedReader(new InputStreamReader(inputStream, StandardCharsets.UTF_8));
            while((line = reader.readLine()) != null)
                retSb.append(baseProcessor.processStr(line));
            
            errorOccur = false;
            
            reader.close();
            inputStream.close();
        }catch(IOException ignore){  // error stream
            errorOccur = true;
            try{
                retSb = new StringBuilder();
                var errorStream = rt.exec(command).getErrorStream();
                var reader = new BufferedReader(new InputStreamReader(errorStream, StandardCharsets.UTF_8));
                while((line = reader.readLine()) != null)
                    retSb.append(line + baseProcessor.processStr(line));
                
                errorStream.close();
                reader.close();
            }catch(IOException ignore_inner){;}
        }

        return new ShellRet(retSb.toString(), errorOccur);
    }    

    /**
     * 运行 top -b -n 1 命令 返回当前系统中占用内核和CPU最多的三个进程
     * @param command 需要运行的命令或者脚本
     */
    public ShellRet[] runs(String command){
        
        ShellRet[] ret = new ShellRet[2];
        ArrayList<MyProcess> processList = new ArrayList<>();
        
        String line;
        int index = 0;

        // input stream
        try(var inputStream = rt.exec(command).getInputStream();
            var reader = new BufferedReader(new InputStreamReader(inputStream, StandardCharsets.UTF_8))){

            while((line = reader.readLine()) != null){
                if(index ++ >= 7){
                    String[] params = line.trim().replaceAll("\\s+", " ").split(" ");
                    String processName = params[params.length - 1];
                    String processCPUUsage = params[8];
                    String processMEMUsage = params[9];
                    processList.add(new MyProcess(processName, Float.parseFloat(processCPUUsage), Float.parseFloat(processMEMUsage)));
                }
            }

            // 开始对取出的进程进行排序
            StringBuilder retSb = new StringBuilder();
            Collections.sort(processList, new cpuCompartor());
            for(int i = 0; i < Math.min(3, processList.size()); i ++)
                retSb.append(processList.get(i).processName + "&nbsp;&nbsp;" + processList.get(i).cpuUsage + "<br>");
            ret[0] = new ShellRet(retSb.toString(), false);


            retSb = new StringBuilder();
            Collections.sort(processList, new memComprator());
            for(int i = 0; i < Math.min(3, processList.size()); i ++)
            retSb.append(processList.get(i).processName + "&nbsp;&nbsp;" + processList.get(i).memUsage + "<br>");
            ret[1] = new ShellRet(retSb.toString(), false);

        }catch(IOException ignored){;}

        return ret;
    } 

    public static void main(String[] args) {
        new ShellRunner().runs("top -b -n 1");
    }
}