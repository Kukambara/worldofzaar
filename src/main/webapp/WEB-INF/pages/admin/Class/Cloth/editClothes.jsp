<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="../../adminPageHeader.jsp"/>

<h3>Edit Clothes</h3>

<div class="container-fluid">
    <div class="row-fluid">
        <div class="span7">
            <div class="mycontent-left">
                <form action="/admin/class/cloth/edit" method="post" enctype="multipart/form-data">
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
                Clothes
                <div id="pictures">
                    <c:forEach var="cloth" items="${clothes}">
                        <form action="/admin/class/cloth/edit/${cloth.clothId}" method="post"
                              enctype="multipart/form-data">
                            <div class="previewDiv" id="preview_preview${cloth.clothId}">
                                <img src="${cloth.clothPath}" class="picturePreview">
                            </div>
                            <a href="/admin/class/cloth/delete/${cloth.clothId}"><i
                                    class="icon-remove"></i></a>    </br>
                            <input type="file" id="preview${cloth.clothId}" name="preview${cloth.clothId}" onchange="myFunc(this)"
                                   required/>
                            <button type="submit" class="btn">Save</button>
                            </br>

                        </form>
                        <hr>
                        </br>
                    </c:forEach>
                </div>
            </div>
        </div>
    </div>
</div>
<hr>

<jsp:include page="../../AdminPageFooter.jsp"/>