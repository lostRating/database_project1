import java.util.*;
import java.io.*;
import java.sql.*;

public class query_4
{
	static int feedbacknum = 0;
	
	public static void work(Scanner in, Statement stmt)
	{
		while (true)
		{
			System.out.println("--------------------------");
			System.out.println("Here is Feedback recording");
			System.out.println("Back to previous menu? Y/N");
			if (in.nextLine().equals("Y")) return;
			System.out.print("Type ISBN: "); String isbn = in.nextLine();
			System.out.print("Type score(0 ~ 10): "); String number = in.nextLine();
			System.out.print("Type some comments: "); String text = in.nextLine();
			try
			{
				int num = Integer.parseInt(number);
				if (!(0 <= num && num <= 10)) throw new Exception();
				if (stmt.executeQuery(String.format("select * from Feedback where isbn = '%s' and loginname = '%s'", isbn, Main.nowUser)).next())
				{
					System.out.println("No two feedbacks for same book");
					continue;
				}
				ResultSet r = null;
				try
				{
					r = stmt.executeQuery(String.format("select MAX(s.feedbackid) AS feedbackid from Feedback s"));
					r.next();
					feedbacknum = r.getInt("feedbackid");
				}
				catch (Exception e){}
				finally
				{
					try
					{
						if (r != null)
							r.close();
					}
					catch (Exception e) {}
				}
				stmt.executeUpdate(String.format("insert into Feedback (loginname, isbn, score, text, feedbackid) values ('%s', '%s', %d, '%s', %d)", Main.nowUser, isbn, num, text, ++feedbacknum));
				break;
			}
			catch (Exception e)
			{
				System.out.println("Invalid input");
			}
		}
		System.out.println("Feedback recording success");
		System.out.println("----------Press Enter to continue----------");
		String tmp = in.nextLine();
	}
	
	public static void main(String[] arg)
	{
	}
}