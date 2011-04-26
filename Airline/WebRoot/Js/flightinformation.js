/* ============== */
/* 编辑者：叶茂安 */
/* ============ */

// 定义日期（星期几）
var weeks_data = [['星期一', '星期一'], ['星期二', '星期二'], ['星期三', '星期三'],
		['星期四', '星期四'], ['星期五', '星期五'], ['星期六', '星期六'], ['星期天', '星期天']];

var week_store = new Ext.data.SimpleStore({
			fields : ['value', 'weeks'],
			data : weeks_data
		});

//定义航班状态
var fli_status=[['正常','正常'],['取消','取消']];
var fli_store = new Ext.data.SimpleStore({
			fields : ['value', 'fli'],
			data : fli_status
		});

//定义订票系统的状态
var sys_status=[['运行','运行'],['关闭','关闭']];
var sys_store = new Ext.data.SimpleStore({
			fields : ['value', 'sys'],
			data : sys_status
		});

// 定义时间combobox
var time_data = [['00:00', '00:00'], ['00:30', '00:30'],
		['01:00', '01:00'], ['01:30', '01:30'], ['02:00', '02:00'],
		['02:30', '02:30'], ['03:00', '03:00'], ['03:30', '03:30'],
		['04:00', '04:00'], ['04:30', '04:30'], ['05:00', '05:00'],
		['05:30', '05:30'], ['06:00', '06:00'], ['07:30', '07:30'],
		['08:00', '08:00'], ['08:30', '08:30'], ['09:00', '09:00'],
		['09:30', '09:30'], ['10:00', '10:00'], ['10:30', '10:30'],
		['11:00', '11:00'], ['11:30', '11:30'], ['12:00', '12:00'],
		['12:30', '12:30'], ['12:00', '12:00'], ['12:30', '12:30'],
		['13:00', '13:00'], ['13:30', '13:30'], ['14:00', '14:00'],
		['15:30', '15:30'], ['16:00', '16:00'], ['17:30', '17:30'],
		['18:00', '18:00'], ['18:30', '18:30'], ['19:00', '19:00'],
		['19:30', '19:30'], ['20:00', '20:00'], ['20:30', '20:30'],
		['21:00', '21:00'], ['21:30', '21:30'], ['22:00', '22:00'],
		['22:30', '22:30'], ['23:00PM', '23:00'], ['23:30', '23:30']];

var time_store = new Ext.data.SimpleStore({
			fields : ['value', 'time'],
			data : time_data
		});

// combobox从数据库取值
// 从数据库中load comcode的数据

var com_store = new Ext.data.Store({
			proxy : new Ext.data.HttpProxy({
						url : "Flightinformation?type=add_comcode"
					}),
			reader : new Ext.data.ArrayReader({}, [{
								name : 'value'
							}, {
								name : "text"
							}])
		});

// 从数据库中load airtype的数据
var air_store = new Ext.data.Store({
			proxy : new Ext.data.HttpProxy({
						url : "Flightinformation?type=add_airtype"
					}),
			reader : new Ext.data.ArrayReader({}, [{
								name : 'value'
							}, {
								name : "text"
							}])
		});

// 增加的时候，航空代码、机型号码、起
// 航班管理-->增加航班信息
var com_code = new Ext.form.ComboBox({
			name : 'comCode',
			fieldLabel : '航空公司代号',
			itemCls : 'float-left',
			clearCls : 'allow-float',
			allowBlank : false,
			store : com_store,
			mode : 'remote',
			triggerAction : 'all',
			valueField : 'value',
			displayField : 'text'
		})

var air_code = new Ext.form.ComboBox({
			name : 'airCode',
			fieldLabel : '机型号码',
			itemCls : 'float-left',
			clearCls : 'allow-float',
			allowBlank : false,
			store : air_store,
			mode : 'remote',
			triggerAction : 'all',
			valueField : 'value',
			displayField : 'text',
			
		})
/*
 * 	为comboBox设置监听事件
 */
air_code.on('select',function(comboBox){
		//取数据，填充数据
	Ext.Ajax.request({
		url : 'Flightinformation?type=auto_insert',
		params : {
			airCode : addform.getForm().findField('airCode').getValue()
		},
		success : function(response, opts) {
			obj = Ext.decode(response.responseText);
			//取得json数组的值
			addform.getForm().findField('fliFnumber').setValue(obj[0].Fnumber);
			addform.getForm().findField('fliCnumber').setValue(obj[0].Cnumber);
			addform.getForm().findField('fliYnumber').setValue(obj[0].Ynumber);
			addform.getForm().findField('fliRefundtime').setValue("正常");
			addform.getForm().findField('fliRefund').setValue("运行");
		},
		failure : function() {
				//JsHelper.ShowError("操作失败!");
				//alert(findform.getForm().findField('airCode').getValue());
		}
	});
})

var fli_no = new Ext.form.NumberField({
			name : 'fliNo',
			fieldLabel : '航班号',
			itemCls : 'float-left',
			clearCls : 'allow-float',
			allowBlank : false
		});
var fli_everyday = new Ext.form.ComboBox({
			name : 'fliEveryday',
			//itemCls : 'float-left',
			//clearCls : 'allow-float',
			store : week_store,
			fieldLabel : '飞行日期',
			emptyText : '请选择',
			editable : false,
			mode : 'local',
			valueField : 'value',
			displayField : 'weeks',
			triggerAction : 'all',
			allowBlank : false
		});
var fli_discount = new Ext.form.NumberField({
			name : 'fliDiscount',
			width:180,
			//itemCls : 'float-left',
			//clearCls : 'allow-float',
			fieldLabel : '折扣',
			allowBlank : false
		});
var fli_baddress = new Ext.form.TextField({
			name : 'fliBaddress',
			fieldLabel : '起始地点',
			width:180,
			allowBlank : false
		});
var fli_aaddress = new Ext.form.TextField({
			name : 'fliAaddress',
			fieldLabel : '抵达地点',
			width:180,
			allowBlank : false
		});
var fli_btime = new Ext.form.ComboBox({
			name : 'fliBtime',
			fieldLabel : '起飞时间',
			store : time_store,
			emptyText : '请选择',
			editable : false,
			mode : 'local',
			valueField : 'value',
			displayField : 'time',
			triggerAction : 'all',
			allowBlank : false
		});

var fli_atime = new Ext.form.ComboBox({
			name : 'fliAtime',
			fieldLabel : '抵达时间',
			store : time_store,
			emptyText : '请选择',
			editable : false,
			mode : 'local',
			valueField : 'value',
			displayField : 'time',
			triggerAction : 'all',
			allowBlank : false
		});

var fli_Fnumber = new Ext.form.NumberField({
			name : 'fliFnumber',
			fieldLabel : 'F人数',
			width:180,
			allowBlank : false
		});

var fli_Cnumber = new Ext.form.NumberField({
			name : 'fliCnumber',
			fieldLabel : 'C人数',
			width:180,
			allowBlank : false
		});

var fli_Ynumber = new Ext.form.NumberField({
			name : 'fliYnumber',
			fieldLabel : 'Y人数',
			width:180,
			allowBlank : false
		});

var fli_Ffare = new Ext.form.NumberField({
			name : 'fliFfare',
			//itemCls : 'float-left',
			//clearCls : 'allow-float',
			fieldLabel : 'F价格',
			width:180,
			allowBlank : false
		});
var fli_Cfare = new Ext.form.NumberField({
			name : 'fliCfare',
			width:180,
			//itemCls : 'float-left',
			//clearCls : 'allow-float',
			fieldLabel : 'C价格',
			allowBlank : false
		});
var fli_Yfare = new Ext.form.NumberField({
			name : 'fliYfare',
			//itemCls : 'float-left',
			//clearCls : 'allow-float',
			fieldLabel : 'Y价格',
			width:180,
			allowBlank : false
		});
var fli_refundtime = new Ext.form.TextField({
			name : 'fliRefundtime',
			width:180,
			//itemCls : 'float-left',
			//clearCls : 'allow-float',
			fieldLabel : '航班状态',
			//value : '正常',
			readOnly : true
		});
var fli_refund = new Ext.form.TextField({
			name : 'fliRefund',
			width:180,
			//itemCls : 'float-left',
			//clearCls : 'allow-float',
			fieldLabel : '系统状态',
			//value : '运行',
			readOnly : true
		});
// 增加用的formpanel
var addform = new Ext.form.FormPanel({
			labelSeparator : ':',
			labelAlign : 'right',
			labelWidth : 80,// 标签宽度
			frame : true,
			autoHeight : true,
			width : '100%',
			layout : 'form',
			items : [{
						layout : 'form',
						xtype : 'fieldset',
						width : '100%',
						title : '航班信息',
						height : 60,
						items : [com_code, air_code, fli_no]
					}, {
						layout : 'column',
						items : [{
									columnWidth : .5,
									layout : 'form',
									xtype : 'fieldset',
									width : '100%',
									title : '航班信息',
									height : 110,
									items : [fli_everyday, fli_discount]
								}, {
									columnWidth : .5,
									layout : 'form',
									xtype : 'fieldset',
									width : '100%',
									style : 'margin-left:20px;',
									title : '航班信息',
									height : 110,
									items : [fli_refundtime, fli_refund]
								}]
						
					}, {
						layout : 'column',
						items : [{
									columnWidth : .5,
									layout : 'form',
									xtype : 'fieldset',
									width : '100%',
									title : '航班信息',
									height : 110,
									items : [fli_baddress, fli_aaddress]
								}, {
									columnWidth : .5,
									layout : 'form',
									xtype : 'fieldset',
									width : '100%',
									style : 'margin-left:20px;',
									title : '航班信息',
									height : 110,
									items : [fli_btime, fli_atime]
								}]
					}, {
						layout : 'column',
						items : [{
									columnWidth : .5,
									layout : 'form',
									xtype : 'fieldset',
									width : '100%',
									title : '航班信息',
									height : 130,
									items : [fli_Fnumber, fli_Cnumber,fli_Ynumber]
								}, {
									columnWidth : .5,
									layout : 'form',
									xtype : 'fieldset',
									width : '100%',
									title : '航班信息',
									style : 'margin-left:20px;',
									height : 130,
									items : [fli_Ffare, fli_Cfare, fli_Yfare]
								}]
						/*layout : 'form',
						xtype : 'fieldset',
						width : '100%',
						title : '航班信息',
						height : 60,
						items : [fli_Ffare, fli_Cfare, fli_Yfare]*/
					/*}, {
						layout : 'form',
						xtype : 'fieldset',
						width : '100%',
						title : '航班信息',
						height : 60,
						items : [fli_refundtime, fli_refund]*/
					}]
		});
// 将addform加载到windows上（定义一个windows窗体）
var win = new Ext.Window({
			title : '<font size=2>增加航班信息</font>',
			modal : true,
			width : 825,
			height : 450,
			resizable : false,
			plain : true,
			iconCls : 'information',
			bodyStyle : 'padding:5px;',
			buttonAlign : 'center',
			closeAction : 'hide',
			items : addform,
			listeners : {
				"show" : function() {
				}
			},
			buttons : [{
						text : '<font size=2>增加</font>',
						iconCls : 'add',
						handler : function() {

							if (!addform.getForm().isValid())
								return;

							addform.getForm().submit({
										url : 'Flightinformation?type=add',
										success : function() {
											JsHelper.OK("操作成功!", function() {
														store.reload();
														addform.getForm().reset();
														win.hide();
													});
										},
										failure : function() {
											JsHelper.ShowError("操作失败(请确保所填信息的正确性)!");
											addform.getForm().reset();
											win.hide();
										}
									})
						}
					}, {
						text : '<font size=2>清空</font>',
						iconCls : 'arrow_rotate_anticlockwise',
						handler : function() {
							addform.getForm().reset();
						}
					}, {
						text : '<font size=2>取消</font>',
						iconCls : 'arrow_rotate_anticlockwise',
						handler : function() {
							addform.getForm().reset();
							win.hide();
						}
					}]
		});
/* #################################################################################################### */
// 航班信息修改
var com_code1 = new Ext.form.TextField({
			name : 'comCode',
			fieldLabel : '航空公司代号',
			itemCls : 'float-left',
			clearCls : 'allow-float',
			readOnly : true
		});

var air_code1 = new Ext.form.TextField({
			name : 'airCode',
			fieldLabel : '机型号码',
			itemCls : 'float-left',
			clearCls : 'allow-float',
			readOnly : true
		});
var fli_no1 = new Ext.form.NumberField({
			name : 'fliNo',
			fieldLabel : '航班号',
			itemCls : 'float-left',
			clearCls : 'allow-float',
			readOnly : true
		});
var fli_everyday1 = new Ext.form.ComboBox({
			name : 'fliEveryday',
			store : week_store,
			fieldLabel : '飞行日期',
			//itemCls : 'float-left',
			//clearCls : 'allow-float',
			emptyText : '请选择',
			editable : false,
			mode : 'local',
			valueField : 'value',
			displayField : 'weeks',
			triggerAction : 'all',
			allowBlank : false
		});
var fli_discount1 = new Ext.form.NumberField({
			name : 'fliDiscount',
			width:180,
			//itemCls : 'float-left',
			//clearCls : 'allow-float',
			fieldLabel : '折扣',
			allowBlank : false
		});
var fli_baddress1 = new Ext.form.TextField({
			width:180,
			name : 'fliBaddress',
			fieldLabel : '起始地点',
			allowBlank : false
		});
var fli_aaddress1 = new Ext.form.TextField({
			name : 'fliAaddress',
			width:180,
			fieldLabel : '抵达地点',
			allowBlank : false
		});
var fli_btime1 = new Ext.form.ComboBox({
			name : 'fliBtime',
			fieldLabel : '起飞时间',
			store : time_store,
			emptyText : '请选择',
			editable : false,
			mode : 'local',
			valueField : 'value',
			displayField : 'time',
			triggerAction : 'all',
			allowBlank : false
		});

var fli_atime1 = new Ext.form.ComboBox({
			name : 'fliAtime',
			fieldLabel : '抵达时间',
			store : time_store,
			emptyText : '请选择',
			editable : false,
			mode : 'local',
			valueField : 'value',
			displayField : 'time',
			triggerAction : 'all',
			allowBlank : false
		});
var fli_Fnumber1 = new Ext.form.NumberField({
			name : 'fliFnumber',
			fieldLabel : 'F人数',
			width:180,
			allowBlank : false
		});
var fli_Cnumber1 = new Ext.form.NumberField({
			name : 'fliCnumber',
			fieldLabel : 'C人数',
			width:180,
			allowBlank : false
		});
var fli_Ynumber1 = new Ext.form.NumberField({
			name : 'fliYnumber',
			width:180,
			fieldLabel : 'Y人数',
			allowBlank : false
		});
var fli_Ffare1 = new Ext.form.NumberField({
			name : 'fliFfare',
			fieldLabel : 'F价格',
			width:180,
			allowBlank : false
		});
var fli_Cfare1 = new Ext.form.NumberField({
			name : 'fliCfare',
			fieldLabel : 'C价格',
			width:180,
			allowBlank : false,
			blankText : 'C价格不为空'
		});
var fli_Yfare1 = new Ext.form.NumberField({
			name : 'fliYfare',
			width:180,
			fieldLabel : 'Y价格',
			allowBlank : false
		});
var fli_refundtime1 = new Ext.form.ComboBox({
			name : 'fliRefundtime',
			store : fli_store,
			editable : false,
			mode : 'local',
			valueField : 'value',
			displayField : 'fli',
			triggerAction : 'all',
			allowBlank : false,
			fieldLabel : '航班状态'
		});
var fli_refund1 = new Ext.form.ComboBox({
			name : 'fliRefund',
			store : sys_store,
			editable : false,
			mode : 'local',
			valueField : 'value',
			displayField : 'sys',
			triggerAction : 'all',
			allowBlank : false,
			fieldLabel : '系统状态'
		});
// 定义一个修改formpanel
var updateform = new Ext.form.FormPanel({
			labelSeparator : ':',
			labelAlign : 'right',
			labelWidth : 80,// 标签宽度
			iconCls : 'information',
			bodyStyle : 'padding:5 5 5 5',
			frame : true,
			autoHeight : true,
			width : '100%',
			layout : 'form',
			items : [{
						layout : 'form',
						xtype : 'fieldset',
						width : '100%',
						title : '航班信息',
						height : 60,
						items : [com_code1, air_code1, fli_no1]
					}, {
						layout : 'column',
						items:[{
							columnWidth : .5,
							layout : 'form',
							xtype : 'fieldset',
							width : '100%',
							title : '航班信息',
							height : 110,
							items : [fli_everyday1, fli_discount1]
						}, {
							columnWidth : .5,
							layout : 'form',
							xtype : 'fieldset',
							style : 'margin-left:20px;',
							width : '100%',
							title : '航班信息',
							height : 110,
							items : [fli_refundtime1, fli_refund1]
						}]
						
					}, {
						layout : 'column',
						items : [{
									columnWidth : .5,
									layout : 'form',
									xtype : 'fieldset',
									width : '100%',
									title : '航班信息',
									height : 110,
									items : [fli_baddress1, fli_aaddress1]
								}, {
									columnWidth : .5,
									layout : 'form',
									style : 'margin-left:20px;',
									xtype : 'fieldset',
									width : '100%',
									title : '航班信息',
									height : 110,
									items : [fli_btime1, fli_atime1]
								}]
					}, {
						layout : 'column',
						items : [{
									columnWidth : .5,
									layout : 'form',
									xtype : 'fieldset',
									width : '100%',
									title : '航班信息',
									height : 130,
									items : [fli_Fnumber1, fli_Cnumber1,
											fli_Ynumber1]
								}, {
									columnWidth : .5,
									layout : 'form',
									xtype : 'fieldset',
									width : '100%',
									title : '航班信息',
									style : 'margin-left:20px;',
									height : 130,
									items : [fli_Ffare1, fli_Cfare1, fli_Yfare1]
								}]
					}]
		});
// 将updateform加载到windows中，定义一个windows窗体
var win1 = new Ext.Window({
			title : '<font size=2>修改航班信息</font>',
			modal : true,
			width : 800,
			height : 450,
			resizable : false,
			plain : true,
			iconCls : 'information',
			bodyStyle : 'padding:5px;',
			buttonAlign : 'center',
			closeAction : 'hide',
			items : updateform,
			listeners : {
				"show" : function() {
				}
			},
			buttons : [{
				text : '<font size=2>确定</font>',
				iconCls : 'application_edit',
				handler : function() {
					if (!updateform.getForm().isValid())
						return;

					var row = grid.getSelectionModel().getSelected();
					Ext.Ajax.request({
								url : 'Flightinformation?type=update',
								params : {
									fliAutoid : row.get('fliAutoid'),
									comCode : updateform.getForm().findField('comCode').getValue(),
									airCode : updateform.getForm().findField('airCode').getValue(),
									fliEveryday : updateform.getForm().findField('fliEveryday').getValue(),
									fliNo : updateform.getForm().findField('fliNo').getValue(),
									fliDiscount : updateform.getForm().findField('fliDiscount').getValue(),
									fliBaddress : updateform.getForm().findField('fliBaddress').getValue(),
									fliAaddress : updateform.getForm().findField('fliAaddress').getValue(),
									fliBtime : updateform.getForm().findField('fliBtime').getValue(),
									fliAtime : updateform.getForm().findField('fliAtime').getValue(),
									fliFnumber : updateform.getForm().findField('fliFnumber').getValue(),
									fliCnumber : updateform.getForm().findField('fliCnumber').getValue(),
									fliYnumber : updateform.getForm().findField('fliYnumber').getValue(),
									fliFfare : updateform.getForm().findField('fliFfare').getValue(),
									fliCfare : updateform.getForm().findField('fliCfare').getValue(),
									fliYfare : updateform.getForm().findField('fliYfare').getValue(),
									fliRefundtime : updateform.getForm().findField('fliRefundtime').getValue(),
									fliRefund : updateform.getForm().findField('fliRefund').getValue()
								},
								success : function() {
									JsHelper.OK("操作成功!", function() {
												store.reload();
												win1.hide();
											});
								},
								failure : function() {
									JsHelper.ShowError("操作失败(航班号可能重复)!");
								}
							})
				}
			}, {
				text : '<font size=2>重置</font>',
				iconCls : 'arrow_undo',
				handler : function() {
					updateform.getForm().reset();
				}
			}]
		});
/* #################################################################################################### */
// 查询信息功能
var com_code2 = new Ext.form.ComboBox({
			name : 'comCode',
			fieldLabel : '航空公司代号',
			itemCls : 'float-left',
			clearCls : 'allow-float',
			store : com_store,
			mode : 'remote',
			triggerAction : 'all',
			valueField : 'value',
			displayField : 'text'
		});

var fli_no2 = new Ext.form.NumberField({
			name : 'fliNo',
			fieldLabel : '航班号',
			itemCls : 'float-left',
			clearCls : 'allow-float'
		});
var fli_baddress2 = new Ext.form.TextField({
			name : 'fliBaddress',
			fieldLabel : '起始地点'
		});
var fli_aaddress2 = new Ext.form.TextField({
			name : 'fliAaddress',
			fieldLabel : '抵达地点'
		});
var fli_btime2 = new Ext.form.ComboBox({
			name : 'fliBtime',
			fieldLabel : '起飞时间',
			store : time_store,
			editable : false,
			mode : 'local',
			valueField : 'value',
			displayField : 'time',
			triggerAction : 'all'
		});

var fli_atime2 = new Ext.form.ComboBox({
			name : 'fliAtime',
			fieldLabel : '抵达时间',
			store : time_store,
			editable : false,
			mode : 'local',
			valueField : 'value',
			displayField : 'time',
			triggerAction : 'all'
		});
// 定义一个查询formpanel
var findform = new Ext.form.FormPanel({
			labelSeparator : ':',
			labelAlign : 'right',
			labelWidth : 80,// 标签宽度
			iconCls : 'information',
			bodyStyle : 'padding:5 5 5 5',
			frame : true,
			autoHeight : true,
			width : '100%',
			layout : 'form',
			items : [{
						layout : 'form',
						xtype : 'fieldset',
						width : '100%',
						title : '查询航班信息',
						height : 60,
						items : [com_code2, fli_no2]
					}, {
						layout : 'column',
						items : [{
									columnWidth : .5,
									layout : 'form',
									xtype : 'fieldset',
									width : '100%',
									title : '查询航班信息',
									height : 110,
									items : [fli_baddress2, fli_aaddress2]
								}, {
									columnWidth : .5,
									layout : 'form',
									style : 'margin-left:20px;',
									xtype : 'fieldset',
									width : '100%',
									title : '查询航班信息',
									height : 110,
									items : [fli_btime2, fli_atime2]
								}]
					}]
		});
// 将一个formpanel加载到一个windows中
var findwin = new Ext.Window({
			title : '<font size=2>查询航班信息</font>',
			modal : true,
			width : 800,
			height : 260,
			resizable : false,
			plain : true,
			iconCls : 'information',
			bodyStyle : 'padding:5px;',
			buttonAlign : 'center',
			closeAction : 'hide',
			items : findform,
			listeners : {
				"show" : function() {
				}
			},
			buttons : [{
				text : '<font size=2>查询</font>',
				iconCls : 'zoom',
				handler : function() {
					/*
					 * if (findform.getForm().isValid()) { store.proxy = new
					 * Ext.data.HttpProxy({ url :
					 * 'Flightinformation?type=queryall' }); } else {
					 */
					Ext.Ajax.request({
								url : 'Flightinformation?type=query',
								params : {

									comCode : findform.getForm()
											.findField('comCode').getValue(),
									fliNo : findform.getForm()
											.findField('fliNo').getValue(),
									fliBaddress : findform.getForm()
											.findField('fliBaddress')
											.getValue(),
									fliAaddress : findform.getForm()
											.findField('fliAaddress')
											.getValue(),
									fliBtime : findform.getForm()
											.findField('fliBtime').getValue(),
									fliAtime : findform.getForm()
											.findField('fliAtime').getValue()

								},
								success : function(response, opts) {
									//前台一次性分页
									var obj = Ext.decode(response.responseText);// obj储存响应的数据
									store.proxy = new Ext.data.PagingMemoryProxy(obj), // PagingMemoryProxy()
									store.load({
												params : {
													start : start,
													limit : limit
												}
											});

									JsHelper.OK("查找完毕！", function() {
												findform.getForm().reset();
												findwin.hide();
											});
								},
								failure : function() {
									JsHelper.ShowError("操作失败(无效输入)!");
								}
							})
					/* } */
				}
			}, {
				text : '<font size=2>清空</font>',
				iconCls : 'arrow_rotate_anticlockwise',
				handler : function() {
					findform.getForm().reset();
				}
			}, {
				text : '<font size=2>取消</font>',
				iconCls : 'arrow_rotate_anticlockwise',
				handler : function() {
					findform.getForm().reset();
					findwin.close();
				}
			}]
		});

// -----航班信息表格显示
var start = 0;
var limit = 10;
var store = new Ext.data.JsonStore({
			root : 'root',
			totalProperty : 'totalCount',
			url : 'Flightinformation?type=queryall',
			fields : [{
						name : 'fliAutoid'
					}, {
						name : 'comCode'
					}, {
						name : 'airCode'
					}, {
						name : 'fliEveryday'
					}, {
						name : 'fliNo'
					}, {
						name : 'fliDiscount'
					}, {
						name : 'fliBaddress'
					}, {
						name : 'fliAaddress'
					}, {
						name : 'fliBtime'
					}, {
						name : 'fliAtime'
					}, {
						name : 'fliFnumber'
					}, {
						name : 'fliCnumber'
					}, {
						name : 'fliYnumber'
					}, {
						name : 'fliFfare'
					}, {
						name : 'fliCfare'
					}, {
						name : 'fliYfare'
					}, {
						name : 'fliRefundtime'
					}, {
						name : 'fliRefund'
					}]
		});
var grid = new Ext.grid.GridPanel({
			title : '<font size=2>航班信息表格显示</font>',
			stripeRows : true, // 斑马线
			selModel : new Ext.grid.RowSelectionModel({
						singleSelect : true
					}),// 只允许选中一行
			trackMouseOver : true,
			store : store,
			height : 450,
			frame : true,
			loadMask : {
				msg : '正在加载数据，请稍侯……'
			},
			listeners : {
				"rowclick" : function() {
					var row = grid.getSelectionModel().getSelections();
					updateform.getForm().reset();
					updateform.getForm().loadRecord(row[0]);
				},
				"rowdblclick" : function() {
					row = grid.getSelectionModel().getSelections();
					updateform.getForm().reset();
					updateform.getForm().loadRecord(row[0]);
					win1.show();
					//alert("航空代码为："+row[0].fliEveryday);
				}
			},
			viewConfig : {
				forceFit : true
			},
			columns : [
					new Ext.grid.RowNumberer(), // 行号
					{
						header : '<font size=2>航空公司代码</font>',
						width : 70,
						align : 'center',
						dataIndex : 'comCode',
						sortable : true
					}, {
						header : '<font size=2>机型号码</font>',
						width : 50,
						align : 'center',
						dataIndex : 'airCode',
						sortable : true
					}, {
						header : '<font size=2>日期</font>',
						width : 60,
						align : 'center',
						dataIndex : 'fliEveryday',
						sortable : true
					}, {
						header : '<font size=2>航班号</font>',
						width : 60,
						dataIndex : 'fliNo',
						align : 'center',
						sortable : true
					}, {
						header : '<font size=2>折扣</font>',
						width : 30,
						dataIndex : 'fliDiscount',
						align : 'center',
						sortable : true
					}, {
						header : '<font size=2>起始地点</font>',
						width : 50,
						dataIndex : 'fliBaddress',
						align : 'center',
						sortable : true
					}, {
						header : '<font size=2>抵达地点</font>',
						width : 50,
						dataIndex : 'fliAaddress',
						align : 'center',
						sortable : true
					}, {
						header : '<font size=2>起始时间</font>',
						width : 60,
						dataIndex : 'fliBtime',
						align : 'center',
						sortable : true
					}, {
						header : '<font size=2>抵达时间</font>',
						width : 60,
						dataIndex : 'fliAtime',
						align : 'center',
						sortable : true
					}, {
						header : '<font size=2>F人数</font>',
						width : 32,
						dataIndex : 'fliFnumber',
						align : 'center',
						sortable : true
					}, {
						header : '<font size=2>F价格</font>',
						width : 32,
						dataIndex : 'fliFfare',
						align : 'center',
						sortable : true
					}, {
						header : '<font size=2>C人数</font>',
						width : 32,
						dataIndex : 'fliCnumber',
						align : 'center',
						sortable : true
					}, {
						header : '<font size=2>C价格</font>',
						width : 32,
						dataIndex : 'fliCfare',
						align : 'center',
						sortable : true
					}, {
						header : '<font size=2>Y人数</font>',
						width : 32,
						dataIndex : 'fliYnumber',
						align : 'center',
						sortable : true
					}, {
						header : '<font size=2>Y价格</font>',
						width : 32,
						dataIndex : 'fliYfare',
						sortable : true
					}, {
						header : '<font size=2>航班状态</font>',
						width : 60,
						dataIndex : 'fliRefundtime',
						align : 'center',
						sortable : true
					}, {
						header : '<font size=2>系统状态</font>',
						align : 'center',
						width : 60,
						dataIndex : 'fliRefund',
						sortable : true
					}],
			// 顶部操作栏
			tbar : new Ext.Toolbar(['->', '-', {
						text : '<font size=2>查询信息</font>',
						handler : function() {
							findwin.show()
						},
						iconCls : 'zoom'
					}, '-', {
						text : '<font size=2>增加信息</font>',
						iconCls : 'add',
						handler : function() {
							win.show()
						}
					}, '-', {
						text : '<font size=2>删除信息</font>',
						iconCls : 'delete',
						handler : function() {
							if (grid.getSelectionModel().getSelections().length == 0) {
								JsHelper.ShowError("请选择一行!");
								return;
							}

							var row = grid.getSelectionModel().getSelected();
							Ext.Ajax.request({
										url : 'Flightinformation?type=delete',
										params : {
											fliAutoid : row.get('fliAutoid')
										},
										success : function() {
											JsHelper.OK("操作成功!", function() {
														store.reload();
													});
										},
										failure : function() {
											JsHelper.ShowError("操作失败!");
										}
									})
						}
					}, '-', {
						text : '<font size=2>修改信息</font>',
						iconCls : 'application_edit',
						handler : function() {
							if (grid.getSelectionModel().getSelections().length == 0) {
								JsHelper.ShowError("请选择一行!");
								return;
							} else {
								win1.show()
							}
						}
					}/*, '-', {
						text : '<font size=2>打印信息</font>',
						iconCls : 'printer'
					}, '-', {
						text : '<font size=2>导出信息</font>',
						iconCls : 'page_excel'
					}*/]),
			// 底部分页栏
			bbar : new Ext.PagingToolbar({
						pageSize : limit,
						store : store,
						displayInfo : true,// 显示分页按钮
						displayMsg : '<font size=2>第 {0} 条到 {1} 条,一共 {2} 条记录</font>',
						emptyMsg : '没有记录'
					})
		});
Ext.onReady(function() {
			Ext.QuickTips.init();// 初始化
			new Ext.Viewport({
						items : grid
					});
			// -----装载数据
			store.load({
						params : {
							start : start,
							limit : limit
						}
					});
		})