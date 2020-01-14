<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<html>
<head>
	<title>Barcode</title>
	
</head>
<body>

<p align=left>
	<img alt="Barcode image" src="<c:url value="/${barcodeUrl}"/>"/><br/>
	${barcodeText}
</p>

</body>
</html>
