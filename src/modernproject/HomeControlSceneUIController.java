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
import javafx.stage.Stage;
import javafx.scene.control.Alert;

public class HomeControlSceneUIController implements Initializable {
    private Alert alert = new Alert(Alert.AlertType.ERROR);

    @Override
    public void initialize(URL url, ResourceBundle rb) {
    	alert.setTitle("No Access");
        alert.setHeaderText("Log In or Sign Up");
    }    

    @FXML private void homeButtonAction(ActionEvent event) {
        //home controller so it would not do anything when clicked :)
    }
    
    @FXML private void taskButtonAction(ActionEvent event) throws IOException {
    	alert.setContentText("Please log in or sign up before you can access the application features.");
        alert.showAndWait();
    }

    @FXML private void calendarButtonAction(ActionEvent event) throws IOException {
    	alert.setContentText("Please log in or sign up before you can access the application features.");
        alert.showAndWait();
    }

    @FXML private void journalButtonAction(ActionEvent event) throws IOException {
    	alert.setContentText("Please log in or sign up before you can access the application features.");
        alert.showAndWait();
    }
    
    @FXML private void loginButtonAction(ActionEvent event) throws IOException{
        Parent loginParent = FXMLLoader.load(getClass().getResource("LoginSceneUI.fxml"));
        Scene loginScene = new Scene(loginParent);
        Stage loginWindow = (Stage)((Node)event.getSource()).getScene().getWindow();
        
        loginWindow.setScene(loginScene);
        loginWindow.show();
        loginWindow.setResizable(false);
        loginWindow.setTitle("Task Manager - Log In");
    }
    
    @FXML private void signupButtonAction(ActionEvent event) throws IOException{
        Parent signParent = FXMLLoader.load(getClass().getResource("SignUpSceneUI.fxml"));
        Scene signScene = new Scene(signParent);
        Stage signWindow = (Stage)((Node)event.getSource()).getScene().getWindow();
        
        signWindow.setScene(signScene);
        signWindow.show();
        signWindow.setResizable(false);
        signWindow.setTitle("Task Manager - Sign Up");
    }
    
}
