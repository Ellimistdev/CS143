public class InheritanceClient {

public static void main(String[] args) {
   Pond[] ponds = {new Ocean(), new Pond(), new Lake(), new Bay()};
      for(int i=0; i<ponds.length; i++) {
         ponds[i].method1();
         System.out.println();
         ponds[i].method2();
         System.out.println();
         ponds[i].method3();
         System.out.println("\n");
      }   
   Pond var1 = new Bay();
   Object var2 = new Ocean();
   ((Lake) var1).method1();
   System.out.println();
   ((Bay) var1).method1();
   System.out.println();
   //((Ocean) var1).method2();
   System.out.println();
   ((Pond) var2).method2();
   System.out.println();
   ((Lake) var2).method2();
   System.out.println();
   ((Ocean) var2).method3();
}

}