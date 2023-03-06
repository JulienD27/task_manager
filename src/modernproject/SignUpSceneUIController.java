package modernproject;

import java.io.File;
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
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.scene.control.PasswordField;

public class SignUpSceneUIController implements Initializable {
    @FXML private TextField fnText;
    @FXML private TextField lnText;
    @FXML private TextField userText;
    @FXML private PasswordField passText;
    @FXML private PasswordField confirmText;
    @FXML private TextField hiddenPass;
    @FXML private TextField hiddenConfirm;
    @FXML private CheckBox showPassword;
    
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
    
    @FXML private void showPasswordAction(ActionEvent event) throws IOException{
        if(showPassword.isSelected()){
            hiddenPass.setText(passText.getText());
            hiddenConfirm.setText(confirmText.getText());
            hiddenPass.setDisable(true);
            hiddenConfirm.setDisable(true);
            passText.setVisible(false);
            confirmText.setVisible(false);
        }else{
            passText.setVisible(true);
            confirmText.setVisible(true);
        }
    }
    
    @FXML private void signButtonAction(ActionEvent event) throws IOException, ClassNotFoundException {
        String signUpArray[] = {fnText.getText(), lnText.getText(), userText.getText(), passText.getText(), confirmText.getText()};
        ErrorHandle error = new ErrorHandle();
        boolean noError = error.checkSignUp(signUpArray);
        
        if(noError){
        	UserInfo user = new UserInfo(fnText.getText(), lnText.getText(), userText.getText(), passText.getText());
            UserAction.serializeSignUp(user);
            
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setContentText("Thank you for signing up! Please login to access the features!");
            alert.showAndWait();

            File userDir = new File(ModernProject.directory.getAbsolutePath(),userText.getText());
            if(!userDir.exists()) {
                userDir.mkdir();
            }

            Parent homeParent = FXMLLoader.load(getClass().getResource("LoginSceneUI.fxml"));
            Scene homeScene = new Scene(homeParent);
            Stage homeWindow = (Stage)((Node)event.getSource()).getScene().getWindow();

            homeWindow.setScene(homeScene);
            homeWindow.show();
            homeWindow.setResizable(false);
            homeWindow.setTitle("Task Manager");
        }
    }
}
