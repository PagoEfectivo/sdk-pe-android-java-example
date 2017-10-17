package pe.elcomercio.pagoefectivosdkjavasample;

import pe.elcomercio.pagoefectivosdk.util.Currency;

/**
 * Created by tohure on 16/10/17.
 */

class TypeCurrency extends TypeDefault {

    private Currency currency;

    TypeCurrency(String name, Currency currency) {
        super(name);
        this.currency = currency;
    }

    Currency getCurrency() {
        return currency;
    }

    public void setCurrency(Currency currency) {
        this.currency = currency;
    }
}