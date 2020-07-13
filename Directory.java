/*LOKESH KUMAR NAHATA
* DIRECTORY PROJECT
*DATE - 4-06-2020*/
package chapter14;


import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Scanner; 

public class Directory 
{ 
     static void RecursivePrint(File[] arr, int level, String path) throws IOException  
     { 
    	 
    	
         
    	 String rw = "";  //code stored for read and write
        
    	 // headings of the table of directory
         
    	 String heading1 = "Name";
         String heading2 = "Length(Bytes)";
         String heading3 = "Modified";
         String heading4 = "Date & Time Modified";
         String heading5 =  "Code";
         
         //Print the heading
         System.out.printf("%-40s%25s%25s%25s%25s%n",heading1,heading2,heading3,heading4,heading5);
         System.out.println("\n---------------------------------------------------------------------------------------------------------------------------------------------");
         // for-each loop for main directory files 
         for (File f : arr)  
         { 
        	 
        	// Formatter for the raw date to readable form.
        	 // Innovation to change the last modified time to standard date and time.
             SimpleDateFormat dateformat = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
             // tabs for internal levels 
             for (int i = 0; i < level; i++) 
                 System.out.print("\t"); 
          // Code generation Read and Write rw, r-, -w, --
             if(f.isFile())  
             {
              
            	  if (f.canRead())
            		  rw +="r";
            	  else 
            		  rw += "-";
            	  if (f.canWrite())
            		  rw+="w";
            	  else
            		  rw +="-";
                 // display the each file with description
                 System.out.printf("%-40s|%25s|%25s|%25s|%20s|%n",f.getName(), f.length(),f.lastModified(),dateformat.format(f.lastModified()) ,rw); 
                 
                 rw ="";  //update to null for next iteration
             }  
             else if(f.isDirectory())  
             {  
                 System.out.println("[" + f.getName() + "]"); 
               
                 // recursion for sub-directories 
                 RecursivePrint(f.listFiles(), level + 1,path); 
                 
                 
             } 
             
         
         } 
    } 
     // Innovation 2 : renaming a file depending by users choice
     static void rename(String path)
     {
    	  Scanner sc1 = new Scanner(System.in);
    	  //input orignal file
    	 System.out.println("Enter the name of file to be renamed in directory beginning from /");
    	  
    	 String a = sc1.nextLine();
    	  String b = path.concat(a); // set the complete path of old name
    	 
    	  System.out.println("Enter the  new name of file beginning from /");
    	  String a1 = sc1.nextLine();
    	  String b1 = path.concat(a1); // set complete path of new name
    	  
    	  // access the file to rename it
    	  File oldName = new File(b);  
  		  File newName = new File(b1); 
         //renaming the file 
  		if (oldName.renameTo(newName)) 
  			System.out.println(a+"  Renamed successfully to  "+a1);		 
  		else
  			System.out.println("Error");  // error display if the old file name is not in the directory.
     }
      
     
     //innovation 3 creating a new file based on user input
     static void create(String path)
     {
    	 Scanner sc = new Scanner (System.in);
    	 // input name of new file
    	 System.out.println("Enter the file to create > ");
    	 String a = sc.nextLine();
    	 //creating a new file
    	 try
    	 {
    	 File file = new File(path.concat(a));
    	 if(file.createNewFile()) // check if file is created or not or already present there
    		 
    	 {
    		 System.out.println("new file is created!");
    		 
    	 }
    	 else
    		 System.out.println("File already exists.");
    	 }
    	 catch(IOException e)
    	 {
    		 e.printStackTrace();
    	 }
     }
     
     static void delete (String path)
     {
    	 Scanner sc3 = new Scanner (System.in);
    	 System.out.println("Enter the file to be deleted");
    	 String a = sc3.nextLine();
    	File file = new File(path.concat(a));
    	
    	if(file.delete())
    		System.out.println("File deleted successfully.");
    	else
    		System.out.println("Failed to delete the file ");
     }
       // Driver Method 
    public static void main(String[] args) throws IOException 
    { 
    	int i;
    	char ch;
    	Scanner sc = new Scanner (System.in);
    	System.out.println("Enter the full path for the directory details you want");
        // Provide full path for directory(change accordingly)   
        String maindirpath = sc.nextLine() ; 
          
                // File object 
        File maindir = new File(maindirpath); 
         
           
        if(maindir.exists() && maindir.isDirectory()) 
        { 
            // array for files and sub-directories  
            // of directory pointed by maindir 
            File arr[] = maindir.listFiles(); 
              
            System.out.println("::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::"); 
            System.out.println("Listing for directory : " + maindir); 
            System.out.println("::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::"); 
          
            
            // Calling recursive method 
            RecursivePrint(arr, 0,maindirpath);           
            
       }
        else 
        	System.out.println("directory not found !! \nenter a valid path name");
         
      //INNOVATION IN DIRECTORY FILe
        do
        {
        	//choices
        System.out.println("\n\nENTER SPECIFIC OPERATION  YOU WANT TO PERFORM : ");
        System.out.println("1 . Renaming a file in directory");
        System.out.println("2. creating a new file ");
        System.out.println("3. deleting a file");
        System.out.println("4. exit");
        System.out.println("ENTER YOUR CHOICE :");
        i = sc.nextInt();
        switch(i)
        {
        case 1:
        	rename(maindirpath);  //call rename function
        	break;
        case 2:
        	create(maindirpath);// call create new file function
        	break;
        case 3:
        	delete(maindirpath); //call delete function
        	break;
        case 4:
        	System.out.println("Thankyou for using.");// exit the operations
        	System.exit(0);
        	break;
        default:
        	System.out.println("Invalid Choice");
        		
        }
        System.out.println("Do you want to continue?(y/n)");
        ch = sc.next().charAt(0);
        if (ch =='n'||ch =='N')
        	System.out.println("Thankyou for using.");
        }while(ch=='y'||ch=='Y');
    } 
} 
