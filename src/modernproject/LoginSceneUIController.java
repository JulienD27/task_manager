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
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import java.io.File;

public class LoginSceneUIController implements Initializable {
    @FXML private TextField userNameField;
    @FXML private PasswordField passwordField;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    @FXML private void homeButtonAction(ActionEvent event) throws IOException {
        Parent homeControlParent = FXMLLoader.load(getClass().getResource("HomeControlSceneUI.fxml"));
        Scene homeControlScene = new Scene(homeControlParent);
        Stage homeControlWindow = (Stage)((Node)event.getSource()).getScene().getWindow();
        
        homeControlWindow.setScene(homeControlScene);
        homeControlWindow.show();
        homeControlWindow.setResizable(false);
        homeControlWindow.setTitle("Task Manager");
    }
    
    @FXML private void loginButtonAction(ActionEvent event) throws IOException, ClassNotFoundException {
        Alert alert = new Alert(Alert.AlertType.ERROR);
    	if(!(userNameField.getText().equals("") && passwordField.getText().equals(""))){
            UserInfo userFound = UserAction.readUserData(userNameField.getText());
            if(!(userFound == null)) {
                if(userFound.getUserName().equals(userNameField.getText()) && userFound.getPassword().equals(passwordField.getText())) {
                    ModernProject.setSignedUser(userFound);
                    File userDir = new File(ModernProject.directory, userFound.getUserName());
                    ModernProject.updateDir(userDir);
                    ModernProject.taskLL.fileToList("taskList.txt");
                    ModernProject.completedLL.fileToList("completedList.txt");
                    ModernProject.eventsLL.fileToList("eventList.txt");
                    ModernProject.journalLL.fileToList();

                    Parent homeParent = FXMLLoader.load(getClass().getResource("HomeSceneUI.fxml"));
                    Scene homeScene = new Scene(homeParent);
                    Stage homeWindow = (Stage)((Node)event.getSource()).getScene().getWindow();
                    homeWindow.setScene(homeScene);
                    homeWindow.show();
                    homeWindow.setResizable(false);
                    homeWindow.setTitle("Task Manager");
                }else if(!userFound.getPassword().equals(passwordField.getText())) {
                    alert.setContentText("Please input the right password!");
                    alert.showAndWait();
                }else {
                    alert.setContentText("It seems that you have not signed up for our services!");
                    alert.showAndWait();

                    Scene homeScene = new Scene(FXMLLoader.load(getClass().getResource("SignUpSceneUI.fxml")));
                    Stage homeWindow = (Stage) ((Node) event.getSource()).getScene().getWindow();
                    homeWindow.setScene(homeScene);
                    homeWindow.show();
                    homeWindow.setResizable(false);
                    homeWindow.setTitle("Task Manager");
                }
            } else {
                alert.setContentText("It seems that you have not signed up for our services!");
                alert.showAndWait();
            }
    	}else{
    	        alert.setHeaderText("Nothing is there");
                alert.setContentText("Put something in the text fields!");
                alert.showAndWait();
        }
    }
}
