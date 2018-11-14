<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%><%@ taglib prefix="c"

                                                                                                 uri="http://java.sun.com/jsp/jstl/core"%><%@ taglib
        prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%><%@page import="com.gdssoft.core.tools.SystemContext"%><!doctype

html>
<html>
    <head>
        <meta charset="utf-8">
        <title>红黑名单反馈记录查询</title>
        <meta name="renderer" content="webkit">
        <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
        <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
        <link rel="stylesheet" href="${StaticResourcePath}/layer/layui-2.3.0/css/layui.css"  media="all">
        <script type="text/javascript" src="${StaticResourcePath}/js/jquery-1.7.2.min.js"></script>
        <script type="text/javascript" src="${StaticResourcePath}/layer/layer.js"></script>
        <script src="${StaticResourcePath}/layer/layui-2.3.0/layui.js"></script>
        <style>
            .main {
                width: 1200px;
                height: 600px;
                border: 1px #666666 solid;
                margin: 10px auto;
            }
            .main .layui-main {
                margin: 15px;
                line-height: 22px;
            }
        </style>

    </head>
    <body>
        <div class="main">
            <div class="queryCondition layui-main layui-form">
                反馈状态：
                <div class="layui-input-inline">
                    <select name="status" id="status" lay-verify="statusfilter">
                        <option value="">请选择</option>
                        <option value="0">未反馈</option>
                        <option value="1">反馈中</option>
                        <option value="2">已反馈</option>
                    </select>
                </div>
                查询开始时间：
                <div class="layui-input-inline">
                    <input type="text" name="beginCreateTime" id="beginCreateTime" lay-verify="date" placeholder="请选择" autocomplete="off" class="layui-input">
                </div>
                查询结束时间：
                <div class="layui-input-inline">
                    <input type="text" name="endCreateTime" id="endCreateTime" lay-verify="date" placeholder="请选择" autocomplete="off" class="layui-input">
                </div>
                <button class="layui-btn" data-type="reload">搜索</button>
            </div>

            <table class="layui-hide" id="LAY_table_user" lay-filter="queryRecord"></table>

            <script type="text/html" id="barDemo">
                <a class="layui-btn layui-btn-primary layui-btn-xs" lay-event="detail">查看详情</a>
            </script>
        </div>

    </body>
    <script>
        var redBackUrl = "";
        $(function() {
            layui.use('laydate', function(){
                var laydate = layui.laydate;

                //执行一个laydate实例
                laydate.render({
                    elem: '#beginCreateTime' //指定元素
                    ,type: "datetime" //可选择：年、月、日、时、分、秒
                });
                laydate.render({
                    elem: '#endCreateTime' //指定元素
                    ,type: "datetime" //可选择：年、月、日、时、分、秒
                });
            });


            layui.use('table', function(){
                var table = layui.table;

                //方法级渲染
                table.render({
                    elem: '#LAY_table_user'
                    ,url: '../console/redBalckQuery.xhtml?action=getRedBackQueryRecordPageData'
                    ,cols: [[
                        {field:'createTimeStr', title: '查询时间', width:200}
                        ,{field:'queryName', title: '查询条件', width:180}
                        ,{field:'redDes', title: '红名单类型', width:300}
                        ,{field:'blackDes', title: '黑名单类型', width:300}
                        ,{field:'status', title: '反馈状态', width:100, templet: function(d){
                                if (d.status == "0") {
                                    return "未反馈";
                                } else if (d.status == "1") {
                                    return "反馈中";
                                } else if (d.status == "2") {
                                    return "已反馈";
                                } else {
                                    return "未知状态";
                                }
                            }
                        }
                        ,{title: '操作', toolbar: '#barDemo'}
                    ]]
                    ,id: 'testReload'
                    ,page: true
                    ,height: 471
                });

                var $ = layui.$, active = {
                    reload: function(){
                        //执行重载
                        table.reload('testReload', {
                            page: {
                                curr: 1 //重新从第 1 页开始
                            }
                            ,where: {
                                status: $('#status').val(),
                                beginCreateTime: $('#beginCreateTime').val(),
                                endCreateTime: $('#endCreateTime').val()
                            }
                        });
                    }
                };

                $('.queryCondition .layui-btn').on('click', function(){
                    var type = $(this).data('type');
                    active[type] ? active[type].call(this) : '';
                });

                //监听工具条
                table.on('tool(queryRecord)', function(obj){
                    var data = obj.data;
                    if(obj.event === 'detail'){
                        // 查看记录详情
                        loadDataParam(data.queryName, "", data.redNum, data.blackNum, data.redDes, data.blackDes, data.id);
                    }
                });
            });

            layui.use('form', function(){
                var form = layui.form;

                //各种基于事件的操作
                form.on('select(statusfilter)', function(data){
                    console.log(data.elem); //得到select原始DOM对象
                    console.log(data.value); //得到被选中的值
                    console.log(data.othis); //得到美化后的DOM对象
                });
            });
        });

        function loadDataParam(name, code, rednum, blacknum, redDes, blackDes, recordId) {
            if (!recordId) {
                recordId == "";
            }
            name = encodeURI(name);
            redDes = encodeURI(redDes);
            blackDes = encodeURI(blackDes);
            redBackUrl = '../console/redBalckQuery.xhtml?action=enterpriseRedlistpage&name='+name+'&code='+code+'&rednum=' + rednum + '&blacknum=' + blacknum + '&redDes=' + redDes + '&blackDes=' + blackDes + '&recordId=' + recordId;
            redBackUrl = encodeURI(redBackUrl);
            layer.open({
                id: 'L123243545',
                type: 2,
                title: "详情信息",
                area: ['950px', '550px'],
                fixed: false,
                maxmin: true,
                content: redBackUrl,
                btn: ['取消'],
                btnAlign: 'c',
                btn1: function(index, layero) {
                    layer.close(index);
                    return false;
                }
            });
            // 定时2s刷新
            // window.setTimeout(" getQueryRecord()", 2000);
        }

        function reOpenLayerIframe() {
            layer.closeAll();
            layer.open({
                id: 'L123243546',
                type: 2,
                title: "详情信息",
                area: ['950px', '550px'],
                fixed: false,
                maxmin: true,
                content: redBackUrl,
                btn: ['取消'],
                btnAlign: 'c',
                btn1: function(index, layero) {
                    layer.close(index);
                    return false;
                }
            });
        }
    </script>
</html>