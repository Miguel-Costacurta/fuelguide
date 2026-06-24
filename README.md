# ⛽ FuelGuide

**FuelGuide** é uma aplicação web para consulta e comparação de postos de combustível. Com um banco de dados de mais de 6.000 postos, o usuário pode pesquisar postos próximos e comparar preços de combustíveis de forma prática.

---

## 🗂️ Estrutura do Projeto

```
fuelguide/
├── api/               # Backend em Java (API REST)
├── frontend/          # Frontend em TypeScript
└── banco de postos.csv  # Base de dados com +6.000 postos cadastrados
```

---

## 🛠️ Tecnologias Utilizadas

| Camada    | Tecnologia  |
|-----------|-------------|
| Backend   | Java        |
| Frontend  | TypeScript  |
| Dados     | CSV         |

---

## 🚀 Como Rodar o Projeto

### Pré-requisitos

Antes de começar, você precisa ter instalado na sua máquina:

- [Java JDK 17+](https://www.oracle.com/java/technologies/downloads/)
- [Node.js](https://nodejs.org/) (para o frontend)
- [Git](https://git-scm.com/)

### 1. Clone o repositório

```bash
git clone https://github.com/Miguel-Costacurta/fuelguide.git
cd fuelguide
```

### 2. Rode o backend (API Java)

```bash
cd api
# Compile e execute com sua IDE (ex: IntelliJ IDEA) ou via terminal:
./mvnw spring-boot:run
```

### 3. Rode o frontend

```bash
cd frontend
npm install
npm run dev
```

### 4. Acesse no navegador

```
http://localhost:3000
```

---

## 📊 Banco de Dados de Postos

O arquivo `banco de postos.csv` contém informações de **mais de 6.000 postos de combustível** cadastrados, incluindo dados como localização, bandeira e preços.

---

## 👤 Autor

**Miguel Costacurta**  
[github.com/Miguel-Costacurta](https://github.com/Miguel-Costacurta)

---

## 📄 Licença

Este projeto está sob a licença MIT. Consulte o arquivo `LICENSE` para mais detalhes.
