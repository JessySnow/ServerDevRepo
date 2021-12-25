package model.stringprocessor;

/**
 * 处理 top 命令
 */
public class TopProcessor extends BaseProcessor{
    private String footer;
    private int lineIndex;
    public TopProcessor(String footer){
        this.footer = footer;
        lineIndex = 0;
    }

    @Override
    public String processStr(String str){
        // 过滤掉top的表头，和 top 自身产生的 process
        if(lineIndex ++ <= 6 || str.contains("top")) return "";

        String[] strList = str.trim().replaceAll("\\s+", " ").split(" ");
        String formDemo = "<form method='post' action='killProcess'><input style='border:none' type='text' name=pid value=%s> %s <input type='submit' value=kill></form>";
        String form =  String.format(formDemo, strList[0], strList[strList.length - 1]);    //strList[0],strList[strList.length-1]分别对应pid和pname
        return form;
    }
}
