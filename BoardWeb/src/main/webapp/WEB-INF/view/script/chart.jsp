<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

<head>
  <script type="text/javascript" src="https://www.gstatic.com/charts/loader.js"></script>
  <script type="text/javascript">
    google.charts.load('current', {
      'packages': ['corechart']
    });
    
    // ajax호출. url: centerChart.do
    let centerAry = [];
    centerAry.push(['시도명', '센터갯수']);

    fetch('centerChart.do')
      .then(result => result.json())
      .then(result => {
        console.log(result);
        //for (let prop in result) {
        //  centerAry.push([prop, result[prop]]);
        //}
        result.forEach(center => {
          centerAry.push([center.sido, center.cnt]);
        })
	    google.charts.setOnLoadCallback(drawChart); // 비동기처리의 순서.
      });

    function drawChart() {
      // [{},{},{}] => [ [],[],[],[] ] 
      console.log(centerAry.length);
      var data = google.visualization.arrayToDataTable(centerAry);

      var options = {
        title: '시도별 센터갯수'
      };

      var chart = new google.visualization.PieChart(document.getElementById('piechart'));

      chart.draw(data, options);
    }
  </script>
</head>

<body>
  <div id="piechart" style="width: 900px; height: 500px;"></div>
</body>

</html>