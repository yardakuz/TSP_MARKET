<!DOCTYPE html>
<html>
<head>
    <title>TSP</title>
</head>
<body>
<h1>TSP</h1><hr>
<h4>Список товаров</h4>
<#list products as product>
    <div>
        <p>
            <b>${product.title}</b>
            ${product.price} руб. | <a href="/product/${product.ID}">Подробнее...</a>
        </p>
    </div>
</#list>
<hr>
<h3>Создать новый товар</h3>
<form action="/product/create" method="post">
    Название товара: <input type="text" name="title"/><br><br>
    Описание товара: <input type="text" name="description"/><br><br>
    Цена товара: <input type="number" name="price"/><br><br>
    Изображение товара: <input type="text" name="image"/><br><br>
    <input type="submit" value="Добавить товар"/>
</form>
</body>
</html>