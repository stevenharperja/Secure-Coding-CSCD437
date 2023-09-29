from selenium import webdriver
from selenium.webdriver.common.keys import Keys

import time

driver = webdriver.Firefox()
driver.get("https://canvas.ewu.edu/courses/1544426/quizzes/1643987/take?user_id=4036812")
#assert "Python" in driver.title
#elem = driver.find_element_by_name("q")
#elem.clear()
#elem.send_keys("pycon")
#elem.send_keys(Keys.RETURN)
#assert "No results found." not in driver.page_source
#driver.close()

def send_input(text):
	#identify text box
	l = driver.find_element_by_id("quiz_access_code")
	#send input
	l.send_keys(text)
	#send keyboard input
	l.send_keys(Keys.RETURN)

def get_words():
	file1 = open('words.txt','r')
	lines = file1.readlines()
	return lines

def go():
	words = get_words()
	for word in words:
		send_input(word.capitalize())
		time.sleep(2)
	for word in words:
		for word in words:
			send_input(word.capitalize() + word.capitalize())
			time.sleep(2)
