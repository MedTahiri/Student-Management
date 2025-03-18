from django.db import models

class Student(models.Model):
    # id = models.AutoField(primary_key=True)
    firstname = models.CharField(max_length=100)
    lastname = models.CharField(max_length=100)
    image = models.TextField()
    email = models.EmailField(max_length=255, unique=True)
    phone = models.CharField(max_length=15)
    class_name = models.CharField(max_length=100)
    remarque = models.TextField(blank=True, null=True)
    #note_id = models.IntegerField()

    class Meta:
        db_table = 'student'

    def __str__(self):
        return f"{self.firstname} {self.lastname}"


class Note(models.Model):
    # id = models.AutoField(primary_key=True)
    student = models.ForeignKey(Student, on_delete=models.CASCADE)
    matiere = models.CharField(max_length=100)
    score = models.IntegerField()
    status = models.BooleanField()

    class Meta:
        db_table = 'note'

    def __str__(self):
        return f"{self.student} {self.matiere}"

