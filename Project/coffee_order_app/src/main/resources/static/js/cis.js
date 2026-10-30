//Unsure how much of this is needed yet so it'll stay for now (as of S2)
$(document).ready(function () {
    $('#theData').dataTable();
    $('#theData')
            .removeClass('display')
            .addClass('table table-striped table-bordered');

    //alert("Ripple is used to animate the buttons a bit");
    mdc.ripple.MDCRipple.attachTo(document.querySelector('.foo-button'));
    // let table = new DataTable('#myTable');
});