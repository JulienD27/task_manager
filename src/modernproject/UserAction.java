package modernproject;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class UserAction {
	private static ArrayList <UserInfo> userList = new ArrayList<UserInfo>();

	@SuppressWarnings("unchecked")
	//takes the incoming UserInfo object and serializes the information into a text file
	public static void serializeSignUp(UserInfo user) throws IOException, ClassNotFoundException {
		File file = new File("UserInfo.txt");
		
		if(file.exists()) {
			ObjectInputStream objectInput = new ObjectInputStream(new BufferedInputStream
					(new FileInputStream("UserInfo.txt")));
			userList = (ArrayList<UserInfo>) objectInput.readObject();
			objectInput.close();
		} else {
			userList =  new ArrayList<UserInfo>();
		}
		
		FileOutputStream fileOut = new FileOutputStream(file, false);
		ObjectOutputStream out = new ObjectOutputStream(fileOut);

		userList.add(user);
		out.writeObject(userList);
		fileOut.close();
		out.close();
	}
	
	@SuppressWarnings("unchecked")
	//the incoming String will be the username of the user and it will read the text file for the information
	public static UserInfo readUserData(String username) throws IOException, ClassNotFoundException {
		File file = new File("UserInfo.txt");
		UserInfo user = null;
		if(file.exists()){
			FileInputStream fileInput = new FileInputStream(file);
			ObjectInputStream objectInput = new ObjectInputStream(new BufferedInputStream(fileInput));
			userList = (ArrayList<UserInfo>) objectInput.readObject();
			objectInput.close();

			user = findUser(username);
		}
		if(user != null) {
			return user;
		}
		return null;
	}
	
	private static UserInfo findUser(String username) {
		for(int i = 0; i < userList.size(); i++) {
			if(userList.get(i).getUserName().equals(username)) {
				return userList.get(i);
			}
		}
		return null;
	}
}
