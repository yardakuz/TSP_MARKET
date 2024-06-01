<#import "blocks/template.ftlh" as t>
<@t.template user>
<h4>Панель администратора</h4>
<table border="1">
    <tr>
        <th>Email</th>
        <th>Номер телефона</th>
        <th>Активность</th>
        <th>Роли</th>
        <th>Бан</th>
        <th>Редактирование</th>
        <th>Подробная информация</th>
    </tr>
    <#list users as user1>
        <tr>
            <th>${user1.email}</th>
            <th><#if user1.phoneNumber?has_content>${user1.phoneNumber}<#else>None</#if></th>
            <th><#if user1.active>true<#else>false</#if></th>
            <th><#list user1.roles as role>${role}</#list></th>
            <th>
                <form action="admin/user/ban/${user1.id}" method="post">
                    <input type="hidden" name="_csrf" value="${_csrf.token}">
                    <input type="submit" value="Бан">
                </form>
            </th>
            <th><a href="/admin/user/edit/${user1.id}">Редактирование</a></th>
            <th><a href="/user/${user1.id}">Подробная информация</a></th>
        </tr>
    <#else>
        <h3>Пользователей нет</h3>
    </#list>
</table>
</@t.template>