<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java"  pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
<title>编辑功能信息</title>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/skin/css/base.css">
	<script type="application/javascript" src="${pageContext.request.contextPath}/js/jquery.min.js"></script>
	<script type="text/javascript">
		$(function () {
			var proname='${f.proname}';//当前编辑的功能对应的项目名
			//alert(proname);
			$.ajax({
				url:'${pageContext.request.contextPath}/pro/getProsHasNeed',
				type:'post',
				dataType:'json',
				success:function (data) {
					$.each(data,function (index,ana) {
						if(ana.proname==proname){
							var opcon=ana.id+"_"+ana.proname+"_"+ana.title;
							$("#pro").append("<option selected value='"+opcon+"'>"+ana.proname+"</option>");
							addayalisys(opcon);//获取选中项目对应的需求
						}else{
							$("#pro").append("<option value='"+ana.id+"_"+ana.proname+"_"+ana.title+"'>"+ana.proname+"</option>");
						}
					});
				}
			});
		})

		function addayalisys(pnameAndAna) {
			var proAndAna=pnameAndAna.split("_");
			var title=proAndAna[2];
			$("#anid option").remove();
			$("#anid").append("<option>"+title+"</option>");
			//异步请求改需求下所有模块
			var mid='${f.modeleFk}';//获取当前要编辑的功能对应的模块
			$("#mod option").remove();
			$.ajax({
				url:'${pageContext.request.contextPath}/pro/getModsByAid',
				type:'post',
				data:{"aid":proAndAna[0]},
				dataType:'json',
				success:function (data) {
					$.each(data,function (index,m) {
						//判断使当前要编辑的功能所属的模块选中
						if(m.id==mid){
							$("#mod").append("<option selected value='"+m.id+"'>"+m.modname+"</option>");
						}else{
							$("#mod").append("<option value='"+m.id+"'>"+m.modname+"</option>");
						}

					})
				}
			});
		}

		function commit() {
			$("#forms").submit();
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
    当前位置:项目管理>>编辑功能信息
 </td>
 </tr>
</table>
</td>
</tr>
</table>

<form name="form2" id="forms" action="${pageContext.request.contextPath}/pro/editFun" method="post">

<table width="98%" border="0" cellpadding="2" cellspacing="1" bgcolor="#D1DDAA" align="center" style="margin-top:8px">
<tr bgcolor="#E7E7E7">
	<td height="24" colspan="2" background="skin/images/tbg.gif">&nbsp;编辑功能&nbsp;</td>
</tr>
<tr >
	<td align="right" bgcolor="#FAFAF1" height="22">选择项目：</td>
	<td  align='left' bgcolor="#FFFFFF" onMouseMove="javascript:this.bgColor='#FCFDEE';" onMouseOut="javascript:this.bgColor='#FFFFFF';" height="22">
		<select id="pro" name="newpro" onchange="addayalisys(this.value)">
			<option value=0>选择系统</option>
		</select>
	</td>
</tr>
<tr >
	<td align="right" bgcolor="#FAFAF1" height="22">选择需求：</td>
	<td  align='left' bgcolor="#FFFFFF" onMouseMove="javascript:this.bgColor='#FCFDEE';" onMouseOut="javascript:this.bgColor='#FFFFFF';" height="22">
		<select id="anid" name="analysisname">
			<option value=0>选择需求</option>
		</select>
	</td>
</tr>
<tr >
	<td align="right" bgcolor="#FAFAF1" height="22">选择模块：</td>
	<td  align='left' bgcolor="#FFFFFF" onMouseMove="javascript:this.bgColor='#FCFDEE';" onMouseOut="javascript:this.bgColor='#FFFFFF';" height="22">
		<select id="mod" name="modeleFk">
			<option value=0>选择模块</option>
		</select>
	</td>
</tr>
<tr >
	<td align="right" bgcolor="#FAFAF1" height="22">功能名称：</td>
	<td  align='left' bgcolor="#FFFFFF" onMouseMove="javascript:this.bgColor='#FCFDEE';" onMouseOut="javascript:this.bgColor='#FFFFFF';" height="22">
		<input value="${f.functionname}" name="functionname"/></td>
</tr>
<tr >
	<td align="right" bgcolor="#FAFAF1" height="22">优先级：</td>
	<td  align='left' bgcolor="#FFFFFF" onMouseMove="javascript:this.bgColor='#FCFDEE';" onMouseOut="javascript:this.bgColor='#FFFFFF';" height="22">
		<select name="level">
			<option <c:if test="${f.level eq '高'}">selected</c:if> >高</option>
			<option <c:if test="${f.level eq '中'}">selected</c:if> >中</option>
			<option <c:if test="${f.level eq '低'}">selected</c:if> >低</option>
			<option <c:if test="${f.level eq '暂缓'}">selected</c:if> >暂缓</option>
		</select>
	</td>
</tr>
<tr >
	<td align="right" bgcolor="#FAFAF1" height="22">简单描述：</td>
	<td align='left' bgcolor="#FFFFFF" onMouseMove="javascript:this.bgColor='#FCFDEE';" onMouseOut="javascript:this.bgColor='#FFFFFF';" height="22">
		<textarea rows=10 cols=130 name="simpledis">${f.simpledis}</textarea></td>
</tr>
<tr >
	<td align="right" bgcolor="#FAFAF1" height="22">详细描述：</td>
	<td align='left' bgcolor="#FFFFFF" onMouseMove="javascript:this.bgColor='#FCFDEE';" onMouseOut="javascript:this.bgColor='#FFFFFF';" height="22">
		<textarea rows=15 cols=130>帐户管理各功能的详细详细信息如下</textarea></td>
</tr>

<tr >
	<td align="right" bgcolor="#FAFAF1" >备注：</td>
	<td colspan=3 align='left' bgcolor="#FFFFFF" onMouseMove="javascript:this.bgColor='#FCFDEE';" onMouseOut="javascript:this.bgColor='#FFFFFF';" >
		<textarea rows=10 cols=130>暂无</textarea>
	</td>
</tr>


<tr bgcolor="#FAFAF1">
<td height="28" colspan=4 align=center>
	<input type="hidden" name="id" value="${f.id}">&nbsp;
	<a href="javascript:commit()" class="coolbg">保存</a>
	<a href="project-function.jsp" class="coolbg">返回</a>
</td>
</tr>
</table>

</form>
  

</body>
</html>