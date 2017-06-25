/*Method finds all valid substrings of a given input string
 * @param: word: Given input word
 * @param: dict: A dictionary of all valid English words
 */
public static Set<String> getDictionarySubstrings(String word, Set<String> dict) {
	
	//validSubStrings contains all valid substrings of a word
	Set<String> validSubStrings = new HashSet<>();
	if(word == null || word.length() ==0 || dict.size()==0)
		return validSubStrings;
	
	for(int i=0;i<word.length();i++){
		for(int j=i+1;j<=word.length();j++){
			
			//Check if every substring of a word is valid
			String currentWord = word.substring(i,j);
			if(dict.contains(currentWord)){
				validSubStrings.add(currentWord);
			}			
		} // End of inner for
	} // End  of for
	return validSubStrings;
}
