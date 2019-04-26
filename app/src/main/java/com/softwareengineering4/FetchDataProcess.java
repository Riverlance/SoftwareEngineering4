package com.softwareengineering4;

import android.os.AsyncTask;

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
    String data = "";

    // Não pode alterar o UI.
    // Será executado antes do onPostExecute.
    // Esse método preenche o data com os dados recebidos do banco de dados via HTTP.
    @Override
    protected Void doInBackground(Object[] objects) {
        try {
            // Conexão a um URL
            URL url = new URL("https://api.myjson.com/bins/j5f6b");

            // Conexão HTTP que permite lermos ou escrevemos dados
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();

            // Stream que recebe os dados da conexão HTTP
            InputStream inputStream = httpURLConnection.getInputStream();

            // Nos permite ler os dados recebidos na stream
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));

            String line;
            do {
                line = bufferedReader.readLine();
                data = String.format("%s%s", data, line); // Concatena cada linha
            } while (line != null);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
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

        // Atualiza o dataTextView com os dados recebidos via HTTP
        MainActivity.dataTextView.setText(this.data);
    }
}
