<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="adminPageHeader.jsp"/>
<table class="table table-hover">
    <thead>
    <th>Email</th>
    <th>Is Approved</th>
    <th>Manage</th>
    <th>Delete</th>
    </thead>
    <tbody><c:forEach var="admin" items="${admins}">
        <tr>
            <td>
                    ${admin.webUser.webUserEmail}
            </td>
            <td>
                    ${admin.approved}
            </td>
            <td>
                <c:choose>
                    <c:when test="${admin.approved == true}">
                        <a class="btn btn-primary"
                           href="/admin/disapproveAdmin/${admin.adminId}">Disapprove</a>
                    </c:when>
                    <c:when test="${admin.approved == false}">
                        <a class="btn btn-primary"
                           href="/admin/approveAdmin/${admin.adminId}">Approve</a>
                    </c:when>
                </c:choose>
            </td>
            <td>
                <a class="btn btn-danger"
                   href="/admin/deleteAdmin/${admin.adminId}">Delete</a>
            </td>

        </tr>
    </c:forEach>
    </tbody>
</table>
<jsp:include page="AdminPageFooter.jsp"/>