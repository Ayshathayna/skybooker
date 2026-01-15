# ✈️ Sistema de Reservas de Voos – Java  
Projeto acadêmico desenvolvido em Java para gerenciamento de **voos**, **passageiros**, **funcionários** e **reservas** utilizando um menu interativo no console.

---

## 📌 Sumário
- [Sobre o Projeto](#-sobre-o-projeto)
- [Funcionalidades](#-funcionalidades)
- [Estrutura do Projeto](#-estrutura-do-projeto)
- [Como Executar](#️-como-executar)
- [Detalhamento das Classes](#-detalhamento-das-classes)
- [Tecnologias Utilizadas](#-tecnologias-utilizadas)

---

## 📘 Sobre o Projeto
Este projeto foi desenvolvido com o objetivo de simular o funcionamento básico de um sistema de reservas de voos.  
O sistema opera completamente via console, permitindo:

- Gerenciamento de passageiros  
- Criação e controle de voos  
- Reserva e cancelamento de assentos  
- Consulta de dados e histórico de reservas  

Ele utiliza listas internas em memória (sem banco de dados) e validações simples via Java.

---

## ✔️ Funcionalidades

### ⭐ **1. Reservar voo**
- Solicita origem, destino e data
- Cria voo automaticamente se não existir
- Solicita CPF e valida cadastro
- Exibe mapa de assentos(com assentos livres e reservados)
- Reserva cadeira e gera ID de reserva

---

### ⭐ **2. Cancelar reserva**
- Consulta pelo CPF
- Lista reservas
- Permite cancelamento por ID
- Libera a cadeira no voo

---

### ⭐ **3. Consultar reservas**
- Busca passageiro pelo CPF
- Lista todas as reservas cadastradas
- Exibe informações completas do voo

---

### ⭐ **4. Cadastrar Passageiro**
- Adiciona novo passageiro com CPF e nome
- Valida duplicidade

---

### ⭐ **5. Gerenciar Funcionários**
A classe `Funcionario` permite:
- Exibir dados completos do funcionário
- Listar reservas associadas

---

## 📂 Estrutura do Projeto
```bash

src/
└── trabalho/
├── menu/
│    └── Menu.java
├── passageiro/
│    └── Passageiro.java
├── pessoa/
│    └── Pessoa.java
├── reserva/
│    └── Reserva.java
├── voo/
│    └── Voo.java
└── funcionario/
     └── Funcionario.java
```

---



## ▶️ Como Executar

### **1. Clonar o repositório**
```bash
git clone https://github.com/seuusuario/seurepositorio.git
```
### 2. Abrir no terminal e compilar
Dentro da pasta src:

```bash
javac trabalho/menu/Menu.java
```

### 3. Executar
```bash
java trabalho.menu.Menu
```

## 🧩 Detalhamento das Classes
### 📌 Menu.java
Controla todo o fluxo do sistema:  
Apresenta o menu principal  
Recebe e valida entradas do usuário  
Chama funções de reserva, cancelamento, cadastro, etc.  

### 📌 Voo.java
Gerencia informações do voo:  
Origem, destino e data  
Número identificador  
Controle de assentos (20 poltronas)  
Verificação de disponibilidade  
Cancelamento e reserva de assentos  
 
### 📌 Passageiro.java
Armazena informações:  
Nome  
CPF  
Lista de reservas vinculadas  
Adiciona e cancela reservas  
Exibe dados completos do passageiro  

### 📌 Reserva.java
Representa uma reserva individual contendo:  
ID da reserva  
Data do voo  
Número da poltrona  
Referência ao objeto Voo  
Passageiro associado  

### 📌 Pessoa.java
Classe base para Passageiro e Funcionário contendo:  
Nome    
CPF  

### 📌 Funcionario.java
Herda de Pessoa e adiciona:  
Número de cadastro  
Lista separada de reservas  
Relatório de reservas feitas pelo funcionário  



## 🛠 Tecnologias Utilizadas
Java 8+  
Programação Orientada a Objetos  
Estruturas de dados (List, Array)  
Console (Scanner)  
