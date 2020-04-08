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

//Remove 'All' option for Index page dropdown
$(".indexDropdown .dropdown-item").last().remove();

$(function () {
    //click on table delete button
    $('.deleteSkunkBtn'). on('click', function(){
        const skunkId = $(this).data("skunk");

        $("#indexPageModalTitle").text("Skunk");
        $("#indexPageModalBody").children('p').text('Please select the datasource in order to delete the chosen skunk:');

        $.ajax({
            type: "GET",
            url: "/api/skunks/dataSources/" + skunkId,
            contentType: "application/json; charset=utf-8",
            success: function (result) {
                console.log('success');
                $('#indexPageModalBody .checkboxes').empty();

                $.each(result, function (i, datasource) {
                    $('.checkboxes').append($('<input class="form-check-input checkBox ml-3" type="checkbox" name="checkboxes" id="checkbox1" value="option1">').val(datasource));
                    $('.checkboxes').append($('<label class="ml-2 form-check-label" for="checkbox1">').text(datasource));
                });
            },
            error: function (error) {
                console.log("error");
            }
        });

        var array = [];
        $('#indexPageModal').on('shown.bs.modal', function () {
            $('.checkBox').on('change', function() {
                if ($(this).prop('checked') === true) {
                    array.push($(this).attr('value'));
                } else {
                    array.splice(array.indexOf($(this).attr('value'),1));
                }
            });
        });
        //delete on button click
        $("#deleteSkunkModalBtn").on ('click', function() {
            $.ajax({
                type: "POST",
                url: "/api/skunks/dataSources/" + skunkId,
                data: {
                    "dataSources": array
                },
                success: function (response) {
                    console.log("success");
                    $('#indexPageModal').modal('hide');
                    window.location.href = "/index";
                },
                error: function () {
                    console.log("error");
                }
            });
        });

    });

});

