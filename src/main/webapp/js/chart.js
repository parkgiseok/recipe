window.onload = function() {
			var chart1 = new CanvasJS.Chart("chartContainer1", {
				width : 474,//in pixels
				height : 290,//in pixels
				title : {
					text : "분야별 랭킹"
				},
				animationEnabled : true,
				theme : "theme2",
				data : [ {
					type : "pie",
					indexLabelFontFamily : "Garamond",
					indexLabelFontSize : 15,
					startAngle : 90,
					indexLabelFontColor : "dimgrey",
					indexLabelLineColor : "darkgrey",
					toolTipContent : "{y} %",

					dataPoints : [ {
						y : 27.34,
						indexLabel : "한식"
					}, {
						y : 20.6,
						indexLabel : "일식"
					}, {
						y : 9.78,
						indexLabel : "중식"
					}, {
						y : 10.84,
						indexLabel : "분식"
					}, {
						y : 20.74,
						indexLabel : "디저트류"
					}, {
						y : 12.06,
						indexLabel : "양식"
					}

					]
				} ]
			});
			chart1.render();

			var chart2 = new CanvasJS.Chart("chartContainer2", {
				width : 474,//in pixels
				height : 290,//in pixels
				title : {
					text : "재료별 랭킹"
				},
				animationEnabled : true,
				theme : "theme2",
				data : [ {
					type : "pie",
					indexLabelFontFamily : "Garamond",
					indexLabelFontSize : 15,
					startAngle : 90,
					indexLabelFontColor : "dimgrey",
					indexLabelLineColor : "darkgrey",
					toolTipContent : "{y} %",

					dataPoints : [ {
						y : 17.34,
						indexLabel : "감자"
					}, {
						y : 18.6,
						indexLabel : "밀가루"
					}, {
						y : 1.78,
						indexLabel : "버섯"
					}, {
						y : 10.84,
						indexLabel : "닭"
					}, {
						y : 20.74,
						indexLabel : "소고기"
					}, {
						y : 12.06,
						indexLabel : "돼지고기"
					}

					]
				} ]
			});
			chart2.render();

			var chart3 = new CanvasJS.Chart("chartContainer3", {
				animationEnabled : true,
				width : 948,
				height : 300,
				title : {
					text : "순위 / 인기도"
				},
				data : [ {
					type : "spline", //change type to bar, line, area, pie, etc
					showInLegend : true,
					name : "순위",
					dataPoints : [ {
						x : 10,
						y : 51
					}, {
						x : 20,
						y : 45
					}, {
						x : 30,
						y : 50
					}, {
						x : 40,
						y : 62
					}, {
						x : 50,
						y : 95
					}, {
						x : 60,
						y : 66
					}, {
						x : 70,
						y : 24
					}, {
						x : 80,
						y : 32
					}, {
						x : 90,
						y : 16
					} ]
				}, {
					type : "spline",
					name : "인기도",
					showInLegend : true,
					dataPoints : [ {
						x : 10,
						y : 21
					}, {
						x : 20,
						y : 44
					}, {
						x : 30,
						y : 35
					}, {
						x : 40,
						y : 45
					}, {
						x : 50,
						y : 75
					}, {
						x : 60,
						y : 58
					}, {
						x : 70,
						y : 18
					}, {
						x : 80,
						y : 30
					}, {
						x : 90,
						y : 11
					} ]
				} ],
				legend : {
					cursor : "pointer",
					itemclick : function(e) {
						if (typeof (e.dataSeries.visible) === "undefined"
								|| e.dataSeries.visible) {
							e.dataSeries.visible = false;
						} else {
							e.dataSeries.visible = true;
						}
						chart3.render();
					}
				}
			});

			chart3.render();
		}