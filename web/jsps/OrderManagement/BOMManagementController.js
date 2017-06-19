/**
 * Created by hanchangming on 2017/5/16.
 */
'use strict';
angular.module("IntegratedFramework.BOMManagementController", ['ngRoute'])
    .config(['$routeProvider', function ($routeProvider) {
        $routeProvider.when('/BOMManagement', {
            templateUrl: '/jsps/OrderManagement/BOMManagement.jsp',
            controller: 'BOMManagementController'
        })
    }])
    .controller("BOMManagementController", function ($scope) {
        var data=[
            { "cid": "1", "name": "零件1", "pid": "0" },
            { "cid": "2", "name": "自产零件A", "pid": "1" },
            { "cid": "3", "name": "自产零件A1", "pid": "2" },
            { "cid": "4", "name": "自产零件A2", "pid": "2" },
            { "cid": "5", "name": "零件2", "pid": "0" },
            { "cid": "6", "name": "自产零件B", "pid": "5" },
            { "cid": "7", "name": "自产零件B1", "pid": "6" },
            { "cid": "8", "name": "自产零件B2", "pid": "6" },
        ];

        //加载页面时显示BOM树
        $=function(){
            for(var i=0;i<data.length;i++){
                if(data[i].pid==0){
                    var ul=document.createElement("ul");
                    var li=documnet.createElement('li');
                    li.innerHTML=data[i].name;
                    ul.appendChild(li);
                    for(var k=0;k<data.length;k++){
                        if(data[k].pid==data[i].cid){
                            var ul1=document.createElement("ul");
                            var li1=documnet.createElement('li');
                            li1.innerHTML=data[k].name;
                            ul.appendChild(li);
                            li.appendChild(ul1);
                        }
                    }
                }
            }
        }



    });
