<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<body>
  
<form action="<c:url value='/edit?id=${id}'/>" method='POST'>
    <table>
        <tr>
            <td>Введите название чата:</td>
            <td><input type='text' name='name_form' value="${name}"></td>
        </tr>

        <tr>
            <td colspan='2'><input name="submit" type="submit" value="Сохранить" /></td>
        </tr>
    </table>
</form>
</body>
</html>