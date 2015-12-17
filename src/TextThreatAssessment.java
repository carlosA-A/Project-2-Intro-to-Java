//Name: Carlos Avogadro
//UFL ID: c.avogadro
//Section:6909
//Project Number: 2
//Brief description of file documents: Program that opens,reads,"un-encrypts",and searches for key words in a txt doc
import java.io.File;
import java.util.Scanner;


public class TextThreatAssessment {
	public static void main(String [] args){
		
		
		
		//Variables
		
		int error=0;
		
		
		//Determine if program should continue or close
		String continueOrStop="";
		
		while(!(continueOrStop.equals("n"))&&(error!=1)){
			//String that contains reversed encrypted string
			String newSentence="";
			
			//Takes decrypted string
			String decryptedText="";
			
			//Contains new strings from substrings
			String modifiedText=" ";
			
			//Counts words
			int wordCount=0;
			
			//Threat Level
			String threatLevel="";
			
			//File id being opened
			String fileName="";
			
			//Word being searched
			String findWord="";
			
			System.out.print("Enter the UFID of the person who wrote the message: ");
			
			Scanner input= new Scanner(System.in);
			//Name of file being opened
			fileName=input.next();
			
			//First number in UFID stored in var
			String firstCharUfid=""+fileName.charAt(0);
			
			if(firstCharUfid.equals("0")){
				System.out.println("Error: UFID must not begin with a zero. Now Exiting.");
				++error;
			}
			else if((fileName.length()>8) ||(fileName.length()<8)){
				System.out.print("Error: UFID must be 8 digits. Now Exiting.");
				++error;
			}
			
			else{
				fileName=fileName+".txt";
				System.out.printf("Opening file: %s\n",fileName);	
				//Encrypted string from file
				String encryptedString = "";
		        try
				         {
				File file = new File(fileName);
				Scanner sc = new Scanner(file);
				while (sc.hasNextLine())
				{
				encryptedString+=sc.nextLine();
				}
				 sc.close();
				} catch(Exception ex)
				{
				ex.printStackTrace();
				}
							
				//Loop that reverses a string
				for(int i=encryptedString.length()-1;i>0;--i){
					
					newSentence=newSentence+encryptedString.charAt(i);	
					
				}
				//Loop that decrypts string by creating a new string with all the odd characters 
				for (int i=0;i<newSentence.length();i++){
					if ((i)%2 !=0 ){
						decryptedText=decryptedText+newSentence.charAt(i);	
					}
				}
								
				System.out.print("Enter the word (or phrase) of interest: ");
				
				findWord=input.next();
				
				//Counts times a word appears in a sentence
				while(decryptedText.contains(findWord)){
						
						//Counter for word being looked for
						++wordCount;
						//Removes the word being looked for from string so it can look for repeats,creates a substring without the word being looked for
						modifiedText=""+decryptedText.substring(decryptedText.indexOf(findWord)+findWord.length());
						decryptedText=modifiedText;
						
					
					
				}
				System.out.printf("The word %s was found %d time(s).\n",findWord,wordCount);
				
				//Determine threat level based on the amount of times a word appears in the text being analyzed 
				if(wordCount>=4){
					threatLevel="Highly Threatening";
					
				}
				else if(wordCount>=3){
					threatLevel="Threatening";
					
				}
				
				else if(wordCount>=2){
					threatLevel="Somewhat Threatening";
				}
				else{
					threatLevel="Safe";
					
				}
				
				
				System.out.printf("Messages from student %s were found to be: %s\n",fileName,threatLevel);	
				
				
				System.out.print("Press (y) to continue or (n) to exit. ");
				
				continueOrStop=input.next();
				
				
			}
			
		}
		
	}

}
