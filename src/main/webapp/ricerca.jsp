<%--
  Created by IntelliJ IDEA.
  User: jhon
  Date: 11/02/23
  Time: 16:09
  To change this template use File | Settings | File Templates.
--%>
<html>
<head>
	<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>

	<script>
        $(document).ready(function(){
            $("#searchTerm").keyup(function(){
                var searchTerm = $(this).val();
                if (searchTerm.length > 0) {
                    $.ajax({
                        type: "POST",
                        url: "SearchServlet",
                        data: {searchTerm: searchTerm},
                        success: function(data){
                            $("#searchResults").html(data);
                        }
                    });
                } else {
                    $("#searchResults").html("");
                }
            });
        });
	</script>
</head>
<body>

	<div class="container">
		<input type="text" id="searchTerm" class="form-control" autocomplete="off">
		<br>
		<div id="searchResults"></div>
	</div>

</body>
</html>