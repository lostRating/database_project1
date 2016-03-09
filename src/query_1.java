import java.util.*;
import java.util.Date;
import java.io.*;
import java.sql.*;

public class query_1
{
	static int ordernum = 0;
	
	public static void work(Scanner in, Statement stmt)
	{
		while (true)
		{
			System.out.println("--------------------------");
			System.out.println("Here is ordering");
			System.out.println("Back to previous menu? Y/N");
			if (in.nextLine().equals("Y")) return;
			System.out.print("Type ISBN of book: "); String isbn = in.nextLine();
			try
			{
				ResultSet r = null;
				int rest = -1;
				try
				{
					r = stmt.executeQuery(String.format("select MAX(s.orderid) AS orderid from Orders s"));
					r.next();
					ordernum = r.getInt("orderid");
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
				r = null;
				try
				{
					r = stmt.executeQuery(String.format("select numofcopy from Book where isbn = '%s'", isbn));
					r.next();
					rest = r.getInt("numofcopy");
				}
				catch (Exception e)
				{
					throw new Exception();
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
				if (rest == -1) throw new Exception();
				System.out.println(rest + " copies you can order");
				if (rest > 0)
				{
					System.out.print("Type the number of books your want to order (1 ~ " + rest + "): "); String number = in.nextLine();
					int num = Integer.parseInt(number);
					if (!(1 <= num && num <= rest)) throw new Exception();
					Date date = new Date();
					int t = (int)(date.getTime() / (1000 * 60));
					stmt.executeUpdate(String.format("insert into Orders(loginname, isbn, numofcopy, orderid, ordertime) values ('%s', '%s', '%s', '%d', '%d')", Main.nowUser, isbn, number, ++ordernum, t));
					stmt.executeUpdate(String.format("update Book set numofcopy = numofcopy - %d where isbn = '%s'", num, isbn));
				}
				else
					continue;
				suggest.work(in, stmt, isbn);
				break;
			}
			catch (Exception e)
			{
				System.out.println("Invalid input");
			}
		}
		System.out.println("Order success");
		System.out.println("----------Press Enter to continue----------");
		String tmp = in.nextLine();
	}
	
	public static void main(String[] arg)
	{
	}
}