package book.java_cc;

public class Account {
   private int amount = 500;
    public boolean debit(int amount){
       this.amount+=100;
       return true;
    }
    public boolean credit(int amount){
       this.amount-=100;
       return true;
    }

   public int getBalance(){
        return this.amount;
    }
}
