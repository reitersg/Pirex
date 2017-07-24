package store;

import java.util.HashMap;

import javax.swing.JTabbedPane;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import Indexer.Indexer;
import adminInterface.SumTab;
import sourceProcessor.GutenbergBook;

/********************
 * This class contains the code that
 * changes the text in the Sum Tab.
 ********************/
public class SumTabChange implements ChangeListener
{
  private SumTab sumTab;

  /********************
   * Constructor for the SumTabChange class.
   * 
   * @param sumTab SumTab
   ********************/
  public SumTabChange(SumTab sumTab)
  {
    this.sumTab = sumTab;
  }

  @Override
  public void stateChanged(ChangeEvent e)
  {
    // TODO Auto-generated method stub
    sumTab.getTextAreaS().setText("");
    JTabbedPane sourceTabbedPane = (JTabbedPane) e.getSource();
    int index = sourceTabbedPane.getSelectedIndex();
    if (sourceTabbedPane.getTitleAt(index).equals("Summarize Documents"))
    {
      //HashMap<String, GutenbergBook> map = GUIListener.deserialize();
    	HashMap<Integer, GutenbergBook> map = SerializationMethods.deserializeBooks();
    	Indexer in = SerializationMethods.deserializeIndexer();
      if (map != null)
      {
        for (Integer key : map.keySet())
        {
          GutenbergBook book = map.get(key);
          StringBuilder sb = new StringBuilder();
          sb.append("Opus   " + book.getOpusNumber() + ": ");
          sb.append(book.getAuthor() + "  " + book.getTitle());
          sb.append("   " + book.getDocumentNumber() + " documents\n");
          sb.append("             " + book.getFile().getAbsolutePath() + "\n" );
          sumTab.getTextAreaS().append(sb.toString());
        }
        sumTab.getTextAreaS().append("\nIndex terms: "+in.getTotalIndexCount()+"\n");
        sumTab.getTextAreaS().append("Postings: "+in.getPostings());
      }
    }
  }
}
