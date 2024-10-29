

public class Guitariste  extends  Artiste{
    public String typeGuitar;
    public int nbrMorceaux;
    public Guitariste(int idAr,String nom, int anneDebut  ,String typeGuitar,int nbrMorceaux){
        super(idAr,nom ,anneDebut);
        this.typeGuitar = typeGuitar;
        this.nbrMorceaux=nbrMorceaux;
    }
    @Override
    public String toString() {
        return super.toString()+ " il est Guitariste de type guitar=" + typeGuitar + ", nombre de morceaux=" + nbrMorceaux + "}";
    }


}
