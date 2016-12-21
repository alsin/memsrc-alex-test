// This is a manifest file that'll be compiled into application.js.
//
// Any JavaScript file within this directory can be referenced here using a relative path.
//
// You're free to add application-wide JavaScript to this file, but it's generally better
// to create separate JavaScript files as needed.
//
//= require jquery-2.2.0.min
//= require bootstrap
//= require_tree .
//= require_self

if (typeof jQuery !== 'undefined') {
    (function($) {
        $(document).ajaxStart(function() {
            $('#spinner').fadeIn();
        }).ajaxStop(function() {
            $('#spinner').fadeOut();
        });
    })(jQuery);
}

$('#loadProjectsBtn').on('click', function () {
    var $btn = $(this).button('loading')

    var req = $.post( '/projects');

    req.done(function( data ) {
        $( "#alertMsg" ).empty().text("Loaded: " + data.length + " rows");
        $("#projectsTableBody").empty();
        $.each(data, function (i, item) {
            $('<tr>').append(
                $('<td>').text(i),
                $('<td>').text(item.name),
                $('<td>').text(item.status),
                $('<td>').text(item.sourceLang),
                $('<td>').text(item.targetLangs ? item.targetLangs.join(', ') : '')
            ).appendTo('#projectsTableBody');
        });
        $( "#statusDiv" ).removeClass('alert-danger').addClass('alert-success').show();
    }).fail(function( data ) {
        $( "#alertMsg" ).empty().text(data.responseJSON.errorDescription);
        $( "#statusDiv").removeClass('alert-success').addClass('alert-danger').show();
    }).always(function () {
        $btn.button('reset')
    });

});

$( "#submitMemsrcCredsForm" ).submit(function( event ) {

    event.preventDefault();

    $("#submitMemsrcCredsBtn").button('loading');

    var $form = $( this ),
        memsrcLogin = $form.find( "input[name='memsrcLogin']" ).val(),
        memsrcPassword = $form.find( "input[name='memsrcPassword']" ).val(),
        url = $form.attr( "action" );

    var req = $.post( url, { login: memsrcLogin, password: memsrcPassword } );

    req.done(function( data ) {
        var content = $( data ).find( "#content" );

        $( "#alertMsg" ).empty().text("Successfully updated Memsource credentials!");
        $( "#statusDiv" ).removeClass('alert-danger').addClass('alert-success').show();
    }).fail(function( data ) {
        $( "#alertMsg" ).empty().text(data.responseJSON.errorDescription);
        $( "#statusDiv").removeClass('alert-success').addClass('alert-danger').show();
    }).always(function () {
        $("#submitMemsrcCredsBtn").button('reset');
    });
});