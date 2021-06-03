document.addEventListener("DOMContentLoaded", init, false);

let bookURL = "/book";

function init() {
    getBook(0); 
}


function getBook(id) {
     const request = new XMLHttpRequest();
     request.open("GET", "/book");

     request.onload = function () {
         var divList = "";
        const response = JSON.parse(this.responseText);
         $.each(response, function(index, item) { 
             divList += createListItem(item);
        });
       document.getElementById('list').innerHTML = divList;
    }
    
  request.send();
}


function createListItem(item){
    var div = `<div class="book">
        <div class="info">
            <b>${item.title}</b>
            <b>${item.author}</b>
            <b>${item.status}</b>
        </div>
        <div class="action">
        	<button onclick="editStatus('${item.id}')">
                <i class="fa fa-undo"></i>      
            </button>          
            <button onclick="editBook('${item.id}')">
                <i class="fa fa-pencil"></i>
            </button>
            <button onclick="deleteBook('${item.id}')">
                <i class="fa fa-trash"></i>
            </button>
        </div>
    </div>`;
    return div;
}


function addBook(){
    clearBook();
    document.getElementById("title-modal").innerHTML = "Adicionar Livro";
    document.getElementById("modal").style.display = "block";
    document.getElementById("form").setAttribute("method", "POST");
}

function closeModal(){
    document.getElementById("modal").style.display = "none";
}

function foundBook(id){

      const request = new XMLHttpRequest();
     request.open("GET", `/book?bookId=${id}`);

     request.onload = function () {
        const response = JSON.parse(this.responseText);
        console.log(response);
        document.getElementById("book-id").value = response.id;
        document.getElementById("book-id").readOnly = true;
        document.getElementById("book-title").value = response.title;
        document.getElementById("book-author").value = response.author;
        document.getElementById("book-status").value = response.status;
    }
    
  request.send();
  
}
function editBook(id){
    clearBook();
    foundBook(id);
    document.getElementById("title-modal").innerHTML = "Editar Livro";
    document.getElementById("modal").style.display = "block";
}

function deleteBook (id){
    Swal.fire({
        title: 'Tem certeza?',
        text: "Nao sera possivel reverter!",
        icon: 'warning',
        showCancelButton: true,
        confirmButtonColor: '#3085d6',
        cancelButtonColor: '#d33',
        confirmButtonText: 'Sim!',
        cancelButtonText: 'Cancelar'
      }).then((result) => {
        if (result.isConfirmed) {
            const request = new XMLHttpRequest();
 	      request.open("DELETE", `/book?bookId=${id}`);
		  request.setRequestHeader("Content-type", "application/json; charset=utf-8");
 	      request.onload = function () {
 	    	  
 	      }
 	     request.onerror = function () {
 	        alert("erro ao executar a requisi��o");
 	      };
 	      request.send();
              
          Swal.fire(
            'Deletado!',
            '',
            'success'
          )
  
        location.reload();
        }
      })
}

function clearBook(){
    document.getElementById("book-id").value = "";
    document.getElementById("book-title").value = "";
    document.getElementById("book-author").value = "";
}

function saveBook(){
    getBook(0);
    closeModal();
}

function editStatus(id){
	Swal.fire({
        title: 'Este livro ja foi devolvido?',
        text: "Confirmar devolucao!",
        icon: 'warning',
        showCancelButton: true,
        confirmButtonColor: '#3085d6',
        cancelButtonColor: '#d33',
        confirmButtonText: 'Sim!',
        cancelButtonText: 'Cancelar'
      }).then((result) => {
        if (result.isConfirmed) {
            const request = new XMLHttpRequest();
 	      request.open("PUT", `/book?bookId=${id}`);
		  request.setRequestHeader("Content-type", "application/json; charset=utf-8");
 	      request.onload = function () {
 	    	  
 	      }
 	     request.onerror = function () {
 	        alert("erro ao executar a requisi��o");
 	      };
 	      request.send();
              
          Swal.fire(
            'Devolvido!',
            '',
            'success'
          )
  
        location.reload();
        }
      })
}