from bs4 import BeautifulSoup
from urllib.request import urlopen

# input subject (string) type
subject = 'acct'

quote_page = 'http://www.catalog.gatech.edu/coursesaz/' + subject +'/'

# query the website and return the html to the variable ‘page’
page = urlopen(quote_page)

# parse the html using beautiful soap and store in variable `soup`
soup = BeautifulSoup(page, 'html.parser')

charLength = len(subject)

courseList = []

for course in soup.find_all('p', attrs = {'class': 'courseblocktitle'}):
	
	# strips string and retreives the 4 digit course number
	course = course.text.strip()[charLength: charLength + 5]

	# making sure there are no letters in course number before typecasting
	# adds course to courseList
	if 'X' not in course:
		courseNumber = int (course)
		courseList.append(courseNumber)

	else:
		print("This is a transfer credit course:" + subject + course)

	
# print is for debugging purposes
# print(courseList)
