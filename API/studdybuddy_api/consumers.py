import json

from channels import Group
from channels.sessions import channel_session
from django.contrib.auth.models import User

from .models import Room, Message


@channel_session
def ws_connect(message):
    prefix, name = message['path'].strip('/').split('/')
    room = Room.objects.get(name=name)
    Group('chat-' + name).add(message.reply_channel)
    message.channel_session['room'] = room.name


@channel_session
def ws_receive(message):
    name = message.channel_session['room']
    room = Room.objects.get(name=name)
    data = json.loads(message['text'])
    user = User.objects.get(id=int(message['id']))
    mess = Message.objects.create(user=user, room=room, text=data)
    Group('chat-' + name).send({'id': mess.user.id, 'text': mess.data})


@channel_session
def ws_disconnect(message):
    name = message.channel_session['room']
    Group('chat-' + name).discard(message.reply_channel)
