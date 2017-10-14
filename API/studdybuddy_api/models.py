from django.db import models
from django.contrib.auth.models import User


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
