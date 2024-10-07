package com.example.kotlin.mydragonballapp.data.network

import com.example.kotlin.mydragonballapp.utils.Constants
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.security.cert.CertificateException
import javax.net.ssl.*

object NetworkModuleDI {

    // Cliente HTTP configurado para aceptar todos los certificados
    private val okHttpClient: OkHttpClient = getUnsafeOkHttpClient()

    // Retorna una instancia de DragonBallAPIService
    operator fun invoke(): DragonBallAPIService {
        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL) // Asegúrate de que BASE_URL termine con "/"
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(DragonBallAPIService::class.java)
    }

    // Función para configurar un OkHttpClient que acepte todos los certificados SSL
    private fun getUnsafeOkHttpClient(): OkHttpClient {
        return try {
            // Crea un administrador de confianza que no valida los certificados SSL
            val trustAllCerts = arrayOf<TrustManager>(
                object : X509TrustManager {
                    override fun checkClientTrusted(chain: Array<java.security.cert.X509Certificate>, authType: String) {}
                    override fun checkServerTrusted(chain: Array<java.security.cert.X509Certificate>, authType: String) {}
                    override fun getAcceptedIssuers(): Array<java.security.cert.X509Certificate> = arrayOf()
                }
            )

            // Instala el "Trust Manager" que acepta todos los certificados
            val sslContext = SSLContext.getInstance("SSL")
            sslContext.init(null, trustAllCerts, java.security.SecureRandom())
            val sslSocketFactory = sslContext.socketFactory

            // Construye el cliente OkHttp
            OkHttpClient.Builder()
                .sslSocketFactory(sslSocketFactory, trustAllCerts[0] as X509TrustManager)
                .hostnameVerifier { _, _ -> true }
                .build()

        } catch (e: Exception) {
            throw RuntimeException(e)
        }
    }
}
