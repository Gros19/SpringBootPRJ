<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="kopo.poly.dto.CorpDTO" %>
<%@ page import="kopo.poly.util.CmmUtil" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.Iterator" %>

<html>
<head>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.4.0/Chart.min.js"></script>
    <%

        ArrayList<CorpDTO> list = (ArrayList<CorpDTO>) request.getAttribute("result");
        if (list == null) {
            list = new ArrayList<CorpDTO>();
        }
    %>
    <title>Title</title>
</head>
<body>


<div>
    <button onclick="window.location.href='https://gros24.click/getCorp'">모든 회사 보기</button>
    <button onclick="window.location.href='https://gros24.click/getFilterCorp'">흑자 기업만 보기</button>
</div>
<br/>

<%
    for(CorpDTO c : list){


%>
<div style="width: 900px; height: 100px;margin-bottom: 50px;">
    <!--차트가 그려질 부분-->
    <canvas id="bar-chart-horizontal-<%=c.getCorpCode()%>"></canvas>
</div>
<br/>

<%
    }
%>
<script type="text/javascript">



    <%
    Iterator<CorpDTO> it = list.iterator();

    while (it.hasNext()){
        CorpDTO c = it.next();
%>

    new Chart(document.getElementById("bar-chart-horizontal-<%=c.getCorpCode()%>"), {
        type: 'horizontalBar',
        data: {
            labels: ["<%=c.getCorpName()%>"],
            datasets: [
                {
                    label: "영업활동 현금흐름",
                    backgroundColor: ['rgba('+Math.floor(Math.random() * 255)+','+Math.floor(Math.random() * 255)+','+Math.floor(Math.random() * 255)+','+"0.5)"],
                    data: ["<%=c.getAmount()%>"]

                }
            ]
        },
        options: {responsive: false,
            legend: { display: false },
            title: {
                display: true,
                text: '영업활동 현금흐름'
            },

        }
    });


    <%

   }

%>

</script>
</body>
</html>
