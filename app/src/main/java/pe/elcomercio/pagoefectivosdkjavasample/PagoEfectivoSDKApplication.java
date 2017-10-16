package pe.elcomercio.pagoefectivosdkjavasample;

import android.app.Application;

import pe.elcomercio.pagoefectivosdk.PagoEfectivoSdk;

/**
 * Created by tohure on 16/10/17.
 */

public class PagoEfectivoSDKApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        PagoEfectivoSdk pagoEfectivoSdk = PagoEfectivoSdk.inicialize(this);
        pagoEfectivoSdk.setServiceId(5);
        pagoEfectivoSdk.setAccessKey("AKIPJP77AHN2DKVIJCCA");
        pagoEfectivoSdk.setSecretKey("Nfybo8h0yN7CFN1ycX+XaG99pj/y3Vt25urt1LXM");
    }

}
