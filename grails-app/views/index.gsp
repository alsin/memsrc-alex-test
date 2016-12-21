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
            <a href="/admin/dbconsole" target="_blank">
                H2 DB Console
            </a>
        </li>
    </content>

<div class="container-fluid">
    <div class="row">
        <h1>Projects Page</h1>
    </div>
    <div class="row alert-row">
        <div class="alert" role="alert" id="statusDiv" style="display: none;">
            <span id="alertMsg"></span>
        </div>
    </div>
    <div class="row projects">
        <table class="table table-striped">
            <thead>
                <tr>
                    <th>#</th>
                    <th>Name</th>
                    <th>Status</th>
                    <th>Source Language</th>
                    <th>Target Languates</th>
                </tr>
            </thead>
            <tbody id="projectsTableBody">
            </tbody>
        </table>
    </div>
    <div class="row">
        <button type="button" class="btn btn-default" id="loadProjectsBtn">Load</button>
    </div>
</div>

</body>
</html>
