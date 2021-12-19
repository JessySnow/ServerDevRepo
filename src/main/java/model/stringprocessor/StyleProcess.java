package model.stringprocessor;

public class StyleProcess extends BaseProcessor{
    private String header;
    private String footer;

    public StyleProcess(String header, String footer){
        super();
        this.header = header;
        this.footer = footer;
    }

    public String processStr(String str){
        return (header + str + footer);
    }
}
