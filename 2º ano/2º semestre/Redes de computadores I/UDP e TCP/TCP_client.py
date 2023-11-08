from socket import *
serverName = "192.168.91.1"        #"Nome" do servidor que queremos contatar
serverPort = 12000      #Porta do servidor, pode ser qualquer uma na range de 1024-65535
clientSocket = socket(AF_INET, SOCK_STREAM)     #Cria a socket e guarda numa variavel
clientSocket.connect((serverName,serverPort))       #A socket do cliente vai pedir acesso ao servidor, e se ele aceitar conecta-se
sentence = input("Input lowercase sentence:")
clientSocket.send(sentence.encode())        #Vai mandar a mensagem em bytes(message.encode()) introduzida no input pelo user para o servidor que ja esta conectado
modifiedSentence = clientSocket.recv(1024)      #Vai receber a mensagem do servidor
print ("From Server:", modifiedSentence.decode())       #decode() vai meter a mensagem em bytes de volta para uma string
clientSocket.close()