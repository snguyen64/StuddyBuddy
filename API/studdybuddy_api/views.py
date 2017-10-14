import json

from django.shortcuts import render
from django.contrib.auth.models import User
from django.contrib.auth import authenticate
from django.http import JsonResponse, HttpResponse
from django.views.decorators.csrf import csrf_exempt
from bs4 import BeautifulSoup
from urllib.request import urlopen
from studdybuddy_api.models import Course, Room, Message
from django.core.exceptions import ObjectDoesNotExist


@csrf_exempt
def register(request):
    body = json.loads(request.body.decode('utf-8'))
    firstName = body["firstName"]
    lastName = body["lastName"]
    username = body["username"]
    password = body["password"]
    email = body["email"]
    try:
        olduser = User.objects.get_by_natural_key(username)
        if olduser is not None:
            return JsonResponse({"success": False, "message": "Username already taken."})
    except ObjectDoesNotExist:
        user = User.objects.create_user(username, email=email, password=password, first_name=firstName,
                                        last_name=lastName)
        print(user.id)
        return JsonResponse({"success": True, "message": user.id})


@csrf_exempt
def login(request):
    body = json.loads(request.body.decode('utf-8'))
    username = body["username"]
    password = body["password"]
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
    return JsonResponse(courseList, safe=False)


@csrf_exempt
def store_course(request, id):
    body = json.loads(request.body.decode('utf-8'))
    user = User.objects.get(id=id)
    Course.objects.create(field=body["courseType"], number=body["courseNumber"], user=user)
    return JsonResponse({"success": True, "message": "Stored course."})


@csrf_exempt
def delete_course(request, id, subject, number):
    user = User.objects.get(id=id)
    Course.objects.get(field=str(subject).upper(), number=number, user=user).delete()
    return JsonResponse({"success": True, "message": "Deleted course."})


@csrf_exempt
def get_courses(request, id):
    user = User.objects.get(id=id)
    courseslist = [c.as_json() for c in list(Course.objects.filter(user=user).all())]
    return HttpResponse(json.dumps(courseslist), content_type="application/json")

@csrf_exempt
def create_chatroom(request, id):
    user = User.objects.get(id=id)
    body = json.loads(request.body.decode('utf-8'))
    try:
        oldroom = Room.objects.get(name=body['name'], course=body['course'])
        return JsonResponse({"success": False, "message": "Room name already taken."})
    except ObjectDoesNotExist:
        chatroom = Room.objects.create(name=body['name'], course=body['course'])
        chatroom.users.add(user)
        return JsonResponse({"success": True, "message": "Added to chatroom."})

@csrf_exempt
def join_chatroom(request, name, id):
    user = User.objects.get(id=id)
    Room.objects.get(name=name).users.add(user)
    return JsonResponse({"success": True, "message": "Joined chatroom."})

@csrf_exempt
def get_chatrooms(request):
    body = json.loads(request.body.decode('utf-8'))
    chatrooms = [cr.as_json() for cr in list(Room.objects.filter(course=body).all())]
    print(json.dumps(chatrooms))
    return HttpResponse(json.dumps(chatrooms), content_type="application/json")
