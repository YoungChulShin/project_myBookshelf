var mainFunction = {
    init : function () {
        var _this = this;
        $('#btn-search-book').on('click', function() {
            _this.searchBook();
        });
    },
    searchBook : function () {
        //var searchKeyword = $('#search-keyword').val();
        alert('Test');

//        $.ajax({
//            type: 'GET',
//            url: '/api/v1/books/new/' + searchKeyword,
//            dataType: 'json',
//            contentType: 'application/json; charset=utf-8'
//        }).fail(function(error) {
//            alert(JSON.stringify(error));
//        });
    }
};

mainFunction.init();