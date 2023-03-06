/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modernproject;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.scene.control.*;

public class JournalSceneUIController implements Initializable {
    @FXML private TextField nameField;
    @FXML private TextArea entryField;
    @FXML private ListView<String> entryList;
    @FXML private TextArea displayArea;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        loadData();
        displayArea.setText("Nothing is selected!");
    }    
    
    @FXML private void homeButtonAction(ActionEvent event) throws IOException {
    	if(ModernProject.signedUser != null) {
    		Parent homeParent = FXMLLoader.load(getClass().getResource("HomeSceneUI.fxml"));
            Scene homeScene = new Scene(homeParent);
            Stage homeWindow = (Stage)((Node)event.getSource()).getScene().getWindow();
            
            homeWindow.setScene(homeScene);
            homeWindow.show();
            homeWindow.setResizable(false);
            homeWindow.setTitle("Task Manager");
    	} else {
    		Parent homeParent = FXMLLoader.load(getClass().getResource("HomeControlSceneUI.fxml"));
            Scene homeScene = new Scene(homeParent);
            Stage homeWindow = (Stage)((Node)event.getSource()).getScene().getWindow();
            
            homeWindow.setScene(homeScene);
            homeWindow.show();
            homeWindow.setResizable(false);
            homeWindow.setTitle("Task Manager");
    	}
    }
    
    @FXML private void taskButtonAction(ActionEvent event) throws IOException {
        Parent taskParent = FXMLLoader.load(getClass().getResource("TaskSceneUI.fxml"));
        Scene taskScene = new Scene(taskParent);
        Stage taskWindow = (Stage)((Node)event.getSource()).getScene().getWindow();
        
        taskWindow.setScene(taskScene);
        taskWindow.show();
        taskWindow.setResizable(false);
        taskWindow.setTitle("Task Manager - Task");
    }

    @FXML private void calendarButtonAction(ActionEvent event) throws IOException {
        Parent calendarParent = FXMLLoader.load(getClass().getResource("CalendarSceneUI.fxml"));
        Scene calendarScene = new Scene(calendarParent);
        Stage calendarWindow = (Stage)((Node)event.getSource()).getScene().getWindow();
        
        calendarWindow.setScene(calendarScene);
        calendarWindow.show();
        calendarWindow.setResizable(false);
        calendarWindow.setTitle("Task Manager - Calendar");

    }

    @FXML private void journalButtonAction(ActionEvent event) throws IOException {
        Scene journalScene = new Scene(FXMLLoader.load(getClass().getResource("JournalSceneUI.fxml")));
        Stage journalWindow = (Stage)((Node)event.getSource()).getScene().getWindow();

        journalWindow.setScene(journalScene);
        journalWindow.show();
        journalWindow.setResizable(false);
        journalWindow.setTitle("Task Manager - Journal");
    }

    @FXML private void clearBtnAction(ActionEvent event) throws IOException{
        Scene journalScene = new Scene(FXMLLoader.load(getClass().getResource("JournalSceneUI.fxml")));
        Stage journalWindow = (Stage)((Node)event.getSource()).getScene().getWindow();
        journalWindow.setScene(journalScene);
        journalWindow.show();
        journalWindow.setResizable(false);
        journalWindow.setTitle("Task Manager - Journal");
    }

    @FXML private void createBtnAction(ActionEvent event) throws IOException {
        if(nameField.getText().equals("") && entryField.getText().equals("")) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("No Information");
            alert.setContentText("Please input information on the fields provided!");
            alert.showAndWait();
        } else {
            String[] data = new String[2];
            data[0] = nameField.getText();
            data[1] = entryField.getText();
            Entry newEntry = new Entry(data);
            ModernProject.journalLL.push(newEntry);
            ModernProject.journalLL.listToFile();

            Scene journalScene = new Scene(FXMLLoader.load(getClass().getResource("JournalSceneUI.fxml")));
            Stage journalWindow = (Stage)((Node)event.getSource()).getScene().getWindow();
            journalWindow.setScene(journalScene);
            journalWindow.show();
            journalWindow.setResizable(false);
            journalWindow.setTitle("Task Manager - Journal");
        }
    }

    @FXML public void handleMouseClick(MouseEvent arg0) {
        String entryNum = entryList.getSelectionModel().getSelectedItem();
        if(entryNum == null || entryNum.isEmpty()) {
            displayArea.setText("Nothing is selected!");
        }else{
            Entry o = ModernProject.journalLL.findNode(entryNum);
            displayArea.setText(o.getEntryName() + ":\n\t" + o.getEntry());
        }
    }

    @FXML private void popBtnAction(ActionEvent event) throws IOException {
        if(ModernProject.journalLL.pop()) {
            ModernProject.journalLL.listToFile();
            Scene journalScene = new Scene(FXMLLoader.load(getClass().getResource("JournalSceneUI.fxml")));
            Stage journalWindow = (Stage)((Node)event.getSource()).getScene().getWindow();
            journalWindow.setScene(journalScene);
            journalWindow.show();
            journalWindow.setResizable(false);
            journalWindow.setTitle("Task Manager - Journal");
        }else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("Nothing to remove");
            alert.setContentText("There are no journal entries to remove.");
            alert.showAndWait();
        }
    }

    private void loadData(){
        String[] entries = ModernProject.journalLL.displayNodes();
        entryList.getItems().addAll(entries);
    }
}
