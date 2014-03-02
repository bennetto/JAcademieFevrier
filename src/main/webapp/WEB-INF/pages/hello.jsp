<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring" %>

<html>
<head>
	<title>Hello SpringMVC</title>
</head>
<body>
<h1>Test</h1>
<h1><spring:message code="label.hello"/> <c:out value="${name}"/></h1>
</body>
</html>