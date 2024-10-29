public class Acteur extends Artiste{
    public String genre;
    public int nbrFilmes;

    public Acteur(int idAr,String nom,int anneDebut,String genre, int nbrFilmes){
        super(idAr,nom,anneDebut);
        this.genre = genre;
        this.nbrFilmes = nbrFilmes;
    }
    @Override
    public String toString() {
        return super.toString() + " : il est acteur de : genre = " + genre + ", nbrFilmes=" + nbrFilmes + ".";
    }
}