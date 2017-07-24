package zJUnitTest;

import static org.junit.Assert.*;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

import org.junit.Test;

import Indexer.Indexer;
import sourceProcessor.GutenbergBook;

public class ZIndexer {

	File file = new File("GutenbergFiles/Test1.txt");
	File file2 = new File("GutenbergFiles/Test2.txt");
	File file3 = new File("GutenbergFiles/Test3.txt");
	GutenbergBook book = new GutenbergBook(file, 0);
	GutenbergBook book2 = new GutenbergBook(file2, 1);
	GutenbergBook book3 = new GutenbergBook(file3, 2);
	
	Indexer index = new Indexer();
	
	
	@Test
	public void testIndex() 
	{
		index.clear();
		index.addTerms(book);
		
		Map<String, HashMap<Integer, ArrayList<Integer>>> map = index.getIndex();
		
		Map<String, HashMap<Integer, ArrayList<Integer>>> hash = new TreeMap<String, HashMap<Integer, ArrayList<Integer>>>();
		HashMap<Integer, ArrayList<Integer>> value = new HashMap<Integer, ArrayList<Integer>>();
		ArrayList<Integer> list = new ArrayList<Integer>();
		list.add(0);
		value.put(0, list);
		hash.put("test", value);
		
		assertEquals(hash, map);
	}
	@Test
	public void testMultipleKeys() 
	{
		index.clear();
		index.addTerms(book2);
		
		Map<String, HashMap<Integer, ArrayList<Integer>>> map2 = index.getIndex();
		
		Map<String, HashMap<Integer, ArrayList<Integer>>> hash2 = new TreeMap<String, HashMap<Integer, ArrayList<Integer>>>();
		HashMap<Integer, ArrayList<Integer>> value2 = new HashMap<Integer, ArrayList<Integer>>();
		ArrayList<Integer> list2 = new ArrayList<Integer>();
		
		list2.add(0);
		value2.put(1, list2);
		hash2.put("muppets", value2);
		hash2.put("singing", value2);
		hash2.put("loud", value2);
		
		assertEquals(hash2, map2);
	}
	
	@Test
	public void testMultipleValues() 
	{
		index.clear();
		index.addTerms(book3);
		
		Map<String, HashMap<Integer, ArrayList<Integer>>> map = index.getIndex();
		
		Map<String, HashMap<Integer, ArrayList<Integer>>> hash = new TreeMap<String, HashMap<Integer, ArrayList<Integer>>>();
		HashMap<Integer, ArrayList<Integer>> value = new HashMap<Integer, ArrayList<Integer>>();
		ArrayList<Integer> list = new ArrayList<Integer>();
		list.add(0);
		list.add(1);
		value.put(2, list);
		hash.put("test", value);
		
		assertEquals(hash, map);
	}
	
	@Test
	public void testMultipleFiles() 
	{
		index.clear();
		index.addTerms(book3);
		index.addTerms(book);
		
		Map<String, HashMap<Integer, ArrayList<Integer>>> map = index.getIndex();
		
		Map<String, HashMap<Integer, ArrayList<Integer>>> hash = new TreeMap<String, HashMap<Integer, ArrayList<Integer>>>();
		HashMap<Integer, ArrayList<Integer>> value = new HashMap<Integer, ArrayList<Integer>>();
		ArrayList<Integer> list = new ArrayList<Integer>();
		ArrayList<Integer> list2 = new ArrayList<Integer>();
		list.add(0);
		list.add(1);
		value.put(2, list);
		list2.add(0);
		value.put(0, list2);
		hash.put("test", value);
		
		assertEquals(hash, map);
	}
	
	@Test
	public void testNewPostings()
	{
		index.clear();
		index.addTerms(book2);
		
		Map<String, HashMap<Integer, ArrayList<Integer>>> map2 = index.getIndex();
		
		Map<String, HashMap<Integer, ArrayList<Integer>>> hash2 = new TreeMap<String, HashMap<Integer, ArrayList<Integer>>>();
		HashMap<Integer, ArrayList<Integer>> value2 = new HashMap<Integer, ArrayList<Integer>>();
		ArrayList<Integer> list2 = new ArrayList<Integer>();
		
		list2.add(0);
		value2.put(1, list2);
		hash2.put("muppets", value2);
		hash2.put("singing", value2);
		hash2.put("loud", value2);
		
		assertEquals(index.getNewPosting(), 3);
	}
	
	@Test
	public void testNewPostingsWithMultiDocs()
	{
		index.clear();

		index.addTerms(book);	//2 terms
		index.addTerms(book2);	//3 terms
		
		assertEquals(index.getNewPosting(), 3);
	}
	
	@Test
	public void testTotalPostings()
	{
		index.clear();
		
		index.addTerms(book3);	//2
		index.addTerms(book2);	//3
		
		assertEquals(index.getPostings(), 5);
	}
	
	@Test
	public void testToString()
	{
		index.clear();

		index.addTerms(book3);	//2
		index.addTerms(book2);	//3
		
		StringBuilder builder = new StringBuilder();
		
		builder.append("Key: loud Value: {1=[0]}\n");
		builder.append("Key: muppets Value: {1=[0]}\n");
		builder.append("Key: singing Value: {1=[0]}\n");
		builder.append("Key: test Value: {2=[0, 1]}\n");
		
		
		assertEquals(index.toString(), builder.toString());
	}
}
