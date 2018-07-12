
// Java Program to illustrate reading from FileReader
// using BufferedReader
import java.io.*;
public class ReadFromFile
{
  public static void main(String[] args)throws Exception
  {
  
  File file = new File("/Users/arnabsarkar/Desktop/input.rtf");
 
  BufferedReader br = new BufferedReader(new FileReader(file));
 
  String st;
  while ((st = br.readLine()) != null)
    System.out.println(st);
  }
}
