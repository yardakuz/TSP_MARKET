<!DOCTYPE html>
<html>
<head>
    <title>TSP</title>
</head>
<body>
<h1>TSP</h1><gr></gr>
<hr>
<h4>Корзина пользователя</h4>
<#list cart as product>
    <div>
        <p>
            <b>${product.title}</b>
            ${product.price} руб.







        </p>
    </div>
<#else>
    <h3>Товаров нет</h3>
</#list>
<form action="/order/create" method="post">
    <input type="hidden" name="order" value="${cart}">
    <input type="submit" value="Создать заказ"/>
</form>
</body>
</html>