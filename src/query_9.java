import java.util.*;
import java.io.*;
import java.sql.*;

public class query_9
{
	public static void work(Scanner in, Statement stmt)
	{
		while (true)
		{
			System.out.println("--------------------------");
			System.out.println("Here is 'Two degrees of separation'");
			System.out.println("Back to previous menu? Y/N");
			if (in.nextLine().equals("Y")) return;
			System.out.print("Type name of author1: "); String name1 = in.nextLine();
			System.out.print("Type name of author2: "); String name2 = in.nextLine();
			ResultSet r = null;
			try
			{
				if (name1.equals(name2)) throw new Exception();
				r = null;
				r = stmt.executeQuery(String.format("select * from Book b where exists(select * from Writes w where w.isbn = b.isbn and w.author = '%s') and exists(select * from Writes w where w.isbn = b.isbn and w.author = '%s')", name1, name2));
				if (r.next())
				{
					System.out.println("1-degree");
					break;
				}
				r.close();
				r = null;
				String sql = "";
				sql += "select * from Author a where ";
				sql += "exists(select * from Book b where exists(select * from Writes w where w.isbn = b.isbn and w.author = '" + name1 + "') and exists(select * from Writes w where w.isbn = b.isbn and w.author = a.name)) and ";
				sql += "exists(select * from Book b where exists(select * from Writes w where w.isbn = b.isbn and w.author = '" + name2 + "') and exists(select * from Writes w where w.isbn = b.isbn and w.author = a.name))";
				r = stmt.executeQuery(sql);
				if (r.next())
				{
					System.out.println("2-degree");
					break;
				}
				r.close();
				r = null;
				System.out.println("neither 1-degree nor 2-degree");
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
		System.out.println("'Two degrees of separation' success");
		System.out.println("----------Press Enter to continue----------");
		String tmp = in.nextLine();
	}
	
	public static void main(String[] arg)
	{
	}
}