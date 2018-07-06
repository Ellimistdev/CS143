import java.io.*;
/*
   Saveable objects can be saved and retrieved.
*/
public interface Saveable {
   public void save(String filename) throws IOException;
   public void retrieve(String filename) throws IOException;
}