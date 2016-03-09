import java.util.*;
import java.util.Date;
import java.io.*;
import java.sql.*;

public class query_10
{
	public static void work(Scanner in, Statement stmt)
	{
		while (true)
		{
			System.out.println("--------------------------");
			System.out.println("Here is Statistics");
			System.out.println("Back to previous menu? Y/N");
			if (in.nextLine().equals("Y")) return;
			System.out.println("Here are some options");
			System.out.println("1: show most popular books");
			System.out.println("2: show most popular authors");
			System.out.println("3: show most popular publisher");
			String number = in.nextLine();
			ResultSet r = null;
			try
			{
				int num = Integer.parseInt(number);
				if (!(1 <= num && num <= 3)) throw new Exception();
				System.out.println("the number of elements you want to show: ");
				number = in.nextLine();
				int num2 = Integer.parseInt(number);
				if (!(num2 >= 0)) throw new Exception();
				Date a = new Date();
				int time = (int)(a.getTime() / (1000 * 60));
				time -= 30 * 3 * 24 * 60;
				if (num == 1)
				{
					r = stmt.executeQuery(String.format("select b.isbn AS A, sum(o.numofcopy) AS B from Book b, Orders o where o.ordertime >= '%d' and b.isbn = o.isbn group by b.isbn order by sum(o.numofcopy) desc", time));
					boolean have = false;
					System.out.println("----------begin----------");
					while (r.next() && num2 > 0)
					{
						num2--;
						have = true;
						System.out.println(String.format("ISBN: %s, SOLDNUMBER: %s", r.getString("A"), r.getString("B")));
					}
					if (!have)
						System.out.println("None");
					System.out.println("-----------end-----------");
				}
				else if (num == 2)
				{
					r = stmt.executeQuery(String.format("select a.name AS A, sum(o.numofcopy) AS B from Author a, Orders o where o.ordertime >= '%d' and exists(select * from Writes w where w.author = a.name and w.isbn = o.isbn) group by a.name order by sum(o.numofcopy) desc", time));
					boolean have = false;
					System.out.println("----------begin----------");
					while (r.next() && num2 > 0)
					{
						num2--;
						have = true;
						System.out.println(String.format("AUTHOR: %s, SOLDNUMBER: %s", r.getString("A"), r.getString("B")));
					}
					if (!have)
						System.out.println("None");
					System.out.println("-----------end-----------");
				}
				else if (num == 3)
				{
					r = stmt.executeQuery(String.format("select b.publisher AS A, sum(o.numofcopy) AS B from Book b, Orders o where o.ordertime >= '%d' and b.isbn = o.isbn group by b.publisher order by sum(o.numofcopy) desc", time));
					boolean have = false;
					System.out.println("----------begin----------");
					while (r.next() && num2 > 0)
					{
						num2--;
						have = true;
						System.out.println(String.format("PUBLISHER: %s, SOLDNUMBER: %s", r.getString("A"), r.getString("B")));
					}
					if (!have)
						System.out.println("None");
					System.out.println("-----------end-----------");
				}
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
				catch (Exception e){}
			}
		}
		System.out.println("Statistics success");
		System.out.println("----------Press Enter to continue----------");
		String tmp = in.nextLine();
	}
	
	public static void main(String[] arg)
	{
	}
}