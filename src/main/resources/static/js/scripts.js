(function() {
    'use strict';
    window.addEventListener('load', function() {
        // Fetch all the forms we want to apply custom Bootstrap validation styles to
        var forms = document.getElementsByClassName('needs-validation');
        // Loop over them and prevent submission
        var validation = Array.prototype.filter.call(forms, function(form) {
            form.addEventListener('submit', function(event) {
                if (form.checkValidity() === false) {
                    event.preventDefault();
                    event.stopPropagation();
                }
                form.classList.add('was-validated');
            }, false);
        });
    }, false);
})();

//Remove All option for Index page dropdown
$(".indexDropdown .dropdown-item").last().remove();

$(function () {

    $('.deleteSkunkBtn'). on('click', function(){
        const skunkId = $(this).data("skunk");
        console.log(skunkId);
        var skunkName = $(this).data('name');
        var datasource = $(this).data('datasource');

        var deleteSkunkUrl = "skunks/" + datasource + "/" + skunkId + "/delete";

        $("#deleteSkunkModalBtn").attr("href", deleteSkunkUrl);
        $("#deleteSkunkModalBtn").text("Delete");

        $("#indexPageModalTitle").text("Skunk");
        $("#indexPageModalBody").children('p').text('Are you sure you want to delete the following skunk?');
        $("#indexPageModalBody").children('h5').text(skunkName);



        // var datasources =/*[[${skunkDto}]]*/ 'enum';
        // console.log(datasources);
        // var data = JSON.parse(datasources);




    });


});