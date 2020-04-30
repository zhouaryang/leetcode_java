package test;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
//		序列化对象
		Serializable();
//		反序列化
		DesSerializable();
	}
	
	public static void Serializable(){
		Employee e = new Employee();
		e.name = "Reyan Ali";
		e.address = "Phokka kuan,Ambehta Peer";
		e.SSN = 11122333;
		e.number = 101;
		
		try{
			FileOutputStream fileOut = 
					new FileOutputStream("D:/sn.txt");
			ObjectOutputStream outputStream = new ObjectOutputStream(fileOut);
			outputStream.writeObject(e);
			outputStream.close();
			fileOut.close();
			System.out.println("Serialized data is saved int D:/sn.txt");
			
		}catch(IOException i){
			i.printStackTrace();
		}
	}
	
	public static void DesSerializable(){
		Employee e = null;
		try{
			FileInputStream fileIn = new FileInputStream("D:/sn.txt"); //throw f
			ObjectInputStream in = new ObjectInputStream(fileIn);      //throw i
			e = (Employee) in.readObject();  //e2
			in.close();
			fileIn.close();
		}catch(FileNotFoundException f){
			f.printStackTrace();
		}catch (IOException i) {
			i.printStackTrace();
		}catch (ClassNotFoundException e2) {
			// TODO: handle exception
			e2.printStackTrace();
		}
		System.out.println("Deserializad Employee...");
		System.out.println("Name: "+ e.name);
		System.out.println("Address: "+ e.address); 
		System.out.println("SSN: " + e.SSN);
	    System.out.println("Number: " + e.number);
	}
}
