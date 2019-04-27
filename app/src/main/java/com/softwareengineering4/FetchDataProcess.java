package com.softwareengineering4;

import android.os.AsyncTask;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/*
    Essa classe foi criada porque o Android não permite que tarefas em background sejam executadas
    junto à thread do Main activity.

    Se essa tarefa fosse implementada junto à Main Activity, a execução dessa tarefa poderia
    travar o aplicativo ou até crashar o aplicativo inteiro. Mas, sendo em outra thread (como
    estamos fazendo a partir dessa classe), isso não ocorrerá porque a execução dessa tarefa
    estará em outra thread.
*/
public class FetchDataProcess extends AsyncTask
{
    // Não pode alterar o UI.
    // Será executado antes do onPostExecute.
    // Esse método preenche o data com os dados recebidos do banco de dados via HTTP.
    @Override
    protected Void doInBackground(Object[] objects) {
        try {
            // Conexão a um URL
            URL url = new URL("https://randomuser.me/api/?nat=br&results=25"); // https://api.myjson.com/bins/j5f6b

            // Conexão HTTP que permite lermos ou escrevemos dados
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();

            // Stream que recebe os dados da conexão HTTP
            InputStream inputStream = httpURLConnection.getInputStream();

            // Nos permite ler os dados recebidos na stream
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));

            // Passando os dados para a variável data
            String data = "";
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                data = String.format("%s%s", data, line); // Concatena cada linha
            }

            // Limpa lista
            MainActivity.personListItemAdapter.personListItems.clear();

            // Parsing JSONArray to PersonListItem
            JSONArray jsonArray = (new JSONObject(data)).getJSONArray("results");
            if (jsonArray != null) {
                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject jsonObject = (JSONObject) jsonArray.get(i);

                    /* Dados principais necessários para criar um objeto Person */
                    // Name
                    JSONObject jsonObjectName = (JSONObject) jsonObject.get("name");
                    String nameTitle = jsonObjectName.getString("title");
                    String nameFirst = jsonObjectName.getString("first");
                    String nameLast = jsonObjectName.getString("last");
                    // Username
                    String username = ((JSONObject) jsonObject.get("login")).getString("username");
                    // Age
                    short age = Short.parseShort(((JSONObject) jsonObject.get("dob")).getString("age"));
                    // Person (criando um objeto Person)
                    PersonListItem personListItem = new PersonListItem(nameTitle, nameFirst, nameLast, username, age);

                    /* Other data of Person */
                    // Gender
                    personListItem.gender = jsonObject.getString("gender").equals("male") ? PersonListItem.GENDER_MALE : PersonListItem.GENDER_FEMALE;
                    // Location
                    JSONObject jsonObjectLocation = (JSONObject) jsonObject.get("location");
                    personListItem.locationStreet = jsonObjectLocation.getString("street");
                    personListItem.locationCity = jsonObjectLocation.getString("city");
                    personListItem.locationState = jsonObjectLocation.getString("state");
                    // Email
                    personListItem.email = jsonObject.getString("email");
                    // Phone
                    personListItem.phone = jsonObject.getString("phone");
                    // Picture
                    JSONObject jsonObjectPicture = (JSONObject) jsonObject.get("picture");
                    personListItem.pictureLargeURL = jsonObjectPicture.getString("large");
                    personListItem.pictureMediumURL = jsonObjectPicture.getString("medium");
                    personListItem.pictureThumbnailURL = jsonObjectPicture.getString("thumbnail");

                    // Adiciona item na lista
                    MainActivity.personListItemAdapter.personListItems.add(personListItem);
                }
            }

        // Não foi possível acessar a URL / URL mal formada
        } catch (MalformedURLException e) {
            e.printStackTrace();
        // Erro de Entrada/Saída
        } catch (IOException e) {
            e.printStackTrace();
        // Erro de JSON (conversão de dados de JSON para Object)
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

    // Pode alterar o UI.
    // Será executado depois do doInBackground.
    // Sendo assim, pegamos os dados em doInBackground e atualizamos o layout aqui.
    @Override
    protected void onPostExecute(Object o) {
        super.onPostExecute(o);

        // Notifica que a lista foi alterada
        MainActivity.personListItemAdapter.notifyDataSetChanged();
    }
}
