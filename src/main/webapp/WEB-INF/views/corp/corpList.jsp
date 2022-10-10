<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="kopo.poly.dto.CorpDTO" %>
<%@ page import="kopo.poly.util.CmmUtil" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.Iterator" %>

<html>
<head>
    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="../css/bootstrap.min.css"/>

    <!-- Master CSS -->
    <link rel="stylesheet" href="../css/main.css"/>

    <script src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.4.0/Chart.min.js"></script>
    <%

        ArrayList<CorpDTO> list = (ArrayList<CorpDTO>) request.getAttribute("result");
        if (list == null) {
            list = new ArrayList<CorpDTO>();
        }
    %>
    <title>Corp List</title>
</head>
<body>

<div class="container">

    <div class="row justify-content-md-center">
        <div class="col-xl-4 col-lg-5 col-md-6 col-sm-12">

            <div class="login-screen" style="width: 50%">
            <button class="btn btn-info ml-0" onclick="window.location.href='https://gros24.click/getCorp'">모든 회사 보기</button>
            <button class="btn btn-info ml-0" onclick="window.location.href='https://gros24.click/getFilterCorp'">흑자 기업만 보기</button>

            <br/>

            <%
                for (CorpDTO c : list) {


            %>
            <div style="margin-bottom: 50px;">
                <!--차트가 그려질 부분-->
                <canvas id="bar-chart-horizontal-<%=c.getCorpCode()%>"></canvas>
            </div>
            <br/>

            <%
                }
            %>

            </div>
        </div>
    </div>
</div>

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
                    backgroundColor: ['rgba(' + Math.floor(Math.random() * 255) + ',' + Math.floor(Math.random() * 255) + ',' + Math.floor(Math.random() * 255) + ',' + "0.5)"],
                    data: ["<%=c.getAmount()%>"]

                }
            ]
        },
        options: {
            responsive: false,
            legend: {display: false},
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
