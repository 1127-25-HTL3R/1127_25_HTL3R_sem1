package secondtry;

import secondtry.finiteStateMachine;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class secondTryTest {

	/**
	* Test method for testing the FSM.
	* @author Felix Friesenbichler
	*/
	@Test
	void test() {
// leicht
        assertEquals(0, new finiteStateMachine().count(""));
        assertEquals(0, new finiteStateMachine().count(" "));
        assertEquals(0, new finiteStateMachine().count("  "));

        // normal
        assertEquals(1, new finiteStateMachine().count("one"));
        assertEquals(1, new finiteStateMachine().count(" one"));
        assertEquals(1, new finiteStateMachine().count("one "));
        assertEquals(1, new finiteStateMachine().count(" one "));
        assertEquals(1, new finiteStateMachine().count(" one  "));
        assertEquals(1, new finiteStateMachine().count("  one "));
        assertEquals(1, new finiteStateMachine().count("  one  "));

        assertEquals(1, new finiteStateMachine().count("one:"));
        assertEquals(1, new finiteStateMachine().count(":one"));
        assertEquals(1, new finiteStateMachine().count(":one:"));
        assertEquals(1, new finiteStateMachine().count(" one  "));
        assertEquals(1, new finiteStateMachine().count(" one : "));
        assertEquals(1, new finiteStateMachine().count(": one :"));
        assertEquals(3, new finiteStateMachine().count("ein erster Text"));
        assertEquals(3, new finiteStateMachine().count(" ein  erster   Text      "));
        assertEquals(3, new finiteStateMachine().count("ein:erster.Text"));

        // vielleicht falsch
        assertEquals(1, new finiteStateMachine().count("a"));
        assertEquals(1, new finiteStateMachine().count(" a"));
        assertEquals(1, new finiteStateMachine().count("a "));
        assertEquals(1, new finiteStateMachine().count(" a "));

        // mit html
        assertEquals(1, new finiteStateMachine().count(" one  <html> "));
        assertEquals(1, new finiteStateMachine().count(" one  < html> "));
        assertEquals(1, new finiteStateMachine().count(" one  <html > "));
        assertEquals(1, new finiteStateMachine().count(" one  < html > "));
        assertEquals(4, new finiteStateMachine().count(" one <html> two<html>three <html> four"));

        assertEquals(2, new finiteStateMachine().count(" one <html> two "));
        assertEquals(2, new finiteStateMachine().count(" one <html>two "));
        assertEquals(2, new finiteStateMachine().count(" one<html> two "));
        assertEquals(2, new finiteStateMachine().count(" one<html>two "));
        assertEquals(2, new finiteStateMachine().count(" one<img alt=\"xxx\" > two"));
        assertEquals(2, new finiteStateMachine().count(" one<img alt=\"xxx yyy\" > two"));

        assertEquals(2, new finiteStateMachine().count(" one \"two\" "));
        assertEquals(2, new finiteStateMachine().count(" one\"two\" "));
        assertEquals(2, new finiteStateMachine().count(" one \"two\""));
        assertEquals(3, new finiteStateMachine().count(" one \"two\"three"));
        assertEquals(3, new finiteStateMachine().count(" one \"two\" three"));

        // html - trickreich
        // Achtung: das ist teilweise nicht ganz legales HTML
        assertEquals(1, new finiteStateMachine().count(" one<html")); // kein >

        assertEquals(2, new finiteStateMachine().count(" one<img alt=\"<bild>\" > two")); // <> innerhalb ""
        assertEquals(2, new finiteStateMachine().count(" one<img alt=\"bild>\" > two")); // <> innerhalb ""
        assertEquals(2, new finiteStateMachine().count(" one<img alt=\"<bild>\" keinwort> two"));
        assertEquals(2, new finiteStateMachine().count(" one<img alt=\"<bild>\" src=\"bild.png\" >two"));
        assertEquals(2, new finiteStateMachine().count(" one<img alt=\"<bilFd\" keinwort>two"));

        assertEquals(1, new finiteStateMachine().count(" one<img alt=\"<bild\" keinwort"));
        assertEquals(2, new finiteStateMachine().count(" one<img alt=\"<bild\" keinwort> two"));
        assertEquals(1, new finiteStateMachine().count(" one<img alt=\"<bild keinwort> keinwort"));
        assertEquals(2, new finiteStateMachine().count(" one<img alt=\"<bild keinwort keinwort\">two"));
        assertEquals(2, new finiteStateMachine().count(" one<img alt=\"<bild keinwort< keinwort\">two"));

        // ganz ganz fies -- \ entwertet das nÃ¤chste Zeichen
        assertEquals(2, new finiteStateMachine().count(" one<img alt=\"<bild \\\" keinwort> keinwort\" keinwort>two"));
        assertEquals(2, new finiteStateMachine().count(" one<img alt=\"<bild \\\" keinwort<keinwort\" keinwort>two"));
        assertEquals(2, new finiteStateMachine().count(" one<img alt=\"<bild \\\" keinwort keinwort\" keinwort>two"));

        assertEquals(4, new finiteStateMachine().count(" \\\"null\\\" one<img alt=\"<bild \\\" keinwort keinwort\" keinwort>two \"three\""));
	}

}
