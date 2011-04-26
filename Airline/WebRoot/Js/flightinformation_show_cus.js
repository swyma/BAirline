/* ============== */
/* 编辑者：杨海亮 */
/* ============ */

// 定义日期（星期几）
var weeks_data = [['星期一', '星期一'], ['星期二', '星期二'], ['星期三', '星期三'],
		['星期四', '星期四'], ['星期五', '星期五'], ['星期六', '星期六'], ['星期天', '星期天']];

var week_store = new Ext.data.SimpleStore({
			fields : ['value', 'weeks'],
			data : weeks_data
		});

// 定义时间combobox
var time_data = [['00:00AM', '00:00AM'], ['00:30AM', '00:30AM'],
		['01:00AM', '01:00AM'], ['01:30AM', '01:30AM'], ['02:00AM', '02:00AM'],
		['02:30AM', '02:30AM'], ['03:00AM', '03:00AM'], ['03:30AM', '03:30AM'],
		['04:00AM', '04:00AM'], ['04:30AM', '04:30AM'], ['05:00AM', '05:00AM'],
		['05:30AM', '05:30AM'], ['06:00AM', '06:00AM'], ['07:30AM', '07:30AM'],
		['08:00AM', '08:00AM'], ['08:30AM', '08:30AM'], ['09:00AM', '09:00AM'],
		['09:30AM', '09:30AM'], ['10:00AM', '10:00AM'], ['10:30AM', '10:30AM'],
		['11:00AM', '11:00AM'], ['11:30AM', '11:30AM'], ['12:00PM', '12:00PM'],
		['12:30PM', '12:30PM'], ['12:00PM', '12:00PM'], ['12:30PM', '12:30PM'],
		['13:00PM', '13:00PM'], ['13:30PM', '13:30PM'], ['14:00PM', '14:00PM'],
		['15:30PM', '15:30PM'], ['16:00PM', '16:00PM'], ['17:30PM', '17:30PM'],
		['18:00PM', '18:00PM'], ['18:30PM', '18:30PM'], ['19:00PM', '19:00PM'],
		['19:30PM', '19:30PM'], ['20:00PM', '20:00PM'], ['20:30PM', '20:30PM'],
		['21:00PM', '21:00PM'], ['21:30PM', '21:30PM'], ['22:00PM', '22:00PM'],
		['22:30PM', '22:30PM'], ['23:00PM', '23:00PM'], ['23:30PM', '23:30PM']];

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
			displayField : 'text'
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
			itemCls : 'float-left',
			clearCls : 'allow-float',
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
			itemCls : 'float-left',
			clearCls : 'allow-float',
			fieldLabel : '折扣',
			allowBlank : false
		});
var fli_baddress = new Ext.form.TextField({
			name : 'fliBaddress',
			fieldLabel : '起始地点',
			allowBlank : false
		});
var fli_aaddress = new Ext.form.TextField({
			name : 'fliAaddress',
			fieldLabel : '抵达地点',
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
			fieldLabel : 'F剩余人数',
			allowBlank : false
		});
var fli_Cnumber = new Ext.form.NumberField({
			name : 'fliCnumber',
			fieldLabel : 'C剩余人数',
			allowBlank : false
		});
var fli_Ynumber = new Ext.form.NumberField({
			name : 'fliYnumber',
			fieldLabel : 'Y剩余人数',
			allowBlank : false
		});
var fli_Ffare = new Ext.form.NumberField({
			name : 'fliFfare',
			fieldLabel : 'F价格',
			allowBlank : false
		});
var fli_Cfare = new Ext.form.NumberField({
			name : 'fliCfare',
			fieldLabel : 'C价格',
			allowBlank : false
		});
var fli_Yfare = new Ext.form.NumberField({
			name : 'fliYfare',
			fieldLabel : 'Y价格',
			allowBlank : false
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
						items : [com_code, fli_no]
					}, {
						layout : 'column',
						items : [{
									columnWidth : .5,
									layout : 'form',
									xtype : 'fieldset',
									width : '100%',
									title : '查询航班信息',
									height : 110,
									items : [fli_baddress, fli_aaddress]
								}, {
									columnWidth : .5,
									layout : 'form',
									style : 'margin-left:20px;',
									xtype : 'fieldset',
									width : '100%',
									title : '查询航班信息',
									height : 110,
									items : [fli_btime, fli_atime]
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
									var obj = Ext.decode(response.responseText);// obj储存响应的数据
									store.proxy = new Ext.data.PagingMemoryProxy(obj), // PagingMemoryProxy()
									store.load({
												params : {
													start : start,
													limit : limit
												}
											});

									store.proxy = new Ext.data.HttpProxy({
												url : 'Flightinformation?type=queryall'
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
			url : 'Flightinformation?type=fliInfo',
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
var grid_flightinformation = new Ext.grid.GridPanel({
			title : '<font size=2>航班信息表格显示</font>',
			stripeRows : true, // 斑马线
			selModel : new Ext.grid.RowSelectionModel({
						singleSelect : true
					}),// 只允许选中一行
			trackMouseOver : true,
			store : store,
			height : '300',
			region : 'north',
			frame : true,
			loadMask : {
				msg : '正在加载数据，请稍侯……'
			},
			listeners : {
				"rowdblclick" : function() {
					var row = grid_flightinformation.getSelectionModel().getSelections();
					win_add.show();
					Ext.getCmp('comCode_add').setValue(row[0].get('comCode')),
					Ext.getCmp('booNo_add').setValue(row[0].get('fliNo')),
					Ext.getCmp('booEveryday_add').setValue(row[0].get('fliEveryday')),
					Ext.getCmp('booBaddress_add').setValue(row[0].get('fliBaddress')),
					Ext.getCmp('booAaddress_add').setValue(row[0].get('fliAaddress')),
					Ext.getCmp('booBtime_add').setValue(row[0].get('fliBtime')),
					Ext.getCmp('booAtime_add').setValue(row[0].get('fliAtime')),
					Ext.getCmp('booTime_add').setValue((new Date().format('Y-m-d H:m:s')))
				}
			},
			viewConfig : {
				forceFit : true
			},
			columns : [
					new Ext.grid.RowNumberer(), // 行号
					{
						header : '<font size=2>航空公司代码</font>',
						width : 60,
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
						width : 80,
						align : 'center',
						dataIndex : 'fliEveryday',
						sortable : true
					}, {
						header : '<font size=2>航班号</font>',
						width : 40,
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
						width : 70,
						dataIndex : 'fliBtime',
						align : 'center',
						sortable : true
					}, {
						header : '<font size=2>抵达时间</font>',
						width : 70,
						dataIndex : 'fliAtime',
						align : 'center',
						sortable : true
					}, {
						header : '<font size=2>F人数</font>',
						width : 30,
						dataIndex : 'fliFnumber',
						align : 'center',
						sortable : true
					}, {
						header : '<font size=2>C人数</font>',
						width : 30,
						dataIndex : 'fliCnumber',
						align : 'center',
						sortable : true
					}, {
						header : '<font size=2>Y人数</font>',
						width : 30,
						dataIndex : 'fliYnumber',
						align : 'center',
						sortable : true
					}, {
						header : '<font size=2>F价格</font>',
						width : 30,
						dataIndex : 'fliFfare',
						align : 'center',
						sortable : true
					}, {
						header : '<font size=2>C价格</font>',
						width : 30,
						dataIndex : 'fliCfare',
						align : 'center',
						sortable : true
					}, {
						header : '<font size=2>Y价格</font>',
						width : 30,
						dataIndex : 'fliYfare',
						sortable : true
					}],
			// 顶部操作栏
			tbar : new Ext.Toolbar(['->', '-', {
						text : '<font size=2>查询信息</font>',
						handler : function() {
							findwin.show()
						},
						iconCls : 'zoom'
					},'-',{
					  
					}
					]),
			// 底部分页栏
			bbar : new Ext.PagingToolbar({
						pageSize : limit,
						store : store,
						displayInfo : true,// 显示分页按钮
						displayMsg : '<font size=2>第 {0} 条到 {1} 条,一共 {2} 条记录</font>',
						emptyMsg : '没有记录'
					})
		});
		
			// -----装载数据
		store.load({
						params : {
							start : start,
							limit : limit
						}
					});
