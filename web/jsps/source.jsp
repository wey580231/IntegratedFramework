<%--
  Created by IntelliJ IDEA.
  User: XY
  Date: 2017/5/3
  Time: 9:11
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>资源工组测试</title>

    <style type="text/css">
        * {
            font-size: 10pt;
        }

        body {
            text-align: center;
            margin: 0px;
        }

        .table {
            width: 100%;
            height: 100%;
            border: 1px solid gray; /*固定边框,1像素*/
            border-collapse: collapse; /*单线的列表边框*/
        }

        .table td {
            border: 1px solid gray; /*固定边框,1像素*/
        }

        iframe {
            width: 100%;
            height: 100%;
        }
    </style>
</head>
<body>
<table class="table" align="center">
    <tr style="background: #3873D6; " height="50">
        <td colspan="2" align="center">
            <iframe frameborder="0" style=" height: 50px" src="<c:url value='/jsps/top.jsp'/>" name="top"></iframe>
        </td>
    </tr>
    <tr>
        <td width="200" style="padding:5px;background: #3873D6;" align="center" valign="top">
            <iframe frameborder="0" width="120" src="<c:url value='/jsps/NavMenu.jsp'/>" name="NavMenu"></iframe>
        </td>
        <td>
            <%--<iframe frameborder="0" src="<c:url value='/jsps/body.jsp'/>" name="body"></iframe>--%>
            <iframe frameborder="0" src="<c:url value='/jsps/guide.jsp'/>" name="OrderList"></iframe>
        </td>
    </tr>
</table>
</body>
</html>
