<#import "blocks/template.ftlh" as t>
<@t.template user>
<h1>TSP</h1>
<h4 style="text-align: center">История заказов</h4>
<table class="table table-hover table-dark">
    <tr>
        <th scope>ID</th>
        <th scope>Цена</th>
        <th scope>Список товаров</th>
        <th scope>Активность</th>
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