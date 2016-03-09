import java.util.*;
import java.io.*;
import java.sql.*;

public class query_3
{
	public static void work(Scanner in, Statement stmt)
	{
		while (true)
		{
			System.out.println("--------------------------");
			System.out.println("Here is Arrival of more copies");
			System.out.println("Back to previous menu? Y/N");
			if (in.nextLine().equals("Y")) return;
			System.out.print("Type ISBN: "); String isbn = in.nextLine();
			System.out.print("Type increasing number: "); String number = in.nextLine();
			try
			{
				if (!stmt.executeQuery(String.format("select * from Book where isbn = '%s'", isbn)).next()) throw new Exception();
				int num = Integer.parseInt(number);
				if (!(num >= 0)) throw new Exception();
				stmt.executeUpdate(String.format("update Book set numofcopy = numofcopy + %d where isbn = '%s'", num, isbn));
				break;
			}
			catch (Exception e)
			{
				System.out.println("Invalid input");
			}
		}
		System.out.println("Change success");
		System.out.println("----------Press Enter to continue----------");
		String tmp = in.nextLine();
	}
	
	public static void main(String[] arg)
	{
	}
}