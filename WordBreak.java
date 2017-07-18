public static int getNumValidSentences(String sentence, Set<String> dict) {
        //Empty string or empty dictionary        
	if(sentence==null || sentence.length()==0 || dict.ssize()==0)         return 0;
	
	//outputSentences holds all the valid sentences the string can be broken into        
        ArrayList<String> outputSentences;
        
	//When the input string contains just one character
	if(sentence.length()==1){
            return dict.contains(sentence) ? 1:0;
         }
        
	//wordPositions[i] holds all valid words the sentence can be broken down into at position i
        List<String>[] wordPositions = new ArrayList[sentence.length()+1];
        wordPositions[0] = new ArrayList<>();
        
        for(int i=0;i<sentence.length();i++){
            if(wordPositions[i]!=null){
		//Check if substring, sentence(i,j) is a valid word 
                for(int j=i+1;j<=sentence.length();j++){
                    String subString = sentence.substring(i,j);
                    		    	
                    if(dict.contains(subString)){
                        if(wordPositions[j]==null){
                            ArrayList<String> listOfWords = new ArrayList<>();
                            listOfWords.add(subString);
                            wordPositions[j] = (listOfWords);
                        } else{
                            wordPositions[j].add(subString);
                        }
                    }
                } // End of inner for
            }
        } // End of for
        
	//If sentence cannot be broken to valid dictionary words, return 0
        if(wordPositions[sentence.length()]==null)
            return 0;
        
        outputSentences = new ArrayList<>();

	//Perform a DFS on all the valid sentence word sequences to construct all possible output sequences
        constructSentences(wordPositions, "", s.length(), outputSentences);
        
    return outputSentences.size();    
    }
    /*DFS to ocnstruct output sequences
     * @param: wordPositions  : A list of all valid words at each postion in the input
     * @param: combinedString : The output string that will be constructed. Initially, empty
     * @param: stringPosition : Indicates the position in the input to choose the corresponding words and construct the output sequence
     * @param: outputSentences: Holds all output sentences
     */
private void constructSentences(List<String>[] wordPositions, String combinedString, int stringPosition, ArrayList<String> outputSentences){
        
        //If the input is traversed completely
        if(stringPosition==0){
            outputSentences.add(combinedString.trim());
            return;
        }
       
        for(String word : wordPositions[stringPosition]){
            String newString = word + " " + combinedString;
            constructSentences(wordPositions, newString, stringPosition - word.length(), outputSentences); 
        }
    }

