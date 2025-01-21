package secondtry;

import org.junit.*;
import secondtry.finiteStateMachine;

class secondTry {

	/**
	* Test method for testing the FSM.
	* @author Felix Friesenbichler
	*/
	@Test
	void test() {
		// leicht
	finiteStateMachine FSM = new finiteStateMachine();
	assert 0 == FSM.count("");
	assert 0 == FSM.count(" ");
	assert 0 == FSM.count("  ");


        // normal
	assert 1 == FSM.count("one");
	assert 1 == FSM.count(" one");
	assert 1 == FSM.count("one ");
	assert 1 == FSM.count(" one ");
	assert 1 == FSM.count(" one  ");
	assert 1 == FSM.count("  one ");
	assert 1 == FSM.count("  one  ");

	assert 1 == FSM.count("one:");
	assert 1 == FSM.count(":one");
	assert 1 == FSM.count(":one:");
	assert 1 == FSM.count(" one  ");
	assert 1 == FSM.count(" one : ");
	assert 1 == FSM.count(": one :");
	assert 3 == FSM.count("ein erster Text");
	assert 3 == FSM.count(" ein erster    Text     ");
	assert 3 == FSM.count("ein:erster.Text");

        // vielleicht falsch
	assert 1 == FSM.count("a");
	assert 1 == FSM.count(" a");
	assert 1 == FSM.count("a ");
	assert 1 == FSM.count(" a ");

        // mit html
	assert 1 == FSM.count(" one <html> ");
	assert 1 == FSM.count(" one < html> ");
	assert 1 == FSM.count(" one <html > ");
	assert 1 == FSM.count(" one < html > ");
	assert 4 == FSM.count(" one <html> two<html>three <html> four");

	assert 2 == FSM.count(" one <html> two");
	assert 2 == FSM.count(" one <html>two ");
	assert 2 == FSM.count(" one<html> two ");
	assert 2 == FSM.count(" one<html>two ");
	assert 2 == FSM.count(" one<img alt=\"xxx\" > two");
	assert 2 == FSM.count(" one<img alt=\"xxx yyy\" > two");

	assert 2 == FSM.count(" one \"two\" ");
	assert 2 == FSM.count(" one\"two\" ");
	assert 2 == FSM.count(" one \"two\"");
	assert 3 == FSM.count(" one \"two\"three");
	assert 3 == FSM.count(" one \"two\" three");

        // html - trickreich
        // Achtung: das ist teilweise nicht ganz legales HTML
	assert 1 == FSM.count(" one<html");

	assert 2 == FSM.count(" one<img alt=\"<bild>\" > two");
	assert 2 == FSM.count(" one<img alt=\"bild>\" > two");
	assert 2 == FSM.count(" one<img alt=\"<bild>\" keinwort> two");
	assert 2 == FSM.count(" one<img alt=\"<bild>\" src=\"bild.png\" > two");
	assert 2 == FSM.count(" one<img alt=\"<bilFd\" keinwort> two");

	assert 1 == FSM.count(" one<img alt=\"<bild\" keinwort");
	assert 2 == FSM.count(" one<img alt=\"<bild\" keinwort> two");
	assert 1 == FSM.count(" one<img alt=\"<bild keinwort> keinwort");
	assert 2 == FSM.count(" one<img alt=\"<bild keinwort keinwort\">two");
	assert 2 == FSM.count(" one<img alt=\"<bild keinwort< keinwort\">two");

        // ganz ganz fies -- \ entwertet das nächste Zeichen
	assert 2 == FSM.count(" one<img alt=\"<bild \\\" keinwort> keinwort\" keinwort>two");
	assert 2 == FSM.count(" one<img alt=\"<bild \\\" keinwort<keinwort\" keinwort>two");
	assert 2 == FSM.count(" one<img alt=\"<bild \\\" keinwort keinwort\" keinwort>two");

	assert 4 == FSM.count(" \\\"null\\\" one<img alt=\"<bild \\\" keinwort keinwort\" keinwort>two \"three\"");
	}

}
