<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
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

        /*안드로이드 함수 호출*/
        function callAndroid(msg) {
            console.log("start callAndroid");
            /*안드로이드 함수를 호출하며 msg 전송*/
            window.myJs.callAndroid(msg);
        }

        /*이메일 유효성 검사*/
        function validEmail() {
            let obj = document.getElementById("email");
            if (validEmailCheck(obj) == false) {
                alert("이메일 형식을 맞춰주세요.");
                obj.value = '';
                obj.focus();
                return false;
            } else return true;
        }

        function validEmailCheck(obj) {
            var pattern = /^[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*.[a-zA-Z]{2,3}$/i;
            return (obj.value.match(pattern) != null)
        }

        console.log("script");
        window.onload = function () {
            document.getElementById("submit").onclick = function () {
                console.log("clicked!");

                /*email 유효성 검사 성공 시*/
                if (validEmail()) {
                    let email = document.getElementById("email").value;

                    console.log(email)
                    let jsonData = {"email": email};
                    console.log(jsonData);
                    $.ajax({
                        url: 'https://gros19.click/resetRequest',
                        type: "POST",
                        data: {"email": email},

                        /*
                         * res -1 //email 미등록
                         * res -2 //email 미인증
                         * res  0 //server error
                         * res 1  //이메일 확인
                        * */
                        success: function (data) {
                            console.log("데이터 전송 성공");
                            console.log(data);
                            if (data.toString() == "1") {
                                alert("이메일 정상 확인");
                                callAndroid("Send");
                            } else{
                                alert("이메일이 유효하지 않습니다.");
                                document.getElementById("email").focus();
                            }
                        },
                        error: function (error) {
                            console.log("error");
                            console.log(error);
                        }
                    });
                }


            }
        }
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
                        <img src="../img/logo-dark.png" alt="Maxwell Admin Dashboard"/>
                    </a>
                    <h5>In order to access your dashboard, please enter the email id you provided during the
                        registration process.</h5>
                    <div class="form-group">
                        <input type="text" class="form-control" placeholder="Enter Email Address" id="email"/>
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