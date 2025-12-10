# Sistema F1 - Demonstracao de Padroes de Projeto

Sistema de Gerenciamento de Formula 1 desenvolvido em Java para demonstrar padroes de projeto (Design Patterns).

---

## Integrantes

**RODRIGO CUSTODIO MULLER**
- Matricula: 202310208844

---

## Video de Apresentacao

> **Importante:** Substitua `SEU_LINK_DO_YOUTUBE_AQUI` pelo link real do video apos fazer o upload.

---

## Padroes Implementados

Este projeto demonstra **5 padroes de projeto** essenciais aplicados em um contexto real de Formula 1:

### Criacionais (2):
- **Singleton** - CampeonatoManager
- **Builder** - CarroF1Builder

### Estrutural (1):
- **Adapter** - TelemetriaAdapter

### Comportamentais (2):
- **Strategy** - Estrategias de Corrida
- **Observer** - Sistema de Notificacoes

---

## Documentacao Completa

Para informacoes detalhadas sobre a aplicabilidade e justificativa de cada padrao de projeto, consulte:



## Detalhamento dos Padroes

### 1. SINGLETON - Criacional
**Classe:** `CampeonatoManager`

**Proposito:** Garantir que exista apenas uma instancia do gerenciador de campeonato.
**Aplicacao:** Gerenciamento centralizado de corridas, pontuacoes e classificacoes.
**Justificativa:** Em um campeonato real, deve existir apenas UM gerenciador oficial que controla todas as corridas.


### 2. BUILDER - Criacional
**Classe:** `CarroF1Builder`

**Proposito:** Construir objetos complexos passo a passo.
**Aplicacao:** Criacao de carros de F1 com multiplos componentes.
**Justificativa:** Um carro de F1 possui multiplos componentes (motor, chassi, aerodinamica, potencia). O Builder facilita a criacao passo a passo com validacao.


### 3. ADAPTER - Estrutural
**Classe:** `TelemetriaAdapter`

**Proposito:** Converter a interface de uma classe em outra esperada.
**Aplicacao:** Adaptar CarroF1 para sistemas de telemetria externos.
**Justificativa:** Sistemas de telemetria externos podem usar formatos diferentes. O Adapter permite integracao sem modificar o CarroF1 original.


### 4. STRATEGY - Comportamental
**Classes:** `EstrategiaCorrida`, `GerenciadorEstrategia`, `EstrategiaUmaParada`, `EstrategiaDuasParadas`, `EstrategiaAgressiva`

**Proposito:** Definir familia de algoritmos intercambiaveis.
**Aplicacao:** Estrategias de corrida (pit stops).
**Justificativa:** Estrategias podem mudar durante a corrida. O Strategy permite trocar algoritmos em tempo de execucao.


### 5. OBSERVER - Comportamental
**Classes:** `SistemaNotificacoes`, `EventoObserver`, `EquipeObserver`, `MidiaObserver`, `TorcedorObserver`

**Proposito:** Notificar multiplos observadores sobre mudancas de estado.
**Aplicacao:** Sistema de notificacoes de eventos da corrida.
**Justificativa:** Durante uma corrida, varios interessados (equipes, midia, torcedores) precisam ser notificados. O Observer permite adicionar/remover observadores dinamicamente.

### Requisitos:
- Java 11 ou superior
- Maven 3.6+

### Comandos:

```bash
# Limpar e compilar
mvn clean compile

# Compilar e criar JAR
mvn clean package
```

---

## Como Executar

### Opção 1: Via Maven
```bash
mvn exec:java
```

### Opção 2: Via JAR
```bash
java -jar target/formula1-racing-1.0.0.jar
```

### Opção 3: Via IDE
Execute a classe `com.f1.Main`

---
### Menu Principal - Opções

### 1. Criar Carro Manualmente (Builder)
- Crie seu proprio carro configurando cada componente
- Define piloto, escuderia, motor, chassi, potencia, aerodinamica
- Valida campos obrigatorios antes de construir

### 2. Cadastrar Pilotos Aleatorios 2025
- Cadastra 6-7 pilotos aleatorios da temporada 2025
- Lista com todos os 21 pilotos oficiais da F1 2025
- Previne duplicacoes de pilotos
- Cria carros configurados especificamente por equipe

### 3. Definir Estrategia de Corrida (Strategy)
Tres estrategias disponiveis:
**Uma Parada:** Conservadora, economia de combustivel
**Duas Paradas:** Balanceada, pneus sempre frescos
**Agressiva:** Ataque total, alto risco/recompensa

### 4. Visualizar Telemetria (Adapter)
- Velocidade maxima calculada
- Temperatura do motor simulada
- Status dos pneus
- Metricas completas do carro

### 5. Configurar Observadores (Observer)
Adicione observadores que serao notificados durante eventos:
- **Equipe**: Recebe notificacoes e analisa estrategias
- **Midia**: Transmite eventos ao vivo
- **Torcedor**: Reage quando seu piloto favorito e mencionado

### 6. Simular Corrida (Singleton + Observer)
- Usa o Singleton CampeonatoManager
- Ordena carros por desempenho
- Sistema de pontuacao F1
- Notifica todos os observadores registrados

### 7. Ver Classificacao (Singleton)
- Classificacao completa do campeonato
- Historico de vencedores
- Pontuacao acumulada de cada piloto

### 8. Demonstracao Completa
- Executa automaticamente todos os 5 padroes
- Mostra cada padrao em acao

---

## Calculo de Desempenho

Formula usada para calcular o desempenho total do carro:
Desempenho = (Potencia × 0.4) + (Aerodinamica × 0.5) - (Peso × 0.1)

---

## Caracteristicas do Projeto

- JavaDoc completo em todas as classes de padroes
- Validacao de entrada do usuario
- Menu interativo completo
- Sistema de pontuacao F1
- Dados atualizados da temporada 2025
- Sistema de cadastro aleatorio de pilotos