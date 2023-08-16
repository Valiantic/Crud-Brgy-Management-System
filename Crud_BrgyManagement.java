/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.crud_brgymanagement;

/**
 *
 * @author HP
 */
import java.util.Scanner;
import java.util.ArrayList;

public class Crud_BrgyManagement {
	static Scanner s = new Scanner(System.in);
	// admin account
	static String account = "admin";
	static String[] accountEntities = {"username: ", "password: "};
	static String[] enteredAcc = new String[2];
	static int remainingAttempts = 3;
	
	static int maxResidents = 100;
	static int maxEntity = 13; // the 13 attributes (fullname, alias, age, gender, address... etc.)
	static String[][] residents = new String[maxResidents][maxEntity];
	static String[] entities = {" Full Name: ", " Nickname: ", " Age: ", " Gender (Male/Female): ", " Street: ", " Birthdate: ", " Place of Birth: ", " Phone Number: ", " Voter (Yes/No): ", " Occupation: ", " Civil Status: ", " Citizenship: ", " Address: "};
	static String[] entitiesBrgy = {"Full Name: ", "Address: ", "Pick-up Date (MM-DD-YYYY): ", "Purpose: "};
	static String[] entitiesBusinessPermit = {"Business Name: ", "Business Address: ", "Business Owner: ", "Business Type (ex. Sari-Sari Store/food and beverage): "};
	static int totalResidents = 0, totalBrgyInfo = 0, totalBusinessInfo = 0, totalCodes = 0;
	static ArrayList<String> brgyClearanReqInfo = new ArrayList<String>();
	static ArrayList<String> businessReqInfo = new ArrayList<String>();
	static ArrayList<String> allRequestedDocu = new ArrayList<String>();
	static ArrayList<String> trackingNumbers = new ArrayList<String>();
	static int totalRevenues = 0;
	
	public static void main(String[/* created by Arvee */] args)  throws InterruptedException {
		while(true) {
			if(remainingAttempts == 0) {
				remainingAttempts += 3;
			}
			System.out.print("\t\"BARANGAY MANAGEMENT INFORMATION SYSTEM\"\n[1] Admin Menu\n[2] Customer Service\n[0] Exit\n: ");
			String select = s.nextLine();
			if(select.equals("1")) {
				while(remainingAttempts != 0) {
					System.out.print("\tLogin\n");
					for(int i = 0; i < accountEntities.length; i++) {
						while(true) {
							System.out.print(accountEntities[i]);
							enteredAcc[i] = s.nextLine();
							if(containsText(enteredAcc[i])) {
								break;
							} else {
								System.out.println("\tPlease enter a text\n");
							}
						}
					}
					if(enteredAcc[0].equals(account) && enteredAcc[1].equals(account)) {
						adminSelection();
					} else {
						remainingAttempts--;
						String error = (enteredAcc[0].equals(account) && !enteredAcc[1].equals(account)) ? "\tError, wrong password. Remaining attempts " + remainingAttempts : "\tError, wrong username. Remaining attempts " + remainingAttempts;
						System.out.println("\n" + error + "\n");
						if(remainingAttempts != 0) {
							System.out.print("Do you want to continue? (Y/N)\n: ");
							String ans = s.nextLine();
							if(!ans.equals("y")) {
								break;
							}
						} else {
							// refreshing time 10 seconds - in case of login error, three times
							System.out.println("Refreshing..");
							for(int count = 10; count > 0; count--) {
								System.out.print(count + " ");
            					Thread.sleep(1000); // Pause for 1 second
        					}
        					System.out.println("done.");
						}
					}
				}
				System.out.println("\n");
			} else if(select.equals("2")) {
				customerService();
			} else if(select.equals("0")) {
				System.out.println("\tEXITING PROGRAM... Arigatōgozaimasu!");
				break;
			} else {
				System.out.println("\tERROR, PLEASE SELECT FROM THE GIVEN ABOVE!\n------------------------------------------------------\n");
			}
		}
		s.close();
	}
	
	static void adminSelection() {
		do {
			System.out.print("\n\tADMIN MENU\n[1] Resident Information\n[2] Barangay Clearance\n[3] Issue Business Permit\n[0] Logout\n: ");
			String choose = s.nextLine();
			if(choose.equals("0")) {
				if(remainingAttempts > 0) {
					remainingAttempts = 0;
				}
				break;
			}
			switch(choose) {
				case "1":
					resident();
					break;
				case "2":
					brgyClearance();
					break;
				case "3":
					businessPermit();
					break;
					
				default:
					System.out.println("\tERROR, PLEASE SELECT FROM THE GIVEN ABOVE!\n------------------------------------------------------\n");
			}
		} while(true);
		System.out.println("\n");
	}
	
	static void resident() {
		while(true) {
			System.out.print("\n\tResident Information\n[1] Add Resident\n[2] View Profile\n[3] Edit Resident Information\n[4] Remove Resident\n[5] Residents Record\n[0] Back\n: ");
			String choose = s.nextLine();
			if(choose.equals("0"))	 {
				System.out.println("\n");
				break;
			}
			switch(choose) {
			case "1":
				String add;
				System.out.print("\n\tResident Information | Add Resident\n\n\tAdd a resident\nResident #" + (totalResidents + 1) + "\n");
				for(int i = 0; i < residents[totalResidents].length; i++) {
					if(entities[i].equals(entities[2])) {
						do{
							System.out.print(entities[i]);
							add = s.nextLine();
							if(!add.matches("[0-9]+")) {
								System.out.println("\tInvalid Input\n");
							}
						} while(!add.matches("[0-9]+"));
						residents[totalResidents][i] = add;
					} else if(entities[i].equals(entities[3])) {
						do{
							System.out.print(entities[i]);
							add = s.nextLine();
							if(!add.equalsIgnoreCase("male") && !add.equalsIgnoreCase("female")) {
								System.out.println("\tInvalid Input\n");;
							}
						} while(!add.equalsIgnoreCase("male") && !add.equalsIgnoreCase("female"));
						residents[totalResidents][i] = add;
					} else if(entities[i].equals(entities[5])) {
						do{
							System.out.print(entities[i]);
							add = s.nextLine();
							if(!add.matches("[0-9/-]+")) {
								System.out.println("\tInvalid Input\n");;
							}
						} while(!add.matches("[0-9/-]+"));
						residents[totalResidents][i] = add;
					} else if(entities[i].equals(entities[7])) {
						do{
							System.out.print(entities[i]);
							add = s.nextLine();
							if(!add.matches("[0-9]+")) {
								System.out.println("\tInvalid Input\n");
							}
						}while(!add.matches("[0-9]+"));
						residents[totalResidents][i] = add;
					} else if(entities[i].equals(entities[8])) {
						do{
							System.out.print(entities[i]);
							add = s.nextLine();
							if(!add.equalsIgnoreCase("yes") && !add.equalsIgnoreCase("no")) {
								System.out.println("\tInvalid Input\n");
							}
						} while(!add.equalsIgnoreCase("yes") && !add.equalsIgnoreCase("no"));
						residents[totalResidents][i] = add;
					} else {
						do{
							System.out.print(entities[i]);
							add = s.nextLine();
							if(!containsText(add)) {
								System.out.println("\tPlease enter a text\n");
							}
						} while(!containsText(add));
						residents[totalResidents][i] = add;
					}
				}
				if(isExist(residents[totalResidents][0])) {
					System.out.println("\n\tFailed, this Resident is already registered!\n------------------------------------------------------\n");
				} else {
					totalResidents++;
					System.out.println("\n\tResident added successfully!\n------------------------------------------------------\n");
				}
				System.out.println("\n");
				break;
			case "2":
				System.out.print("\n\tResident Information | View Profile\n");
				if(totalResidents == 0) {
					System.out.println("\n\tNo residents found, please add a resident first.\n------------------------------------------------------\n");
				} else {
					System.out.print("Enter Full Name: ");
					String search = s.nextLine();
					if(isExist(search)) {
						System.out.println("\n\tSuccess! Resident found");
						for(int i = 0; i < totalResidents; i++) {
							if(search.equalsIgnoreCase(residents[i][0])) {
								System.out.println("Resident #" + (i + 1));
								for(int j = 0; j < residents[i].length; j++) {
									System.out.println(entities[j] + residents[i][j]);
								}
							}
						}
					} else {
						System.out.println("\n\tFailed! Resident not found\n------------------------------------------------------\n");
					}
				}
				System.out.println("\n");
				break;
			case "3":
				System.out.print("\n\tResident Information | Edit Resident Information\n");
				if(totalResidents == 0) {
					System.out.println("\n\tNo residents found, please add a resident first.\n------------------------------------------------------\n");
				} else{
					String[] prevEntities = {"Full Name", "Nickname", "Age", "Gender", "Street", "Birthdate", "Place of Birth", "Phone Number", "Voter", "Occupation", "Civil Status", "Citizenship", "Address"};
					String prevInfo = "", newInfo;
					System.out.print("Enter Full Name: ");
					String toEdit = s.nextLine();
					boolean found = false;
					for(int i = 0; i < totalResidents; i++) {
						if(toEdit.equalsIgnoreCase(residents[i][0])) {
							found = true;
							prevInfo = residents[i][0];
							System.out.print("\nWhich entities would you like to edit?\n(Full Name/ Nickname/ Age/ Gender/ Street/ Birthdate/ Place of Birth/ Phone Number/ Voter/ Occupation/ Civil Status/ Citizenship/ Address)\n: ");
							String edit = s.nextLine();
							boolean aa = false;
							for(int j = 0; j < prevEntities.length; j++) {
								if(edit.equalsIgnoreCase(prevEntities[j])) {
									aa = true;
									if(entities[j].equals(entities[2])) {
										do{
											System.out.print("\nNew" + entities[j]);
											newInfo = s.nextLine();
											if(!newInfo.matches("[0-9]+")) {
												System.out.println("\tInvalid Input\n");
											}
										} while(!newInfo.matches("[0-9]+"));
										residents[i][j] = newInfo;
									} else if(entities[j].equals(entities[3])) {
										do{
											System.out.print("\nNew" + entities[j]);
											newInfo = s.nextLine();
											if(!newInfo.equalsIgnoreCase("male") && !newInfo.equalsIgnoreCase("female")) {
												System.out.println("\tInvalid Input\n");
											}
										} while(!newInfo.equalsIgnoreCase("male") && !newInfo.equalsIgnoreCase("female"));
										residents[i][j] = newInfo;
									} else if(entities[j].equals(entities[5])) {
										do{
											System.out.print("\nNew" + entities[j]);
											newInfo = s.nextLine();
											if(!newInfo.matches("[0-9/-]+")) {
												System.out.println("\tInvalid Input\n");
											}
										} while(!newInfo.matches("[0-9/-]+"));
										residents[i][j] = newInfo;
									} else if(entities[j].equals(entities[7])) {
										do{
											System.out.print("\nNew" + entities[j]);
											newInfo = s.nextLine();
											if(!newInfo.matches("[0-9]+")) {
												System.out.println("\tInvalid Input\n");
											}
										}while(!newInfo.matches("[0-9]+"));
										residents[i][j] = newInfo;
									} else if(entities[j].equals(entities[8])) {
										do{
											System.out.print("\nNew" + entities[j]);
											newInfo = s.nextLine();
											if(!newInfo.equalsIgnoreCase("yes") && !newInfo.equalsIgnoreCase("no")) {
												System.out.println("\tInvalid Input\n");
											}
										} while(!newInfo.equalsIgnoreCase("yes") && !newInfo.equalsIgnoreCase("no"));
										residents[i][j] = newInfo;
									} else {
										do{
											System.out.print("\nNew" + entities[j]);
											newInfo = s.nextLine();
											if(!containsText(newInfo)) {
												System.out.println("\tPlease enter a text\n");
											}
										} while(!containsText(newInfo));
										residents[i][j] = newInfo;
									}
									System.out.println("\n\tResident Information for " + prevInfo + " was changed!\n------------------------------------------------------\n");
								}
							}
							if(!aa) {
								System.out.println("\tInvalid Entity\n");
							}
						}
					}
					if(!found) {
						System.out.println("\n\tFailed! Resident not found\n------------------------------------------------------\n");
					}
				}
				System.out.println("\n");
				break;
			case "4":
				System.out.print("\n\tResident Information | Remove Resident\n");
				if(totalResidents == 0) {
					System.out.println("\n\tNo residents found, please add a resident first.\n------------------------------------------------------\n");
				} else{
					System.out.print("Enter Full Name: ");
					String removeResident = s.nextLine();
					if(isExist(removeResident)) {
						for(int i  = 0; i < totalResidents; i++) {
							if(removeResident.equalsIgnoreCase(residents[i][0])) {
								while(i < totalResidents) {
									residents[i][0] = residents[i + 1][0];
									residents[i][1] = residents[i + 1][1];
									residents[i][2] = residents[i + 1][2];
									residents[i][3] = residents[i + 1][3];
									i++;
								}
							}
						}
						totalResidents--;
						System.out.println("\n\tResident removed successfully!\n------------------------------------------------------\n");
					} else {
						System.out.println("\n\tResident is not exist, please add the resident first.\n------------------------------------------------------\n");
					}
				}
				System.out.println("\n");
				break;
			case "5":
				System.out.print("\n\tResident Information | Residents Record\n");
				if(totalResidents == 0) {
					System.out.println("\n\tNo residents found, please add a resident first.\n------------------------------------------------------\n");
				} else{
					System.out.printf("----------------------------------------------------------------------%n");
					System.out.printf("%-3s %-17s | %-3s | %-12s | %-6s | %-12s %n", "", "FULLNAME", "AGE", "CIVIL STATUS", "GENDER", "VOTER STATUS");
					System.out.printf("----------------------------------------------------------------------%n");
					for(int i = 0; i < totalResidents; i++) {
						System.out.printf("%-3s %-17s | %-3s | %-12s | %-6s | %-12s %n", "#" + (i + 1), residents[i][0], residents[i][2], residents[i][10], residents[i][3], residents[i][8]);
					}
				}
				System.out.println("\n");
				break;
				
			default:
				System.out.println("\tERROR, PLEASE SELECT FROM THE GIVEN ABOVE!\n------------------------------------------------------\n");
			}
		}
	}
	
	static void brgyClearance() {
		while(true) {
			System.out.print("\n\tBarangay Clearance\n[1] View Document\n[2] Edit Document Status\n[3] Remove Document\n[4] Display all Documents\n[0] Back\n: ");
			String choose = s.nextLine();
			if(choose.equals("0"))	 {
				System.out.println("\n");
				break;
			}
			switch(choose) {
				case "1":
					if(totalBrgyInfo != 0) {
						System.out.print("\n\tBarangay Clearance | View Document");
						while(true) {
								System.out.print("\nFull Name: ");
								String name = s.nextLine();
								if(containsText(name)) {
									int counts = 0;
									boolean found = false;
									for(int i = 0; i < totalBrgyInfo; i++) {
										try{
											if(brgyClearanReqInfo.get(counts).equalsIgnoreCase(name)) {
												found = true;									
												System.out.println("\n\tDocument\nFull Name: " + brgyClearanReqInfo.get(counts) + "\nAddress: " + brgyClearanReqInfo.get(counts + 1) + "\nPick-up Date (MM-DD-YYYY): " + brgyClearanReqInfo.get(counts + 2) + "\nPurpose: " + brgyClearanReqInfo.get(counts + 3) + "\nStatus: " + brgyClearanReqInfo.get(counts + 4));
												break;
											}
										} catch(Exception e) {
											System.out.println(e);
										}
										counts += 6;
									}
									if(!found) {
										System.out.println("\n\tFailed, Document not found\n------------------------------------------------------\n");
									}
									break;
								} else {
									System.out.println("\tPlease enter a text\n");
								}
							}
					} else {
						System.out.println("\n\tNo Requested Document\n------------------------------------------------------\n");
					}
					System.out.println("\n");
					break;
					case "2":
						if(totalBrgyInfo != 0) {
							System.out.print("\n\tBarangay Clearance | Edit Document Status");
							while(true) {
								System.out.print("\nFull Name: ");
								String edit = s.nextLine();
								if(containsText(edit)) {
									boolean found = false;
									int counts = 0;
									for(int i = 0; i < totalBrgyInfo; i++) {
										if(brgyClearanReqInfo.get(counts).equalsIgnoreCase(edit)) {
											found = true;
											System.out.printf("----------------------------------------------------------------------%n%-17s | %-20s | %-12s | %-17s %n", "Name", "Service Type", "Pick-up Date", "Status");
											System.out.printf("----------------------------------------------------------------------%n%-17s | %-20s | %-12s | %-17s %n", brgyClearanReqInfo.get(0 + counts), brgyClearanReqInfo.get(5 + counts), brgyClearanReqInfo.get(2 + counts), brgyClearanReqInfo.get(4+ counts));
											while(true) {
												System.out.print("Edit Status (Processing/ Released/ Ready-to-Pickup): ");
												String stats = s.nextLine();
												if(containsText(stats)) {
													brgyClearanReqInfo.set(4+ counts, stats);
													allRequestedDocu.set(4+ counts, stats);
													break;
												} else {
													System.out.println("\tPlease enter a text\n");
												}
											}
										}
										counts += 6;
									}
									if(!found) {
										System.out.println("\n\tNot Found\n------------------------------------------------------\n");
									}
									break;
								} else {
									System.out.println("\tPlease enter a text\n");
								}
							}
						} else {
							System.out.println("\n\tNo Requested Document\n------------------------------------------------------\n");
						}
						System.out.println("\n");
						break;
					case "3":
						if(totalBrgyInfo != 0) {
							int counts = 0;
							boolean found = false;
							System.out.print("\n\tBarangay Clearance | Remove Document");
							System.out.print("\nFull Name: ");
							String remove = s.nextLine();
							for(int i = 0; i < totalBrgyInfo; i++) {
								if(brgyClearanReqInfo.get(counts).equalsIgnoreCase(remove)) {
									found = true;
									while(i < totalBrgyInfo) {
										try{
											brgyClearanReqInfo.set(counts, brgyClearanReqInfo.get(counts + 6));
											brgyClearanReqInfo.set(counts + 1, brgyClearanReqInfo.get(counts + 7));
											brgyClearanReqInfo.set(counts + 2, brgyClearanReqInfo.get(counts + 8));
											brgyClearanReqInfo.set(counts + 3, brgyClearanReqInfo.get(counts + 9));
											brgyClearanReqInfo.set(counts + 4, brgyClearanReqInfo.get(counts + 10));
											brgyClearanReqInfo.set(counts + 5, brgyClearanReqInfo.get(counts + 11));
										
											allRequestedDocu.set(counts, allRequestedDocu.get(counts + 6));
											allRequestedDocu.set(counts + 1, allRequestedDocu.get(counts + 7));
											allRequestedDocu.set(counts + 2, allRequestedDocu.get(counts + 8));
											allRequestedDocu.set(counts + 3, allRequestedDocu.get(counts + 9));
											allRequestedDocu.set(counts + 4, allRequestedDocu.get(counts + 10));
											allRequestedDocu.set(counts + 5, allRequestedDocu.get(counts + 11));
										} catch(Exception e) {}
										i++;
										counts += 6;
									}
									System.out.println("\n\tDocument, removed successfully!\n------------------------------------------------------\n");
									totalBrgyInfo--;
									totalCodes--;
									break;
								}
								counts += 6;
							}
							if(!found) {
								System.out.println("\n\tNot Found\n------------------------------------------------------\n");
							}
						} else {
							System.out.println("\n\tNo Requested Document\n------------------------------------------------------\n");
						}
						System.out.println("\n");
						break;
					case "4":
						if(totalBrgyInfo != 0) {
							int counts = 0;
							System.out.print("\n\tBarangay Clearance | Display all Documents");
							for(int i = 0; i < totalBrgyInfo; i++) {
								System.out.println("\nDocument #" + (i + 1) + "\n " + entitiesBrgy[0] + brgyClearanReqInfo.get(counts) + "\n " + entitiesBrgy[1] + brgyClearanReqInfo.get(counts + 1) + "\n " + entitiesBrgy[2] + brgyClearanReqInfo.get(counts + 2) + "\n " + entitiesBrgy[3] + brgyClearanReqInfo.get(counts + 3) + "\n Service Type: " + brgyClearanReqInfo.get(counts + 5) + "\n Status: " + brgyClearanReqInfo.get(counts + 4) + "\n");
								counts += 6;
							}
						} else {
							System.out.println("\n\tNo Requested Document\n------------------------------------------------------\n");
						}
						System.out.println("\n");
						break;
						
				default:
					System.out.println("\tERROR, PLEASE SELECT FROM THE GIVEN ABOVE!\n------------------------------------------------------\n");
			}
		}
	}
	
	static void businessPermit() {
		do{
			System.out.print("\n\tIssue Business Permit\n[1] View Document\n[2] Edit Document Status\n[3] Remove Document\n[4] Display all Documents\n[0] Back\n: ");
			String choose = s.nextLine();
			if(choose.equals("0"))	 {
				System.out.println("\n");
				break;
			}
			switch(choose) {
				case "1":
					if(totalBusinessInfo != 0) {
						System.out.print("\n\tIssue Business Permit | View Document");
						while(true) {
								System.out.print("\nBusiness Owner: ");
								String name = s.nextLine();
								if(containsText(name)) {
									int counts = 2;
									boolean found = false;
									for(int i = 0; i < totalBusinessInfo; i++) {
										try{
											if(businessReqInfo.get(counts).equalsIgnoreCase(name)) {
												found = true;									
												System.out.println("\n\tDocument\n" + entitiesBusinessPermit[0] + businessReqInfo.get(counts - 2) + "\n" + entitiesBusinessPermit[1] + businessReqInfo.get(counts - 1) + "\n" + entitiesBusinessPermit[2] + businessReqInfo.get(counts) + "\n" + entitiesBusinessPermit[3] + businessReqInfo.get(counts + 1) + "\nStatus: " + businessReqInfo.get(counts + 2));
												break;
											}
										} catch(Exception e) {
											System.out.println(e);
										}
										counts += 6;
									}
									if(!found) {
										System.out.println("\n\tFailed, Document not found\n------------------------------------------------------\n");
									}
									break;
								} else {
									System.out.println("\tPlease enter a text\n");
								}
							}
					} else {
						System.out.println("\n\tNo Requested Document\n------------------------------------------------------\n");
					}
					System.out.println("\n");
					break;
				case "2":
						if(totalBusinessInfo != 0) {
							System.out.print("\n\tIssue Business Permit | Edit Document Status");
							while(true) {
								System.out.print("\nBusiness Owner: ");
								String edit = s.nextLine();
								if(containsText(edit)) {
									boolean found = false;
									int counts = 0;
									for(int i = 0; i < totalBusinessInfo; i++) {
										if(businessReqInfo.get(counts + 2).equalsIgnoreCase(edit)) {
											found = true;
											System.out.printf("----------------------------------------------------------------------%n%-17s | %-20s | %-13s | %-17s %n", "Owner", "Service Type", "Business Type", "Status");
											System.out.printf("----------------------------------------------------------------------%n%-17s | %-20s | %-13s | %-17s %n", businessReqInfo.get(2 + counts), businessReqInfo.get(5 + counts), businessReqInfo.get(3 + counts), businessReqInfo.get(4+ counts));
											while(true) {
												System.out.print("Edit Status (Processing/ Released/ Ready-to-Pickup): ");
												String stats = s.nextLine();
												if(containsText(stats)) {
													businessReqInfo.set(4+ counts, stats);
													allRequestedDocu.set(4+ counts, stats);
													break;
												} else {
													System.out.println("\tPlease enter a text\n");
												}
											}
										}
										counts += 6;
									}
									if(!found) {
										System.out.println("\n\tNot Found\n------------------------------------------------------\n");
									}
									break;
								} else {
									System.out.println("\tPlease enter a text\n");
								}
							}
						} else {
							System.out.println("\n\tNo Requested Document\n------------------------------------------------------\n");
						}
						System.out.println("\n");
					break;
				case "3":
					if(totalBusinessInfo != 0) {
							int counts = 0;
							boolean found = false;
							System.out.print("\n\tIssue Business Permit | Remove Document");
							System.out.print("\nBusiness Owner: ");
							String removed = s.nextLine();
							for(int i = 0; i < totalBusinessInfo; i++) {
								if(businessReqInfo.get(counts + 2).equalsIgnoreCase(removed)) {
									found = true;
									while(i < totalBusinessInfo) {
										try{
											businessReqInfo.set(counts, businessReqInfo.get(counts + 6));
											businessReqInfo.set(counts + 1, businessReqInfo.get(counts + 7));
											businessReqInfo.set(counts + 2, businessReqInfo.get(counts + 8));
											businessReqInfo.set(counts + 3, businessReqInfo.get(counts + 9));
											businessReqInfo.set(counts + 4, businessReqInfo.get(counts + 10));
											businessReqInfo.set(counts + 5, businessReqInfo.get(counts + 11));
										
											allRequestedDocu.set(counts, allRequestedDocu.get(counts + 6));
											allRequestedDocu.set(counts + 1, allRequestedDocu.get(counts + 7));
											allRequestedDocu.set(counts + 2, allRequestedDocu.get(counts + 8));
											allRequestedDocu.set(counts + 3, allRequestedDocu.get(counts + 9));
											allRequestedDocu.set(counts + 4, allRequestedDocu.get(counts + 10));
											allRequestedDocu.set(counts + 5, allRequestedDocu.get(counts + 11));
										} catch(Exception e) {}
										i++;
										counts += 6;
									}
									System.out.println("\n\tDocument, removed successfully!\n------------------------------------------------------\n");
									totalBusinessInfo--;
									totalCodes--;
									break;
								}
								counts += 6;
							}
							if(!found) {
								System.out.println("\n\tNot Found\n------------------------------------------------------\n");
							}
						} else {
							System.out.println("\n\tNo Requested Document\n------------------------------------------------------\n");
						}
						System.out.println("\n");
					break;
				case "4":
					if(totalBusinessInfo != 0) {
							int counts = 0;
							System.out.print("\n\tIssue Business Permit | Display all Documents");
							for(int i = 0; i < totalBusinessInfo; i++) {
								System.out.println("\nDocument #" + (i + 1) + "\n " + entitiesBusinessPermit[0] + businessReqInfo.get(counts) + "\n " + entitiesBusinessPermit[1] + businessReqInfo.get(counts + 1) + "\n " + entitiesBusinessPermit[2] + businessReqInfo.get(counts + 2) + "\n " + entitiesBusinessPermit[3] + businessReqInfo.get(counts + 3) + "\n Service Type: " + businessReqInfo.get(counts + 5) + "\n Status: " + businessReqInfo.get(counts + 4) + "\n");
								counts += 6;
							}
						} else {
							System.out.println("\n\tNo Requested Document\n------------------------------------------------------\n");
						}
						System.out.println("\n");
						break;
					
				default:
					System.out.println("\tERROR, PLEASE SELECT FROM THE GIVEN ABOVE!\n------------------------------------------------------\n");
			}
		} while(true);
	}

	static void customerService() {
		do {
			System.out.print("\n\tServices\n[1] Request Barangay Clearance\n[2] Issue Business Permit\n[3] Track My Request\n[0] Back\n: ");
			String choose = s.nextLine();
			if(choose.equals("0")) {
				break;
			}
			switch(choose) {
				case "1":
					System.out.print("\n\tCustomer Service | Barangay Clearance\nYou would pay 150 for the fees, do you want to continue? (Y/N)\n: ");
					String yes = s.nextLine();
					System.out.println();
					if(yes.equalsIgnoreCase("y")) {
						String date, fname, cash;
						System.out.print("Amount to pay: P 150.00\ncash: ");
						cash = s.nextLine();
						if(!cash.matches("[0-9]+") || cash.equals("0")) {
							System.out.println("\tInvalid Input\n");
						} else {
							double change = Integer.parseInt(cash) - 150;
							if(change < 0) {
								System.out.println("\tPlease provide exact payment\n");
							} else {
								System.out.println("\n-----------------------------------\n \tCASH:   "+ cash + "\n\tFEES: - " + 150 + ".00\n\tCHANGE: = " + change + "\n-----------------------------------\n");
								totalRevenues += 150;
								System.out.println("\n\tDocument");
								for(int i = 0; i < entitiesBrgy.length; i++) {
									if(entitiesBrgy[i].equals(entitiesBrgy[2])) {
										do{
											System.out.print(entitiesBrgy[i]);
											date = s.nextLine();
											if(!date.matches("[0-9]+")) {
												System.out.println("\tInvalid Input\n");
											}
										} while(!date.matches("[0-9]+"));
										brgyClearanReqInfo.add(date);
										allRequestedDocu.add(date);
									} else {
										do{
											System.out.print(entitiesBrgy[i]);
											fname = s.nextLine();
											if(!containsText(fname)) {
												System.out.println("\tPlease enter a text\n");
											}
										} while(!containsText(fname));
										brgyClearanReqInfo.add(fname);
										allRequestedDocu.add(fname);
									}
								}
								brgyClearanReqInfo.add("Pending");
								allRequestedDocu.add("Pending");
								brgyClearanReqInfo.add("Barangay Clearance");
								allRequestedDocu.add("Barangay Clearance");
								String code = "CL-" + (int) (Math.random() * 10000);
								trackingNumbers.add(code);
								totalCodes++;
								totalBrgyInfo++;
								System.out.println("\nTRACKING CODE: " + code + "\n\n\tPlease wait for the certification to be processed.");
							}
						}
					} else {
						System.out.println("\n\tThank You, come back again\n------------------------------------------------------\n");
					}
					System.out.println("\n");
					break;
				case "2":
					System.out.print("\n\tCustomer Service | Issue Business Permit\nYou would pay 150 for the fees, do you want to continue? (Y/N)\n: ");
					String yes1 = s.nextLine();
					System.out.println();
					if(yes1.equalsIgnoreCase("y")) {
						System.out.print("Amount to pay: P 150.00\ncash: ");
						String cash = s.nextLine();
						if(!cash.matches("[0-9]+")) {
							System.out.println("\tInvalid Input\n");
						} else {
							double change = Integer.parseInt(cash) - 150;
							if(change < 0) {
								System.out.println("\tPlease provide exact payment\n");
							} else {
								System.out.println("\n-----------------------------------\n \tCASH:   "+ cash + "\n\tFEES: - " + 150 + ".00\n\tCHANGE: = " + change + "\n-----------------------------------\n");
								totalRevenues += 150;
								String add = "";
								System.out.println("\n\tDocument");
								for(int i = 0; i < entitiesBusinessPermit.length; i++) {
									do{
										System.out.print(entitiesBusinessPermit[i]);
										add = s.nextLine();
										if(!containsText(add)) {
											System.out.println("\tPlease enter a text\n");
										}
									} while(!containsText(add));
									businessReqInfo.add(add);
									allRequestedDocu.add(add);
								}
								businessReqInfo.add("Pending");
								allRequestedDocu.add("Pending");
								businessReqInfo.add("Business Permit");
								allRequestedDocu.add("Business Permit");
								String code = "BP-" + (int) (Math.random() * 10000);
								trackingNumbers.add(code);
								totalCodes++;
								totalBusinessInfo++;
								System.out.println("\nTRACKING CODE: " + code + "\n\n\tPlease wait for the certification to be processed.");
							}
						}
					} else {
						System.out.println("\n\tThank You, come back again\n------------------------------------------------------\n");
					}
					System.out.println("\n");
					break;
				case "3":
					System.out.print("\n\tCustomer Service | Track My Request\nTRACKING CODE: ");
					String code = s.nextLine();
					boolean result = false;
					int counts = 0;
					for(int i = 0; i < totalCodes; i++) {
						if(code.equals(trackingNumbers.get(i))) {
							if(code.contains("CL")) {
								result = true;
								System.out.printf("----------------------------------------------------------------------%n%-17s | %-20s | %-12s | %-17s %n", "Name", "Service Type", "Pick-up Date", "Status");
								System.out.printf("----------------------------------------------------------------------%n%-17s | %-20s | %-12s | %-17s %n", allRequestedDocu.get(0 + counts), allRequestedDocu.get(5 + counts), allRequestedDocu.get(2 + counts), allRequestedDocu.get(4+ counts));
								break;
							} else {
								result = true;
								System.out.printf("----------------------------------------------------------------------%n%-17s | %-20s | %-13s | %-17s %n", "Owner", "Service Type", "Business Type", "Status");
								System.out.printf("----------------------------------------------------------------------%n%-17s | %-20s | %-13s | %-17s %n", allRequestedDocu.get(2 + counts), allRequestedDocu.get(5 + counts), allRequestedDocu.get(3 + counts), allRequestedDocu.get(4+ counts));
							break;
							}
						}
						counts += 6;
					}
					if(!result) {
						System.out.println("\n\tInvalid Code\n------------------------------------------------------\n");
					}
					System.out.println("\n");
					break;
					
				default:
					System.out.println("\tERROR, PLEASE SELECT FROM THE GIVEN ABOVE!\n------------------------------------------------------\n");
			}
		} while(true);
		System.out.println("\n");
	}
	
	static boolean isExist(String search) {// used to check if the resident is existed or not
		boolean isFound = false;
		for(int i = 0; i < totalResidents; i++) {
			if(search.equalsIgnoreCase(residents[i][0])) {
				isFound = true;
				break;
			}
		}
		return isFound;
	}
	
	public static boolean containsText(String str) {// used to check if the entered string had a texts
        return str.matches(".*[a-zA-Z0-9\\p{Punct}]+.*");
    }
}
