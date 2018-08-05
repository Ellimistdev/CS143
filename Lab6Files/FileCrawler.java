// CSE 143, Winter 2011, Marty Stepp
// This program contains a recursive file/directory-crawling method.

import java.io.*;     // for File
import java.util.*;   // for Scanner

public class FileCrawler {
    public static void main(String[] args) {
        Scanner console = new Scanner(System.in);
        System.out.print("Directory to crawl? ");
        String directoryName = console.nextLine();
        
        File f = new File(directoryName);
        crawl(f);
    }
    
    // Prints the given file/directory and any files/directories inside it,
    // starting with zero indentation.
    // Precondition: f != null and f exists
    public static void crawl(File f) {
    	crawl(f, "");
    }
    
    // This recursive "helper" method prints the given file/directory and any
    // files/directories inside it at the given level of indentation.
    // Precondition: f != null and f exists
    private static void crawl(File f, String indent) {
    	System.out.println(indent + f.getName());
    	if (f.isDirectory()) {
        	// recursive case: directory
        	// print everything in the directory
        	File[] subFiles = f.listFiles();
    		indent += "    ";
        	for (int i = 0; i < subFiles.length; i++) {
              	crawl(subFiles[i], indent);
        	}
        }
    }
}

