package Server;

/**
 * Created by Windows on 25.02.2017.
 */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.SocketException;

public class ClientHandler extends Thread {
    private Socket socket;
    private SenderWorker senderWorker;
    private ServerExample serverExample;
    //Имя клиента
    private String nickName;
    private boolean isRegistered = false;

    public ClientHandler(ServerExample serverExample, Socket socket, int id) {
        this.serverExample = serverExample;
        this.socket = socket;
        //Временное имя клиента
        nickName = ""+id;
    }

    public String getNickName() {
        return nickName;
    }

    @Override
    public void run() {
        try {
            //Создание читателей, писателей из потока
            BufferedReader reader = new BufferedReader(
                    new InputStreamReader(socket.getInputStream()));
            PrintWriter writer = new PrintWriter(socket.getOutputStream());
            //Создание отправителя в отдельном потоке
            senderWorker = new SenderWorker(writer, serverExample.gson);
            senderWorker.start();

            String inputMessage;

            inputMessage = "Здравствуйте. Введите ваше имя для чата";
            sendMessage("Сервер",nickName,inputMessage);

            //Регистрация имени польователя
            while (!isRegistered){

                inputMessage = reader.readLine();
                Message message = serverExample.gson.fromJson(inputMessage, Message.class);
                inputMessage = message.getBody();

                //Проверяем имя на уникальность
                if(serverExample.isNameFree(inputMessage)){
                    nickName = inputMessage;
                    inputMessage = "Поздравляем. Ваше имя в чате: "+inputMessage;
                    sendMessage("Сервер",nickName,inputMessage);
                    isRegistered=true;
                }
                else{
                    inputMessage = "Извините, но имя уже занято. Введите новое ";
                    sendMessage("Сервер",nickName,inputMessage);
                }
            }
            inputMessage=nickName+" присоединился к нашему чату";
            //Отправка сообщения всем
            serverExample.multiSendWorker.addMessage(new Message("Сервер",null,inputMessage));

            boolean socketNotNull = true;
            //Чтение сокета клиента и получение от него сообщений
            while (socketNotNull){

                try{
                    inputMessage = reader.readLine();
                    if(!(inputMessage==null)){
                        serverExample.parseMessage(inputMessage,this);
                    }

                }
                catch (SocketException e){

                    inputMessage="Пользователь "+nickName+" отключился от чата";
                    serverExample.multiSendWorker.addMessage(new Message("Сервер",null,inputMessage));
                    socketNotNull = false;
                }


            }
            //Удаление пользователя из коллекции. Закрытие потока отправителя(senderWorker),
            //(writer), (reader) и сокета
            serverExample.disconnectClient(this);
            senderWorker.stopWorker();
            writer.close();
            reader.close();
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //Отправка сообщения клиенту
    public void sendMessage(String sender,String receiver, String body) {

        Message message = new Message(sender,receiver,body);

        senderWorker.addMessage(message);

    }


}
