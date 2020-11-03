const topVideogamesList = document.getElementById("topVideogamesContainer");

document.addEventListener("DOMContentLoaded", init);

function init() {
    topVideogamesList.innerHTML = "";

    let xhr = new XMLHttpRequest();
    xhr.onloadend = function () {
        let objects = JSON.parse(xhr.responseText);
        let max = 0;
        if(objects.length < 3) max = objects.length;
        else max = 3;
        for (let i = 0; i < max; i++) {
            addNVideogame(i+1, objects[i]);
        }

    };
    xhr.open("GET", "api/videogame/getall");
    xhr.send();
}

function addNVideogame(rating, nVideogame) {
    let paragraphElement = document.createElement("p");
    let videogame = document.createElement("h3");
    videogame.innerHTML = rating.toString() + ". " + nVideogame.name;
    paragraphElement.appendChild(videogame);
    topVideogamesList.appendChild(paragraphElement);
}