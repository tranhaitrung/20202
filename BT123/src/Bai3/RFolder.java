package Bai3;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class RFolder {
    private final List<String> listPathFile;
    private final String pathFolder;

    public RFolder(String path){
        listPathFile = new ArrayList<String>();
        this.pathFolder = path;
    }

    private void setListPathFile(){
        File folder = new File(pathFolder);
        File[] files = folder.listFiles();

        for (File tmp:files){
            String str = tmp.getAbsolutePath();
            listPathFile.add(str);
        }
    }

    public List<String> getListPathFile(){
        setListPathFile();
        System.out.println("Size " + listPathFile.size());
        return  listPathFile;
    }
}
