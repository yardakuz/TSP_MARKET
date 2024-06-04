<#import "blocks/template.ftlh" as t>
<@t.template user>
<h4>Список товаров</h4><hr>
    <form action="/" method="get">
        Поиск по названию товара: <input type="text" name="title">
        <input type="submit" value="Поиск"/>
    </form>
    <#list products as product>
        <div class="row row-cols-2 row-cols-md-2 g-4">
            <div class="col">
        <a href="/product/${product.id}" class="product-link">
            <div class="card text-white bg-dark my-3 product" style="width: 18rem;">
                <img src="/images/${product.previewImageId}"
                     class="card-img-top">
                <div class="card-body">
                    <h5 class="card-title">${product.title}</h5>
                    <p class="card-text">${product.price} ₽</p>
                    <#if !user.isAdmin() && user.email??>
                    <form action="/product/cart/${product.id}" method="post">
                        <input type="hidden" name = "_csrf" value="${_csrf.token}">
                        <input type="submit" value="Добавить товар в корзину"/>
                    </form>
                    </#if>
                </div>

            </div>

        </a>
            </div>

        </div>
    <#else>
        <h1 style="text-align: center">Не найдено</h1>
    </#list>
<#if user.isAdmin()>
<hr>
<h3>Создать новый товар</h3>
<form action="/product/create" method="post" enctype="multipart/form-data">
    Название товара: <input type="text" name="title"/><br><br>
    Описание товара: <input type="text" name="description"/><br><br>
    Цена товара: <input type="number" name="price"/><br><br>
    Первая фотография: <input type="file" name="file1"/><br><br>
    Вторая фотография: <input type="file" name="file2"/><br><br>
    Третья фотография: <input type="file" name="file3"/><br><br>
    <input type="hidden" name = "_csrf" value="${_csrf.token}">
    <input type="submit" value="Добавить товар"/>
</form>
</#if>

</@t.template>