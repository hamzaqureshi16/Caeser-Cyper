/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ceasercypher;

import java.util.Random;
import java.util.Scanner;

/**
 *
 * @author Mhamz
 */
public class CeaserCypher {

    /**
     * @param args the command line arguments
     */
    public static final double[] english = {
        0.0855, 0.0160, 0.0316, 0.0387, 0.1210, 0.0218, 0.0209, 0.0496, 0.0733,
        0.0022, 0.0081, 0.0421, 0.0253, 0.0717, 0.0747, 0.0207, 0.0010, 0.0633,
        0.0673, 0.0894, 0.0268, 0.0106, 0.0183, 0.0019, 0.0172, 0.0011
    };
    
 
  
  public static int[] count(String s){
      int[] frequency = new int[26];
      int f= 0;
      
      for(int j = 97 ; j< 123; j++){
          f= 0;
      for(int i = 0 ; i< s.length(); i++){
          if(Character.toLowerCase(s.charAt(i)) == (char)j)
              f++;
      
     
      }
       frequency[j-97] = f;}
      
      
      return frequency;
  }
  
  public static double[] letterFreq(String s){
      int[] count = count(s);
      double[] letterfreq = new double[26];
      
      for(int i = 0; i < 26; i++){
          letterfreq[i] = (double) count[i]/s.length();
      }
      
      
      return letterfreq;
  }
  
  
  public static double chiSquared(double[] f1, double[] typical){
      double score = 0;
      
      for(int i = 0 ; i < 26; i++){
          score += Math.pow(f1[i]-typical[i],2)/typical[i];
      }



return score;      
  }
    
    
    
    
    
    
    public static char rotate(int shift, char ch){
        
       
       if(ch != ' '){
           
            char x = Character.toLowerCase(ch);
            if(shift > 0){
             while(shift > 0){
            if((int) x == 122)
                x = (char)96;
            
            /*if((int) x <= 97)
                x = (char)122;
            */
         x = (char) ((int)x+1);
         shift--;
        }

        }
        else if(shift < 0){
             while(shift < 0){
          /*  if((int) x >= 122)
                x = (char)97;
            */
            if((int) x == 97)
                x = (char)123;
         x = (char) ((int)x-1);
         shift++;
        }

        }
        
         return Character.isUpperCase(ch)? Character.toUpperCase(x):x;
       }
       else{
          
           return ' ';
       }
       
    }
    
    public static String encrypt(int shift, String s){
        String tempString = "";
        
        for(int i = 0; i < s.length(); i++){
            tempString+=rotate(shift, s.charAt(i));
        }
        
        
        return tempString;
    }
    
    public static String decrypt(int shift, String s){
        shift*=-1;
       return encrypt(shift, s);
        
    }
    
  
    public static void main(String[] args) {
        // TODO code application logic here
        
        Scanner scan = new Scanner(System.in);
        Random rand = new Random();
        int r = rand.nextInt(26);
        String sent = encrypt(r,scan.nextLine());
       
        System.out.println("Encrypted sentence is "+sent+"\n"+r);
        
        String[] guesses = new String[26];
        double[] closeness = new double[26];
        for(int i = 0 ; i < guesses.length; i++){
            guesses[i] = decrypt(i, sent);
            closeness[i] = chiSquared(letterFreq(guesses[i]), english);
            System.out.println(guesses[i]+"\t"+ closeness[i]);
        }
        
        double smallest  =  Double.MAX_VALUE;
        int ind = 0;
        for(int i = 0;  i < closeness.length; i++){
            if(smallest > closeness[i]){
                smallest = closeness[i];
                ind = i;
                
            }
        }
        
        
        
        System.out.println("\nClosest guess was "+guesses[ind]);
        
        
        
        
        
    }
    

    
}
