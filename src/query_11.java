import java.util.*;
import java.io.*;
import java.sql.*;

public class query_11
{
	public static void work(Scanner in, Statement stmt)
	{
		while (true)
		{
			System.out.println("--------------------------");
			System.out.println("Here is User awards");
			System.out.println("Back to previous menu? Y/N");
			if (in.nextLine().equals("Y")) return;
			System.out.println("1: show most trusted users");
			System.out.println("2: show most useful users");
			String number = in.nextLine();
			ResultSet r = null;
			try
			{
				int num = Integer.parseInt(number);
				if (!(1 <= num && num <= 2)) throw new Exception();
				System.out.println("the number of elements your want to show: ");
				number = in.nextLine();
				int num2 = Integer.parseInt(number);
				if (!(num2 >= 0)) throw new Exception();
				if (num == 1)
				{
					r = stmt.executeQuery(String.format("select d.loginname2 as A, sum(d.trust) as B from Declares d group by d.loginname2 order by sum(d.trust) desc"));
					boolean have = false;
					System.out.println("----------begin----------");
					while (r.next() && num2 > 0)
					{
						num2--;
						have = true;
						System.out.println(String.format("USERNAME: %s, SCORE: %s", r.getString("A"), r.getString("B")));
					}
					if (!have)
						System.out.println("None");
					System.out.println("-----------end-----------");
					break;
				}
				else
				{
					r = stmt.executeQuery(String.format("select f.loginname as A, avg(r.score) as B from RateFB r, Feedback f where r.feedbackid = f.feedbackid group by f.loginname order by avg(r.score) desc"));
					boolean have = false;
					System.out.println("----------begin----------");
					while (r.next() && num2 > 0)
					{
						num2--;
						have = true;
						System.out.println(String.format("USERNAME: %s, SCORE: %s", r.getString("A"), r.getString("B")));
					}
					if (!have)
						System.out.println("None");
					System.out.println("-----------end-----------");
					break;
				}
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
		System.out.println("User awards success");
		System.out.println("----------Press Enter to continue----------");
		String tmp = in.nextLine();
	}
	
	public static void main(String[] arg)
	{
	}
}