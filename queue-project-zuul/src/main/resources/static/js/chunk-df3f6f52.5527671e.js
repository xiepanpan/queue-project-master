(window["webpackJsonp"]=window["webpackJsonp"]||[]).push([["chunk-df3f6f52"],{"0570":function(e,t,a){"use strict";var s=a("341f"),n=a.n(s);n.a},"341f":function(e,t,a){},5917:function(e,t,a){"use strict";a.r(t);var s=function(){var e=this,t=e.$createElement,a=e._self._c||t;return a("div",{attrs:{id:"leaveMes"}},[a("el-table",{directives:[{name:"loading",rawName:"v-loading",value:e.load,expression:"load"}],staticStyle:{width:"60.1%"},attrs:{data:e.data,border:"",height:"250"}},[a("el-table-column",{attrs:{prop:"userNo",label:"用户号",width:"130"}}),a("el-table-column",{attrs:{prop:"staffName",label:"工作人员",width:"130"}}),a("el-table-column",{attrs:{prop:"queueNo",label:"预约号码",width:"150"}}),a("el-table-column",{attrs:{prop:"messageType",label:"留言星级",width:"220"}}),a("el-table-column",{attrs:{prop:"messageContent",label:"内容",width:"200"}})],1)],1)},n=[],i={data:function(){return{data:[],load:!0,value:5}},mounted:function(){this.init()},methods:{init:function(){var e=this;this.axios.getleaveMes({}).then(function(t){t.success?(e.load=!1,e.data=t.dataList):e.$message.error("查询留言信息失败,请检查网络设置")})},deleteWindows:function(e){var t=this;this.axios.removeLeaveMes({id:e.id}).then(function(e){if(e.success)return t.$message.success(e.message),void t.init();t.$message.error("删除失败，请检查网络设置")})}}},l=i,o=(a("0570"),a("2877")),r=Object(o["a"])(l,s,n,!1,null,null,null);t["default"]=r.exports}}]);
//# sourceMappingURL=chunk-df3f6f52.5527671e.js.map