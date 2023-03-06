package modernproject;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import javafx.scene.control.TextArea;

public class Tasks implements Comparable<Tasks> {
    private String taskN;
    private String date;
    private String type;
    private String desc;

    public Tasks(String dataArr[]) {
        this.taskN = dataArr[0];
        this.date = dataArr[1];
        this.type = dataArr[2];
        this.desc = dataArr[3];
    }

    public String getTaskName() {
        return taskN;
    }

    public String getDueDate() {
        return date;
    }

    public String getType() {
        return type;
    }

    public String getDesc() {
        return desc;
    }

    public void setTaskName(String name) {
        taskN = name;
    }

    public void setDueDate(String ddate) {
        date = ddate;
    }

    public void setType(String nType) {
        type = nType;
    }

    public void setDesc(String nDesc) {
        desc = nDesc;
    }

    public String[] transferEditToFile(TextArea taskSelectedArea) throws IOException {
        File editFile = new File(ModernProject.directory, "ScannedEditFile.txt");
        FileWriter writer = new FileWriter(editFile);
        writer.write(taskSelectedArea.getText());
        writer.close();

        Scanner scan = new Scanner(editFile);
        String[] edits = new String[4];
        int counter = 0;
        while(scan.hasNextLine()) {
            String line = scan.nextLine();
            String[] lineSplit = line.split(": ", 2);
            edits[counter++] = lineSplit[1];
        }
        scan.close();
        return edits;
    }

    @Override
    public int compareTo(Tasks o) {
        return taskN.compareTo(o.getTaskName());
    }

    public String toString() {
        return taskN;
    }
}
