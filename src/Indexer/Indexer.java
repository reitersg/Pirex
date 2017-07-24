package Indexer;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.TreeMap;

import sourceProcessor.GutenbergBook;

/*********************
 * This class allows for texts to be indexed.
 *********************/
public class Indexer implements Serializable
{
  //alphabetizes
  private Map<String, HashMap<Integer, ArrayList<Integer>>> index;
  private int fileCount;
  private int totalIndexCount;
  private int posting;
  private int newPosting;
  private int pos;
  String[] stopList = {"", "a", "about", "above", "across", "after", "again", "against", "all", 
		  "along", "already", "also", "although", "always", "am", "among", "an", "and", "another", 
		  "any", "anybody", "anyone", "anything", "anywhere", "are", "aren't", "as", "at", "be",
		  "because", "been", "before", "being", "below", "between", "both", "but", "by", "can't", 
		  "cannot", "certainly", "could", "couldn't", "did", "didn't", "do", "does", "doesn't",
		  "don't", "down", "during", "each", "either", "enough", "ever", "every", "everybody",
		  "everyone", "everything", "everywhere", "few", "for", "from", "further", "furthered",
		  "furthering", "furthers", "gave", "generally", "gets", "give", "given", "gives", "going", 
		  "got", "had", "hadn't", "has", "hasn't", "have", "haven't", "having", "he", "he'd", "he's",
		  "her", "here", "here's", "herself", "him", "himself", "his", "how", "how's", "however", "i",
		  "i'd", "i'll", "i'm", "i've", "if", "in", "into", "is", "isn't", "it", "it's", "its", 
		  "itself","let's", "lets", "me", "more", "most", "mostly", "mr", "mrs", "much", "must",
		  "mustn't", "my", "myself", "no", "nobody", "non", "none", "nor", "not", "nowhere", "of",
		  "off", "often", "on", "once", "only", "or", "other", "others", "ought", "our", "ours",
		  "ourselves", "out", "over", "perhaps", "put", "puts", "quite", "rather", "really", "said",
		  "same", "several", "shall", "shan't", "she", "she'd", "she'll", "she's", "should", 
		  "shouldn't", "so", "some", "somebody", "someone", "something", "somewhere", "such", 
		  "than", "that", "that's", "the", "their", "theirs", "them", "themselves", "then",
		  "there", "there's", "therefore", "these", "they", "they'd", "they'll", "they're", 
		  "they've", "thing", "things", "this", "those", "though", "through", "thus", "to", 
		  "too", "toward", "under", "until", "up", "upon", "us", "very", "was", "wasn't", "we",
		  "we'd", "we'll", "we've", "went", "were", "weren't", "what", "what's", "when", "when's", 
		  "where", "where's", "whether", "which", "while", "who", "who's", "whom", "whose", "why",
		  "why's", "will", "with", "within", "without", "won't", "would", "wouldn't", "yet", "you",
		  "you'd", "you'll", "you're", "you've", "your", "yours", "yourself", "yourselves"};

	
	/**
	 * Basic constructor.
	 */
	public Indexer()
	{
		index = new TreeMap<String, HashMap<Integer, ArrayList<Integer>>>();
		totalIndexCount = 0;
	}
	
	/**
	 * This method takes in the gutenbergBook and adds the words by paragraphs.
	 * @param text GutenbergBook
	 */
	public void addTerms(GutenbergBook text)
	{
		fileCount = 0;
		newPosting = 0;
		GutenbergBook book = text;
		int opusNum = book.getOpusNumber();
		LinkedList<StringBuilder> paragraph = book.getOpus();
		
		String para = "";
		for(int i = 0; i<paragraph.size(); i++)
		{
			para = paragraph.get(i).toString().toLowerCase();
			for(String word : para.split("[\\W_]"))
			{
			  word.trim();
				if(!searchStopList(word)) 
				{
				  HashMap<Integer, ArrayList<Integer>> map = index.get(word);
				  if (map == null) 
				  {
				    map = new HashMap<Integer, ArrayList<Integer>>();
				    index.put(word, map);
				    map.put(opusNum, new ArrayList<Integer>());
				    this.fileCount++;
				    this.totalIndexCount++;
				  } 
				  else if (!map.containsKey(opusNum))
				  {
				    map.put(opusNum, new ArrayList<Integer>());
				  } 
				  if (!map.get(opusNum).contains(i)) 
				  {
				    map.get(opusNum).add(i);
				    newPosting++;
				  }
			
				}
			}
		}
					
	}
	/**
	 * Clears the Map.
	 */
	public void clear()
	{
		index.clear();
	}
	
	/**
	 * @return fileCount int
	 */
	public int getFileCount()
	{
		return fileCount;
	}
	
	/**
	 * @return fileCount int
	 */
	public int getNewPosting()
	{
		return newPosting;
	}
	
	/**
	 * 
	 * @return totalIndexCount int
	 */
	public int getTotalIndexCount()
	{
		return totalIndexCount;
	}
	
	/**
	 * Returns the Map.
	 * 
	 * @return index Map<String, HashMap<Integer,ArrayList<Integer>>>
	 */
	public Map<String, HashMap<Integer, ArrayList<Integer>>> getIndex()
	{
		return index;
	}
	
	/**
	 * This method checks if the word is a stopList word.
	 * 
	 * @param word String
	 * @return boolean
	 */
	private boolean searchStopList(String word)
	{
		return(Arrays.asList(stopList).contains(word));
	}
	
	/**
	 * This method checks if the word is a stopList word.
	 * 
	 * @param word String
	 * @return boolean
	 */
	public boolean searchIndex(String word)
	{
		return index.containsKey(word);
	}

	@Override
	public String toString()
	{
		StringBuilder builder = new StringBuilder();
		for (String word: this.index.keySet())
		{
			builder.append("Key: "+word + " Value: "+ this.index.get(word).toString() + "\n");
		}
		
		return builder.toString();
	}
	
	/********************
	 * This method returns postings.
	 * 
	 * @return posting int
	 ********************/
	public int getPostings()
	{
	  int posting = 0;
	  
	  for (String word: this.index.keySet())
	  {
		  HashMap<Integer, ArrayList<Integer>> postingList = this.index.get(word);
	      	
		  for (Integer key: postingList.keySet())
		  {
			  posting = posting + postingList.get(key).size();
			  //posting = posting + postingList.size();
		  }   
	  }
	  
	  return posting;
	}
	
	/********************
	 * Returns the size of the index.
	 * 
	 * @return index.size() int
	 ********************/
	public int getSize()
	{
	  return this.index.size();
	}
}
