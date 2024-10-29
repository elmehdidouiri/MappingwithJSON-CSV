public class Artiste {

    public String nom;
    public int idArtiste;
    public int anneDebut;


    public Artiste( int idArtiste) {
        this.idArtiste = idArtiste;
    }
    public Artiste( int idArtiste,String nom,int anneDebut) {
        this.idArtiste = idArtiste;
        this.anneDebut=anneDebut;
        this.nom=nom;
    }

        @Override
        public String toString() {
            return "Artiste {" + "idArtist=" + idArtiste + ", nom=" + nom + ", anneeDebut=" + anneDebut+ "}";
        }

    }



