Ext.onReady(function() {

	var row;// 选中行
	// ******************************************增加用户*************************
	function add_account() {
		var value;
		if (Ext.getCmp('manSexb1').getValue())
			value = '男';
		else
			value = '女';
		Ext.Ajax.request({
					url : 'Manager?type=add',
					method : "post",
					success : function(response, opts) {
						if (response.responseText) {
							store.reload();
							JsHelper.OK("添加成功!");
						} else {
							JsHelper.ShowError("帐户已存在、或密码不对应、不允许空白,操作失败!");
						}
					},
					failure : function() {
						JsHelper.ShowError("发送到后台失败，请重新检查发送是否正常!")
					},
					params : {
						manAccount : Ext.getCmp('manAccount1').getValue(),
						manPwd : Ext.getCmp('manPwd1').getValue(),
						manRepwd : Ext.getCmp('manRepwd1').getValue(),
						manId : Ext.getCmp('manId1').getValue(),
						manSex : value,
						manTelnumber : Ext.getCmp('manTelnumber1').getValue(),
						manEmail : Ext.getCmp('manEmail1').getValue()
					}
				});
	}

	// ******************************************增加人员信息*****************************************
	var window1 = new Ext.Window({
				title : '<font size=2>用户添加</font>',
				width : '20%',
				height : 285,
				layout : 'absolute',
				plain : true,
				modal : true,
				closeAction : 'hide',
				id : 'window1',
				bodyStyle : 'padding:5px;',
				buttonAlign : 'center',
				items : [{
							x : 5,
							y : 5,
							xtype : 'label',
							text : '用户帐号：'
						}, {
							x : 60,
							y : 5,
							xtype : 'textfield',
							id : 'manAccount1',
							allowBlank : false,
							anchor : '100%'
						}, {
							x : 5,
							y : 35,
							xtype : 'label',
							text : '用户密码：'
						}, {
							x : 60,
							y : 35,
							xtype : 'textfield',
							id : 'manPwd1',
							inputType : 'password',
							allowBlank : false,
							anchor : '100%'
						}, {
							x : 5,
							y : 65,
							xtype : 'label',
							text : '密码确认：'
						}, {
							x : 60,
							y : 65,
							xtype : 'textfield',
							id : 'manRepwd1',
							inputType : 'password',
							allowBlank : false,
							anchor : '100%'
						}, {
							x : 5,
							y : 95,
							xtype : 'label',
							text : '身份证号：'
						}, {
							x : 60,
							y : 95,
							id : 'manId1',
							xtype : 'textfield',
							regex : /\d/,
							allowBlank : false,
							anchor : '100%'
						}, {
							x : 5,
							y : 125,
							xtype : 'label',
							text : '性别：'
						}, {
							x : 60,
							y : 125,
							xtype : 'radio',
							id : 'manSexb1',
							name : 'manSex1',
							boxLabel : '男',
							checked : true,
							inputValue : '男' //
						}, {
							x : 155,
							y : 125,
							xtype : 'radio',
							id : 'manSexg1',
							name : 'manSex1',
							boxLabel : '女',
							inputValue : '女' //
						}, {
							x : 5,
							y : 155,
							xtype : 'label',
							text : '电话号码：'
						}, {
							x : 60,
							y : 155,
							id : 'manTelnumber1',
							xtype : 'textfield',
							allowBlank : false,
							anchor : '100%'
						}, {
							x : 5,
							y : 185,
							xtype : 'label',
							text : '邮箱：'
						}, {
							x : 60,
							y : 185,
							xtype : 'textfield',
							id : 'manEmail1',
							inputType : 'textfield',
							allowBlank : false,
							anchor : '100%'
						}],
				buttons : [{
							text : '<font size=2>确定添加</font>',
							handler : function() {
								add_account();
							}
						}, {
							text : '<font size=2>取消</font>',
							handler : function() {
								window1.hide();
							}
						}]
			});
	// ******************************************更新人员信息*************************
	function update_account() {
		var value;
		if (Ext.getCmp('manSexb').getValue())
			value = '男';
		else
			value = '女';
		Ext.Ajax.request({
					url : 'Manager?type=update',
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
						manAccount : Ext.getCmp('manAccount').getValue(),
						manPwd : Ext.getCmp('manPwd').getValue(),
						manRepwd : Ext.getCmp('manRepwd').getValue(),
						manId : Ext.getCmp('manId').getValue(),
						manSex : value,
						manTelnumber : Ext.getCmp('manTelnumber').getValue(),
						manEmail : Ext.getCmp('manEmail').getValue()
					}
				});
	}

	// ******************************************修改人员信息*****************************************
	var window = new Ext.Window({
				title : '<font size=2>用户信息修改</font>',
				width : '20%',
				height : 285,
				layout : 'absolute',
				plain : true,
				modal : true,
				closeAction : 'hide',
				id : 'window',
				bodyStyle : 'padding:5px;',
				buttonAlign : 'center',
				items : [{
							x : 5,
							y : 5,
							xtype : 'label',
							text : '用户账户：'
						}, {
							x : 60,
							y : 5,
							xtype : 'textfield',
							readOnly : 'true',
							id : 'manAccount',
							allowBlank : false,
							anchor : '100%'
						}, {
							x : 5,
							y : 35,
							xtype : 'label',
							text : '用户密码：'
						}, {
							x : 60,
							y : 35,
							xtype : 'textfield',
							id : 'manPwd',
							inputType : 'password',
							allowBlank : false,
							anchor : '100%'
						}, {
							x : 5,
							y : 65,
							xtype : 'label',
							text : '密码确认：'
						}, {
							x : 60,
							y : 65,
							xtype : 'textfield',
							id : 'manRepwd',
							inputType : 'password',
							allowBlank : false,
							anchor : '100%'
						}, {
							x : 5,
							y : 95,
							xtype : 'label',
							text : '身份证号：'
						}, {
							x : 60,
							y : 95,
							id : 'manId',
							xtype : 'textfield',
							regex : /\d/,
							allowBlank : false,
							anchor : '100%'
						}, {
							x : 5,
							y : 125,
							xtype : 'label',
							text : '性别：'
						}, {
							x : 60,
							y : 125,
							xtype : 'radio',
							id : 'manSexb',
							name : 'manSex',
							boxLabel : '男',
							inputValue : '男'
						}, {
							x : 135,
							y : 125,
							xtype : 'radio',
							id : 'manSexg',
							name : 'manSex',
							boxLabel : '女',
							inputValue : '女'
						}, {
							x : 5,
							y : 155,
							xtype : 'label',
							text : '电话号码'
						}, {
							x : 60,
							y : 155,
							id : 'manTelnumber',
							xtype : 'textfield',
							allowBlank : false,
							anchor : '100%'
						}, {
							x : 5,
							y : 185,
							xtype : 'label',
							text : '邮箱：'
						}, {
							x : 60,
							y : 185,
							id : 'manEmail',
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
	// ******************************数据源*********************************
	store = new Ext.data.JsonStore({
				root : 'root',
				totalProperty : 'totalCount',
				url : 'Manager?type=queryall',
				fields : [{
							name : 'manAutoid'
						}, {
							name : 'manAccount'
						}, {
							name : 'manPwd'
						}, {
							name : 'manId'
						}, {
							name : 'manSex'
						}, {
							name : 'manTelnumber'
						}, {
							name : 'manEmail'
						}, {
							name : 'manRegister'
						}]
			});

	// ************************部分搜索*****************************************
	function search_account(a, url) {
		Ext.Ajax.request({ // ajax响应
			url : "Manager?type=query",
			method : 'post',
			params : {
				manAccount : Ext.getCmp('search_account').getValue(),
				manId : Ext.getCmp('search_account').getValue()
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
							url : 'Manager?type=queryall'
						});
				JsHelper.OK("操作成功!", function() {
						});
			},
			failure : function() {
				JsHelper.ShowError("操作失败(用户帐号可能重复)!");
			}

		});
	}

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
		tbar : new Ext.Toolbar(['-', {
					text : '<font size=2>添加用户</font>',
					iconCls : 'add',
					handler : function() {
						window1.show();
					}
				}, '-', {
					text : '<font size=2>更新用户</font>',
					iconCls : 'application_edit',
					handler : function() {
						var s = grid.getSelectionModel().getSelections();
						if (s.length == 0) {// **************************************判断有没有选中行
							Ext.Msg.alert('提示', '你还没有选择要操作的记录!');
						} else {
							row = grid.getSelectionModel().getSelected();// *********记录选中行，row为数组
							window.show();
							Ext.getCmp("manAccount").setValue(row
									.get("manAccount"));// *****window里面赋值
							Ext.getCmp('manPwd').setValue(row.get('manPwd'));
							Ext.getCmp('manRepwd').setValue(row.get('manPwd'));
							Ext.getCmp("manId").setValue(row.get("manId"));
							if (row.get('manSex') == '女')
								Ext.getCmp('manSexg').setValue(row
										.get('manSex'));
							else
								Ext.getCmp('manSexb').setValue(row
										.get('manSex'));
							Ext.getCmp('manTelnumber').setValue(row
									.get('manTelnumber'));
							Ext.getCmp('manEmail')
									.setValue(row.get('manEmail'));
						};
					}
				}, '-', {
					text : '<font size=2>删除用户</font>',
					iconCls : 'delete',
					handler : function() {
						if (grid.getSelectionModel().getSelections().length == 0) {
							JsHelper.ShowError("请选择一行!");
							return;
						}

						var row = grid.getSelectionModel().getSelected();
						Ext.Ajax.request({
									url : 'Manager?type=delete',
									params : {
										manAutoid : row.get('manAutoid')
									},
									success : function() {
										JsHelper.OK("已删除!", function() {
													store.reload();
												});
									},
									failure : function() {
										JsHelper.ShowError("操作失败!");
									}
								})
					}
				}, '-', {
					xtype : 'textfield',
					id : 'search_account',
					width : 100
				}, {
					text : '<font size=2>按帐号或身份证号搜索</font>',// *******************************调用search_style搜索用户
					iconCls : 'search',
					handler : function() {
						search_account(Ext.getCmp('manAccount').getValue(), '',
								'Manager?type=queryall');
					}
				}

		]),
		columns : [
				new Ext.grid.RowNumberer(),// 行号
				{
					header : '<font size=2>用户帐户</font>',
					dataIndex : 'manAccount',
					sortable : true
				}, {
					header : '<font size=2>身份证号</font>',
					dataIndex : 'manId',
					sortable : true
				}, {
					header : '<font size=2>用户性别</font>',
					dataIndex : 'manSex',
					sortable : true
				}, {
					header : '<font size=2>电话号码</font>',
					dataIndex : 'manTelnumber',
					sortable : true
				}, {
					header : '<font size=2>邮箱</font>',
					dataIndex : 'manEmail',
					sortable : true
				}, {
					header : '<font size=2>注册日期</font>',
					dataIndex : 'manRegister',
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
