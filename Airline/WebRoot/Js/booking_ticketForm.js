//客户订票 
// 编辑者：杨海亮

var comCode_add = new Ext.form.TextField( {
	name : 'comCode_add',
	id : 'comCode_add',
	fieldLabel : '航空公司',
	allowBlank : false,
	width : '130',
	readOnly : true
});

var cusId_add = new Ext.form.NumberField( {
	name : 'cusId_add',
	id : 'cusId_add',
	fieldLabel : '身份证',
	width : '130',
	allowBlank : false
});
var booNo_add = new Ext.form.TextField( {
	name : 'fliNo',
	id : 'booNo_add',
	fieldLabel : '航班号',
	allowBlank : false,
	width : '130',
	readOnly : true
});

var booEveryday_add = new Ext.form.ComboBox( {
	name : 'booEveryday_add',
	id : 'booEveryday_add',
	fieldLabel : '飞行日期',
	allowBlank : false,
	width : 130,
	readOnly : true
});

var booBaddress_add = new Ext.form.TextField( {
	name : 'booBaddress_add',
	id : 'booBaddress_add',
	fieldLabel : '起始地址',
	readOnly : true,
	width : '130',
	itemCls : 'float-left',
	clearCls : 'allow-float',
	allowBlank : false
});

var booAaddress_add = new Ext.form.TextField( {
	name : 'booAaddress_add',
	id : 'booAaddress_add',
	fieldLabel : '抵达地址',
	readOnly : true,
	width : '130',
	itemCls : 'float-left',
	clearCls : 'allow-float',
	allowBlank : false
});

var booBtime_add = new Ext.form.TextField( {
	name : 'booBtime_add',
	id : 'booBtime_add',
	fieldLabel : '起始时间',
	allowBlank : false,
	width : 130,
	itemCls : 'float-left',
	clearCls : 'allow-float',
	readOnly : true
});
var booAtime_add = new Ext.form.TextField( {
	name : 'booAtime_add',
	id : 'booAtime_add',
	fieldLabel : '抵达时间',
	allowBlank : false,
	width : 130,
	itemCls : 'float-left',
	clearCls : 'allow-float',
	readOnly : true
});

var data_booBerth = [ [ '头等舱', '头等舱' ], [ '商务舱', '商务舱' ], [ '经济舱', '经济舱' ], ];
var store_booBerth = new Ext.data.SimpleStore( {
	fields : [ 'id_booBerth', 'name_booBerth' ],
	data : data_booBerth
});
var booBerth_add = new Ext.form.ComboBox( {
	store : store_booBerth,
	name : 'booBerth_add',
	id : 'booBerth_add',
	fieldLabel : '舱位',
	//  allowBlank:false,
	emptyText : '请选择',
	editable : false,
	width : 140,
	mode : 'local',
	triggerAction : 'all',
	typeAhead : true,
	valueField : 'id_booBerth',
	displayField : 'name_booBerth'
});

/*  var store_bookNumber = new Ext.data.Store({
proxy : new Ext.data.HttpProxy({
url : "Bookinformation?type=get_booNumber"
}),
reader : new Ext.data.ArrayReader({}, [{
		name : 'value'
	}, {
		name : "text"
	}])
});*/

booBerth_add.on('select', function(booBerth_add) {
	var value = get_booNumber_info();
	//alert(store_bookNumber);
		//Ext.getCmp('booNumber_add').setValue(store_bookNumber)
		get_flifare();
	});

var booNumber_add = new Ext.form.TextField( {
	name : 'booNumber_add',
	id : 'booNumber_add',
	fieldLabel : '座位号',
	width : '140',
	allowBlank : false,
	//editable : false,
	readOnly:true,
	hidden:true
		});

/*2011 3 19 */

/* 
var booNumber_add=new Ext.form.TextField({
store:store_bookNumber,
name:'booNumber_add',
id:'booNumber_add',
fieldLabel:'座位号',
allowBlank:false,
editable:false,
width:130,
mode:'remote',
valueField:'value',
displayField:'text'
});*/

var booTime_add = new Ext.form.TextField( {
	name : 'booTime_add',
	id : 'booTime_add',
	fieldLabel : '订票时间',
	width : '140',
	allowBlank : false,
	editable : false
});

var booFare_add = new Ext.form.TextField( {
	name : 'booFare_add',
	id : 'booFare_add',
	fieldLabel : '金额',
	allowBlank : false
});


var flagPay_add = new Ext.form.TextField( {
	name : 'flagPay_add',
	id : 'flagPay_add',
	fieldLabel : '是否付款',
	readOnly:true,
	value : '是',
	editable : false,
	width : 130
});

var flagPass_add = new Ext.form.TextField( {
	name : 'flagPass_add',
	id : 'flagPass_add',
	fieldLabel : '是否为退票',
	editable : false,
	readOnly:true,
	value : '否',
	width : 130
});

var booking_ticketForm = new Ext.form.FormPanel( {
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
			items : [ comCode_add, booNo_add ]
		}, {
			columnWidth : .5,
			layout : 'form',
			xtype : 'fieldset',
			width : '100%',
			title : '客户订票信息',
			style : 'margin-left:20px; padding:5 5 5 5',
			height : 80,
			items : [ cusId_add, booEveryday_add ]
		} ]
	}, {
		//2011-04-10 叶茂安修改
		layout : 'column',
		items : [ {
			columnWidth : .5,
			layout : 'form',
			xtype : 'fieldset',
			width : '100%',
			title : '航班起抵地址',
			height : '60',
			style : 'padding:5 5 5 5',
			items : [ booBaddress_add, booAaddress_add ]
		}, {
			columnWidth : .5,
			layout : 'form',
			xtype : 'fieldset',
			width : '100%',
			title : '航班起抵时间',
			height : '60',
			style : 'margin-left:20px;padding:5 5 5 5',
			items : [ booBtime_add, booAtime_add ]
		} ]
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
			items : [ booBerth_add, booNumber_add, booTime_add ]
		}, {
			columnWidth : .5,
			layout : 'form',
			xtype : 'fieldset',
			width : '100%',
			title : '客户订票信息',
			style : 'margin-left:20px; padding:5 5 5 5',
			height : 110,
			items : [ booFare_add, flagPay_add,flagPass_add ]
		} ]
	} ]
});

var win_add = new Ext.Window( {
	title : '<font size=2>增加客户订票信息</font>',
	modal : true,
	width : '849',
	height : '380',
	resizable : false,
	plain : true,
	iconCls : 'information',
	bodyStyle : 'padding:15px;',
	buttonAlign : 'center',
	closeAction : 'hide',
	items : booking_ticketForm,
	listeners : {
		"show" : function() {
		}
	},
	buttons : [ {
		text : '<font size=2>增加</font>',
		iconCls : 'add',
		handler : function() {
			add_account();
			booking_ticketForm.getForm().reset();
		}
	}/*, {
									text : '<font size=2>清空</font>',
									iconCls : 'arrow_rotate_anticlockwise',
									handler : function() {
										booking_ticketForm.getForm().reset();
									}
								}*/, {
		text : '<font size=2>取消</font>',
		handler : function() {
			booking_ticketForm.getForm('booBerth_add').reset();
			booking_ticketForm.getForm('booNumber_add').reset();
			win_add.hide();
		}
	} ]
});

//**********************************增加客户信息**************************
function add_account() {
Ext.Ajax.request({
			   url: 'Bookinformation?type=add',
			   method: "post",
			   params:{
			   	comCode:Ext.getCmp('comCode_add').getValue(),
				cusId:Ext.getCmp('cusId_add').getValue(),
				booNo:Ext.getCmp('booNo_add').getValue(),
				booEveryday:Ext.getCmp('booEveryday_add').getValue(),
				booBaddress:Ext.getCmp('booBaddress_add').getValue(),
				booAaddress:Ext.getCmp('booAaddress_add').getValue(),
				booBtime:Ext.getCmp('booBtime_add').getValue(),
				booAtime:Ext.getCmp('booAtime_add').getValue(),
				booBerth:Ext.getCmp('booBerth_add').getValue(),
				booNumber:Ext.getCmp('booNumber_add').getValue(),
				booTime:Ext.getCmp('booTime_add').getValue(),
				booFare:Ext.getCmp('booFare_add').getValue(),
				flagPay:Ext.getCmp('flagPay_add').getValue(),
				flagPass:Ext.getCmp('flagPass_add').getValue()
			   },
			   success: function(response,opts){
			  // JsHelper.OK("订票成功!");
				   JsHelper.OK(response.responseText);
			       store1.reload();
			       store.reload();
				   win_add.hide();
						},
			   failure: function(){
							 JsHelper.ShowError("发送到后台失败，请重新检查发送是否正常!")
						},
			   
			});
		}
   
     /*2011 3 19 */
	//*************************座位号信息***********************
	  function get_booNumber_info(){
	    Ext.Ajax.request({
	       url:"Bookinformation?type=get_booNumber",
	       method:'post',
	       success:function(response, opts){
				    var obj= Ext.decode(response.responseText);  //响应
				    
				    b=obj[0].text;
				    if(b!=0){
				    	   Ext.getCmp("booNumber_add").setValue(obj[0].text); 
				    }
				    else{
				    	    JsHelper.ShowError("对不起，座位已满！");	 	
				     }
				  //  Ext.Msg.alert("提示","成功!");			 
				},
		  failure: function(){ Ext.Msg.alert("提示","发送数据失败！请检查错误！");},
		  params: {
			     comCode:Ext.getCmp('comCode_add').getValue(),
			     booNo:Ext.getCmp('booNo_add').getValue(),
			     booBaddress:Ext.getCmp('booBaddress_add').getValue(),
			     booBtime:Ext.getCmp('booBtime_add').getValue(),
				 booBerth:Ext.getCmp('booBerth_add').getValue()
				}
		 
	  });
	}
	  
// 2011 4 8
//***************显示客户订票金额***************
  function get_flifare(){
	  Ext.Ajax.request({
		 url:"Bookinformation?type=get_flifare",
		 method:"post",
		 success:function(response,opts){
		   var obj1=Ext.decode(response.responseText);
		   Ext.getCmp("booFare_add").setValue(obj1[0].fliFare);
		 },
		 failure:function(){Ext.Msg.alert("提示","金额发生错误!")},
		 params:{
			     comCode:Ext.getCmp('comCode_add').getValue(),
			     booNo:Ext.getCmp('booNo_add').getValue(),
			     booBaddress:Ext.getCmp('booBaddress_add').getValue(),
			     booBtime:Ext.getCmp('booBtime_add').getValue(),
				 booBerth:Ext.getCmp('booBerth_add').getValue()	
				 }
	  });
  }

	
	