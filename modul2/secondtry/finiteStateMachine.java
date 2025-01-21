package secondtry;

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
				}
			}

		},
		// New word
		NEWWORD {
			@Override
		},
		// if inside html-tag
		INHTMLTAG {
			@Override

		},
		// if in html-tag and escaped with '\'
		INHTMLTAGESCAPED {
			@Override
		},
		// if in html-tag and in '"' / block-quotes
		INHTMLTAGQUOTE {
			@Override
		},
		// if in html-tag and escaped and in block-quotes
		INHTMLTAGQUOTEESCAPED {
			@Override
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
