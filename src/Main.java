import java.util.*;
import java.io.*;
import java.sql.*;

public class Main
{
	public static Scanner in;
	public static Connector con = null;
	public static String nowUser = null;
	
	public static void work2()
	{
		while (true)
		{
			System.out.println("-----------------------");
			System.out.println("Welcome " + nowUser);
			System.out.println("Here are some options");
			System.out.println("0: Logout");
			System.out.println("1: Ordering");
			System.out.println("2: New book (root only)");
			System.out.println("3: Arrival of more copies (root only)");
			System.out.println("4: Feedback recordings");
			System.out.println("5: Usefulness ratings");
			System.out.println("6: Trust recordings");
			System.out.println("7: Book Browsing");
			System.out.println("8: Useful feedbacks");
			System.out.println("9: ¡®Two degrees of separation¡¯");
			System.out.println("10: Statistics (root only)");
			System.out.println("11: User awards (root only)");
			try
			{
				int num = Integer.parseInt(in.nextLine());
				if (!nowUser.equals("root") && (num == 2 || num == 3 || num == 11 || num == 12))
				{
					System.out.println("permission denied");
					continue;
				}
				if (num == 0)
				{
					nowUser = null;
					return;
				}
				if (num == 1) query_1.work(in, con.stmt);
				else if (num == 2) query_2.work(in, con.stmt);
				else if (num == 3) query_3.work(in, con.stmt);
				else if (num == 4) query_4.work(in, con.stmt);
				else if (num == 5) query_5.work(in, con.stmt);
				else if (num == 6) query_6.work(in, con.stmt);
				else if (num == 7) query_7.work(in, con.stmt);
				else if (num == 8) query_8.work(in, con.stmt);
				else if (num == 9) query_9.work(in, con.stmt);
				else if (num == 10) query_10.work(in, con.stmt);
				else if (num == 11) query_11.work(in, con.stmt);
				else
				{
					System.out.println("Invalid input");
				}
			}
			catch (Exception e)
			{
				System.out.println("Invalid input");
			}
		}
	}
	
	public static void work()
	{
		try
		{
			con.stmt.executeUpdate(String.format("insert into Customer(fullname, loginname, password, address, phonenumber) values ('%s', '%s', '%s', '%s', '%s')", "", "root", "root", "", "")); 
		}
		catch (Exception e) {}
		
		while (true)
		{
			System.out.println("----------------------------");
			System.out.println("Welcome to Online book Store");
			System.out.println("Here are some options");
			System.out.println("1: Login");
			System.out.println("2: register");
			System.out.println("3: Quit");
			System.out.print("Please type your choice: ");
			try
			{
				int num = Integer.parseInt(in.nextLine());
				if (num == 1)
				{
					nowUser = login.work(in, con.stmt);
					if (nowUser != null)
						work2();
				}
				else if (num == 2)
				{
					register.work(in, con.stmt);
				}
				else if (num == 3)
				{
					System.out.println("Bye bye!");
					break;
				}
				else
				{
					System.out.println("Invalid input");
				}
			}
			catch (Exception e)
			{
				System.out.println("Invalid input");
			}
		}
	}
	
	public static void main(String[] arg)
	{
		in = new Scanner(System.in);
		try
		{
			con = new Connector();
			System.out.println("Database connection established");
			work();
		}
		catch (Exception e)
		{
			e.printStackTrace();
			System.out.println("Cannot connect to database server");
		}
		finally
        {
			if (con != null)
       	 	{
				try
				{
					con.closeConnection();
					System.out.println("Database connection terminated");
       		 	}
       		 	catch (Exception e) {}
       	 	}	 
        }
	}
}