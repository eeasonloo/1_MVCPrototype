<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8"/>
    <meta http-equiv="X-UA-Compatible" content="IE=edge"/>
    <meta name="viewport" content="width=device-width, initial-scale=1"/>


    <link href="css/bootstrap.min.css" rel="stylesheet">
    <script src="js/jquery-2.1.0.min.js"></script>
    <script src="js/bootstrap.min.js"></script>
    <script type="text/javascript"></script>
    <script>
        function refreshCode() {
            var vcode = document.getElementById("vcode");
            vcode.src = "${pageContext.request.contextPath}/checkCodeServlet?time=" + new Date().getTime();
        }
    </script>
    <title>Admin Login</title>

</head>
<body>
<div class="container" style="width: 400px;margin-top: 250px">
    <h3 style="text-align: center;">Admin Login</h3>
    <form action="${pageContext.request.contextPath}/loginServlet" method="post">
        <div class="form-group">
            <label for="user">Username</label>
            <input type="text" name="username" class="form-control" id="user" placeholder="Enter your username"/>
        </div>

        <div class="form-group">
            <label for="password">Password:</label>
            <input type="password" name="password" class="form-control" id="password" placeholder="Enter your password"/>
        </div>

        <div class="form-inline">
            <label for="vcode">Verification code</label>
            <input type="text" name="verifycode" class="form-control" id="verifycode" placeholder="Enter the verification code" style="width: 120px;"/>
            <a href="javascript:refreshCode()"><img src="${pageContext.request.contextPath}/checkCodeServlet"
                                                    title="Not Clear? Click to refresh" id="vcode"/></a>
        </div>
        <hr/>
        <div class="form-group" style="text-align: center;">
            <input class="btn btn btn-primary" type="submit" value="Login">
        </div>
    </form>

    <div class="alert alert-warning alert-dismissible" role="alert" >
        <button type="button" class="close" data-dismiss="alert" >
            <span>&times;</span></button>
        <strong>${login_msg}</strong>
    </div>
</div>
</body>
</html>