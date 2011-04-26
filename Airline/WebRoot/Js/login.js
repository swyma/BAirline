Ext.onReady(function() {
	//初始化
		Ext.QuickTips.init();
		//错误提示
		Ext.form.Field.prototype.msgTarget = 'side';

		var loginForm = new Ext.FormPanel( {
			labelAlign : 'top',
			frame : true,
			monitorValid : true,// 把有formBind:true的按钮和验证绑定
			id : 'login',
			bodyStyle : 'padding:5px 5px 0',
			width : 400,
			items : [ {
				layout : 'table', // 把整个空间划分成两列
				items : [ {
					width : 180,
					html : '<img src="image/airline.jpg" width=165 />'
				}, {
					width : 180,
					layout : 'form', // 右边列再分成上下两行
					items : [ {
						xtype : 'textfield',
						name : 'manAccount',
						fieldLabel : '帐号',
						width : 140,
						allowBlank : false,
						blankText : '帐号不能为空'
					}, {
						xtype : 'textfield',
						name : 'manPwd',
						fieldLabel : '密码',
						allowBlank : false,
						width : 140,
						blankText : '密码不能为空',
						inputType : 'password'
					}, {
						items : [ {
							layout : 'table', // 把整个空间划分成两列
							items : [ {
								layout : 'form', // 右边列再分成上下两行
								width : 85,
								items : [ {
									name : 'Code',
									xtype : 'textfield',
									fieldLabel : '验证码',
									regex : /^[0-9]{4}$/,
									width : 60,
									allowBlank : false,
									blankText : '验证码不能为空!'
								} ]
							}, {
								//生成图片（验证码）
									html : '<img id="photo" src="image.jsp"/>'
								} ]
						} ]
					} ]
				} ]

			} ],
			buttons : [ {
				text : '登陆',
				handler : function() {
					/*if (loginForm.form.isValid()) { 验证合法后使用加载进度条					Ext.MessageBox.show( {
						title : '请稍等',
						msg : '正在登陆...',
						progressText : '',
						width : 300,
						progress : true,
						closable : false,
						animEl : 'loading'
					});
					 控制进度速度				var f = function(v) {
					return function() {
						var i = v / 11;
						Ext.MessageBox.updateProgress(i, '');
					};
				};

				for ( var i = 1; i < 12; i++) {
					setTimeout(f(i), i * 150);
				}

			}*/

			//利用ajax提交
			/*Ext.Ajax.request({
						url : 'Manager?type=login',
						params : {
							manAccount : loginForm.getForm()
									.findField('manAccount').getValue(),
							manPwd : loginForm.getForm()
									.findField('manPwd').getValue()
							Code: loginForm.getForm()
									.findField('Code').getValue()
							rand:Ext.getCmp("photo").getValue()
						},*/
			//利用submit()进行数据交互
			loginForm.getForm().submit( {
				url : 'Manager?type=login',
				success : function() {
					JsHelper.OK("登录成功!", function() {
						window.location = 'HomePage.jsp';
					});
				},
				failure : function() {
					JsHelper.OK("登录失败，请核对数据!", function() {
						loginForm.form.reset();
					});
				}
			})

		}
			}, {
				text : '取消',
				handler : function() {
					loginForm.form.reset();
				}// 重置表单
			} ]
		});
		// 定义窗体

		// 构建一个窗口面板容器
		login_win = new Ext.Window( {
			title : '航空后台登录',
			width : 400,
			resizable : false,
			autoHeight : true,
			layout : 'column',
			//	collapsible : true,
			closable : false,
			draggable : false,
			defaults : {
				border : false
			},
			items : {
				columnWidth : 1,
				items : loginForm
			}
		});
		login_win.show();
	});
/*// 更换验证码
 function changeImg(obj) {
 obj.src = "image.jsp";
 }*/
