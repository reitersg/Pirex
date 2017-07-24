package searchEngine;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import Indexer.Indexer;
import sourceProcessor.GutenbergBook;
import store.SerializationMethods;
import userInterface.SearchTab;

/*********************
 * This class contains all the listeners for the search tab
 * part of the GUI. 
 *********************/
public class SearchTabGUIListener implements ActionListener, KeyListener, ListSelectionListener
{
  private static SearchTab searchTab;
  private HashMap<Integer, ArrayList<Integer>> searchTermLocations;
  private static SearchEngine searchEngine;
  private Indexer index;
  private static Map<String, StringBuilder> rows = new LinkedHashMap<String, StringBuilder>();
  private DefaultListModel<String> model;

  /**********************
   * Constructor that takes in a SearchTab object.
   * 
   * @param searchTab SearchTab
   **********************/
  public SearchTabGUIListener(SearchTab searchTab)
  {
    SearchTabGUIListener.searchTab = searchTab;
  }
  

  @Override
  public void keyPressed(KeyEvent e)
  {
    if (e.getKeyCode() == KeyEvent.VK_ENTER) 
    {
      rows.clear();
      int num = 0;
      GutenbergBook foundBook;
      LinkedHashMap<Integer, GutenbergBook> map = SerializationMethods.deserializeBooks();
      if(Files.exists(new File(SerializationMethods.INDEXER_SER).toPath()))
      {
    	  index = SerializationMethods.deserializeIndexer();
      }
      else
      {
        index = new Indexer();
      }
      searchEngine = new SearchEngine(index);
      searchEngine.addQueryTerm(searchTab.getTextFieldQ().getText());
      if(!searchEngine.searchTermFound())
      {
    	  model = (DefaultListModel<String>) searchTab.docFound.getModel();
    	  model.removeAllElements();
    	  model.addElement("None");
    	  searchTab.displayCount.setText("Retrieved 0 documents");
      }
      else
      {
    	  model = (DefaultListModel<String>) searchTab.docFound.getModel();
    	  model.removeAllElements();
    	  for(String str : SearchEngine.getSearch()) 
    	  {
    		  if(searchEngine.searchTermFound(str))
    		  {
    			  searchTermLocations = searchEngine.getHashMap(str);
    			  if(searchTermLocations != null)
    			  {
    				  for(Integer key : searchTermLocations.keySet())
    				  {
    					  //System.out.print("Books.get("+key+"): "+ map.get(key).getAuthor());
    					  foundBook = map.get(key);
    					  ArrayList<Integer> docNum = searchTermLocations.get(key);
    					  for(int i = 0; i<docNum.size(); i++)
    					  {
    						  StringBuilder docInfo = new StringBuilder();
    						  docInfo.append(" " +foundBook.getAuthor()+"  "
    								  +foundBook.getTitle()+"    "
    								  +docNum.get(i) + "  "
    								  +foundBook.getShortForm(docNum.get(i)));
    						  this.rows.put(docInfo.toString(), foundBook.getOpus().get(docNum.get(i)));
    						  num++;
    					  }
    				  }
    				  for (String key: rows.keySet()) 
    				  {
    					  model.addElement(key);
    				  }
    				  //rows.clear();
    			  }
    			  searchTab.docFound.setModel(model);
    			  searchTab.displayCount.setText("Retrieved " + num + " documents");
    		  }
    		  searchTab.getLongFormOfDocument().setText("");
    	  }
      }
    }
  }
  
  @Override
  public void keyReleased(KeyEvent e)
  {
    // TODO Auto-generated method stub
    
  }
  
  @Override
  public void keyTyped(KeyEvent e)
  {
    // TODO Auto-generated method stub
    
  }


  @Override
  public void actionPerformed(ActionEvent e)
  {
    // TODO Auto-generated method stub
    if (e.getActionCommand().equals(searchTab.getClear()))
    {
      searchTab.getTextFieldQ().setText("");
      rows.clear();
      searchTab.docFound.clearSelection();
      DefaultListModel model = (DefaultListModel) searchTab.docFound.getModel();
      model.removeAllElements();
      searchTab.getLongFormOfDocument().setText("");
    }
  }


  @Override
  public void valueChanged(ListSelectionEvent ev)
  {
    // TODO Auto-generated method stub
    JList<String> list =  ((JList<String>) ev.getSource());
    if (list.getSelectedValue() != null) 
    {
    	String sb = list.getSelectedValue();
    	StringBuilder get = rows.get(sb);
    	searchTab.getLongFormOfDocument().setText(get.toString());
    }
  }
}
