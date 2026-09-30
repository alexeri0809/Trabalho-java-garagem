# Manual Técnico — Projeto Garagem (Java)

> 📘 Este documento é o **Manual Técnico**. Para instruções de utilização do programa, consulta o [Manual do Utilizador](./Manual_Utilizador.md).

---

## 1. Descrição do projeto

Aplicação de consola em **Java**, gerida com **Maven**, que simula uma garagem com capacidade máxima de **5 lugares**, independentemente do tipo de veículo estacionado (ex: 5 motas, 3 barcos + 2 carros, etc.).

O utilizador escolhe, através de um menu interativo no terminal, que veículos quer adicionar (**Carro**, **Barco** ou **Mota**), sem necessidade de sair do programa para consultar o estado da garagem.

Cada veículo recebe automaticamente uma **matrícula gerada aleatoriamente pelo código**, com formato diferente consoante o tipo:

| Tipo de veículo | Formato da matrícula | Exemplo |
|---|---|---|
| Carro / Mota | Terrestre (2 letras - 2 dígitos - 2 letras) | `FS-13-HD` |
| Barco | Marítima (distrito - capitania - dígito - 2 dígitos - 2 dígitos) | `6º-CO-7-15-21` |

O projeto inclui também um conjunto de **testes unitários** (JUnit 5), correndo via Maven, que validam o comportamento das principais classes.

---

## 2. Estrutura do projeto

```
trabalho-java-garagem
└── demo
    ├── pom.xml
    └── src
        ├── main
        │   ├── java
        │   │   └── com
        │   │       └── example
        │   │           ├── Main.java
        │   │           ├── veiculo/
        │   │           │   ├── Veiculo.java
        │   │           │   ├── Carro.java
        │   │           │   ├── Barco.java
        │   │           │   ├── Mota.java
        │   │           │   ├── TipoVeiculo.java
        │   │           │   └── ElementoGaragem.java
        │   │           ├── matricula/
        │   │           │   ├── EstrategiaMatricula.java
        │   │           │   ├── Matricula.java
        │   │           │   ├── MatriculaTerrestre.java
        │   │           │   └── MatriculaMaritima.java
        │   │           ├── garagem/
        │   │           │   ├── Garagem.java
        │   │           │   ├── GrupoVeiculos.java
        │   │           │   └── IteradorGaragem.java
        │   │           ├── fabrica/
        │   │           │   ├── VeiculoFactory.java
        │   │           │   ├── CarroFactory.java
        │   │           │   ├── BarcoFactory.java
        │   │           │   └── MotaFactory.java
        │   │           └── facade/
        │   │               └── GaragemFacade.java
        │   └── resources
        └── test
            └── java
                └── com
                    └── example
                        ├── matricula/
                        │   ├── MatriculaTerrestreTest.java
                        │   └── MatriculaMaritimaTest.java
                        ├── garagem/
                        │   └── GaragemTest.java
                        ├── fabrica/
                        │   └── VeiculoFactoryTest.java
                        └── facade/
                            └── GaragemFacadeTest.java
```

A organização em subpacotes (`veiculo`, `matricula`, `garagem`, `fabrica`, `facade`) separa o código por responsabilidade, o que facilita a leitura e coincide com a aplicação dos padrões de desenho descritos na secção seguinte. A estrutura de `src/test/java` espelha exatamente a de `src/main/java`, como é habitual num projeto Maven.

---

## 3. Padrões de desenho (Design Patterns) utilizados

O trabalho exigia a aplicação dos seguintes padrões, organizados pelas categorias do catálogo (Refactoring Guru):

### 🟢 Criacional — Factory Method
- **Onde:** pacote `fabrica`
- **Classes:** `VeiculoFactory` (classe base abstrata) → `CarroFactory`, `BarcoFactory`, `MotaFactory`
- **Porquê:** cada subclasse decide, através do método `criarVeiculo()`, que tipo concreto de veículo é instanciado, sem o código cliente (`GaragemFacade`) precisar de conhecer os detalhes de construção de cada classe.

### 🔵 Estrutural — Composite
- **Onde:** pacote `veiculo` (interface) e `garagem` (implementação)
- **Classes:** `ElementoGaragem` (interface comum) → `Veiculo` (folha) e `GrupoVeiculos` / `Garagem` (compostos)
- **Porquê:** um veículo individual, um grupo de veículos (ex: "Carros") e a garagem inteira respondem à mesma interface (`contarVeiculos()`, `mostrar()`), permitindo tratar peças individuais e agrupamentos de forma uniforme.

### 🔵 Estrutural — Facade
- **Onde:** pacote `facade`
- **Classe:** `GaragemFacade`
- **Porquê:** o `Main.java` só comunica com esta classe. A `Facade` esconde a complexidade interna (fábricas, garagem, geração de matrículas, iterador) atrás de métodos simples: `adicionarVeiculo(...)`, `mostrarGaragem()`, `mostrarMatriculas()`.

### 🔴 Comportamental — Strategy
- **Onde:** pacote `matricula`
- **Classes:** `EstrategiaMatricula` (interface) → `MatriculaTerrestre`, `MatriculaMaritima`
- **Porquê:** o algoritmo de geração da matrícula varia consoante o tipo de veículo. Cada `Veiculo` recebe a sua estratégia no construtor (`Carro`/`Mota` usam `MatriculaTerrestre`; `Barco` usa `MatriculaMaritima`), permitindo trocar o algoritmo sem alterar a classe `Veiculo`.

### 🔴 Comportamental — Iterator
- **Onde:** pacote `garagem`
- **Classe:** `IteradorGaragem`
- **Porquê:** permite percorrer todos os veículos da garagem, grupo a grupo (ordem Carro → Barco → Mota), sem expor a estrutura interna (`Map<TipoVeiculo, GrupoVeiculos>`). A `Garagem` implementa `Iterable<Veiculo>`, por isso é possível usar `for (Veiculo v : garagem)`.

---

## 4. Testes unitários (JUnit 5)

Os testes correm através do Maven (`mvn test`) e ficam em `src/test/java`, espelhando a estrutura de pacotes do código principal.

| Classe de teste | O que valida |
|---|---|
| `MatriculaTerrestreTest` | O formato da matrícula de Carro/Mota está correto (`FS-13-HD`) |
| `MatriculaMaritimaTest` | O formato da matrícula de Barco está correto (`6º-CO-7-15-21`) |
| `GaragemTest` | A garagem começa vazia, nunca ultrapassa 5 lugares e aceita qualquer combinação de tipos |
| `VeiculoFactoryTest` | Cada fábrica (`CarroFactory`, `BarcoFactory`, `MotaFactory`) cria o tipo certo, com a marca/modelo corretos |
| `GaragemFacadeTest` | A `GaragemFacade` respeita o limite de 5 lugares |

Resultado obtido na última execução: **9 testes, 0 falhas, 0 erros** (`BUILD SUCCESS`).

### Dependências necessárias no `pom.xml`

```xml
<dependencies>
    <dependency>
        <groupId>org.junit.jupiter</groupId>
        <artifactId>junit-jupiter</artifactId>
        <version>5.10.2</version>
        <scope>test</scope>
    </dependency>
</dependencies>

<build>
    <plugins>
        <plugin>
            <groupId>org.apache.maven.plugins</groupId>
            <artifactId>maven-surefire-plugin</artifactId>
            <version>3.2.5</version>
        </plugin>
    </plugins>
</build>
```

### Correr os testes

```powershell
mvn test
```

---

## 5. Cronologia do desenvolvimento, dificuldades e soluções

| # | Dificuldade / Erro encontrado | Causa | Solução aplicada |
|---|---|---|---|
| 1 | `mvn` não reconhecido no terminal | Maven não estava instalado / não estava no PATH | Passou a compilar-se e a correr-se o projeto diretamente com `javac`/`java`, sem depender do Maven (numa fase inicial) |
| 2 | `javac`/`java` não reconhecidos | JDK não estava instalado nem no PATH | Instalação do JDK (Eclipse Temurin) em formato **`.zip`** (sem instalador), por não haver acesso de administrador |
| 3 | Impossibilidade de instalar programas como administrador | Utilizador sem permissões de admin na máquina | Uso do PATH ao nível do **utilizador** (`[Environment]::SetEnvironmentVariable(..., "User")`), que não exige privilégios de administrador |
| 4 | `error: Invalid filename: ...com\example*.java` | Erro de digitação no comando (faltava uma barra `\` antes de `*.java`) | Correção do comando para `src\main\java\com\example\*.java` |
| 5 | `class GeradorMatricula is public, should be declared in a file named GeradorMatricula.java` | Em Java, o nome do ficheiro tem de coincidir com o nome da classe pública nele | Ficheiro renomeado para corresponder ao nome da classe |
| 6 | Barco com matrícula no mesmo formato do Carro/Mota | Uma única classe geradora tratava todos os veículos da mesma forma | Separação em duas estratégias de geração (`MatriculaTerrestre` e `MatriculaMaritima`), aplicando o padrão **Strategy** |
| 7 | Necessidade de reestruturar `GeradorMatricula` em três classes (`Matricula`, `MatriculaTerrestre`, `MatriculaMaritima`) | Pedido de reorganização do código para refletir melhor as responsabilidades | Criada `Matricula` como base abstrata com utilitários comuns (letras/dígitos aleatórios), e as duas subclasses específicas |
| 8 | Necessidade de aplicar 5 padrões de desenho ao código já existente, mantendo tudo funcional | Requisito do trabalho | Refatoração incremental (Factory Method, Composite, Facade, Strategy, Iterator), testada após cada alteração para garantir que o comportamento se mantinha |
| 9 | Ficheiros todos soltos dentro de `com.example` | Organização inicial pouco escalável | Reorganização em subpacotes (`veiculo`, `matricula`, `garagem`, `fabrica`, `facade`), com atualização de `package` e `import` em cada ficheiro |
| 10 | Acentuação incorreta no terminal Windows (ex: "Opção" a aparecer com caracteres estranhos) | Codificação do terminal diferente de UTF-8 | Uso de `-encoding UTF-8` na compilação e `-Dfile.encoding=UTF-8` na execução |
| 11 | `pom.xml` com o bloco `<dependency>` fora da tag `<project>` | Erro de colagem/formatação do XML ao adicionar a dependência do JUnit | `<dependency>` movido para dentro de `<dependencies>`, corretamente aninhado dentro de `<project>` |
| 12 | `winget install Apache.Maven` → "No package found matching input criteria" | O Maven não está disponível no catálogo do `winget` | Instalação manual do Maven em formato **`.zip`** (mesmo método usado para o JDK) |
| 13 | Depois de definir o PATH, `mvn` continuava "não reconhecido" | O PowerShell só lê o PATH quando a janela abre; alterar a variável de ambiente não atualiza uma sessão já aberta | Atualização do PATH da sessão atual com `$env:Path = [Environment]::GetEnvironmentVariable("Path","User") + ";" + [Environment]::GetEnvironmentVariable("Path","Machine")`, ou abertura de um terminal novo |
| 14 | Aviso `Using platform encoding UTF-8... build is platform dependent` no `mvn test` | O `pom.xml` não define explicitamente o encoding do projeto | Sugerida a adição de `<project.build.sourceEncoding>UTF-8</project.build.sourceEncoding>` em `<properties>` (opcional, não bloqueia o build) |
| 15 | `mvn compile exec:java -Dexec.mainClass="com.example.Main"` → `[ERROR] Unknown lifecycle phase ".mainClass=com.example.Main"` | O PowerShell interpreta mal as aspas quando só envolvem o valor a seguir ao `-D`, partindo o argumento a meio do `=` | Colocar as aspas à volta do argumento inteiro: `mvn compile exec:java "-Dexec.mainClass=com.example.Main"` |

---

## 6. Ferramentas, linguagens e plataformas utilizadas

| Ferramenta / Plataforma | Utilização no projeto |
|---|---|
| **Java (JDK)** | Linguagem principal do projeto (Eclipse Temurin, instalado via `.zip`, sem necessidade de administrador) |
| **Maven** | Gestão de dependências, compilação e execução dos testes unitários (`mvn test`); instalado via `.zip`, sem necessidade de administrador |
| **JUnit 5 (Jupiter)** | Framework de testes unitários usado para validar matrículas, garagem, fábricas e facade |
| **Visual Studio Code (VS Code)** | Editor utilizado para escrever e organizar o código |
| **PowerShell / CMD (Windows)** | Terminal utilizado para compilar, correr e testar o programa |
| **GitHub** | Plataforma utilizada no âmbito do projeto |
| **Claude** | Assistente de IA utilizado para gerar, corrigir e refatorar o código, resolver erros de compilação/ambiente, aplicar os padrões de desenho, configurar os testes unitários e redigir esta documentação |
| **ChatGPT** | Assistente de IA utilizado como apoio adicional ao longo do desenvolvimento |
| **Refactoring Guru** ([refactoring.guru](https://refactoring.guru)) | Referência de consulta sobre os padrões de desenho (Factory Method, Composite, Facade, Strategy, Iterator) |

---

## 7. Compilar, correr e testar o projeto

A partir da pasta `demo` (onde está o `pom.xml`):

```powershell
# Compilar o projeto
mvn compile

# Correr os testes unitários
mvn test

# Compilar e correr o programa (nota as aspas à volta do argumento inteiro)
mvn compile exec:java "-Dexec.mainClass=com.example.Main"
```

Se o `mvn` não for reconhecido, confirma se abriste um terminal **novo** depois de o instalares — ver ponto 13 da tabela de erros acima. Se o `exec:java` falhar com `Unknown lifecycle phase`, ver ponto 15 (é um problema de aspas do PowerShell, não do Maven).

### Alternativa sem Maven (javac/java diretos)

Caso o `exec:java` continue a dar problemas, ou simplesmente para correr o programa sem depender de plugins do Maven, é possível compilar e correr diretamente com `javac`/`java`:

```powershell
Remove-Item -Recurse -Force target\classes
javac -d target\classes -encoding UTF-8 (Get-ChildItem -Recurse -Filter *.java src\main\java).FullName
java -cp target\classes com.example.Main
```

O `Remove-Item` apaga as classes compiladas antigas (evita ficheiros desatualizados misturados). O `Get-ChildItem -Recurse` é necessário porque o código está organizado em subpacotes (`veiculo`, `matricula`, `garagem`, `fabrica`, `facade`) — um simples `*.java` não apanharia os ficheiros dentro dessas subpastas. Este método **não corre os testes unitários**; para isso continua a ser necessário o `mvn test`.

Para instruções de utilização do menu e exemplos de utilização, consulta o **[Manual do Utilizador](./Manual_Utilizador.md)**.

---

## 8. Possíveis melhorias futuras

- Validação para impedir matrículas repetidas dentro da mesma garagem.
- Mais testes unitários (ex: casos-limite da `GaragemFacade`, `IteradorGaragem`).
- Persistência dos dados (ex: gravar/ler o estado da garagem de um ficheiro).
- Opção para remover veículos da garagem através do menu.
