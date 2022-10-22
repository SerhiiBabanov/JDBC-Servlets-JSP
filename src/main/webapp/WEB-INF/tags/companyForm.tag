<%@tag description="Company Page template" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@attribute name="methodName" required="true" type="java.lang.String" %>
<div class="col-md-3 col-lg-3">
    <h4 class="lg-3">${methodName} company</h4>
    <form class="needs-validation" action="${pageContext.request.contextPath}/companies" method="post" novalidate="">
        <div class="row g-3">

            <div class="col-3">
                <label class="form-label" for="id">id</label>
                <input class="form-control" disabled id="id" name="id" placeholder="${company.id}" type="number">
            </div>


            <div class="col-3">
                <label class="form-label" for="name">Name</label>
                <input class="form-control" id="name" name="name" placeholder="${company.name}" required type="text">
            </div>

            <div class="col-3">
                <label class="form-label" for="country">Country </label>
                <input class="form-control" id="country" name="country" placeholder="${company.country}" type="text">
            </div>

            <hr class="my-4">


            <button class="w-100 btn btn-primary btn-lg" type="submit"
                    <c:if test="${entityName.equal('Update')}"> onclick="makePUTrequest('${pageContext.request.contextPath}/companies}')"</c:if>>
                ${methodName} company
            </button>
        </div>
    </form>
</div>

