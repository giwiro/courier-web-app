document.addEventListener('DOMContentLoaded', function() {
    const elems = document.querySelectorAll('.datepicker');
    const options = {
        format: 'dd/mm/yyyy',
    };
    const instances = M.Datepicker.init(elems, options);
    console.log('Script initialized');
});
