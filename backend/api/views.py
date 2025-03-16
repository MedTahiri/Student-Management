from django.http import JsonResponse
from django.shortcuts import render

students = [
        {
            'id': 1,
            'firstname': 'Alice',
            'lastname': 'Smith',
            'image': 'alice.jpg',
            'email': 'alice.smith@example.com',
            'phone': '123-456-7890',
            'class': 'Mathematics',
            'remarque': 'Excellent problem-solving skills.',
            'note-id': 101
        },
        {
            'id': 2,
            'firstname': 'Bob',
            'lastname': 'Johnson',
            'image': 'bob.jpg',
            'email': 'bob.johnson@example.com',
            'phone': '234-567-8901',
            'class': 'Physics',
            'remarque': 'Needs improvement in lab work.',
            'note-id': 102
        },
        {
            'id': 3,
            'firstname': 'Charlie',
            'lastname': 'Brown',
            'image': 'charlie.jpg',
            'email': 'charlie.brown@example.com',
            'phone': '345-678-9012',
            'class': 'Chemistry',
            'remarque': 'Very creative in experiments.',
            'note-id': 103
        },
        {
            'id': 4,
            'firstname': 'David',
            'lastname': 'Williams',
            'image': 'david.jpg',
            'email': 'david.williams@example.com',
            'phone': '456-789-0123',
            'class': 'Biology',
            'remarque': 'Consistently performs well.',
            'note-id': 104
        },
        {
            'id': 5,
            'firstname': 'Eva',
            'lastname': 'Jones',
            'image': 'eva.jpg',
            'email': 'eva.jones@example.com',
            'phone': '567-890-1234',
            'class': 'History',
            'remarque': 'Needs to participate more in discussions.',
            'note-id': 105
        },
        {
            'id': 6,
            'firstname': 'Frank',
            'lastname': 'Garcia',
            'image': 'frank.jpg',
            'email': 'frank.garcia@example.com',
            'phone': '678-901-2345',
            'class': 'Geography',
            'remarque': 'Excellent map-reading skills.',
            'note-id': 106
        },
        {
            'id': 7,
            'firstname': 'Grace',
            'lastname': 'Martinez',
            'image': 'grace.jpg',
            'email': 'grace.martinez@example.com',
            'phone': '789-012-3456',
            'class': 'Literature',
            'remarque': 'Very imaginative in writing.',
            'note-id': 107
        },
        {
            'id': 8,
            'firstname': 'Henry',
            'lastname': 'Rodriguez',
            'image': 'henry.jpg',
            'email': 'henry.rodriguez@example.com',
            'phone': '890-123-4567',
            'class': 'Computer Science',
            'remarque': 'Excellent coding skills.',
            'note-id': 108
        },
        {
            'id': 9,
            'firstname': 'Ivy',
            'lastname': 'Lee',
            'image': 'ivy.jpg',
            'email': 'ivy.lee@example.com',
            'phone': '901-234-5678',
            'class': 'Art',
            'remarque': 'Very talented in painting.',
            'note-id': 109
        },
        {
            'id': 10,
            'firstname': 'Jack',
            'lastname': 'Taylor',
            'image': 'jack.jpg',
            'email': 'jack.taylor@example.com',
            'phone': '012-345-6789',
            'class': 'Music',
            'remarque': 'Great at playing the piano.',
            'note-id': 110
        }
    ]

# Create your views here.
def hello_world(request):
    return JsonResponse({'message': 'Hello from Django Backend!'})

def profiles(request):
    return JsonResponse(students, safe=False)

def profile(request):
    student_id = request.GET.get('id')
    student = None
    for i in students:
        if i['id'] == int(student_id):
            student = i
            break
    if not student:
        student = {'message': f'Student with id {student_id} is not found'}
    return JsonResponse(student, safe=False)