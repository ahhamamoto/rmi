# Projeto WriteBoard - Descrição

O projeto WriteBoard é uma simulação de um quadro de sala de aula, onde várias pessoas 
podem fazer uso do quadro e todas as outras pessoas da sala visualizam essas alterações. 
Existem várias soluções comerciais que implementam esse tipo de projeto.

O projeto WriteBoard permite que várias pessoas, cada uma em seu computador, compartilhe 
o mesmo quadro, ou seja, qualquer alteração realizada no quadro por um dos participantes 
deve ser exibida para todos os usuários que compartilham o mesmo quadro.

Para simplificar o projeto, o quadro aceitará apenas o desenho de linhas, sendo que cada 
usuário do quadro aparece em um cor diferente.

Fase 1: Desenvolver o projeto WriteBoard, contendo módulos cliente e servidor. 
Um servidor pode abrigar um conjunto de quadros, cada quadro com seu conjunto de usuários. 
O módulo cliente permite que o usuário inicie um quadro em um servidor ou solicite a entrada 
em um quadro já existente, para isso deve saber a identificação do quadro e o endereço do servidor.

O usuário pode solicitar a saída de um quadro (um quadro sem usuários deve ser removido do servidor).

Fase 2: Implementar um módulo denominado wbAdmin, que realiza solicitações a servidores para:
* Obter informações: o servidor selecionado deve fornecer informações sobre a identificação 
	dos quadros ativos e a identificação (nome) das máquinas clientes de cada quadro;
* Transferência de Quadros: o servidor deve transferir um determinado quadro (e todos os 
	usuários associados ao quadro) para outro servidor. Essa transferência deve ser transparente 
	aos usuários cliente. O wbAdmin deve enviar a identificação do quadro e do servidor destino (endereço IP).
	
Entregar zip de diretório (html) gerado pelo doxigen. Instalar pacote doxygen e graphviz. 
Gerar documentação no doxygen: doxygen doxygen_log.txt

Avaliação:
* Fase 1: 0 a 4 pontos
* Fase 2: 0 a 5 pontos
* Arquivos de Documentação (gerado pelo doxygen): 0 a 1 ponto

Trabalho pode ser realizado em grupos com dois integrantes. 
Submeter trabalho na próxima tarefa em aberto (WriteBoard).