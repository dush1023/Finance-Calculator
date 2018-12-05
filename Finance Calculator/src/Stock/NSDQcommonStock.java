package Stock;

import exceptions.Commonexception;

public class NSDQcommonStock extends NSDQStock {

    @Override
    public String stockcategory() throws Commonexception {
        if (stockname.equals("common0")){
            throw new Commonexception();
        }
        return ("This is a Common company");
    }

}
