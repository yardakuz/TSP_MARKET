<#import "blocks/template.ftlh" as t>
<@t.template user>
<h1>TSP</h1>
<h4>Панель администратора(заказы)</h4>
    <table class="table table-hover table-dark">
    <tr>
        <th scope="col">ID</th>
        <th scope="col">e-mail пользователя</th>
        <th scope="col">Цена</th>
        <th scope="col">Список товаров</th>
        <th scope="col">Активность</th>
        <th scope="col">Сменить активность</th>
    </tr>
    <#list orders as order>
        <tr>
            <th>${order.id}</th>
            <th>${order.user.email}</th>
            <th>${order.price}</th>
            <th>
                <#list order.techniques as tech>
                    <li>${tech.title}</li>
                </#list>
            </th>
            <th><#if order.active>В обработке<#else>Выполнен</#if></th>

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
</@t.template>