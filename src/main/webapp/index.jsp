<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>Employee Record Deletion</title>
</head>
<body>
    <h2>Employee Management System</h2>
    <p>Click the button below to delete all records where the <b>Emp_Name</b> starts with 'S'.</p>
    
    <form action="EmployeeServlet" method="post">
        <input type="submit" value="Delete 'S' Records & Show Report">
    </form>
</body>
</html>