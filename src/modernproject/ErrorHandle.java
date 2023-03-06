package modernproject;

import javafx.scene.control.Alert;

public class ErrorHandle {
    private Alert alert;
    
    public ErrorHandle(){
        this.alert = new Alert(Alert.AlertType.ERROR);
    }

    public void warningMessage() {
        alert.setTitle("Entry Text Area Error");
        alert.setHeaderText("Follow Instructions!");
        alert.setContentText("Please do not use any line breaks or new line in your entries " +
                "as this will cause an error " + "in reading the files afterwards. Thank you very much.");
        alert.showAndWait();
    }
    
    public boolean checkSignUp(String info[]){
        boolean noError = false;
        alert.setTitle("Input Error");
        alert.setHeaderText(null);
        boolean noInput = false, yesInput = false;
        
        for(int x = 0; x < 5; x++){
            if(info[x].equals("")){
                noInput = true;
            }else 
                yesInput = true;
        }
        
        if(noInput && yesInput != true){
            alert.setContentText("Please input your information in the text fields!");
            alert.showAndWait();
        }else if(info[0].equals("")){
            alert.setContentText("Please input a first name!");
            alert.showAndWait();
        }else if(info[1].equals("")){
            alert.setContentText("Please input a last name!");
            alert.showAndWait();
        }else if(info[2].equals("")){
            alert.setContentText("Please input a username!");
            alert.showAndWait();
        }else if(info[3].equals("")){
            alert.setContentText("Please input a password!");
            alert.showAndWait();
        }else if(info[4].equals("")){
            alert.setContentText("Please confirm your password!");
            alert.showAndWait();
        }else{
            if(info[3].equals(info[4])){
                noError = true;
            }else{
                alert.setContentText("Password does not match!");
                alert.showAndWait();
            }
        }
        return noError;
    }

    public boolean checkInputs(String dataArr[]) {
        boolean error = false;
        for(int x = 0; x < dataArr.length; x++) {
            if(dataArr[x].equals("")) {
                error = true;
            }
        }
        if(error) {
            alert.setHeaderText("Input all fields");
            alert.setContentText("Please input information into all fields");
            alert.showAndWait();
        }
        return error;
    }
}
