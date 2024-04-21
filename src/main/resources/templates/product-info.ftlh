<!DOCTYPE html>
<html>
<head>
    <title>TSP</title>
</head>
<body>
<h1>TSP</h1><hr>
<h4>Информация о товаре</h4>
<b>Название товара: </b>${product.title}<br>
<b>Описание товара: </b>${product.description}<br>
<b>Цена товара: </b>${product.price}<br>
<b>Изображение товара: </b>${product.image}<br>
<hr>
<h3>Создать новый товар</h3>
<form action="/product/delete/${product.ID}" method="post">
    <input type="submit" value="Удалить товар"/>
</form>
</body>
</html>