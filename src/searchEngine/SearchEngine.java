package searchEngine;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

import Indexer.Indexer;

/********************
 * This class will allow the user to search for terms
 * that are in the loaded opuses in Pirex. Pirex will
 * take the term or terms that is entered and search through the 
 * index database to find where the word occurs in the 
 * opuses that are loaded into Pirex and display.
 ********************/
public class SearchEngine 
{
	private Map<String, HashMap<Integer, ArrayList<Integer>>> index;
	private static ArrayList<String> search;
	
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
	
	/*******************
	 * Constructor Method.
	 * 
	 * @param input Indexer
	 *******************/
	public SearchEngine(Indexer input)
	{
		index = input.getIndex();
		search = new ArrayList<String>();
	}
	
	/********************
	 * Adding query terms.
	 * 
	 * @param input String
	 ********************/
	public void addQueryTerm(String input)
	{
		search.clear();
		String word = input.toString();
		if(!word.contains(" ")) 
		{
			search.add(word.toLowerCase());
			return;
		}
		else 
		{
			word = word+" ";
			while(word.contains(" ")) 
			{
				String temp = word.substring(0,word.indexOf(" "));
				if(!Arrays.asList(stopList).contains(temp)) 
				{
					search.add(temp.toLowerCase());
				}
				word = word.substring(word.indexOf(" ")+1);
			}
		}
	}
	
	/*******************
	 * This method gets the array list that 
	 * is needed for the search.
	 * 
	 * @return search ArrayList<String>
	 *******************/
	public static ArrayList<String> getSearch()
	{
		return search;
	}
	
	/********************
	 * Returns whether a specific search term exists 
	 * in the index.
	 * 
	 * @param str String
	 * @return index.containsKey(str) boolean
	 ********************/
	public boolean searchTermFound(String str)
	{
		return index.containsKey(str);
	}
	
	/********************
	 * Returns whether a search term exists in
	 * the index.
	 *
	 * @return boolean
	 ********************/
	public boolean searchTermFound()
	{
		for(String temp : search) 
		{
			if(index.containsKey(temp))
				return true;
		}
		 
		return false;
	}
	
	/********************
	 * This returns the hash map.
	 * 
	 * @param str String 
	 * @return index.get(str) HasMap<Integer, ArrayList<Integer>>
	 ********************/
	public HashMap<Integer, ArrayList<Integer>> getHashMap(String str)
	{
		return index.get(str);
	}
	
	/********************
	 * This gets the array list of documents.
	 * 
	 * @param documents LisedLis<StringBuilder>
	 * @return foundDocuments ArrayList<StringBuilder>
	 ********************/
	public ArrayList<StringBuilder> getDocuments(LinkedList<StringBuilder> documents)
	{
	  ArrayList<StringBuilder> foundDocuments = new ArrayList<StringBuilder>();
	  for (StringBuilder sb : documents)
	  {
		  for(String temp : search)
		  {
			  if (sb.toString().toLowerCase().contains(temp))
			  {
				  foundDocuments.add(sb);
			  }
		  }
	  }
	  
	  return foundDocuments;
	}
}
