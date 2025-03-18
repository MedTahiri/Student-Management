import json

from django.http import JsonResponse
from django.shortcuts import render
from django.views.decorators.csrf import csrf_exempt

from .models import Student, Note


# Create your views here.
def hello_world(request):
    return JsonResponse({'message': 'Hello from Django Backend!'})

def profiles(request):
    students = Student.objects.all().values()
    return JsonResponse(list(students), safe=False)

@csrf_exempt
def profile(request):
    if request.method == 'POST':
        try:
            student = json.loads(request.body)
            Student.objects.create(**student)
            students = Student.objects.all().values()
            return JsonResponse(list(students), safe=False)
        except Exception as e:
            return JsonResponse({'message': str(e)}, status=500)
    elif request.method == 'PUT':
        try:
            student_data = json.loads(request.body)
            student_id = student_data.get('id')
            student = Student.objects.filter(pk=student_id).first()
            for key, value in student_data.items():
                setattr(student, key, value)
            student.save()
            students = Student.objects.all().values()
            return JsonResponse(list(students), safe=False)
        except Exception as e:
            return JsonResponse({'message': str(e)}, status=500)
    elif request.method == 'DELETE':
        try:
            student_data = json.loads(request.body)
            student_id = student_data.get('id')
            student = Student.objects.filter(pk=student_id).first()
            student.delete()
            students = Student.objects.all().values()
            return JsonResponse(list(students), safe=False)
        except Exception as e:
            return JsonResponse({'message': str(e)}, status=500)
    else:
        return JsonResponse({'error': 'Invalid request method'}, status=405)

def notes(request):
    student_id = request.GET.get('id')
    notes = Note.objects.filter(student_id=student_id).values('id', 'student_id', 'matiere', 'score', 'status')
    return JsonResponse(list(notes), safe=False)

@csrf_exempt
def note(request):
    student_id = request.GET.get('id')
    if request.method == 'POST':
        note_data = json.loads(request.body)
        Note.objects.create(student_id=student_id, **note_data)
        notes = Note.objects.filter(student_id=student_id).values()
        return JsonResponse(list(notes), safe=False)
    elif request.method == 'PUT':
        note_data = json.loads(request.body)
        note_id = note_data.get('id')
        note = Note.objects.filter(pk=note_id).first()
        for key, value in note_data.items():
            setattr(note, key, value)
        note.save()
        notes = Note.objects.filter(student_id=student_id).values()
        return JsonResponse(list(notes), safe=False)
    elif request.method == 'DELETE':
        note_data = json.loads(request.body)
        note_id = note_data.get('id')
        note = Note.objects.filter(pk=note_id).first()
        note.delete()
        notes = Note.objects.filter(student_id=student_id).values()
        return JsonResponse(list(notes), safe=False)
    else:
        return JsonResponse({'error': 'Invalid request method'}, status=405)