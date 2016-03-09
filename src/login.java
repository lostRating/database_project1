import java.util.*;
import java.io.*;
import java.sql.*;

public class login
{
	public static String work(Scanner in, Statement stmt)
	{
		while (true)
		{
			System.out.println("--------------------------");
			System.out.println("Here is login");
			System.out.println("Back to previous menu? Y/N");
			if (in.nextLine().equals("Y")) return null;
			System.out.print("Type your username: "); String loginname = in.nextLine();
			System.out.print("Type your password: "); String password = in.nextLine();
			try
			{
				if (stmt.executeQuery(String.format("select * from Customer where loginname = '%s' and password = '%s';", loginname, password)).next())
				{
					System.out.println("Login Success");
					System.out.println("----------Press Enter to continue----------");
					String tmp = in.nextLine();
                    return loginname;
				}
				else
				{
					System.out.println("Username or password wrong");
				}
			}
			catch (Exception e)
			{
				System.out.println("Username or password wrong");
			}
		}
	}
	
	public static void main(String[] arg)
	{
	}
}