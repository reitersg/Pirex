package store;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashMap;
import java.util.LinkedHashMap;

import Indexer.Indexer;
import sourceProcessor.GutenbergBook;
import searchEngine.*;

/********************
 * This class contains all the code that allows 
 * files and indexed terms to be serialized and
 * deserialized.
 ********************/
public class SerializationMethods
{
  public static final String INDEXER_SER = "GutenbergFiles/indexer.ser";
  public static final String BOOKS_SER = "GutenbergFiles/books.ser";
  
  /**********************
   * This method deserializes the serialized file.
   * 
   * @return map LinkedHashMap<Integer, GutenbergBook>
   **********************/
  public static LinkedHashMap<Integer, GutenbergBook> deserializeBooks()
  {
    LinkedHashMap<Integer, GutenbergBook> map = null;
    try
    {
      FileInputStream fis = new FileInputStream(BOOKS_SER);
      ObjectInputStream ois = new ObjectInputStream(fis);
      //map = (LinkedHashMap<String, GutenbergBook>) ois.readObject();
      map = (LinkedHashMap<Integer, GutenbergBook>) ois.readObject();
      fis.close();
      ois.close();
    }
    catch (IOException | ClassNotFoundException e)
    {
      // TODO Auto-generated catch block
       
    }
    return map;
      
  }
  
  /********************
   * This method deserializes the index and returns it.
   * 
   * @return index Indexer
   ********************/
  public static Indexer deserializeIndexer()
  {
    Indexer index = null;
    try
    {
      FileInputStream fis = new FileInputStream(INDEXER_SER);
      ObjectInputStream ois = new ObjectInputStream(fis);
      index = (Indexer) ois.readObject();
      fis.close();
      ois.close();
    }
    catch (IOException | ClassNotFoundException f)
    {
      // TODO Auto-generated catch block
    } 
    return index;
  }
  
  /********************
   * This method serializes books.
   * 
   * @param books HashMap<Integer, GutenbergBook>
   ********************/
  public static void serializeBooks(HashMap<Integer, GutenbergBook> books)
  {
    try 
    {
      FileOutputStream fileOut = new FileOutputStream(BOOKS_SER);
      ObjectOutputStream out = new ObjectOutputStream(fileOut);
      out.writeObject(books);
      out.close();
      fileOut.close();
    } catch (IOException f) 
    {
      
    }
  }
  
  /********************
   * This method serializes the indexer.
   * 
   * @param index Indexer
   ********************/
  public static void serializeIndexer(Indexer index)
  {
    try 
    {
      FileOutputStream fileOut = new FileOutputStream(INDEXER_SER);
      ObjectOutputStream out = new ObjectOutputStream(fileOut);
      out.writeObject(index);
      out.close();
      fileOut.close();
    } catch (IOException f) 
    {
      
    }
  }
}
