import json

from django.shortcuts import render
from django.contrib.auth.models import User
from django.contrib.auth import authenticate
from django.http import JsonResponse


# Create your views here.
def register(request):
    body = json.loads(request.body.decode('utf-8'))
    firstName = body["firstName"]
    lastName = body["lastName"]
    username = body["username"]
    password = body["password"]
    email = body["email"]
    user = User.objects.create_user(username, email=email, password=password, first_name=firstName, last_name=lastName)
    user.save()
    return JsonResponse({"success": True, "message": "Successfully registered user."})


def login(request):
    body = json.loads(request.body.decode('utf-8'))
    username = body["username"]
    password = body["password"]
    user = authenticate(username=username, password=password)
    if user is not None:
        return JsonResponse({"success": True, "message": user.id})
    else:
        return JsonResponse({"success": False, "message": "Unable to log user in."})
