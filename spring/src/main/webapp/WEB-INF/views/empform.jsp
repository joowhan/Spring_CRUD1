<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>    
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>    
  <!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

  
        <h1>Add New Employee</h1>  
       <form:form method="POST" action="save" enctype="multipart/form-data">    
        <table >    
         <tr>    
          <td>Name : </td>   
          <td><form:input path="name" id="name" /></td>  
         </tr>    
         <tr>    
          <td>Salary :</td>    
          <td><form:input path="salary" id="salary"/></td>  
         </tr>   
         <tr>    
          <td>Designation :</td>    
          <td><form:input path="designation" id="designation"/></td>  
         </tr> 
         <tr>
				<td><label for="Image">Image :</label>
				<td><form:input id="image" type="file" path="image" name="image" />
         </tr>  
         <tr>    
          <td> </td>    
          <td><input type="submit" value="Save" /></td>    
         </tr>    
        </table>    
       </form:form>    
       
</body>
</html>