package sourceProcessor;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.Serializable;
import java.util.LinkedList;

/*********************
 * 
 *********************/
public class GutenbergBook implements Serializable
{
	private File book;
	private String author;
	private String title;
	private String text;
	private int opusNumber;
	private static final int AUTHOR_START = 8;
	private static final int TITLE_START = 7;
	private LinkedList<StringBuilder> opus;
	private int paragraphNumber;
	
	/*********************
	 * This is the constructor. It is received the gutenberg text file as well as the 
	 * opus number. It then calls readGutenbergBook() to set the author, title, and the text.
	 * @param file File
	 * @param number int
	 *********************/
	public GutenbergBook(File file, int number)
	{
		book = file;
		author = "";
		title = "";
		text = "";
		opusNumber = number;
    opus = new LinkedList<StringBuilder>();
		readGutenbergBook();
	}
	
	/*********************
	 * A method to return the author of the Gutenberg Book.
	 * @return author String
	 *********************/
	public String getAuthor()
	{
		return author;
	}
	
	/*********************
	 * A method to return the opus number.
	 * @return opusNumber the integer of the document number
	 *********************/
	public int getOpusNumber()
	{
		return opusNumber;
	}
	
	/*********************
	 * A method to return the short form of a document.
	 * @param documentNumber int
	 * @return title String
	 *********************/
	public String getShortForm(int documentNumber)
	{
		String[] paragraphs = opus.get(documentNumber).toString().split(" ");
		String line = "";
		boolean cont = true;
		int count = 0;
		while(cont)
		{
			line += paragraphs[count] + " ";
			cont = ((paragraphs.length-1)>count) && (line.length()<50);
			count++;
		}
		return line;
	}
	
	/*********************
	 * A method to return the title of the Gutenberg Book.
	 * @return title String
	 *********************/
	public String getTitle()
	{
		return title;
	}
	
	/*********************
	 * A method to return the text of the Gutenberg Book.
	 * @return text String
	 *********************/
	public String getText()
	{
		return text;
	}
	
	/*********************
	 * A method to return the actual file of the Gutenberg book.
	 * @return book File
	 *********************/
	public File getFile()
	{
		return book;
	}
	/*********************
	 * A method to return the Linked List of StringBuilders which hold a paragraph each.
	 * @return opus LinkedList<StringBuilder> 
	 *********************/
	public LinkedList<StringBuilder> getOpus()
	{
	  return opus;
	}
	/*********************
   * A method to return the number of paragraphs in the book.
   * @return paragraphNumber int 
   *********************/
	public int getDocumentNumber()
	{
	  return paragraphNumber;
	}
	
	/*********************
	 * This method is taking the author and title from the file as
	 * well as seperating out the text of the book.
	 *********************/
	public void readGutenbergBook()
	{
		
		String line = null;

		try
		{
			FileReader fileReader = new FileReader(book.getAbsolutePath());
			BufferedReader bufferedReader = new BufferedReader(fileReader);
			StringBuilder paragraph = new StringBuilder();
			while(((line = bufferedReader.readLine()) != null)) 
			{
				if(line.contains("Author: "))
				{
					if(line.substring(AUTHOR_START).equals(""))
					{
						author = "Anonymous";
					}
					else
					{
						author = line.substring(AUTHOR_START);	
					}
				}
				if(line.contains("Title: "))
				{
					if(line.substring(TITLE_START).equals(""))
					{
						title = "None";
					}
					else
					{
						title = line.substring(TITLE_START);
					}
				}
				if(line.contains("*** START OF") || line.contains("***START OF"))
				{
					while(((line = bufferedReader.readLine()) != null) && 
							!(line.contains("*** END OF ") || line.contains("***END OF ")))
					{
					  if (!line.equals(""))
					  {
						  paragraph.append(line + "\n");
					  } 
					  else 
					  {
						  if (!paragraph.toString().equals(""))
						  {
						    this.opus.add(paragraph);
						    paragraphNumber++;
						  }
						  paragraph = new StringBuilder();
					  }
					}
				}    
			}
			
			bufferedReader.close();	
		}
		catch(IOException ex)
		{
			ex.printStackTrace();
		}
	
		
	}
	
	/*********************
	 * A method to edit the Title of the Gutenberg Book.
	 * @param name String
	 *********************/
	public void setTitle(String name)
	{
		title = name;
	}
	
	/*******************
	 * A method to edit the Author of the Gutenberg Book.
	 * @param person String
	 *********************/
	public void setAuthor(String person)
	{
		author = person;
	}
}
