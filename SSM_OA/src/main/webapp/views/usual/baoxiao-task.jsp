<%@ page language="java"  pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"  %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
<title>报销管理</title>
<link rel="stylesheet" type="text/css" href="skin/css/base.css">
</head>
<body leftmargin="8" topmargin="8" background='skin/images/allbg.gif'>

<!--  快速转换位置按钮  -->
<table width="98%" border="0" cellpadding="0" cellspacing="1" bgcolor="#D1DDAA" align="center">
<tr>
 <td height="26" background="skin/images/newlinebg3.gif">
  <table width="58%" border="0" cellspacing="0" cellpadding="0">
  <tr>
  <td >
    当前位置:报销管理>>报销列表
 </td>

 </tr>
</table>
</td>
</tr>
</table>


<!--  内容列表   -->
<form name="form2">

<table width="98%"  cellpadding="2" cellspacing="1" bgcolor="#D1DDAA" align="center" style="margin-top:8px">
<tr bgcolor="#E7E7E7">
	<td height="24" colspan="12" background="skin/images/tbg.gif">&nbsp;报销单列表&nbsp;</td>
</tr>
<tr align="center" bgcolor="#FAFAF1" height="22">
	<td width="4%">选择</td>
	<td width="10%">编号</td>
	<td width="10%">类型</td>
	<td width="10%">报销人</td>
	<td width="6%">总金额</td>
	<td width="10%">使用时间</td>
	<td width="30%">备注信息</td>
	<td width="10%">审批状态</td>
	<c:if test="${flag==0}">
	<td width="10%">
			操作
	</td>
	</c:if>
</tr>

	<c:forEach items="${info.list}" var="bs" varStatus="cou">
<tr align='center' bgcolor="#FFFFFF" onMouseMove="javascript:this.bgColor='#FCFDEE';" onMouseOut="javascript:this.bgColor='#FFFFFF';" height="22" >
	<td><input name="id" type="checkbox" id="id" value="${bs.bxid}" class="np"></td>
	<td>${cou.count}</td>
	<td>${bs.paymode}</td>
	<td>${bs.emp.ename}</td>
	<td>${bs.totalmoney}</td>
	<td align="center"><a href=''><u> <fmt:formatDate value="${bs.bxtime}" pattern="yyyy-MM-dd"></fmt:formatDate> </u></a></td>
	<td>${bs.bxremark}</td>
	<td>
		<c:if test="${bs.bxstatus==0}">	财务未审批</c:if>
		<c:if test="${bs.bxstatus!=0}">	已审批</c:if>
	</td>
	<c:if test="${flag==0}">
		<%--<td><a href="views/usual/baoxiao-task-edit.jsp">审批</a> </td>--%>
		<td><a href="${pageContext.request.contextPath}/bx/getBxId?id=${bs.bxid}">审批</a> </td>
	</c:if>
</tr>
	</c:forEach>
	<tr>
		<td colspan="9">
			<div id="pager" style="width:20%;float:right">
			</div>
		</td>
	</tr>
	<link href="${pageContext.request.contextPath}/page/pagination.css"  type="text/css" rel="stylesheet" />
	<script type="text/javascript" src="${pageContext.request.contextPath}/page/jquery-1.10.2.min.js" ></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/page/jquery.pagination.js" ></script>
	<script type="text/javascript">
		//初始化分页组件
		var count=${info.total};//总条数
		var size=${info.pageSize};//每页条数
		var pageNO=${info.pageNum};//页码
		//alert(count+"==="+size+"==="+pageNO);
		$("#pager").pagination(count, {
			items_per_page:size,
			current_page:pageNO-1,
			next_text:"下一页",
			prev_text:"上一页",
			num_edge_entries:2,
			load_first_page:false,
			callback:handlePaginationClick
		});

		//回调方法（点击上一页，下一页，某个页码时执行的函数）
		function handlePaginationClick(new_page_index, pagination_container){

			location.href="<c:url value="/bx"/>?pageNO="+(new_page_index+1);
		}
	</script>


	<tr bgcolor="#FAFAF1">
		<td height="28" colspan="12">
			<a href="${pageContext.request.contextPath}/bx" class="coolbg">&nbsp;已审批的报销&nbsp;</a>
		</td>
	</tr>

<tr align="right" bgcolor="#EEF4EA">
	<td height="36" colspan="12" align="center"><!--翻页代码 --></td>
</tr>
</table>

</form>
  

</body>
</html>