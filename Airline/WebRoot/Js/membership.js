Ext.onReady(function() {

	var row;// 选中行	
	// ******************************************更新留言板信息*************************
	function update_account() {
		var value;
		Ext.Ajax.request({
					url : 'membership?type=update',
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
						customer_id : Ext.getCmp('customer_id').getValue(),
						customer_name : Ext.getCmp('customer_name').getValue(),
						comment_content: Ext.getCmp('comment_content').getValue(),
						comment_time: Ext.getCmp('comment_time').getValue(),
						comment_IP : Ext.getCmp('customer_IP').getValue()				
					}
				});
	}

	// ******************************************修改留言板信息*****************************************
	var window = new Ext.Window({
				title : '<font size=2>留言板信息修改</font>',
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
							text : '客户身份证：'
						}, {
							x : 60,
							y : 5,
							xtype : 'textfield',
							readOnly : 'true',
							id: 'customer_id',
							allowBlank : false,
							anchor : '100%'
						}, {
							x : 5,
							y : 35,
							xtype : 'label',
							text : '客户姓名：'
						}, {
							x : 60,
							y : 35,
							xtype : 'textfield',
							id : 'customer_name',
							allowBlank : false,
							anchor : '100%'
						}, {
							x : 5,
							y : 65,
							xtype : 'label',
							text : '评论内容：'
						}, {
							x : 60,
							y : 65,
							xtype : 'textfield',
						    id: 'comment_content',
							allowBlank : false,
							anchor : '100%'
						}, {
							x : 5,
							y : 95,
							xtype : 'label',
							text : '评论时间：'
						}, {
							x : 60,
							y : 95,
							id: 'comment_time',
							xtype : 'textfield',
							allowBlank : false,
							anchor : '100%'
						},  {
							x : 5,
							y : 155,
							xtype : 'label',
							text : '评论IP:'
						}, {
							x : 60,
							y : 155,
							id : 'customer_IP',
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
				url : 'membership?type=queryall',
				fields : [{
							name:'customer_autoid',
						},{
							name : 'customer_id'
						}, {
							name : 'customer_name'
						}, {
							name : 'comment_content'
						}, {
							name : 'comment_time'
						}, {
							name : 'customer_IP'
						}]
			});

	// ************************部分搜索*****************************************
	function search_account(a, url) {
		Ext.Ajax.request({ // ajax响应
			url : "membership?type=query",
			method : 'post',
			params : {
				customer_id : Ext.getCmp('customer_id').getValue()
				//manId : Ext.getCmp('search_account').getValue()
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
							url : 'membership?type=queryall'
						});
				JsHelper.OK("操作成功!", function() {
						});
			},
			failure : function() {
				JsHelper.ShowError("操作失败(用户帐号可能重复)!");
			}

		});
	}
//*************************删除人员信息***************************************
	function showResult(btn) {
      if (btn == 'yes') {
        Ext.Msg.wait("请等候", "删除中", "操作进行中......");
        var row = Ext.getCmp("grid").getSelectionModel().getSelections();
        
       
        var conn = new Ext.data.Connection();
        conn.request({
            url: "membership?type=delete", //请注意引用的路径
            params: { customer_id: row[0].get("customer_id") },
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
					text : '<font size=2>更新用户</font>',
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
							Ext.getCmp("customer_id").setValue(row
									.get("customer_id"));// *****window里面赋值
							Ext.getCmp('customer_name').setValue(row.get('customer_name'));
							Ext.getCmp('comment_content').setValue(row.get('comment_content'));
							Ext.getCmp("comment_time").setValue(row.get("comment_time"));							
							Ext.getCmp('customer_IP').setValue(row
									.get('customer_IP'));							
						};
					}
				}, '-', {
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
					id : 'customer_id',
					width : 100
				}, {
					text : '<font size=2>按帐号或身份证号搜索</font>',// *******************************调用search_style搜索用户
					iconCls : 'search',
					handler : function() {
						search_account(Ext.getCmp('customer_id').getValue(), '',
								'membership?type=query');
					}
				}

		]),
		columns : [
				new Ext.grid.RowNumberer(),// 行号
				{
					header : '<font size=2>客户身份证</font>',
					dataIndex : 'customer_id',
					sortable : true
				}, {
					header : '<font size=2>客户姓名</font>',
					dataIndex : 'customer_name',
					sortable : true
				}, {
					header : '<font size=2>评论内容</font>',
					dataIndex : 'comment_content',
					sortable : true
				}, {
					header : '<font size=2>评论时间</font>',
					dataIndex : 'comment_time',
					sortable : true
				}, {
					header : '<font size=2>评论IP</font>',
					dataIndex : 'customer_IP',
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
