<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
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
    <title>Maxwell Admin Template - Signup</title>



    <!-- *************
        ************ Common Css Files *************
    ************ -->
    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="../css/bootstrap.min.css"/>

    <!-- Main CSS -->
    <link rel="stylesheet" href="../css/main.css"/>
    <script type="text/javascript" charset="UTF-8">

        /*안드로이드 함수 호출*/
        function callAndroid(str) {
            console.log("start callAndroid");
            window.myJs.callAndroid(str);
        }

        /*아이디 유효성 검사*/
        function CV_checkIdPattern(){
            let d_id = document.getElementById("id");
            let str = d_id.value;
            var pattern1 = /[0-9]/; // 숫자
            var pattern2 = /[a-zA-Z]/; // 문자
            var pattern3 = /[~!@#$%^&*()_+|<>?:{}]/; // 특수문자

            var numtextyn = (pattern1.test(str) || pattern2.test(str));
            if(!numtextyn || pattern3.test(str) || str.length > 14) {
                alert("아이디는 14자리 이하 문자 또는 숫자로 구성하여야 합니다.");
                d_id.value = "";
                d_id.focus();
                return false;
            } else {
                return true;
            }
        }

        /*이메일 유효성 검사*/
        function validEmail(){
            let obj = document.getElementById("email");
            if(validEmailCheck(obj)==false){
                alert("이메일 형식을 맞춰주세요.");
                obj.value='';
                obj.focus();
                return false;
            }else return true;
        }
        function validEmailCheck(obj){
            var pattern = /^[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*.[a-zA-Z]{2,3}$/i;
            return (obj.value.match(pattern)!=null)
        }

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

                /*이메일&비밀번호 유효성 검사 성공 시*/
                if(CV_checkIdPattern()&&validEmail()&&CheckPassword()){
                    let email = document.getElementById("email").value;
                    let password = document.getElementById("password").value;
                    let id = document.getElementById("id").value;

                    let jsonData = {"email": email, "password":password, "id":id};
                    console.log(jsonData);
                    $.ajax({
                        url: 'https://gros19.click/signupProc',
                        // url: 'http://localhost:8443/signupProc',
                        type: "POST",
                        data: {"email": email, "password":password, "id":id},

                        success: function (data) {
                            console.log("데이터 전송 성공");
                            console.log(data);
                            if (data.toString() == "result duplicated"){
                                alert("이메일 중복");
                                document.getElementById("email").focus();
                            }else if(data == "id duplicated"){
                                alert("id 중복");
                                document.getElementById("id").focus();
                            }else if(data == "error"){
                                alert("서버의 오류가 발생했습니다.");
                            }
                            else{
                                alert("이메일 발송");
                                callAndroid("Send");

                            }
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
            <div class="col-xl-5 col-lg-6 col-md-6 col-sm-12">
                <div class="login-screen">
                    <div class="login-box">
                        <a href="#" class="login-logo">
                            <img src="../img/logo-dark.png" alt="Maxwell Admin Dashboard"/>
                        </a>
                        <h5>Welcome,<br/>Create your Admin Account.</h5>
                        <div class="form-group">
                            <input type="text" class="form-control" placeholder="id" id="id"/>
                        </div>
                        <div class="form-group">
                            <input type="text" class="form-control" placeholder="Email Address" id="email"/>
                        </div>
                        <div class="form-group">
                            <div class="input-group">
                                <input type="password" class="form-control" placeholder="Password" id="password"/>
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="input-group">
                                <input type="password" class="form-control" placeholder="Confirm Password" id="password2"/>
                            </div>
                        </div>
                        <div class="actions mb-4">
                            <div class="custom-control custom-checkbox">
                                <input type="checkbox" class="custom-control-input" id="remember_pwd">
                                <label class="custom-control-label" for="remember_pwd">Remember me</label>
                            </div>
                            <button type="button" class="btn btn-success" id="submit">Signup</button>
                        </div>
                        <hr>
                        <div class="m-0">
                            <a href="https://gros19.click/login" class="btn btn-info ml-0">Login</a></span>
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