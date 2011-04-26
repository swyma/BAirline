//编辑: 卢伟灵
//飞机机型管理

//飞机机型信息修改
var air_code = new Ext.form.TextField({
			name : 'airCode',
			fieldLabel : '机型号码',
			itemCls : 'float-left',
			clearCls : 'allow-float',
			allowBlank : false
		});
		
var air_F = new Ext.form.TextField({
			name : 'airF',
			fieldLabel : 'F',
			itemCls : 'float-left',
			clearCls : 'allow-float',
			allowBlank : false
		});
		
var air_Fname = new Ext.form.TextField({
			name : 'airFname',
			fieldLabel : '头等舱',
			itemCls : 'float-left',
			clearCls : 'allow-float',
			allowBlank : false
		});

var air_Fnumber= new Ext.form.NumberField({
			name : 'airFnumber',
			fieldLabel : '容纳人数',
			itemCls : 'float-left',
			clearCls : 'allow-float',
			allowBlank : false
		});
		
var air_C = new Ext.form.TextField({
			name : 'airC',
			fieldLabel : 'C',
			itemCls : 'float-left',
			clearCls : 'allow-float',
			allowBlank : false
		});
		
var air_Cname = new Ext.form.TextField({
			name : 'airCname',
			fieldLabel : '公务舱',
			itemCls : 'float-left',
			clearCls : 'allow-float',
			allowBlank : false
		});
		
var air_Cnumber= new Ext.form.NumberField({
			name : 'airCnumber',
			fieldLabel : '容纳人数',
			itemCls : 'float-left',
			clearCls : 'allow-float',
			allowBlank : false
		});
		

var air_Y= new Ext.form.TextField({
			name : 'airY',
			fieldLabel : 'Y',
			itemCls : 'float-left',
			clearCls : 'allow-float',
			allowBlank : false
		});
		
var air_Yname = new Ext.form.TextField({
			name : 'airYname',
			fieldLabel : '经济舱',
			itemCls : 'float-left',
			clearCls : 'allow-float',
			allowBlank : false
		});
		
var air_Ynumber= new Ext.form.NumberField({
			name : 'airYnumber',
			fieldLabel : '容纳人数',
			itemCls : 'float-left',
			clearCls : 'allow-float',
			allowBlank : false
		});
		
var air_totalnumber=new Ext.form.NumberField({
	        name:'airTotalnumber',
	        fieldLabel:'总人数',
	        itemCls:'float-left',
	        clearCls:'allow=float',
	        allowBlank:false
})

//更新用的pannel
var updateform = new Ext.form.FormPanel({
//			title : '<font size=2>飞机机型信息修改</font>',
			labelSeparator : ':',
			labelWidth : 80,// 标签宽度
			iconCls : 'information',
			region : 'east',
			bodyStyle : 'padding:5 5 5 5',
			frame : true,
			autoheight : true,
			width : '100%',
			layout : 'form',
			items : [{
						layout : 'column',
						items : [{
									columnWidth : .5,
									layout : 'form',
									xtype : 'fieldset',
									width : '100%',
									title : '飞机机型信息',
									height : 70,
									items : [air_code]
								}, {
									columnWidth : .5,
									layout : 'form',
									xtype : 'fieldset',
									width : '100%',
									title : '可容纳总人数',
									style : 'margin-left:10px;',
									height : 70,
									items : [air_totalnumber]
								}]},
								{
						layout : 'column',
						items : [{
									columnWidth : .33,
									layout : 'form',
									xtype : 'fieldset',
									width : '100%',
									title : '头等舱信息',
									height : 120,
									items : [air_F,air_Fname,air_Fnumber]
								}, {
									columnWidth : .34,
									layout : 'form',
									xtype : 'fieldset',
									width : '100%',
									style : 'margin-left:10px;',
									title : '公务舱信息',
									height : 120,
									items : [air_C,air_Cname,air_Cnumber]
								},{
									columnWidth : .33,
									layout : 'form',
									xtype : 'fieldset',
									width : '100%',
									style : 'margin-left:10px;',
									title : '经济舱信息',
									height : 120,
									items : [air_Y,air_Yname,air_Ynumber]
								}]
					} ]
		});
	
var updateWin = new Ext.Window({
			title : '<font size=2>飞机机型信息修改</font>',
			modal : true,
			width : 800,
			height : 253,
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
						text : '<font size=2>提交</font>',
						iconCls : 'application_edit',
						handler : function() {
							var row = grid.getSelectionModel().getSelected();
							if (!updateform.getForm().isValid())
								return;
							updateform.getForm().submit({
										url : 'Airtype?type=update',
										params : {
											airAutoid : row.get('airAutoid')
										},
										success : function() {
											JsHelper.OK("操作成功!", function() {
														store.reload();
														updateWin.hide();
													});
										},
										failure : function() {
											JsHelper.ShowError("操作失败(机型号可能重复)!");
										}
									})
						}
					}, {
						text : '<font size=2>撤销所有</font>',
						iconCls : 'arrow_rotate_anticlockwise',
						handler : function() {
							updateform.getForm().reset();
						}
					}, {
						text : '<font size=2>取消</font>',
						iconCls : 'arrow_rotate_anticlockwise',
						handler : function() {
							updateWin.hide();
						}
					}]
		});
		
		/*.................................华丽的分割线...................................*/
//增加飞机机型信息
var air_code1 = new Ext.form.TextField({
			name : 'airCode',
			fieldLabel : '机型号码',
			itemCls : 'float-left',
			clearCls : 'allow-float',
			allowBlank : false
		});
		
var air_F1 = new Ext.form.TextField({
			name : 'airF',
			fieldLabel : 'F',
			itemCls : 'float-left',
			clearCls : 'allow-float',
			allowBlank : false
		});
		
var air_Fname1 = new Ext.form.TextField({
			name : 'airFname',
			fieldLabel : '头等舱',
			itemCls : 'float-left',
			clearCls : 'allow-float',
			allowBlank : false
		});

var air_Fnumber1= new Ext.form.NumberField({
			name : 'airFnumber',
			fieldLabel : '容纳人数',
			itemCls : 'float-left',
			clearCls : 'allow-float',
			allowBlank : false
		});
		
var air_C1 = new Ext.form.TextField({
			name : 'airC',
			fieldLabel : 'C',
			labelAlign :'right',
			itemCls : 'float-left',
			clearCls : 'allow-float',
			allowBlank : false
		});
		
var air_Cname1 = new Ext.form.TextField({
			name : 'airCname',
			fieldLabel : '公务舱',
			itemCls : 'float-left',
			clearCls : 'allow-float',
			allowBlank : false
		});
		
var air_Cnumber1 = new Ext.form.NumberField({
			name : 'airCnumber',
			fieldLabel : '容纳人数',
			itemCls : 'float-left',
			clearCls : 'allow-float',
			allowBlank : false
		});
		

var air_Y1 = new Ext.form.TextField({
			name : 'airY',
			fieldLabel : 'Y',
			itemCls : 'float-left',
			clearCls : 'allow-float',
			allowBlank : false
		});
		
var air_Yname1 = new Ext.form.TextField({
			name : 'airYname',
			fieldLabel : '经济舱',
			itemCls : 'float-left',
			clearCls : 'allow-float',
			allowBlank : false
		});
		
var air_Ynumber1 = new Ext.form.NumberField({
			name : 'airYnumber',
			fieldLabel : '容纳人数',
			itemCls : 'float-left',
			clearCls : 'allow-float',
			allowBlank : false
		});
		
var air_totalnumber1 =new Ext.form.NumberField({
	        name:'airTotalnumber',
	        fieldLabel:'总人数',
	        itemCls:'float-left',
	        clearCls:'allow=float',
	        allowBlank:false
})

//增加飞机机型信息用的panel
var addform = new Ext.form.FormPanel({
		//  title : '<font size=2>增加飞机机型信息</font>',
			labelSeparator : ':',
			labelWidth : 80,// 标签宽度
			iconCls : 'information',
			region : 'east',
			bodyStyle : 'padding:5 5 5 5',
			frame : true,
			autoheight : true,
			width : '100%',
			layout : 'form',
			items : [{
						layout : 'column',
						items : [{
									columnWidth : .5,
									layout : 'form',
									xtype : 'fieldset',
									width : '100%',
									title : '飞机机型信息',
									height : 70,
									items : [air_code1]
								}, {
									columnWidth : .5,
									layout : 'form',
									xtype : 'fieldset',
									width : '100%',
									title : '可容纳总人数',
									style : 'margin-left:10px;',
									height : 70,
									items : [air_totalnumber1]
								}]},
								{
						layout : 'column',
						items : [{
									columnWidth : .33,
									layout : 'form',
									xtype : 'fieldset',
									width : '100%',
									title : '头等舱信息',
									height : 120,
									items : [air_F1,air_Fname1,air_Fnumber1]
								}, {
									columnWidth : .34,
									layout : 'form',
									xtype : 'fieldset',
									width : '100%',
									style : 'margin-left:10px;',
									title : '公务舱信息',
									height : 120,
									items : [air_C1,air_Cname1,air_Cnumber1]
								},{
									columnWidth : .33,
									layout : 'form',
									xtype : 'fieldset',
									width : '100%',
									style : 'margin-left:10px;',
									title : '经济舱信息',
									height : 120,
									items : [air_Y1,air_Yname1,air_Ynumber1]
								}]
					} ]
		});
	
var addWin = new Ext.Window({
			title : '<font size=2>增加飞机机型信息</font>',
			modal : true,
			width : 800,
			height : 253,
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
										url : 'Airtype?type=add',
										success : function() {
											JsHelper.OK("操作成功!", function() {
														store.reload();
    													addWin.hide();
													});
										},
										failure : function() {
											JsHelper.ShowError("操作失败(飞机机型号可能重复)!");
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
							addWin.hide();
						}
					}]
		});
		
		/*.................................华丽的分割线...................................*/	
	//飞机机型信息查询
var air_code2 = new Ext.form.TextField({
			name : 'airCode',
			fieldLabel : '机型号码',
			itemCls : 'float-left',
			clearCls : 'allow-float'
		});
		
var air_F2 = new Ext.form.TextField({
			name : 'airF',
			fieldLabel : 'F',
			itemCls : 'float-left',
			clearCls : 'allow-float'
		});

var air_C2 = new Ext.form.TextField({
			name : 'airC',
			fieldLabel : 'C',
			itemCls : 'float-left',
			clearCls : 'allow-float'
		});
		
var air_Y2= new Ext.form.TextField({
			name : 'airY',
			fieldLabel : 'Y',
			itemCls : 'float-left',
			clearCls : 'allow-float'
		});
		
var air_totalnumber2=new Ext.form.NumberField({
	        id:'airTotalnumber',
	        name:'airTotalnumber',
	        fieldLabel:'总人数',
	        itemCls:'float-left',
	        clearCls:'allow=float'
})

var searchform = new Ext.form.FormPanel({
//			title : '<font size=2>飞机机型信息查询</font>',
			labelSeparator : ':',
			labelWidth : 80,// 标签宽度
			iconCls : 'information',
			region : 'east',
			bodyStyle : 'padding:5 5 5 5',
			frame : true,
			autoheight : true,
			width : '100%',
			layout : 'form',
			items : [{
						layout : 'column',
						items : [{
									columnWidth : .5,
									layout : 'form',
									xtype : 'fieldset',
									width : '100%',
									title : '飞机机型信息',
									height : 70,
									items : [air_code2]
								}, {
									columnWidth : .5,
									layout : 'form',
									xtype : 'fieldset',
									width : '100%',
									title : '可容纳总人数',
									style : 'margin-left:10px;',
									height : 70,
									items : [air_totalnumber2]
								}]},
								{
						layout : 'column',
						items : [{
									columnWidth : .33,
									layout : 'form',
									xtype : 'fieldset',
									width : '100%',
									title : '头等舱信息',
									height : 70,
									items : [air_F2]
								}, {
									columnWidth : .34,
									layout : 'form',
									xtype : 'fieldset',
									width : '100%',
									style : 'margin-left:10px;',
									title : '公务舱信息',
									height : 70,
									items : [air_C2]
								},{
									columnWidth : .33,
									layout : 'form',
									xtype : 'fieldset',
									width : '100%',
									style : 'margin-left:10px;',
									title : '经济舱信息',
									height : 70,
									items : [air_Y2]
								}]
					} ]
		});
	
var searchWin = new Ext.Window({
			title : '<font size=2>飞机机型信息查询</font>',
			modal : true,
			width : 800,
			height : 205,
			resizable : false,
			plain : true,
			iconCls : 'information',
			bodyStyle : 'padding:5px;',
			buttonAlign : 'center',
			closeAction : 'hide',
			items : searchform,
			listeners : {
				"show" : function() {
				}
			},
			buttons : [{
				text : '<font size=2>查询</font>',
				iconCls : 'search',
				handler : function() {
					Ext.Ajax.request({
								url : 'Airtype?type=query',
								params : {

									airCode : searchform.getForm().findField('airCode').getValue(),
									airF    : searchform.getForm().findField('airF').getValue(),
									airC    : searchform.getForm().findField('airF').getValue(),
									airY    : searchform.getForm().findField('airF').getValue(),
						     airTotalnumber : searchform.getForm().findField('airTotalnumber').getValue()

								},
								success : function(response, opts) {
									/*var ss=Ext.getCmp("airTotalnumber").getValue()=='0';
									if(!searchform.getForm().isValid() && !ss)
									{*/
										var obj = Ext.decode(response.responseText);// obj储存响应的数据
										store.proxy = new Ext.data.PagingMemoryProxy(obj);// PagingMemoryProxy()
										store.load({
												params : {
													start : start,
													limit : limit
												}
											});
									/*}
									else
									{
										store.proxy = new Ext.data.HttpProxy({
												url : 'Airtype?type=queryall'
											});
									}*/
									
									JsHelper.OK("操作成功!", function() {
												searchWin.hide();
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
					searchform.getForm().reset();
				}
			}, {
				text : '<font size=2>取消</font>',
				iconCls : 'arrow_rotate_anticlockwise',
				handler : function() {
					searchWin.hide();
				}
			}]
		});
		

// ----飞机机型信息表格显示
var start = 0;
var limit = 10;
var store = new Ext.data.JsonStore({
			root : 'root',
			totalProperty : 'totalCount',
			url : 'Airtype?type=queryall',
			fields : [{name : 'airAutoid'}, 
				      {name : 'airCode'}, 
				      {name : 'airF'}, 
				      {name : 'airFname'}, 
				      {name : 'airFnumber'},
				      {name : 'airC'}, 
				      {name : 'airCname'}, 
				      {name : 'airCnumber'},
				      {name : 'airY'}, 
				      {name : 'airYname'},
				      {name : 'airYnumber'}, 
				      {name : 'airTotalnumber'}
				      ]
		});
		

var grid = new Ext.grid.GridPanel({
			title : '<font size=2>飞机机型信息表格显示</font>',
			stripeRows : true, // 斑马线
			selModel : new Ext.grid.RowSelectionModel({
						singleSelect : true
					}),// 只允许选中一行
			trackMouseOver : true,
		    width : '100%',
		 	height : 550,
			store : store,
			iconCls : 'information',
			region : 'center',
			autoheight : true,
			frame : true,
			loadMask : {
				msg : '正在加载数据，请稍侯……'
			},
			listeners : {
				"rowclick" : function() {
					var row = grid.getSelectionModel().getSelections();
				 	updateform.getForm().reset();
				 	updateform.getForm().loadRecord(row[0]);
				}
			},
			viewConfig : {
				forceFit : true
			},
			columns : [
					new Ext.grid.RowNumberer(), // 行号
					{
						header : '<font size=2>机型号码</font>',
						width : 50,
						align : 'center',
						dataIndex : 'airCode',
						sortable : true
					}, {
						header : '<font size=2>F（舱号）</font>',
						width : 80,
						dataIndex : 'airF',
						align : 'center',
						sortable : true
					},{
						header : '<font size=2>头等舱（舱名）</font>',
						width : 80,
						dataIndex : 'airFname',
						align : 'center',
						sortable : true
					},{
						header : '<font size=2>F人数</font>',
						width : 30,
						dataIndex : 'airFnumber',
						align : 'center',
						sortable : true
					}, {
						header : '<font size=2>C</font>',
						width : 80,
						dataIndex : 'airC',
						align : 'center',
						sortable : true
					},{
						header : '<font size=2>公务舱</font>',
						width : 80,
						dataIndex : 'airCname',
						align : 'center',
						sortable : true
					},{
						header : '<font size=2>C人数</font>',
						width : 30,
						dataIndex : 'airCnumber',
						align : 'center',
						sortable : true
					},  {
						header : '<font size=2>Y</font>',
						width : 80,
						dataIndex : 'airY',
						align : 'center',
						sortable : true
					},{
						header : '<font size=2>经济舱</font>',
						width : 80,
						dataIndex : 'airYname',
						align : 'center',
						sortable : true
					},{
						header : '<font size=2>Y人数</font>',
						width : 30,
						dataIndex : 'airYnumber',
						align : 'center',
						sortable : true
					}, {
						header : '<font size=2>总人数</font>',
						align : 'center',
						width : 40,
						dataIndex : 'airTotalnumber',
						sortable : true
					}],
			// 顶部操作栏
			tbar : new Ext.Toolbar(['->', '-', {
						text : '<font size=2>查询信息</font>',
						handler : function() {
							searchWin.show()
						},
						iconCls : 'search'
					}, '-', {
						text : '<font size=2>增加信息</font>',
						iconCls : 'add',
						handler : function() {
						 	addWin.show()
						}
					},  '-', {
						text : '<font size=2>删除信息</font>',
						iconCls : 'delete',
						handler : function() {
							if (grid.getSelectionModel().getSelections().length == 0) {
								JsHelper.ShowError("请选择要删除的一行!");
								return;
							}

							var row = grid.getSelectionModel().getSelected();
							Ext.Ajax.request({
										url : 'Airtype?type=delete',
										params : {
											airAutoid : row.get('airAutoid')
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
								JsHelper.ShowError("请选择要修改的一行!");
								return;
							} else {
      							var row = grid.getSelectionModel().getSelected();
								Ext.Ajax.request({
											url : 'Airtype?type=update',
											params : {
												airAutoid : row.get('airAutoid')
											}
								});
								updateWin.show()
							}
						}
					}]),
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