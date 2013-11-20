<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="../../adminPageHeader.jsp"/>

<h3>Create subset</h3>

<div class="container-fluid">
    <div class="row-fluid">
        <div class="span7">
            <div class="mycontent-left">
                <form action="/admin/subset/add" method="post" enctype="multipart/form-data">
                    <select name="setId">
                        <c:forEach var="set" items="${sets}">
                            <option value="${set.set.setId}">
                                    ${set.setName}
                            </option>
                        </c:forEach>
                    </select>
                    </br>
                    <input type="text" name="techName" placeholder="Technic name" required>
                    </br>

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
                    <button type="submit" class="btn">Upload</button>
                </form>
            </div>
        </div>
        <div class="span5">
            <div class="mycontent-right">
                <div id="pictures">
                    <c:forEach var="subset" items="${subsets}">
                        <form action="/admin/subset/edit/${subset.subsetId}" method="post"
                              enctype="multipart/form-data">

                            <div class="previewDiv" id="preview_preview${subset.subsetId}">
                                <img src="${subset.frontPath}" class="picturePreview">
                            </div>

                            <a href="/admin/subset/delete/${subset.subsetId}">

                                <i class="icon-remove"></i></a>    </br>

                            <input type="file" id="preview${subset.subsetId}" name="preview${subset.subsetId}"
                                   onchange="myFunc(this)"/>

                            </br>
                            <select name="setId">
                                <c:forEach var="set" items="${sets}">
                                    <option value="${set.set.setId}"
                                            <c:if test="${set.set.setId == subset.set.setId}">
                                                selected
                                            </c:if>
                                            >
                                            ${set.setName}
                                    </option>
                                </c:forEach>
                            </select>
                            </br>
                            <input type="text" name="techName" placeholder="Technic name"
                                   value="${subset.subsetTechName}" required>
                            </br>
                            <button type="submit" class="btn">Change subset</button>
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