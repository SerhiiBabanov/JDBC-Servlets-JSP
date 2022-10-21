<%@tag description="Overall Page template" pageEncoding="UTF-8" %>
<%@attribute name="entityName" required="true" type="java.lang.String" %>
<div class="row gy-3">
    <div class="col-md-3">
        <button class="w-100 btn btn-primary btn-lg" type="submit">Create ${entityName}</button>
    </div>
    <div class="col-md-6">
        <form>
            <div class="col-md-3">
                <label class="form-label" for="id">Id for search</label>
                <input class="form-control" id="id" placeholder="id" required type="number"></div>

            <div class="col-md-3">
                <button class="w-100 btn btn-primary btn-lg" type="submit">Find ${entityName} by id</button>
            </div>
        </form>
    </div>
</div>
<hr class="my-4">
<hr class="my-4">
