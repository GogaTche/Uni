from socket import *
serverName = "192.168.91.1"     #"Nome" do servidor que queremos contatar
serverPort = 12000      #Porta do servidor, pode ser qualquer uma na range de 1024-65535
clientSocket = socket(AF_INET, SOCK_DGRAM)      #Cria a socket e guarda numa variavel
message = input("Input lowercase sentence:")
clientSocket.sendto(message.encode(),(serverName, serverPort))      #Vai mandar a mensagem em bytes(message.encode()) introduzida no input pelo user para o servidor a partir do IP
modifiedMessage, serverAddress = clientSocket.recvfrom(2048)        #Vai receber a mensagem do servidor
print (modifiedMessage.decode())        #decode() vai meter a mensagem em bytes de volta para uma string
clientSocket.close()        