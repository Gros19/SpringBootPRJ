<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@
        page import="kopo.poly.dto.MemberDTO"
%>
<html>

<head>
    <!-- Required meta tags -->
    <meta charset="utf-8">

    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <!-- Meta -->
    <meta name="description" content="Responsive Bootstrap4 Dashboard Template">
    <meta name="author" content="ParkerThemes">
    <link rel="shortcut icon" href="../img/fav.png"/>

    <!-- Title -->
    <title>Maxwell Admin Template - Password Reset</title>



    <!-- *************
        ************ Common Css Files *************
    ************ -->
    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="../css/bootstrap.min.css"/>

    <!-- Main CSS -->
    <link rel="stylesheet" href="../css/main.css"/>
    <script type="text/javascript" charset="UTF-8">

        /*비밀번호 유효성 검사*/
        function CheckPassword(){
            let upw = document.getElementById("password");
            let upw2 = document.getElementById("password2");

            if(!/^[a-zA-Z0-9]{8,20}$/.test(upw.value)){
                alert("비밀번호는 숫자와 영문자 조합으로 8~20자리를 사용해야 합니다.");
                upw.value='';
                upw.focus();
                return false;
            }
            var chk_num = upw.value.search(/[0-9]/g);
            var chk_eng = upw.value.search(/[a-z]/ig);
            if(chk_num<0 || chk_eng<0){
                alert("비밀번호는 숫자와 영문자를 혼용하여야 합니다.");
                upw.value='';
                upw.focus();
                return false;
            }

            if(/(\w)\1\1\1/.test(upw.value)){
                alert("비밀번호에 같은 문자를 4번 이상 사용하실 수 없습니다.");
                upw.value='';
                upw.focus();
                return false;
            }

            if(upw.value != upw2.value){
                alert("비밀번호가 다릅니다.");
                upw2.value='';
                upw2.focus();
                return false;
            }
            return true;
        }


        console.log("script");
        window.onload = function () {
            document.getElementById("submit").onclick = function () {
                console.log("clicked!");

                <%
                MemberDTO mDTO = (MemberDTO) request.getAttribute("MemberDTO");
                %>
                /*password 유효성 검사 성공 시*/
                if(CheckPassword()){
                    let password = document.getElementById("password").value;
                    let email = "<%=mDTO.getEmail()%>";
                    let auth = "<%=mDTO.getAuth()%>";

                    console.log("<%=mDTO.getEmail()%>");
                    console.log("<%=mDTO.getAuth()%>");
                    console.log(password);

                    let jsonData = {
                        "email": email,
                        "auth": auth,
                        "password": password
                    };
                    console.log(jsonData);
                    $.ajax({
                        url: 'https://gros19.click/resetProc',
                        type: "POST",
                        data: {
                            "email": email,
                            "password":password,
                            "auth": auth
                        },

                        success: function (data) {
                            console.log("데이터 전송 성공");
                            console.log(data);
                            if (data.toString() == "1"){
                                alert("비밀번호 변경 완료");
                            }else {
                                alert("서버의 오류가 발생했습니다.");
                            }
                            window.close()
                        },
                        error: function(error){
                            console.log("error");
                            console.log(error);
                        }
                    });
                }


            }}
    </script>

</head>
<body class="authentication">

<!-- Container start -->
<div class="container">

    <form>
        <div class="row justify-content-md-center">
            <div class="col-xl-5 col-lg-5 col-md-6 col-sm-12">
                <div class="login-screen">
                    <div class="login-box">
                        <a href="#" class="login-logo">
                            <img src="../img/logo-dark.png" alt="Maxwell Admin Dashboard" />
                        </a>
                        <h5>In order to access your dashboard, please enter the email id you provided during the registration process.</h5>
                        <div class="form-group">
                            <div class="input-group">
                                <input type="password" class="form-control" placeholder="Enter New password" id="password" />
                            </div>
                        </div><div class="form-group">
                            <div class="input-group">
                                <div class="input-group">
                                    <input type="password" class="form-control" placeholder="Confirm Password" id="password2"/>
                                </div>
                            </div>
                        </div>
                        <div class="actions">
                            <button type="button" class="btn btn-success" id="submit">Submit</button>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </form>

</div>
<!-- Container end -->

<script src="js/jquery.min.js"></script>
</body>

<!-- Mirrored from bootstrap.gallery/maxwell/designs/gradient-version/signup.html by HTTrack Website Copier/3.x [XR&CO'2014], Tue, 12 Apr 2022 21:48:34 GMT -->
</html>