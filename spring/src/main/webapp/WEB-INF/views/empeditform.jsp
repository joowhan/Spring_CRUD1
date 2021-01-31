<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>    
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>    
  
        <h1>Edit Employee</h1>  
       <form:form method="POST" action="../editsave" enctype="multipart/form-data">    
       <fieldset>
			<p>
				<label for="Id"></label>
				<form:hidden id="id" path="id" />
			</p>
			<p>
				<label for="Name">Name :</label>
				<form:input id="name" path="name" />
			</p>
			<p>
				<label for="Salary">Salary :</label>
				<form:input id="salary" path="salary" />
			</p>
			<p>
				<label for="Designation">Designation :</label>
				<form:input id="designation" path="designation" />
			</p>
			<p>
				<label for="Image">Image :</label>
				<form:input id="image" type="file" path="image" name="image" />
			</p>

			<p>
				<input type="submit" value="Edit Save" />
			</p>
		</fieldset>
       </form:form>   