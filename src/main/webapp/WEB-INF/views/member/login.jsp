<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<html>

<!-- Mirrored from bootstrap.gallery/maxwell/designs/gradient-version/login.html by HTTrack Website Copier/3.x [XR&CO'2014], Tue, 12 Apr 2022 21:44:33 GMT -->
<head>
    <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <!-- Meta -->
    <meta name="description" content="Responsive Bootstrap4 Dashboard Template">
    <meta name="author" content="ParkerThemes">
    <link rel="shortcut icon" href="../img/fav.png" />

    <!-- Title -->
    <title>Login</title>

    <!-- *************
        ************ Common Css Files *************
    ************ -->
    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="../css/bootstrap.min.css" />

    <!-- Master CSS -->
    <link rel="stylesheet" href="../css/main.css" />

    <script type="text/javascript" charset="UTF-8">

        /*안드로이드 함수 호출*/
        function callAndroid(msg) {
            console.log("start callAndroid")
            /*안드로이드 함수를 호출하며 msg 전송*/
            window.myJs.callAndroid(msg);
        }

        /*이메일 유효성 검사*/
        function validEmail(){
            let obj = document.getElementById("email");
            if(validEmailCheck(obj)==false){
                alert("이메일 형식을 맞춰주세요.");
                obj.focus();
                return false;
            }else return true;
        }
        function validEmailCheck(obj){
            var pattern = /^[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*.[a-zA-Z]{2,3}$/i;
            return (obj.value.match(pattern)!=null)
        }
        function passNullCheck() {
            let doc_pw = document.getElementById("password");
            if(doc_pw.value.length<8) {
                alert("비밀번호를 다시 입력해주세요");
                doc_pw.focus();
                return false;
            }else return true;

        }

        /*submit button on*/
        console.log("script")
        window.onload = function () {
            document.getElementById("submit").onclick = function () {
                console.log("button");
                /*email 유효성 검사와 비밀번호 공백 체크 */
                if(passNullCheck()&&validEmail()){

                    let email = document.getElementById("email").value;
                    let password = document.getElementById("password").value;

                    let jsonData = {"email": email, "password":password};
                    console.log(jsonData);
                    $.ajax({
                        url: 'https://gros24.click/loginProc',
                        // url: 'http://localhost:8443/loginProc',
                        type: "POST",
                        data: {"email": email, "password":password},

                        success: function (data) {
                            console.log("데이터 전송 성공");
                            console.log(data.toString());

                            /*
                             * res -1 //email 미등록
                             * res -2 //email 미인증
                             * res -3 //password 오류
                             * res 1  //로그인 성공
                             */


                            /*loginProc 반환 결과*/
                            if(data.toString() == "로그인 성공"){
                                alert("###로그인 성공");
                                callAndroid("Login");
                            }
                            else if (data.toString() == "email 미등록"){
                                alert("email 미등록");
                                return ;
                                document.getElementById("email").focus();
                            }else if(data.toString() == "email 미인증") {
                                alert("email 미인증");
                                return ;
                            }else if(data.toString() == "password 오류"){
                                alert("password 오류");
                                return ;
                            } else{
                                alert("서버 오류");
                            }
                        },
                        error: function(error){
                            console.log("error");
                            console.log(error);
                        }
                    });
                }else{
                    console.log("다시 입력");
                }
            }
        }

    </script>
</head>

<body class="authentication">

<!-- Container start -->
<div class="container">

    <%--서브밋 경로--%>
    <form>
        <div class="row justify-content-md-center">
            <div class="col-xl-4 col-lg-5 col-md-6 col-sm-12">
                <div class="login-screen">
                    <div class="login-box">
                        <a href="#" class="login-logo">
                        </a>
                        <h5>Welcome back,<br />Please Login to your Account.</h5>
                        <div class="form-group">
                            <input type="text" class="form-control" placeholder="Email Address" id="email"/>
                        </div>
                        <div class="form-group">
                            <input type="password" class="form-control" placeholder="Password" id="password"/>
                        </div>
                        <div class="actions mb-4">
                            <div class="custom-control custom-checkbox">
                                <input type="checkbox" class="custom-control-input" id="remember_pwd">
                                <label class="custom-control-label" for="remember_pwd">Remember me</label>
                            </div>
                            <button type="button" class="btn btn-success" id="submit">Login</button>

                        </div>
                        <div class="forgot-pwd">
                            <a class="link" href="https://gros24.click/resetrequest">Forgot password?</a>
                        </div>
                        <hr>
                        <div class="actions align-left">
                            <a href="https://gros24.click/signup" class="btn btn-info ml-0">Create an Account</a>
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

<!-- Mirrored from bootstrap.gallery/maxwell/designs/gradient-version/login.html by HTTrack Website Copier/3.x [XR&CO'2014], Tue, 12 Apr 2022 21:44:37 GMT -->
</html>