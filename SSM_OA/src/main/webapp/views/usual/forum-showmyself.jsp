<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page language="java" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

    <meta http-equiv="Content-Type" content="text/html; charset=gb2312" />
    <title>main</title>
    <base target="_self">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/skin/css/base.css" />
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/skin/css/main.css" />
    <style type="text/css">
        li:hover{background:lightgray}

    </style>
    <script type="application/javascript" src="${pageContext.request.contextPath}/js/jquery.min.js"></script>
    
</head>
<body leftmargin="8" topmargin='8'>

<table width="98%" align="center" border="1" cellpadding="3"
       cellspacing="1" bgcolor="#CBD8AC"
       style="margin-bottom: 8px; margin-top: 8px; background: white" id="tb">
    <tr id="sp">
        <td colspan="3" background="skin/images/frame/wbg.gif" bgcolor="#EEF4EA"
            class='title'>
            <span>员工论坛</span> |<span>生活广场</span> |<span>租房信息</span>
            <a href='${pageContext.request.contextPath}/views/usual/forum-add.jsp' style='padding-left: 1260px'><font size="5">发发帖</font></a>
        </td>
    </tr>
<c:forEach items="${fmlist}" var="fom" varStatus="cou">
    <c:if test="${cou.count%3==0}">
    <tr bgcolor="#FFFFFF" id="ttr">
    </c:if>
       <td>
            <ul class='notice-list'>
                <li class='ue-clear'>
                    <span id='${fom.forumid}'>
                        <img src='${pageContext.request.contextPath}/images/tx.png' height='50px' width='50px'/>
                    </span>
                    <font>
                        <fmt:formatDate value='${fom.createtime}' pattern="yyyy-MM-dd"></fmt:formatDate>
                    </font>
                    <a href='${pageContext.request.contextPath}/fom/getContent?fid=${fom.forumid}' class='notice-title'>${fom.forumtitle}</a>
                </li>
            </ul>
        </td>
<c:if test="${cou.count%3==2}">
      </tr>

</c:if>
</c:forEach>

</table>

</body>
</html>