<%@tag description="Company Page template" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@attribute name="methodName" required="true" type="java.lang.String" %>
<div class="col-md-3 col-lg-3">
    <h4 class="lg-3">${methodName} project</h4>
    <form class="needs-validation" action="${pageContext.request.contextPath}/projects"
          method="post">
        <div class="row g-3">

            <div class="col-3">
                <label class="form-label" for="id">id</label>
                <input class="form-control" disabled id="id" name="id" placeholder="${project.id}" type="number">
            </div>

            <div class="col-3">
                <label class="form-label" for="name">Name</label>
                <div class="input-group has-validation">

                    <input class="form-control" id="name" name="name" placeholder="${project.name}" required="" type="text">
                    <div class="invalid-feedback">
                        Name is required.
                    </div>
                </div>
            </div>

            <div class="col-3">
                <label class="form-label" for="git_url">Git_url</label>
                <div class="input-group has-validation">

                    <input class="form-control" id="git_url" name="git_url" placeholder="${project.git_url}" required="" type="text">
                </div>
            </div>

            <div class="col-3">
                <label class="form-label" for="cost">Cost</label>
                <div class="input-group has-validation">

                    <input class="form-control" id="cost" name="cost" placeholder="${project.cost}" required="" type="number">
                </div>
            </div>

            <div class="col-3">
                <label class="form-label" for="date">Date</label>
                <div class="input-group has-validation">

                    <input class="form-control" id="date" name="date" placeholder="${dateValue}" required="" type="date">
                </div>
            </div>
            <hr class="my-4">


            <button class="w-100 btn btn-primary btn-lg" type="submit"
                    <c:if test="${entityName.equal('Update')}"> onclick="makePUTrequest('${pageContext.request.contextPath}/projects}')"</c:if>>
                ${methodName} project
            </button>
        </div>
    </form>
</div>

