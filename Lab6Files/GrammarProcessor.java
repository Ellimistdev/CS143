/*
Programmer: Nick Rodriguez
Description: Describes a Grammar Processor.
*/
import java.util.*;

public class GrammarProcessor {

   private Map<String, String[]> rules = new TreeMap<String, String[]>();

   public GrammarProcessor(List<String> grammar) {
      if (grammar == null || grammar.isEmpty()) {
         throw new IllegalArgumentException();
      }
      System.out.println("GrammarProcessor constructor display of grammar parameter:");
      for (String rule : grammar) {
         System.out.println(rule);
         String[] currentRule = rule.split("::=");
         if (rules.containsKey(currentRule[0])){
            throw new IllegalArgumentException("Duplicate rule detected");
         }
         rules.put(currentRule[0], currentRule[1].split("[|]"));
      }
      System.out.println("GrammarProcessor constructor display of contents of rules map:");
      for (String key : rules.keySet()){
         System.out.printf("%s\n\t%s\n", key, Arrays.toString(rules.get(key)));
      }
   }

   // not implemented
   public boolean grammarContains(String symbol) {
      return false;
   }

   // not implemented
   public String getSymbols() {
      return "";
   }

   // not implemented
   public String generate(String symbol) {
      return "";
   }

   public String[] generate(String symbol, int times) {
      if (times < 0) {
         throw new IllegalArgumentException();
      }
      String[] result = new String[times];
      for (int i = 0; i < times; i++) {
         result[i] = generate(symbol);
      }
      return result;
   }
}