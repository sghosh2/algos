package com.lamda.prac;

import java.util.Arrays;
import java.util.function.Function;

public class Lamdaexp {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		MathOpt subtraction = (a, b) -> {return a - b;};
		
		Function print = (String message, String msg ) -> {System.out.println("Hello " + message); return message;};

		print.print(""+subtraction.operate(10, 5));
		
		
		
		Arrays.asList( "element", "other", "another one" ).forEach( e -> { System.out.println(e); } );
	}

	   interface MathOpt {
	      int operate(int a, int b);
	   }  
	   @FunctionalInterface
	   interface Print {
		      void print(String message);
		   }
	   
	   
	   public class Person {
		   
		   private String firstName, lastName, job, gender;
		   private int salary, age;
		    
		   public Person(String firstName, String lastName, String job, String gender, int age, int salary)       {
		             this.firstName = firstName;
		             this.lastName = lastName;
		             this.gender = gender;
		             this.age = age;
		             this.job = job;
		             this.salary = salary;
		   }
		   // Getter and Setter 
		   . . . . .   
		   }  
	   
}
