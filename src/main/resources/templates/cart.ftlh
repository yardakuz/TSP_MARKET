<#import "blocks/template.ftlh" as t>
<@t.template user>
<h3>Корзина пользователя <i>${user.name}</i></h3>
<form method="post" action="/order/create" modelAttribute="technique">
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
    <#list cart as product>
    <input type="hidden" name="techniques[]" value="${product.id}"/>
    </#list>
    <input type="hidden" name = "_csrf" value="${_csrf.token}">
    <input type="submit" value="Создать заказ"/>
</form>
</@t.template>
