## Overview

**NJX-ZDCharts** wraps the JavaScript library [Chart.js](http://chartjs.org "Chart.js") into a Natural for AJAX&trade; custom control. This library is based on the HTML5 canvas control. Credits to the author [Nick Downie](http://www.nickdownie.com/ "Nick Downie") for the great job he did with Chart.js.

**NJX-ZDCharts** makes the Chart.js chart types available to Natural&trade; programmers.

* Line chart
* Bar chart
* Radar chart
* Polar area chart
* Pie chart
* Doughnut chart

The charts can be configured with a variety of options. Hovering across a chart shows a tooltip explaining the content.

The charts are interactive. Clicking inside a chart raises an event in the application, so that the application can be partly or fully driven by interaction with charts.

## Installation

The file NJX-ZDCharts.zip contains a NaturalONE&trade; project that can be imported into a NaturalONE&trade; Eclipse&trade; workspace. After having built the project, you will find the new control **Chart.js Chart** in the section **zerodrive** in the Layout Painter. Check and execute the example programs to verify if everything is working.

## Usage

The Natural for AJAX&trade; custom control **Chart.js Chart** has the following properties:

### charttype

Select one of the available chart types.

### width and height

The width and height of the chart. Specify for instance 500px.

### valueprop

The name of a string variable in which you later will pass data to the chart. There are convenience subprograms CHART1-N and CHART2-N that create the correct string representation of the chart data from an array of values and options.

### clickmethod

The name of an event that shall be raised when the user clicks into the chart.

### selectedlabel

The name of a string variable that will contain the name (label) of the clicked chart area, when the event specified in clickmethod is raised. 

## Convenience subprograms

In order to fill the Line, Radar and Bar chart, use the data structure CHART1-A.

	1 CHART1-A
	2 DLABEL (U/1:*) DYNAMIC
	2 DSERIES (1:*)
	3 DPROPS (1:*)
	4 PNAME (U) DYNAMIC
	4 PVALUE (U) DYNAMIC
	3 DDATA (P9.2/1:*)

Expand the X-array to your needs, fill the labels and data and set properties for the chart. The names of the available properties can be found in [Chart.js](http://chartjs.org "Chart.js"). Then call CHART1-N to serialize the data structure into a string that can be passed to the chart. Take the provided examples as a guideline on how to fill the data structure.

In order to fill the Polar area, Pie and Doughnut chart, use the data structure CHART2-A.

	1 CHART2-A
	2 DSERIES (1:*)
	3 DPROPS (1:*)
	4 PNAME (U) DYNAMIC
	4 PVALUE (U) DYNAMIC
	3 DDATA (P9.2)

Again, take the provided examples as a guideline on how to fill the data structure.

## Ongoing development

* Customizable tooltips
* Customizable legends
* Set general options for the charts

## Trademarks, registered trademarks and license issues
Natural, NaturalONE and Natural for AJAX are trademarks or registered trademarks of [Software AG](http://softwareag.com "Software AG").  
Eclipse is a trademark or registered trademark of [Eclipse Foundation](http://eclipse.org "Eclipse Foundation").  
zerodrive is a registered trademark of [zerodrive UG (haftungsbeschränkt)](http://zerodrive.net "zerodrive UG (haftungsbeschränkt)").    
Chart.js is released under the MIT license.  
NJX-ZDCharts is released under the EUPL V1.1.
