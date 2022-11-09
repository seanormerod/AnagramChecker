package test;

import static org.junit.jupiter.api.Assertions.*;
import anagramChecker.AnagramChecker;

import org.junit.*;

public class AnagramCheckerTest {
	public AnagramChecker anagramTestObject = new AnagramChecker();
	
	@Before
	public void setUpBeforeClass() throws Exception {
	}

	@After
	public void tearDownAfterClass() throws Exception {
	}

	@Test
	public void testValidString() {
		boolean validString = anagramTestObject.validStringCheck("abc");
		assertEquals(validString, true);
	}
	@Test
	public void testInvalidStringNumbers() {
		boolean invalidStringNumbers = anagramTestObject.validStringCheck("123");
		assertEquals(invalidStringNumbers, false);
	}
	@Test
	public void testInvalidStringLettersAndNumbers() {
		boolean invalidStringLettersAndNumbers = anagramTestObject.validStringCheck("abc123");
		assertEquals(invalidStringLettersAndNumbers, false);
	}
	@Test
	public void testInvalidStringSpaces() {
		boolean invalidStringSpaces = anagramTestObject.validStringCheck(" ");
		assertEquals(invalidStringSpaces, false);
	}
	@Test
	public void testInvalidStringSpacesNumbers() {
		boolean invalidStringSpacesAndNumbers = anagramTestObject.validStringCheck("1 2");
		assertEquals(invalidStringSpacesAndNumbers, false);
	}
	@Test
	public void testInvalidStringSpecialCharacters() {
		boolean invalidStringNumbers = anagramTestObject.validStringCheck("@[;");
		assertEquals(invalidStringNumbers, false);
	}
	@Test
	public void testAnagramCheckerWithAnagrams() {
		String username = "Sean";
		String word1 = "friend";
		String word2 = "finder";
		String filepath = "C:\\Users\\Sean\\eclipse-workspace\\AnagramChecker\\anagramdata.txt";
		boolean anagramValid = anagramTestObject.anagramChecker(username, word1, word2, filepath);
		assertEquals(anagramValid, true);
	}
	@Test
	public void testAnagramCheckerWithOutAnagrams() {
		String username = "Sean";
		String word1 = "friend";
		String word2 = "enemy";
		String filepath = "C:\\Users\\Sean\\eclipse-workspace\\AnagramChecker\\anagramdata.txt";
		boolean anagramValid = anagramTestObject.anagramChecker(username, word1, word2, filepath);
		assertEquals(anagramValid, false);
	}
	@Test 
	public void testResultsAddedToExternalFile() {
		String username = "Sean";
		String word1 = "friend";
		String word2 = "enemy";
		String filepath = "C:\\Users\\Sean\\eclipse-workspace\\AnagramChecker\\anagramdata.txt";
		anagramTestObject.anagramChecker(username, word1, word2, filepath);
		assertEquals(anagramTestObject.anagramAddedToFile, true);
	}
	@Test
	public void testResultsNotAddedToExternalFile() {
		String username = "Sean";
		String word1 = "123";
		String word2 = "234";
		String filepath = "C:\\Users\\Sean\\eclipse-workspace\\AnagramChecker\\anagramdata.txt";
		anagramTestObject.anagramChecker(username, word1, word2, filepath);
		assertEquals(anagramTestObject.anagramAddedToFile, true);
	}

	@Test
	public void testAnagramPresentInArray() {
		
		String arrayTextS1 = "friend";
		String arrayTextS2 = "finder";
		String arrayText2S1 = "god";
		String arrayText2S2 = "dog";
		String arrayText = "sean,friend,finder,y";
		String arrayText2 = "sean,god,dog,y";
		String[] resultsArray = {arrayText, arrayText2};
		boolean text1Present = anagramTestObject.checkResultsArray(resultsArray,arrayTextS1, arrayTextS2);
		boolean text2Present = anagramTestObject.checkResultsArray(resultsArray,arrayText2S1, arrayText2S2);
		assertEquals(true,text1Present);
		assertEquals(true, text2Present);
	}
	@Test
	public void testAnagramNotPresentInArray() {
		
		String notPresentS1 = "cat";
		String notPresentS2 = "dog";
		String arrayText = "sean,friend,finder,y";
		String arrayText2 = "sean,god,dog,y";
		String[] resultsArray = {arrayText, arrayText2};
		boolean text1Present = anagramTestObject.checkResultsArray(resultsArray,notPresentS1, notPresentS2);
		assertEquals(false,text1Present);

	}
}
