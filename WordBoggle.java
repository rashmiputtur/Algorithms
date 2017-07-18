/*
O C H
A S T
T R Q

Find all valid words and print them out.
Output: CASH, STAR, START, not COACH

*/

import java.io.*;
import java.util.*;


  
  
  private void findWords(String[][] matrix){
    boolean visited [matrix.length][matrix[0].length] ={{false}};
    
    String word = "";
    
    for(int i=0;i<matrix.length;i++){
      for(int j=0;j<matrix[0].length;j++){
        computeWords(matrix, i, j, visited, word);
      }
    }
  }
  
  
  private void computeWords(String[][] matrix, int i, int j, StringBuilder word){
    
    visited[i][j] = true;
    word.append(matrix[i][j]);
    
    
    //If word is present in the dictionary
    if(isWord(word.toString())==true){
       System.out.println(word);
    }
    
    //Traverse adjacent cells to find probable valid words
    for(int row = i-1;row<=i+1 && row<matrix.length;row++){
      for(int col = j-1;col <= j+1 && col <matrix[0].length;j++){
        if(row>=0 && col >=0 && !visited[row][col]){
          computeWords(matrix, row, col, word);
        }
      }
    }
    
    //Backtrack by 1 position
    word.deleteCharAt(word.length()-1);
    visited[i][j] = false;
  } 
  
  //Dictionary is an ArrayList of valid words (dict)
  private boolean isWord(String word){
     for(String validWord : dict){
       if(validWord.equals(word)){
         return true;
       }
     }
    
    return false;
  }
      


