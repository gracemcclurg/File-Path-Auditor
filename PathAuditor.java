package hashMap;

import hashMap.InsertionSort;
import java.util.Map;
import java.util.LinkedHashMap;
import java.util.*;

public class PathAuditor {

   static void audit(List<String> paths) {
      //make a map that encompasses all the letters and their associated paths
      Map<String, List<String>> lettersAndPaths = new LinkedHashMap<String, List<String>>();

      for (int i = 0; i < paths.size(); i++) {
         //for (int i = 0; i < 1; i++) {
         String folder = paths.get(i);

         //letter/phrases per a line
         String[] temp = folder.split("/");
         String[] lettersInFolder = Arrays.copyOfRange(temp, 1, temp.length);
         lettersAndPaths = findPaths(lettersInFolder, lettersAndPaths);
      }
      alphebatizePaths(lettersAndPaths);
      printAllPaths(lettersAndPaths);
   }

   static void alphebatizePaths(Map<String, List<String>> lettersAndPaths) {
      //go through each object in the map
      for (Map.Entry<String, List<String>> entry : lettersAndPaths.entrySet()) {
         //this list will have the paths in the correct order
         List<String> temp = new LinkedList<String>();
         String[] pathsArr = new String[entry.getValue().size()];

         for (int i = 0; i < entry.getValue().size(); i++)
            pathsArr[i] = entry.getValue().get(i);

         InsertionSort.sort(pathsArr);

         //insert the paths back into temp 
         for (int i = 0; i < pathsArr.length; i++)
            temp.add(pathsArr[i]);

         //the entry will be reset to the sorted values
         entry.setValue(temp);
      }

   }

   static Map<String, List<String>> alphebitizeKeys(Map<String, List<String>> letters) {

      String[] keyArr = new String[letters.size()];
      Map<String, List<String>> formated = new LinkedHashMap<String, List<String>>();

      int i = 0;
      //make an array housing all keys
      for (Map.Entry<String, List<String>> entry : letters.entrySet()) {
         keyArr[i] = entry.getKey();
         i++;
      }

      InsertionSort.sort(keyArr);

      //insert map entries in the order of the sorted keys
      for (int j = 0; j < keyArr.length; j++) {
         for (Map.Entry<String, List<String>> entry : letters.entrySet()) {
            //if the map key value matches the current letter that is next in order,
            //insert that entry into the new formated map
            if (entry.getKey().compareTo(keyArr[j]) == 0) {
               formated.put(entry.getKey(), entry.getValue());
            }
         }
      }
      return formated;

   }


   static void printAllPaths(Map<String, List<String>> letters) {
      //print keys and associated paths in the correct order
      Map<String, List<String>> finalFormat = alphebitizeKeys(letters);
      for (Map.Entry<String, List<String>> entry : finalFormat.entrySet()) {
         int pathLength = entry.getValue().size();
         //only print repeated entries
         if (pathLength > 1) {
            System.out.print(entry.getKey() + ": ");

            for (int i = 0; i < pathLength; i++) {
               //print commas for entries before the last
               if (i != pathLength - 1)
                  System.out.print(entry.getValue().get(i) + ", ");
               else
                  System.out.println(entry.getValue().get(i));
            }
         }
      }
   }

   static Map<String, List<String>> findPaths(String[] lettersToTest, Map<String, List<String>> allPaths) {
      //find paths for each letter
      for (int i = 0; i < lettersToTest.length; i++) {
         //list of the paths associated to that one letter
         List<String> letterPaths = new LinkedList<String>();
         String currentLetter = lettersToTest[i];
        
         //update list to have preexisting finds
         if (allPaths.get(currentLetter) != null) {
            letterPaths = allPaths.get(currentLetter);
         }
         
         //look through the current file name to find the occurance of letter
         for (int j = 0; j < lettersToTest.length; j++) {
            String path = "";
            //once a letter matches, update path list
            if (lettersToTest[j] == currentLetter) {
               //find the pathway that was taken in that name to get to the letter
               for (int x = 0; x <= j; x++) {
                  path = path + "/" + lettersToTest[x];
               }

            }
            //if letter occurance was found
            if (path != "") {
               letterPaths.add(path);
            }
            allPaths.put(currentLetter, letterPaths);
         }

      }
      return allPaths;

   }
}
