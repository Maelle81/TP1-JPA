package monprojet.dao;
import java.util.List;

import javax.persistence.Tuple;
import org.springframework.data.jpa.repository.JpaRepository;
import monprojet.entity.Country;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
// This will be AUTO IMPLEMENTED by Spring 
//

public interface CountryRepository extends JpaRepository<Country, Integer> {
    //Une méthode qui, pour un pays dont l'ID est passé en paramètre, calcule sa population (somme des populations des villes)
    //@Query("SELECT ligne.produit.nom as nom, SUM(ligne.quantite) AS unites »
    //+ "FROM Ligne ligne "
    //+ "WHERE ligne.produit.categorie.code = :codeCategorie "
    //+ "GROUP BY nom ")
    //public List<UnitesParProduit> produitsVendusPour(Integer codeCategorie);

    
    @Query(value = "SELECT SUM(CITY.POPULATION)"
    +"FROM COUNTRY"
    +"INNER JOIN CITY ON COUNTRY.ID=CITY.COUNTRY_ID"
    +"WHERE COUNTRY.ID = : idDeCountry",
    nativeQuery = true)
    public int paysQuiExisteDeja(int idDeCountry);

    //Une méthode sans paramètre, qui renvoie une liste (nom du pays, population)
    @Query (value = "SELECT COUNTRY.NAME, SUM(CITY.POPULATION)"
    +"FROM COUNTRY"
    +"INNER JOIN CITY ON (COUNTRY.ID=CITY.COUNTRY_ID)"
    +"GROUP BY COUNTRY.NAME",
    nativeQuery = true)
    public List<Country> listePaysPopulation();

    
    
}
