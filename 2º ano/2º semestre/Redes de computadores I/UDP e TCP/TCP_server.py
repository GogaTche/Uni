from socket import *
serverPort = 12000      #Porta do servidor, pode ser qualquer uma na range de 1024-65535
serverSocket = socket(AF_INET,SOCK_STREAM)      #Cria a socket e guarda numa variavel
serverSocket.bind(("",serverPort))      #a socket liga-se ao server porte
serverSocket.listen(1)      #Vai meter uma fila de espera de tamanha 1
print ("The server is ready to receive")
while 1:
    connectionSocket, addr = serverSocket.accept()      #O servidor aceita qualquer pedido de entrada
    sentence = connectionSocket.recv(1024)        #Messege vai guardar a mensagem que foi recebida de um cliente com o comando "serverSocket.recvfrom(2048)", e o client adress guarda o adress do cliente que enviou a mensagem
    capitalizedSentence = sentence.upper()      #Vai meter em maiusculas a mensagem enviada pelo user
    connectionSocket.send(capitalizedSentence)      #vai mandar para o user a partir do seu adress que foi guardado, a mesangem em maiusculas
    connectionSocket.close()