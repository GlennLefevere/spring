<%@page contentType='text/html' pageEncoding='UTF-8' session='false'%>
<%@taglib prefix='c' uri='http://java.sun.com/jsp/jstl/core'%>
<%@taglib prefix='v' uri='http://vdab.be/tags'%>
<!doctype html>
<html lang='nl'>
<v:head title='Werknemers' />
<body>
	<v:menu />
	<h1>Werknemers</h1>
	<ul>
		<c:forEach items='${werknemers}' var='werknemer'>
			<li>${werknemer.voornaam} ${werknemer.familienaam} (${werknemer.filiaal.naam})</li>
		</c:forEach>
	</ul>
</body>
</html>