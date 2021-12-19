package model.stringprocessor;

public class StyleProcessor extends BaseProcessor{
    private String header;
    private String footer;

    public StyleProcessor(String header, String footer){
        super();
        this.header = header;
        this.footer = footer;
    }

    @Override
    public String processStr(String str){
        return (header + str + footer);
    }
}
