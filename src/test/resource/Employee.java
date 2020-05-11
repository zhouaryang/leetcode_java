package test.resource;

import java.io.Serializable;

/*
 * @introduction：this is class for test serializable
 * @time : 2020.4.30
 * @author: yangzhou  
 */
public class Employee implements Serializable {
	public String name;
	public String address;
	public transient int SSN;
	public int number;
	public void mailCheck(){
		System.out.println("Mailing a check to "+
				name + "address:"+ address);
	}
	
}
