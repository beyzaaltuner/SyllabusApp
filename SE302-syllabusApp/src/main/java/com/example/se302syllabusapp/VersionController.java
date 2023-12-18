package com.example.se302syllabusapp;


import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class VersionController extends FileManager {
    ArrayList<Integer> diffIndex;
    SyllabusData syllabusData1;
    SyllabusData syllabusData2;

    public VersionController(SyllabusData syllabusData1, SyllabusData syllabusData2) {
        super(syllabusData1, syllabusData2);
    }


    public void compare(SyllabusData syllabusData1, SyllabusData syllabusData2){
        for (int i = 0; i < syllabusData1.getChildren().size(); i++) {

            ArrayList<SyllabusData> children1 = syllabusData1.getChildren().get(i).getChildren();
            ArrayList<SyllabusData> children2 = syllabusData2.getChildren().get(i).getChildren();

            for (int j = 0; j < children1.size(); j++) {
                ArrayList<SyllabusData> grandChildren1 = children1.get(j).getChildren();
                ArrayList<SyllabusData> grandChildren2 = children2.get(j).getChildren();
                for (int k = 0; k < grandChildren1.size(); k++) {
                    if (!grandChildren1.get(k).getValue().equals(grandChildren2.get(k).getValue())){
                        System.out.println(grandChildren1.get(k).getValue() + " --- " + grandChildren2.get(k).getValue() );
                    }

                }
            }
        }
    }

    public void showDescriptions(String filePath){


        int lastDotIndex = filePath.lastIndexOf('.');
        if (lastDotIndex > 0) {
            filePath = filePath.substring(0, lastDotIndex)+"txt";
        }

        try  {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(filePath));
            String line;


            while ((line = bufferedReader.readLine()) != null) {
                System.out.println(line);
            }

        }catch(IOException e) {
            System.out.println("file not found");
        }


    }

}


