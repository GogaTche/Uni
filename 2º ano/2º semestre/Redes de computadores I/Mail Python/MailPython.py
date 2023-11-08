from socket import *
# Mensagem a ser enviada
msg = '\r\nRedes de Computadores e fixe!'
endmsg = '\r\n.\r\n'
# Servidor de email
mailserver = '10.10.23.49'
portnumber = 25
# Criacao de socket e estabelecimento de conexao TCP com mailserver
clientSocket = socket(AF_INET, SOCK_STREAM)
clientSocket.connect((mailserver, portnumber))
# Analise da primeira resposta do servidor
print(clientSocket.recv(1024).decode())
# Envio do comando HELO para servidor
clientSocket.send('HELO mekie\r\n'.encode())
print(clientSocket.recv(1024).decode())
# Envio do comando MAIL FROM para servidor
clientSocket.send('MAIL FROM: a76943@ualg.pt\r\n'.encode())
print(clientSocket.recv(1024).decode())
# Envio do comando RCPT TO para servidor
clientSocket.send('RCPT TO: a75555@ualg.pt\r\n'.encode())
print(clientSocket.recv(1024).decode())
# Envio do comando DATA para servidor
clientSocket.send('DATA 30 de Fevereiro 2067\r\n'.encode())
print(clientSocket.recv(1024).decode())
# Envio da mensagem para servidor
clientSocket.send(('Subject: ' + msg + endmsg).encode())
print(clientSocket.recv(1024).decode())
# Envio do comando QUIT para servidor
clientSocket.send('QUIT\r\n'.encode())
print(clientSocket.recv(1024).decode())
# Fim do programa