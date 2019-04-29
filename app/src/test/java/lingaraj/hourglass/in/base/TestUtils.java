package lingaraj.hourglass.in.base;

import com.google.gson.Gson;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import static org.junit.Assert.assertNotNull;

public class TestUtils {

  private static TestUtils INSTANCE = new TestUtils();
  private static Gson gson = new Gson();

  public static String getFileString(String path) {
    try {
      StringBuilder sb = new StringBuilder();
      BufferedReader reader = new BufferedReader(new InputStreamReader(INSTANCE.getClass().getClassLoader().getResourceAsStream(path)));
      String line;
      while ((line = reader.readLine()) != null) {
        sb.append(line);
      }
      return sb.toString();
    } catch (IOException e) {
      throw new IllegalArgumentException("Could not read from resource at: " + path);
    }
  }



}
