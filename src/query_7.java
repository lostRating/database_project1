import java.util.*;
import java.io.*;
import java.sql.*;

public class query_7
{
	public static void work(Scanner in, Statement stmt)
	{
		while (true)
		{
			System.out.println("--------------------------");
			System.out.println("Here is Book Browsing");
			System.out.println("Back to previous menu? Y/N");
			if (in.nextLine().equals("Y")) return;
			System.out.print("Type authors (seperated by ',' and no extra ' ', if none just type enter): "); String authors = in.nextLine();
			System.out.print("Type publisher (if none just type enter): "); String publisher = in.nextLine();
			System.out.print("Type title (if none just type enter): "); String title = in.nextLine();
			System.out.print("Type subject (if none just type enter): "); String subject = in.nextLine();
			System.out.println("--------------------------");
			System.out.println("Sorted by");
			System.out.println("1: by year");
			System.out.println("2: by the average numerical score of the feedbacks");
			System.out.println("3: by the average numerical score of the trusted user feedbacks");
			String number = in.nextLine();
			ResultSet r = null;
			String sql = "select *";
			try
			{
				int num = Integer.parseInt(number);
				if (!(1 <= num && num <= 3)) throw new Exception();
				if (num >= 2)
					sql += ", avg(f.score) AS avgFeedback";
				sql += " From Book b";
				if (num >= 2)
					sql += ", Feedback f";
				sql += " ";
				boolean first = true;
				if (authors.length() > 0)
				{
					String[] t = authors.split(",");
					for (String i : t)
					{
						if (first)
						{
							sql += "where ";
							first = false;
						}
						else
							sql += "and ";
						sql += String.format("exists(select * from Writes w where b.isbn = w.isbn and w.author = '%s') ", i);
					}
				}
				if (publisher.length() > 0)
				{
					if (first)
					{
						sql += "where ";
						first = false;
					}
					else
						sql += "and ";
					sql += String.format("b.publisher = '%s' ", publisher);
				}
				if (title.length() > 0)
				{
					if (first)
					{
						sql += "where ";
						first = false;
					}
					else
						sql += "and ";
					sql += String.format("b.title = '%s' ", title);
				}
				if (subject.length() > 0)
				{
					if (first)
					{
						sql += "where ";
						first = false;
					}
					else
						sql += "and ";
					sql += String.format("b.subject = '%s' ", subject);
				}
				if (num == 1)
				{
					sql += "order by b.yearofpb";
					r = stmt.executeQuery(sql);
					boolean have = false;
					System.out.println("----------begin----------");
					while (r.next())
					{
						have = true;
						System.out.println(String.format("ISBN: %s, YEAR: %s", r.getString("isbn"), r.getString("yearofpb")));
					}
					if (!have)
						System.out.println("None");
					System.out.println("-----------end-----------");
				}
				else if (num == 2)
				{
					if (first)
					{
						sql += "where ";
						first = false;
					}
					else
						sql += "and ";
					sql += "f.isbn = b.isbn ";
					sql += "group by f.isbn ";
					sql += "order by avg(f.score)";
					r = stmt.executeQuery(sql);
					boolean have = false;
					System.out.println("----------begin----------");
					while (r.next())
					{
						have = true;
						System.out.println(String.format("ISBN: %s, AVGFEEDBACK: %s", r.getString("isbn"), r.getString("avgFeedback")));
					}
					if (!have)
						System.out.println("None");
					System.out.println("-----------end-----------");
				}
				else if (num == 3)
				{
					if (first)
					{
						sql += "where ";
						first = false;
					}
					else
						sql += "and ";
					sql += "f.isbn = b.isbn ";
					sql += String.format(" and exists(select * from Declares d where d.loginname1 = '%s' and d.loginname2 = f.loginname and d.trust = 1) ", Main.nowUser);
					sql += "group by f.isbn ";
					sql += "order by avg(f.score)";
					r = stmt.executeQuery(sql);
					boolean have = false;
					System.out.println("----------begin----------");
					while (r.next())
					{
						have = true;
						System.out.println(String.format("ISBN: %s, AVGFEEDBACK: %s", r.getString("isbn"), r.getString("avgFeedback")));
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
				catch (Exception e) {}
			}
		}
		System.out.println("Book Browsing success");
		System.out.println("----------Press Enter to continue----------");
		String tmp = in.nextLine();
	}
	
	public static void main(String[] arg)
	{
	}
}