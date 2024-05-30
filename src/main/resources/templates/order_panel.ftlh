<!DOCTYPE html>
<html>
<head>
    <title>TSP</title>
</head>
<body>
<h1>TSP</h1>
<h4>Панель администратора(заказы)</h4>
<table border="1">
    <tr>
        <th>ID</th>
        <th>Цена</th>
        <th>Активность</th>
        <th>Список товаров</th>
        <th>Редактирование</th>
        <th>Подробная информация</th>
    </tr>
    <#list orders as order>
        <tr>
            <th>${order.id}</th>
            <th>${order.price}</th>
            <th><#if order.active>true<#else>false</#if></th>
            <th><#list order.techniques as tech>${tech}</#list></th>
            <th>
                <form action="admin/order/edit/${order.id}" method="post">
                    <input type="hidden" name="_csrf" value="${_csrf.token}">
                    <input type="submit" value="Изменить активность">
                </form>
            </th>
            <th><a href="/admin/user/edit/${order.id}">Редактирование</a></th>
            <th><a href="/user/${order.id}">Подробная информация</a></th>
        </tr>
    <#else>
        <h3>Заказов нет</h3>
    </#list>
</table>
</body>
</html>