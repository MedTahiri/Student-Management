CREATE TABLE student(
    id SERIAL PRIMARY KEY,  -- Auto-incrementing primary key
    firstname TEXT NOT NULL,
    lastname TEXT NOT NULL,
    image TEXT,
    email TEXT UNIQUE NOT NULL,
    phone TEXT NOT NULL,
    class_name TEXT,  -- Fixed column name
    remarque TEXT
);

INSERT INTO student (firstname, lastname, image, email, phone, class_name, remarque)
VALUES
( 'Alice', 'Smith', 'https://randomuser.me/api/portraits/men/1.jpg', 'alice.smith@example.com', '123-456-7890', 'Mathematics', 'Excellent problem-solving skills.'),
( 'Bob', 'Johnson', 'https://randomuser.me/api/portraits/men/2.jpg', 'bob.johnson@example.com', '234-567-8901', 'Physics', 'Needs improvement in lab work.'),
( 'Charlie', 'Brown', 'https://randomuser.me/api/portraits/men/3.jpg', 'charlie.brown@example.com', '345-678-9012', 'Chemistry', 'Very creative in experiments.'),
( 'David', 'Williams', 'https://randomuser.me/api/portraits/men/4.jpg', 'david.williams@example.com', '456-789-0123', 'Biology', 'Consistently performs well.'),
( 'Eva', 'Jones', 'https://randomuser.me/api/portraits/men/5.jpg', 'eva.jones@example.com', '567-890-1234', 'History', 'Needs to participate more in discussions.'),
( 'Frank', 'Garcia', 'https://randomuser.me/api/portraits/men/6.jpg', 'frank.garcia@example.com', '678-901-2345', 'Geography', 'Excellent map-reading skills.'),
( 'Grace', 'Martinez', 'https://randomuser.me/api/portraits/men/7.jpg', 'grace.martinez@example.com', '789-012-3456', 'Literature', 'Very imaginative in writing.'),
( 'Henry', 'Rodriguez', 'https://randomuser.me/api/portraits/men/8.jpg', 'henry.rodriguez@example.com', '890-123-4567', 'Computer Science', 'Excellent coding skills.'),
( 'Ivy', 'Lee', 'https://randomuser.me/api/portraits/men/9.jpg', 'ivy.lee@example.com', '901-234-5678', 'Art', 'Very talented in painting.'),
( 'Jack', 'Taylor', 'https://randomuser.me/api/portraits/men/10.jpg', 'jack.taylor@example.com', '012-345-6789', 'Music', 'Great at playing the piano.');

CREATE TABLE note (
    id SERIAL PRIMARY KEY,  -- Fix: Added SERIAL for auto-increment
    student_id INT NOT NULL,
    matiere TEXT NOT NULL,
    score INT CHECK (score BETWEEN 0 AND 20),
    status BOOLEAN DEFAULT FALSE,
    FOREIGN KEY (student_id) REFERENCES student(id) ON DELETE CASCADE
);

-- Corrected student_id to match auto-generated student IDs
INSERT INTO note (student_id, matiere, score, status) VALUES
(1, 'Math', 15, TRUE),
(2, 'Science', 12, TRUE),
(3, 'History', 18, TRUE),
(4, 'English', 14, TRUE),
(5, 'Physics', 10, TRUE),
(6, 'Chemistry', 17, TRUE),
(7, 'Biology', 8, FALSE),
(8, 'Geography', 19, TRUE),
(9, 'French', 11, TRUE),
(10, 'Philosophy', 16, TRUE),
(1, 'Math', 13, TRUE),
(2, 'Science', 14, TRUE),
(3, 'History', 19, TRUE),
(4, 'English', 12, TRUE),
(5, 'Physics', 9, FALSE),
(6, 'Chemistry', 15, TRUE),
(7, 'Biology', 6, FALSE),
(8, 'Geography', 20, TRUE),
(9, 'French', 10, TRUE),
(10, 'Philosophy', 17, TRUE),
(1, 'Physics', 11, TRUE),
(2, 'Math', 14, FALSE),
(3, 'French', 16, TRUE),
(4, 'Philosophy', 9, FALSE),
(5, 'Geography', 18, TRUE),
(6, 'History', 13, TRUE),
(7, 'Science', 10, TRUE),
(8, 'English', 19, TRUE),
(9, 'Chemistry', 8, FALSE),
(10, 'Biology', 15, TRUE);
