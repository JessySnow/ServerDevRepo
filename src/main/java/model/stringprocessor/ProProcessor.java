package model.stringprocessor;

import java.util.ArrayList;
import java.util.List;

import model.process.MyProcess;

public class ProProcessor extends BaseProcessor{
    
    private String footer;
    private int lineIndex;
    private List<MyProcess> processList;

    public ProProcessor(String footer){
        this.footer = footer;
        lineIndex = 0;
        processList = new ArrayList<>();
    }
}
