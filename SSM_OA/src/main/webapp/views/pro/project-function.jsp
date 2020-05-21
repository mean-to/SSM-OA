<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java"  pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
<title>功能管理</title>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/skin/css/base.css">
    <script type="text/javascript" src="${pageContext.request.contextPath}/page/jquery-1.10.2.min.js" ></script>
</head>
<body leftmargin="8" topmargin="8" background='${pageContext.request.contextPath}/skin/images/allbg.gif'>

<!--  快速转换位置按钮  -->
<table width="98%" border="0" cellpadding="0" cellspacing="1" bgcolor="#D1DDAA" align="center">
<tr>
 <td height="26" background="${pageContext.request.contextPath}/skin/images/newlinebg3.gif">
  <table width="58%" border="0" cellspacing="0" cellpadding="0">
  <tr>
  <td >
    当前位置:项目管理>>功能管理
 </td>
  <td>
    <input type='button' class="coolbg np" onClick="location='views/pro/project-function-add.jsp';" value='添加新功能' />
 </td>
 </tr>
</table>
</td>
</tr>
</table>

<!--  搜索表单  -->
<form name='form3' action='fun' method='get' id="prose">

<table width='98%'  border='0' cellpadding='1' cellspacing='1' bgcolor='#CBD8AC' align="center" style="margin-top:8px">
  <tr bgcolor='#EEF4EA'>
      <input type='hidden' name='dopost' value='' />
      <input type='hidden' name='pageNO' id="pg" value='1' />
    <td background='skin/images/wbg.gif' align='center'>
      <table border='0' cellpadding='0' cellspacing='0'>
        <tr>
          <td width='90' align='center'>搜索条件：</td>
          <td width='160'>
          <select name='cid' style='width:150'>
          <option <c:if test="${qo.cid==0}">selected</c:if> value='0'>选择类型...</option>
          	<option <c:if test="${qo.cid==1}">selected</c:if> value='1'>项目名称</option>
          	<option <c:if test="${qo.cid==2}">selected</c:if> value='2'>需求名称</option>
          	<option <c:if test="${qo.cid==3}">selected</c:if> value='3'>模块名称</option>
          	<option <c:if test="${qo.cid==4}">selected</c:if> value='4'>功能名称</option>
          </select>
        </td>
        <td width='70'>
          关键字：

        </td>
        <td width='160'>
          	<input type='text' name='key' value='${qo.key}' style='width:120px' />
        </td>
        <td width='110'>
    		<select name='order' style='width:120px'>
            <option value='0'>等级</option>
            <option value='1'>高</option>
            <option value='2'>中</option>
      	</select>
        </td>
        <td>
          &nbsp;&nbsp;&nbsp;<input name="imageField" type="image" src="${pageContext.request.contextPath}/skin/images/frame/search.gif" width="45" height="20" border="0" class="np" />
        </td>
       </tr>
      </table>
    </td>
  </tr>
</table>
</form>
<!--  内容列表   -->
<form name="form2" action="fun/delFun" method="post" id="formF">

<table width="98%" border="0" cellpadding="2" cellspacing="1" bgcolor="#D1DDAA" align="center" style="margin-top:8px">
<tr bgcolor="#E7E7E7">
	<td height="24" colspan="12" background="skin/images/tbg.gif">&nbsp;功能列表&nbsp;</td>
</tr>
<tr align="center" bgcolor="#FAFAF1" height="22">
	<td width="4%">选择</td>
	<td width="6%">序号</td>
	<td width="10%">功能名称</td>
	<td width="10%">模块名称</td>
	<td width="10%">需求名称</td>
	<td width="10%">项目名称</td>
	<td width="10%">优先级</td>
	<td width="8%">详情</td>
	<td width="8%">备注</td>
	<td width="10%">操作</td>
</tr>
<c:forEach items="${info.list}" var="fun" varStatus="cou">
<tr align='center' bgcolor="#FFFFFF" onMouseMove="javascript:this.bgColor='#FCFDEE';" onMouseOut="javascript:this.bgColor='#FFFFFF';" height="22" >
	<td><input name="id" type="checkbox" id="id" value="${fun.id}" class="np"></td>
	<td>1</td>
	<td align="center"><a href=''><u>${fun.functionname}</u></a></td>
	<td align="center"><a href=''><u>${fun.mou.modname}</u></a></td>
	<td align="center"><a href=''><u>${fun.analysisname}</u></a></td>
	<td align="center"><a href=''><u>${fun.proname}</u></a></td>
	<td>${fun.level}</td>
	<td>${fun.simpledis}</td>
	<td>${fun.detaileddis}</td>
	<td>
        <a href="${pageContext.request.contextPath}/fun/getIdFun?id=${fun.id}">编辑</a> |
        <a href="${pageContext.request.contextPath}/fun/getIdFunL?id=${fun.id}">查看详情</a></td>
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
            $("#pg").val(new_page_index+1);//查询的页面保存到隐藏域
            $("#prose").submit();
            //  location.href="<c:url value="/pro"/>?pageNO="+(new_page_index+1);
        }
    </script>


<tr bgcolor="#FAFAF1">
<td height="28" colspan="12">
	&nbsp;
	<a href="javascript:CAll()" class="coolbg">全选</a>
	<a href="javascript:NOCAll()" class="coolbg">反选</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	<a href="javascript:delFun()" class="coolbg">&nbsp;删除&nbsp;</a>
    <a href="${pageContext.request.contextPath}/fun/exportExl"class="coolbg">&nbsp;导出Excel&nbsp;</a>
</td>
</tr>
<tr align="right" bgcolor="#EEF4EA">
	<td height="36" colspan="12" align="center"><!--翻页代码 --></td>
</tr>
</table>

</form>
<script type="text/javascript">
   function CAll(){
           $("input[name='id']").prop("checked",true);

   }
   function NOCAll() {
       $("input[name='id']").each(function () {
           $(this).prop("checked",!$(this).prop("checked"));
       })

   }
   function delFun() {
       var len=$("input[name='id']:checked").length;
       if(len>0){
           $("#formF").submit();
       }else {
           alert("请选择要删除的数据")
       }

   }

</script>
  

</body>
</html>