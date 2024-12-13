📋 TodoList Sync App
Este é um aplicativo Android desenvolvido como projeto de estudo para explorar conceitos avançados de arquitetura e tecnologias modernas. O app é uma To-Do List que utiliza o protocolo MQTT para sincronizar tarefas entre diferentes dispositivos conectados ao mesmo broker MQTT. O objetivo é implementar offline-first, garantindo que os dados estejam disponíveis mesmo sem conexão à internet.

🚀 Tecnologias Utilizadas
Jetpack Compose 🖌️: Para a interface moderna e reativa.
Clean Architecture 🧼: Para organizar o código de forma escalável e modular.
MQTT 📡: Para comunicação em tempo real entre dispositivos.
Protocol Buffers (Protobuf) 🛠️: Para serialização eficiente dos dados.
Room Database 🗃️: Para armazenamento local.
WorkManager ⚙️: Para sincronização em segundo plano.
📝 Funcionalidades
Criar, editar e excluir tarefas na lista.
Sincronizar automaticamente tarefas entre dispositivos conectados ao broker MQTT.
Armazenamento local com sincronização quando a rede estiver disponível (Offline-First).
🛠️ Configuração do Ambiente
1️⃣ Configurar o Broker MQTT (HiveMQ)
Execute o comando abaixo para criar e iniciar o container Docker do HiveMQ:

bash
Copiar código
docker run --name hivemq -p 8080:8080 -p 1883:1883 hivemq/hivemq4
O port 8080 será utilizado para acessar o painel web.
O port 1883 é usado para comunicação MQTT.
2️⃣ Acessar o Cliente Web
Após iniciar o container, você pode acessar o Cliente Web para monitorar as mensagens MQTT:

Acesse http://localhost:8080 no navegador.
Use as credenciais padrão para login, se necessário (ver documentação do HiveMQ).
🖥️ Como Executar o Projeto
Clone este repositório:

bash
Copiar código
git clone https://github.com/seu-usuario/todolist-sync-app.git
Abra o projeto no Android Studio.

Execute o app em um dispositivo ou emulador configurado.

Certifique-se de que o container HiveMQ está rodando e o dispositivo/emulador consegue acessar localhost (ou o IP do servidor se em outra máquina).

🌟 Objetivos de Estudo
Este projeto foi desenvolvido para aprender e explorar os seguintes conceitos:

Comunicação MQTT: Uso do protocolo para sincronização de dados em tempo real.
Sincronização de Dados (Online e Offline): Combinação de armazenamento local e sincronização de rede.
Protobuf: Serialização eficiente para mensagens compactas e rápidas.
Jetpack Compose: Criação de UIs declarativas e modernas.
Clean Architecture: Separação de responsabilidades e camadas bem definidas.
WorkManager: Execução de tarefas em segundo plano, mesmo quando o app está fechado.
🛠️ Estrutura do Projeto
plaintext
Copiar código
📂 app
├── 📂 common        // Modelos e utilitários compartilhados
├── 📂 data          // Repositórios, DataSource e RoomDatabase
├── 📂 domain        // Modelos e Casos de Uso
├── 📂 drivers       // MQTT e WorkManager
└── 📂 ui            // Telas e componentes do Jetpack Compose

🧩 Contribuições
Sinta-se à vontade para abrir issues ou enviar pull requests para melhorias.

📜 Licença
Este projeto está licenciado sob a MIT License.

Desenvolvido com ❤️ e ☕