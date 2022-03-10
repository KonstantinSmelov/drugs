<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html>
<html>

<head>
    <title>Данные о препарате</title>
<body>

<h2>Данные о препарате</h2>
<br>

<form:form action="saveMedicine" modelAttribute="drug">

    <form:hidden path="id"/>

    Препарат <form:input path="medicine"/>
    <br><br>
    Дозировка <form:input path="dosage"/>
    <br><br>
    Кол-во <form:input path="quantity"/>
    <br><br>
    Использовать до <form:input path="expiration"/>
    <br><br>
    <input type="submit" value="Сохранить">


</form:form>

</body>
</html>