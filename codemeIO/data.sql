USE CodeMeIO;
GO

-- Thêm dữ liệu vào bảng Roles
INSERT INTO Roles (RoleName) VALUES 
('Admin'), ('Instructor'), ('Student');

-- Thêm dữ liệu vào bảng Users
INSERT INTO Users (Username, Fullname, Password, Email, Phone, Gender, Status, UserType) VALUES 
('admin', N'Admin', '123', 'admin@codeme.io', '0901234567', 'Nam', 'Active', 'Admin'),
('Hungth', N'Hùng Trần', '123', 'hungth@gmail.com', '0901234567', 'Nam', 'Active', 'Admin'),
('instructor1', N'Trần Thị Hướng Dẫn', 'hashed_password', 'instructor@codeme.io', '0912345678', N'Nữ', 'Active', 'Instructor'),
('student1', N'Lê Văn Học', 'hashed_password', 'student@codeme.io', '0923456789', 'Nam', 'Active', 'Student');

-- Thêm dữ liệu vào bảng UserRoles
INSERT INTO UserRoles (UserId, RoleId, DateAssigned) VALUES 
(1, 1, GETDATE()),
(2, 1, GETDATE()),
(3, 2, GETDATE()),
(4, 3, GETDATE());

-- Thêm dữ liệu vào bảng Categories
INSERT INTO Categories (CategoryName, Description) VALUES 
(N'Lập trình Web', N'Các khóa học về phát triển web'),
(N'Lập trình Mobile', N'Các khóa học về phát triển ứng dụng di động'),
(N'Trí tuệ nhân tạo', N'Các khóa học về AI và Machine Learning');

-- Thêm dữ liệu vào bảng Courses
INSERT INTO Courses (Title, Description, ImageCourses, Price, Rating, CategoryId, InstructorId) VALUES 
(N'Lập trình Web với React', N'Khóa học toàn diện về React', 'react_course.jpg', 999000, 4.5, 1, 2),
(N'Phát triển ứng dụng Android', N'Xây dựng ứng dụng Android từ cơ bản đến nâng cao', 'android_course.jpg', 1299000, 4.2, 2, 2),
(N'Machine Learning cơ bản', N'Nhập môn về Machine Learning', 'ml_course.jpg', 1499000, 4.8, 3, 2);

-- Thêm dữ liệu vào bảng Lessons
INSERT INTO Lessons (CourseId, Title, Content, LinkVideo) VALUES 
(1, N'Giới thiệu về React', N'Bài học này giới thiệu về React và các khái niệm cơ bản', 'https://example.com/react_intro'),
(1, N'Components và Props', N'Tìm hiểu về components và props trong React', 'https://example.com/react_components'),
(2, N'Cài đặt môi trường Android Studio', N'Hướng dẫn cài đặt và cấu hình Android Studio', 'https://example.com/android_setup');

-- Thêm dữ liệu vào bảng Reviews
INSERT INTO Reviews (CourseId, StudentId, Rating, Comment) VALUES 
(1, 3, 5, N'Khóa học rất hay và dễ hiểu'),
(2, 3, 4, N'Nội dung phong phú, cần thêm bài tập thực hành');

-- Thêm dữ liệu vào bảng Assignments
INSERT INTO Assignments (LessonId, Title, Description) VALUES 
(1, N'Tạo ứng dụng React đầu tiên', N'Xây dựng một ứng dụng React đơn giản hiển thị "Hello, World!"'),
(2, N'Xây dựng component có thể tái sử dụng', N'Tạo một component Button có thể tái sử dụng với các props tùy chỉnh');

-- Thêm dữ liệu vào bảng Submissions
INSERT INTO Submissions (AssignmentId, StudentId, SubmissionContent, Grade, Feedback) VALUES 
(1, 3, 'https://github.com/student1/react-hello-world', 90, N'Bài làm tốt, cần cải thiện về cấu trúc project'),
(2, 3, 'https://github.com/student1/react-reusable-button', 85, N'Component hoạt động tốt, có thể tối ưu hóa thêm');

-- Thêm dữ liệu vào bảng Enrollments
INSERT INTO Enrollments (StudentId, CourseId, EnrollmentDate, CompletionDate) VALUES 
(3, 1, '2023-01-15', NULL),
(3, 2, '2023-02-01', '2023-04-30');

-- Thêm dữ liệu vào bảng Payments
INSERT INTO Payments (StudentId, CourseId, Amount, PaymentDate, PaymentStatus) VALUES 
(3, 1, 999000, '2023-01-15', 'Completed'),
(3, 2, 1299000, '2023-02-01', 'Completed');