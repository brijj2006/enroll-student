package com.school.project.enrollstudent.bdd;

public class ResponseContext {

    private static String statusCode;
    private static String firstName;
    private static String lastName;
    private static String className;
    private static String nationality;
    private static String message;

    public static String getStatusCode() {
        return statusCode;
    }

    public static void setStatusCode(String statusCode) {
        ResponseContext.statusCode = statusCode;
    }

    public static String getFirstName() {
        return firstName;
    }

    public static void setFirstName(String firstName) {
        ResponseContext.firstName = firstName;
    }

    public static String getLastName() {
        return lastName;
    }

    public static void setLastName(String lastName) {
        ResponseContext.lastName = lastName;
    }

    public static String getClassName() {
        return className;
    }

    public static void setClassName(String className) {
        ResponseContext.className = className;
    }

    public static String getNationality() {
        return nationality;
    }

    public static void setNationality(String nationality) {
        ResponseContext.nationality = nationality;
    }

    public static String getMessage() {
        return message;
    }

    public static void setMessage(String message) {
        ResponseContext.message = message;
    }
}
