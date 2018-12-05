package Stock;


import exceptions.Commonexception;
import exceptions.Finexception;
import exceptions.Techexception;


import java.util.ArrayList;
import java.util.Objects;
import java.util.Observable;

public abstract class NSDQStock extends Observable {
    public String stockname;
    public double price;
    public ArrayList<Double> prices;
    private int shares;
    public double result;
    public String categories;



    //EFFECTS: prices is empty
    public NSDQStock(){
        prices = new ArrayList<>();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        NSDQStock nsdqStock = (NSDQStock) o;
        return Objects.equals(stockname, nsdqStock.stockname);
    }

    @Override
    public int hashCode() {

        return Objects.hash(stockname);
    }




    public void setStock(String stockname) {
        this.stockname = stockname;
    }

    //MODIFIES: this
    //EFFECTS: set result

    public void setResult(double result){
        this.result=result;
    }
    //MODIFIES: this
    //EFFECTS: set the shares

    public void setShares(Integer shares) {
        this.shares = shares;
    }

    //MODIFIES: this
    //EFFECTS: add price to the prices

    public void addPrice(double price){
        prices.add(price);
        setChanged();
        notifyObservers(this);
    }

    public void setCategories(String string){
        this.categories = string;
    }


    //EFFECTS: calculate the total value of the company
    //by multipling the latest stock price to the shares;

    public double EV() {
        return (shares * prices.get(prices.size()-1));
    }


    public abstract String stockcategory() throws Finexception, Commonexception, Techexception;


}
