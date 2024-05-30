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








        </p>
    </div>
<#else>
    <h3>Товаров нет</h3>
</#list>
</body>
</html>