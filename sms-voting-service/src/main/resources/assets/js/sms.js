//$(document).ready(function() {
//	function drawVisualization() {
//		  // Create and populate the data table.
//		  var data = google.visualization.arrayToDataTable([
//		    ['Options', 'Red', 'White'],
//		    ['',  4,    1]
//		  ]);
//
//		  // Create and draw the visualization.
//		  new google.visualization.BarChart(document.getElementById('visualization')).
//		      draw(data,
//		           {title:"Real Time SMS-Voting Statistics",
//		            width:800, height:400,
//		            vAxis: {title: "Options"},
//		            hAxis: {title: "Count"}}
//		      );
//		}
//});
$(document).ready(function() {
	
	$(":button").click(function() {
		var id = this.id;
		var selectedVal = "";
		var selected = $("input[type='radio'][name='option']:checked");
		//alert('length : '+selected.length)
		if (selected.length > 0) {
		    selectedVal = selected.val();
		    //alert('selected value is : '+selectedVal);
		    localStorage.clear();
		    localStorage.setItem('selectedOpt', selectedVal);
		}
		var destinationUrl = "http://localhost:8080/sms-voting/v1/user/polls/"+id+"?option="+selectedVal;
		var button = ':button#'+id;
		jQuery.ajax({
		    type: "PUT",
		    url: destinationUrl,
		    contentType: "application/json",
		    success : function ()
		    {
		    	alert('success');
		    	location.reload();
		    	
		    },
			failure : function ()
			{
				alert('failure...');
			}
		});
		$(button).attr("disabled", true);
	});
	
	 // Retrieve the radio button value from the local storage
	 var savedOption = localStorage.getItem('selectedOpt');
	 $('input[name=option][value=' + savedOption + ']').attr('checked', 'checked');
	 
	 //function drawVisualization() {
		  // Create and populate the data table.
//		var poll = document.getElementById("poll");
//	        for (var i = 1, row; row = poll.rows[i]; i++) {
//	        		var option = poll.rows[i].cells[1].innerHTML;
//	                var optionCount=poll.rows[i].cells[2].innerHTML;
//	                var data = google.visualization.arrayToDataTable([
//	                           [option],
//	                           [optionCount]
//	                           ]);
//	        
//		 // Create and draw the visualization.
//		  new google.visualization.BarChart(document.getElementById('test')).
//		      draw(data,
//		           {title:"Real Time SMS-Voting Statistics",
//		            width:800, height:400,
//		            vAxis: {title: "Options"},
//		            hAxis: {title: "Count"}}
//		      );
//	        }
		//}
}); 
	 
	 //======================================
//	 $(function () {
//		    var chart;
//		    $(document).ready(function() {
//		    	chart = new Highcharts.Chart({
//		            chart: {
//		                renderTo: 'container',
//		                type: 'bar'
//		            },
//		            title: {
//		                text: 'Stacked bar chart'
//		            },
//		            xAxis: {
//		                categories: ['$option']
//		            },
//		            yAxis: {
//		                min: 0,
//		                title: {
//		                    text: 'Web-voting statistics'
//		                },
//		                stackLabels: {
//		                enabled: true,
//		                style: {
//		                    fontWeight: 'bold',
//		                    color: (Highcharts.theme && Highcharts.theme.textColor) || 'gray'
//		                }
//		            }
//		            },
//		            legend: {
//		               align: 'right',
//		            x: -100,
//		            verticalAlign: 'top',
//		            y: 20,
//		            floating: true,
//		            backgroundColor: (Highcharts.theme && Highcharts.theme.legendBackgroundColorSolid) || 'white',
//		            borderColor: '#CCC',
//		            borderWidth: 1,
//		            shadow: false
//		            },
//		            tooltip: {
//		              formatter: function() {
//		                return '<b>'+ this.x +'</b><br/>'+
//		                    this.series.name +': '+ this.y +'<br/>'+
//		                    'Total: '+ this.point.stackTotal;
//		            }
//		            },
//		            plotOptions: {
//		                series: {
//		                    stacking: 'normal',
//		                    dataLabels: {
//		                    enabled: true,
//		                    color: (Highcharts.theme && Highcharts.theme.dataLabelsColor) || 'white'
//		                }
//		                }
//		            }
//		        });
//		    });
//		});
	 //======================================
