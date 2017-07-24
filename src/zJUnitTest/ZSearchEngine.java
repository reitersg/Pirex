package zJUnitTest;

import static org.junit.Assert.*;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;

import org.junit.Test;

import sourceProcessor.GutenbergBook;
import Indexer.Indexer;
import searchEngine.SearchEngine;

public class ZSearchEngine {
	
	File file = new File("GutenbergFiles/Test1.txt");
	File file2 = new File("GutenbergFiles/Test2.txt");
	File file3 = new File("GutenbergFiles/Test3.txt");
	GutenbergBook book = new GutenbergBook(file, 0);
	GutenbergBook book2 = new GutenbergBook(file2, 1);
	GutenbergBook book3 = new GutenbergBook(file3, 2);
	
	
	@Test
	public void testSearchList() 
	{
		Indexer index = new Indexer();
		index.addTerms(book);

		SearchEngine search = new SearchEngine(index);
		search.addQueryTerm("test");
		ArrayList<String> test = new ArrayList<String>();
		test.add("test");
		assertEquals(search.getSearch(), test);
		
		index.addTerms(book2);

		search.addQueryTerm("muppets sing");
		test.clear();
		test.add("muppets");
		test.add("sing");
		assertEquals(search.getSearch(), test);
	}
	
	@Test
	public void testSearchTermFound() 
	{
		Indexer index = new Indexer();
		index.addTerms(book);
		SearchEngine search = new SearchEngine(index);
		
		assertEquals(search.searchTermFound("test"), true);
		assertEquals(search.searchTermFound("fail"), false);
		
		index.addTerms(book2);

		search.addQueryTerm("muppets sing");
		assertEquals(search.searchTermFound(), true);
		
		search.addQueryTerm("This should fail");
		assertEquals(search.searchTermFound(), false);
	}
	
	@Test
	public void testGetHashMap()
	{
		Indexer index = new Indexer();
		index.addTerms(book);		
		index.addTerms(book2);
		SearchEngine search = new SearchEngine(index);

		HashMap<Integer, ArrayList<Integer>> map = new HashMap<Integer, ArrayList<Integer>>();
		ArrayList<Integer> list = new ArrayList<Integer>();
		list.add(0);
		map.put(0, list);
		
		assertEquals(search.getHashMap("test"), map);
		
		index.addTerms(book3);
		ArrayList<Integer> list2 = new ArrayList<Integer>();
		list2.add(0);
		list2.add(1);
		map.put(2, list2);
		
		assertEquals(search.getHashMap("test"), map);
		
	}
}
