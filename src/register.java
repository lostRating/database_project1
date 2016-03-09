import java.util.*;
import java.io.*;
import java.sql.*;

public class register
{
	public static void work(Scanner in, Statement stmt)
	{
		while (true)
		{
			System.out.println("--------------------------");
			System.out.println("Here is register");
			System.out.println("Back to previous menu? Y/N");
			if (in.nextLine().equals("Y")) return;
			System.out.print("Type your username: "); String loginname = in.nextLine();
			System.out.print("Type your password: "); String password = in.nextLine();
			System.out.print("Type your fullname: "); String fullname = in.nextLine();
			System.out.print("Type your address: "); String address = in.nextLine();
			System.out.print("Type your phonenumber: "); String phonenumbmer = in.nextLine();
			try
			{
				stmt.executeUpdate(String.format("insert into Customer(fullname, loginname, password, address, phonenumber) values ('%s', '%s', '%s', '%s', '%s')", fullname, loginname, password, address, phonenumbmer));  
				break;
			}
			catch (Exception e)
			{
				System.out.println("Username has existed or invalid input");
			}
		}
		System.out.println("Registion completed");
		System.out.println("----------Press Enter to continue----------");
		String tmp = in.nextLine();
	}
	
	public static void main(String[] arg)
	{
	}
}