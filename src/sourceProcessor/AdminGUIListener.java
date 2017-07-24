package sourceProcessor;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedHashMap;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

import store.SerializationMethods;
import adminInterface.LoadTab;

import Indexer.Indexer;

/*********************
 * This class contains the code necessary for action listeners
 * for the GUI interface. 
 *********************/
public class AdminGUIListener implements ActionListener
{
  private static GutenbergBook book;
  private static LoadTab loadTab;
  private static int number = 0;
  private Indexer index;
  private LinkedHashMap<Integer, GutenbergBook> books = new LinkedHashMap<Integer, GutenbergBook>();
  

  /**********************
   * Constructor that takes in a LoadTab object.
   * 
   * @param loadTab LoadTab
   **********************/
  public AdminGUIListener(LoadTab loadTab)
  {
	  AdminGUIListener.loadTab = loadTab;
  }
  

  @Override
  public void actionPerformed(ActionEvent e)
  {

    //LinkedHashMap<String, GutenbergBook> map = this.deserialize();
	  books = SerializationMethods.deserializeBooks();
	  
    // TODO Auto-generated method stub
    if (e.getActionCommand().equals(loadTab.getBROWSE()))
    {
      int browseVal = loadTab.getFC().showOpenDialog(loadTab.getFrame());
      if (browseVal == JFileChooser.APPROVE_OPTION)
      {
        if (books != null)
        {
          number = books.size();
        }
        book = new GutenbergBook(loadTab.getFC().getSelectedFile(), number);

        //JOptionPane.showMessageDialog(loadTab.getFrame(), "Loaded the document");
        loadTab.getTextFileFieldL().setText((loadTab.getFC().getSelectedFile().getAbsolutePath()));
         
        loadTab.getTextTitleFieldL().setText((book.getTitle()));

        loadTab.getTextAuthorFieldL().setText((book.getAuthor()));
        loadTab.getProcessButtonL().setEnabled(true);
         
      }
    } 
    
    else if(e.getActionCommand().equals(loadTab.getPROCESS()))
    {
    	book.setAuthor(loadTab.getTextAuthorFieldL().getText()); 
      book.setTitle(loadTab.getTextTitleFieldL().getText());
      if (books == null)
      {
        books = new LinkedHashMap<Integer, GutenbergBook>();
      }
      
      //if(books.containsKey(book.getTitle()))
      if(books.containsKey(book.getOpusNumber()))
      {
        JOptionPane.showMessageDialog(loadTab.getFrame(), 
      		  "This opus has been processed already.", 
      		  "Process Error", JOptionPane.WARNING_MESSAGE);
        loadTab.getProcessTextAreaL().setText("");
      }
      else
      {
        books.put(book.getOpusNumber(), book);
        SerializationMethods.serializeBooks(books);
        if(index == null)
        {
      	  index = new Indexer();
        }
        index.addTerms(book);
        SerializationMethods.serializeIndexer(index);
        System.out.print(index.toString());
       
        //System.out.printf("Serialized data is saved"); 
        
        loadTab.getTextFileFieldL().setText("");
        loadTab.getTextTitleFieldL().setText("");
        loadTab.getTextAuthorFieldL().setText("");
        loadTab.getProcessTextAreaL().setText("Opus: " + book.getFile().getAbsolutePath()+"\n"
      					+"Title: " + book.getTitle()+"\n"
      					+"Author: " + book.getAuthor() +"\n"
      					+ "Opus Size: " + book.getDocumentNumber() + " documents\n"
      					+"Opus number: " + book.getOpusNumber() + "\n"
      					+"New index terms: " + index.getSize() + "\n"
      					+"New postings: "+ index.getNewPosting() + "\n"
      					+"Total index terms: " + index.getTotalIndexCount() + "\n"
      					+"Total postings: " + index.getPostings() + "\n");
        loadTab.getProcessButtonL().setEnabled(false);
        number++;
      }

    }
  }
  



}
