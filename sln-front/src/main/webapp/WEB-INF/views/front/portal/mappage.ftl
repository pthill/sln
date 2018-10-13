<#include "/front/portal/common/header.ftl" />
<link rel="stylesheet" href="${(domainUrlUtil.SLN_STATIC_RESOURCES)!}/portal/css/map-style.css">
<div id="container"   class="col-lg-8 col-md-8" style="height:800px;float: left;margin: 0 auto"></div>
<div class="right col-lg-4 col-md-4" style="float: left;margin-left: 20px;">
<#if AreaMap??>
    <#list AreaMap?keys as key>
        <#list codeManager.codeMap['AREA_CODE'] as code>
            <#if key==code.codeCd>
                <h3>${code.codeText!''}</h3>
                <#list AreaMap[key] as park>
                    <div id="${park.parkName}" class=" <#if park?size &gt; 0>park2<#else>park</#if> park-box" >${park.parkName}</div>
                </#list>
            </#if>
        </#list>
    </#list>
</#if>
</div>
<div style="clear: both"></div>
<script type="text/javascript" src="${(domainUrlUtil.SLN_STATIC_RESOURCES)!}/portal/js/echarts-all-3.js"></script>
<script type="text/javascript" src="${(domainUrlUtil.SLN_STATIC_RESOURCES)!}/portal/js/ecStat.min.js"></script>
<script type="text/javascript" src="${(domainUrlUtil.SLN_STATIC_RESOURCES)!}/portal/js/dataTool.min.js"></script>
<script type="text/javascript" src="${(domainUrlUtil.SLN_STATIC_RESOURCES)!}/portal/js/china.js"></script>
<script type="text/javascript" src="${(domainUrlUtil.SLN_STATIC_RESOURCES)!}/portal/js/world.js"></script>
<script type="text/javascript" src="http://api.map.baidu.com/api?v=2.0&ak=ZUONbpqGBsYGXNIYHicvbAbM"></script>
<script type="text/javascript" src="${(domainUrlUtil.SLN_STATIC_RESOURCES)!}/portal/js/bmap.min.js"></script>
<script type="text/javascript">

    var array=[];
    var point=[];
    var tal=[];
    <#if parkList??>
        <#list parkList as park>
        var temp = {name:"${park.parkName}",value : ${park.id}};
        var parkPoint={name:"${park.parkName}",value:"${park.id}",tel:"${park.tel}",dz:"${park.parkAddr}",province:"${park.province}",url:"${domainUrlUtil.SLN_IMAGE_RESOURCES}${park.img}",address:[${park.longitude},${park.latitude}]};
        array.push(temp);
        point.push(parkPoint);
        </#list>
    </#if>
    var dom = document.getElementById("container");
    var myChart = echarts.init(dom);
    var app = {};
    option = null;
    var data =array;
    var geoCoordMap = point;
    for(var i=0;i<data.length;i++){
        tal.push(data[i].value);
    }
    function convertData(data) {
        var res = [];
        for (var i = 0; i < data.length; i++) {
            var geoCoord = geoCoordMap[i].address;
            if (geoCoord) {
                res.push({
                    name: data[i].name,
                    value: geoCoord.concat(data[i].value)
                });
            }
        }
        return res;
    };
    function randomValue() {
        return Math.round(Math.random()*1000);
    }
    option = {
        /*visualMap: {
            show:true,
            type: 'piecewise',
            left:null,
            right:0,
            dimension:2,
            selected:{
               //13:true,
            },
            categories:tal,
            inRange:{
               symbolSize: 25,
               color:'#F06C00'
            },
            outOfRange: {
                //color: '#ff3300',
            }
        },*/
        geo: {
            map: 'china',
            roam: true,
            label: {
                normal: {
                    show: true,  //显示省名称
                    textStyle: {
                        color: 'rgba(0,0,0,0.4)',
                    }
                }
            },
            itemStyle: {   //定义样式
                normal:{   //普通状态下的样式
                    borderColor: 'rgba(0, 0, 0, 0.3)'
                },
                emphasis:{ //高亮状态下的样式
                    areaColor: null,
                    shadowOffsetX: 0,
                    shadowOffsetY: 0,
                    shadowBlur: 20,
                    borderWidth: 0,
                    shadowColor: 'rgba(0, 0, 0, 0.5)'
                }
            }
        },
        series : [  //新建散点图series
            {
                type: 'scatter',  //series图表类型
                coordinateSystem: 'geo',
                data: convertData(data),
                symbolSize: 10,
                symbol: 'circle',
                symbolRotate: 0,
                showLegendSymbol : false,
                label: {
                    normal: {
                        position: 'right',
                        show: false ,  //直接显示散点的地址
                        formatter:function (params) {
                             return params.value;
                        }
                    },
                    emphasis: {
                        show: false    //鼠标移入显示散点地址
                    }
                },
                itemStyle: {
                    normal: {
                        color: '#F06C00'    //小圆点的颜色
                    },
                    emphasis: {
                        color: '#F06C00'
                    }
                }
            },
            {
                name: '城市',
                type: 'map',
                geoIndex: 0,
                // tooltip: {show: false},
                data:[
                    {name: '北京', value: randomValue()},
                    {name: '天津', value: randomValue()},
                    {name: '上海', value: randomValue()},
                    {name: '重庆', value: randomValue()},
                    {name: '河北', value: randomValue()},
                    {name: '河南', value: randomValue()},
                    {name: '云南', value: randomValue()},
                    {name: '辽宁', value: randomValue()},
                    {name: '黑龙江', value: randomValue()},
                    {name: '湖南', value: randomValue()},
                    {name: '安徽', value: randomValue()},
                    {name: '山东', value: randomValue()},
                    {name: '新疆', value: randomValue()},
                    {name: '江苏', value: randomValue()},
                    {name: '浙江', value: randomValue()},
                    {name: '江西', value: randomValue()},
                    {name: '湖北', value: randomValue()},
                    {name: '广西', value: randomValue()},
                    {name: '甘肃', value: randomValue()},
                    {name: '山西', value: randomValue()},
                    {name: '内蒙古', value: randomValue()},
                    {name: '陕西', value: randomValue()},
                    {name: '吉林', value: randomValue()},
                    {name: '福建', value: randomValue()},
                    {name: '贵州', value: randomValue()},
                    {name: '广东', value: randomValue()},
                    {name: '青海', value: randomValue()},
                    {name: '西藏', value: randomValue()},
                    {name: '四川', value: randomValue()},
                    {name: '宁夏', value: randomValue()},
                    {name: '海南', value: randomValue()},
                    {name: '台湾', value: randomValue()},
                    {name: '香港', value: randomValue()},
                    {name: '澳门', value: randomValue()}
                ]
            },

        ],
        tooltip:{
            trigger:'item',
            alwaysShowContent:true,
            show:true,
            textStyle:{
                color:'#fff'
            },
            triggerOn:'mousemove|click',
            showDelay:500,
            position:function(p){   //其中p为当前鼠标的位置
                return [p[0] -125, p[1] -250];
            },
            formatter:function (params) {
                var item=params.name;
                var res='';
                for(var i=0;i<point.length;i++){
                    if(item==point[i].name){
                        res = '<img src="'+ point[i].url +'" style="height: 130px;width:230px"/>'+
                                '<div class="bottom"><div class="province"><p style="font-size: 16px;">'+point[i].province+'省&nbsp;&nbsp;' +
                                '<span style="font-size: 12px;">'+point[i].name+'</span></p></div>'+
                        '<div style="font-size: 12px"><p>电话：'+point[i].tel+'</p><p>地址：'+point[i].dz+'</p></div>'+
                                '<button type="button" class="btn btn-info" onclick="changePark('+point[i].value+')" style="width: 100%;height: 30px;margin: 5px 5px 5px 0px">' +
                                '设为当前园区</button></div>';
                    }
                }
                return res;
            }
        }
    };
    if (option && typeof option === "object") {
        myChart.setOption(option, true);
    }
    function changePark(val) {
       window.location.href="${(domainUrlUtil.SLN_URL_RESOURCES)!}/park/parkMap.html?parkId="+val;
    }
    $(function () {
        $('.park-box').on('click', function () {
            var parkName=$(this).attr('id')
            $(this).siblings('div').removeClass('active');
            $(this).addClass('active');
            var sz=option.series[0].data;
            for(var j=0;j<sz.length;j++){
                 if(parkName==sz[j].name){
                     //sz[j].attr("selected",true);
                     check(j);
                 }
            }
            console.log(sz);
        });
        function check(index) {
            myChart.dispatchAction({
                type: 'showTip',
                seriesIndex: 0,
                dataIndex: index
            });
        }
    })





</script>
<#include "/front/portal/common/footer.ftl" />