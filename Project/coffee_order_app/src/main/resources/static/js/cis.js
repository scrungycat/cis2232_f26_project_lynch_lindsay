//added this for failsafe in case table data is empty
$(document).ready(function () {
    $('#theData').DataTable({
        order: [[0, 'desc']],
        columnDefs: [{ targets: -1, orderable: false, searchable: false }],
        language: {
            emptyTable: 'No coffee orders found. Make sure the database is set up using resources/db/mysql/createDatabase.sql'
        }
    });
});