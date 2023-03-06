package modernproject;

import java.io.IOException;
import java.net.URL;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import java.time.LocalDate;

public class TaskInputSceneUIController implements Initializable {
	@FXML private TextField taskInput;
	@FXML private DatePicker dateInput;
	@FXML private TextField typeInput;
	@FXML private TextArea descInput;
	@FXML private Button doneBtn;
	@FXML private Button cancelBtn;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		//TO-DO
	}
	
	@FXML private void doneBtnAction(ActionEvent event) throws IOException {
		LocalDate localDate = dateInput.getValue();
		DateTimeFormatter date = DateTimeFormatter.ofPattern("EEEE MMMM dd, yyyy");
		String dataArr[] = {taskInput.getText(), date.format(localDate), typeInput.getText(), descInput.getText()};
		ErrorHandle error = new ErrorHandle();
		boolean noError = error.checkInputs(dataArr);

		if(!noError){
			Tasks task = new Tasks(dataArr);
			boolean checkDuplicate = ModernProject.taskLL.insert(task);
			if(!checkDuplicate) {
				Alert alert = new Alert(Alert.AlertType.ERROR);
				alert.setHeaderText("No Duplicate Tasks");
				alert.setContentText("Please Input a Different Task Name as you have already inputted this task before!");
				alert.showAndWait();
			}else {
				ModernProject.taskLL.listToFile("taskList.txt");
				Parent taskParent = FXMLLoader.load(getClass().getResource("TaskSceneUI.fxml"));
				Scene taskScene = new Scene(taskParent);
				Stage taskWindow = (Stage)((Node)event.getSource()).getScene().getWindow();
				taskWindow.setScene(taskScene);
				taskWindow.show();
				taskWindow.setResizable(false);
				taskWindow.setTitle("Task Manager - Task");
			}
		}
	}

	@FXML private void cancelBtnAction(ActionEvent event) throws IOException {
		Parent taskParent = FXMLLoader.load(getClass().getResource("TaskSceneUI.fxml"));
		Scene taskScene = new Scene(taskParent);
		Stage taskWindow = (Stage)((Node)event.getSource()).getScene().getWindow();

		taskWindow.setScene(taskScene);
		taskWindow.show();
		taskWindow.setResizable(false);
		taskWindow.setTitle("Task Manager - Task");
	}
}
