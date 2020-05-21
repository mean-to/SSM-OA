<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page language="java"  pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
<title>档案信息管理</title>
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/skin/css/base.css">
	<script type="application/javascript" src="${pageContext.request.contextPath}/js/jquery.min.js"></script>

<body leftmargin="8" topmargin="8" background='skin/images/allbg.gif'>

<!--  快速转换位置按钮  -->
<table width="98%" border="0" cellpadding="0" cellspacing="1" bgcolor="#D1DDAA" align="center">
<tr>
 <td height="26" background="skin/images/newlinebg3.gif">
  <table width="58%" border="0" cellspacing="0" cellpadding="0">
  <tr>
  <td >
    当前位置:项目管理>>档案信息管理
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
	<td height="24" colspan="12" background="skin/images/tbg.gif">&nbsp;员工档案信息列表&nbsp;</td>
</tr>
<tr align="center" bgcolor="#FAFAF1" height="22">
	<td width="4%">选择</td>
	<td width="6%">序号</td>
	<td width="10%">姓名</td>
	<td width="10%">年龄</td>
	<td width="10%">毕业院校</td>
	<td width="8%">入职时间</td>
	<td width="5%">联系方式</td>
	<td width="8%">学历</td>
	<td width="6%">邮箱</td>
	<td width="8%">政治面貌</td>
	<td width="8%">民族</td>	
	<td width="10%">操作</td>
</tr>
<c:forEach items="${info.list}" var="em" varStatus="cou">
<tr align='center' bgcolor="#FFFFFF" onMouseMove="javascript:this.bgColor='#FCFDEE';" onMouseOut="javascript:this.bgColor='#FFFFFF';" height="22" >
	<td><input name="id" type="checkbox" id="id" value="${em.eid}" class="np"></td>
	<td>${cou.count}</td>
	<td align="left"><a href=''><u>${em.ename}</u></a></td>
	<td>${em.eage}</td>
	<td>${em.arc.school}</td>
	<td>${em.hiredate}</td>
	<td>${em.telephone}</td>
	<td>${em.arc.xueli}</td>
	<td>${em.arc.email}</td>
	<td>${em.arc.zzmm}</td>
	<td>${em.arc.minzu}</td>
	<td><a href="${pageContext.request.contextPath}/arc/getByArc?id=${em.eid}">编辑</a> | <a href="project-base-look.jsp">查看详情</a></td>
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
			 location.href="<c:url value="/arc"/>?pageNO="+(new_page_index+1);
		}
	</script>





<tr bgcolor="#FAFAF1">
<td height="28" colspan="12">
	&nbsp;
	<a href="" class="coolbg">全选</a>
	<a href="" class="coolbg">反选</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	<a href="" class="coolbg">&nbsp;删除&nbsp;</a>
	<a href="${pageContext.request.contextPath}/arc/exportExl" class="coolbg">&nbsp;导出Excel&nbsp;</a>
	<a href="views/usual/archives-add.jsp" class="coolbg">&nbsp;添加档案信息&nbsp;</a>
</td>
</tr>
<tr align="right" bgcolor="#EEF4EA">
	<td height="36" colspan="12" align="center"><!--翻页代码 --></td>
</tr>
</table>

</form>
  

</body>
</html>