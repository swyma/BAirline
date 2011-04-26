Ext.onReady(function() {
// combobox从数据库取值
// 从数据库中load comcode的数据

	var com_store = new Ext.data.Store({
			proxy : new Ext.data.HttpProxy({
						url : "faretype?type=add_comcode"
					}),
			reader : new Ext.data.ArrayReader({}, [{
								name : 'value'
							}, {
								name : "text"
							}])
		});
	var row;// 选中行	
	// ******************************************更新票价信息*************************
	function update_account() {
		var value;
		Ext.Ajax.request({
					url : 'faretype?type=update',
					method : "post",
					success : function(response, opts) {
						if (response.responseText) {
							store.reload();
							Ext.Msg.alert("提示", "更新成功!");
						} else {
							Ext.Msg.alert("提示", "操作失败!");
						}
					},
					failure : function() {
						Ext.MessageBox.alert("提示", "发送到后台失败，请重新检查发送是否正常!");
					},
					params : {
						farComcode : Ext.getCmp('farComcode').getValue(),
						farGoldscore : Ext.getCmp('farGoldscore').getValue(),
						farGolddiscount: Ext.getCmp('farGolddiscount').getValue(),
						farSilscore: Ext.getCmp('farSilscore').getValue(),
						farSildiscount : Ext.getCmp('farSildiscount').getValue(),
						farBroscore : Ext.getCmp('farBroscore').getValue(),
						farBrodiscount : Ext.getCmp('farBrodiscount').getValue()				
					}
				});
	}
	
	// ******************************************修改票价信息*****************************************
	var window = new Ext.Window({
				title : '<font size=2>票价信息修改</font>',
				width : '20%',
				height : 285,
				layout : 'absolute',
				plain : true,
				modal : true,
				closeAction : 'hide',
				id : 'window',
				bodyStyle : 'padding:4px;',
				buttonAlign : 'center',
				items : [{
							x : 5,
							y : 5,
							xtype : 'label',
							text : '航空公司代号：'
						}, {
							x : 60,
							y : 5,
							xtype : 'textfield',
							//readOnly : 'true',
							//store : com_store,
							//mode : 'remote',
							//triggerAction : 'all',
							//valueField : 'value',
							id: 'farComcode',
							allowBlank : false,
							anchor : '100%'
						}, {
							x : 5,
							y : 35,
							xtype : 'label',
							text : '金牌客户积分：'
						}, {
							x : 60,
							y : 35,
							xtype : 'textfield',
							id : 'farGoldscore',
							allowBlank : false,
							anchor : '100%'
						}, {
							x : 5,
							y : 65,
							xtype : 'label',
							text : '金牌客户打折：'
						}, {
							x : 60,
							y : 65,
							xtype : 'textfield',
						    id: 'farGolddiscount',
							allowBlank : false,
							anchor : '100%'
						}, {
							x : 5,
							y : 95,
							xtype : 'label',
							text : '银牌客户积分：'
						}, {
							x : 60,
							y : 95,
							id: 'farSilscore',
							xtype : 'textfield',
							allowBlank : false,
							anchor : '100%'
						},  {
							x : 5,
							y : 125,
							xtype : 'label',
							text : '银牌客户打折:'
						}, {
							x : 60,
							y : 125,
							id : 'farSildiscount',
							xtype : 'textfield',
							allowBlank : false,
							anchor : '100%'
						}
						,  {
							x : 5,
							y : 155,
							xtype : 'label',
							text : '铜牌客户积分:'
						}, {
							x : 60,
							y : 155,
							id : 'farBroscore',
							xtype : 'textfield',
							allowBlank : false,
							anchor : '100%'
						}
						,  {
							x : 5,
							y : 185,
							xtype : 'label',
							text : '铜牌客户打折:'
						}, {
							x : 60,
							y : 185,
							id : 'farBrodiscount',
							xtype : 'textfield',
							allowBlank : false,
							anchor : '100%'
						}],
				buttons : [{
							text : '<font size=2>确定修改</font>',
							handler : function() {
								update_account();
							}
						}, {
							text : '<font size=2>取消</font>',
							handler : function() {
								window.hide();
							}
						}]
			});
			// ******************************************增加票价信息*****************************************
var farComcode = new Ext.form.ComboBox({
			name : 'farComcode',
			fieldLabel : '航空公司代号',
			//itemCls : 'float-left',
			//clearCls : 'allow-float',
			allowBlank : false,
			store : com_store,
			mode : 'remote',
			triggerAction : 'all',
			valueField : 'value',
			displayField : 'text'
		});
var farGoldscore = new Ext.form.TextField({
			name : 'farGoldscore',
			fieldLabel : '金牌客户积分',
			allowBlank : false
		});
var farGolddiscount = new Ext.form.TextField({
			name : 'farGolddiscount',
			fieldLabel : '金牌客户打折',
			allowBlank : false
		});
var farSilscore = new Ext.form.TextField({
			name : 'farSilscore',
			fieldLabel : '银牌客户积分',
			allowBlank : false
		});
var farSildiscount = new Ext.form.TextField({
			name : 'farSildiscount',
			fieldLabel : '银牌客户打折',
			allowBlank : false
		});
var farBroscore = new Ext.form.TextField({
			name : 'farBroscore',
			fieldLabel : '铜牌客户积分',
			allowBlank : false
		});
var farBrodiscount = new Ext.form.TextField({
			name : 'farBrodiscount',
			fieldLabel : '铜牌客户打折',
			allowBlank : false
		});		
var form1 = new Ext.FormPanel({
			labelSeparator : ':',
			labelWidth : 80,// 标签宽度
			frame : true,
			autoheight : true,
			items : [farComcode, farGoldscore, farGolddiscount,farSilscore,farSildiscount,farBroscore,
					farBrodiscount]
		});
var win = new Ext.Window({
			title : '<font size=2>增加票价信息</font>',
			modal : true,
			width : '30%',
			autoheight : true,
			resizable : false,
			plain : true,
			iconCls : 'information',
			bodyStyle : 'padding:5px;',
			buttonAlign : 'center',
			closeAction : 'hide',
			items : form1,
			listeners : {
				"show" : function() {
				}
			},
			buttons : [{
						text : '<font size=2>增加</font>',
						iconCls : 'add',
						handler : function() {
							if (!form1.getForm().isValid())
								return;
							
							form1.getForm().submit({
										url : 'faretype?type=add',
										success : function() {
											JsHelper.OK("操作成功!", function() {
														store.reload();
													});
										},
										failure : function() {
											JsHelper
													.ShowError("操作失败(航空公司代号可能重复)!");
										}
									})
						}
					}, {
						text : '<font size=2>清空</font>',
						iconCls : 'arrow_rotate_anticlockwise',
						handler : function() {
							form1.getForm().reset();
						}
					}]
		});
			
	// ******************************数据源*********************************
	store = new Ext.data.JsonStore({
				root : 'root',
				totalProperty : 'totalCount',
				url : 'faretype?type=queryall',
				fields : [{
							name:'farComcode',
						},{
							name : 'farGoldscore'
						}, {
							name : 'farGolddiscount'
						}, {
							name : 'farSilscore'
						}, {
							name : 'farSildiscount'
						}, {
							name : 'farBroscore'
						},{
						    name :'farBrodiscount'
						}]
			});

	// ************************部分搜索*****************************************
	function search_account(a, url) {
		Ext.Ajax.request({ // ajax响应
			url : "faretype?type=query",
			method : 'post',
			params : {
				farComcode : Ext.getCmp('farComcode').getValue()
				
			},
			success : function(response, opts) {
				//一次性分页，效率低
				var obj = Ext.decode(response.responseText);// obj储存响应的数据
				store.proxy = new Ext.data.PagingMemoryProxy(obj), // PagingMemoryProxy()
				store.load({
							params : {
								start : 0,
								limit : 10
							}
						});
				//当取值为空的时候则执行后台分页
				store.proxy = new Ext.data.HttpProxy({
							url : 'faretype?type=queryall'
						});
				JsHelper.OK("操作成功!", function() {
						});
			},
			failure : function() {
				JsHelper.ShowError("操作失败(没有此票价信息！)!");
			}

		});
	}
//*************************删除票价信息***************************************
	function showResult(btn) {
      if (btn == 'yes') {
        Ext.Msg.wait("请等候", "删除中", "操作进行中......");
        var row = Ext.getCmp("grid").getSelectionModel().getSelections();

        var conn = new Ext.data.Connection();
        conn.request({
            url: "faretype?type=del", //请注意引用的路径
            params: { farComcode: row[0].get("farComcode") },
            method: 'post',
            scope: this,
            callback: function(options, success, response) {
                if (success) {
                    Ext.MessageBox.alert("提示", "所选记录成功删除！");
                    var row = Ext.getCmp("grid").getSelectionModel().getSelections();
                    store.remove(row);
                }
                else
                { Ext.MessageBox.alert("提示", "所选记录删除失败！"); }
            }
        })
    }
}; 
	// **************************界面显示****************************************
	grid = new Ext.grid.GridPanel({
		frame : true,
		stripeRows : true,// 斑马线
		store : store,
		id : 'grid',
		trackMouseOver : true,
		loadMask : true,
		viewConfig : {
			forceFit : true
		},
		tbar : new Ext.Toolbar(['-', 
				 {
					text : '<font size=2>更新票价</font>',
					iconCls : 'application_edit',
					handler : function() {
						var s = grid.getSelectionModel().getSelections();
						if (s.length == 0) {// **************************************判断有没有选中行
							Ext.Msg.alert('提示', '你还没有选择要操作的记录!');
						}  else if (s.length > 1) {
                        Ext.Msg.alert('提示', '不能更新多个操作记录!');}
                        else {
							row = grid.getSelectionModel().getSelected();// *********记录选中行，row为数组
							window.show();
							Ext.getCmp("farComcode").setValue(row
									.get("farComcode"));// *****window里面赋值
							Ext.getCmp('farGoldscore').setValue(row.get('farGoldscore'));
							Ext.getCmp('farGolddiscount').setValue(row.get('farGolddiscount'));
							Ext.getCmp("farSilscore").setValue(row.get("farSilscore"));							
							Ext.getCmp('farSildiscount').setValue(row.get('farSildiscount'));	
							Ext.getCmp('farBroscore').setValue(row.get('farBroscore'));	
							Ext.getCmp('farBrodiscount').setValue(row.get('farBrodiscount'));							
						};
					}
				}, '-', {
						text : '<font size=2>增加信息</font>',
						iconCls : 'add',
						handler : function() {
							win.show()
						}
					},'-', {
					text : '<font size=2>删除用户</font>',
					iconCls : 'delete',
					handler : function() {
					    var s = grid.getSelectionModel().getSelections();					
						if (grid.getSelectionModel().getSelections().length == 0) {
							JsHelper.ShowError("请选择一行!");
							return;
						}else if (s.length > 1) {
                        Ext.Msg.alert('提示', '不能删除多个操作记录!');
                    }
 else {
                        Ext.MessageBox.confirm('提示', '您确认要删除当前记录吗?', showResult);
                    };
					
						/*Ext.Ajax.request({
									url : 'membership?type=delete',
									params : {
										 customer_id: row.get('customer_id')
									},
									success : function() {
										JsHelper.OK("已删除!", function() {
													store.reload();
												});
									},
									failure : function() {
										JsHelper.ShowError("操作失败!");
									}
								})*/
					}
				}, '-', {
					xtype : 'textfield',
					id : 'farComcode',
					width : 100
				}, {
					text : '<font size=2>按照航空公司代号查找</font>',// *******************************调用search_style搜索用户
					iconCls : 'search',
					handler : function() {
						search_account(Ext.getCmp('farComcode').getValue(), '',
								"faretype?type=query");
					}
				}

		]),
		columns : [
				new Ext.grid.RowNumberer(),// 行号
				{
					header : '<font size=2>航空公司代号</font>',
					dataIndex : 'farComcode',
					sortable : true
				}, {
					header : '<font size=2>金牌客户积分</font>',
					dataIndex : 'farGoldscore',
					sortable : true
				}, {
					header : '<font size=2>金牌客户打折</font>',
					dataIndex : 'farGolddiscount',
					sortable : true
				}, {
					header : '<font size=2>银牌客户积分</font>',
					dataIndex : 'farSilscore',
					sortable : true
				}, {
					header : '<font size=2>银牌客户打折</font>',
					dataIndex : 'farSildiscount',
					sortable : true				
				}
				, {
					header : '<font size=2>铜牌客户打折</font>',
					dataIndex : 'farBroscore',
					sortable : true				
				}
				, {
					header : '<font size=2>铜牌客户打折</font>',
					dataIndex : 'farBrodiscount',
					sortable : true				
				}],
		bbar : new Ext.PagingToolbar({// 分页
			pageSize : 10,
			store : store,
			displayInfo : true,
			displayMsg : '<font size=2>第 {0} 条到 {1} 条，一共 {2} 条记录</font>',
			emptyMsg : "没有记录"
		})
	})
	store.load({
				params : {
					start : 0,
					limit : 10
				}
			});// 10条记录
	new Ext.Viewport({
				layout : 'border',
				items : [{
							region : 'center',
							margins : '0 0 0 0',
							layout : 'fit',
							items : [grid]
						}]
			})
})
