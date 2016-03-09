import java.util.*;
import java.io.*;
import java.sql.*;

public class query_2
{
	public static void work(Scanner in, Statement stmt)
	{
		while (true)
		{
			System.out.println("--------------------------");
			System.out.println("Here is New Book");
			System.out.println("Back to previous menu? Y/N");
			if (in.nextLine().equals("Y")) return;
			System.out.print("Type ISBN: "); String isbn = in.nextLine();
			System.out.print("Type title: "); String title = in.nextLine();
			System.out.print("Type authors (seperated by ',' and no extra ' '): "); String authors = in.nextLine();
			System.out.print("Type publisher: "); String publisher = in.nextLine();
			System.out.print("Type the published year: "); String yearofpb = in.nextLine();
			System.out.print("Type num of copies: "); String numofcopy = in.nextLine();
			System.out.print("Type price: "); String price = in.nextLine();
			System.out.print("Type format: "); String format = in.nextLine();
			System.out.print("Type keyword: "); String keyword = in.nextLine();
			System.out.print("Type subject: "); String subject = in.nextLine();
			try
			{
				int num = Integer.parseInt(numofcopy);
				if (!(num >= 0)) throw new Exception();
				float pri = Float.parseFloat(price);
				if (!(pri > 0)) throw new Exception();
				stmt.executeUpdate(String.format("insert into Book(isbn, title, publisher, yearofpb, numofcopy, price, format, keyword, subject) values ('%s', '%s', '%s', '%s', '%s', '%s', '%s', '%s', '%s')", isbn, title, publisher, yearofpb, numofcopy, price, format, keyword, subject));  
				String[] t = authors.split(",");
				for (String i : t)
				{
					try
					{
						stmt.executeUpdate(String.format("insert into Author(name) values ('%s')", i));  
					}
					catch (Exception e){}
					stmt.executeUpdate(String.format("insert into Writes(author, isbn) values ('%s', '%s')", i, isbn));  
				}
				break;
			}
			catch (Exception e)
			{
				System.out.println("Invalid input");
			}
		}
		System.out.println("New Book added success");
		System.out.println("----------Press Enter to continue----------");
		String tmp = in.nextLine();
	}
	
	public static void main(String[] arg)
	{
	}
}