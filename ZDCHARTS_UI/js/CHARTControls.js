/*
 * CHCHARTControls.js
 * Version: 0.8
 * Copyright 2015 Thomas Schwade
 * http://www.zerodrive.net
 * Licensed under the EUPL V.1.1
 * https://github.com/zerodrive/NJX-ZDCharts/blob/master/LICENSE.pdf
 */

function romuCHCHART2(cc, charttype, valueprop, selectedprop, clickmethod) {
	var strData = getPropertyValue(valueprop);
	var data = JSON.parse(strData);
	var ctx = cc.getContext("2d");
	if (cc.CASA_chart != undefined) {
		Chart.defaults.global.animation = false;
		cc.CASA_chart.destroy({});
	} else {
		Chart.defaults.global.animation = true;
	}

	if (charttype == 'Line') {
		cc.CASA_chart = new Chart(ctx).Line(data);
	} else if (charttype == 'Bar') {
		cc.CASA_chart = new Chart(ctx).Bar(data);
	} else if (charttype == 'Radar') {
		cc.CASA_chart = new Chart(ctx).Radar(data);
	} else if (charttype == 'PolarArea') {
		cc.CASA_chart = new Chart(ctx).PolarArea(data);
	} else if (charttype == 'Pie') {
		cc.CASA_chart = new Chart(ctx).Pie(data);
	} else if (charttype == 'Doughnut') {
		cc.CASA_chart = new Chart(ctx).Doughnut(data);
	}

	cc.onclick = function(evt) {
		var activePoints = undefined;
		var selected = undefined;
		if (clickmethod != null) {
			if (charttype == 'Bar') {
				activePoints = cc.CASA_chart.getBarsAtEvent(evt);
			} else if (charttype == 'Line'
					|| charttype == 'Radar') {
				activePoints = cc.CASA_chart.getPointsAtEvent(evt);
			} else {
				activePoints = cc.CASA_chart.getSegmentsAtEvent(evt);
			}
			selected = activePoints[0].label;
			setPropertyValue(selectedprop, selected);
			invokeMethodInModel(clickmethod);
		}
	}
}