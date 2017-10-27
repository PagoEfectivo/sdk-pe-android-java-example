package pe.elcomercio.pagoefectivosdkjavasample;

import android.app.Application;

import pe.elcomercio.pagoefectivosdk.PagoEfectivoSdk;

/**
 * Created by Tohure and CarlitosDroid on 16/10/17.
 */

public class PagoEfectivoSDKApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        PagoEfectivoSdk pagoEfectivoSdk = PagoEfectivoSdk.inicialize(this);
        pagoEfectivoSdk.setServiceId(10);
        pagoEfectivoSdk.setAccessKey("AKIPJP77AHN2DKVIJPR1");
        pagoEfectivoSdk.setSecretKey("Nfybo8h0yN7CFN1ycX+XaG99pj/y3Vt25urt1PR1");
    }

}
