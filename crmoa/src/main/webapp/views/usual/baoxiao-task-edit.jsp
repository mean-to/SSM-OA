<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"  %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
<title>编辑附件</title>
<link rel="stylesheet" type="text/css" href="skin/css/base.css">
<script type="application/javascript" src="${pageContext.request.contextPath}/js/jquery.min.js"></script>
<script type="text/javascript">
	function commit(st) {
      $("#st").val(st);
      $("#form10").submit();
	}
</script>
</head>
<body leftmargin="8" topmargin="8" background='skin/images/allbg.gif'>
<!--  快速转换位置按钮  -->
<table width="98%" border="0" cellpadding="0" cellspacing="1" bgcolor="#D1DDAA" align="center">
<tr>
 <td height="26" background="skin/images/newlinebg3.gif">
  <table width="58%" border="0" cellspacing="0" cellpadding="0">
  <tr>
  <td >
    当前位置:项目管理>>报销审批
 </td>
 </tr>
</table>
</td>
</tr>
</table>

<form name="form2" id="form10" action="${pageContext.request.contextPath}/us/upBxStatus" method="post">

<table width="98%" border="0" cellpadding="2" cellspacing="1" bgcolor="#D1DDAA" align="center" style="margin-top:8px">
<tr >
	<td colspan="2"  align='left' bgcolor="#FFFFFF" onMouseMove="javascript:this.bgColor='#FCFDEE';" onMouseOut="javascript:this.bgColor='#FFFFFF';" height="22">
		<input  type="hidden" name="bxid" id="bxid" value="${bx.bxid}"/>
	</td>
</tr>
<tr >
	<td align="right" bgcolor="#FAFAF1" height="22">金额：</td>
	<td  align='left' bgcolor="#FFFFFF" onMouseMove="javascript:this.bgColor='#FCFDEE';" onMouseOut="javascript:this.bgColor='#FFFFFF';" height="22">
		<input value="${bx.totalmoney}" readonly="true"/></td>
</tr>
<tr >
	<td align="right" bgcolor="#FAFAF1" height="22">使用时间</td>
	<td  align='left' bgcolor="#FFFFFF" onMouseMove="javascript:this.bgColor='#FCFDEE';" onMouseOut="javascript:this.bgColor='#FFFFFF';" height="22">
		<input value="<fmt:formatDate value='${bx.bxtime}' pattern='yyyy-MM-dd' ></fmt:formatDate>" readonly="true" /></td>
</tr>
<tr >
	<td align="right" bgcolor="#FAFAF1" >备注：</td>
	<td colspan=3 align='left' bgcolor="#FFFFFF" onMouseMove="javascript:this.bgColor='#FCFDEE';" onMouseOut="javascript:this.bgColor='#FFFFFF';" >
		<textarea rows=10 cols=130 readonly="true">${bx.bxremark}</textarea>
	</td>
</tr>
	<tr >
	<td align="right" bgcolor="#FAFAF1" >批准信息：</td>
	<td colspan=3 align='left' bgcolor="#FFFFFF" onMouseMove="javascript:this.bgColor='#FCFDEE';" onMouseOut="javascript:this.bgColor='#FFFFFF';" >
		<!-- 批注信息 -->
		<textarea rows=10 id="pizhu" cols=130 name="result" placeholder="${bx.result}"></textarea>
	</td>
</tr >

<tr bgcolor="#FAFAF1">
<td height="28" colspan=4 align=center>
	<input type="hidden" id="st" name="bxstatus">
	<a href="javascript:commit(1)" class="coolbg">同意</a>
	<a href="javascript:commit(2)" class="coolbg">驳回</a>
</td>
</tr>
</table>

</form>
  

</body>
</html>