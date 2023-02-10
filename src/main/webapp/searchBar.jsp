<%--
  Created by IntelliJ IDEA.
  User: jhon
  Date: 10/02/23
  Time: 16:10
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
	<title>SearchBar</title>
	<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-GLhlTQ8iRABdZLl6O3oVMWSktQOp6b7In1Zl3/Jr59b6EGGoI1aFkw7cmDA6j6gD" crossorigin="anonymous">
</head>
<body>

	<div class="form-outline mb-4">
		<input type="search" class="form-control" id="datatable-search-input">
		<label class="form-label" for="datatable-search-input">Search</label>
	</div>

	<div id="datatable">
	</div>

<script>
    const data2 = {
        columns: ['Name', 'Position', 'Office', 'Age', 'Start date', 'Salary'],
        rows: [
            ['Tiger Nixon', 'System Architect', '	Edinburgh', 61, '2011/04/25', '$320,800'],
            ['Sonya Frost', 'Software Engineer', 'Edinburgh', 23, '2008/12/13', '$103,600'],
            ['Jena Gaines', 'Office Manager', 'London', 30, '2008/12/19', '$90,560'],
            ['Quinn Flynn', 'Support Lead', 'Edinburgh', 22, '2013/03/03', '$342,000'],
            ['Charde Marshall', 'Regional Director', 'San Francisco', 36, '2008/10/16', '$470,600'],
            ['Haley Kennedy', 'Senior Marketing Designer', 'London', 43, '2012/12/18', '$313,500'],
            ['Tatyana Fitzpatrick', 'Regional Director', 'London', 19, '2010/03/17', '$385,750'],
            ['Michael Silva', 'Marketing Designer', 'London', 66, '2012/11/27', '$198,500'],
            ['Paul Byrd', 'Chief Financial Officer (CFO)', 'New York', 64, '2010/06/09', '$725,000'],
            ['Gloria Little', 'Systems Administrator', 'New York', 59, '2009/04/10', '$237,500'],
        ],
    };

    const instance = new mdb.Datatable(document.getElementById('datatable'), data2)

    document.getElementById('datatable-search-input').addEventListener('input', (e) => {
        instance.input-group(e.target.value);
    });

</script>

<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.6/dist/umd/popper.min.js" integrity="sha384-oBqDVmMz9ATKxIep9tiCxS/Z9fNfEXiDAYTujMAeBAsjFuCZSmKbSSUnQlmh/jp3" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.min.js" integrity="sha384-mQ93GR66B00ZXjt0YO5KlohRA5SY2XofN4zfuZxLkoj1gXtW8ANNCe9d5Y3eG5eD" crossorigin="anonymous"></script>

</body>
</html>
