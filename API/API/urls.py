"""API URL Configuration

The `urlpatterns` list routes URLs to views. For more information please see:
    https://docs.djangoproject.com/en/1.11/topics/http/urls/
Examples:
Function views
    1. Add an import:  from my_app import views
    2. Add a URL to urlpatterns:  url(r'^$', views.home, name='home')
Class-based views
    1. Add an import:  from other_app.views import Home
    2. Add a URL to urlpatterns:  url(r'^$', Home.as_view(), name='home')
Including another URLconf
    1. Import the include() function: from django.conf.urls import url, include
    2. Add a URL to urlpatterns:  url(r'^blog/', include('blog.urls'))
"""
from django.conf.urls import url
from django.contrib import admin
from studdybuddy_api import views

urlpatterns = [
    url(r'^admin/', admin.site.urls),
    url(r'^auth/register/$', views.register),
    url(r'^auth/login/$', views.login),
    url(r'^courses/(?P<id>[0-9]+)/$', views.get_courses),
    url(r'^courses/(?P<subject>\w+)/$', views.courses),
    url(r'^course/(?P<id>[0-9]+)/store/$', views.store_course),
    url(r'^course/(?P<id>[0-9]+)/delete/(?P<subject>\w+)/(?P<number>[0-9]+)/$', views.delete_course),
    url(r'^chatroom/create/(?P<id>[0-9]+)/$', views.create_chatroom),
    url(r'^chatroom/(?P<name>\w+)/join/(?P<id>[0-9]+)/$', views.join_chatroom),
    url(r'^chatroom/list/$', views.get_chatrooms),
    url(r'^chatroom/messages/(?P<name>\w+)/$', views.get_messages)
]
