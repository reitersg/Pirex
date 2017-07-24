package userInterface;

import java.awt.Container;

import javax.swing.BorderFactory;
import javax.swing.JComponent;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

import adminInterface.LoadTab;
import adminInterface.SumTab;
import store.SumTabChange;

/********************
 * This class is meant to be the backbone of the Pirex GUI
 * and its functionality.
 * 
 * @author Team A
 ********************/
public class GUI
{
	JFrame frame;
	final int SIZE_X = 800, SIZE_Y = 500;
	final int BORDER = 10;
	private Container contentPane;
	
	private LoadTab loadTab = new LoadTab();
	private SumTab sumTab = new SumTab();
	private SearchTab searchTab = new SearchTab();
	
	/********************
	 * GUI's constructor which contains the code necessary for 
	 * Pirex's GUI.
	 ********************/
	public GUI()
	{
		frame = new JFrame("Pirex");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(SIZE_X, SIZE_Y);
		frame.setLocationRelativeTo(null);
		contentPane = frame.getContentPane();
	
	 	//Tabs
		JTabbedPane tabPane = new JTabbedPane();
	 
		contentPane.add(tabPane);
		JPanel searchPanel = searchTab.searchPanelTab(); //Panel dedicated to the Search tab
		JPanel loadPanel = loadTab.loadPanelTab(); //Panel dedicated to the Load tab
		JPanel sumPanel = sumTab.sumPanelTab(); //Panel dedicated to the Summarizing tab
		tabPane.addChangeListener(new SumTabChange(sumTab));
		tabPane.addTab("Search for Documents", searchPanel);
		tabPane.addTab("Load Documents", loadPanel);
		tabPane.addTab("Summarize Documents", sumPanel);
		
		((JComponent)contentPane)
        	.setBorder(BorderFactory.createEmptyBorder(BORDER, BORDER, BORDER, BORDER));
	}
	
	/********************
	 * This method allows for the Pirex GUI to be displayed.
	 ********************/
	private void display()
	{
		frame.setVisible(true);
	}
	
  	/*******************
  	 * The main of the GUI class.
  	 * 
  	 * @param args String[]
  	 *******************/
	public static void main(String[] args)
	{
		new GUI().display();
	}

}
