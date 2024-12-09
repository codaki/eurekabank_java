package com.example.eurekabank_soap_java.service;

import android.annotation.SuppressLint;
import android.os.AsyncTask;
import android.util.Log;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.PropertyInfo;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;

public class CuentaService {
    private static final String TAG = "CuentaService";
    private static final String NAMESPACE = "http://ws.monster.edu.ec/";
    private static final String METHOD_NAME = "cuenta";
    private static final String SOAP_ACTION = "http://ws.monster.edu.ec/WSCuenta/cuentaRequest";
    private static final String URL = "http://10.40.13.255:8080/EUREKABANK_SOAP_JAVA/WSCuenta";
    private static final int TIMEOUT = 15000;

    public interface CuentaCallback {
        void onSuccess(boolean result);
        void onError(String errorMessage);
    }

    @SuppressLint("StaticFieldLeak")
    // In CuentaService.java

    public void procesarOperacion(final String numeroCuenta, final double monto, final String tipoOperacion, final String cuentaDestino, final CuentaCallback callback) {
        new AsyncTask<Void, Void, AsyncTaskResult<Boolean>>() {
            @Override
            protected AsyncTaskResult<Boolean> doInBackground(Void... voids) {
                try {
                    if (tipoOperacion == null || tipoOperacion.isEmpty()) {
                        Log.e(TAG, "tipoOperacion is null or empty");
                        return new AsyncTaskResult<>(new IllegalArgumentException("tipoOperacion cannot be null or empty"));
                    }

                    SoapObject request = new SoapObject(NAMESPACE, METHOD_NAME);

                    PropertyInfo cuentaProperty = new PropertyInfo();
                    cuentaProperty.setName("cuenta");
                    cuentaProperty.setValue(numeroCuenta);
                    cuentaProperty.setType(String.class);
                    request.addProperty(cuentaProperty);

                    PropertyInfo montoProperty = new PropertyInfo();
                    montoProperty.setName("monto");
                    montoProperty.setValue(String.valueOf(monto));
                    montoProperty.setType(String.class);
                    request.addProperty(montoProperty);

                    PropertyInfo tipoOperacionProperty = new PropertyInfo();
                    tipoOperacionProperty.setName("tipo");
                    tipoOperacionProperty.setValue(tipoOperacion); // "DEP", "RET", or "TRA"
                    tipoOperacionProperty.setType(String.class);
                    request.addProperty(tipoOperacionProperty);

                    if ("TRA".equals(tipoOperacion)) {
                        PropertyInfo cuentaDestinoProperty = new PropertyInfo();
                        cuentaDestinoProperty.setName("cd");
                        cuentaDestinoProperty.setValue(cuentaDestino);
                        cuentaDestinoProperty.setType(String.class);
                        request.addProperty(cuentaDestinoProperty);
                    }

                    SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
                    envelope.setOutputSoapObject(request);
                    envelope.dotNet = false;

                    HttpTransportSE transport = new HttpTransportSE(URL, TIMEOUT);
                    transport.debug = true;

                    transport.call(SOAP_ACTION, envelope);
                    Object response = envelope.getResponse();
                    boolean result = Boolean.parseBoolean(response.toString());

                    return new AsyncTaskResult<>(result);
                } catch (Exception e) {
                    Log.e(TAG, "Request preparation failed", e);
                    return new AsyncTaskResult<>(e);
                }
            }

            @Override
            protected void onPostExecute(AsyncTaskResult<Boolean> result) {
                if (result.getError() != null) {
                    callback.onError(result.getError().getMessage());
                } else {
                    callback.onSuccess(result.getResult());
                }
            }
        }.execute();
    }

    // Utility class for handling AsyncTask results (same as in other services)
    private static class AsyncTaskResult<T> {
        private T result;
        private Exception error;

        AsyncTaskResult(T result) {
            this.result = result;
        }

        AsyncTaskResult(Exception error) {
            this.error = error;
        }

        T getResult() {
            return result;
        }

        Exception getError() {
            return error;
        }
    }
}