//绘制正多边形
function polygon(context, n, x, y, r, angle, counterclockwise) {
				var delta = 2 * Math.PI / n;
				angle = angle || 0;
				counterclockwise = counterclockwise || false;
				context.moveTo(x + r * Math.sin(angle), y - r * Math.cos(angle));
				for (var i = 0; i < n; i++) {
					angle += counterclockwise ? -delta : delta;
					context.lineTo(x + r * Math.sin(angle), y - r * Math.cos(angle));
				}
				context.closePath();
			}
//绘制连接正多边形顶点的线段
function line(context, n, x, y, r, angle, counterclockwise) {
				var delta = 2 * Math.PI / n;
				angle = angle || 0;
				counterclockwise = counterclockwise || false;
				for (var i = 0; i < n; i++) {
					context.moveTo(x, y);
					angle += counterclockwise ? -delta : delta;
					context.lineTo(x + r * Math.sin(angle), y - r * Math.cos(angle));
				}
				context.closePath();
			}

//在各顶点处插入文字
function keyWord(context, n, x, y, r, angle, counterclockwise,keyWords) {
				var delta = 2 * Math.PI / n;
				angle = angle || 0;
				counterclockwise = counterclockwise || false;
				for (var i = 0; i < n; i++) {
					angle += counterclockwise ? -delta : delta;
					context.strokeText(keyWords[i],x + r * Math.sin(angle) - 20, y - r * Math.cos(angle) - 6);
				}
			}

//绘制雷达图
function radarChart(context, n, x, y, r, angle, counterclockwise,value) {
				var delta = 2 * Math.PI / n;
				angle = angle || 0;
				counterclockwise = counterclockwise || false;
				for (var i = 0; i < n; i++) {
					angle += counterclockwise ? -delta : delta;
					context.lineTo(x + value[i]*2 * Math.sin(angle), y - value[i]*2 * Math.cos(angle));//要放大两倍
					//在每一个节点处加一个小矩形
					context.fillStyle = "#00B2EE";
					context.fillRect(x + value[i]*2 * Math.sin(angle) - 4, y - value[i]*2 * Math.cos(angle) - 4, 8, 8);
				}
				context.closePath();
			}