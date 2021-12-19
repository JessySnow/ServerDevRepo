package model.shell;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

import model.stringprocessor.BaseProcessor;

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
}