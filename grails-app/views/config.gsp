<!doctype html>
<html>
<head>
    <meta name="layout" content="main"/>
    <title>Memsource Grails Test App</title>

    <asset:link rel="icon" href="favicon.ico" type="image/x-ico" />
</head>
<body>
<content tag="nav">
    <li>
        <a href="/"
           aria-haspopup="true"
           aria-expanded="false">Projects
        </a>
    </li>
    <li class="active">
        <span>Setup</span>
    </li>
    <li>
        <a href="/admin/dbconsole">
            H2 DB Console
        </a>
    </li>
</content>

<div class="container-fluid">
    <div class="row alert-row">
        <div class="alert alert-dismissable" role="alert" id="statusDiv" style="display: none;">
            <span id="alertMsg"></span>
        </div>
    </div>
    <div class="row memsrc-account-form">
        <form id="submitMemsrcCredsForm" action="/setup/modify">
            <div class="form-group">
                <label for="memsrcLogin">Login</label>
                <input type="text"
                       class="form-control"
                       name="memsrcLogin"
                       id="memsrcLogin"
                       placeholder="Login"
                       value="${userName}">
            </div>
            <div class="form-group">
                <label for="memsrcPassword">Password</label>
                <input type="password"
                       class="form-control"
                       name="memsrcPassword"
                       id="memsrcPassword"
                       placeholder="Password">
            </div>
            <button type="submit" class="btn btn-default" id="submitMemsrcCredsBtn">Save</button>
        </form>
    </div>
</div>

</body>
</html>
