//编辑: 李招意
//酒店信息管理

//酒店信息修改
var hot_Name = new Ext.form.TextField({
			name : 'hotelName',
			fieldLabel : '酒店名称',
			itemCls : 'float-left',
			clearCls : 'allow-float',
			allowBlank : false
		});
		
var hot_Level = new Ext.form.TextField({
			name : 'hotelLevel',
			fieldLabel : '酒店等级',
			itemCls : 'float-left',
			clearCls : 'allow-float',
			allowBlank : false
		});
		
var hot_Tel = new Ext.form.TextField({
			name : 'hotelTel',
			fieldLabel : '酒店电话',
			itemCls : 'float-left',
			clearCls : 'allow-float',
			allowBlank : false
		});

var hot_City= new Ext.form.TextField({
			name : 'hotelCity',
			fieldLabel : '酒店所在城市',
			itemCls : 'float-left',
			clearCls : 'allow-float',
			allowBlank : false
		});
		
var hot_Address = new Ext.form.TextField({
			name : 'hotelAddress',
			fieldLabel : '酒店具体位置',
			itemCls : 'float-left',
			clearCls : 'allow-float',
			allowBlank : false
		});
		
var hot_Page = new Ext.form.TextField({
			name : 'hotelPage',
			fieldLabel : '酒店主页',
			itemCls : 'float-left',
			clearCls : 'allow-float',
			allowBlank : false
		});
		
var hot_Picture= new Ext.form.TextField({
			name : 'hotelPicture',
			fieldLabel : '酒店外观图片',
			itemCls : 'float-left',
			clearCls : 'allow-float',
			allowBlank : false
		});
		

//更新用的pannel
var updateform = new Ext.form.FormPanel({
//			title : '<font size=2>酒店信息修改</font>',
			labelSeparator : ':',
			labelWidth : 80,// 标签宽度
			iconCls : 'information',
			region : 'east',
			bodyStyle : 'padding:5 5 5 5',
			frame : true,
			autoheight : true,
			width : '100%',
			defaults:"textfield",
			layout : 'form',
			items : [{
						layout : 'column',
						items : [{
									layout : 'form',
									xtype : 'fieldset',
									width : '99%',
									title : '酒店信息',
									height : 70,
									items : [hot_Name]
								}]},
								{
						layout : 'column',
						items : [{
									columnWidth : .5,
									layout : 'form',
									xtype : 'fieldset',
									width : '100%',
									title : '酒店信息',
									height : 120,
									items : [hot_Level,hot_Tel,hot_City]
								}, {
									columnWidth : .5,
									layout : 'form',
									xtype : 'fieldset',
									width : '100%',
									title : '酒店信息',
									height : 120,
									items : [hot_Address,hot_Page,hot_Picture]
								}]
					} ]
		});
	
var updateWin = new Ext.Window({
			title : '<font size=2>酒店信息修改</font>',
			modal : true,
			width : 550,
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
										url : 'Hotelinformation?type=update',
										params : {
											hotelAutoid : row.get('hotelAutoid')
										},
										success : function() {
											JsHelper.OK("操作成功!", function() {
														store.reload();
														updateWin.hide();
													});
										},
										failure : function() {
											JsHelper.ShowError("操作失败(酒店名称可能重复)!");
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
//增加酒店信息
var hot_Name1 = new Ext.form.TextField({
			name : 'hotelName',
			fieldLabel : '酒店名称',
			itemCls : 'float-left',
			clearCls : 'allow-float',
			allowBlank : false
		});
		
var hot_Level1 = new Ext.form.TextField({
			name : 'hotelLevel',
			fieldLabel : '酒店等级',
			itemCls : 'float-left',
			clearCls : 'allow-float',
			allowBlank : false
		});
		
var hot_Tel1 = new Ext.form.TextField({
			name : 'hotelTel',
			fieldLabel : '酒店电话',
			itemCls : 'float-left',
			clearCls : 'allow-float',
			allowBlank : false
		});

var hot_City1= new Ext.form.TextField({
			name : 'hotelCity',
			fieldLabel : '酒店所在城市',
			itemCls : 'float-left',
			clearCls : 'allow-float',
			allowBlank : false
		});
		
var hot_Address1 = new Ext.form.TextField({
			name : 'hotelAddress',
			fieldLabel : '酒店具体位置',
			labelAlign :'right',
			itemCls : 'float-left',
			clearCls : 'allow-float',
			allowBlank : false
		});
		
var hot_Page1 = new Ext.form.TextField({
			name : 'hotelPage',
			fieldLabel : '酒店主页',
			itemCls : 'float-left',
			clearCls : 'allow-float',
			allowBlank : false
		});
		
var hot_Picture1 = new Ext.form.TextField({
			name : 'hotelPicture',
			fieldLabel : '酒店外观图片',
			itemCls : 'float-left',
			clearCls : 'allow-float',
			allowBlank : false
		});
		

//增加酒店信息用的panel
var addform = new Ext.form.FormPanel({
		//  title : '<font size=2>增加酒店信息</font>',
			labelSeparator : ':',
			labelAlign : 'right',
			labelWidth : 80,// 标签宽度
			iconCls : 'information',
			//region : 'east',
			//bodyStyle : 'padding:5 5 5 5',
			frame : true,
			autoheight : true,
			width : '100%',
			layout : 'form',
			items : [{
						layout : 'column',
						items : [{
									layout : 'form',
									xtype : 'fieldset',
									width : '99%',
									title : '酒店信息',
									height : 70,
									items : [hot_Name1]
								}]},
								{
						layout : 'column',
						items : [{
									columnWidth : .5,
									layout : 'form',
									xtype : 'fieldset',
									width : '100%',
									title : '酒店信息',
									height : 120,
									items : [hot_Level1,hot_Tel1,hot_City1]
								}, {
									columnWidth : .5,
									layout : 'form',
									xtype : 'fieldset',
									width : '100%',
									style : 'margin-left:10px;',
									title : '酒店信息',
									height : 120,
									items : [hot_Address1,hot_Page1,hot_Picture1]
								}]
					} ]
		});
	
var addWin = new Ext.Window({
			title : '<font size=2>增加酒店信息</font>',
			modal : true,
			width : 555,
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
										url : 'Hotelinformation?type=add',
										success : function() {
											JsHelper.OK("操作成功!", function() {
														store.reload();
    													addWin.hide();
													});
										},
										failure : function() {
											JsHelper.ShowError("操作失败(酒店名称可能重复)!");
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
	//酒店信息查询
var hot_City2 = new Ext.form.TextField({
			name : 'hotelCity',
			fieldLabel : '酒店所在城市',
			itemCls : 'float-left',
			clearCls : 'allow-float'
		});
		
var hot_Level2 = new Ext.form.TextField({
			name : 'hotelLevel',
			fieldLabel : '酒店等级',
			itemCls : 'float-left',
			clearCls : 'allow-float'
		});

var hot_Tel2 = new Ext.form.TextField({
			name : 'hotelTel',
			fieldLabel : '酒店电话',
			itemCls : 'float-left',
			clearCls : 'allow-float',
			allowBlank : false
		});

var hot_City2= new Ext.form.TextField({
			name : 'hotelCity',
			fieldLabel : '酒店所在城市',
			itemCls : 'float-left',
			clearCls : 'allow-float',
			allowBlank : false
		});
		
var hot_Address2 = new Ext.form.TextField({
			name : 'hotelAddress',
			fieldLabel : '酒店具体位置',
			labelAlign :'right',
			itemCls : 'float-left',
			clearCls : 'allow-float',
			allowBlank : false
		});
		
var hot_Page2 = new Ext.form.TextField({
			name : 'hotelPage',
			fieldLabel : '酒店主页',
			itemCls : 'float-left',
			clearCls : 'allow-float',
			allowBlank : false
		});
		
var hot_Picture2 = new Ext.form.TextField({
			name : 'hotelPicture',
			fieldLabel : '酒店外观图片',
			itemCls : 'float-left',
			clearCls : 'allow-float',
			allowBlank : false
		});
var searchform = new Ext.form.FormPanel({
//			title : '<font size=2>酒店信息查询</font>',
			labelSeparator : ':',
			labelWidth : 80,// 标签宽度
			iconCls : 'information',
			region : 'east',
			bodyStyle : 'padding:5 5 5 5',
			frame : true,
			autoheight : true,
			width : '100%',
			layout : 'form',
			items : [
								{
						layout : 'column',
						items : [{
									columnWidth : .5,
									layout : 'form',
									xtype : 'fieldset',
									width : '100%',
									style : 'margin-left:10px;',
									title : '酒店等级',
									height : 70,
									items : [hot_Level2]
								}, {
									columnWidth : .5,
									layout : 'form',
									xtype : 'fieldset',
									width : '100%',
									style : 'margin-left:10px;',
									title : '酒店所在城市',
									height : 70,
									items : [hot_City2]
								}]
					} ]
		});
	
var searchWin = new Ext.Window({
			title : '<font size=2>酒店信息查询</font>',
			modal : true,
			width : 600,
			height : 155,
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
								url : 'Hotelinformation?type=query',
								params : {

									hotelLevel : searchform.getForm().findField('hotelLevel').getValue(),
									hotelCity  : searchform.getForm().findField('hotelCity').getValue()

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
		

// ----酒店信息表格显示
var start = 0;
var limit = 10;
var store = new Ext.data.JsonStore({
			root : 'root',
			totalProperty : 'totalCount',
			url : 'Hotelinformation?type=queryall',
			fields : [{name : 'hotelAutoid'}, 
				      {name : 'hotelName'}, 
				      {name : 'hotelLevel'}, 
				      {name : 'hotelTel'}, 
				      {name : 'hotelCity'},
				      {name : 'hotelAddress'}, 
				      {name : 'hotelPage'}, 
				      {name : 'hotelPicture'}
				      ]
		});
		

var grid = new Ext.grid.GridPanel({
			title : '<font size=2>酒店信息表格显示</font>',
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
						header : '<font size=2>酒店名称</font>',
						width : 50,
						align : 'center',
						dataIndex : 'hotelName',
						sortable : true
					}, {
						header : '<font size=2>酒店等级</font>',
						width : 50,
						dataIndex : 'hotelLevel',
						align : 'center',
						sortable : true
					},{
						header : '<font size=2>酒店电话</font>',
						width : 80,
						dataIndex : 'hotelTel',
						align : 'center',
						sortable : true
					},{
						header : '<font size=2>酒店所在城市</font>',
						width : 80,
						dataIndex : 'hotelCity',
						align : 'center',
						sortable : true
					}, {
						header : '<font size=2>酒店具体位置</font>',
						width : 80,
						dataIndex : 'hotelAddress',
						align : 'center',
						sortable : true
					},{
						header : '<font size=2>酒店主页</font>',
						width : 80,
						dataIndex : 'hotelPage',
						align : 'center',
						sortable : true
					},{
						header : '<font size=2>酒店外观图片</font>',
						width : 80,
						dataIndex : 'hotelPicture',
						align : 'center',
						sortable : true
					}],
			// 顶部操作栏
			tbar : new Ext.Toolbar(['->', '-', {
						text : '<font size=2>查询信息</font>',
		                iconCls : 'search',
						handler : function() {
							searchWin.show()
						}
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
										url : 'Hotelinformation?type=delete',
										params : {
											hotelAutoid : row.get('hotelAutoid')
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
								/*Ext.Ajax.request({
											url : 'Hotelinformation?type=update',
											params : {
												hotelAutoid : row.get('hotelAutoid')
											}
								});*/
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