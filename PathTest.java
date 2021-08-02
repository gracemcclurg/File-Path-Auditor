package hashMap;



import java.util.*;
import java.util.List;
import hashMap.PathAuditor;

public class PathTest {
   public static void main(String[] args) {
      List<String> letterPaths = new LinkedList<String>();
      //is successful because keys, paths are sorted alphebetically
      //y is not shown
      //all paths are found
      letterPaths.add("/x/a/z");
      letterPaths.add("/ab/x");
      letterPaths.add("/a/x/ab/y");
      letterPaths.add("/z");
      //test 1
      PathAuditor.audit(letterPaths);
      
      System.out.println();
       System.out.println("Test 2:");
      List<String> test2 = new LinkedList<String>();
      //is successful because keys, paths are sorted alphebetically
      //y is not shown
      //all paths are found
      test2.add("/a/ab/x/y");
      test2.add("/ab/x");
      test2.add("/x/a");
      //test 2
      PathAuditor.audit(test2);
   }
}
