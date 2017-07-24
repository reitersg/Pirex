package adminInterface;

import java.awt.BorderLayout;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

/********************
 * This class contains the code for the summarize tab 
 * in the GUI. 
 ********************/
public class SumTab
{
  private JTextArea textAreaS;

  /********************
   * A get method for the textAreaS value.
   * 
   * @return textAreaS JTextArea
   ********************/
  public JTextArea getTextAreaS()
  {
	  return textAreaS;
  }
  
  /********************
   * Returns the sumPanel so that the summarize tab
   * can be formed in the GUI main.
   * 
   * @return sumPanel JPanel
   ********************/
  public JPanel sumPanelTab()
  {
    JPanel sumPanel = new JPanel();
    textAreaS = new JTextArea();
    textAreaS.setEditable(false);
    JScrollPane scrollPaneS = new JScrollPane(textAreaS);
    sumPanel.setLayout(new BorderLayout());
    sumPanel.add(scrollPaneS, BorderLayout.CENTER);
 
    return sumPanel;
  }
  
}

