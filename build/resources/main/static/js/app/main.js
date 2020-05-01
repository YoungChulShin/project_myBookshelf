let commonFunction = {
    init : function () {
        let _this = this;
        $('#btn-search-book').on('click', function() {
            _this.searchBook();
        });
    },
    searchBook : function () {
        let searchKeyword = $('#search-keyword').val();

        let form = document.createElement("form");
        form.setAttribute("method", "get");
        form.setAttribute("action", "/api/v1/books/new/" + searchKeyword);
        document.body.appendChild(form);
        form.submit();
   }
};

commonFunction.init();