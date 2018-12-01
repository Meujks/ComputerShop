import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class customPanelTest {
	public String formatString(String str) {
		String formatString = str.substring(str.indexOf("-") + 1, str.indexOf("€"));
		formatString = formatString.replaceAll("\\s+", "");
		return formatString;
	}

	@Test
	void testFormatString() {

		String testString = "6gb - 300 €";
		testString = formatString(testString);
		String expectedString = "300";
		assertEquals(testString, expectedString);
	}

}
