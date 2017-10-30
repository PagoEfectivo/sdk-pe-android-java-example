package pe.elcomercio.pagoefectivosdkjavasample.model;

/**
 * Created by tohure on 17/10/17.
 */

@SuppressWarnings({"ALL", "DefaultFileTemplate"})
public class PaymentMethodEntity {

    private int type;
    private String name;

    public PaymentMethodEntity(int type, String name) {
        this.type = type;
        this.name = name;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
