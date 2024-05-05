package com.pizza.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;
public class Pizza {

	public static void main(String[] args) {
		Scanner sc=null;
		Connection con=null;
		Statement st=null;
		ResultSet rs=null;
		String topy;
		String cheesey;
		String takey;
		String choice;
		String size;
		int price=0;
		System.out.println("                    WELCOME TO UNO PIZZERIA");
		System.out.println("===========================================================");
		System.out.println("\t\t\tPRICE CHART");
		System.out.println("***********************************************************");
		System.out.println("PIZZA                                         REGULAR         MEDIUM");
		System.out.println("Veg Margherita                                  99               159");
		System.out.println("Veg Classic Corn                                119               179");
		System.out.println("Veg Exotica                                       139               179");
		System.out.println("Veg Tandoori Paneer                         139               179");
		System.out.println("Veg Spiced Paneer                             139              179");
		System.out.println("Veg Paneer Tikka                               139              179");
		System.out.println("NonVeg BBQ Chicken                         149             220");
		System.out.println("NonVeg Peri Peri Chicken                   149             220");
		System.out.println("NonVeg Cheesy Chicken Tikka           149             220");
		System.out.println("NonVeg Schezwan Chicken                 149             220");
		System.out.println("NonVeg Chicken Sausage                    149             220");
		System.out.println("NonVeg Golden Chicken                      149             220");
		System.out.println("NonVeg  Chicken 65                            149             220");
		System.out.println("NonVeg Cheesy Chicken Keema          149             220");
		System.out.println("************************************************************");
		try {
			sc=new Scanner(System.in);
			if(sc!=null)
			{
				System.out.print("ENTER THE PIZZA THAT YOU WANT:");
				choice=sc.nextLine();
				System.out.print("WHICH PIZZA SIZE DO YOU WANT(REGULAR/MEDIUM)?(PRESS R/M):");
				size=sc.nextLine();
				//Load the Driver Class
				Class.forName("com.mysql.cj.jdbc.Driver");
				//Establish the connection
				con=DriverManager.getConnection("jdbc:mysql://localhost:3306/pizza_bill","root","resume");
				//Create statement object
				if(con!=null)
					st=con.createStatement();
				//Prepare the sql query
				String query="select * from pizza";
				if(st!=null)
					rs=st.executeQuery(query);
				if(rs!=null)
				{
					while(rs.next())
					{
						if(rs.getString(2).equalsIgnoreCase(choice) && size.equalsIgnoreCase("R"))
						{
							price=price+rs.getInt(3);
							break;
						}
					}
				}
				if(st!=null)
					rs=st.executeQuery(query);
				if(rs!=null)
				{
					while(rs.next())
					{
						if(rs.getString(2).equalsIgnoreCase(choice) && size.equalsIgnoreCase("M"))
						{
							price=price+rs.getInt(4);
							break;
						}
					}
				}
				query="select * from extra_cost";
				if(st!=null)
					rs=st.executeQuery(query);
				System.out.print("DO YOU WANT TO ADD EXTRA TOPPINGS TO YOUR PIZZA?(Y/N):");
				topy=sc.nextLine();
				if(topy.equalsIgnoreCase("Y"))
				{
					while(rs.next())
					{
						if(rs.getString(1).equalsIgnoreCase("Extra Topping"))
							price=price+rs.getInt(2);
					}
				}
				if(st!=null)
					rs=st.executeQuery(query);
				System.out.print("PRESS YES IF YOU WANT EXTRA CHEESE:");
				cheesey=sc.nextLine();
				if(cheesey.equalsIgnoreCase("yes"))
				{
					while(rs.next())
					{
						if(rs.getString(1).equalsIgnoreCase("Extra Cheese"))
							price=price+rs.getInt(2);
					}
				}
				if(st!=null)
					rs=st.executeQuery(query);
				System.out.print("WANT A TAKEAWAY?(YES/NO):");
				takey=sc.nextLine();
				if(takey.equalsIgnoreCase("YES"))
				{
					while(rs.next())
					{
						if(rs.getString(1).equalsIgnoreCase("Takeaway"))
							price=price+rs.getInt(2);
					}
				}
			}
			System.out.println("=============================================================");
			System.out.println("Your total pizza bill is:"+price);
			System.out.println("THANKS FOR VISITING OUR STORE...SEE YOU SOON");
			System.out.println("=============================================================");
		}
		catch(SQLException sqe)
		{
			sqe.printStackTrace();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
}
