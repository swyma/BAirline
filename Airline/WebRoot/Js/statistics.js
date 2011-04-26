Ext.chart.Chart.CHART_URL = 'ExtJs/resources/charts.swf';

Ext.onReady(function() {
			var store = new Ext.data.JsonStore({
						url : 'Statistics?type=querymonth',
						fields : [{
									name : 'name'
								}, {
									name : 'visits',
									type : 'int'
								}]
					});

			store.load();
			// extra extra simple
			var time = new Date();
			new Ext.Panel({
						title : time.getMonth() + 1
								+ '<font size=2>月销售量统计(本月)</font>',
						applyTo : 'container',
						width : '100%',
						height : 300,
						layout : 'fit',
						items : {
							xtype : 'linechart',
							store : store,
							xField : 'name',
							listeners : {
								itemclick : function(o) {
									var rec = store.getAt(o.index);
									Ext.example.msg('Item Selected',
											'You chose {0}.', rec.get('name'));
								}
							},
							series : [{// 列
								type : 'line', // 类型可以改变（线）
								displayName : '今天销售量',
								yField : 'visits',
								style : {
									color : 0x00BB00
								}
							}]

						}
					});
					
					var store1 = new Ext.data.JsonStore({
						url : 'Statistics?type=queryyear',
						fields : [{
									name : 'name'
								}, {
									name : 'visits',
									type : 'int'
								}]
					});			
					store1.load();
			new Ext.Panel({
						title : '<font size=2>年销售量统计</font>',
						applyTo : 'container1',
						width : '100%',
						height : 300,
						layout : 'fit',
						items : {
							xtype : 'linechart',
							store : store1,
							xField : 'name',
							listeners : {
								itemclick : function(o) {
									var rec = store.getAt(o.index);
									Ext.example.msg('Item Selected',
											'You chose {0}.', rec.get('name'));
								}
							},
							series : [{// 列
								type : 'line', // 类型可以改变（线）
								displayName : '今天销售量',
								yField : 'visits',
								style : {
									color : 0x00BB00
								}
							}]

						}
					});
		});