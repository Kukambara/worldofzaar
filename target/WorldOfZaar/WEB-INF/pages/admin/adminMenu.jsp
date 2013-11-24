<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<li class="dropdown">
    <a href="#" class="dropdown-toggle" data-toggle="dropdown">
        Race<b class="caret"></b>
    </a>
    <ul class="dropdown-menu">
        <li>
            <a href="/admin/race/create">Create race</a>
        </li>
        <li>
            <a href="/admin/race/list">All races</a>
        </li>
        <li>
            <a href="/admin/race/picture/list">Pictures</a>
        </li>
    </ul>
</li>

<li class="dropdown">
    <a href="#" class="dropdown-toggle" data-toggle="dropdown">
        Classes<b class="caret"></b>
    </a>
    <ul class="dropdown-menu">
        <li>
            <a href="/admin/class/create">Create class</a>
        </li>
        <li>
            <a href="/admin/class/list">All classes</a>
        </li>
        <li>
            <a href="/admin/class/blazon/edit">Blazons</a>
        </li>
        <li>
            <a href="/admin/class/cloth/edit">Clothes</a>
        </li>
    </ul>
</li>
<li class="dropdown">
    <a href="#" class="dropdown-toggle" data-toggle="dropdown">
        Cards<b class="caret"></b>
    </a>
    <ul class="dropdown-menu">
        <li>
            <a href="/admin/card/create">Create card</a>
        </li>
        <li>
            <a href="/admin/card/list">Cards</a>
        </li>
        <li class="dropdown-submenu">
            <a tabindex="-1" href="/admin/mastercard/list">MasterCard</a>
            <ul class="dropdown-menu">
                <li>
                    <a href="/admin/mastercard/create">Create</a>
                </li>
                <li>
                    <a href="/admin/mastercard/list">List</a>
                </li>
            </ul>
        </li>
    </ul>
</li>
<li class="dropdown">
    <a href="#" class="dropdown-toggle" data-toggle="dropdown">
        Sets<b class="caret"></b>
    </a>
    <ul class="dropdown-menu">
        <li>
            <a href="/admin/set/create">Create set</a>
        </li>
        <li>
            <a href="/admin/set/list">Sets</a>
        </li>
        <li>
            <a href="/admin/subset/add">Subset</a>
        </li>
    </ul>
</li>
<li class="dropdown">
    <a href="#" class="dropdown-toggle" data-toggle="dropdown">
        Properties<b class="caret"></b>
    </a>
    <ul class="dropdown-menu">
        <li>
            <a href="/admin/property/create">Create Property</a>
        </li>
        <li>
            <a href="/admin/property/list">Properties</a>
        </li>
    </ul>
</li>
<li class="dropdown">
    <a href="#" class="dropdown-toggle" data-toggle="dropdown">
        Experience<b class="caret"></b>
    </a>
    <ul class="dropdown-menu">
        <li>
            <a href="/admin/experience/create">Create experience</a>
        </li>
        <li>
            <a href="/admin/experience/list">List</a>
        </li>
    </ul>
</li>
<li>
    <a href="/admin/adminList">Admin list</a>
</li>


