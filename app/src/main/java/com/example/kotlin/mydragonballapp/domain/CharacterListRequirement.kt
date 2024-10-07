import android.util.Log
import com.example.kotlin.mydragonballapp.data.network.NetworkModuleDI
import com.example.kotlin.mydragonballapp.data.network.model.CharactersObject

class CharacterListRequirement {
    private val apiService = NetworkModuleDI()

    suspend operator fun invoke(page: Int, limit: Int): CharactersObject? {
        return try {
            val response = apiService.getCharacterList(page, limit)
            if (response.isSuccessful) {
                response.body()
            } else {
                // Log o manejar errores aquí
                Log.e("API_ERROR", "API call failed: ${response.code()} - ${response.message()}")
                null
            }
        } catch (e: Exception) {
            // Log o manejar excepciones aquí
            Log.e("API_EXCEPTION", "Exception occurred: ${e.message}")
            null
        }
    }
}
