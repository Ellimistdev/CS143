/*
Programmer: Nick Rodriguez
Description: Describes a Grammar Processor.
Time spent on Assignment #6: 3 hours, 43 minutes
*/
import java.util.*;

public class GrammarProcessor {

   private Map<String, String[]> rules = new TreeMap<String, String[]>();

   public GrammarProcessor(List<String> grammar) {
      if (grammar == null || grammar.isEmpty()) {
         throw new IllegalArgumentException();
      }
      for (String rule : grammar) {
         String[] currentRule = rule.split("::=");
         if (rules.containsKey(currentRule[0])){
            throw new IllegalArgumentException("Duplicate rule detected");
         }
         rules.put(currentRule[0], currentRule[1].split("[|]"));
      }
   }

   public boolean grammarContains(String symbol) {
      return rules.containsKey(symbol);
   }

   public String getSymbols() {
      return rules.keySet().toString();
   }

   public String generate(String symbol) {
      if (!rules.keySet().contains(symbol)){
         throw new IllegalArgumentException("Symbol not in set");
      }
      return getString(symbol);
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

   private String getString(String symbol) {
      Random rand = new Random();
      String[] ruleSet = rules.get(symbol);
      String rule = ruleSet[rand.nextInt(ruleSet.length)]; // select random value
      String[] result = rule.trim().split("\\s+"); // some values may be nonterminal
      String output = "";
      
      for (String str : result) {
         // if str is terminal, add to output, else recurse
         output += (!rules.keySet().contains(str)) ? String.format("%s ",str) : getString(str);
      }
      return output;
   }
}
