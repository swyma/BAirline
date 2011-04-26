<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!--  编辑者：杨海亮  -->

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<meta http-equiv="content-type" content="text/html; charset=UTF-8">
		<title>客户订票管理</title>

		<link rel="stylesheet" type="text/css"
			href="ExtJs/resources/css/ext-all.css" />
		<link rel="stylesheet" type="text/css"
			href="ExtJs/resources/css/xtheme-gray.css" />

		<script type="text/javascript" src="ExtJs/adapter/ext/ext-base.js">
</script>
		<script type="text/javascript" src="ExtJs/ext-all.js">
</script>
		<script type="text/javascript" src="Js/PagingMemoryProxy.js">
</script>

		<link rel="stylesheet" href="Css/bookinformation.css" type="text/css"></link>
		<script type="text/javascript" src="Js/JsHelper.js">
</script>


		<!--  **************向用户显示航班信息*********************** -->
		<script type="text/javascript" src="Js/flightinformation_show_cus.js">
</script>
		<!-- 客户订票 -->
		<script type="text/javascript" src="Js/booking_ticketForm.js">
</script>

		<style type="text/css">
.allow-float {
	clear: none !important;
}

.stop-float {
	clear: both !important;
}

.float-left {
	float: left;
}
</style>
		<script type="text/javascript">

var comCode = new Ext.form.TextField( {
	name : 'comCode',
	id : 'comCode',
	fieldLabel : '航空公司',
	allowBlank : false,
	readOnly : true
});

var cusId = new Ext.form.NumberField( {
	name : 'cusId',
	id : 'cusId',
	fieldLabel : '身份证',
	allowBlank : false,
	readOnly : true
});
var booNo = new Ext.form.TextField( {
	name : 'booNo',
	id : 'booNo',
	fieldLabel : '航班号',
	readOnly : true,
	allowBlank : false
});

var booEveryday = new Ext.form.TextField( {
	name : 'booEveryday',
	id : 'booEveryday',
	fieldLabel : '日期',
	readOnly : true,
	allowBlank : false
});

var booBaddress = new Ext.form.TextField( {
	name : 'booBaddress',
	id : 'booBaddress',
	fieldLabel : '起始地址',
	readOnly : true,
	allowBlank : false,
	itemCls : 'float-left',
	clearCls : 'allow-float'
});

var booAaddress = new Ext.form.TextField( {
	name : 'booAaddress',
	id : 'booAaddress',
	fieldLabel : '抵达地址',
	readOnly : true,
	allowBlank : false,
	itemCls : 'float-left',
	clearCls : 'allow-float'
});

var booBtime = new Ext.form.TextField( {
	name : 'booBtime',
	id : 'booBtime',
	fieldLabel : '起始时间',
	readOnly : true,
	allowBlank : false,
	itemCls : 'float-left',
	clearCls : 'allow-float'
});
var booAtime = new Ext.form.TextField( {
	name : 'booAtime',
	id : 'booAtime',
	fieldLabel : '抵达时间',
	allowBlank : false,
	readOnly : true,
	itemCls : 'float-left',
	clearCls : 'allow-float'
});

var booBerth = new Ext.form.TextField( {
	name : 'booBerth',
	id : 'booBerth',
	fieldLabel : '舱位',
	allowBlank : false,
	readOnly : true
});

var booNumber = new Ext.form.TextField( {
	name : 'booNumber',
	id : 'booNumber',
	fieldLabel : '座位号',
	allowBlank : false,
	readOnly : true
});

var booTime = new Ext.form.TextField( {
	name : 'booTime',
	id : 'booTime',
	fieldLabel : '订票时间',
	readOnly : true,
	allowBlank : false
});

var booFare = new Ext.form.TextField( {
	name : 'booFare',
	id : 'booFare',
	fieldLabel : '金额',
	readOnly : true,
	allowBlank : false
});
var data = [ [ '0', '0' ], [ '1', '1' ] ];
var store_flagPay = new Ext.data.SimpleStore( {
	fields : [ 'id_flagPay', 'name_flagPay' ],
	data : data
});
var flagPay = new Ext.form.ComboBox( {
	name : 'flagPay',
	id : 'flagPay',
	fieldLabel : '是否付款',
	allowBlank : false,
	mode : 'local',
	emptyText : '请选择',
	editable : false,
	width : 130,
	triggerAction : 'all',
	store : store_flagPay,
	valueField : 'id_flagPay',
	displayField : 'name_flagPay'
});

var label = new Ext.form.Label( {
	text : '(注明:1 表示 是,0 表示 否)'
});

var store_flagPass = new Ext.data.SimpleStore( {
	fields : [ 'id_flagPass', 'name_flagPass' ],
	data : data
});
var flagPass = new Ext.form.ComboBox( {
	name : 'flagPass',
	id : 'flagPass',
	fieldLabel : '是否为退票',
	editable : false,
	allowBlank : false,
	emptyText : '请选择',
	triggerAction : 'all',
	width : 130,
	mode : 'local',
	store : store_flagPass,
	valueField : 'id_flagPass',
	displayField : 'name_flagPass'
});
// 客户详细信息 cus_information
var cus_information = new Ext.form.FormPanel( {
	labelSeparator : ':',
	labelWidth : 80,// 标签宽度
	region : 'east',
	frame : true,
	autoheight : true,
	width : '100%',
	layout : 'form',
	items : [ {
		layout : 'column',
		items : [ {
			columnWidth : .5,
			layout : 'form',
			xtype : 'fieldset',
			width : '100%',
			title : '客户订票信息',
			height : 80,
			style : 'padding:5 5 5 5',
			items : [ comCode, booNo ]
		}, {
			columnWidth : .5,
			layout : 'form',
			xtype : 'fieldset',
			width : '100%',
			title : '客户订票信息',
			style : 'margin-left:20px; padding:5 5 5 5',
			height : 80,
			items : [ cusId, booEveryday ]
		} ]
	}, {
		layout : 'form',
		xtype : 'fieldset',
		width : '100%',
		title : '航班起抵地址',
		height : '60',
		style : 'padding:5 5 5 5',
		items : [ booBaddress, booAaddress ]
	}, {
		layout : 'form',
		xtype : 'fieldset',
		width : '100%',
		title : '航班起抵时间',
		height : '60',
		style : 'padding:5 5 5 5',
		items : [ booBtime, booAtime ]
	}, {
		layout : 'column',
		items : [ {
			columnWidth : .5,
			layout : 'form',
			xtype : 'fieldset',
			width : '100%',
			title : '客户订票信息',
			style : 'padding:5 5 5 5',
			height : 110,
			items : [ booBerth, booNumber, booTime ]
		}, {
			columnWidth : .5,
			layout : 'form',
			xtype : 'fieldset',
			width : '100%',
			title : '客户订票信息',
			style : 'margin-left:20px; padding:5 5 5 5',
			height : 110,
			items : [ booFare, flagPay, label, flagPass ]
		} ]
	} ]
});

//********************查询票***********************
//以下拉框的形式显示订票客户的身份证号码
var store_cusId = new Ext.data.Store( {
	proxy : new Ext.data.HttpProxy( {
		url : "Bookinformation?type=search_cusId"
	}),
	reader : new Ext.data.ArrayReader( {}, [ {
		name : 'cusId'
	}, {
		name : "cusId"
	} ])
});

//*************显示客户订票信息***********************
var win_show_cusinfo = new Ext.Window( {
	title : '<font size=2>客户订票详细信息</font>',
	modal : true,
	width : '830',
	height : '470',
	autoheight : true,
	resizable : false,
	plain : true,
	iconCls : 'information',
	bodyStyle : 'padding:5px;',
	buttonAlign : 'center',
	closeAction : 'hide',
	items : cus_information,
	listeners : {
		"show" : function() {
		}
	},
	buttons : [ {
		text : '<font size=2>退票</font>',
		iconCls : 'delete',
		handler : function() {
			var row = grid_bookinformation.getSelectionModel().getSelected();
			Ext.Ajax.request( {
				url : 'Bookinformation?type=update',
				params : {
					booAutoid : row.get('booAutoid'),
					comCode : row.get('comCode'),
					booNo : row.get('booNo'),
					booBaddress : row.get('booBaddress'),
					booNumber : row.get('booNumber'),
					booBtime : row.get('booBtime'),
					flagPass : Ext.getCmp('flagPass').getValue()
				},
				success : function() {
					JsHelper.OK("操作成功!", function() {
						store1.reload();
						win_show_cusinfo.hide();
					});
				},
				failure : function() {
					JsHelper.ShowError("操作失败!");
				}
			})
		}
	}, {
		text : '<font size=2>取消</font>',
		handler : function() {
			win_show_cusinfo.hide();
		}
	} ]
});

//**********查询特定身份证号码客户订票信息***********
function search_cusId_info(cusid, url) {
	Ext.Ajax.request( { //ajax响应
				url : url,
				method : 'post',
				success : function(response, opts) {
					var obj = Ext.decode(response.responseText);//响应
				store.proxy = new Ext.data.PagingMemoryProxy(obj),//PagingMemoryProxy()分页
						store.load( {
							params : {
								start : 0,
								limit : 20
							}
						});//20条记录
				Ext.Msg.alert("提示", "搜索成功!");
			},
			failure : function() {
				Ext.Msg.alert("提示", "您还没有输入要查询的客户身份证号码!");
			},
			params : {
				cusId : cusid
			}
			});
}


//**********查询特定客户订票信息***********

var search_comCode= new Ext.form.TextField( {
	name : 'search_comCode',
	id : 'search_comCode',
	fieldLabel : '航空公司',
//	allowBlank : false
});

var search_booNo = new Ext.form.TextField( {
	name : 'search_booNo',
	id : 'search_booNo',
	fieldLabel : '航班号',
//	allowBlank : false
});

var search_cusId = new Ext.form.TextField( {
	name : 'search_cusId',
	id : 'search_cusId',
	fieldLabel : '身份证号',
//	allowBlank : false
});

//查询条件信息
var search_information = new Ext.form.FormPanel( {
	labelSeparator : ':',
	labelWidth : 80,// 标签宽度
	region : 'east',
	frame : true,
	autoheight : true,
	labelAlign:'right',
	width : '100%',
	layout : 'form',
	items : [ {
			columnWidth : .5,
			layout : 'form',
			xtype : 'fieldset',
			width : '100%',
			title : '客户查询信息',
			height : 150,
			style : 'padding:5 5 5 5',
			items : [ search_comCode, search_booNo,search_cusId ]
		}
		]
});

//查询特定条件的客户信息
var cusId_Info_form = new Ext.Window( {
	title : '<font size=2>客户查询信息</font>',
	modal : true,
	width : '300',
	height : '200',
	autoheight : true,
	resizable : false,
	plain : true,
	iconCls : 'information',
	bodyStyle : 'padding:5px;',
	buttonAlign : 'center',
	closeAction : 'hide',
	items : search_information,
	listeners : {
		"show" : function() {
		}
	},
	buttons : [ {
		text : '<font size=2>查询</font>',
		iconCls : 'zoom',
		handler : function() {
			search_info()
		}
	}, 
	{
		text : '<font size=2>重置</font>',
		handler : function() {
			search_information.getform.reset();
	}
	},
	{
		text : '<font size=2>取消</font>',
		handler : function() {
			cusId_Info_form.hide();
	}
	} ]
});


function search_info() {
	Ext.Ajax.request( { //ajax响应
				url :'Bookinformation?type=search_cusId_information',
				method : 'post',
				success : function(response, opts) {
					var obj = Ext.decode(response.responseText);//响应
				store1.proxy = new Ext.data.PagingMemoryProxy(obj),//PagingMemoryProxy()分页
						store1.load( {
							params : {
								start : 0,
								limit : 20
							}
						});//20条记录
			//	Ext.Msg.alert("提示", "搜索成功!");
			    JsHelper.OK("搜索成功!",function(){
			      cusId_Info_form.hide();
				  search_information.getForm().reset();
			    })
				
			},
			failure : function() {
				Ext.Msg.alert("提示", "您还没有输入要查询的相应条件!");
			},
			params : {
			    comCode:Ext.getCmp('search_comCode').getValue(),
			    booNo:Ext.getCmp('search_booNo').getValue(),
				cusId :Ext.getCmp('search_cusId').getValue()
			}
			});
}


//用户订票信息表格显示 
var start = 0;
var limit = 10;
var store1 = new Ext.data.JsonStore( {
	root : 'root',
	totalProperty : 'totalCount',
	url : 'Bookinformation?type=QueryAll',
	fields : [ {
		name : 'booAutoid'
	}, {
		name : 'comCode'
	}, {
		name : 'cusId'
	}, {
		name : 'booEveryday'
	}, {
		name : 'booNo'
	}, {
		name : 'booBaddress'
	}, {
		name : 'booAaddress'
	}, {
		name : 'booBtime'
	}, {
		name : 'booAtime'
	}, {
		name : 'booBerth'
	}, {
		name : 'booNumber'
	}, {
		name : 'booFare'
	}, {
		name : 'booTime'
	}, {
		name : 'flagPay'
	}, {
		name : 'flagPass'
	} ]
});

//grid start
var grid_bookinformation = new Ext.grid.GridPanel( {
	title : '<font size=2>客户订票信息</font>',
	id : 'id',
	stripeRows : true, // 斑马线
	selModel : new Ext.grid.RowSelectionModel( {
		singleSelect : true
	}),// 只允许选中一行
	trackMouseOver : true,
	width : '50%',
	store : store1,
	iconCls : 'information',
	region : 'center',
	autoheight : true,
	loadMask : {
		msg : '正在加载数据，请稍侯……'
	},
	frame : true,
	listeners : {
		"rowdblclick" : function() { //双击触发事件
			win_show_cusinfo.show();
			var row = grid_bookinformation.getSelectionModel().getSelections();
			cus_information.getForm().reset();
			cus_information.getForm().loadRecord(row[0]);

		}
	},
	//loadMask : {
	//	msg : '正在加载数据，请稍侯……'
	//},
	columns : [ new Ext.grid.RowNumberer(), // 行号
			{
				header : '<font size=2>航空公司</font>',
				dataIndex : 'comCode',
				sortable : true
			}, {
				header : '<font size=2>航班号</font>',
				dataIndex : 'booNo',
				sortable : true
			}, {
				header : '<font size=2>身份证</font>',
				dataIndex : 'cusId',
				sortable : true
			}, {
				header : '<font size=2>日期</font>',
				dataIndex : 'booEveryday',
				sortable : true
			}, {
				header : '<font size=2>起始地址</font>',
				dataIndex : 'booBaddress',
				sortable : true
			}, {
				header : '<font size=2>抵达地址</font>',
				dataIndex : 'booAaddress',
				sortable : true
			}, {
				header : '<font size=2>起始时间</font>',
				dataIndex : 'booBtime',
				width : 180,
				sortable : true
			}, {
				header : '<font size=2>抵达时间</font>',
				dataIndex : 'booAtime',
				width : 180,
				sortable : true
			}, {
				header : '<font size=2>舱位</font>',
				dataIndex : 'booBerth',
				sortable : true
			}, {
				header : '<font size=2>座位号</font>',
				dataIndex : 'booNumber',
				sortable : true
			}, {
				header : '<font size=2>订票时间</font>',
				dataIndex : 'booTime',
				width : 180,
				sortable : true
			}, {
				header : '<font size=2>金额</font>',
				dataIndex : 'booFare',
				sortable : true
			}, {
				header : '<font size=2>是否付款</font>',
				dataIndex : 'flagPay',
				sortable : true
			}, {
				header : '<font size=2>是否为退票</font>',
				dataIndex : 'flagPass',
				sortable : true
			} ],
	viewConfig : {
		forceFit : true
	},
	// 顶部操作栏
	tbar : new Ext.Toolbar( [
			'->',
            '-',
            {
				text : '<font size=2>查询</font>',
				iconCls : 'zoom',
				handler : function() {
					cusId_Info_form.show()

				}
			},
			'-',
			{
				text : '<font size=2>订票</font>',
				iconCls : 'add',
				handler : function() {

					var s = grid_flightinformation.getSelectionModel()
							.getSelections();
					if (s.length == 0) {
						Ext.Msg.alert("提示", "您还没有选择相应的航班信息,请先选择，谢谢!");
					} else {
						row = grid_flightinformation.getSelectionModel()
								.getSelected();
						win_add.show();
						Ext.getCmp('comCode_add').setValue(row.get('comCode')),
								Ext.getCmp('booNo_add').setValue(
										row.get('fliNo')),
								/*Ext.getCmp('booNo_add').setValue(
										row.get('airCode')),*/
										Ext.getCmp('booEveryday_add').setValue(row.get('fliEveryday')), 
										Ext.getCmp('booBaddress_add').setValue(row.get('fliBaddress')), 
										Ext.getCmp('booAaddress_add').setValue(row.get('fliAaddress')),
										Ext.getCmp('booBtime_add').setValue(row.get('fliBtime')), 
										Ext.getCmp('booAtime_add').setValue(row.get('fliAtime')), 
										Ext.getCmp('booTime_add').setValue(
											new Date().format("Y-m-d H:m:s"))
											<%--(new Date()).toLocaleDateString()
												+ " "
												+ (new Date())
														.toLocaleTimeString())--%>

					}
				}
			},
			'-',
			{
				text : '<font size=2>退票</font>',
				iconCls : 'delete',
				handler : function() {
					if (grid_bookinformation.getSelectionModel()
							.getSelections().length == 0) {
						JsHelper.ShowError("请选择一行!");
						return;
					}
					var row = grid_bookinformation.getSelectionModel()
							.getSelected();
					Ext.Ajax.request( {
						url : 'Bookinformation?type=update',
						params : {
							booAutoid : row.get('booAutoid'),
							comCode : row.get('comCode'),
							booNo : row.get('booNo'),
							booBaddress : row.get('booBaddress'),
							booNumber : row.get('booNumber'),
							booBtime : row.get('booBtime'),
							flagPass : Ext.getCmp('flagPass').getValue()
						},
						success : function() {
							JsHelper.OK("操作成功!", function() {
								store.reload();
								store1.reload();
							});
						},
						failure : function() {
							JsHelper.ShowError("操作失败!");
						}
					})
				}
			}, '-' ]),
	// 底部分页栏
	bbar : new Ext.PagingToolbar( {
		pageSize : limit,
		store : store1,
		displayInfo : true,// 显示分页按钮
		displayMsg : '<font size=2>第 {0} 条到 {1} 条,一共 {2} 条记录</font>',
		emptyMsg : '没有记录'
	})
});

Ext.onReady(function() {
	Ext.QuickTips.init();// 初始化
		// -----左边显示表格，右边显示信息修改
		new Ext.Viewport( {
			layout : 'border',
			items : [ grid_bookinformation, grid_flightinformation ]
		});
		// -----装载数据
		store1.load( {
			params : {
				start : start,
				limit : limit
			}
		});

	})
</script>

	</head>

	<body>

	</body>
</html>
