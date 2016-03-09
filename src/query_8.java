import java.util.*;
import java.io.*;
import java.sql.*;

public class query_8
{
	public static void work(Scanner in, Statement stmt)
	{
		while (true)
		{
			System.out.println("--------------------------");
			System.out.println("Here is Useful feedbacks");
			System.out.println("Back to previous menu? Y/N");
			if (in.nextLine().equals("Y")) return;
			System.out.print("Type ISBN: "); String isbn = in.nextLine();
			System.out.print("Type the number of most useful feedbacks you want to show: "); String number = in.nextLine();
			try
			{
				int num = Integer.parseInt(number);
				if (!(0 <= num)) throw new Exception();
				ResultSet r = null;
				try
				{
					r = stmt.executeQuery(String.format("select f.loginname AS A, f.text AS B, avg(r.score) AS C from RateFB r, Feedback f where r.feedbackid = f.feedbackid and f.isbn = isbn group by f.isbn order by avg(r.score) desc"));
					System.out.println("----------begin----------");
					boolean have = false;
					while (num > 0 && r.next())
					{
						have = true;
						--num;
						System.out.println(String.format("USERNAME: %s, AVGSCORE: %s, COMMENTS: %s", r.getString("A"), r.getString("C"), r.getString("B")));
					}
					if (!have)
						System.out.println("None");
					System.out.println("-----------end-----------");
					break;
				}
				catch (Exception e){
					System.out.println(e.getMessage());
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
			catch (Exception e)
			{
				System.out.println("Invalid input");
			}
		}
		System.out.println("Useful feedbacks success");
		System.out.println("----------Press Enter to continue----------");
		String tmp = in.nextLine();
	}
	
	public static void main(String[] arg)
	{
	}
}