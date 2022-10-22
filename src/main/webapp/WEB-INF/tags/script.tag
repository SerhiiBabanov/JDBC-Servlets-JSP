<%@tag description="Overall Page template" pageEncoding="UTF-8" %>
<script>
    function makePUTrequest() {
        $.ajax({
            url: 'test.html',
            type: 'PUT',
            success: function () {
                location.reload();
            }
        });
    }

    function makeDELETErequest(urlParametr) {
        if (confirm('Are you sure you want to delete this?')) {
            $.ajax({
                url: urlParametr,
                type: 'DELETE',
                success: function () {
                    location.reload();
                }
            });
        }

    }
</script>
