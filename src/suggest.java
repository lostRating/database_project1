import java.util.*;
import java.io.*;
import java.sql.*;

public class suggest
{
	public static void work(Scanner in, Statement stmt, String isbn)
	{
		System.out.println("----------begin----------");
		System.out.println("Here is suggestion list");
		ResultSet r = null;
		try
		{
			r = stmt.executeQuery(String.format("select distinct o.isbn AS A, sum(o.numofcopy) AS B from Orders o where o.isbn <> '%s' and o.loginname in (select oo.loginname from Orders oo where oo.isbn = '%s') and o.loginname <> '%s' group by o.isbn order by sum(o.numofcopy) desc", isbn, isbn, Main.nowUser));
			boolean have = false;
			while (r.next())
			{
				have = true;
				System.out.println(String.format("ISBN: %s, SaleNumber: %s", r.getString("A"), r.getString("B")));
			}
			if (!have)
				System.out.println("None");
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
		System.out.println("-----------end-----------");
	}
	
	public static void main(String[] arg)
	{
	}
}