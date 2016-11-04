/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 * Initialze an event when DOM is ready then function callback starts running.
 * **/
$(document).ready(function() {
    $('#itemTable > tbody > tr').click(function(evt) {
        if(evt.target.tagName != 'BUTTON') {
            var rows = $('#itemTable > tbody > tr');
            var clickedRow = $(this);
            var editButton = $('#editButton');
            var deleteButton = $('#deleteButton');
            
            if (!clickedRow.hasClass('active')) {
                rows.removeClass('active');
                clickedRow.addClass('active');
                editButton.show().attr('href', '/spectre/item/edit?id=' + clickedRow.attr('id'));
                deleteButton.show();
                $('#deleteButtonModal').attr('href', '/spectre/item/delete?id=' + clickedRow.attr('id'));
            } else {
                rows.removeClass('active');
                editButton.hide();
                deleteButton.hide();                
            }
        }
    });
    
    $('table input').change(function() {
        var totalAmount = $('#totalAmount');
        
        alert($(this).attr('value'));
    });
});


//                                    $(document).ready(function() {
//                                        $('tbody>tr').click(function () {
//                                            if ($(this).hasClass('active')) {
//                                                $('tbody>tr').removeClass('active');
//                                                $('#editButton').fadeOut('normal');
//                                                $('#deleteButton').fadeOut('normal');
//                                            } else {
//                                                $('tbody>tr').removeClass('active');
//                                                $(this).addClass('active');
//                                                $('#editButton').fadeIn('fast');
//                                                $('#deleteButton').fadeIn('fast');
//                                                $('#editButton').;
//                                                $('#deleteButtonModal').
//                                               
//                                            };
//                                        });
//                                    });


