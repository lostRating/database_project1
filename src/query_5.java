import java.util.*;
import java.io.*;
import java.sql.*;

public class query_5
{
	public static void work(Scanner in, Statement stmt)
	{
		while (true)
		{
			System.out.println("--------------------------");
			System.out.println("Here is Usefulness ratings");
			System.out.println("Back to previous menu? Y/N");
			if (in.nextLine().equals("Y")) return;
			System.out.print("Type username: "); String loginname = in.nextLine();
			System.out.print("Type ISBN: "); String isbn = in.nextLine();
			System.out.print("Type score(0 ~ 2): "); String score = in.nextLine();
			ResultSet r = null;
			try
			{
				if (Main.nowUser.equals(loginname)) throw new Exception();
				r = stmt.executeQuery(String.format("select * from Feedback where isbn = '%s' and loginname = '%s'", isbn, loginname));
				r.next();
				int feedbackid = r.getInt("feedbackid");
				int num = Integer.parseInt(score);
				if (!(0 <= num && num <= 2)) throw new Exception();
				stmt.executeUpdate(String.format("insert into RateFB (loginname, feedbackid, score) values ('%s', '%s', %d)", loginname, feedbackid, num));
				break;
			}
			catch (Exception e)
			{
				System.out.println("Invalid input");
			}
			finally
			{
				try
				{
					if (r != null)
						r.close();
				}
				catch (Exception e) {}
			}
		}
		System.out.println("Usefulness ratings success");
		System.out.println("----------Press Enter to continue----------");
		String tmp = in.nextLine();
	}
	
	public static void main(String[] arg)
	{
	}
}