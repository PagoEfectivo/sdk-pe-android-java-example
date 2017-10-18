package pe.elcomercio.pagoefectivosdkjavasample.model;

import pe.elcomercio.pagoefectivosdk.util.Currency;

/**
 * Created by tohure on 16/10/17.
 */

public class TypeCurrencyEntity extends TypeDefaultEntity {

    private Currency currency;

    public TypeCurrencyEntity(String name, Currency currency) {
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