<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page language="java"  pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
<title>发件箱</title>
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
    当前位置:信息箱>>通知公告
 </td>
	  <td>
		  <input type='button' class="coolbg np" onClick="location='notice-send.jsp';" value='发布新通告' />
	  </td>
 </tr>
</table>
</td>
</tr>
</table>

<!--  搜索表单  -->

<!--  内容列表   -->
<form name="form2">

<table width="98%" border="0" cellpadding="2" cellspacing="1" bgcolor="#D1DDAA" align="center" style="margin-top:8px">
<tr bgcolor="#E7E7E7">
	<td height="24" colspan="12" background="skin/images/tbg.gif">&nbsp;发件箱&nbsp;</td>
</tr>
<tr align="center" bgcolor="#FAFAF1" height="22" id="tr2">
	<td width="4%">选择</td>
	<td width="6%">序号</td>
	<td width="10%">标题</td>
	<td width="10%">内容</td>
	<td width="8%">发送时间</td>
	<td width="8%">操作</td>
</tr>
<c:forEach items="${info.list}" var="notice" varStatus="cou">
<tr align='center' bgcolor="#FFFFFF" onMouseMove="javascript:this.bgColor='#FCFDEE';" onMouseOut="javascript:this.bgColor='#FFFFFF';" height="22" >
	<td><input name="id" type="checkbox" id="id" value="${notice.nid}" class="np"></td>
	<td>${cou.count}</td>
	<td>${notice.ntitle}?</td>
	<td align="center"><span >${notice.remark}</span></td>
	<td>
		<fmt:formatDate value='${notice.ndate}' pattern="yyyy-MM-dd"></fmt:formatDate>

	</td>
	<td><a >删除</a></td>
</tr>
</c:forEach>
	<tr>
		<td colspan="6">
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
			location.href="<c:url value="/notice"/>?pageNO="+(new_page_index+1);
		}
	</script>


	<tr bgcolor="#FAFAF1">
<td height="28" colspan="12">
	&nbsp;
	<a href="" class="coolbg">全选</a>
	<a href="" class="coolbg">反选</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	<a href="" class="coolbg">&nbsp;删除&nbsp;</a>
	<a href="" class="coolbg">&nbsp;导出Excel&nbsp;</a>
</td>
</tr>
<tr align="right" bgcolor="#EEF4EA">
	<td height="36" colspan="12" align="center"><!--翻页代码 --></td>
</tr>
</table>

</form>
  

</body>
</html>