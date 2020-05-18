<%@ page language="java"  pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
<title>创建任务</title>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/skin/css/base.css">
	<script type="application/javascript" src="${pageContext.request.contextPath}/js/jquery.min.js"></script>
	<script src="${pageContext.request.contextPath}/layer/layer.js"></script>
	<script type="text/javascript">
		$(function () {
			//异步获取所有有需求的项目
			$.ajax({
				url:'${pageContext.request.contextPath}/pro/getProsHasNeed',
				type:'post',
				dataType:'json',
				success:function (data) {
					$.each(data,function (index,ana) {
						$("#pro").append("<option value='"+ana.id+"_"+ana.proname+"_"+ana.title+"'>"+ana.proname+"</option>");
					});
				}
			});
			//请求所有开发人员（不包括项目经理）
			$.ajax({
				url:'${pageContext.request.contextPath}/pro/getEmpByPos',
				type:'post',
				dataType:'json',
				success:function (data) {
					$.each(data,function (index,emp) {
                        $("#emp").append("<option value='"+emp.eid+"'>"+emp.ename+"</option>");
					});
				}
			});
		})

		//获取项目对应的需求
		function addayalisys(pnameAndAna) {
			var proAndAna=pnameAndAna.split("_");
			var title=proAndAna[2];
			$("#anid option").remove();
			$("#anid").append("<option>"+title+"</option>");
			//异步请求改需求下所有模块
			//$("#mod option").remove();
			$("#mod").html("<option value='0'>请选择模块</option>");
			$.ajax({
				url:'${pageContext.request.contextPath}/pro/getModsByAid',
				type:'post',
				data:{"aid":proAndAna[0]},
				dataType:'json',
				success:function (data) {
					$.each(data,function (index,m) {
						$("#mod").append("<option value='"+m.id+"'>"+m.modname+"</option>");
					})
				}
			});
		}

		function addfunc(mid) {
			//获取模块下的功能
			$("#fun").html("<option value='0'>请选择功能</option>");
			$.ajax({
				url:'${pageContext.request.contextPath}/pro/getFunsByMid',
				type:'post',
				data:{"mid":mid},
				dataType:'json',
				success:function (data) {
					$.each(data,function (index,f) {
						$("#fun").append("<option value='"+f.id+"'>"+f.functionname+"</option>");
					})
				}
			})
		}

		function commit() {
			//$("#form7").submit();
			$.ajax({
				url:'${pageContext.request.contextPath}/us/addTask',
				type:'post',
				data:$("#form7").serialize(),
				dataType:'json',
				success:function (data) {
                   if(data>0){
					   layer.msg("保存任务成功", {time:1000, icon:1, shift:6}, function(){
                          window.location.reload();
					   });
				   }else{
					   layer.msg("保存任务失败", {time:1000, icon:2, shift:6}, function(){
					   });
				   }
				}
			});
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
    当前位置:任务管理>>创建任务
 </td>
 </tr>
</table>
</td>
</tr>
</table>

<form name="form2" id="form7" method="post">

<table width="98%" border="0" cellpadding="2" cellspacing="1" bgcolor="#D1DDAA" align="center" style="margin-top:8px">
<tr bgcolor="#E7E7E7">
	<td height="24" colspan="2" background="skin/images/tbg.gif">&nbsp;创建任务&nbsp;</td>
</tr>
<tr >
	<td align="right" bgcolor="#FAFAF1" height="22">参考位置：</td>
	<td  align='left' bgcolor="#FFFFFF" onMouseMove="javascript:this.bgColor='#FCFDEE';" onMouseOut="javascript:this.bgColor='#FFFFFF';" height="22">
		<select id="pro" onchange="addayalisys(this.value)">
			<option value=0>请选择项目</option>
		</select>-
		<select id="anid">
			<option value=0>请选择需求</option>
		</select>
		-<select id="mod" onchange="addfunc(this.value)">
		    <option value=0>请选择模块</option>
	    </select>-
		<select id="fun" name="funFk">
			<option value=0>请选择功能</option>
		</select>
	</td>
</tr>
<tr >
	<td align="right" bgcolor="#FAFAF1" height="22">任务标题：</td>
	<td  align='left' bgcolor="#FFFFFF" onMouseMove="javascript:this.bgColor='#FCFDEE';" onMouseOut="javascript:this.bgColor='#FFFFFF';" height="22">
		<input name="tasktitle"/></td>
</tr>
<tr >
	<td align="right" bgcolor="#FAFAF1" height="22">开始时间：</td>
	<td  align='left' bgcolor="#FFFFFF" onMouseMove="javascript:this.bgColor='#FCFDEE';" onMouseOut="javascript:this.bgColor='#FFFFFF';" height="22">
		<input name="starttime"/></td>
</tr>
<tr >
	<td align="right" bgcolor="#FAFAF1" height="22">结束时间：</td>
	<td  align='left' bgcolor="#FFFFFF" onMouseMove="javascript:this.bgColor='#FCFDEE';" onMouseOut="javascript:this.bgColor='#FFFFFF';" height="22">
		<input name="endtime"/></td>
</tr>
<tr >
	<td align="right" bgcolor="#FAFAF1" height="22">执行者：</td>
	<td  align='left' bgcolor="#FFFFFF" onMouseMove="javascript:this.bgColor='#FCFDEE';" onMouseOut="javascript:this.bgColor='#FFFFFF';" height="22">
		<select id="emp" name="empFk2">
			<option value=0>任务的执行者</option>
		</select>
	</td>
</tr>
<tr >
	<td align="right" bgcolor="#FAFAF1" height="22">优先级：</td>
	<td  align='left' bgcolor="#FFFFFF" onMouseMove="javascript:this.bgColor='#FCFDEE';" onMouseOut="javascript:this.bgColor='#FFFFFF';" height="22">
		<select name="level">
			<option value="高">高</option>
			<option value="中">中</option>
			<option value="低">低</option>
		</select>
	</td>
</tr>

<tr >
	<td align="right" bgcolor="#FAFAF1" >详细说明：</td>
	<td colspan=3 align='left' bgcolor="#FFFFFF" onMouseMove="javascript:this.bgColor='#FCFDEE';" onMouseOut="javascript:this.bgColor='#FFFFFF';" >
		<textarea rows=10 cols=130 name="remark"></textarea>
	</td>
</tr>


<tr bgcolor="#FAFAF1">
<td height="28" colspan=4 align=center>
	&nbsp;
	<a href="javascript:commit()" class="coolbg">保存</a>
</td>
</tr>
</table>

</form>
  

</body>
</html>