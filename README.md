# SIMULADOR DE SISTEMA DE ARQUIVOS  

**Autor**: Samuel Lucas de Araujo Farias

**Palavras-chave**: Simulador. Sistema de Arquivos. Journaling. Java. Shell.

---

### **Resumo**

Este trabalho apresenta o desenvolvimento de um simulador para compreender o funcionamento de um sistema de arquivos. O simulador, implementado em Java, permite realizar operações como criação, renomeação, remoção e cópia de arquivos e diretórios, além de implementar um sistema de journaling para garantir a integridade dos dados. Este projeto tem como objetivo facilitar o entendimento da estrutura e funcionamento de sistemas de arquivos.

---

### **Introdução**

O gerenciamento eficiente de arquivos é crucial para o funcionamento dos sistemas operacionais. Entender como os sistemas de arquivos são estruturados e organizados é essencial para compreender o funcionamento geral dos sistemas operacionais. Este projeto busca explorar esses conceitos através da implementação de um simulador de sistema de arquivos, utilizando a linguagem Java e integrando um modelo de journaling para registro e recuperação de alterações.

---

### **Metodologia**

O simulador foi desenvolvido em linguagem Java, com uma arquitetura modular composta por classes que representam arquivos, diretórios e o sistema de journaling. As operações disponíveis incluem:  
- Criação, remoção e renomeação de arquivos e diretórios.  
- Listagem do conteúdo de diretórios.  
- Registro de operações realizadas no sistema através do journaling.

A implementação incluiu as seguintes etapas:  
1. **Estruturação do Sistema de Arquivos**: Desenvolvimento das classes `File` e `Directory` para representar os elementos do sistema.  
2. **Registro de Operações**: Implementação da classe `Journal` para realizar o registro de todas as operações executadas.  
3. **Simulador de Comandos**: Implementação da classe `FileSystemSimulator`, responsável pela execução das operações.  
4. **Interface Interativa (Modo Shell)**: Desenvolvimento da classe `Shell` para interação com o usuário por meio de comandos, simulando um ambiente de linha de comando.

---

### **Resultados e Discussão**

O simulador implementado permite:  
- Manipular arquivos e diretórios de maneira estruturada.  
- Registrar operações no journaling para rastreabilidade e integridade.  
- Operar em um ambiente interativo (Shell), facilitando o aprendizado sobre sistemas de arquivos.  

Exemplo de uso no modo Shell:  
1. **Criar um diretório**: `mkdir / dir1`  
2. **Criar um arquivo**: `mkfile /dir1 file.txt txt 100`  
3. **Listar conteúdo de um diretório**: `ls /dir1`  
4. **Renomear arquivo**: `rename /dir1 file.txt file-renamed.txt`  

Os testes confirmaram a eficácia das operações e a integridade dos dados armazenados.

---

### **Conclusão**

O simulador desenvolvido demonstra a funcionalidade de um sistema de arquivos e o impacto do journaling na segurança e rastreabilidade das operações. Ele se mostrou uma ferramenta educacional eficaz, possibilitando a visualização prática de conceitos teóricos. A implementação modular em Java permite a expansão para funcionalidades mais avançadas, como tipos diferentes de journaling e simulação de cenários reais de falhas.

---

### **Execução**

Para executar o projeto, siga as instruções abaixo:  
1. Clone o repositório.  
2. Compile o projeto usando um ambiente Java (JDK 11 ou superior).  
3. Execute a classe `Main` para iniciar o simulador.  

**Bibliotecas utilizadas**: Nenhuma dependência externa além do Java SE.
