# Documentação dos Padrões de Projeto
## Sistema de Gerenciamento de Fórmula 1

**Disciplina:** Padrões de Projeto de Software
**Instituição:** Instituto Federal de Santa Catarina - Câmpus Gaspar
**Curso:** Análise e Desenvolvimento de Sistemas
**Professor:** Dr. Renato Simões Moreira

---

## 1. INTRODUÇÃO

Este documento descreve a implementação de **5 padrões de projeto** (Design Patterns) aplicados em um sistema de gerenciamento de Fórmula 1. O projeto demonstra como os padrões podem resolver problemas comuns de desenvolvimento de software em um contexto real e prático.

### Padrões Implementados:
- **2 Criacionais:** Singleton e Builder
- **1 Estrutural:** Adapter
- **2 Comportamentais:** Strategy e Observer

---

## 2. PADRÕES CRIACIONAIS

### 2.1 SINGLETON - CampeonatoManager

**Arquivo:** `com.f1.padroes.criacionais.CampeonatoManager`

#### Propósito
Garantir que uma classe tenha apenas **uma única instância** em toda a aplicação e fornecer um ponto de acesso global a ela.

#### Aplicabilidade
No contexto de Fórmula 1, o gerenciador de campeonato deve ser único. Não pode existir múltiplas instâncias gerenciando o mesmo campeonato, pois isso causaria inconsistências nos dados de:
- Carros inscritos
- Pontuação dos pilotos
- Histórico de corridas
- Classificação geral

#### Justificativa
Em um campeonato real da FIA, existe apenas **um** órgão regulador oficial que controla todas as corridas, pontuações e classificações. Múltiplas instâncias causariam:
- ❌ Pontuações duplicadas ou conflitantes
- ❌ Classificações inconsistentes
- ❌ Perda de sincronização entre dados
- ❌ Desperdício de memória

#### Implementação
```java
public class CampeonatoManager {
    private static CampeonatoManager instance;

    private CampeonatoManager() {
        // Construtor privado impede instanciação externa
    }

    public static synchronized CampeonatoManager getInstance() {
        if (instance == null) {
            instance = new CampeonatoManager();
        }
        return instance;
    }
}
```

#### Características Implementadas
- ✓ **Thread-safe** com método `synchronized`
- ✓ **Lazy initialization** (criado apenas quando necessário)
- ✓ Construtor privado
- ✓ Ponto de acesso global via `getInstance()`

---

### 2.2 BUILDER - CarroF1Builder

**Arquivo:** `com.f1.padroes.criacionais.CarroF1Builder`

#### Propósito
Separar a construção de um objeto complexo de sua representação, permitindo criar diferentes representações usando o mesmo processo de construção.

#### Aplicabilidade
Um carro de Fórmula 1 é um objeto extremamente complexo com múltiplos componentes:
- Escuderia (Red Bull, Ferrari, Mercedes, etc.)
- Motor (Honda RBPT, Mercedes, Ferrari, Renault)
- Chassi (RB21, SF-25, W16, etc.)
- Potência (950-1000 HP)
- Aerodinâmica (85-95%)
- Peso (798 kg - peso mínimo regulamentar)
- Pneus (Soft, Medium, Hard)
- Piloto

#### Justificativa
Criar um carro com construtor tradicional seria complexo e propenso a erros:
```java
// Problemático - muitos parâmetros, ordem confusa
CarroF1 carro = new CarroF1("Red Bull", "Honda", "RB21", 1000, 95.5, 798.0, "Medium", piloto);
```

O Builder permite:
- ✓ Construção passo a passo com método fluente
- ✓ Código legível e autoexplicativo
- ✓ Validação antes da construção
- ✓ Flexibilidade (parâmetros opcionais)

#### Implementação
```java
CarroF1 carro = new CarroF1Builder()
    .comEscuderia("Red Bull Racing")
    .comMotor("Honda RBPT")
    .comChassi("RB21")
    .comPotencia(1000)
    .comAerodinamica(95.5)
    .comPiloto(verstappen)
    .build();
```

#### Características Implementadas
- ✓ **Method chaining** (encadeamento de métodos)
- ✓ Validação de campos obrigatórios no `build()`
- ✓ Valores padrão para campos opcionais
- ✓ Interface fluente e legível

---

## 3. PADRÃO ESTRUTURAL

### 3.1 ADAPTER - TelemetriaAdapter

**Arquivo:** `com.f1.padroes.estruturais.TelemetriaAdapter`

#### Propósito
Converter a interface de uma classe em outra interface esperada pelos clientes, permitindo que classes com interfaces incompatíveis trabalhem juntas.

#### Aplicabilidade
Sistemas de telemetria externos (usados por equipes e TV) esperam dados em formatos específicos diferentes da estrutura interna do `CarroF1`. O Adapter permite:
- Integração com sistemas legados
- Conversão de dados sem modificar a classe original
- Compatibilidade com múltiplos formatos de saída

#### Justificativa
Na Fórmula 1 real, diferentes sistemas precisam acessar dados dos carros:
- **TV/Transmissão:** Velocidade, posição, status
- **Equipe:** Temperatura motor, pressão pneus, combustível
- **FIA:** Dados regulatórios e conformidade

Cada sistema tem seu próprio formato de interface. O Adapter permite integração **sem modificar** a classe `CarroF1` original.

#### Implementação
```java
public class TelemetriaAdapter implements SistemaTelemetria {
    private CarroF1 carro;

    @Override
    public double getVelocidadeMaxima() {
        return carro.getPotencia() * 0.35; // Conversão adaptada
    }

    @Override
    public double getTemperaturaMotor() {
        return 850 + (carro.getPotencia() - 950) * 0.1; // Simulação
    }
}
```

#### Características Implementadas
- ✓ Interface `SistemaTelemetria` define contrato esperado
- ✓ Adapter converte dados do `CarroF1` para formato esperado
- ✓ Classe original (`CarroF1`) não é modificada
- ✓ Facilita testes e manutenção

---

## 4. PADRÕES COMPORTAMENTAIS

### 4.1 STRATEGY - Estratégias de Corrida

**Arquivos:**
- `com.f1.padroes.comportamentais.EstrategiaCorrida` (interface)
- `com.f1.padroes.comportamentais.EstrategiaUmaParada`
- `com.f1.padroes.comportamentais.EstrategiaDuasParadas`
- `com.f1.padroes.comportamentais.EstrategiaAgressiva`
- `com.f1.padroes.comportamentais.GerenciadorEstrategia`

#### Propósito
Definir uma família de algoritmos, encapsular cada um deles e torná-los intercambiáveis. O Strategy permite que o algoritmo varie independentemente dos clientes que o utilizam.

#### Aplicabilidade
Durante uma corrida de F1, as equipes podem escolher diferentes estratégias de pit stop:
- **Uma Parada:** Conservadora, economia de combustível
- **Duas Paradas:** Balanceada, pneus sempre frescos
- **Agressiva:** Ataque total, maior risco

A estratégia pode mudar **durante a corrida** devido a:
- Safety Car
- Condições climáticas
- Posição na pista
- Estado dos pneus

#### Justificativa
Sem o Strategy, seria necessário usar `if/else` para cada estratégia:
```java
// Problemático
if (estrategia.equals("uma parada")) {
    // lógica complexa
} else if (estrategia.equals("duas paradas")) {
    // lógica complexa
}
```

Com Strategy:
```java
// ✓ Limpo e extensível
gerenciador.definirEstrategia(new EstrategiaAgressiva());
gerenciador.executarEstrategia();
```

#### Características Implementadas
- ✓ Interface comum (`EstrategiaCorrida`)
- ✓ Múltiplas implementações concretas
- ✓ Troca de estratégia em tempo de execução
- ✓ Código fechado para modificação, aberto para extensão (OCP)

---

### 4.2 OBSERVER - Sistema de Notificações

**Arquivos:**
- `com.f1.padroes.comportamentais.SistemaNotificacoes` (Subject)
- `com.f1.padroes.comportamentais.EventoObserver` (interface)
- `com.f1.padroes.comportamentais.EquipeObserver`
- `com.f1.padroes.comportamentais.MidiaObserver`
- `com.f1.padroes.comportamentais.TorcedorObserver`

#### Propósito
Definir uma dependência um-para-muitos entre objetos, de modo que quando um objeto muda de estado, todos os seus dependentes são notificados e atualizados automaticamente.

#### Aplicabilidade
Durante uma corrida, vários interessados precisam ser notificados de eventos:
- **Equipes:** Pit stops, ultrapassagens, incidentes
- **Mídia:** Eventos para transmissão ao vivo
- **Torcedores:** Notificações sobre pilotos favoritos

#### Justificativa
Na F1 real, quando ocorre um evento importante (Safety Car, acidente, pole position), múltiplos sistemas precisam reagir simultaneamente. O Observer permite:
- ✓ Adicionar/remover observadores dinamicamente
- ✓ Desacoplamento entre Subject e Observers
- ✓ Notificação automática de todos os interessados
- ✓ Cada observador reage de forma específica

#### Implementação
```java
SistemaNotificacoes sistema = new SistemaNotificacoes();
sistema.adicionarObservador(new EquipeObserver("Red Bull"));
sistema.adicionarObservador(new MidiaObserver("ESPN"));
sistema.adicionarObservador(new TorcedorObserver("Verstappen"));

sistema.notificarEvento("Safety Car deployed!");
// Todos os observadores são notificados automaticamente
```

#### Características Implementadas
- ✓ Subject mantém lista de observadores
- ✓ Interface comum para todos os observadores
- ✓ Notificação automática em cascata
- ✓ Observadores podem se registrar/desregistrar dinamicamente

---

## 5. CONCLUSÃO

Os 5 padrões implementados trabalham de forma integrada para criar um sistema robusto:

1. **Singleton** garante consistência global do campeonato
2. **Builder** facilita criação de objetos complexos (carros)
3. **Adapter** permite integração com sistemas externos
4. **Strategy** oferece flexibilidade nas decisões de corrida
5. **Observer** permite comunicação eficiente entre componentes

### Benefícios Alcançados
- ✓ Código mais limpo e organizado
- ✓ Manutenibilidade facilitada
- ✓ Extensibilidade sem modificar código existente
- ✓ Baixo acoplamento entre componentes
- ✓ Alta coesão dentro de cada classe
- ✓ Reusabilidade de código

### Lições Aprendidas
Os padrões de projeto não são apenas conceitos teóricos - eles resolvem **problemas reais** que aparecem constantemente no desenvolvimento de software. Este projeto demonstra como aplicá-los em um contexto prático e compreensível.

---

**Referências:**
- GoF (Gang of Four) - Design Patterns: Elements of Reusable Object-Oriented Software
- Código-fonte completo disponível no repositório GitHub
