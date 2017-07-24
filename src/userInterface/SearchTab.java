package userInterface;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.TextArea;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;

import searchEngine.SearchTabGUIListener;

/********************
 * This class contains the code for the
 * search tab in the GUI.
 ********************/
public class SearchTab 
{
	JTextField textFieldQ;
	TextArea searchDocumentsFound; 
	TextArea longFormOfDocument;
	public JTextField displayCount;
	final int BORDER = 10;
	final int TEXTFIELD_QVALUE = 45, LONGTEXTFIELD_VALUE = 40, SHORTTEXTFIELD_VALUE = 20;
	final int VISIBLE_ROW_COUNT = 10, FIXED_CELL_HEIGHT = 15, FIXED_CELL_WIDTH = 100;
	final String CLEAR = "Clear";
	public static JList<String> docFound = new JList<String>();
	static JScrollPane scrollPane;
	/********************
	 * A method that allows other classes to get the
	 * longFormOfDocument TextArea.
	 * 
	 * @return longFormOfDocument textArea
	 ********************/
	public TextArea getLongFormOfDocument()
	{
		return longFormOfDocument;
	}
	
	/********************
	 * A method that allows other classes to get the
	 * searchDocumentsFound TextArea.
	 * 
	 * @return textArea searchDocumentsFound
	 ********************/
	public TextArea getSearchDocumentsFound()
	{
		return searchDocumentsFound;
	}
	
	/********************
	 * A method that allows other classes to get the
	 * textFieldQ JTextField.
	 * 
	 * @return textFieldQ JTextField
	 ********************/
	public JTextField getTextFieldQ()
	{
		return textFieldQ;
	}
	
	/********************
	 * A method that allows other classes to get the 
	 * CLEAR String.
	 * 
	 * @return CLEAR String
	 ********************/
	public String getClear()
	{
		return CLEAR;
	}
	
	/********************
	 * This method contains the bulk of the code
	 * for the display of the search tab for the GUI. 
	 * 
	 * @return searchPanel JPanel
	 ********************/
	public JPanel searchPanelTab()
	{
	  DefaultListModel<String> model = new DefaultListModel<String>();
	  docFound.setModel(model);
	  docFound.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
	  docFound.setLayoutOrientation(JList.VERTICAL);
	  docFound.setVisibleRowCount(VISIBLE_ROW_COUNT);
	  docFound.setFixedCellHeight(FIXED_CELL_HEIGHT);
	  docFound.setFixedCellWidth(FIXED_CELL_WIDTH);
	  docFound.addListSelectionListener(new SearchTabGUIListener(this));
		JPanel searchPanel = new JPanel();
		JLabel labelQ = new JLabel("Query: ", JLabel.RIGHT);
		JButton clearButtonQ = new JButton(CLEAR);
		clearButtonQ.addActionListener(new SearchTabGUIListener(this));
		textFieldQ = new JTextField(TEXTFIELD_QVALUE);
		textFieldQ.addKeyListener(new SearchTabGUIListener(this));
		displayCount = new JTextField(LONGTEXTFIELD_VALUE);
		displayCount.setEditable(false);
		//Adding buttons to the Searching Tab
		searchPanel.setLayout(new BorderLayout());
		JPanel jPanelQ1 = new JPanel();
		jPanelQ1.add(labelQ);
		jPanelQ1.add(textFieldQ);
		jPanelQ1.add(clearButtonQ);
		searchPanel.add(jPanelQ1,  BorderLayout.NORTH);
		Box box = Box.createVerticalBox();
		scrollPane  = new JScrollPane(docFound);
		searchDocumentsFound = new TextArea();
		box.add(scrollPane);
		box.add(displayCount);
		displayCount.setText("No Documents currently searched");
		longFormOfDocument = new TextArea();
		box.add(longFormOfDocument);
		searchPanel.add(box, BorderLayout.CENTER);
		searchPanel.setBorder(BorderFactory.createEmptyBorder(BORDER, BORDER, BORDER, BORDER));
	  
		return searchPanel;
	}
}
