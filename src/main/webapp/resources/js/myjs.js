$(document)
    .ready(
    function () {
        //add more file components if Add is clicked
        $('#addFile')
            .click(
            function () {
                var fileIndex = $('#fileTable tr').children().length;
                $('#fileTable')
                    .append(
                        '<tr id="row_' + fileIndex + '"><td>'
                            + '   <input type="file" name="file' + fileIndex + '" required onchange="myFunc(this)"/>'
                            + '</td>' + '<td><div id="preview_file' + fileIndex + '"></div></td>' +
                            '<td><a  id="delete_file' + fileIndex + '"href="#" onclick="delFile(this)">delete</a></td>' +
                            '</tr>'
                    )
                ;
            });

    });
function myFunc(input) {
    if (input.files && input.files[0]) {
        var reader = new FileReader();

        reader.onload = function (e) {
            var container = $('#preview_' + $(input).attr('name'));
            var image = $('<img id="img_' + $(input).attr('name') + '">');
            image.attr('src', e.target.result).attr('class', 'picturePreview');
            container.empty();
            image.appendTo(container);
        };
        reader.readAsDataURL(input.files[0]);
    }
}

function delFile(link) {
    var fileIndex = $(link).attr("id").match(/[\d]+$/);
    $('#row_' + fileIndex).remove();

}

$(document)
    .ready(
    function () {
        $("input[name='cardType']").on('click', function () {
            var cardType = $("input[name='cardType']:checked");
            if (cardType.val() == 'support') {
                $('#warriorCardType').hide();
            } else {
                $('#warriorCardType').show();
            }

        });

        var cardType = $("input[name='cardType']:checked");
        if (cardType.val() == 'support') {
            $('#warriorCardType').hide();
        } else {
            $('#warriorCardType').show();
        }

    });