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
        <th>Сменить активность</th>
    </tr>
    <#list orders as order>
        <tr>
            <th>${order.id}</th>
            <th>${order.price}</th>
            <th><#if order.active>true<#else>false</#if></th>
            <th><#list order.techniques as tech>${tech}<#sep>, </#list></th>
            <th>
                <form action="/order/active/${order.id}" method="post">
                    <input type="hidden" name="_csrf" value="${_csrf.token}">
                    <input type="submit" value="Изменить активность">
                </form>
            </th>
        </tr>
    <#else>
        <h3>Заказов нет</h3>
    </#list>
</table>
</body>
</html>