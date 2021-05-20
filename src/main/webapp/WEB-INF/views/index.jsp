<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!doctype html>
<html lang="en">
<head>
    <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css"
          integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">

    <title>чат</title>
    <style>
    #bt1 {
    font-size:18px;
    font-weight: bold;
    height:70px;
    width:210px; 
    background: green;
    color: white;
    border-radius: 20px;
    }
    </style>
</head>
<body>
<div class="container mt-3">
    <div class="row">
        <h4>Мой профиль: <c:out value="${user}" /></h4>
        <br>
    </div>

    <div>
       <form action="<c:url value='/create'/>" method="get">
           <button id="bt1" type="submit" >+ Новый чат</button>
       </form>
       <br>
    </div>

    <div class="row">
        <table class="table">
            <thead>
            <tr>
                <th scope="col">Чаты</th>
                <th scope="col">Дата изменения</th>
                <th scope="col">Участники чата</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${posts}" var="post">
                <tr>
                    <td>
                        <h2><c:out value="${post.name}"/></h2>
                        <a href="<c:url value='/chat?id=${post.id}&name=${post.name}'/>" style="color:blue">Написать в чат</a>
                     
                        <br><input type="button"  value="+ Добавить пользователя" style="background:green; color:white; border-radius: 10px;" onclick="window.location.href='<c:url value='/add?id=${post.id}&name=${post.name}'/>';"/>
                    </td>
                    <td><c:out value="${post.created.time}"/></td>
                    <td><c:out value="${post.users}"/></td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
</div>

<!-- Optional JavaScript -->
<!-- jQuery first, then Popper.js, then Bootstrap JS -->
<script src="https://code.jquery.com/jquery-3.4.1.slim.min.js"
integrity="sha384-J6qa4849blE2+poT4WnyKhv5vZF5SrPo0iEjwBvKU7imGFAV0wwj1yYfoRSJoZ+n" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js"
integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js"
integrity="sha384-wfSDF2E50Y2D1uUdj0O3uMBJnjuUD4Ih7YwaYd1iqfktj0Uod8GCExl3Og8ifwB6" crossorigin="anonymous"></script>
</body>
</html>
