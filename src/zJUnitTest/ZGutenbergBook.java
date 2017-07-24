package zJUnitTest;

import static org.junit.Assert.*;

import java.io.File;
import org.junit.Test;

import sourceProcessor.GutenbergBook;

public class ZGutenbergBook 
{
	File file = new File("GutenbergFiles/Test1.txt");
	File file2 = new File("GutenbergFiles/Test2.txt");
	File file3 = new File("GutenbergFiles/Test3.txt");
	GutenbergBook book = new GutenbergBook(file, 0);
	GutenbergBook book2 = new GutenbergBook(file2, 1);
	GutenbergBook book3 = new GutenbergBook(file3, 2);
	
	@Test
	public void testGetAuthor()
	{
		assertEquals(book.getAuthor(), "me");
		assertEquals(book2.getAuthor(), "Author2");
		assertEquals(book3.getAuthor(), "Author the Third");
		
	}
	
	@Test
	public void testGetOpusNumber()
	{
		assertEquals(book.getOpusNumber(), 0);
		assertEquals(book2.getOpusNumber(), 1);
		assertEquals(book3.getOpusNumber(), 2);
		
	}
	
	@Test
	public void testGetShortForm()
	{
		assertEquals(book.getShortForm(0), "test.\n ");
		assertEquals(book2.getShortForm(0), "Muppets singing very loud.\n ");
		assertEquals(book3.getShortForm(0), "test.\n ");
		
	}
	
	@Test
	public void testGetTitle()
	{
		assertEquals(book.getTitle(), "Test1");
		assertEquals(book2.getTitle(), "Test2");
		assertEquals(book3.getTitle(), "Test3");	
	}
	
	@Test
	public void testGetFile()
	{
		assertEquals(book.getFile(), file);
		assertEquals(book2.getFile(), file2);
		assertEquals(book3.getFile(), file3);	
	}
	
	@Test
	public void testSetTitle()
	{
		book.setTitle("Diane is awesome");
		assertEquals(book.getTitle(), "Diane is awesome");
	}
	
	@Test
	public void testSetAuthor()
	{
		book.setAuthor("Queen Diane");
		assertEquals(book.getAuthor(), "Queen Diane");
	}
}
