-- Create the CodeMeIO database
CREATE DATABASE CodeMeIO;
GO

-- Use the CodeMeIO database
USE CodeMeIO;
GO

-- Create Users table
CREATE TABLE Users (
    UserId INT PRIMARY KEY IDENTITY(1,1),
    Username NVARCHAR(50) NOT NULL UNIQUE,
    Fullname NVARCHAR(100) NOT NULL,
    Password NVARCHAR(255) NOT NULL,
    Email NVARCHAR(100) NOT NULL UNIQUE,
    Phone NVARCHAR(20),
    Gender NVARCHAR(10),
    Photo NVARCHAR(255),
    Status NVARCHAR(20),
    UserType NVARCHAR(20),
    StartDate DATE,
    Specialization NVARCHAR(100),
    YearsOfExperience INT,
    Department NVARCHAR(50)
);

-- Create Roles table
CREATE TABLE Roles (
    RoleId INT PRIMARY KEY IDENTITY(1,1),
    RoleName NVARCHAR(50) NOT NULL UNIQUE
);

-- Create UserRoles table
CREATE TABLE UserRoles (
    UserRoleId INT PRIMARY KEY IDENTITY(1,1),
    UserId INT,
    RoleId INT,
    DateAssigned DATE NOT NULL,
    FOREIGN KEY (UserId) REFERENCES Users(UserId),
    FOREIGN KEY (RoleId) REFERENCES Roles(RoleId)
);

-- Create Categories table
CREATE TABLE Categories (
    CategoryId INT PRIMARY KEY IDENTITY(1,1),
    CategoryName NVARCHAR(100) NOT NULL,
    Description NVARCHAR(MAX)
);

-- Create Courses table
CREATE TABLE Courses (
    CourseId INT PRIMARY KEY IDENTITY(1,1),
    Title NVARCHAR(255) NOT NULL,
    Description NVARCHAR(MAX),
    ImageCourses NVARCHAR(255),
    Price DECIMAL(10, 2) NOT NULL,
    Rating DECIMAL(3, 2),
    CategoryId INT,
    InstructorId INT,
    TotalStudents INT DEFAULT 0,
    FOREIGN KEY (CategoryId) REFERENCES Categories(CategoryId),
    FOREIGN KEY (InstructorId) REFERENCES Users(UserId)
);

-- Create Lessons table
CREATE TABLE Lessons (
    LessonId INT PRIMARY KEY IDENTITY(1,1),
    CourseId INT,
    Title NVARCHAR(255) NOT NULL,
    Content NVARCHAR(MAX),
    LinkVideo NVARCHAR(255),
    FOREIGN KEY (CourseId) REFERENCES Courses(CourseId)
);

-- Create Reviews table
CREATE TABLE Reviews (
    ReviewId INT PRIMARY KEY IDENTITY(1,1),
    CourseId INT,
    StudentId INT,
    Rating INT NOT NULL,
    Comment NVARCHAR(MAX),
    FOREIGN KEY (CourseId) REFERENCES Courses(CourseId),
    FOREIGN KEY (StudentId) REFERENCES Users(UserId)
);

-- Create Assignments table
CREATE TABLE Assignments (
    AssignmentId INT PRIMARY KEY IDENTITY(1,1),
    LessonId INT,
    Title NVARCHAR(255) NOT NULL,
    Description NVARCHAR(MAX),
    FOREIGN KEY (LessonId) REFERENCES Lessons(LessonId)
);

-- Create Submissions table
CREATE TABLE Submissions (
    SubmissionId INT PRIMARY KEY IDENTITY(1,1),
    AssignmentId INT,
    StudentId INT,
    SubmissionContent NVARCHAR(MAX),
    Grade DECIMAL(5, 2),
    Feedback NVARCHAR(MAX),
    FOREIGN KEY (AssignmentId) REFERENCES Assignments(AssignmentId),
    FOREIGN KEY (StudentId) REFERENCES Users(UserId)
);

-- Create Enrollments table
CREATE TABLE Enrollments (
    EnrollmentId INT PRIMARY KEY IDENTITY(1,1),
    StudentId INT,
    CourseId INT,
    EnrollmentDate DATE NOT NULL,
    CompletionDate DATE,
    FOREIGN KEY (StudentId) REFERENCES Users(UserId),
    FOREIGN KEY (CourseId) REFERENCES Courses(CourseId)
);

-- Create Payments table
CREATE TABLE Payments (
    PaymentId INT PRIMARY KEY IDENTITY(1,1),
    StudentId INT,
    CourseId INT,
    Amount DECIMAL(10, 2) NOT NULL,
    PaymentDate DATE NOT NULL,
    PaymentStatus NVARCHAR(20) NOT NULL,
    FOREIGN KEY (StudentId) REFERENCES Users(UserId),
    FOREIGN KEY (CourseId) REFERENCES Courses(CourseId)
);