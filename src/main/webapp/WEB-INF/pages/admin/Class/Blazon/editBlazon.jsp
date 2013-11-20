<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="../../adminPageHeader.jsp"/>

<h3>Edit Blazon</h3>

<div class="container-fluid">
    <div class="row-fluid">
        <div class="span7">
            <div class="mycontent-left">
                <form action="/admin/class/blazon/add/${cloth.clothId}" method="post" enctype="multipart/form-data">
                    <img src="${cloth.clothPath}" class="picturePreview">
                    <select name="classId">
                        <c:forEach var="classif" items="${classes}">
                            <option value="${classif.classification.classificationId}">
                                    ${classif.className}
                            </option>
                        </c:forEach>
                    </select>
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
                Blazons
                <div id="pictures">
                    <c:forEach var="blazon" items="${blazons}">
                        <form action="/admin/class/blazon/edit/${blazon.blazonId}" method="post"
                              enctype="multipart/form-data">
                            <div class="previewDiv" id="preview_preview${cloth.clothId}">
                                <img src="${blazon.blazonPath}" class="picturePreview">
                            </div>
                            <a href="/admin/class/blazon/delete/${blazon.blazonId}"><i
                                    class="icon-remove"></i></a>
                            </br></br>
                            <input type="file" id="preview${cloth.clothId}" name="preview${cloth.clothId}"
                                   onchange="myFunc(this)"
                                    />
                            </br>
                            <select name="classId">
                                <c:forEach var="classif" items="${classes}">
                                    <option value="${classif.classification.classificationId}"
                                            <c:if test="${blazon.classification.classificationId == classif.classification.classificationId}">
                                                selected
                                            </c:if>
                                            >
                                            ${classif.className}
                                    </option>
                                </c:forEach>
                            </select>
                            </br>
                            <button type="submit" class="btn">Change class</button>
                            <hr>
                            </br>
                        </form>
                    </c:forEach>
                </div>
            </div>
        </div>
    </div>
</div>
<hr>

<jsp:include page="../../AdminPageFooter.jsp"/>