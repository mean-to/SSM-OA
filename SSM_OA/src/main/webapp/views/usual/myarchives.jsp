<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java"  pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>展示个人档案</title>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/skin/css/base.css">
	<script type="application/javascript" src="${pageContext.request.contextPath}/js/jquery.min.js"></script>

</head>
<body leftmargin="8" topmargin="8" background='skin/images/allbg.gif'>

<!--  快速转换位置按钮  -->
<table width="98%" border="0" cellpadding="0" cellspacing="1" bgcolor="#D1DDAA" align="center">
<tr>
 <td height="26" background="skin/images/newlinebg3.gif">
  <table width="58%" border="0" cellspacing="0" cellpadding="0">
  <tr>
  <td >
    当前位置:档案>>个人档案信息
 </td>
 </tr>
</table>
</td>
</tr>
</table>

<form name="form2" id="forms" action="${pageContext.request.contextPath}/arc/saveInfo" method="POST">

<table width="98%" border="0" cellpadding="2" cellspacing="1" bgcolor="#D1DDAA" align="center" style="margin-top:8px">
<tr bgcolor="#E7E7E7">
	<td height="24" colspan="12" background="skin/images/tbg.gif">&nbsp;个人档案信息&nbsp;</td>
</tr>
<tr >
	<td align="right" bgcolor="#FAFAF1" height="22" >员工姓名：</td>
	<td align='left' bgcolor="#FFFFFF" onMouseMove="javascript:this.bgColor='#FCFDEE';" onMouseOut="javascript:this.bgColor='#FFFFFF';" height="22">
		<input type="text" name="ename" id="pname"  value="${emp.ename}"/>
	</td>

	<td align="right" bgcolor="#FAFAF1" height="22">性别：</td>
	<td align='left' bgcolor="#FFFFFF" onMouseMove="javascript:this.bgColor='#FCFDEE';" onMouseOut="javascript:this.bgColor='#FFFFFF';" height="22">

		<input type="radio" name="esex" <c:if test="${emp.esex==0}"> checked</c:if> value="0"/>男
		<input type="radio" name="esex" <c:if test="${emp.esex==1}"> checked</c:if> value="1"/>女
	</td>
</tr>
<tr >
	<td align="right" bgcolor="#FAFAF1" height="22">毕业院校：</td>
	<td align='left' bgcolor="#FFFFFF" onMouseMove="javascript:this.bgColor='#FCFDEE';" onMouseOut="javascript:this.bgColor='#FFFFFF';" height="22">
		<input type="text"  id="arc.school" name="school" value="${emp.arc.school}"/>
	</td>
	<td align="right" bgcolor="#FAFAF1" height="22">专业：</td>
	<td align='left' bgcolor="#FFFFFF" onMouseMove="javascript:this.bgColor='#FCFDEE';" onMouseOut="javascript:this.bgColor='#FFFFFF';" height="22">
		<input type="text"  id="zhuanye" name="arc.zhuanye" value="${emp.arc.zhuanye}"/>
	</td>
</tr>
<input type="hidden" name="aa" value="-1" />
<tr >
	<td align="right" bgcolor="#FAFAF1" height="22" >生日：</td>
	<td align='left' bgcolor="#FFFFFF" onMouseMove="javascript:this.bgColor='#FCFDEE';" onMouseOut="javascript:this.bgColor='#FFFFFF';" height="22">
		<input type="text" name="arc.biyedate" value="<fmt:formatDate value='${emp.arc.biyedate}' pattern="yyyy-MM-dd"></fmt:formatDate>"/></td>

	<td align="right" bgcolor="#FAFAF1" height="22">民族：</td>
	<td align='left' bgcolor="#FFFFFF" onMouseMove="javascript:this.bgColor='#FCFDEE';" onMouseOut="javascript:this.bgColor='#FFFFFF';" height="22">
		<input type="text" name="arc.minzu" value="${emp.arc.minzu}"/></td>
</tr>

<tr >
	<td align="right" bgcolor="#FAFAF1" height="22">入职时间：</td>
	<td align='left' bgcolor="#FFFFFF" onMouseMove="javascript:this.bgColor='#FCFDEE';" onMouseOut="javascript:this.bgColor='#FFFFFF';" height="22">
		<input type="text" name="hirdate" value="<fmt:formatDate value='${emp.hiredate}' pattern="yyyy-MM-dd"></fmt:formatDate>"/></td>

	<td align="right" bgcolor="#FAFAF1" height="22">紧急联系人：</td>
	<td align='left' bgcolor="#FFFFFF" onMouseMove="javascript:this.bgColor='#FCFDEE';" onMouseOut="javascript:this.bgColor='#FFFFFF';" height="22">
		<input type="text" name="arc.sosperson" id="sosperson" value="${emp.arc.sosperson}" /></td>
</tr>
<tr >
	<td align="right" bgcolor="#FAFAF1" height="22">级别：</td>
	<td align='left' bgcolor="#FFFFFF" onMouseMove="javascript:this.bgColor='#FCFDEE';" onMouseOut="javascript:this.bgColor='#FFFFFF';" height="22">
		<input type="text"  id="level" readonly="true" value="${emp.level.jname}" /></td>
	</td>

	<td align="right" bgcolor="#FAFAF1" height="22">职位：</td>
	<td align='left' bgcolor="#FFFFFF" onMouseMove="javascript:this.bgColor='#FCFDEE';" onMouseOut="javascript:this.bgColor='#FFFFFF';" height="22">
		<input type="text"  id="position" readonly="true" value="${emp.pos.name}"/></td>
</tr>
	<tr >
		<td align="right" bgcolor="#FAFAF1" height="22">部门：</td>
		<td align='left' bgcolor="#FFFFFF" onMouseMove="javascript:this.bgColor='#FCFDEE';" onMouseOut="javascript:this.bgColor='#FFFFFF';" height="22">
			<input type="text" name="dname" id="dname" readonly="true" value="${emp.dept.dname}"/></td>
		</td>

		<td align="right" bgcolor="#FAFAF1" height="22">邮箱：</td>
		<td align='left' bgcolor="#FFFFFF" onMouseMove="javascript:this.bgColor='#FCFDEE';" onMouseOut="javascript:this.bgColor='#FFFFFF';" height="22">
			<input type="text" name="arc.email" readonly="true" value="${emp.arc.email}"/></td>
	</tr>



<tr >
	<input type="hidden" name="arc.dnum" value="${emp.arc.dnum}"/>
	<td align="right" bgcolor="#FAFAF1" >备注：</td>
	<td colspan=3 align='left' bgcolor="#FFFFFF" onMouseMove="javascript:this.bgColor='#FCFDEE';" onMouseOut="javascript:this.bgColor='#FFFFFF';" >
		<textarea type="text" rows=15 cols=130 name="remark" >${emp.remark}</textarea><span id="number"></span>
	</td>
</tr>


<tr bgcolor="#FAFAF1">
<td height="28" colspan=4 align=center>
	&nbsp;
	<A class="coolbg" onclick="commit()" >保存</A>
	<a href="project-base.jsp" class="coolbg">返回</a>
	<a href="${pageContext.request.contextPath}/us" class="coolbg">下载</a>
</td>
</tr>
</table>

</form>
  <script type="text/javascript">
	  function commit() {
		  $("#forms").submit();
	  }
  </script>

</body>
</html>