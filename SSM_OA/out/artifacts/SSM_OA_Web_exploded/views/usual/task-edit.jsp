<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java"  pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
<title>编辑任务</title>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/skin/css/base.css">
	<script type="application/javascript" src="${pageContext.request.contextPath}/js/jquery.min.js"></script>
	<script type="text/javascript">
		$(function () {
var aid='${m.analysisFk}';
			$.ajax({
				url:'${pageContext.request.contextPath}/mou/getMouPro',
				dataType:'json',
				type:'post',
				success:function (data) {
					$.each(data,function (index,ana) {
						var ana1=ana.id + "_" + ana.title + "_" + ana.proname;
						if(aid==ana.id){
							$("#pro").append("<option selected value='" +ana1 + "'>" + ana.proname + "</option>");
							addayalisys(ana1);
						}

						})

				}
			})
			var empk='${task.empFk2}';
			$.ajax({
				url:'${pageContext.request.contextPath}/task/getIdEmp',
				dataType:'json',
				type:'post',
				success:function (data) {
					$.each(data,function (index,emp) {
						if(empk==emp.eid){
							$("#emp").append("<option  selected value='"+emp.eid+"'>"+ emp.ename+"</option>");
						}

					})

				}
			})

		})
		function addayalisys(idAndProTit) {
			var  idAndProTit=idAndProTit.split("_");
			var mid='${m.id}';
			var title=idAndProTit[1];
			$("#anid option").remove();
			$("#anid").append("<option value=''>"+title+"</option>");
			$("#mod").html("<option value=''>请选择模块</option>")
			$.ajax({
				url:'${pageContext.request.contextPath}/fun/getModsByAid',
				dataType:'json',
				data:{"id":idAndProTit[0]},
				type:'post',
				success:function (data) {
					$.each(data,function (index,mou) {
						if(mou.id==mid){
							$("#mod").append("<option selected value='"+mou.id+"'>"+mou.modname+"</option>");
							addfunc(mou.id);
						}

					})
				}
			})

		}
		function addfunc(modK) {
			var fid='${task.funFk}';
			$("#fun").html("<option value=''>请选择功能</option>")
			$.ajax({
				url:'${pageContext.request.contextPath}/fun/getFunsByFk',
				data:{"id":modK},
				dataType:'json',
				type:'post',
				success:function (data) {
					$.each(data,function (index,fun) {
				if(fid==fun.id){
						$("#fun").append("<option selected value='"+fun.id+"'>"+fun.functionname+"</option>")
				}
					})
				}
			})


		}
		function commit() {
			$("#form2").submit();
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
    当前位置:任务管理>>编辑任务
 </td>
 </tr>
</table>
</td>
</tr>
</table>

<form name="form2" action="task/updatetask" method="post" id="form2">

<table width="98%" border="0" cellpadding="2" cellspacing="1" bgcolor="#D1DDAA" align="center" style="margin-top:8px">
<tr bgcolor="#E7E7E7">
	<td height="24" colspan="2" background="skin/images/tbg.gif">&nbsp;编辑任务&nbsp;</td>
</tr>
<tr >

	<td align="right" bgcolor="#FAFAF1" height="22">参考位置：</td>
	<td  align='left' bgcolor="#FFFFFF" onMouseMove="javascript:this.bgColor='#FCFDEE';" onMouseOut="javascript:this.bgColor='#FFFFFF';" height="22">
		<select onchange="addayalisys(this.value)" id="pro">
			<option value=0>请选择项目</option>
		</select>-
		<select id="anid">
			<option value=0>请选择需求</option>
		</select >-
		<select id="mod" onchange="addfunc(this.value)">
		<option value=0>请选择模块</option>
		</select>-
		<select  id="fun" name="funFk">
			<option value=0>请选择功能</option>

	</select></td>
</tr>
<tr >
	<td align="right" bgcolor="#FAFAF1" height="22">任务标题：</td>
	<td  align='left' bgcolor="#FFFFFF" onMouseMove="javascript:this.bgColor='#FCFDEE';" onMouseOut="javascript:this.bgColor='#FFFFFF';" height="22">
		<input name="tasktitle" value="${task.tasktitle}"/></td>
</tr>
<tr >
	<td align="right" bgcolor="#FAFAF1" height="22">开始时间：</td>
	<td  align='left' bgcolor="#FFFFFF" onMouseMove="javascript:this.bgColor='#FCFDEE';" onMouseOut="javascript:this.bgColor='#FFFFFF';" height="22">
		<input name="starttime" value="<fmt:formatDate value='${task.starttime}' pattern="yyyy-MM-dd"/>"/></td>
</tr>
<tr >
	<td align="right" bgcolor="#FAFAF1" height="22">结束时间：</td>
	<td  align='left' bgcolor="#FFFFFF" onMouseMove="javascript:this.bgColor='#FCFDEE';" onMouseOut="javascript:this.bgColor='#FFFFFF';" height="22">
		<input name="endtime" value="${task.endtime}"/></td>
</tr>
<tr >
	<td align="right" bgcolor="#FAFAF1" height="22">执行者：</td>
	<td  align='left' bgcolor="#FFFFFF" onMouseMove="javascript:this.bgColor='#FCFDEE';" onMouseOut="javascript:this.bgColor='#FFFFFF';" height="22">
		<select  id="emp"  name="empFk2">
			<option></option>
		</select>
	</td>
</tr>
<tr >
	<td align="right" bgcolor="#FAFAF1" height="22">优先级：</td>
	<td  align='left' bgcolor="#FFFFFF" onMouseMove="javascript:this.bgColor='#FCFDEE';" onMouseOut="javascript:this.bgColor='#FFFFFF';" height="22">
		<select name="level">
			<option <c:if test="${task.level eq '高'}">selected</c:if>>高</option>
			<option <c:if test="${task.level eq '中'}">selected</c:if>>中</option>
			<option <c:if test="${task.level eq '低'}">selected</c:if>>低</option>
			<option <c:if test="${task.level eq '暂缓'}">selected</c:if>>暂缓</option>

		</select>

	</td>
</tr>

<tr >
	<td align="right" bgcolor="#FAFAF1" >详细说明：</td>
	<td colspan=3 align='left' bgcolor="#FFFFFF" onMouseMove="javascript:this.bgColor='#FCFDEE';" onMouseOut="javascript:this.bgColor='#FFFFFF';" >
		<textarea rows=10 cols=130>${task.remark}</textarea>
	</td>
</tr>


<tr bgcolor="#FAFAF1">
<td height="28" colspan=4 align=center>
	&nbsp;
	<a href="" class="coolbg">保存</a>
</td>
</tr>
</table>

</form>


</body>
</html>