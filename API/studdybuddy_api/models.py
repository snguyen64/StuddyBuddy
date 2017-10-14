from django.db import models
from django.contrib.auth.models import User


class Course(models.Model):
    type = models.CharField(max_length=4)
    number = models.IntegerField()
    user = models.ForeignKey(User, on_delete=models.CASCADE)
