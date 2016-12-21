<!doctype html>
<html>
<head>
    <meta name="layout" content="main"/>
    <title>Memsource Grails Test App</title>

    <asset:link rel="icon" href="favicon.ico" type="image/x-ico" />
</head>
<body>
    <content tag="nav">
        <li class="active">
            <span>Projects</span>
        </li>
        <li>
            <a href="${createLink(action: 'index', controller: 'setup')}"
               aria-haspopup="true"
               aria-expanded="false">Setup
            </a>
        </li>
        <li>
            <a href="/admin/dbconsole">
                H2 DB Console
            </a>
        </li>
    </content>


</body>
</html>
