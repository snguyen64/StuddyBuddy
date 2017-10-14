from django.db import models
from django.contrib.auth.models import User
from jsonfield import JSONField
from django.utils.timezone import now


class Course(models.Model):
    field = models.CharField(max_length=4)
    number = models.IntegerField()
    user = models.ForeignKey(User)

    def as_json(self):
        return dict(
            courseType=self.field,
            courseNumber=self.number
        )

    class Meta:
        unique_together = ("field", "number", "user")


class Room(models.Model):
    name = models.CharField(max_length=255, unique=True)
    course = JSONField()
    users = models.ManyToManyField(User)

    def as_json(self):
        return dict(
            name=self.name,
            course=self.course,
            usersList=list(self.users.all().values('id', 'username'))
        )


class Message(models.Model):
    user = models.ForeignKey(User)
    timestamp = models.DateTimeField(now())
    room = models.ForeignKey(Room)
    text = models.TextField()

    def as_json(self):
        return dict(
            username=self.user.values('username'),
            message=self.text,
            timestamp=self.timestamp
        )
