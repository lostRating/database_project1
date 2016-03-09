import java.util.*;
import java.io.*;
import java.sql.*;

public class query_6
{
	public static void work(Scanner in, Statement stmt)
	{
		while (true)
		{
			System.out.println("--------------------------");
			System.out.println("Here is Trust recordings");
			System.out.println("Back to previous menu? Y/N");
			if (in.nextLine().equals("Y")) return;
			System.out.print("Type username: "); String loginname = in.nextLine();
			System.out.print("Type not-trust(0) or trust(1): "); String score = in.nextLine();
			try
			{
				if (Main.nowUser.equals(loginname)) throw new Exception();
				int num = Integer.parseInt(score);
				if (!(0 <= num && num <= 1)) throw new Exception();
				if (num == 0) num = -1;
				stmt.executeUpdate(String.format("insert into Declares (loginname1, loginname2, trust) values ('%s', '%s', %d)", Main.nowUser, loginname, num));
				break;
			}
			catch (Exception e)
			{
				System.out.println("Invalid input");
			}
		}
		System.out.println("Trust recordings success");
		System.out.println("----------Press Enter to continue----------");
		String tmp = in.nextLine();
	}
	
	public static void main(String[] arg)
	{
	}
}