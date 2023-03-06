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
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.control.TableColumn;
import javafx.stage.Stage;
import javafx.scene.control.cell.PropertyValueFactory;

public class CalendarSceneUIController implements Initializable {
    @FXML private ComboBox<String> yearCombo;
    @FXML private ComboBox<String> monthCombo;
    @FXML private Label calendarLabel;
    @FXML private TableView<Deadlines> calendarTableView;
    @FXML private TableColumn<Deadlines, String> monthColumn;
    @FXML private TableColumn<Deadlines, String> dayColumn;
    @FXML private TableColumn<Deadlines, String> dwColumn;
    @FXML private TableColumn<Deadlines, String> taskColumn;
    @FXML private TableColumn<Deadlines, String> eventColumn;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        calendarLabel.setText("Calendar");
        BuildCalendar build = new BuildCalendar();
        build.buildMonthComboBox(monthCombo);
        build.buildYearComboBox(yearCombo);
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
        Scene calendarScene = new Scene(FXMLLoader.load(getClass().getResource("CalendarSceneUI.fxml")));
        Stage calendarWindow = (Stage)((Node)event.getSource()).getScene().getWindow();
        calendarWindow.setScene(calendarScene);
        calendarWindow.show();
        calendarWindow.setResizable(false);
        calendarWindow.setTitle("Task Manager - Calendar");
    }

    @FXML private void journalButtonAction(ActionEvent event) throws IOException {
        Parent journalParent = FXMLLoader.load(getClass().getResource("JournalSceneUI.fxml"));
        Scene journalScene = new Scene(journalParent);
        Stage journalWindow = (Stage)((Node)event.getSource()).getScene().getWindow();
        journalWindow.setScene(journalScene);
        journalWindow.show();
        journalWindow.setResizable(false);
        journalWindow.setTitle("Task Manager - Journal");

        ErrorHandle o = new ErrorHandle();
        o.warningMessage();
    }

    @FXML private void createBtnAction(ActionEvent event) throws NullPointerException {
        calendarTableView.getItems().clear();
        String[] calendar = new String[2];
        calendar[0] = yearCombo.getSelectionModel().getSelectedItem();
        calendar[1] = monthCombo.getSelectionModel().getSelectedItem();
        boolean error = calendar[0].isEmpty() && calendar[1].isEmpty();
        BuildCalendar build = new BuildCalendar();

        monthColumn.setCellValueFactory(new PropertyValueFactory<>("Month"));
        dayColumn.setCellValueFactory(new PropertyValueFactory<>("Day"));
        dwColumn.setCellValueFactory(new PropertyValueFactory<>("DayWeek"));
        taskColumn.setCellValueFactory(new PropertyValueFactory<>("Task"));
        eventColumn.setCellValueFactory(new PropertyValueFactory<>("Event"));

        String date, task, eventName;
        if(!error) {
            int yNum = Integer.parseInt(calendar[0]);
            int mNum = build.monthConverter(calendar[1]);
            int daysOfMonth = build.getDaysInMonth(yNum, (mNum+1));
            calendarLabel.setText(calendar[1] + " " + calendar[0]);

            for(int x = 0; x < daysOfMonth; x++){
                date = build.returnDayWeek(yNum, mNum, (x+1));
                task = build.findingDeadline(yNum, mNum, (x+1), calendar[1], "taskLL");
                eventName = build.findingDeadline(yNum, mNum, (x+1), calendar[1], "eventsLL");
                calendarTableView.getItems().add(new Deadlines(calendar[1], (x + 1), date, task, eventName));
            }
        }
    }
}
