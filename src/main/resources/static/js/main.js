$(document).ready(function () {

    displayAllBooksReviews();

    $("#addForm").submit(function(event) {

        event.preventDefault();

        $.ajax({
          url: "reviews",
          type: "POST",
          data: JSON.stringify({ title: $("#titleInput").val(), description: $("#descriptionArea").val(), rating: $("#ratingInput").val()}),
          contentType: "application/json; charset=utf-8",
          dataType: "json",
          success: function(){
            alert("Review saved");
          }
        });

    });
});

function displayAllBooksReviews() {
        $.ajax({
          type: "GET",
          url: "/reviews",
          success: function(data){
             console.log(data);
             $.each (data, function (index) {
                var title = ('<div class="title" id="title' + index + '">' + data[index].title + '</div>');
                var description = ('<div class="description">' + '<textarea rows="2" cols="35" id="description' + index + '">' + data[index].description + '</textarea></div>');
                var rating = ('<div class="rating"><input type="text" id="rating' + index + '" value="' + data[index].rating + '" size="2"></div>');
                var editButton = ('<div class="editButton">' + '<button type="button" id="editButton' + index + '" onclick="editFunction(' + index + ')">Edit</button></div>');
                var deleteButton = ('<div class="deleteButton">' + '<button type="button" id="deleteButton' + index + '" onclick="deleteFunction(' + index + ')">Delete</button></div>');
                $('#result').append(title);
                $('#result').append(description);
                $('#result').append(rating);
                $('#result').append(editButton);
                $('#result').append(deleteButton);
             });
          }
        });
}


function editFunction(index) {

        $.ajax({
            url: "reviews",
            type: "PUT",
            data: JSON.stringify({ title: $("#title" + index).text(), description: $("#description" + index).val(), rating: $("#rating" + index).val()}),
            contentType: "application/json; charset=utf-8",
            dataType: "json",
            success: function(){
              console.log("Review updated");
            }
         });

}

function deleteFunction(index) {
        $.ajax({
             url: "reviews/" + $("#title" + index).text(),
             type: "DELETE",
             success: function(){
                 console.log("Review deleted");
             }
         });
}