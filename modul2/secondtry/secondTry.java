package secondtry;

import org.junit.*

class secondTry {

	/**
	* Test method for testing the FSM.
	*/
	@Test
	void test() {

		// leicht
        assertEquals(0, new BezahlungFSM().count(""));
        assertEquals(0, new BezahlungFSM().count(" "));
        assertEquals(0, new BezahlungFSM().count("  "));

        // normal
        assertEquals(1, new BezahlungFSM().count("one"));
        assertEquals(1, new BezahlungFSM().count(" one"));
        assertEquals(1, new BezahlungFSM().count("one "));
        assertEquals(1, new BezahlungFSM().count(" one "));
        assertEquals(1, new BezahlungFSM().count(" one  "));
        assertEquals(1, new BezahlungFSM().count("  one "));
        assertEquals(1, new BezahlungFSM().count("  one  "));

        assertEquals(1, new BezahlungFSM().count("one:"));
        assertEquals(1, new BezahlungFSM().count(":one"));
        assertEquals(1, new BezahlungFSM().count(":one:"));
        assertEquals(1, new BezahlungFSM().count(" one  "));
        assertEquals(1, new BezahlungFSM().count(" one : "));
        assertEquals(1, new BezahlungFSM().count(": one :"));
        assertEquals(3, new BezahlungFSM().count("ein erster Text"));
        assertEquals(3, new BezahlungFSM().count(" ein  erster   Text      "));
        assertEquals(3, new BezahlungFSM().count("ein:erster.Text"));

        // vielleicht falsch
        assertEquals(1, new BezahlungFSM().count("a"));
        assertEquals(1, new BezahlungFSM().count(" a"));
        assertEquals(1, new BezahlungFSM().count("a "));
        assertEquals(1, new BezahlungFSM().count(" a "));

        // mit html
        assertEquals(1, new BezahlungFSM().count(" one  <html> "));
        assertEquals(1, new BezahlungFSM().count(" one  < html> "));
        assertEquals(1, new BezahlungFSM().count(" one  <html > "));
        assertEquals(1, new BezahlungFSM().count(" one  < html > "));
        assertEquals(4, new BezahlungFSM().count(" one <html> two<html>three <html> four"));

        assertEquals(2, new BezahlungFSM().count(" one <html> two "));
        assertEquals(2, new BezahlungFSM().count(" one <html>two "));
        assertEquals(2, new BezahlungFSM().count(" one<html> two "));
        assertEquals(2, new BezahlungFSM().count(" one<html>two "));
        assertEquals(2, new BezahlungFSM().count(" one<img alt=\"xxx\" > two"));
        assertEquals(2, new BezahlungFSM().count(" one<img alt=\"xxx yyy\" > two"));

        assertEquals(2, new BezahlungFSM().count(" one \"two\" "));
        assertEquals(2, new BezahlungFSM().count(" one\"two\" "));
        assertEquals(2, new BezahlungFSM().count(" one \"two\""));
        assertEquals(3, new BezahlungFSM().count(" one \"two\"three"));
        assertEquals(3, new BezahlungFSM().count(" one \"two\" three"));

        // html - trickreich
        // Achtung: das ist teilweise nicht ganz legales HTML
        assertEquals(1, new BezahlungFSM().count(" one<html")); // kein >

        assertEquals(2, new BezahlungFSM().count(" one<img alt=\"<bild>\" > two")); // <> innerhalb ""
        assertEquals(2, new BezahlungFSM().count(" one<img alt=\"bild>\" > two")); // <> innerhalb ""
        assertEquals(2, new BezahlungFSM().count(" one<img alt=\"<bild>\" keinwort> two"));
        assertEquals(2, new BezahlungFSM().count(" one<img alt=\"<bild>\" src=\"bild.png\" >two"));
        assertEquals(2, new BezahlungFSM().count(" one<img alt=\"<bilFd\" keinwort>two"));

        assertEquals(1, new BezahlungFSM().count(" one<img alt=\"<bild\" keinwort"));
        assertEquals(2, new BezahlungFSM().count(" one<img alt=\"<bild\" keinwort> two"));
        assertEquals(1, new BezahlungFSM().count(" one<img alt=\"<bild keinwort> keinwort"));
        assertEquals(2, new BezahlungFSM().count(" one<img alt=\"<bild keinwort keinwort\">two"));
        assertEquals(2, new BezahlungFSM().count(" one<img alt=\"<bild keinwort< keinwort\">two"));

        // ganz ganz fies -- \ entwertet das nÃ¤chste Zeichen
        assertEquals(2, new BezahlungFSM().count(" one<img alt=\"<bild \\\" keinwort> keinwort\" keinwort>two"));
        assertEquals(2, new BezahlungFSM().count(" one<img alt=\"<bild \\\" keinwort<keinwort\" keinwort>two"));
        assertEquals(2, new BezahlungFSM().count(" one<img alt=\"<bild \\\" keinwort keinwort\" keinwort>two"));

        assertEquals(4, new BezahlungFSM().count(" \\\"null\\\" one<img alt=\"<bild \\\" keinwort keinwort\" keinwort>two \"three\""));
	}

}
