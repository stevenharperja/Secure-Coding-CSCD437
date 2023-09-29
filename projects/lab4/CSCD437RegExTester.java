import static org.junit.jupiter.api.Assertions.*;

import java.io.*;

import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class CSCD437RegExTester
{
	private ByteArrayOutputStream bos;
	private PrintStream originalOut;

	@BeforeEach
	public void setup()
	{
		originalOut = System.out;
		bos = new ByteArrayOutputStream();
		System.setOut(new PrintStream(bos));
	}

	@AfterEach
	public void teardown()
	{
		System.setOut(originalOut);
	}

	@ParameterizedTest
	@ValueSource(strings =
	{
		"123456789",
		"123-45-6789",
		"123 45 6789",
		"1--2--3--4--5--6--7--8--9",
		"123	45	6789",
		"000000000",
		"111111111",
		"222222222",
		"333333333",
		"444444444",
		"555555555",
		"666666666",
		"777777777",
		"888888888",
		"999999999",
		"00-0000000",
		"-111111111",
		"222222222-",
		"3--33333333",
		"4 44444 444",
		"  555555555",
		"666666666  ",
		"77-77  777 77",
		"-8-8-8-8-8-8-8-8-8-",
		"--9  9999999 9--"
	})
	void testEasySSNPatternValid(String str)
	{
		CSCD437RegExMethods.easySSNPattern(str, System.out);
		assertEquals("The string " + str + " is a valid easySSNPattern", bos.toString().trim());

	}

	@ParameterizedTest
	@ValueSource(strings =
	{
		"123/45*6789",
		"123456789123",
		"112244",
		"abcdefghi",
		"000.00.0000",
		"00-0.00.00-00",
		"?????????",
		"0",
		"---------",
		"01234567",
		"0123456789",
		"!123456789!"
	})
	void testEasySSNPatternInvalid(String str)
	{
		CSCD437RegExMethods.easySSNPattern(str, System.out);
		assertEquals("The string " + str + " is NOT a valid easySSNPattern", bos.toString().trim());

	}

	@ParameterizedTest
	@ValueSource(strings =
	{
		"123456789",
		"123-45-6789",
		"123 45 6789",
		"123-45 6789",
		"123 45-6789",
		"12345-6789",
		"000000000",
		"111111111",
		"222222222",
		"333333333",
		"444444444",
		"555555555",
		"666666666",
		"777777777",
		"888888888",
		"999999999"
	})
	void testMediumEasySSNPatternValid(String str)
	{
		CSCD437RegExMethods.mediumEasySSNPattern(str, System.out);
		assertEquals("The string " + str + " is a valid mediumEasySSNPattern", bos.toString().trim());

	}

	@ParameterizedTest
	@ValueSource(strings =
	{
		"12-345-6789",
		"123-45--6789",
		"123--45-6789",
		"123.45.6789",
		"666666666  ",
		"abcdefghi",
		"000.00.0000",
		"00-0.00.00-00",
		"?????????",
		"0",
		"---------",
		"01234567",
		"0123456789",
		"-123456789-"
	})
	void testMediumEasySSNPatternInvalid(String str)
	{
		CSCD437RegExMethods.mediumEasySSNPattern(str, System.out);
		assertEquals("The string " + str + " is NOT a valid mediumEasySSNPattern", bos.toString().trim());

	}

	@ParameterizedTest
	@ValueSource(strings =
	{
		"831-26-6969",
		"732-68-6666",
		"732 08 8338",
		"789456783",
		"899468964",
		"687-65-1989",
		"111111111",
		"202222222",
		"633333333",
		"664444444",
		"555555555",
		"777777777",
		"888888888",
		"123456789",
		"123-45-6789",
		"123 45 6789",
		"123-45 6789",
		"123 45-6789",
		"12345-6789"
	})
	void testNewSSNPatternValid(String str)
	{
		CSCD437RegExMethods.newSSNPattern(str, System.out);
		assertEquals("The string " + str + " is a valid newSSNPattern", bos.toString().trim());

	}

	@ParameterizedTest
	@ValueSource(strings =
	{
		"000-26-6969",
		"900-65-9871",
		"831-00-4688",
		"831-00-0000",
		"666-66-6666",
		"66-798-6866",
		"732-68-66668",
		"6/356068684",
		"982922383",
		"982-92-2383",
		"000000000",
		"666666666",
		"999999999",
		"abcdefghi",
		"111.11.1111",
		"111-11.00-00",
		"?????????",
		"1",
		"---------",
		"12345678",
		"1234567890",
		"12-345-6789",
		"123--45-6789",
		"123.45.6789",
		"-123456789-",
		"923-45-6789",
		"666-45-6789",
		"000-45-6789",
		"123-00-6789",
		"123-45-0000",
		"923456789",
		"666456789",
		"000456789",
		"123006789",
		"123450000"
	})
	void testNewSSNPatternInvalid(String str)
	{
		CSCD437RegExMethods.newSSNPattern(str, System.out);
		assertEquals("The string " + str + " is NOT a valid newSSNPattern", bos.toString().trim());

	}

	@ParameterizedTest
	@ValueSource(strings =
	{
		"1234567890",
		"(123)4567890",
		"(123)456-7890",
		"123-456-7890",
		"+1 1234567890",
		"+1 (123)4567890",
		"+1 (123)456-7890",
		"+1 123-456-7890",
		"+1 (123)4567890",
		"+1 (123)456-7890",
		"123-4567890",
		"123456-7890",
		"+1 1388689356",
		"(509)6861381",
		"(509) 686-6813",
		"+1 (509) 686-6831",
		"+1 (509)-686-6831",
		"+1 1234567988"
	})
	void testPhonePatternValid(String str)
	{
		CSCD437RegExMethods.phonePattern(str, System.out);
		assertEquals("The string " + str + " is a valid phonePattern", bos.toString().trim());

	}

	@ParameterizedTest
	@ValueSource(strings =
	{
		"+11234567890",
		"123456789",
		"1",
		"123456789012345678",
		"abcdefghij",
		"!!!!!!!!!!",
		"123.456.7890",
		"+1 (123-456-7890",
		"+1(123)4567890",
		"+1(123)456-7890",
		"+1123-456-7890",
		"+1 123)-456-7890",
		"11234567890",
		"123)4567890",
		"(1234567890",
		"+11385961861",
		"+1 138 186 1357",
		"+1(509) 686-6831",
		"+2 (509)-686-6831"
	})
	void testPhonePatternInvalid(String str)
	{
		CSCD437RegExMethods.phonePattern(str, System.out);
		assertEquals("The string " + str + " is NOT a valid phonePattern", bos.toString().trim());

	}

	@ParameterizedTest
	@ValueSource(strings =
	{
		"dcox1456@ewu.edu",
		"ssteiner@ewu.edu",
		"hello.world@hello.com",
		"HI.HOW.ARE.YOU@weLoveTheWorld.com",
		"jacob-watkins999@yahoo.com",
		"hargasleen99087.wohoo@eagles.ewu.edu",
		"s@ewu.edu",
		"ssteiner@wa.gov"
	})
	void testEmailPatternValid(String str)
	{
		CSCD437RegExMethods.emailPattern(str, System.out);
		assertEquals("The string " + str + " is a valid emailPattern", bos.toString().trim());

	}

	@ParameterizedTest
	@ValueSource(strings =
	{
		"s.steiner.@ewu.edu",
		"s..steiner@ewu.edu",
		"s@steiner@ewu.edu",
		".@ewu.edu",
		"ssteiner",
		"ssteiner@",
		"invalid.@invalid.com",
		"a@b.c",
		"@ewu.edu",
		"ssteiner@ewu",
		"ssteiner@",
		"ssteiner@ewu.edu/hello",
		"ssteiner@w1.gov",
		"ssteiner@w-.gov"
	})
	void testEmailPatternInvalid(String str)
	{
		CSCD437RegExMethods.emailPattern(str, System.out);
		assertEquals("The string " + str + " is NOT a valid emailPattern", bos.toString().trim());

	}

	@ParameterizedTest
	@ValueSource(strings =
	{
		"cost, david",
		"GiLs, HarRRy",
		"WATERTON, JACOB",
		"joe-war, hil, hi",
		"how-do, you-do, friend",
		"how's, this, though",
		"names can, have spaces",
		"even middle, names, can have spaces"
	})
	void testNamePatternValid(String str)
	{
		CSCD437RegExMethods.namePattern(str, System.out);
		assertEquals("The string " + str + " is a valid namePattern", bos.toString().trim());

	}

	@ParameterizedTest
	@ValueSource(strings =
	{
		"joe",
		"not,here",
		"how, do,",
		"how-do, you-do, friend?",
		"2ha, numbers, don't work",
		"'not, at, the start",
		"no--t, two, in a row",
		"  no spaces, at, start"
	})
	void testNamePatternInvalid(String str)
	{
		CSCD437RegExMethods.namePattern(str, System.out);
		assertEquals("The string " + str + " is NOT a valid namePattern", bos.toString().trim());

	}

	@ParameterizedTest
	@ValueSource(strings =
	{
		"02-28-1987",
		"06-24-1896",
		"02/29/2004",
		"02/28/1987",
		"4/4/1932",
		"6/02/1983",
		"01/01/0001",
		"2/29/2000"
	})
	void testDatePatternValid(String str)
	{
		CSCD437RegExMethods.datePattern(str, System.out);
		assertEquals("The string " + str + " is a valid datePattern", bos.toString().trim());

	}

	@ParameterizedTest
	@ValueSource(strings =
	{
		"10-40-1358",
		"02-28/1987",
		"02/28-1987",
		"02-28 1987",
		"01 / 01 / 2001",
		"10 01 2001",
		"15-12-13598",
		"02-29-1987",
		"4/31/1383",
		"13/13/2000",
		"6-02/1983",
		"01/01-2000",
		"02/29/2015",
		"02/29/1700",
		"2/29/1800",
		"2/21/abcd"

	})
	void testDatePatternInvalid(String str)
	{
		CSCD437RegExMethods.datePattern(str, System.out);
		assertEquals("The string " + str + " is NOT a valid datePattern", bos.toString().trim());

	}

	@ParameterizedTest
	@ValueSource(strings =
	{
		"123 Help st",
		"123-456 Help st",
		"123 - 456 Help st",
		"15558 lettuce drive",
		"168613 Longest Address Name In The World street",
		"123 1st street",
		"123 Help street",
		"123 Help rd",
		"123 Help blvd",
		"123 Help ave",
		"123 Help road",
		"123 Help street",
		"123 Help boulevard",
		"123 Help avenue",
		"123 Help drive",
		"123 Help lane",
		"123 Help court",
		"123 Help place",
		"123 Help terrace",
		"123 Help way",
		"123 Help Test way",

	})
	void testAddressPatternValid(String str)
	{
		CSCD437RegExMethods.addressPattern(str, System.out);
		assertEquals("The string " + str + " is a valid addressPattern", bos.toString().trim());

	}

	@ParameterizedTest
	@ValueSource(strings =
	{
		"aaahhhh st 21",
		"this should not work rd",
		"123 te2t street",
		"123,456 Help st",
		"123 456 Help st",
		"-123 Help St",
		"123--456 Help St",
		"  123-456 Help St",
		"123 H@lp st",
		"123 Help thing",
		"123 Help-Test street",
	})
	void testAddressPatternInvalid(String str)
	{
		CSCD437RegExMethods.addressPattern(str, System.out);
		assertEquals("The string " + str + " is NOT a valid addressPattern", bos.toString().trim());

	}

	@ParameterizedTest
	@ValueSource(strings =
	{
		"Spokane WA 99217",
		"Cheney, WA 90082",
		"Cheney WA 12345",
		"spokane, WA 99217",
		"Dover Foxcroft, MN 99999"
	})
	void testCityStateZipPatternValid(String str)
	{
		CSCD437RegExMethods.cityStateZipPattern(str, System.out);
		assertEquals("The string " + str + " is a valid cityStateZipPattern", bos.toString().trim());

	}

	@ParameterizedTest
	@ValueSource(strings =
	{
		"Spokane WA",
		"spokane Washington 99217",
		"WA 99000",
		"Cheney WA 12",
		"Cheney WA 1234567",
		"Cheney 12345",
		"Spokane, wa 99289",
		"Spokane, WQ 99224",
		"Dover-Foxcroft, MN 99999",
		"Spokane  WA 99217",
		" Spokane WA 99217",
		"Spokane WA 99217 ",
		"Spokane WA 992177",
		"Spok@ne, WA 99223"
	})
	void testCityStateZipPatternInvalid(String str)
	{
		CSCD437RegExMethods.cityStateZipPattern(str, System.out);
		assertEquals("The string " + str + " is NOT a valid cityStateZipPattern", bos.toString().trim());

	}

	@ParameterizedTest
	@ValueSource(strings =
	{
		"23:59:59",
		"00:00:00",
		"15:45:00"
	})
	void testMilitaryTimePatternValid(String str)
	{
		CSCD437RegExMethods.militaryTimePattern(str, System.out);
		assertEquals("The string " + str + " is a valid militaryTimePattern", bos.toString().trim());

	}

	@ParameterizedTest
	@ValueSource(strings =
	{
		"235964",
		"24:00:00",
		"240000",
		"000000",
		"12:45 PM",
		"2359:59",
		"ab:cd:ef"
	})
	void testMilitaryTimePatternInvalid(String str)
	{
		CSCD437RegExMethods.militaryTimePattern(str, System.out);
		assertEquals("The string " + str + " is NOT a valid militaryTimePattern", bos.toString().trim());

	}

	@ParameterizedTest
	@ValueSource(strings =
	{
		"$123.20",
		"$1.23",
		"$0.23",
		"$0.00",
		"$1,234.56",
		"$15,686.63",
		"$123,456,789,123,123.00",
		"-$123,456.00"

	})

	void testMoneyPatternValid(String str)
	{
		CSCD437RegExMethods.moneyPattern(str, System.out);
		assertEquals("The string " + str + " is a valid moneyPattern", bos.toString().trim());

	}

	@ParameterizedTest
	@ValueSource(strings =
	{
		"123.20",
		"15686.63",
		"123,456.00",
		"$156",
		"$.23",
		"123,456.00",
		"$123456.00",
		"$123,456.7",
		"@123,456.78",
		"$abd,def.gh",
		"$123 456 78"

	})

	void testMoneyPatternInvalid(String str)
	{
		CSCD437RegExMethods.moneyPattern(str, System.out);
		assertEquals("The string " + str + " is NOT a valid moneyPattern", bos.toString().trim());

	}

	@ParameterizedTest
	@ValueSource(strings =
	{
		"www.google.com",
		"www.ewu.edu",
		"www.eagles.ewu.edu",
		"https://www.eagles.ewu.edu",
		"HTTP://www.wa.gov",
		"http://www.wa.gov",
		"HTTPS://www.eagles.ewu.edu",
		"www.google.biz",
		"ww.gg.ee.ee"
	})
	void testUrlPatternValid(String str)
	{
		CSCD437RegExMethods.urlPattern(str, System.out);
		assertEquals("The string " + str + " is a valid urlPattern", bos.toString().trim());

	}

	@ParameterizedTest
	@ValueSource(strings =
	{
		".eagles.ewu.edu",
		"w.google.com",
		"google.com",
		".com",
		"com",
		"www.ewu.e1.e2",
		"www.ewu.e",
		"w1.g1.e1.e1",
		"w.g.e.e"
	})
	void testUrlPatternInvalid(String str)
	{
		CSCD437RegExMethods.urlPattern(str, System.out);
		assertEquals("The string " + str + " is NOT a valid urlPattern", bos.toString().trim());

	}

	@ParameterizedTest
	@ValueSource(strings =
	{
		"asdFILNE1!",
		"NUlelIan21n!8nbDua",
		"aaaAAA!!11",
		"aaaAAA++22",
		"aaaAAA@@33",
		"aaaAAA<<44",
		"aaaAAA$$55",
		"OndIs9dni?siNn8"
	})
	void testPasswordPatternValid(String str)
	{
		CSCD437RegExMethods.passwordPattern(str, System.out);
		assertEquals("The string " + str + " is a valid passwordPattern", bos.toString().trim());

	}

	@ParameterizedTest
	@ValueSource(strings =
	{
		"aaaAAA!!!!",
		"aaaAAA1111",
		"aaa111!!!!",
		"111AAA!!!!",
		"aaaAAA####",
		"aaaAAA++++",
		"aaaaAAA!!1",
	})
	void testPasswordPatternInvalid(String str)
	{
		CSCD437RegExMethods.passwordPattern(str, System.out);
		assertEquals("The string " + str + " is NOT a valid passwordPattern", bos.toString().trim());

	}

	@ParameterizedTest
	@ValueSource(strings =
	{
		"ion",
		"union",
		"onion",
		"scion",
		"prion",
		"axion",
		"anion",
		"inion",
		"avion",
		"million",
		"section",
		"billion",
		"version",
		"station",
		"mission",
		"opinion",
		"education",
		"operation",
		"attention",
		"application",
		"information",
		"association",
		"communication",
		"consideration",
		"differentiation",
		"dissatisfaction",
		"excommunication",
		"incomprehension"
	})
	void testOddIonPatternValid(String str)
	{
		CSCD437RegExMethods.oddIonPattern(str, System.out);
		assertEquals("The string " + str + " is a valid oddIonPattern", bos.toString().trim());

	}

	@ParameterizedTest
	@ValueSource(strings =
	{
		"aon",
		"lion",
		"pion",
		"cion",
		"action",
		"region",
		"nation",
		"option",
		"vision",
		"motion",
		"position",
		"decision",
		"question",
		"production",
		"commission",
		"television",
		"distribution",
		"organization",
		"construction",
		"administration",
		"!!ion",
		"123456ion",
		"    ion",
		"@@@@@@@@@@@@@@@@@@@@ion",
		"aaaaaaaaa"
	})
	void testOddIonPatternInvalid(String str)
	{
		CSCD437RegExMethods.oddIonPattern(str, System.out);
		assertEquals("The string " + str + " is NOT a valid oddIonPattern", bos.toString().trim());

	}
}
