package com.SimpliLearn.Projects.Phase1;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Registration {
	
	private String Username;
	private String Password;
	private static Login login = new Login();
	
	public void userRegistration() {
		boolean isUserNameAvailable=true;
		System.out.println();
		System.out.println("*****************************************************\n"
				+ "	  --Welcome to Registration Page--			\n"
							+ "******************************************************");
		Scanner scan = new Scanner(System.in);
		while(isUserNameAvailable) {
			System.out.println();
			System.out.print("Enter Username : ");
			this.setUsername(scan.nextLine());
			boolean isValid = login.validateUsername(this.getUsername());
			if(isValid) {
				System.out.println("Username is already exist");
				System.out.println("Do you want to login? (enter Y/N to answer) : ");
				String isLogin = scan.nextLine();
				if(isLogin.toUpperCase().equals("Y") || isLogin.toUpperCase().equals("N")) {
					if(isLogin.toUpperCase().equals("Y")) {
						Login login = new Login();
						login.userLogin();
					}
				}
				else {
					System.out.println("Invalid input given");
				}
			}
			else {
				isUserNameAvailable=false;
			}
		}
		System.out.println();
		System.out.print("\nEnter Password : ");
		setPassword(scan.nextLine());
		try {
			writeCredentials();
			System.out.println("User registered successfully");
			login.userLogin();
			isUserNameAvailable=false;
		} catch (Exception e) {
			scan.close();
			System.out.println("Error occured");
			e.printStackTrace();
		}
	}

	public String getUsername() {
		return Username;
	}

	public void setUsername(String username) {
		Username = username;
	}

	public String getPassword() {
		return Password;
	}

	public void setPassword(String password) {
		Password = password;
	}
	
	public void writeCredentials() throws IOException,FileNotFoundException {
		File credentialFile = new File("Credentials.txt");
		FileWriter fw;
		if(credentialFile.exists()) {
			fw = new FileWriter("Credentials.txt", true);
		}
		else {
			fw = new FileWriter("Credentials.txt");
		}
		BufferedWriter out = new BufferedWriter(fw);
		out.write(getUsername()+" "+getPassword());
		out.write("\r\n");
		out.close();
	}
}
