const videogamesContainer = document.getElementById("videogamesContainer");

document.addEventListener("DOMContentLoaded", loadAllVideogames);

let totalVotes = 0;

function loadAllVideogames() {
    videogamesContainer.innerHTML = "";

    let xhr = new XMLHttpRequest();
    xhr.onloadend = function () {
        var objects = JSON.parse(xhr.responseText);
        console.log(objects);
        totalVotes = 0;
        for(let i=0 ; i<objects.length ; i++){
            let nVideogame = objects[i];
            totalVotes += nVideogame.numberVotes;
        }
        console.log(totalVotes);
        for(let i=0 ; i<objects.length ; i++){
            let nVideogame = objects[i];
            createVideogameBTN(nVideogame);
        }

    };
    xhr.open("GET", "api/videogame/getall");
    xhr.send();

}

function createVideogameBTN(nVideogame) {
    var id = nVideogame.id;
    var name = nVideogame.name;
    var numberVotes = nVideogame.numberVotes;
    var percentage = 0;
    console.log(id);
    if(totalVotes > 0) percentage = 100*parseInt(numberVotes)/totalVotes;
    var paragraphElement = document.createElement("p");
    var btn = document.createElement("button");
    btn.id = id;
    btn.innerHTML = name + " (" + percentage.toString() + "%. " + numberVotes.toString() + " votos)";
    paragraphElement.appendChild(btn);
    videogamesContainer.appendChild(paragraphElement);
    var HTMLBTN = document.getElementById(id);

    HTMLBTN.addEventListener("click", function () {
        var xhr = new XMLHttpRequest();
        xhr.onloadend = function () {
            alert("Votaste exitosamente por " + name);
            loadAllVideogames();
        };
        xhr.open("PUT", "api/videogame/vote/"+id);
        xhr.send();
    });

}