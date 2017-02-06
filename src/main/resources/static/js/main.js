$(document).ready(function () {

    displayAllBooksReviews();

});

function displayAllBooksReviews() {
        $.ajax({
          type: "GET",
          url: "/reviews",
          success: function(data){
             console.log(data);
             $.each (data, function (index) {
                var title = ('<div class="title" id="title' + index + '">' + data[index].title + '</div>');
                var description = ('<div class="description">' + '<textarea rows="2" cols="40" id="description' + index + '">' + data[index].description + '</textarea></div>');
                var rating = ('<div class="rating" id="rating' + index + '">' + data[index].rating + '</div>');
                var editButton = ('<div class="editButton">' + '<button type="button" id="editButton' + index + '" onClick="editFunction()">Edit</button></div>');
                var deleteButton = ('<div class="deleteButton">' + '<button type="button" id="deleteButton' + index + '" onClick="deleteFunction()">Delete</button></div>');
                $('#result').append(title);
                $('#result').append(description);
                $('#result').append(rating);
                $('#result').append(editButton);
                $('#result').append(deleteButton);
             });
          }
        });
}

function editFunction() {
    alert("Edit action");
}

function deleteFunction() {
    alert("Delete action");
}