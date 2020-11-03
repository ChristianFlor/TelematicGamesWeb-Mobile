class Videogame {

    constructor(id, name, studio, yearOfRelease, numberVotes){
        this.id = id;
        this.name = name;
        this.studio = studio;
        this.yearOfRelease = yearOfRelease;
        this.numberVotes = numberVotes;
        Object.seal(this);
    }

    //metodos
    toJson(){
        //to JSON
        return JSON.stringify(this);
    }

}