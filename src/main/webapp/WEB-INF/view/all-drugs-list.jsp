<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html>
<html>

<head>
    <title>Список всех лекарств</title>

</head>


<body>

<h2>Список всех лекарств</h2>
<br>

<input type="button" value="Добавить препарат" style="height:50px; width:150px"
       onclick="window.location.href='addNewMedicine' "/>
<br>
<br>

<form action="findMedicine" method="get">
    <input type="text" name="medicineName" placeholder="препарат для поиска"/>
    <input type="submit" value="Найти" style="height:50px; width:100px"/>
</form>

<br>

<table border="1" style="border-collapse: collapse">

    <c:url var="sortById" value="/sortById"> </c:url>

    <c:url var="sortByName" value="/sortByName"> </c:url>

    <c:url var="sortByExpDate" value="/sortByExpDate"> </c:url>


    <tr align="center">
        <th>
            &nbsp;
            <a href="/medicine/sortById">
                <font size="+2">
                    <b> ID </b>
                </font>
            </a>
            &nbsp;
        </th>
        <th>
            &nbsp;
            <a href="/medicine/sortByName">
                <font size="+2">
                    <b>Препарат</b>
                </font>
            </a>
            &nbsp;
        </th>
        <th>
            &nbsp;
            <font size="+2">
                <b> Дозировка </b>
            </font>
            &nbsp;
        </th>
        <th>
            &nbsp;
            <font size="+2">
                <b> Количество</b>
            </font>
            &nbsp;
        </th>
        <th>
            &nbsp;
            <a href="/medicine/sortByExpDate">
                <font size="+2">
                    <b>Годен до</b>
                </font>
                &nbsp;
                <br>
                <font size="+2">
                    <b>(мм.гггг)</b>
                </font>
        </th>
        <th>
            &nbsp;
            <font size="+2">
                <b> Операции </b>
            </font>
            &nbsp;
            </a>
        </th>
    </tr>

    <c:forEach var="med" items="${allMedicine}">

        <c:url var="updateButton" value="/updateMedicine">
            <c:param name="medId" value="${med.id}"/>
        </c:url>

        <c:url var="deleteButton" value="/deleteMedicine">
            <c:param name="medId" value="${med.id}"/>
        </c:url>

        <tr>
            <td>${med.id}</td>
            <td>${med.medicine}</td>
            <td align="center">${med.dosage}</td>
            <td align="center">${med.quantity}</td>
            <td align="center">${med.expiration}</td>
            <td>
                <input type="button" value="UPDATE" style="height:50px; width:100px"
                       onclick="window.location.href='${updateButton}' "/>
                <input type="button" value="DELETE" style="height:50px; width:100px"
                       onclick="window.location.href='${deleteButton}' "/>
            </td>

        </tr>
    </c:forEach>

</table>

</body>
</html>