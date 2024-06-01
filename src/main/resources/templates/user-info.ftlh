<#import "blocks/template.ftlh" as t>
<@t.template principal>
<h1>TSP</h1><hr>
<h3> Подробная информация о пользователе ${user.name}</h3>
<b>Номер телефона: </b><#if user.phoneNumber?has_content>${user.phoneNumber}<#else>None</#if><br>
<b>Email: </b>${user.email}<br>
<h4>Заказы пользователя ${user.name}</h4>
    <table class="table table-hover table-dark">
        <tr>
            <th scope="col">ID</th>
            <th scope="col">Цена</th>
            <th scope="col">Список товаров</th>
            <th scope="col">Активность</th>
        </tr>
<#list orders as order>
    <tr>
        <th>${order.id}</th>
        <th>${order.price}</th>
        <th>
            <#list order.techniques as tech>
                <li>${tech.title}</li>
            </#list>
        </th>
        <th><#if order.active>В обработке<#else>Выполнен</#if></th>
    </tr>
    <#else>
    <h3>Заказов нет</h3>
</#list>
<#if errorMessage??>
    <h2 style="color: red">${errorMessage}</h2>
</#if>
    </table>
</@t.template>