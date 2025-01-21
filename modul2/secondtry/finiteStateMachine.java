package modul2.secondtry;

/**
  * A class for counting words.
  * @author Felix Friesenbichler
  */


public class finiteStateMachine {

	int counter;
	public static int stringLength;
	public static int iterationCounter;

	enum State {

		// Possible next word.
		MIGHTBEWORD {
			@Override
			finiteStateMachine.State handleChar(char c, finiteStateMachine context) {
				if (isLetter(c)) {
					if (iterationCounter - stringLength == 0) {
						context.counter++;
					}
					return NEWWORD;
				} else if (c == '<') {
					return INHTMLTAG;
				} else {
					return MIGHTBEWORD;
				} } },
		// New word
		NEWWORD {
			@Override
			finiteStateMachine.State handleChar(char c, finiteStateMachine context) {
				if (isLetter(c)) {
					if (stringLength == iterationCounter) {
						iterationCounter = 0;
						context.counter++;
					}
					return NEWWORD;
				}
				if (c == '<') {
					context.counter++;
					return INHTMLTAG;
				} else {
					context.counter++;
					return MIGHTBEWORD;
				}
			}
		},
		// if inside html-tag
		INHTMLTAG {
			@Override
			finiteStateMachine.State handleChar(char c, finiteStateMachine context) {
				if (c == '\\') {
					return INHTMLTAGESCAPED;
				} else if (c == '\"') {
					return INHTMLTAGQUOTE;
				} else if (c == '>') {
					return MIGHTBEWORD;
				} else {
					return INHTMLTAG;
				}
			}
		},
		// if in html-tag and escaped with '\'
		INHTMLTAGESCAPED {
			@Override
			finiteStateMachine.State handleChar(char c, finiteStateMachine context) {
				return INHTMLTAG;
			}
		},
		// if in html-tag and in '"' / block-quotes
		INHTMLTAGQUOTE {
			@Override
			finiteStateMachine.State handleChar(char c, finiteStateMachine context) {
				if (c == '\\') {
					return INHTMLTAGESCAPED;
				} else if (c == '\"') {
					return INHTMLTAG;
				} else {
					return INHTMLTAGQUOTE;
				}
			}
		},
		// if in html-tag and escaped and in block-quotes
		INHTMLTAGQUOTEESCAPED {
			@Override
			finiteStateMachine.State handleChar(char c, finiteStateMachine context) {
				return INHTMLTAGQUOTE;
			}
		};

		/**
	  	* @param c current iterated character
	  	* @param context a finiteStateMachine
	  	* @return the current state
	  	*/
		abstract finiteStateMachine.State handleChar(char c, finiteStateMachine context);

		/**
	  	* @param c currently iterated character
	  	* @return if it's a valid letter
	  	*/
		boolean isLetter(char c) {
			return (c >= 'A' && c <= 'Z') || (c >= 'a' && c <= 'z')
                    		|| (c == 'Ä') || (c == 'Ö') || (c == 'Ü')
                    		|| (c == 'ä') || (c == 'ö') || (c == 'ü') || (c == 'ß'
            		);
		}
	}

	/**
	  * @param text the string whose words are to be counted
	  @ @return the amount of words in the string
	  */
	public int count(String text) {
		finiteStateMachine.State state = finiteStateMachine.State.MIGHTBEWORD;
		stringLength = text.length();
		counter = 0;
		iterationCounter = 0;
		for (char c: text.toCharArray()) {
			iterationCounter++;
			state = state.handleChar(c, this);
		}
		return counter;
	}
}
