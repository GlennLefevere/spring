<%@ page contentType='text/html' pageEncoding='UTF-8' session='false'%>
<%@taglib prefix='c' uri='http://java.sun.com/jsp/jstl/core'%>
<%@taglib prefix='form' uri='http://www.springframework.org/tags/form'%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<form:label path='naam'>Naam:
<form:errors path='naam' cssClass='fout'/></form:label>
<form:input path='naam' autofocus='autofocus' required='true'/>
<form:label path='adres.straat'>Straat:
<form:errors path='adres.straat' cssClass='fout'/></form:label>
<form:input path='adres.straat' required='true'/>
<form:label path='adres.huisNr'>Huisnr.:
<form:errors path='adres.huisNr' cssClass='fout'/></form:label>
<form:input path='adres.huisNr' required='true'/>
<form:label path='adres.postcode'>Postcode:
<form:errors path='adres.postcode' cssClass='fout'/></form:label>
<form:input path='adres.postcode' required='true' type='number' min='1000'
max='9999'/>
<form:label path='adres.gemeente'>Gemeente:
<form:errors path='adres.gemeente' cssClass='fout'/></form:label>
<form:input path='adres.gemeente' required='true'/>
<form:label path='inGebruikName'>Ingebruikname:
<form:errors path='inGebruikName' cssClass='fout'/></form:label>
<form:input path='inGebruikName' required='true'/>
<form:label path='waardeGebouw'>Waarde gebouw:
<form:errors path='waardeGebouw' cssClass='fout'/></form:label>
<form:input path='waardeGebouw' required='true'/>
<div class='rij'><form:checkbox path='hoofdFiliaal' label='Hoofdfiliaal'/></div>
</body>
</html>