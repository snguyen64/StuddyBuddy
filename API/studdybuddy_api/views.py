import json

from django.shortcuts import render
from django.contrib.auth.models import User
from django.contrib.auth import authenticate
from django.http import JsonResponse
from django.views.decorators.csrf import csrf_exempt
from bs4 import BeautifulSoup
from urllib.request import urlopen


@csrf_exempt
def register(request):
    body = json.loads(request.body.decode('utf-8'))
    firstName = body["firstName"]
    lastName = body["lastName"]
    username = body["username"]
    password = body["password"]
    email = body["email"]
    user = User.objects.create_user(username, email=email, password=password, first_name=firstName, last_name=lastName)
    return JsonResponse({"success": True, "message": user.id})


@csrf_exempt
def login(request):
    body = json.loads(request.body.decode('utf-8'))
    username = body["username"]
    print(username)
    password = body["password"]
    print(password)
    user = authenticate(username=username, password=password)
    if user is not None:
        return JsonResponse({"success": True, "message": user.id})
    else:
        return JsonResponse({"success": False, "message": "Unable to log user in."})


@csrf_exempt
def courses(request, subject):
    quote_page = 'http://www.catalog.gatech.edu/coursesaz/' + subject + '/'

    # query the website and return the html to the variable ‘page’
    page = urlopen(quote_page)

    # parse the html using beautiful soap and store in variable `soup`
    soup = BeautifulSoup(page, 'html.parser')

    charLength = len(subject)

    courseList = []

    for course in soup.find_all('p', attrs={'class': 'courseblocktitle'}):

        # strips string and retrieves the 4 digit course number
        course = course.text.strip()[charLength: charLength + 5]

        # making sure there are no letters in course number before typecasting
        # adds course to courseList
        if 'X' not in course:
            coursenumber = int(course)
            courseList.append(coursenumber)
        else:
            print("This is a transfer credit course:" + subject + course)
    return JsonResponse(courseList, safe=False)
