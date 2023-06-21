# MoodMentor
Aplicación móvil que cumple la función de chat bot enfocado a ser soporte emocional.  

Para que la aplicación les funcione correctmante, deben iniciar sección con la compañía de OpenAI, debido que haremos el consumo de API, para esto como se menciona, el soncumo debe se
desde su propio APIKEY.

Una vez iniciado sección en la página de OpenAIView, la parde superior derecha está el mennú de opcines y se busca el apartado de "API keys".
Una vez entrado en el apartado nos dirgimos la útima opción de API key, dentro de ello generamos nuestra APIKey y la guardamos.

Ahora dentro del código nos dirigimos a la activity llamada "MainActivity", en la línea 105 nos encontramos con los parametros de autorización:

                params["Authorization"] = //"Bearer //Aquí tu apiKey de OpenAI"
                
En vez del apartado de "Aquí tu apiKey de OpenAI" remplazamos por el key generado anteriormente.
Ejemplo

                params["Authorization"] = //"Bearer sk888a854aajaqwdI"
                
Y con esto el chatbot funcionará correctamente.

