<#import "blocks/template.ftlh" as t>
<@t.template user>
<h1>TSP</h1>
<h4>История заказов</h4>
<table border="1">
    <tr>
        <th>ID</th>
        <th>Цена</th>
        <th>Список товаров</th>
        <th>Активность</th>
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
</table>
</@t.template>