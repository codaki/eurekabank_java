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
    private static final String URL = "http://192.168.18.8:8080/EUREKABANK_SOAP_JAVA/WSCuenta";
    private static final int TIMEOUT = 15000;

    public interface CuentaCallback {
        void onSuccess(boolean result);
        void onError(String errorMessage);
    }

    @SuppressLint("StaticFieldLeak")
    public void procesarCuenta(final String numeroCuenta, final double monto, final CuentaCallback callback) {
        new AsyncTask<Void, Void, AsyncTaskResult<Boolean>>() {
            @Override
            protected AsyncTaskResult<Boolean> doInBackground(Void... voids) {
                try {
                    // Create SOAP request
                    SoapObject request = new SoapObject(NAMESPACE, METHOD_NAME);

                    // Add account number property
                    PropertyInfo cuentaProperty = new PropertyInfo();
                    cuentaProperty.setName("cuenta");
                    cuentaProperty.setValue(numeroCuenta);
                    cuentaProperty.setType(String.class);
                    request.addProperty(cuentaProperty);

                    // Add amount property
                    PropertyInfo montoProperty = new PropertyInfo();
                    montoProperty.setName("monto");
                    montoProperty.setValue(String.valueOf(monto));
                    montoProperty.setType(String.class);
                    request.addProperty(montoProperty);

                    // Configure SOAP envelope
                    SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
                    envelope.setOutputSoapObject(request);
                    envelope.dotNet = false;

                    // Configure transport
                    HttpTransportSE transport = new HttpTransportSE(URL, TIMEOUT);
                    transport.debug = true;

                    try {
                        // Make SOAP call
                        transport.call(SOAP_ACTION, envelope);

                        // Get response
                        Object response = envelope.getResponse();

                        // Get the boolean return value
                        boolean result = Boolean.parseBoolean(response.toString());

                        Log.d(TAG, "Response: " + result);
                        return new AsyncTaskResult<>(result);

                    } catch (Exception e) {
                        Log.e(TAG, "SOAP call failed", e);
                        return new AsyncTaskResult<>(e);
                    }
                } catch (Exception e) {
                    Log.e(TAG, "Request preparation failed", e);
                    return new AsyncTaskResult<>(e);
                }
            }

            @Override
            protected void onPostExecute(AsyncTaskResult<Boolean> result) {
                if (result.getError() != null) {
                    String errorMessage = "Error: " + result.getError().getMessage();
                    Log.e(TAG, errorMessage);
                    callback.onError(errorMessage);
                } else {
                    Log.d(TAG, "Success! Result: " + result.getResult());
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