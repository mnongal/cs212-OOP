
public class Date212 {
   
   private int year;
   private int month;
   private int day;
   private String original;
   
   public Date212(String date) {
      
      //Program won't work if string doesn't contain digits
      try
      {
          Integer.parseInt(date);
      }
      catch (NumberFormatException ex)
      {
         throw new IllegalDate212Exception("The date must be in number format.");
      }
      
      //The different numbers of the "YYYYMMDD" format are needed in their separate variables so they can arranged in a different format.  
      year = Integer.parseInt(date.substring(0, 4));      
      month = Integer.parseInt(date.substring(4, 6));
      day = Integer.parseInt(date.substring(6, 8));
      original = date;
   }
   
   //An assortment of set and get functions so that all the variables can be acquired, including the original format date, or changed/set as needed.
   public void setDay(int dd) {
      day = dd;
   }
   
   public int getDay() {
      return day;
   }
   
   public void setMonth(int mm) {
      month = mm;
   }
   
   public int getMonth() {
      return month;
   }
   
   public void setYear(int yyyy) {
      year = yyyy;
   }
   
   public int getYear() {
      return year;
   }
   
   public String getOriginal() {
      return original;
   }
   
   public boolean equals(Object obj) {
      if(this == obj) return true;
      //If the two objects being compared are not the same object, the program checks if they are at least the same class and that one of them is not null.
      if(obj != null && this.getClass().equals(obj.getClass())) {
         Date212 ob = (Date212) obj;
         //It further checks to see if the two objects have the same date or not.
         if(year == ob.year && month == ob.month && day == ob.day) return true;
      }
      //False is returned when the two objects are different and also contain different dates.
      return false;
   }
   
   public boolean compareTo(Object obj) {
      Date212 ob = (Date212) obj;
      //The year and month differences are converted into days so that the total difference in days can be calculated between the dates of the two objects.
      int difference;
      difference = (this.year - ob.year) * 365;
      difference += (this.month - ob.month) * 30;
      difference += (this.day - ob.day);
      //If the current object is an equal or older date to the one it's being compared to that means it needs to be sorted and true is returned.
      if(difference <= 0) return true;
      else return false;
   }
   
   public String toString(int year, int month, int day) {
      //The date to be displayed needs to be in the format of "mm/dd/yyyy".
      return month + "/" + day + "/" + year;
   }
   
}