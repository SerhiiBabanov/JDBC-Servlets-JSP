<%@tag description="Overall Page template" pageEncoding="UTF-8" %>
<script>
    function makePUTrequest() {
        $.ajax({
            url: 'test.html',
            type: 'PUT',
            success: function (result) {
                // Do something with the result
            }
        });
    }

    function makeDELETErequest(urlParametr) {
        $.ajax({
            url: urlParametr,
            type: 'DELETE',
            success: function (result) {
                // Do something with the result
            }
        });
    }
</script>
