<%@tag description="Overall Page template" pageEncoding="UTF-8" %>
<script>
    function makePUTrequest(urlParametr) {
        $.ajax({

            url: urlParametr,
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
    var putMethod = ( event ) => {
        event.preventDefault();
        var button = document.querySelectorAll("[name=update_data]");
        var url = button[0].getAttribute("resource")
        var messages = document.querySelectorAll("[class=form-control]");
        var body ={};
        messages.forEach(element=>{
            var name = element.getAttribute("name");
            if ( element.tagName != "TEXTAREA" ) {
                var value = element.value;
            } else {
                // if element is textarea, value attribute may return null or undefined
                var value = element.innerHTML;
            }
            body[name] = value;
        })
        var xhr = new XMLHttpRequest();
        xhr.open( "PUT", url );
        xhr.setRequestHeader( "Content-Type", "application/json" );
        xhr.onload = () => {
            if ( xhr.status === 200 ) {
                // reload() uses cache, reload( true ) force no-cache. I reload the page to make "redirects normal effect" of HTML form when submit. You can manipulate DOM instead.
                location.reload( true );
            } else {
                console.log( xhr.status, xhr.responseText );
            }
        }
        xhr.send( JSON.stringify(body) );
    };
    document.querySelectorAll( "[name=update_data]" ).forEach( element => {
        var button = element;
        button.addEventListener( "click", putMethod);
    })
</script>
