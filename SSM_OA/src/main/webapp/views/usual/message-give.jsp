<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page language="java"  pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
<title>收件箱</title>
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
    当前位置:消息推送>>展示消息
 </td>
      <td>
          <input type='button' class="coolbg np" onClick="location='views/usual/message-seed.jsp';" value='添加消息' />
      </td>
 </tr>
</table>
</td>
</tr>
</table>


<!--  内容列表   -->
<form name="form2">

<table width="98%" border="0" cellpadding="2" cellspacing="1" bgcolor="#D1DDAA" align="center" style="margin-top:8px">
<tr bgcolor="#E7E7E7">
	<td height="24" colspan="12" background="skin/images/tbg.gif">&nbsp;消息列表&nbsp;</td>
</tr>
<tr align="center" bgcolor="#FAFAF1" height="22">
	<td width="4%">选择</td>
	<td width="6%">序号</td>
	<td width="10%">内容</td>
	<td width="10%">收件人</td>
	<td width="8%">收件时间</td>
	<td width="8%">操作</td>
</tr>
<c:forEach items="${info.list}" var="msg" varStatus="cou">
<tr align='center' bgcolor="#FFFFFF" onMouseMove="javascript:this.bgColor='#FCFDEE';" onMouseOut="javascript:this.bgColor='#FFFFFF';" height="22" >
	<td><input name="id" type="checkbox" id="id" value="${msg.msgid}" class="np"></td>
	<td>${cou.count}</td>
	<td align="center"><span >${msg.msgcontent}</span></td>
	<td>${msg.emp.ename}</td>
	<td>
		<fmt:formatDate value='${msg.msgtime}' pattern="yyyy-MM-dd"></fmt:formatDate>
	</td>
	<td><a >删除</a></td>
</tr>

</c:forEach>
	<tr>
		<td colspan="7">
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
			/*$("#pg").val(new_page_index+1);//查询的页面保存到隐藏域
			$("#prose").submit();*/
			  location.href="<c:url value="/msg"/>?pageNO="+(new_page_index+1);
		}
	</script>
<tr bgcolor="#FAFAF1">
<td height="28" colspan="12">
	&nbsp;
	<a href="" class="coolbg">全选</a>
	<a href="" class="coolbg">反选</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	<a href="" class="coolbg">&nbsp;删除&nbsp;</a>

</td>
</tr>
<tr align="right" bgcolor="#EEF4EA">
	<td height="36" colspan="12" align="center"><!--翻页代码 --></td>
</tr>
</table>

</form>
  

</body>
</html>