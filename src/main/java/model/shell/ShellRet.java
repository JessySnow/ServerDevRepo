package model.shell;

/**
 * 存放Process执行的结果
 * @author Jessy Snow
 * @version 1.0
 */
public class ShellRet{
    private String ret;
    private boolean errorOccur;

    /**
     * @param ret Process输入流或者错误流的结果
     * @param errorOccur 是否发生了错误的标志位
     */
    protected ShellRet(String ret, boolean errorOccur){
        this.ret = ret;
        this.errorOccur = errorOccur;
    }

    public String getRet(){
        return this.ret;
    };

    public boolean isErrorOccur(){
        return this.errorOccur;
    };
}
