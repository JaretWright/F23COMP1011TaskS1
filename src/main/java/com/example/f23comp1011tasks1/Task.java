package com.example.f23comp1011tasks1;

import java.time.LocalDate;

public class Task {
    private int taskID;
    private String title, description;
    private LocalDate creationDate, dueDate;
    private int severity;
    private Status status;
    private User user;

    /**
     * This constructor should be used when creating a new task
     */
    public Task(String title, String description, LocalDate dueDate, int severity, User user) {
        status = Status.CREATED;
        creationDate =LocalDate.now();
        taskID =-1;  //set it to be invalid, we will get the proper one from the DB
        setTitle(title);
        setDescription(description);
        setDueDate(dueDate);
        setSeverity(severity);
        setUser(user);
    }

    /**
     * This constructor should be used when reading a task from the database
     */
    public Task(int taskID, String title, String description, LocalDate creationDate, LocalDate dueDate, int severity, Status status, User user) {
        this.taskID = taskID;
        setTitle(title);
        setDescription(description);
        this.creationDate = creationDate;
        this.dueDate = dueDate;
        setSeverity(severity);
        setStatus(status);
        this.user = user;
    }

    public int getTaskID() {
        return taskID;
    }

    public void setTaskID(int taskID) {
        this.taskID = taskID;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        if (validateString(title,70))
            this.title = title;
        else
            throw new IllegalArgumentException("title must be 2 to 30 characters");
    }

    public boolean validateString(String stringToTest, int maxLength)
    {
        stringToTest = stringToTest.trim();
        return (stringToTest.length()>=2 && stringToTest.length()<=maxLength);
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        if (validateString(description, 250))
            this.description = description;
        else
            throw new IllegalArgumentException("description must be 2 to 250 characters");
    }

    public LocalDate getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDate creationDate) {
        this.creationDate = creationDate;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public void setDueDate(LocalDate dueDate) {
        if (status == Status.DONE || status == Status.INPROGRESS)
            this.dueDate = dueDate;
        else if (dueDate.isEqual(LocalDate.now()) || dueDate.isAfter(LocalDate.now()))
            this.dueDate = dueDate;
        else
            throw new IllegalArgumentException("due date must be in the future");
    }

    public int getSeverity() {
        return severity;
    }

    public void setSeverity(int severity) {
        if (severity >=1 && severity <= 3)
            this.severity = severity;
        else
            throw new IllegalArgumentException("severity must be in the range 1-3");
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        if (user != null)
            this.user = user;
        else
            throw new IllegalArgumentException("user cannot be null");
    }

    public boolean contains(String searchTerm, boolean created, boolean inProgress, boolean done)
    {
        searchTerm = searchTerm.toLowerCase();
        //contains is a case sensitive search for a substring
        return (title.toLowerCase().contains(searchTerm) ||
                Integer.toString(taskID).contains(searchTerm) ||
                user.contains(searchTerm)) &&
                (created && status.equals(Status.CREATED) ||
                inProgress && status.equals(Status.INPROGRESS) ||
                done && status.equals(Status.DONE));
    }
}
