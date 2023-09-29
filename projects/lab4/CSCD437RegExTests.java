

import static org.junit.jupiter.api.Assertions.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class CSCD437RegExTests
{
	private ByteArrayOutputStream bos;
	private PrintStream originalOut;

	@BeforeEach
	public void setup()
   {
		originalOut = System.out;
		bos = new ByteArrayOutputStream();
		System.setOut(new PrintStream(bos));
   }// end BeforeEach
	
	@AfterEach
	public void teardown()
    {
		System.setOut(originalOut);
	}// end AfterEach
	
	
	
	@ParameterizedTest
	@ValueSource(strings = {"12-345-6789","123456789", "-111111111"})
	void testEasySSNPatternTrue(String str)
    {
			CSCD437RegExMethods.easySSNPattern(str, System.out);
			assertEquals("The string " + str + " is a valid easySSNPattern", bos.toString().trim());
			bos.reset();
		
	}// end test
	
	@ParameterizedTest
	@ValueSource(strings = {"123/45*6789","112244"})
	void testEasySSNPatternFalse(String str)
    {
			CSCD437RegExMethods.easySSNPattern(str, System.out);
			assertEquals("The string " + str + " is NOT a valid easySSNPattern", bos.toString().trim());
			bos.reset();
		
	}// end test
	
	
	
	@ParameterizedTest
	@ValueSource(strings = {"123-45-6789"})
	void testMediumEasySSNPatternTrue(String str)
    {
			CSCD437RegExMethods.mediumEasySSNPattern(str, System.out);
			assertEquals("The string " + str + " is a valid mediumEasySSNPattern", bos.toString().trim());
			bos.reset();
		
	}// end test
	
	@ParameterizedTest
	@ValueSource(strings = {"12-3456789"})
	void testMediumEasySSNPatternFalse(String str)
    {
			CSCD437RegExMethods.mediumEasySSNPattern(str, System.out);
			assertEquals("The string " + str + " is NOT a valid mediumEasySSNPattern", bos.toString().trim());
			bos.reset();
		
	}// end test
	
	
	
	@ParameterizedTest
	@ValueSource(strings = {"5099997821"})
	void testPhonePatternTrue(String str)
    {
			CSCD437RegExMethods.phonePattern(str, System.out);
			assertEquals("The string " + str + " is a valid phonePattern", bos.toString().trim());
			bos.reset();
		
	}// end test
	
	@ParameterizedTest
	@ValueSource(strings = {"+11385961861"})
	void testPhonePatternFalse(String str)
    {
			CSCD437RegExMethods.phonePattern(str, System.out);
			assertEquals("The string " + str + " is NOT a valid phonePattern", bos.toString().trim());
			bos.reset();
		
	}// end test
	
	
	
}
