package nl.rsvier.sprint1.domain;

public class Adres {
   private int id;

   private String straatnaam;

   private int huisnummer;

   private String toevoeging;

   private String postcode;

   private String woonplaats;

   @Override
   public String toString() {
      StringBuilder sb = new StringBuilder("");
      sb.append("[Adres ");
      sb.append(id);
      sb.append("] ");
      sb.append(straatnaam);
      sb.append(" ");
      sb.append(huisnummer);
      sb.append(toevoeging);
      sb.append(" ");
      sb.append(postcode);
      sb.append(" ");
      sb.append(woonplaats);
      return sb.toString();
   }

   public int getId() {
      return id;
   }

   public void setId(int id) {
      this.id = id;
   }

   public String getStraatnaam() {
      return straatnaam;
   }

   public void setStraatnaam(String straatnaam) {
      this.straatnaam = straatnaam;
   }

   public int getHuisnummer() {
      return huisnummer;
   }

   public void setHuisnummer(int huisnummer) {
      this.huisnummer = huisnummer;
   }

   public String getToevoeging() {
      return toevoeging;
   }

   public void setToevoeging(String toevoeging) {
      this.toevoeging = toevoeging;
   }

   public String getPostcode() {
      return postcode;
   }

   public void setPostcode(String postcode) {
      this.postcode = postcode;
   }

   public String getWoonplaats() {
      return woonplaats;
   }

   public void setWoonplaats(String woonplaats) {
      this.woonplaats = woonplaats;
   }
}
