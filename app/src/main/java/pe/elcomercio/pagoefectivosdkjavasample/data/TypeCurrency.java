package pe.elcomercio.pagoefectivosdkjavasample.data;

import pe.elcomercio.pagoefectivosdk.util.Currency;

/**
 * Created by tohure on 16/10/17.
 */

public class TypeCurrency extends TypeDefault {

    private Currency currency;

    public TypeCurrency(String name, Currency currency) {
        super(name);
        this.currency = currency;
    }

    public Currency getCurrency() {
        return currency;
    }

    public void setCurrency(Currency currency) {
        this.currency = currency;
    }
}