<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!-- 编辑人：杨海亮 -->
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<meta http-equiv="content-type" content="text/html; charset=UTF-8">
		<title>A704航天订票系统后台管理</title>

		<link rel="stylesheet" type="text/css"
			href="ExtJs/resources/css/ext-all.css" />
					<link rel="stylesheet" type="text/css"
			href="ExtJs/resources/css/xtheme-gray.css" />
		<script type="text/javascript" src="ExtJs/adapter/ext/ext-base.js"></script>
		<script type="text/javascript" src="ExtJs/ext-all.js"></script>
        <link rel="stylesheet" href="Css/homepage.css" type="text/css"></link>
        
	<script type="text/javascript">
         var tabPanel = new Ext.TabPanel( {
			region : 'center',
			enableTabScroll : true,
			deferredRender : false,
			margins:'5 5 5 5',
			activeTab : 0,
			items : [ {
				title : '首页',
                iconCls:'houseIcon',
				html : '<table   align=center><tr>   <td   width=100   align=center> <img   src= "image/1.jpg"" <td> </tr></table> '
			} ]
		});
		
		var addPanel = function(node, event) {
			var n;
			n = tabPanel.getComponent(node.id);
			if(n) {
				tabPanel.setActiveTab(n);
				return;
			}
			n = tabPanel.add( {
				id : node.id,
				title : node.text,
				html : '<iframe width=100% frameborder=0 height=100% src=' + node.id + '.jsp />',
				closable : 'true'
			});
			tabPanel.setActiveTab(n);
		}
		    
	
	Ext.onReady( function() {
          Ext.QuickTips.init();//初始化
          
		//**************************************
        //飞机机型管理
        var root_airtype=new Ext.tree.TreeNode({
           text:'飞机机型信息管理',
         });
         
          var node_airtype=new Ext.tree.TreeNode({
               id:'airtype',
               text:'飞机机型信息',
               cls:'tree',
               listeners:{
               click:addPanel
               }
          });
      
          root_airtype.appendChild(node_airtype);
          
         var menu_airtype=new Ext.tree.TreePanel({ 
                   border:false,
                   root:root_airtype,
                   rootVisible:false, //是否显示根节点
                   hlColor:'C3DFF3', //设置节点的高亮颜色
                  });
	
		var item1 = new Ext.Panel( {
			title : '飞机机型信息管理',
			expanded:true,
			cls : 'empty',
			iconCls:'user',
			items : menu_airtype
		});
		
		//**************************************

       //航班信息管理
        var root_flightinformation=new Ext.tree.TreeNode({
           text:'航班信息管理',
         })
         
         var node_flightinformation=new Ext.tree.TreeNode({
             id:'flightinformation',
             text:'航班信息',
              cls:'tree',
             listeners:{
               click:addPanel
             } 
         });
          root_flightinformation.appendChild(node_flightinformation);

          var menu_flightinformation=new Ext.tree.TreePanel({ 
                   border:false,
                   root:root_flightinformation,
                   rootVisible:false, //是否显示根节点
                   hlColor:'C3DFF3', //设置节点的高亮颜色
                  });
		var item2 = new Ext.Panel( {
			title : '航班信息管理',	
			cls : 'empty',
			iconCls:'user',
			items:menu_flightinformation
		});
		
		//**************************************
	  // 人员用户管理
             var root_user=new Ext.tree.TreeNode({
                  text:'人员用户管理',
                  expanded:true   //默认展开根节点 
               });
              var node_manager=new Ext.tree.TreeNode({
                 id:'manager',
                 text:'管理员信息',
                 iconCls:'user',
                  cls:'tree',
                 listeners:{
                   click:addPanel
                  }
               });
             
                 root_user.appendChild(node_manager);
                
          var menu_user=new Ext.tree.TreePanel({ 
                   border:false,
                   root:root_user,
                   rootVisible:false, //是否显示根节点
                   hlColor:'C3DFF3', //设置节点的高亮颜色
                  });
        
		var item3 = new Ext.Panel( {
			title : '人员用户管理',
			iconCls:'user',
			items:menu_user
		});
		
		//**************************************
          // 客户订票管理
        var root_book_ticket=new Ext.tree.TreeNode({
               text:'客户订票管理',
         })
                  
        var node_bookinformation_3=new Ext.tree.TreeNode({
               id:'bookinformation_3',
               text:'客户订票信息',
               iconCls:'user',
               cls:'tree',
               listeners:{
               click:addPanel
               }
          });
          
         root_book_ticket.appendChild(node_bookinformation_3);
         
          var menu_book_ticket=new Ext.tree.TreePanel({ 
                   border:false,
                   root:root_book_ticket,
                   rootVisible:false, //是否显示根节点
                   hlColor:'C3DFF3', //设置节点的高亮颜色
                  });
		var item4 = new Ext.Panel( {
			title : '客户订票管理',
			iconCls:'user',
			cls : 'empty',
			items:menu_book_ticket
		});
		//**************************************
		//报表打印管理
        var root_ticketsell_analysis=new Ext.tree.TreeNode({
           title : '报表打印管理',
         });
         
          var node_ticketsell_analysis_char=new Ext.tree.TreeNode({
             id:'Statistics',
               text:'机票销售分析图',
                cls:'tree',
               listeners:{
               click:addPanel
               }
          });

           root_ticketsell_analysis.appendChild(node_ticketsell_analysis_char);
            
           var menu_ticketsell_analysis=new Ext.tree.TreePanel({ 
                   border:false,
                   root:root_ticketsell_analysis,
                   rootVisible:false, //是否显示根节点
                   hlColor:'C3DFF3', //设置节点的高亮颜色
                  });
		var item5 = new Ext.Panel( {
			title : '报表打印管理',
			items:menu_ticketsell_analysis,
			iconCls:'user',
			cls : 'empty'
		});
		
		//**************************************
		//航空公司信息管理
        var root_flightcompany=new Ext.tree.TreeNode({
           title : '航空公司信息管理',
         });
         
         var node_flightcompany=new Ext.tree.TreeNode({
               id:'flightcompany',
               text:'航空公司信息',
                cls:'tree',
               listeners:{
               click:addPanel
               }
          });
          
     
           root_flightcompany.appendChild(node_flightcompany);
           
           var menu_flightcompany=new Ext.tree.TreePanel({ 
                   border:false,
                   root:root_flightcompany,
                   rootVisible:false, //是否显示根节点
                   hlColor:'C3DFF3', //设置节点的高亮颜色
                  });
		var item6 = new Ext.Panel( {
			title : '航空公司信息管理',
			items:menu_flightcompany,
			iconCls:'user',
			border:false,
			cls : 'empty'
		});
	
	//**************************************	
 //客户积分信息管理
        var root_faretype=new Ext.tree.TreeNode({
           text:'客户积分信息管理',
         });
         
         var node_faretype=new Ext.tree.TreeNode({
               id:'faretype',
               text:'客户积分信息',
               cls:'tree',
               listeners:{
               click:addPanel
               }
          });
          
      root_faretype.appendChild(node_faretype);
          
       var menu_faretype=new Ext.tree.TreePanel({ 
                   border:false,
                   root:root_faretype,
                   rootVisible:false, //是否显示根节点
                   hlColor:'C3DFF3', //设置节点的高亮颜色
                  });
	
		var item7 = new Ext.Panel( {
			title : '客户积分信息管理',
			expanded:true,
			iconCls:'user',
			cls : 'empty',
			items : menu_faretype
		});
		
		//**************************************
		 //酒店信息管理
        var root_hotelinformation=new Ext.tree.TreeNode({
           text:'酒店信息管理',
         });
         
         var node_hotelinformation=new Ext.tree.TreeNode({
               id:'hotelinformation',
               text:'酒店信息管理',
               cls:'tree',
               listeners:{
               click:addPanel
               }
          });
          
      root_hotelinformation.appendChild(node_hotelinformation);
          
       var menu_hotelinformation=new Ext.tree.TreePanel({ 
                   border:false,
                   root:root_hotelinformation,
                   rootVisible:false, //是否显示根节点
                   hlColor:'C3DFF3', //设置节点的高亮颜色
                  });
	
		var item8 = new Ext.Panel( {
			title : '酒店信息管理',
			expanded:true,
			iconCls:'user',
			cls : 'empty',
			items : menu_hotelinformation
		});
		
		//**************************************
		 //用户评论信息管理
        var root_membership=new Ext.tree.TreeNode({
           text:'用户评论信息管理',
         });
         
         var node_membership=new Ext.tree.TreeNode({
               id:'membership',
               text:'用户评论信息管理',
               cls:'tree',
               listeners:{
               click:addPanel
               }
          });
          
      root_membership.appendChild(node_membership);
          
       var menu_membership=new Ext.tree.TreePanel({ 
                   border:false,
                   root:root_membership,
                   rootVisible:false, //是否显示根节点
                   hlColor:'C3DFF3', //设置节点的高亮颜色
                  });
	
		var item9 = new Ext.Panel( {
			title : '用户评论信息管理',
			expanded:true,
			iconCls:'user',
			cls : 'empty',
			items : menu_membership
		});
		
		//**************************************
		
		var accordion = new Ext.Panel( {
		    title:'信息管理',
			region : 'west',
			margins : '5 0 5 5',
			split : true,
			width : 210,
		    autoScroll: true,
            enableTabScroll: true,
            collapsible: true,
			layout : 'accordion',
			layoutConfig:{
			  animate:true
			},
			 items : [ item6, item2, item3, item4, item5,item1,item7,item8,item9 ]
		});


		//个人信息修改
		var user_account=new Ext.form.TextField({
		         name:'useraccount',
		         id:'useraccount',
		         fieldLabel:'帐户',
		         allowBlank:false,
		         value:'<%=(String)session.getAttribute("name")%>',
		         readOnly:true
	         });
	    
	    var user_id=new Ext.form.NumberField({
	         name:'userid',
	         id:'userid',
	         fieldLabel:'身份证',
	         allowBlank:false
	     });

	     var user_phone=new Ext.form.NumberField({
		         name:'userphone',
		         id:'userphone',
		         fieldLabel:'联系方式',
		         allowBlank:false
	         });
	         
	         var user_email=new Ext.form.TextField({
		         name:'useremail',
		         id:'useremail',
		         fieldLabel:'邮箱地址',
		         allowBlank:false,
		         vtype:'email'
	         });
		  var form1=new Ext.FormPanel({
		         labelSeparator:':',
		         labelWidth:80,//标签宽度
		         frame:true,
		         autoheight:true,
		         bodyStyle:'padding:5 5 5 5',
		         items:[
		              user_account,
		              user_id,  
		      /******************    
		      { 
				xtype:'radio',
				id:'manSexb1',
				name:'manSex1',
				fieldLabel:'性别',
				boxLabel:'男',
				checked:true,
				inputValue:'男'				
			},{
				xtype:'radio',
				id:'manSexg1',
				name:'manSex1',
				boxLabel:'女',
				inputValue:'女'
			},*************************/
		         user_phone,
		         user_email,		         
		         ]
	         });
	         
		     var win1= new Ext.Window({
    			title: '<font size=2>个人信息修改</font>',
			    modal:true,
			    width: '30%',
			    autoheight: true,
			    resizable: false,
			    plain: true,
			    iconCls: 'user_edit',
			    bodyStyle: 'padding:5px;',
			    buttonAlign: 'center',
			    closeAction: 'hide',
			    items: form1,
			    listeners: {
			        "show": function() {
			        }
			    },
			    buttons: [{
			         text: '<font size=2>确定</font>',
			         iconCls: 'page_save',
			         handler:function(){
			             	Ext.Ajax.request({
					            url : 'Bookinformation?type=individual_update',
				            	method : "post",
					            success : function() {
					             Ext.Msg.alert("提示", "修改成功!");
					              },
					            failure : function() {Ext.MessageBox.alert("提示", "修改失败!");},
				    	params : {
								useraccount : Ext.getCmp('useraccount').getValue(),
								userid : Ext.getCmp('userid').getValue(),
								userphone : Ext.getCmp('userphone').getValue(),
								useremail : Ext.getCmp('useremail').getValue()
					}
				});
			         }
			    }, {
			        text: '<font size=2>清空</font>',
			        iconCls: 'arrow_redo',
			        handler: function() {
			        	form1.getForm().reset();
			        }
			}]
		    });
        //密码修改
        var user_name=new Ext.form.TextField({
             name:'username',
             id:'username',
             allowBlank:false,
             fieldLabel:'帐户',
             value:'<%=(String)session.getAttribute("name")%>',
		     readOnly:true
         }); 
         
          var admin_newpassword=new Ext.form.TextField({
            name:'adminnewpassword',
            id:'adminnewpassword',
            inputType : 'password',
            allowBlank:false,
            fieldLabel:'新密码',
          });
           var admin_confirmpassword=new Ext.form.TextField({
            name:'adminconfirmpassword',
            id:'adminconfirmpassword',
            allowBlank:false,
            inputType : 'password',
            fieldLabel:'确认密码',
          });
          var form2=new Ext.FormPanel({
		         labelSeparator:':',
		         labelWidth:80,//标签宽度
		         frame:true,
		         autoheight:true,
		         items:[user_name,admin_newpassword,admin_confirmpassword]
	         });
	         
		      var win2= new Ext.Window({
    			title: '<font size=2>修改密码</font>',
			    modal:true,
			    width: '30%',
			    autoheight: true,
			    resizable: false,
			    plain: true,
			    iconCls: 'key',
			    bodyStyle: 'padding:5px;',
			    buttonAlign: 'center',
			    closeAction: 'hide',
			    items: form2,
			    listeners: {
			        "show": function() {
			        }
			    },
			    buttons: [{
			        text: '<font size=2>确定</font>',
			        iconCls: 'page_save',
			        handler:function(){
			            if(Ext.getCmp('adminnewpassword').getValue()==Ext.getCmp('adminconfirmpassword').getValue()){			
			             	Ext.Ajax.request({
					            url : 'Bookinformation?type=password_update',
				            	method : "post",
					            success : function() {
					             Ext.Msg.alert("提示", "修改成功!");
					              },
					            failure : function() {Ext.MessageBox.alert("提示", "密码修改失败!");},
				    	     params : {
								username : Ext.getCmp('username').getValue(),
								adminnewpassword : Ext.getCmp('adminnewpassword').getValue()								
					            }
			        })
			      }else{
			         Ext.MessageBox.alert("提示", "密码修改失败! 两次输入的密码不一致!");
			       }
			    }
			    }, {
			        text: '<font size=2>清空</font>',
			        iconCls: 'arrow_redo',
			        handler: function() {
			        	form2.getForm().reset();
			        }
			}
			]
		    });
          
        var headPanel=new Ext.Panel({ 
                   // title:'航空订票系统ExtJs版',
                    collapsible:false,
                  //  html:'<br><center><font size=4>航空订票系统</font></center>',
                    region:'north',
                    autoheight:true,
                         //顶部操作栏
		            tbar:new Ext.Toolbar([
		              {
		              iconCls:'houseIcon', 
		              text:'<font size=2 color=blue>2A704航空订票系统后台管理</font>',
		                enable:false
		               },
			         '->','-',
			         {
				         text:'<font size=2 >个人信息修改</font>',
				         iconCls:'user_edit',
				         handler:function(){win1.show()}
			         },
			         '-',
			         {
			           text:'<font size=2>修改密码</font>',
			           iconCls:'key',
			           handler:function(){ win2.show()}
			         },
			         '-',
			         {
				         text:'<font size=2>退出</font>',
				         iconCls:'door_out',
				         handler:function(){
				            Ext.Ajax.request({
							  url : 'Manager?type=doorout',
							  params : {
								doorout : "doorout"
							   },
							  method:'post',
							  success: function(form, action) {
									window.location = 'login.jsp';
								},
								 failure: function(form, action) {}	
                                             })
				       }
			         },
			         '-' 
			    ]),
          });
          
		var viewport = new Ext.Viewport({
			layout : 'border',
			items : [ accordion, tabPanel,headPanel ]
		});

	});
</script>
	</head>
	<body>
	    <%
			if (session.getAttribute("name") == null) {
				out.println("<script>window.location.href='login.jsp';</script>");
			}
		%>
	</body>
</html>
