import org.junit.Test;
import static org.junit.Assert.*;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UE01_Junit {

	public static void main(String[] args) {
		System.out.println("Helo");
	}
	@Test
	public void testEmptyString() {

		// leicht
		assertEquals(0, count(""));
		assertEquals(0, count(" "));
		assertEquals(0, count("  "));

		// normal
		assertEquals(1, count("one"));
		assertEquals(1, count(" one"));
		assertEquals(1, count("one "));
		assertEquals(1, count(" one "));
		assertEquals(1, count("  one  "));
		assertEquals(1, count("  one "));
		assertEquals(1, count("  one  "));

		// vielleicht falsch
		assertEquals(1, count(" a "));
		assertEquals(1, count("a "));
		assertEquals(1, count(" a"));
		assertEquals(1, count("a"));
		assertEquals(1, count(" a "));

		//mit html
		assertEquals(1, count(" one  <html> "));
		assertEquals(1, count(" one  < html> "));
		assertEquals(1, count(" one  <html > "));
		assertEquals(1, count(" one  < html > "));
		assertEquals(4, count(" one <html> two<html>three <html> four"));
		assertEquals(2, count(" one <html> two "));
		assertEquals(2, count(" one <html>two "));
		assertEquals(2, count(" one<html> two "));
		assertEquals(2, count(" one<html>two "));
		assertEquals(2, count(" one<img alt=\"xxx\" > two"));
		assertEquals(2, count(" one<img alt=\"xxx yyy\" > two"));
		assertEquals(2, count(" one \"two\" "));
		assertEquals(2, count(" one\"two\" "));
		assertEquals(2, count(" one \"two\""));
		assertEquals(3, count(" one \"two\"three"));
		assertEquals(3, count(" one \"two\" three"));


	}
	public static int count(String text) {

		int wordCount = 0;
		Pattern pattern_tag = Pattern.compile("<.+?>");
		Matcher matcher_tag = pattern_tag.matcher(text);
		text = matcher_tag.replaceAll(" ");



		Pattern patttern_word = Pattern.compile("\\w+");
		Matcher matcher_word = patttern_word.matcher(text);

		while (matcher_word.find()) wordCount++;

		return wordCount;
	}


}
