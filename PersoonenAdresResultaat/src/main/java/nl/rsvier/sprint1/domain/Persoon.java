package nl.rsvier.sprint1.domain;

import nl.rsvier.sprint1.domain.Adres;
import nl.rsvier.sprint1.domain.Resultaat;

public class Persoon {
   private int id;

   private String voornaam;

   private String achternaam;

   private String tussenvoegsel;

   private String geboortedatum;

   private Adres adres;

   private Resultaat[] resultaten;

   @Override
   public String toString() {
      StringBuilder sb = new StringBuilder("");
      sb.append("[Persoon ");
      sb.append(id);
      sb.append("] ");
      sb.append(voornaam);
      sb.append(" ");
      if(tussenvoegsel.length() > 0){
         sb.append(tussenvoegsel);
         sb.append(" ");
      }
      sb.append(achternaam);
      sb.append(" ");
      sb.append(geboortedatum);
      sb.append(" ");
      sb.append(adres.toString());
      return sb.toString();
   }

   public int getId() {
      return id;
   }

   public void setId(int id) {
      this.id = id;
   }

   public String getVoornaam() {
      return voornaam;
   }

   public void setVoornaam(String voornaam) {
      this.voornaam = voornaam;
   }

   public String getAchternaam() {
      return achternaam;
   }

   public void setAchternaam(String achternaam) {
      this.achternaam = achternaam;
   }

   public String getTussenvoegsel() {
      return tussenvoegsel;
   }

   public void setTussenvoegsel(String tussenvoegsel) {
      this.tussenvoegsel = tussenvoegsel;
   }

   public String getGeboortedatum() {
      return geboortedatum;
   }

   public void setGeboortedatum(String geboortedatum) {
      this.geboortedatum = geboortedatum;
   }

   public Adres getAdres() {
      return adres;
   }

   public void setAdres(Adres adres) {
      this.adres = adres;
   }

   public Resultaat[] getResultaten() {
      return resultaten;
   }

   public void setResultaten(Resultaat[] resultaten) {
      this.resultaten = resultaten;
   }
}
