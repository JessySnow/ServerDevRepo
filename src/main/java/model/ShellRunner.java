package model;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

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
    public ShellRet run(String command){
        var retSb = new StringBuilder();
        String line;
        boolean errorOccur = true;

        // input stream
        try{
            var inputStream = rt.exec(command).getInputStream();
            var reader = new BufferedReader(new InputStreamReader(inputStream, StandardCharsets.UTF_8));
            while((line = reader.readLine()) != null)
                retSb.append(line);
            
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
                    retSb.append(line);
                
                errorStream.close();
                reader.close();
            }catch(IOException ignore_inner){;}
        }

        return new ShellRet(retSb.toString(), errorOccur);
    }
}

/**
 * 存放Process执行的结果
 * @author Jessy Snow
 * @version 1.0
 */
class ShellRet{
    public String ret;
    public boolean errorOccur;

    /**
     * @param ret Process输入流或者错误流的结果
     * @param errorOccur 是否发生了错误的标志位
     */
    protected ShellRet(String ret, boolean errorOccur){
        this.ret = ret;
        this.errorOccur = errorOccur;
    }
}