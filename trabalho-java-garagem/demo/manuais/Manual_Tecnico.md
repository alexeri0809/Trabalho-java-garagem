# Manual Técnico — Projeto Garagem (Java)

> 📘 Este documento é o \*\*Manual Técnico\*\*. Para instruções de utilização do programa, consulta o \[Manual do Utilizador](./Manual\_Utilizador.md).

\---

## 1\. Descrição do projeto

Aplicação de consola em **Java**, sem dependências externas, que simula uma garagem com capacidade máxima de **5 lugares**, independentemente do tipo de veículo estacionado (ex: 5 motas, 3 barcos + 2 carros, etc.).

O utilizador escolhe, através de um menu interativo no terminal, que veículos quer adicionar (**Carro**, **Barco** ou **Mota**), sem necessidade de sair do programa para consultar o estado da garagem.

Cada veículo recebe automaticamente uma **matrícula gerada aleatoriamente pelo código**, com formato diferente consoante o tipo:

|Tipo de veículo|Formato da matrícula|Exemplo|
|-|-|-|
|Carro / Mota|Terrestre (2 letras - 2 dígitos - 2 letras)|`FS-13-HD`|
|Barco|Marítima (distrito - capitania - dígito - 2 dígitos - 2 dígitos)|`6º-CO-7-15-21`|

\---

## 2\. Estrutura do projeto

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
```

A organização em subpacotes (`veiculo`, `matricula`, `garagem`, `fabrica`, `facade`) separa o código por responsabilidade, o que facilita a leitura e coincide com a aplicação dos padrões de desenho descritos na secção seguinte.

**Nota:** o `pom.xml` existe (projeto gerado com Maven), mas não foi necessário configurar nada nele — não há dependências externas. O projeto compila e corre apenas com `javac`/`java`.

\---

## 3\. Padrões de desenho (Design Patterns) utilizados

O trabalho exigia a aplicação dos seguintes padrões, organizados pelas categorias do catálogo (Refactoring Guru):

### 🟢 Criacional — Factory Method

* **Onde:** pacote `fabrica`
* **Classes:** `VeiculoFactory` (classe base abstrata) → `CarroFactory`, `BarcoFactory`, `MotaFactory`
* **Porquê:** cada subclasse decide, através do método `criarVeiculo()`, que tipo concreto de veículo é instanciado, sem o código cliente (`GaragemFacade`) precisar de conhecer os detalhes de construção de cada classe.

### 🔵 Estrutural — Composite

* **Onde:** pacote `veiculo` (interface) e `garagem` (implementação)
* **Classes:** `ElementoGaragem` (interface comum) → `Veiculo` (folha) e `GrupoVeiculos` / `Garagem` (compostos)
* **Porquê:** um veículo individual, um grupo de veículos (ex: "Carros") e a garagem inteira respondem à mesma interface (`contarVeiculos()`, `mostrar()`), permitindo tratar peças individuais e agrupamentos de forma uniforme.

### 🔵 Estrutural — Facade

* **Onde:** pacote `facade`
* **Classe:** `GaragemFacade`
* **Porquê:** o `Main.java` só comunica com esta classe. A `Facade` esconde a complexidade interna (fábricas, garagem, geração de matrículas, iterador) atrás de métodos simples: `adicionarVeiculo(...)`, `mostrarGaragem()`, `mostrarMatriculas()`.

### 🔴 Comportamental — Strategy

* **Onde:** pacote `matricula`
* **Classes:** `EstrategiaMatricula` (interface) → `MatriculaTerrestre`, `MatriculaMaritima`
* **Porquê:** o algoritmo de geração da matrícula varia consoante o tipo de veículo. Cada `Veiculo` recebe a sua estratégia no construtor (`Carro`/`Mota` usam `MatriculaTerrestre`; `Barco` usa `MatriculaMaritima`), permitindo trocar o algoritmo sem alterar a classe `Veiculo`.

### 🔴 Comportamental — Iterator

* **Onde:** pacote `garagem`
* **Classe:** `IteradorGaragem`
* **Porquê:** permite percorrer todos os veículos da garagem, grupo a grupo (ordem Carro → Barco → Mota), sem expor a estrutura interna (`Map<TipoVeiculo, GrupoVeiculos>`). A `Garagem` implementa `Iterable<Veiculo>`, por isso é possível usar `for (Veiculo v : garagem)`.

\---

## 4\. Cronologia do desenvolvimento, dificuldades e soluções

|#|Dificuldade / Erro encontrado|Causa|Solução aplicada|
|-|-|-|-|
|1|`mvn` não reconhecido no terminal (`CommandNotFoundException`)|Maven não estava instalado / não estava no PATH|Passou a compilar-se e a correr-se o projeto diretamente com `javac` e `java`, sem depender do Maven|
|2|`javac`/`java` não reconhecidos|JDK não estava instalado nem no PATH|Instalação do JDK (Eclipse Temurin) em formato **`.zip`** (sem instalador), por não haver acesso de administrador|
|3|Impossibilidade de instalar programas como administrador|Utilizador sem permissões de admin na máquina|Uso do PATH ao nível do **utilizador** (`\[Environment]::SetEnvironmentVariable(..., "User")`), que não exige privilégios de administrador|
|4|`error: Invalid filename: ...com\\example\*.java`|Erro de digitação no comando (faltava uma barra `\\` antes de `\*.java`)|Correção do comando para `src\\main\\java\\com\\example\\\*.java`|
|5|`class GeradorMatricula is public, should be declared in a file named GeradorMatricula.java`|Em Java, o nome do ficheiro tem de coincidir com o nome da classe pública nele|Ficheiro renomeado para corresponder ao nome da classe|
|6|Barco com matrícula no mesmo formato do Carro/Mota|Uma única classe geradora tratava todos os veículos da mesma forma|Separação em duas estratégias de geração (`MatriculaTerrestre` e `MatriculaMaritima`), aplicando o padrão **Strategy**|
|7|Necessidade de reestruturar `GeradorMatricula` em três classes (`Matricula`, `MatriculaTerrestre`, `MatriculaMaritima`)|Pedido de reorganização do código para refletir melhor as responsabilidades|Criada `Matricula` como base abstrata com utilitários comuns (letras/dígitos aleatórios), e as duas subclasses específicas|
|8|Necessidade de aplicar 5 padrões de desenho ao código já existente, mantendo tudo funcional|Requisito do trabalho|Refatoração incremental (Factory Method, Composite, Facade, Strategy, Iterator), testada após cada alteração para garantir que o comportamento se mantinha|
|9|Ficheiros todos soltos dentro de `com.example`|Organização inicial pouco escalável|Reorganização em subpacotes (`veiculo`, `matricula`, `garagem`, `fabrica`, `facade`), com atualização de `package` e `import` em cada ficheiro|
|10|Acentuação incorreta no terminal Windows (ex: "Opção" a aparecer com caracteres estranhos)|Codificação do terminal diferente de UTF-8|Recomendado o uso de `-encoding UTF-8` na compilação e `-Dfile.encoding=UTF-8` na execução|

\---

## 5\. Ferramentas, linguagens e plataformas utilizadas

|Ferramenta / Plataforma|Utilização no projeto|
|-|-|
|**Java (JDK)**|Linguagem principal do projeto (Eclipse Temurin, instalado via `.zip`, sem necessidade de administrador)|
|**Maven**|Estrutura inicial do projeto (`pom.xml`), embora não tenha sido necessário para compilar/correr o programa|
|**Visual Studio Code (VS Code)**|Editor utilizado para escrever e organizar o código|
|**PowerShell / CMD (Windows)**|Terminal utilizado para compilar (`javac`) e correr (`java`) o programa|
|**GitHub**|Plataforma utilizada no âmbito do projeto|
|**Claude**|Assistente de IA utilizado para gerar, corrigir e refatorar o código, resolver erros de compilação/ambiente, aplicar os padrões de desenho e redigir esta documentação|
|**ChatGPT**|Assistente de IA utilizado como apoio adicional ao longo do desenvolvimento|
|**Refactoring Guru** ([refactoring.guru](https://refactoring.guru))|Referência de consulta sobre os padrões de desenho (Factory Method, Composite, Facade, Strategy, Iterator)|

\---

## 6\. Compilar e correr o projeto

Sem Maven, diretamente com `javac`/`java`, a partir da pasta `demo`:

```powershell
# Apagar classes compiladas antigas (evita ficheiros desatualizados misturados)
Remove-Item -Recurse -Force target\\classes

# Compilar todos os .java, incluindo os que estão em subpastas
javac -d target\\classes -encoding UTF-8 (Get-ChildItem -Recurse -Filter \*.java src\\main\\java).FullName

# Correr o programa
java -cp target\\classes com.example.Main
```

Para instruções de utilização do menu e exemplos de utilização, consulta o [**Manual do Utilizador**](./Manual_Utilizador.md).

\---

## 7\. Possíveis melhorias futuras

* Validação para impedir matrículas repetidas dentro da mesma garagem.
* Testes automatizados (JUnit) na pasta `src/test/java`.
* Persistência dos dados (ex: gravar/ler o estado da garagem de um ficheiro).
* Opção para remover veículos da garagem através do menu.

