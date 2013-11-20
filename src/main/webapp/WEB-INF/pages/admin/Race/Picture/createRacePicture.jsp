<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="../../adminPageHeader.jsp"/>

<h3>Race pictures</h3>

<div class="container-fluid">
    <div class="row-fluid">
        <div class="span7">
            <div class="mycontent-left">
                <form action="/admin/race/picture/edit/${raceId}" method="post" enctype="multipart/form-data">
                    <select name="sex" required>
                        <option value="male">Male</option>
                        <option value="female">Female</option>
                    </select>
                    <br>
                    <table id="fileTable">
                        <tr>
                            <td>
                                <input type="file" name="file0" required onchange="myFunc(this)"/>
                            </td>
                            <td>
                                <div id="preview_file0"></div>
                            </td>
                        </tr>
                    </table>
                    <br/>
                    <input id="addFile" type="button" value="Add File"/>
                    <br/>

                    <button type="submit" class="btn">Upload</button>
                </form>
            </div>
        </div>
        <div class="span5">
            <div class="mycontent-right">
                Pictures
                <div id="pictures">
                    <c:forEach var="picture" items="${pictures}">
                        <img src="${picture.picturePath}" class="picturePreview">
                        <a href="/admin/race/picture/delete/${picture.racePictureId}"><i
                                class="icon-remove"></i></a>
                        </br>
                        <c:choose>
                            <c:when test="${picture.male == true}">
                                <span class="label label-success">M</span>
                                <a href="/admin/race/picture/changeSex/${picture.racePictureId}">
                                    <span class="label">F</span> </a>
                            </c:when>
                            <c:when test="${picture.male == false}">
                                <a href="/admin/race/picture/changeSex/${picture.racePictureId}">
                                    <span class="label">M</span> </a>
                                <span class="label label-success">F</span>
                            </c:when>
                        </c:choose>
                        </br><hr></br>
                    </c:forEach>
                </div>
            </div>
        </div>
    </div>
</div>
<hr>

<jsp:include page="../../AdminPageFooter.jsp"/>