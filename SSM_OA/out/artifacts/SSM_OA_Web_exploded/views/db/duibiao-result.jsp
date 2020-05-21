<%@ page language="java" pageEncoding="UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=gb2312">
    <title>对标管理</title>
    <link rel="stylesheet" type="text/css" href="skin/css/base.css">
    <script type="application/javascript" src="${pageContext.request.contextPath}/js/jquery.min.js"></script>
    <script type="application/javascript"
            src="${pageContext.request.contextPath}/eacharjs/echarts.common.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/date/WdatePicker.js"></script>
    <script type="text/javascript">
        var dateSkin = "blue";
        $(document).ready(function () {
            $("#st").focus(function () {
                WdatePicker({skin: dateSkin, readOnly: true, dateFmt: 'yyyy-MM-dd HH:mm:ss'})
            });
            $("#et").focus(function () {
                WdatePicker({skin: dateSkin, readOnly: true, dateFmt: 'yyyy-MM-dd HH:mm:ss'})
            });
        });

        $(function () {
            $.ajax({
                url:'${pageContext.request.contextPath}/db',
                dataType:'json',
                type:'post',
                success:function (data) {
                    $.each(data,function (index,com) {
                        $("#com").append("<option value='"+com.dacname+"'>"+com.dacname +"</option>");
                    })
                }
            })
        })
        function sear() {
            // 基于准备好的dom，初始化echarts实例
            var myChart = echarts.init(document.getElementById('box'));
            var myChart1 = echarts.init(document.getElementById('box1'));
            var year=[];
            var nover=[];
            $.ajax({
                url:'${pageContext.request.contextPath}/db/getYear',
                dataType:'json',
                data:{"dname":$("#com").val(),st:$("#st").val(),et:$("#et").val()},
                type:'post',
                success:function (data) {
                    // 指定图表的配置项和数据
                    $.each(data,function (index,db) {
                         var dbtime=new Date(db.datime);
                        year.push(dbtime.getFullYear());
                        nover.push(db.daturnover)

                    })
                    var option = {
                        title: {
                            text: 'ECharts 入门示例'
                        },
                        tooltip: {},
                        legend: {
                            data:['钱']
                        },
                       xAxis: {
                            data: year
                        },
                        yAxis: {},
                        series: [{
                            name: '年份',
                            type: 'bar',
                            data: nover
                        }]
                    };

                    // 使用刚指定的配置项和数据显示图表。
                    myChart.setOption(option);
                    var option = {
                        title: {
                            text: 'ECharts 入门示例'
                        },
                        tooltip: {},
                        legend: {
                            data:['钱']
                        },
                        xAxis: {
                            data: year
                        },
                        yAxis: {},
                        series: [{
                            name: '年份',
                            type: 'line',
                            data: nover
                        }]
                    };

                    // 使用刚指定的配置项和数据显示图表。
                    myChart1.setOption(option);
                }





            })
        }
    </script>
</head>
<body leftmargin="8" topmargin="8" background='skin/images/allbg.gif'>
<%--检索条件--%>
<form  method="post">
    <table width='98%' border='0' cellpadding='1' cellspacing='1' bgcolor='#CBD8AC' align="center"
           style="margin-top:8px">
        <tr bgcolor='#EEF4EA'>
            <td background='skin/images/wbg.gif' align='center'>
                <table border='0' cellpadding='0' cellspacing='0'>
                    <tr>
                        <td width='90' align='center'>搜索条件：</td>
                        <td width='100'>
                            <input type="hidden" value="1" name="pageNO" id="pn">
                            <select name="dname" id="com" class="style='width:120px'">
                                <option>选择企业</option>
                            </select>
                        </td>
                        <td width='70'>
                            时间段：
                        </td>
                        <td width='300'>
                            <input type="text" id="st" name="startTime" style='width:120px'>---
                            <input type="text" id="et" name="endTime" style='width:120px'>
                        </td>
                        <td>
                            &nbsp;&nbsp;&nbsp;<input type="button" onclick="sear()" value="查询" class="np">
                        </td>
                    </tr>
                </table>
            </td>
        </tr>
    </table>
</form>

<!--  快速转换位置按钮  -->
<table width="98%" border="0" cellpadding="0" cellspacing="1" bgcolor="#D1DDAA" align="center">
    <tr>
        <td height="26" background="skin/images/newlinebg3.gif">
            <table width="58%" border="0" cellspacing="0" cellpadding="0">
                <tr>
                    <td>
                        当前位置:对标管理>>对标分析
                    </td>
                </tr>
            </table>
        </td>
    </tr>
</table>
<div>
    <div id="box" style="width:800px;height:400px">
    </div>
</div>
<div>
    <div id="box1" style="width:800px;height:400px">
    </div>
</div>
</body>
</html>