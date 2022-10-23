<%@tag description="Company Page template" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@attribute name="methodName" required="true" type="java.lang.String" %>
<div class="col-md-3 col-lg-3">
    <h4 class="lg-3">${methodName} skill</h4>
    <form class="needs-validation" action="${pageContext.request.contextPath}/skills"
          method="post">
        <div class="row g-3">

            <div class="col-3">
                <label class="form-label" for="id">id</label>
                <input class="form-control" disabled id="id" name="id" placeholder="${skill.id}" type="number">
            </div>

            <div class="col-3">
                <label class="form-label" for="language">Language</label>
                <div class="input-group has-validation">

                    <input class="form-control" id="language" name="language" placeholder="${skill.language}" required="" type="text">
                    <div class="invalid-feedback">
                        Language is required.
                    </div>
                </div>
            </div>

            <div class="col-3">
                <label class="form-label" for="level">Level </label>
                <select class="form-select" id="level" name="level" required="">
                    <option value="">${skill.level}</option>
                    <option>Junior</option>
                    <option>Middle</option>
                    <option>Senior</option>
                </select>

            </div>
            <hr class="my-4">


            <button class="w-100 btn btn-primary btn-lg" type="submit"
                    <c:if test="${entityName.equal('Update')}"> onclick="makePUTrequest('${pageContext.request.contextPath}/skills}')"</c:if>>
                ${methodName} skill
            </button>
        </div>
    </form>
</div>

