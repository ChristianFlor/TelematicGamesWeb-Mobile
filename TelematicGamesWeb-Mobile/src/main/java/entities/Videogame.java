package entities;

public class Videogame {
    private String id;
    private String name;
    private String studio;
    private String yearOfRelease;
    private int numberVotes;

    public Videogame(String id, String name, String studio, String yearOfRelease, int numberVotes) {
        this.id = id;
        this.name = name;
        this.studio = studio;
        this.yearOfRelease = yearOfRelease;
        this.numberVotes = numberVotes;
    }

    public Videogame() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStudio() {
        return studio;
    }

    public void setStudio(String studio) {
        this.studio = studio;
    }

    public String getYearOfRelease() {
        return yearOfRelease;
    }

    public void setYearOfRelease(String yearOfRelease) {
        this.yearOfRelease = yearOfRelease;
    }

    public int getNumberVotes() {
        return numberVotes;
    }

    public void setNumberVotes(int numberVotes) {
        this.numberVotes = numberVotes;
    }

}
