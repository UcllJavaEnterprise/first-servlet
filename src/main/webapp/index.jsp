<%@ page import="java.text.SimpleDateFormat, java.util.Date" %>
<html>
<head>
   <title>App. server tijd</title>
</head>
<body>
<div style="text-align: center;">
   <h1>Welkom op de applicatieserver de datum en tijd is nu</h1>
</div>
<%
   Date dNow = new Date( );
   SimpleDateFormat ft = new SimpleDateFormat ("dd.MM.yyyy HH:mm:ss");
   out.print( "<h2 align=\"center\">" + ft.format(dNow) + "</h2>");
%>
</body>
</html>